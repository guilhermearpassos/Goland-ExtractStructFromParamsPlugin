package com.github.guilhermearpassos.demoplugin.actions

import ai.grazie.utils.capitalize
import com.goide.GoTypes.*
import com.goide.psi.GoFile
import com.goide.psi.impl.*
import com.intellij.openapi.actionSystem.DataContext
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiReference
import com.intellij.psi.search.searches.ReferencesSearch
import com.intellij.psi.util.childrenOfType
import com.intellij.psi.util.elementType
import com.intellij.refactoring.RefactoringActionHandler
import com.intellij.refactoring.lang.ElementsHandler


class ExtractStructGenHandler : RefactoringActionHandler, ElementsHandler {
    override fun invoke(project: Project, editor: Editor, file: PsiFile, dataContext: DataContext) {
        if (editor.caretModel.caretCount != 1) {
            return
        }
        val document = editor.document

        if (!document.isWritable) {
            return
        }
        val structName: String
        val element = file.findElementAt(editor.selectionModel.selectionStart)?.parent
        when (element.elementType) {
            METHOD_DECLARATION -> {

                val javaMethod = element as GoMethodDeclarationImpl
                var type0 = javaMethod.receiver?.childrenOfType<GoTypeImpl>()?.get(0)
                if (type0 is GoPointerTypeImpl) {
                    type0 = type0.childrenOfType<GoTypeImpl>()[0]
                }
                structName = type0?.text.toString().capitalize() + javaMethod.name.toString().capitalize() + "Params"
            }

            FUNCTION_DECLARATION -> {

                val javaFunc = element as GoFunctionDeclarationImpl
                structName = javaFunc.name + "Params"
            }

            else -> {
                return
            }
        }
//        val packageClause = element.containingFile.childrenOfType<GoPackageClauseImpl>()
        val importPath = (element.containingFile as GoFile).getImportPath(true)
        val importAlias = (element.containingFile as GoFile).canonicalPackageName
        var signature: PsiElement? = null
        for (child in element.children) {
            if (child.elementType == SIGNATURE) {

                signature = child
                break
            }
        }
        if (signature == null) {
            return
        }
        var parameters: PsiElement? = null
        for (child in signature.children) {
            if (child.elementType == PARAMETERS) {
                parameters = child
                break
            }
        }
        if (parameters == null) {
            return
        }
//        val transform: (child: PsiElement) -> String = (child) => { child.text }
        val structText = """type $structName struct {
            |${parameters.children.joinToString("\n\t", prefix = "\t", transform = { it.text.capitalize() })}
            |}
        """.trimMargin()
        val paramActions: ArrayList<Runnable> = ArrayList(parameters.children.size)
        for (param in parameters.children.reversed()) {
            val paramRefs = ReferencesSearch.search(param.children[0]).findAll()
            paramActions.add(fun() {
                for (paramRef in paramRefs) {
                    val txt = "params.${paramRef.element.text.capitalize()}"
                    val expression = GoElementFactory.createExpression(project, txt)
                    paramRef.element.replace(expression)
                }
            })

        }

        //TODO: deconstruct params on usages inside body
        //TODO: deal with package level imports
        //TODO: export all params
        //TODO: make it work with and without receiver
        val refs = ReferencesSearch.search(element)
        val allRefs = refs.findAll()
        val actions: ArrayList<Runnable> = ArrayList(allRefs.size)
        for (ref in allRefs.reversed()) {
            val act =
                handleReference(ref, importAlias.toString(), importPath.toString(), structName, parameters, project)
            actions.add(act)
        }
        WriteCommandAction.runWriteCommandAction(project) {
            for (paramAct in paramActions) {
                paramAct.run()
            }
            for (act in actions) {
                act.run()
            }
            parameters.replace(GoElementFactory.createExpression(project, "(params *$structName)"))

            val structDeclFile = GoElementFactory.createFileFromText(project, "package a\n" + structText + "\n")
            element.parent.addRangeBefore(structDeclFile.children[2], structDeclFile.children[4], element)
//            document.insertString(element.startOffset, )
            return@runWriteCommandAction Unit
        }

        return
    }

    private fun handleReference(
        ref: PsiReference,
        importAlias: String,
        importPath: String,
        structName: String,
        parameters: PsiElement,
        project: Project
    ): () -> Unit {
        val currentPackage = (ref.element.containingFile as GoFile).canonicalPackageName
        val callExpr = ref.element.parent

        if (callExpr.elementType != CALL_EXPR) return fun() {}
        //callExpr.containingFile.childrenOfType<GoImportListImpl>()
        //
        val args = ref.element.parent.children[1]
        if (args.elementType != ARGUMENT_LIST) return fun() {}
        var paramText: String
        var needImport = false
        if (currentPackage != importAlias) {
            paramText = "(&$importAlias.$structName{\n\t"
            needImport = true
        } else {
            paramText = "(&$structName{\n\t"
        }
        args.children.forEachIndexed { index: Int, psiElement: PsiElement ->
            paramText += "${parameters.children[index].children[0].text.capitalize()}: ${psiElement.text},\n\t"
        }
        paramText += "})"
        return fun() {
            args.replace(GoElementFactory.createExpression(project, paramText))
            if (needImport) {

            }
        }
    }

    override fun invoke(project: Project, elements: Array<PsiElement>, dataContext: DataContext) {
        if (elements.size != 1) return  // Julien: accept only one element

        return
    }

    override fun isEnabledOnElements(elements: Array<PsiElement>): Boolean {
        /*
    if (elements.length == 1) {
      return elements[0] instanceof PsiClass || elements[0] instanceof PsiField || elements[0] instanceof PsiMethod;
    }
    else if (elements.length > 1){
      for (int  idx = 0;  idx < elements.length;  idx++) {
        PsiElement element = elements[idx];
        if (!(element instanceof PsiField || element instanceof PsiMethod)) return false;
      }
      return true;
    }
    return false;
    */
        // todo: multiple selection etc
        return elements.size == 1
    }
//
//    //
//    companion object {
//        //        private val LOG = Logger.getInstance("#com.intellij.refactoring.memberPullUp.JavaPullUpGenHandler")
//        val REFACTORING_NAME: String = RefactoringBundle.message("pull.members.up.title")
//    }
}
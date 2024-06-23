package com.github.guilhermearpassos.extractStructFromParams.actions
import com.intellij.lang.refactoring.RefactoringSupportProvider
import com.intellij.refactoring.RefactoringActionHandler
import com.intellij.refactoring.actions.BasePlatformRefactoringAction


class ExtractStructAction : BasePlatformRefactoringAction() {
    init {
        setInjectedContext(true)
    }

    public override fun isAvailableInEditorOnly(): Boolean {
        return false
    }

    override fun getRefactoringHandler(provider: RefactoringSupportProvider): RefactoringActionHandler {
        return ExtractStructGenHandler() // FIXME : not up-to-date with JetBrains version.
    }
}
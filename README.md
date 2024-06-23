Extract Struct From Params
=======================

Plugin for Goland. Use it to extract long lists of function/method parameters into input Struct

What does it do?
----------------

For example, if you have a funcion (or method) with multiple input parameters:
```go
func DoSomethingWithManyParams(firstParam string, secondParam string, myAwesomeParam string, myAwesomeBool bool) {

}

func main() {
    DoSomethingWithManyParams("a", "bc", "de", true)
}
```
And you'd rather have something like this:
```go
type ParamsStruct struct {
    FirstParam string
    SecondParam string
    MyAwesomeParam string
    MyAwesomeBool bool
}
func DoSomethingWithManyParams(params *ParamsStruct) {

}
func main() {
    DoSomethingWithManyParams(&ParamStruct{
        FirstParam: "a",
        SecondParam: "bc",
        MyAwesomeParam: "de",
        MyAwesomeBool: true
	},
	)
}
```
You do not need to manually create the struct and replace into all usages.
Just select the function definition
<kbd>right mouse button</kbd> >  <kbd>refactor</kbd> > <kbd>extract struct ...</kbd>


How to install?
---------------

- Using the IDE built-in plugin system:

  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>Marketplace</kbd> > <kbd>Search for "ExtractStructFromParams"</kbd> >
  <kbd>Install</kbd>

- Manually:

  Download the [latest release](https://github.com/guilhermearpassos/Goland-ExtractStructFromParamsPlugin/releases/latest) and install it manually using
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>

Some important notes:
---------------------
* First of all, this plugin works only in files with extension `*.go`, for example, "main.go".
* For now there is no customization on names for params and new structType
* Final text might need formatting to comply with proper GO spacing
* Cursor must be on the function/method definition, not usages.

What can I write?
-----------------
```go
// From
func DoSomethingWithManyParams(firstParam string, secondParam string, myAwesomeParam string, myAwesomeBool bool) {

}

func main() {
    DoSomethingWithManyParams("a", "bc", "de", true)
}
// To (refactor > extract struct
type ParamsStruct struct {
    FirstParam string
    SecondParam string
    MyAwesomeParam string
    MyAwesomeBool bool
}
func DoSomethingWithManyParams(params *ParamsStruct) {

}
func main() {
    DoSomethingWithManyParams(&ParamStruct{
        FirstParam: "a",
        SecondParam: "bc",
        MyAwesomeParam: "de",
        MyAwesomeBool: true
	},
	)
}

```
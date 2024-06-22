package domain

import "fmt"

type MyStruct struct {
	A string
	B bool
}

func (a *MyStruct) DoSomethingWithManyParams(firstParam string, secondParam string, myAwesomeParam string, myAwesomeBool bool) (string, string, int, error) {
	var value int
	if myAwesomeBool {
		value = 10
	} else {
		value = 3
	}
	return firstParam + secondParam, myAwesomeParam, value, fmt.Errorf("myAwesome bool should be %t, got %t", !myAwesomeBool, myAwesomeBool)
}

func DoSomethingWithManyParams(firstParam string, secondParam string, myAwesomeParam string, myAwesomeBool bool) {

}

package gopkg_sample

import "github.com/guilhermearpassos/demoplugin/gopkgs/domain"

type MyOtherStruct struct {
	s domain.MyStruct
}

func (a *MyOtherStruct) DoSomethingWithManyParams(firstParam string, secondParam string, myAwesomeParam string, myAwesomeBool bool) {
	a.s.DoSomethingWithManyParams(firstParam, secondParam, myAwesomeParam, myAwesomeBool)
}

package main

import "github.com/guilhermearpassos/demoplugin/gopkgs/domain"

func main() {
	ms := domain.MyStruct{
		A: "",
		B: false,
	}
	a := "ahsdid"
	b := "ashdia" + a
	ms.DoSomethingWithManyParams(a, b, "asdiuha", true)
	domain.DoSomethingWithManyParams("as", "asdhiao", "asdiuha", true)
	ms.DoSomethingWithManyParams("as", "asdhiao", "asdiuha", true)
	ms.DoSomethingWithManyParams("as", "asdhiao", "asdiuha", true)
	ms.DoSomethingWithManyParams("as", "asdhiao", "asdiuha", true)
	ms.DoSomethingWithManyParams("as", "asdhiao", "asdiuha", true)
	ms.DoSomethingWithManyParams("as", "asdhiao", "asdiuha", true)
	ms.DoSomethingWithManyParams("as", "asdhiao", "asdiuha", true)
	ms.DoSomethingWithManyParams("as", "asdhiao", "asdiuha", true)
	ms.DoSomethingWithManyParams("as", "asdhiao", "asdiuha", true)
}

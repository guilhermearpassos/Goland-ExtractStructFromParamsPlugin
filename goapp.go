package main

import "github.com/guilhermearpassos/demoplugin/gopkgs/domain"

func main() {
	ms := domain.MyStruct{
		A: "",
		B: false,
	}

	ms.DoSomethingWithManyParams("as", "asdhiao", "asdiuha", true)
}

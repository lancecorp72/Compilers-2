class Main {
	main():IO {
		new IO.out_string("Hello world!\n")
	};
};

class A inherits B {
	i : Int;
};

class B inherits A {
	i : Int;
};

class C inherits D {
	i : Int;
};

class D inherits C {
	i : Int;
};

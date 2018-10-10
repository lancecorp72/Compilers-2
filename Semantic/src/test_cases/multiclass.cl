class Main {
	main():IO {
		new IO.out_string("Hello world!\n")
	};
};

class A inherits B {
	i : Int;
	foo() : Int { 1 };
};

class B {
	i : Int;
	foo(i : Int) : Int { 2 };
};


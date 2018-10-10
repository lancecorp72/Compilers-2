class Main {
	main():IO {
		new IO.out_string("Hello world!\n")
	};
};

class A inherits B {
	foo1() : Bool { true };
};

class B {
	i : Bool;
	foo(i : Int) : Int { let i : Int <-1 in i };
};


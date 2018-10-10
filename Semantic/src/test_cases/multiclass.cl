class Main {
	main():IO {
		new IO.out_string("Hello world!\n")
	};
};

class A inherits B {
	j : Int;
	foo1() : Bool { true };
};

class B {
	i : Bool;
	foo(i : Int) : Int { 1 };
};

class C {
	i : A;
	j : B;
	foo() : Object { i <- j };
};


class Main {
	main():IO {
		new IO.out_string("Hello world!\n")
	};
};

--multiple Declarations of class A
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

class A{
	j : Int <- 0;

};

class Main {
	main():IO {
		new IO.out_string("Hello world!\n")
	};
};

--multiple Declarations of class A
class A inherits B {
	foo1() : Bool { true };
};

class B {
	i : Bool;
	foo(i : Int) : Int { let i : Int <-1 in i };
};

class A{
	j : Int <- 0;

};

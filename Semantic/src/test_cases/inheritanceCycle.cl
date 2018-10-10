class Main {
	main():IO {
		new IO.out_string("Hello world!\n")
	};
};
--cycle present B <- A <- C <- B
class A inherits B {
	foo1() : Bool { true };
};

class B inherits C{
	i : Bool;
	foo(i : Int) : Int { let i : Int <-1 in i };
};

class C inherits A{
	a : String;
	foo2(i:Int) : Int { i + 2 };

};




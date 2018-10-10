--no main function
class Main {
	foo1() : Bool { true };
};


class B inherits C{
	i : Bool;
	foo(i : Int) : Int { let i : Int <-1 in i };
};

class C{
	a : String;
	foo2(i:Int) : Int { i + 2 };

};




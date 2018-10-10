--No main class

class A inherits B
{
	--non existent class B
	o:Int;

};


class D inherits C{
	i : Bool;
	foo(i : Int) : Int { let i : Int <-1 in i };
};

class C {
	a : String;
	foo2(i:Int) : Int { i + 2 };

};




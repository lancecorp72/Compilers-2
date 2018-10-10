class Main {
	a : A;
	b : B;
	main() : Int {
		{
			1;
		}
	};
};

--Multiple different Redefinitons of foo()
class A inherits B {
	j : Int;
	foo(i : Int) : Bool { true };
};

class B inherits C {
	k : Int;
	foo() : String { 2 };
};

class C {
	i : Bool;
	foo() : Int { 1 };
};


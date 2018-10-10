class Main {
	a : A;
	b : B;
	main() : Int {
		{
			a.fo(1);
			b.foo();
			1;
		}
	};
};

--multiple Declarations of class A
class A {
	j : Int;
	fo(i : Int) : Bool { true };
};

class B inherits C {
	k : Int;
	foo() : Int { 2 };
};

class C {
	i : Bool;
	foo() : Int { 1 };
};



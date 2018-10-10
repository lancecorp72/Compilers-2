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

--Multiple different Redefinitons of foo()
class A inherits B {
	j : Int;
	fo(i : Int) : Bool { true };
};

class B {
	foo() : Int { 1 };
};



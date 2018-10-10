class Main {
	a : A;
	b : B;
	main() : Int {
		{
			a.fo();
			b.foo(1);
			1;
		}
	};
};

--Multiple different Redefinitons of foo()
class A inherits B {
	j : Int;
	foo(i : Int) : Int { 2 };
	fo() : String { "s" };
};

class B {
	foo(i : Int) : Int { 1 };
	fo() : String { "t" };
};



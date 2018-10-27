class Main {
	a : A;
	b : B;
	c : C;
	main() : Int {
		{
			a.fo();
			b.foo(1);
			1;
		}
	};
};

--Multiple different Redefinitons of foo()
class A {
	j : Int;
	foo(i : Int) : Int { 2 };
	fo() : String { "s" };
};

class B inherits A {
	foo(i : Int) : Int { 1 };
	fo() : String { "t" };
};

class C inherits A {
	foo(i : Int) : Int { 1 };
	fo() : String { "t" };
};

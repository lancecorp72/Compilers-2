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

--Multiple Declarations of class A
class A {
	j : Int;
	fo(i : Int) : Bool { true };
};

class A {
	s : String;
};


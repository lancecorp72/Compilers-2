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
	i : Int;
	s : String;
	b : Bool;
};

class B {
	i : Int;
	s : String;
	b : Bool;
};




class Main {
	i : Int;

	main() : Int {
		{
			1;
		}
	};
};

--Multiple different Redefinitons of foo()
class A {
	j : Int;
	foo(i : Int) : Int { 2 };
	fo(h : Int,i : Int) : Int { 2 };
};

class B inherits A {
	foo(i : Int) : Int { 1 };

};

class C inherits A {
	foo(i : Int) : Int { 1 };

};

class Main inherits IO {
	i : Int <- 1;
	a : C <- new C;
	b : Int <- 0;
	h : Bool;
	main() : Int {
		{
			isvoid i;
		}
	};
	
	ok(a : A) : Int {
		1
	};
};

--Multiple different Redefinitons of foo()
class A inherits IO {
	j : Int;
	k : Int;
	foo(i : Int) : Int { 
		{
			j <- 2;
			j;
		}
	};
	fo(h : Int,i : Int) : Int { 1 };
	ret() : Int { j };
};

class B inherits A {

	foo(i : Int) : Int { 1 };

};

class C inherits B {
	foo(i : Int) : Int { 1 };

};

class Main inherits IO {
	i : Int;
	a : C;
	main() : Int {
		{
			a <- new C;
			a@A.foo(1);
			i <- 1;
			--if (i<5) then 2+2+1 else 2-2-1 fi;
			1;
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

class Main inherits IO {
	i : Int;

	main() : Int {
		{

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
	j : String;
	k : String;
	foo(i : Int) : Int { 
		{
			1;
		}
	};
	fo(h : Int,i : Int) : Int { 1 };
};

class B inherits A {

	foo(i : Int) : Int { 1 };

};

class C inherits B {
	foo(i : Int) : Int { 1 };

};

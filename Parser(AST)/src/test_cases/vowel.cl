-- Program which checks whether given char is a vowel
class Main inherits IO {
	c : String;
	--Multiple functions to check whether feature list is implemented properly
	main() : Object 
	{
		{
			out_string("Enter single char : ");
			c <- in_string();
			if (c.length() = 1) then
			{
				out_string(c);
				if vowel(c) then out_string(" is a vowel\n") else out_string(" is a not a vowel\n") fi;
			}
			--Validity checking
			else out_string("Enter valid single char\n")
			fi;
		}
	};

	vowel(c : String) : Bool
	{
		{
			--If else statements to check for vowel
			--True is returned if c is vowel, otherwise false is returned
			if c = "a" then true else
			if c = "e" then true else
			if c = "i" then true else
			if c = "o" then true else
			if c = "u" then true else false
			fi fi fi fi fi;
		}
	};
};

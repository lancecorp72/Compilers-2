# Compilers - 2
## Semantic Analyser for Cool
----------------------
#### Done by,
#### Akshay Raghavan V  -- CS16BTECH11041
#### Vedha Moorthy S -- CS16BTECH11040

-------------------------------------
This is the report for *Assignment 3 - Semantic Analyser for Cool* . We created a Semantic analyser for Cool using Java which produces annotated AST which is to be used by the code generator in the next assignment. Incorrect programs raises errors including lex, parse and semantic errors.

--------------------------------------
# Design Decisions
##### Creating Inheritance Graph
Class list is traversed to find the inheritance graph Which is stored as an ArrayList. Each class also has a class Index(id). The primitive classes namely Object(id = 0), IO(1), Int(2), Bool(3) and String(4) are added. Classes are added to the inheritance graph as they are discovered. 
The following are executed sequentially:
- Checks if the primitive classes are redifined
- Checks for multiple declarations of the same class
- Checks for multiple declartions of functions and attributes
- Checks if main class and main functions are present
- Checks if parent class exists and if the parent is a non-derivable class such as Int, String or Bool
- Checks for Inheritance graph and displays it as error when found

Also checks to ensure redefinition of an attribute or function (with different signature or return type)  inherited from another class are present.
##### Scope Table
Implemented the same scope table as given along with the assignment to check for scopes and availability of variables within a scope. Lookups for local and global variables are present. 
##### Errors
Errors are handled using a reportError method in Semantic class. This displays detailed errors along with file name and line number.

##### Mangled Names
Mangled names of functions contain its name, name of the class containing it, and list of arguments. This is used to unique names for each function. The list of mangled names are stored globally in a list and hence can be used to check if a function is present in the program. Parent classes' function names are mangled with child's name to show its presence in the child class. If there is another declaration of the same function in the child, its resolved or an error is thrown.
##### Visitor and Type checking
Visits all classes and checks and adds types to all the nodes. 
Appropriate errors are thrown. A few examples include "Type Mismatch", "Undefined type" etc.
Checks for conforming types for assignment are used. The conforming types are checked when the type in the left hand side is one of the ancestor class of the type on the right. Also join of multiple types are used for conditional statements and case statements by finding the least common ancestor using GetLCA(). 

--------------------------------------
# Test Cases Used
Various test cases were used to check the robustness and proper functioning of the Semantic analyser. Both correct and incorrect programs were used to test various functionalities of the analyser.The error messages that are displayed are verbose.
### Correct Programs
> to test the annotated AST

### Incorrect programs 
> to test the various errors displayed
- inheritanceCycle.cl - has a cycle in inheritance graph
- multiclass.cl - has multiple declarations of the same class
- noMainClass.cl - has no main class and a class inherits form a non existent parent
- noMainFunc.cl - has no main()
- typeCheckAndMisc.cl - checks for various type mismatch, and other miscellaneous errors

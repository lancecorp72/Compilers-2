# COOL Code Generator using Antlr and Java - README
### Done by,
>Vedhamoorthy - CS16BTECH11040
>Akshay Raghavan - CS16BTECH11041

The ojective of this assignment is to generation code from the type annotated AST created after the semantic analysis phase. When a correct cool program is given as an input to the code generator, its corresponding LLVM IR representation is emitted. The emitted LLVM representation can be converted into object code using clang command which can then be executed.
The code generator checks for errors like division by 0, dispatch to void and index out of bound error and prints appropriate error messages before aborting.
According to the problem statement, there was no necessity for handling let, case and regular dispatch.

## Overview
The Code generator does the following on a very abstract level.
* Define base functions like _in_int(), substr()_ etc.
* * The base functions call the corresponding C functions (eg: printf() is called for out_string()) which are supported in LLVM IR.
* Declare all the classes along with its attributes.
*  * All the classes are traversed and declared as struct along with its attributes.
* Define initializers of all the classes and calls them appropriately.
* * An initializer of a class store the initial values in the corresponding attributes and call other initializers (if the attribute is a class allocated using new). 
* Define the functions(in their mangled names) present in the class definitions.
* Define the main function and start execution.
* Traverse the AST and emit codes for all the required expressions.

function
value passing
optimiziation

## Design
An overloaded function called Visit is used to traverse the annotated AST to emit corresponding LLVM IR code.  
The program starts by visiting the program node of the AST. Visiting that allows us to emit code for declaring the classes as structs. Further it traverses each of the classes to emit codes which define the functions in them. Then the main function is defined and visited. The main function allocates memory for the main class and emits code for the expressions in it by traversing them accordingly. 

The values or variables (IR of which was emitted previously) obtained after evaluating(visiting) the expressions are stored as strings in the AST for accessing them later. For example, a comparison made in _lt_ expresion stores the result in some variable, say `%v5`. This variable is stored in the AST (specifically in the corresponding node) which is then accessed later by the _cond_ expression to use in the `br` statement.
```
%v5 = icmp slt i32 %v4, 0  ; emittled in AST.lt node and %v5 is saved in the AST
br i1 %v5, label %if.then, label %if.else  ;emited in AST.cond and %v5 retreived from the AST
```
Expressions(say e1 , e2..) which are a part of another expression(e) are traversed within e.  
All types of expressions are handled by our code generator.
There are other miscellaneous functions DecBaseFns() which prints code for the base functions present in cool. The AddConstructors() function adds code for the constructors of each class.

### Further Note:
* The constructors of classes are called only when they are allocated using new expression.
* Scope table is used to map an attribute to its position in the struct definition of its class.
* Code is emitted for checking if the divisior in a divison is zero. The program aborts if true.
* Dead code elimanation for predicates in conditional expression when they are Bool constants(eg : true, 0 > 1 etc).
* Loops are avoided when the loop predicate is false in the beginning.

## Test cases
When correct cool programs are given as input to the code generator, it writes the appropriate LLVM IR code in a file.
Incorrect programs include the ones which try dividing a number by zero or dispatches to void. All other errors are handled by the semantic analyser, parser and the lexer.
Varrious test cases are included which checks for robustness and correctness of the code generator.
* correctProg1.cl - handles most of the functionality of the code generator like multiple classes and functions, inheritance, usage of base functions and most of the expressions like assignment, constants, loops, conditionals, relational expressions, blocks etc.
* correctProg2.cl - checks for correctness of  the base functions.
* incorrectProg1.cl - checks for division by 0.

parser grammar CoolParser;

options {
    tokenVocab = CoolLexer;
}

@header{
    import java.util.List;
}

@members{
    String filename;
    public void setFilename(String f){
        filename = f;
    }

/*
    DO NOT EDIT THE FILE ABOVE THIS LINE
    Add member functions, variables below.
*/
    String boolStr, integerStr;
    int integerVal;
}

/*
    Add appropriate actions to grammar rules for building AST below.
*/

// Start point for AST generation
program returns [AST.program value]:
    c=class_list EOF
    {
        $value = new AST.program($c.value, $c.value.get(0).lineNo);
    };

// Separates a program into multiple classes if given
class_list returns [ArrayList<AST.class_> value]
    @init
    {
        $value = new ArrayList<AST.class_>();
    }
    : (cl = class_ SEMICOLON {$value.add($cl.value);})+;

// Creates node which contains all details of a class
// Its components are created as subnodes by calling feature_list
class_ returns [AST.class_ value]:
    // Class with no inheritance
    cls = CLASS type = TYPEID LBRACE ftr_lst = feature_list RBRACE
    {
        $value = new AST.class_($type.getText(), filename, "Object", $ftr_lst.value, $cls.getLine());
    }
    // Class with inheritance
    | cls = CLASS type = TYPEID INHERITS parent = TYPEID LBRACE ftr_lst = feature_list RBRACE
    {
        $value = new AST.class_($type.getText(), filename, $parent.getText(), $ftr_lst.value, $cls.getLine());
    };

// feature_list identifies features of a class by the SEMICOLON separator
feature_list returns [ArrayList<AST.feature> value]
    @init
    {
        $value = new ArrayList<AST.feature>();
    }
    : (ftr = feature SEMICOLON {$value.add($ftr.value);})*;

// Features are lines of the program which specifies some action
// They can be divided into methods (separate modules like functions) and variables (like assignment statements)
feature returns [AST.feature value]:
    // Method without any parameters
    fn = OBJECTID LPAREN RPAREN COLON type = TYPEID LBRACE expr = expression RBRACE
    {
        $value = new AST.method($fn.getText(), new ArrayList<AST.formal>(), $type.getText(), $expr.value, $fn.getLine());
    }
    // Method with parameters
    | fn = OBJECTID LPAREN formal_parameters = formal_list RPAREN COLON type = TYPEID LBRACE expr = expression RBRACE
    {
        $value = new AST.method($fn.getText(), $formal_parameters.value, $type.getText(), $expr.value, $fn.getLine());
    }
    // Variables which are identified by calling the attr action-rule here
    | variable = attr
    {
        $value    = $variable.value;
    };

// Attribute list to separate multiple attributes in a given feature
// Used in let expressions
attr_list returns [ArrayList<AST.attr> value]
    @init
    {
        $value = new ArrayList<AST.attr>();
    }
    :
        first = attr {$value.add($first.value);}
        (COMMA rest = attr {$value.add($rest.value);})*;

// Attributes are just variable declarations
// They may or may not be initiailized on declarations
attr returns [AST.attr value]:
    // Without initializing variables
    variable = OBJECTID COLON type = TYPEID
    {
        $value = new AST.attr($variable.getText(), $type.getText(), new AST.no_expr(curLineNo), $variable.getLine());
    }
    // Initializing variables using the RHS value calculated by the expression action-rule
    | variable = OBJECTID COLON type = TYPEID ASSIGN expr = expression
    {
        $value = new AST.attr($variable.getText(), $type.getText(), $expr.value, $variable.getLine());
    };

// formal_list is a list of parameters passed to methods
// Should have atleast one parameter, as the no parameter case is handled in feature itself
formal_list    returns [ArrayList<AST.formal> value]
    @init
    {
        $value = new ArrayList<AST.formal>();
    }
    :
        first = formal {$value.add($first.value);}
        (COMMA rest    = formal {$value.add($rest.value);})*;
            
// formal defines one parameter consisting of an Object name and a Type id
formal returns [AST.formal value]:
    obj_id = OBJECTID COLON type = TYPEID
    {
        $value = new AST.formal($obj_id.getText(),($type.getText(), $obj_id.getLine());
    };

// branch_list contains a list of branching statements, namely CASE
// Split using conditional expressions
// Should contain at least one branch
branch_list    returns [ArrayList<AST.branch> value]
    @init
    {
        $value = new ArrayList<AST.branch>();
    }
    :
        (b = branch SEMICOLON {$value.add($b.value);})+;

// Defines the action carried out in each branch statement
branch returns [AST.branch value]:
    obj_id = OBJECTID COLON type = TYPEID DARROW expr = expression
    {
        $value = new AST.branch($obj_id.getText(), $type.getText(), $expr.value,$obj_id.getLine());
    };

// Blocked expressions are nested/compound expressions
// Present inside a block, i.e., within { .. }
blocked_expr    returns [ArrayList<AST.expression> value]
    @init
    {
        $value = new ArrayList<AST.expression>();
    }
    :
    (expr = expression SEMICOLON {$value.add($expr.value);})+;

// expression_list is a list of comma separated expressions
// Used to separate multiple expressions
expression_list    returns [ArrayList<AST.expression> value]
    @init
    {
        $value = new ArrayList<AST.expression>();
    }
    :
    (first_expr    = expression {$value.add($first_expr.value);}
    (COMMA more_expr= expression {$value.add($more_expr.value);})*)?;

// Rules for all expressions specified in COOL language
// Going from bottom-up in the grammar given in book
expression    returns [AST.expression value]:

    // The rules are given in order of precedence. Rule specified earlier has higher precedence
    // The order can be given as (from high to low): '.', '@', `~`, 'ISVOID', 'MDAS (Arithmetic)',
    // 'Comparators(LE, LT, EQ)', 'NOT', 'ASSIGN'
    
    // First is DISPATCH (.)
    // Dispatchs include function call expressions from an object. eg: IO.out_string
    expr = expression DOT obj_id = OBJECTID LPAREN expr_list = expression_list RPAREN
    {
        $value = new AST.dispatch($expr.value, $obj_id.getText(), $expr_list.value, $expr.value.lineNo);
    }

    // '@'
    // Dynamic dispatch from an object, i.e, explicitly envoke parent funtions, discarding any redefinitions
    | expr = expression ATSYM type = TYPEID DOT obj_id = OBJECTID LPAREN expr_list = expression_list RPAREN
    {
        $value = new AST.static_dispatch($expr.value, $type.getText(), $obj_id.getText(), $expr_list.value,$expr.value.lineNo);
    }

    // Dispatch expressions which consists of the expression list passed to methods
    // Can be empty too
    | obj_id = OBJECTID LPAREN expr_list = expression_list RPAREN
    {
        $value = new AST.dispatch(new AST.object("self", curLineNo), $obj_id.getText(), $expr_list.value, $obj_id.getLine());
    }

    // if expressions of the form IF expression THEN expression ELSE expression FI
    | if_cond = IF expr1 = expression THEN expr2 = expression ELSE expr3 = expression FI
    {
        $value = new AST.cond($expr1.value, $expr2.value, $expr3.value, $if_cond.getLine());
    }
    
    // while expressions of the form WHILE expression LOOP expression POOL
    | whl = WHILE expr1 = expression LOOP expr2 = expression POOL
    {
        $value = new AST.loop($expr1.value, $expr2.value, $whl.getLine());
    }

    // Nested expressions of the form LBRACE blocked_expr RBRACE
    | cmpd = LBRACE nested_exprs = blocked_expr RBRACE
    {
        $value = new AST.block($nested_exprs.value, $cmpd.getLine());
    }

    // Let expressions of the form LET attr_list IN expression
    // attr_list is defined above for this purpose
    // Attributes are removed one by one and puts them in the expression list of let
    | let1 = LET attribute = attr_list IN expr = expression
    {
        $value = $expr.value;
        AST.attr attr1;
        int i = $attribute.value.size() - 1;
        while(i >= 0)
        {
            attr1 = $attribute.value.get(i);
            $value = new AST.let(attr1.name, attr1.typeid, attr1.value, $value, $let1.getLine());
            i = i - 1;
        }
    }            

    // Case expressions of the form CASE expression OF branch_list ESAC
    // branch_list defined above for this purpose
    | case1 = CASE expr = expression OF b = branch_list ESAC
    {
        $value = new AST.typcase($expr.value, $branches.value, $case1.getLine());
    }

    // Creating new object of the form NEW TYPEID
    | nw = NEW type = TYPEID
    {
        $value = new AST.new_($type.getText(), $nw.getLine());
    }

    // '~'
    // Takes complement of expression
    | tl = TILDE expr = expression
    {
        $value = new AST.comp($expr.value, $tl.getLine());
    }

    // 'ISVOID'
    // ISVOID checking with expression
    | vd = ISVOID expr = expression
    {
        $value = new AST.isvoid($expr.value, $vd.getLine());
    }

    // MDAS consists of Multiplication, Division, Addition, Subtraction
    // Arithmetic rules follow this order: * or /, + or -
    | expr1 = expression STAR expr2 = expression
    {
        $value = new AST.mul($expr1.value, $expr2.value, $expr1.value.lineNo);
    }
    | expr1 = expression SLASH expr2 = expression
    {
        $value = new AST.divide($expr1.value, $expr2.value, $expr1.value.lineNo);
    }
    | expr1 = expression PLUS expr2 = expression
    {
        $value = new AST.plus($expr1.value, $expr2.value, $expr1.value.lineNo);
    }
    | expr1 = expression MINUS expr2 = expression
    {
        $value = new AST.sub($expr1.value, $expr2.value, $expr1.value.lineNo);
    }

    // Comparison operators: <=, <, =
    // Compares using 2 expressions present on both sides
    | expr1 = expression LE expr2 = expression
    {
        $value = new AST.leq($expr1.value, $expr2.value, $expr1.value.lineNo);
    }
    | expr1 = expression LT expr2 = expression
    {
        $value = new AST.lt($expr1.value, $expr2.value, $expr1.value.lineNo);
    }
    | expr1 = expression EQUALS expr2 = expression
    {
        $value = new AST.eq($expr1.value, $expr2.value, $expr1.value.lineNo);
    }

    // NOT expression
    | nt = NOT expr = expression
    {
        $value = new AST.neg($expr.value, $nt.getLine());
    }

    // ASSIGN
    // assignment expression of the form OBJECTID ASSIGN expression
    |<assoc=right> obj_id = OBJECTID ASSIGN expr1 = expression
    {
        $value = new AST.assign($obj_id.getText(), $expr1.value, $expr1.value.lineNo);
    }
    
    // Parentheses
    // Brackets are removed and then normal evaluation takes place
    | LPAREN expr = expression RPAREN
    {
        $value = $expr.value;
    }

    // Identifiers for Objects
    | obj_id = OBJECTID
    {
        $value = new AST.object($obj_id.getText(), $obj_id.getLine());
    }

    // Integer constants
    | integer = INT_CONST
    {
        integerStr = ($integer.getText());
        integerVal = Integer.parseInt(integerStr);
        $value = new AST.int_const(integerVal, $integer.getLine());
    }

    // String literals
    | str = STR_CONST
    {
        $value  = new AST.string_const($str.getText(), $str.getLine());
    }

    // Bool Constants (are case insensitive)
    | bool = BOOL_CONST
    {
        boolStr    = ($bool.getText());
        if(boolStr.charAt(0) == 't')
        {
            $value = new AST.bool_const(true, $bool.getLine());
        }
        else
        {
            $value = new AST.bool_const(false, $bool.getLine());
        }
    };

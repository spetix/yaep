package it.fe.cassano.yaep.parser;

import it.fe.cassano.yaep.ast.Exp;
import it.fe.cassano.yaep.ccparser.ExpressionParser;
import it.fe.cassano.yaep.ccparser.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.junit.jupiter.api.Test;


class ExpressionParserTest {
	   
    
    @Test
    void testParseDoublePlus() throws IOException, ParseException {
    	Reader r = new StringReader("1+1+1;");
    	ExpressionParser t = new ExpressionParser(r);
    	assertEquals("(((1)+(1))+(1))",t.s().toString());
    }
    
    @Test
    void testParseDoubleMinus() throws IOException, ParseException {
    	Reader r = new StringReader("1- 1- 1;");
    	ExpressionParser t = new ExpressionParser(r);
    	assertEquals("(((1)-(1))-(1))",t.s().toString());
    }
    
    @Test
    void testParseDoubleMinus2() throws IOException, ParseException {
    	Reader r = new StringReader("1-1-1;");
    	ExpressionParser t = new ExpressionParser(r);
    	Exp e = t.s();
    	assertEquals("(((1)-(1))-(1))",e.toString());
    }
    
    @Test
    void testParseDoubleMinus3() throws IOException, ParseException {
    	Reader r = new StringReader("1-1--1;");
    	ExpressionParser t = new ExpressionParser(r);
    	Exp e = t.s();
    	assertEquals("(((1)-(1))-(-(1)))",e.toString());
    }
    
    @Test
    void testParseDoubleExpr() throws IOException, ParseException {
    	Reader r = new StringReader("1-1+1;");
    	ExpressionParser t = new ExpressionParser(r);
    	Exp e = t.s();
    	assertEquals("(((1)-(1))+(1))",e.toString());
    }
    
    @Test
    void testParseDoubleExpr2() throws IOException, ParseException {
    	Reader r = new StringReader("1-1+-1;");
    	ExpressionParser t = new ExpressionParser(r);
    	Exp e = t.s();
    	assertEquals("(((1)-(1))+(-(1)))",e.toString());
    }
    
    @Test
    void testExprOnlyNeededBrackets() throws IOException, ParseException
    {
        Reader valThis = new StringReader("12     * ( ( 5 + 1 )  - 1 );");
        ExpressionParser p = new ExpressionParser(valThis);
        Exp expressionEval = p.s();
        assertEquals("((12)*(((5)+(1))-(1)))",expressionEval.toString());
    }
    
    @Test
    void testExprOnlyNeededBracketsBut() throws IOException, ParseException
    {
       Reader valThis = new StringReader("12     * ( 5 + 1  - 1 );");
        ExpressionParser p = new ExpressionParser(valThis);
        Exp expressionEval = p.s();
        assertEquals("((12)*(((5)+(1))-(1)))",expressionEval.toString());
    }
    
    
    @Test
    void testExprRightAssSimplerMinus() throws IOException, ParseException
    {
         Reader valThis = new StringReader("( 12     - ( 5  - 1 ) );");
        ExpressionParser p = new ExpressionParser(valThis);
        Exp expressionEval = p.s();
        assertEquals("((12)-((5)-(1)))",expressionEval.toString());
    }
    
    @Test
    void testExprRightAssSimplerMul() throws IOException, ParseException
    {
        Reader valThis = new StringReader("( 12     * ( 5  * 1 ) );");
        ExpressionParser p = new ExpressionParser(valThis);
        Exp expressionEval = p.s();
        assertEquals("((12)*((5)*(1)))",expressionEval.toString());
    }
    
    
    @Test
    void testExprRightAss() throws IOException, ParseException
    {
        Reader valThis = new StringReader("( 12     - ( ( 5 + 1 )  - 1 ) );");
        ExpressionParser p = new ExpressionParser(valThis);
        Exp expressionEval = p.s();
        assertEquals("((12)-(((5)+(1))-(1)))",expressionEval.toString());
    }
    
    @Test
    void testExpr2() throws IOException, ParseException
    {
        Reader r = new StringReader("12 *  ( 5 + 1 )  - 1 ;");
        ExpressionParser p = new ExpressionParser(r);
        Exp expressionEval = p.s();
        assertEquals("(((12)*((5)+(1)))-(1))",expressionEval.toString());
    }
    
    @Test
    void testExpr3() throws IOException, ParseException
    {
       Reader r = new StringReader("( 12 *  ( 5 + 1 ) ) - 1 ;");
       ExpressionParser p = new ExpressionParser(r);
        Exp expressionEval = p.s();
        assertEquals("(((12)*((5)+(1)))-(1))",expressionEval.toString());
    }
    
    @Test
    void testParseTermAsExpr1() throws IOException, ParseException
    {
    	Reader r = new StringReader("3 * ( 2 * ( 1 ) );");
    	ExpressionParser p = new ExpressionParser(r);
    	assertEquals("((3)*((2)*(1)))",p.s().toString());
    	
    }
    
    @Test
    void testParseFactorBrackets() throws IOException, ParseException
    {
    	Reader r = new StringReader("( (  ( 3 ) ) );");
    	ExpressionParser p = new ExpressionParser(r);
    	assertEquals("(3)",p.s().toString());
    	
    }
    
    @Test
    void testParseTermBrackets() throws IOException, ParseException
    {
    	Reader r = new StringReader("( (  ( 3 ) ) );");
    	ExpressionParser p = new ExpressionParser(r);
    	assertEquals("(3)",p.s().toString());
    	
    }
    
    @Test
    void testParseTermBracketsDiv() throws IOException, ParseException
    {
    	Reader r = new StringReader("( (  ( 3 / 2 ) ) );");
    	ExpressionParser p = new ExpressionParser(r);
    	assertEquals("((3)/(2))",p.s().toString());
    	
    }
    
    @Test
     void testParseFactorBracketsDiv() throws IOException, ParseException
    {
    	Reader r = new StringReader("( (  ( 3 / 2 ) ) );");
    	ExpressionParser p = new ExpressionParser(r);
    	assertEquals("((3)/(2))",p.s().toString());   	
    }
}

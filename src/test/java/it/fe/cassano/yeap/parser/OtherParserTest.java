package it.fe.cassano.yeap.parser;

import it.fe.cassano.yeap.ast.Exp;
import it.fe.cassano.yeap.ccparser.ExpressionParser;
import it.fe.cassano.yeap.ccparser.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.junit.jupiter.api.Test;

class OtherParserTest {
	
    
    @Test
    void testExprSimpleRightMinus() throws IOException, ParseException
    {
    	Reader valThis = new StringReader("1 - ( 1 - 1 );");
        ExpressionParser p = new ExpressionParser(valThis);
        //assertTrue(p.isValidExpression());
        assertEquals("((1)-((1)-(1)))",p.s().toString());
    	
    }
    
    @Test
    void testUltraViolence() throws IOException, ParseException
    {
    
        Reader valThis = new StringReader("A=3;1 - ( a(3.4,-1) - 1 );");
        ExpressionParser p = new ExpressionParser(valThis);
        Exp e = p.s();
        assertEquals("((A=(3));((1)-((a((3.4),(-(1))))-(1))))",e.toString());
    	
    }
    
    @Test
    void testLongTerms() throws IOException, ParseException
    {
    
        Reader valThis = new StringReader("1-1-1-1-1-1-1-1-1-1-1-1-1-1-1-1-1-1-1-1-1-1-1-1-1-1-1-1-1-1;");
        ExpressionParser p = new ExpressionParser(valThis);
        Exp e = p.s();
        assertEquals("((((((((((((((((((((((((((((((1)-(1))-(1))-(1))-(1))-(1))-(1))-(1))-(1))-(1))-(1))-(1))-(1))-(1))-(1))-(1))-(1))-(1))-(1))-(1))-(1))-(1))-(1))-(1))-(1))-(1))-(1))-(1))-(1))-(1))",e.toString());
    	
    }
    
    
}

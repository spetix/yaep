package it.fe.cassano.yeap.parser;

import it.fe.cassano.yeap.ccparser.ExpressionParser;
import it.fe.cassano.yeap.ccparser.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.Reader;
import java.io.StringReader;

import org.junit.jupiter.api.Test;


class AssignmentParserTest {
	
    @Test
    void testParseAssigment() throws ParseException 
    {
    	Reader r = new StringReader("A = 3;");
    	ExpressionParser p = new ExpressionParser(r);
    	assertEquals("(A=(3))",p.s().toString());
    }
    
    @Test
    void testParseAssigment2() throws ParseException 
    {
    	Reader r = new StringReader("A = 3.0;");
    	ExpressionParser p = new ExpressionParser(r);
    	assertEquals("(A=(3.0))",p.s().toString());
    }
    
	@Test
    void testParseAssigment3() throws ParseException 
    {
    	Reader r = new StringReader("A = -3.0;");
    	ExpressionParser p = new ExpressionParser(r);
    	assertEquals("(A=(-(3.0)))",p.s().toString());
    }
    
    @Test
	void testParseAssigmentInvalidMultiple()  
    {
    	try
    	{
	    	Reader r = new StringReader("A=B=C=3.0;");
	    	ExpressionParser p = new ExpressionParser(r);
	    	p.s();
	    	assertTrue(false);
    	}
    	catch(ParseException e)
    	{
    		assertTrue(true);
    	}
    } 
    
    @Test
	void testParseAssigmentInvalidWithUOP()  
    {
    	try
    	{
	    	Reader r = new StringReader("A=-BCa= 3.0;");
	    	ExpressionParser p = new ExpressionParser(r);
	    	p.s();
	    	assertTrue(false);
    	}
    	catch(ParseException e)
    	{
    		assertTrue(true);
    	}
    }
    
    @Test
	void testParseAssigmentWithFun() throws ParseException 
    {
    	Reader r = new StringReader("A=-f(3);");
    	ExpressionParser p = new ExpressionParser(r);
    	assertEquals("(A=(-(f((3)))))",p.s().toString());
    }
}

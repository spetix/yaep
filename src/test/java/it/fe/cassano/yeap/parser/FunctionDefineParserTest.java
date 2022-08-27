package it.fe.cassano.yeap.parser;

import it.fe.cassano.yeap.ccparser.ExpressionParser;
import it.fe.cassano.yeap.ccparser.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.Reader;
import java.io.StringReader;

import org.junit.jupiter.api.Test;

class FunctionDefineParserTest  {
	  
  
    
    @Test
    void testParseDefine() throws ParseException 
    {
    	Reader r = new StringReader("%sin = java.Math.sin(:RealExp):RealExp; sin(4.1);");
    	ExpressionParser p = new ExpressionParser(r);
    	assertEquals("((sin(RealExp)=java.Math.sin(RealExp):RealExp);(sin((4.1))))",p.s().toString());
    }
    
    @Test
    void testParseDefine2() throws ParseException 
    {
    	Reader r = new StringReader("%sin = java.Math.sin(:RealExp):RealExp; %sin = java.Math.sin(:RealExp):RealExp; sin(4.1);");
    	ExpressionParser p = new ExpressionParser(r);
    	assertEquals("((sin(RealExp)=java.Math.sin(RealExp):RealExp);((sin(RealExp)=java.Math.sin(RealExp):RealExp);(sin((4.1)))))",p.s().toString());
    }
    
}

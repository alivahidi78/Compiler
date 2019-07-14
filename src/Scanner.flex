import java_cup.runtime.*;

%%

%public
%class Scanner

%unicode

%line
%column

%cup

%{
  StringBuilder string = new StringBuilder();
  
  private Symbol symbol(int type) {
    return new Symbol(type, yyline+1, yycolumn+1);
  }

  private Symbol symbol(int type, Object value) {
    return new Symbol(type, yyline+1, yycolumn+1, value);
  }

  private long parseLong(int start, int end, int radix) {
    long result = 0;
    long digit;

    for (int i = start; i < end; i++) {
      digit  = Character.digit(yycharat(i),radix);
      result*= radix;
      result+= digit;
    }

    return result;
  }

%}



/* main character classes */
LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]

WhiteSpace = {LineTerminator} | [ \t\f]

/* comments */
Comment = {TraditionalComment} | {EndOfLineComment}

TraditionalComment = "/#" [^#] ~"#/" | "/#" "#"+ "/"
EndOfLineComment = "##" {InputCharacter}* {LineTerminator}?

/* identifiers */
Identifier = [:jletter:][:jletterdigit:]*

/* integer literals */
DecIntegerLiteral = 0 | [1-9][0-9]*
DecLongLiteral    = {DecIntegerLiteral} [lL]

HexIntegerLiteral = 0 [xX] 0* {HexDigit} {1,8}
HexLongLiteral    = 0 [xX] 0* {HexDigit} {1,16} [lL]
HexDigit          = [0-9a-fA-F]

OctIntegerLiteral = 0+ [1-3]? {OctDigit} {1,15}
OctLongLiteral    = 0+ 1? {OctDigit} {1,21} [lL]
OctDigit          = [0-7]
    
/* floating point literals */        
FloatLiteral  = ({FLit1}|{FLit2}|{FLit3}) {Exponent}? [fF]
DoubleLiteral = ({FLit1}|{FLit2}|{FLit3}) {Exponent}?

FLit1    = [0-9]+ \. [0-9]* 
FLit2    = \. [0-9]+ 
FLit3    = [0-9]+ 
Exponent = [eE] [+-]? [0-9]+

/* string and character literals */
StringCharacter = [^\r\n\"\\]
SingleCharacter = [^\r\n\'\\]

%state STRING, CHARLITERAL

%%

<YYINITIAL> {

  /* keywords */
  "begin"                        { return symbol(sym.BEGIN); }
  "break"                        { return symbol(sym.BREAK); }
  "case"                         { return symbol(sym.CASE); }
  "char"                         { return symbol(sym.CHAR,"char"); }
  "const"                        { return symbol(sym.CONST); }
  "continue"                     { return symbol(sym.CONTINUE); }
  "default"                      { return symbol(sym.DEFAULT); }
  "double"                       { return symbol(sym.DOUBLE,"double"); }
  "else"                         { return symbol(sym.ELSE); }
  "end"                          { return symbol(sym.END); }
  "extern"                       { return symbol(sym.EXTERN); }
  "function"                     { return symbol(sym.FUNCTION); }
  "float"                        { return symbol(sym.FLOAT,"float"); }
  "for"                          { return symbol(sym.FOR); }
  "if"                           { return symbol(sym.IF); }
  "int"                          { return symbol(sym.INT,"int"); }
  "long"                         { return symbol(sym.LONG,"long"); }
  "bool"                         { return symbol(sym.BOOL,"bool"); }
  "return"                       { return symbol(sym.RETURN); }
  "record"                       { return symbol(sym.RECORD); }
  "sizeof"                       { return symbol(sym.SIZEOF); }
  "string"                       { return symbol(sym.STRING,"string"); }
  "switch"                       { return symbol(sym.SWITCH); }

  /* boolean literals */
  "true"                         { return symbol(sym.BOOLEAN_LITERAL, true); }
  "false"                        { return symbol(sym.BOOLEAN_LITERAL, false); }
  
  /* null literal */
  "null"                         { return symbol(sym.NULL_LITERAL); }
  
  
  /* separators */
  "("                            { return symbol(sym.LPAREN); }
  ")"                            { return symbol(sym.RPAREN); }
  "{"                            { return symbol(sym.LBRACE); }
  "}"                            { return symbol(sym.RBRACE); }
  "["                            { return symbol(sym.LBRACK); }
  "]"                            { return symbol(sym.RBRACK); }
  ";"                            { return symbol(sym.SEMICOLON); }
  ","                            { return symbol(sym.COMMA); }
  "."                            { return symbol(sym.DOT); }
  
  /* operators */
  "="                            { return symbol(sym.EQ); }
  ">"                            { return symbol(sym.GT); }
  "<"                            { return symbol(sym.LT); }
  "~"                            { return symbol(sym.TILDA); }
  ":"                            { return symbol(sym.COLON); }
  "=="                           { return symbol(sym.EQEQ); }
  "<="                           { return symbol(sym.LTEQ); }
  ">="                           { return symbol(sym.GTEQ); }
  "!="                           { return symbol(sym.NOTEQ); }
  "+"                            { return symbol(sym.PLUS); }
  "-"                            { return symbol(sym.MINUS); }
  "*"                            { return symbol(sym.MULT); }
  "/"                            { return symbol(sym.DIV); }
  "&"                            { return symbol(sym.BITWISE_AND); }
  "|"                            { return symbol(sym.BITWISE_OR); }
  "^"                            { return symbol(sym.XOR); }
  "%"                            { return symbol(sym.MOD); }
  "+="                           { return symbol(sym.PLUSEQ); }
  "-="                           { return symbol(sym.MINUSEQ); }
  "*="                           { return symbol(sym.MULTEQ); }
  "/="                           { return symbol(sym.DIVEQ); }
  "and"                          { return symbol(sym.LOGICAL_AND); }
  "not"                          { return symbol(sym.NOT); }
  "++"                           { return symbol(sym.PLUSPLUS); }
  "--"                           { return symbol(sym.MINUSMINUS); }
  "or"                           { return symbol(sym.LOGICAL_OR); }
  "void"                         { return symbol(sym.VOID); }
  "auto"                         { return symbol(sym.AUTO); }
  "of"                           { return symbol(sym.OF); }
  "until"                        { return symbol(sym.UNTIL); }
  "foreach"                      { return symbol(sym.FOREACH); }
  "repeat"                       { return symbol(sym.REPEAT); }
  "in"                           { return symbol(sym.IN); }

   /* string literal */
  \"                             { yybegin(STRING); string.setLength(0); }

  /* character literal */
  \'                             { yybegin(CHARLITERAL); }

  /* numeric literals */

  /* This is matched together with the minus, because the number is too big to 
     be represented by a positive integer. */
  "-2147483648"                  { return symbol(sym.INTEGER_LITERAL, new Integer(Integer.MIN_VALUE)); }
  
  {DecIntegerLiteral}            { return symbol(sym.INTEGER_LITERAL, new Integer(yytext())); }
  {DecLongLiteral}               { return symbol(sym.LONG_LITERAL, new Long(yytext().substring(0,yylength()-1))); }
  
  {HexIntegerLiteral}            { return symbol(sym.INTEGER_LITERAL, new Integer((int) parseLong(2, yylength(), 16))); }
  {HexLongLiteral}               { return symbol(sym.LONG_LITERAL, new Long(parseLong(2, yylength()-1, 16))); }
 
  {OctIntegerLiteral}            { return symbol(sym.INTEGER_LITERAL, new Integer((int) parseLong(0, yylength(), 8))); }
  {OctLongLiteral}               { return symbol(sym.LONG_LITERAL, new Long(parseLong(0, yylength()-1, 8))); }
  
  {FloatLiteral}                 { return symbol(sym.FLOAT_LITERAL, new Float(yytext().substring(0,yylength()-1))); }
  {DoubleLiteral}                { return symbol(sym.DOUBLE_LITERAL, new Double(yytext())); }
  {DoubleLiteral}[dD]            { return symbol(sym.DOUBLE_LITERAL, new Double(yytext().substring(0,yylength()-1))); }
  
  
  /* comments */
  {Comment}                      { /* ignore */ }

  /* whitespace */
  {WhiteSpace}                   { /* ignore */ }

  /* identifiers */ 
  {Identifier}                   { return symbol(sym.IDENTIFIER, yytext()); }
}

<STRING> {
  \"                             { yybegin(YYINITIAL); return symbol(sym.STRING_LITERAL, string.toString()); }
  
  {StringCharacter}+             { string.append( yytext() ); }
  
  /* escape sequences */
  "\\b"                          { string.append( '\b' ); }
  "\\t"                          { string.append( '\t' ); }
  "\\n"                          { string.append( '\n' ); }
  "\\f"                          { string.append( '\f' ); }
  "\\r"                          { string.append( '\r' ); }
  "\\\""                         { string.append( '\"' ); }
  "\\'"                          { string.append( '\'' ); }
  "\\\\"                         { string.append( '\\' ); }
  \\[0-3]?{OctDigit}?{OctDigit}  { char val = (char) Integer.parseInt(yytext().substring(1),8);
                                   string.append( val ); }
  
  /* error cases */
  \\.                            { throw new RuntimeException("Illegal escape sequence \""+yytext()+"\""); }
  {LineTerminator}               { throw new RuntimeException("Unterminated string at end of line"); }
}

<CHARLITERAL> {
  {SingleCharacter}\'            { yybegin(YYINITIAL); return symbol(sym.CHARACTER_LITERAL, yytext().charAt(0)); }
  
  /* escape sequences */
  "\\b"\'                        { yybegin(YYINITIAL); return symbol(sym.CHARACTER_LITERAL, '\b');}
  "\\t"\'                        { yybegin(YYINITIAL); return symbol(sym.CHARACTER_LITERAL, '\t');}
  "\\n"\'                        { yybegin(YYINITIAL); return symbol(sym.CHARACTER_LITERAL, '\n');}
  "\\f"\'                        { yybegin(YYINITIAL); return symbol(sym.CHARACTER_LITERAL, '\f');}
  "\\r"\'                        { yybegin(YYINITIAL); return symbol(sym.CHARACTER_LITERAL, '\r');}
  "\\\""\'                       { yybegin(YYINITIAL); return symbol(sym.CHARACTER_LITERAL, '\"');}
  "\\'"\'                        { yybegin(YYINITIAL); return symbol(sym.CHARACTER_LITERAL, '\'');}
  "\\\\"\'                       { yybegin(YYINITIAL); return symbol(sym.CHARACTER_LITERAL, '\\'); }
  \\[0-3]?{OctDigit}?{OctDigit}\' { yybegin(YYINITIAL); 
                                    int val = Integer.parseInt(yytext().substring(1,yylength()-1),8);
                                  return symbol(sym.CHARACTER_LITERAL, (char)val); }
  
  /* error cases */
  \\.                            { throw new RuntimeException("Illegal escape sequence \""+yytext()+"\""); }
  {LineTerminator}               { throw new RuntimeException("Unterminated character literal at end of line"); }
}

/* error fallback */
[^]                              { throw new RuntimeException("Illegal character \""+yytext()+
                                                              "\" at line "+yyline+", column "+yycolumn); }
<<EOF>>                          { return symbol(sym.EOF); }
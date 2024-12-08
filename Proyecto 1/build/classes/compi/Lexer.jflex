// ------------  Paquete e importaciones ------------
package compi; 

import java_cup.runtime.*;

%%	
//-------> Directivas (No tocar)

%public 
%class Lexer
%cup
%char
%column
%line
%unicode
%ignorecase


%debug
//%full en lugar de unicode


%{ 
%} 

// ------> Expresiones Regulares 

numero = [-+]?[0-9]*\.*[0-9]+
id = [A-Za-z]+[0-9]*


%%
// ------------  Reglas Lexicas -------------------

// Palabras reservedas
"CONJ"          {return new Symbol(sym.CONJUNTO, yycolumn, yyline, yytext());}
"OPERA"         {return new Symbol(sym.OPERA, yycolumn, yyline, yytext());}
"EVALUAR"       {return new Symbol(sym.EVALUAR, yycolumn, yyline, yytext());}

// Simbolos
"{"             {return new Symbol(sym.LLAVEIZQ, yycolumn, yyline, yytext());}
"}"             {return new Symbol(sym.LLAVEDER, yycolumn, yyline, yytext());}
":"             {return new Symbol(sym.DOSPUNTOS, yycolumn, yyline, yytext());}
";"             {return new Symbol(sym.PYC, yycolumn, yyline, yytext());}
"->"            {return new Symbol(sym.IGUAL, yycolumn, yyline, yytext());}
"~"             {return new Symbol(sym.RANGO, yycolumn, yyline, yytext());}
","             {return new Symbol(sym.COMA , yycolumn, yyline, yytext());}
"U"             {return new Symbol(sym.UNION , yycolumn, yyline, yytext());}
"&"             {return new Symbol(sym.INTERSECCION , yycolumn, yyline, yytext());}
"^"             {return new Symbol(sym.COMPLEMENTO , yycolumn, yyline, yytext());}
"-"             {return new Symbol(sym.DIFERENCIA , yycolumn, yyline, yytext());}
"("             {return new Symbol(sym.PARIZQ , yycolumn, yyline, yytext());}
")"             {return new Symbol(sym.PARDER , yycolumn, yyline, yytext());}


// Expresiones
{numero}        {return new Symbol(sym.NUMERO, yycolumn, yyline, yytext());}
{id}            {return new Symbol(sym.ID, yycolumn, yyline, yytext());}




//------> Ingorados 
[ \t\r\n\f]                     {/* Espacios en blanco se ignoran */}
["#"].*                         {/* Comentario de una sola linea */}
"<!"([^!]|("!"[^>]))* "!>"      {/* Comentario de mas de una linea */}

//------> Errores Léxicos 
.           	{ System.out.println("Error Lexico: " + yytext() + " | Fila:" + yyline + " | Columna: " + yycolumn); }



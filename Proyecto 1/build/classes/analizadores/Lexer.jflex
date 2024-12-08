// ------------  Paquete e importaciones ------------
package analizadores; 

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

%{ 
%} 

// ------> Expresiones Regulares 

entero = [0-9]+
decimal = [0-9]*\.*[0-9]+
booleano = ("true"|"false")
cadena = [\"][^\"]*[\"]
caracter = [']([\\]u[0-9A-Fa-f]{4})[']|[']([^\d\s])[']


%%
// ------------  Reglas Lexicas -------------------

//Palabras reservadas
"imprimir"       {return new Symbol(sym.IMPRIMIR, yycolumn, yyline, yytext());}



//Simbolos
";"             {return new Symbol(sym.PYC, yycolumn, yyline, yytext());}
"("             {return new Symbol(sym.PARIZQ, yycolumn, yyline, yytext());}
")"             {return new Symbol(sym.PARDER, yycolumn, yyline, yytext());}
"+"             {return new Symbol(sym.MAS, yycolumn, yyline, yytext());}
"-"             {return new Symbol(sym.MENOS, yycolumn, yyline, yytext());}


//Expresiones regulares
{entero}        {return new Symbol(sym.ENTERO, yycolumn, yyline, yytext());}
{decimal}       {return new Symbol(sym.DECIMAL, yycolumn, yyline, yytext());}
{booleano}      {return new Symbol(sym.BOOLEANO, yycolumn, yyline, yytext());}
{cadena}        {String cadena = yytext(); 
                    cadena = cadena.substring(1, cadena.length() - 1); 
                    return new Symbol(sym.CADENA, yycolumn, yyline, cadena);}
{caracter}      {return new Symbol(sym.CARACTER, yycolumn, yyline, yytext());}

/*{String caracter = yytext(); 
                    caracter = caracter.substring(1, caracter.length() - 1); 
                    return new Symbol(sym.CARACTER, yycolumn, yyline, caracter.charAt(1));}
*/

//------> Ingorados 
[ \t\r\n\f]                     {/* Espacios en blanco */}
["/"].*                         {/* Comentario de una sola linea */}
"/*"([^!]|("!"[^>]))* "*/"      {/* Comentario de mas de una linea */}


//------> Errores Léxicos 
.           	{ System.out.println("Error Lexico: " + yytext() + " | Fila:" + yyline + " | Columna: " + yycolumn); }



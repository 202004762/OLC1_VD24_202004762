package main;

import abstracto.Instruccion;
import analizadores.Parser;
import java.io.StringReader;
import java.util.LinkedList;
import simbolo.*;


public class Main {

    public static void main(String[] args) {
        
        //analizadores("src/analizadores/", "Lexer.jflex", "Parser.cup");
        
        String entrada = """
                         imprimir("Mi cadena");
                         imprimir(2+5+"hola"+5.5);
                         imprimir(3.33+5.8+"hola"+(5+10));
                         imprimir(1+true+"hola"+true);
                         imprimir(9.5+false+true);
                         imprimir(false+1);
                         imprimir(true+4.5);
                         imprimir(true+"hola"+"aaa");
                         imprimir('\u0000');
                       
                                                                                                                                                                                                                                                                                                                                
                    """;
        
        analizar(entrada);
        
    }
    
    public static void analizadores(String ruta, String jflexFile, String cupFile){
        try {
            String opcionesJflex[] =  {ruta+jflexFile,"-d",ruta};
            jflex.Main.generate(opcionesJflex);

            String opcionesCup[] =  {"-destdir", ruta,"-parser","Parser",ruta+cupFile};
            java_cup.Main.main(opcionesCup);
            
        } catch (Exception e) {
            System.out.println("No se ha podido generar los analizadores");
            System.out.println(e);
            
        }
        
    }
    
    // Realizar Analisis
    public static void analizar(String entrada){
        try {
            analizadores.Lexer lexer = new analizadores.Lexer(new StringReader(entrada)); 
            analizadores.Parser parser = new Parser(lexer);
            var resultado = parser.parse();
            
            var ast = new Arbol((LinkedList<Instruccion>)resultado.value);
            var tabla = new tablaSimbolos();
            
            for(var a : ast.getInstrucciones()){
                var res = a.interpretar(ast, tabla);
                
            }
            
            System.out.println(ast.getConsola());

            
        } catch (Exception e) {
            System.out.println("Error fatal en compilación de entrada.");
            System.out.println(e);
            
        } 
        
    } 
    
    
    
    
    
}

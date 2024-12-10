package main;

import abstracto.Instruccion;
import analizadores.Parser;
import excepciones.Errores;
import java.io.StringReader;
import java.util.LinkedList;
import simbolo.*;


public class Main {

    public static void main(String[] args) {
        
        //analizadores("src/analizadores/", "Lexer.jflex", "Parser.cup");
        
        String entrada = """
                         //asdfasfdfdsf
                         imprimir("Mi cadena");
                         imprimir(2+5+"hola"+5.5^2);
                         imprimir(3.33+5+"hola"+(5+10)^2);
                         imprimir(1+true+"hola"+'o');
                         imprimir(true+4.5);
                         imprimir(true+"hola"+"aaa");
                         imprimir("--------");
                         imprimir('\u0048');
                         imprimir(5 + '\u0048');
                         imprimir(5 + 'h');
                         imprimir(5-63.5334);
                         imprimir(5.6-6);
                         imprimir(6.5-5.3);
                         imprimir(6-3);
                         imprimir("--------");
                         imprimir(6*3);
                         imprimir(6*2.2);
                         imprimir(3.3*3);
                         imprimir(2.2*2.3);
                         imprimir("--------");
                         imprimir(1/2);
                         imprimir(1/3.5);
                         imprimir(1.3/5);
                         imprimir(5.3/1.3);
                         imprimir("--------");
                         imprimir(2%2);
                         imprimir(2%1.5);
                         imprimir(1.3%5);
                         imprimir(5.3%1.3);
                         imprimir("--------");
                         imprimir(2^2);
                         imprimir(2^1.5);
                         imprimir(1.3^5);
                         imprimir(5.3^1.3);
                         imprimir("--------");
                         imprimir(-5);
                         imprimir(-5+6);
                         imprimir(-3-3);
                         imprimir(-3/1);
                         imprimir("--------");
                         imprimir(4$2);
                         imprimir(36$2.3);
                         imprimir(63.3$9);
                         imprimir(5.2$10.3);
                       
                                                                                                                                                                                                                                                                                                                                
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
                if (res instanceof Errores){
                    System.out.println(res.toString());
                    
                }
                
            }
            
            System.out.println(ast.getConsola());

            
        } catch (Exception e) {
            System.out.println("Error fatal en compilación de entrada.");
            System.out.println(e);
            
        } 
        
    } 
    
    
    
    
    
}

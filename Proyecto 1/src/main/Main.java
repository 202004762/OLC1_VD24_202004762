package main;

import compi.Parser;
import java.io.StringReader;


public class Main {
    
    public static void main(String[] args) {
        
        // Generar Analizadores
        //analizadores("src/compi/", "Lexer.jflex", "Parser.cup");         //este es el que se ejecuta primero para que actualice
        
        String entrada = """
                         {
                         #Definición de conjuntos
                         CONJ : conjuntoA -> 1,2,3,a,b ;
                         CONJ : conjuntoB -> a~z;
                         CONJ : conjuntoC -> 0~9;
                         
                         #Definición de operaciones
                         OPERA : operacion1 -> & {conjuntoA} {conjuntoB};
                         OPERA : operacion2 -> & U {conjuntoB} {conjuntoC} {conjuntoA};
                         
                         #Evaluamos conjuntos de datos
                         EVALUAR ( {a, b, c} , operacion1 );
                         EVALUAR ( {1, b} , operacion1 );
                         }
                         
                         <!
                         # Salida en consola
                         ===============
                         Evaluar: operacion1
                         ===============
                         a -> exitoso
                         b -> exitoso
                         c -> fallo
                         ===============
                         Evaluar: operacion1
                         ===============
                         1 -> exitoso
                         b -> exitoso
                         !>
                         """;
        
        
        analizar(entrada);    //y este es el segundo que se ejecuta///
        
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
    public static void analizar (String entrada){
        
        try {
            
            compi.Lexer lexer = new compi.Lexer(new StringReader(entrada)); 
            compi.Parser parser = new Parser(lexer);
            parser.parse();
            
        } catch (Exception e) {
            
            System.out.println("Error fatal en compilación de entrada.");
            System.out.println(e);
            
        } 
        
    } 
    
    
}
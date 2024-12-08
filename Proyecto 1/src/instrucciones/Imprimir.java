package instrucciones;

import abstracto.Instruccion;
import excepciones.Errores;
import simbolo.*;


public class Imprimir extends Instruccion {
    private Instruccion expresion;

    public Imprimir(Instruccion expresion, int linea, int col){
        super(new Tipo(tipoDato.VOID), linea, col);
        this.expresion = expresion;
        
    }
    
    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        var valor = this.expresion.interpretar(arbol, tabla);
        if(valor instanceof Errores){
            return valor;
            
        }
        
        arbol.Imprimir(valor.toString());    
        return null;
        
    }
    
    
}

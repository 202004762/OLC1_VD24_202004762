package expresiones;

import abstracto.Instruccion;
import excepciones.Errores;
import simbolo.*;


public class Negacion extends Instruccion {
    private Instruccion expresion;

    public Negacion(Instruccion Expresion, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.expresion = Expresion;
        
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        var resultado = this.expresion.interpretar(arbol, tabla);
        if (resultado instanceof Errores){
            return resultado;
            
        }
        
        switch (this.expresion.tipo.getTipo()){
            case ENTERO -> {
                this.tipo.setTipo(tipoDato.ENTERO);
                return -(int) resultado;
                
            }
            
            case DECIMAL -> {
                this.tipo.setTipo(tipoDato.DECIMAL);
                return -(double) resultado;
                
            }
            
            default -> {
                return new Errores("error semantico", "negacion invalida", this.linea, this.col);
                
            }
            
        }
        
    }
    
    
}

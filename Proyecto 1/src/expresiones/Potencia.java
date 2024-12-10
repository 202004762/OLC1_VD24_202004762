package expresiones;

import abstracto.Instruccion;
import excepciones.Errores;
import simbolo.*;
import static simbolo.tipoDato.DECIMAL;
import static simbolo.tipoDato.ENTERO;


public class Potencia extends Instruccion {
    private Instruccion opIzq;
    private Instruccion opDer;

    public Potencia(Instruccion opIzq, Instruccion opDer, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.opIzq = opIzq;
        this.opDer = opDer;
        
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        var valorIzq = this.opIzq.interpretar(arbol, tabla);
        if(valorIzq instanceof Errores){
            return valorIzq;
            
        }
        
        var valorDer = this.opDer.interpretar(arbol, tabla);
        if(valorDer instanceof Errores){
            return valorDer;
            
        }
        
        var tipoIzq = opIzq.tipo.getTipo();
        var tipoDer = opDer.tipo.getTipo();
        
        switch(tipoIzq){
            case ENTERO -> {
                switch(tipoDer){
                    case ENTERO -> {
                        this.tipo.setTipo(tipoDato.ENTERO);
                        return Math.pow((int) valorIzq, (int) valorDer);
                        
                    }
                    
                    case DECIMAL -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return Math.pow((int) valorIzq, (double) valorDer);
                        
                    }
                    
                    default -> {
                        return new Errores("error semantico", "potencia invalida", this.linea, this.col);
                        
                    }
                        
                }
                
            }
            
            case DECIMAL -> {
                switch(tipoDer){
                    case ENTERO -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return Math.pow((double) valorIzq, (int) valorDer);
                        
                    }
                    
                    case DECIMAL -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return Math.pow((double) valorIzq, (double) valorDer);
                        
                    }
                    
                    default -> {
                        return new Errores("error semantico", "potencia invalida", this.linea, this.col);
                        
                    }
                        
                }
                
            }
            
            default -> {
                return new Errores("error semantico", "potencia invalida", this.linea, this.col);
                
            }
                
        }
                
    }
    
    
}

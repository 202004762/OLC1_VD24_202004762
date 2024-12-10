package expresiones;

import abstracto.Instruccion;
import excepciones.Errores;
import simbolo.*;
import static simbolo.tipoDato.CARACTER;
import static simbolo.tipoDato.DECIMAL;
import static simbolo.tipoDato.ENTERO;


public class Division extends Instruccion {
    private Instruccion opIzq;
    private Instruccion opDer;

    public Division(Instruccion opIzq, Instruccion opDer, int linea, int col) {
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
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (int) valorIzq * 1.0 / (int) valorDer * 1.0;
                        
                    }
                    
                    case DECIMAL -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (int) valorIzq / (double) valorDer;
                        
                    }
                    
                    case CARACTER -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        
                        
                    }
                    
                    default -> {
                        return new Errores("error semantico", "division invalida", this.linea, this.col);
                        
                    }
                        
                }
                
            }
            
            case DECIMAL -> {
                switch(tipoDer){
                    case ENTERO -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (double) valorIzq / (int) valorDer;
                        
                    }
                    
                    case DECIMAL -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (double) valorIzq / (double) valorDer;
                        
                    }
                    
                    case CARACTER -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        
                        
                    }
                    
                    default -> {
                        return new Errores("error semantico", "division invalida", this.linea, this.col);
                        
                    }
                        
                }
                
            }
            
            case CARACTER -> {
                switch(tipoDer){
                    case ENTERO -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        
                    }
                    
                    case DECIMAL -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        
                    }
                    
                    default -> {
                        return new Errores("error semantico", "division invalida", this.linea, this.col);
                        
                    }
                        
                }
                
            }
            
            default -> {
                return new Errores("error semantico", "division invalida", this.linea, this.col);
                
            }
                
        }
        
        return new Errores("error semantico", "division invalida", this.linea, this.col);
        
    }
    
    
}

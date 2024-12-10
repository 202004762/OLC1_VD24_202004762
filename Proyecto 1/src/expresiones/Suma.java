package expresiones;

import abstracto.Instruccion;
import excepciones.Errores;
import simbolo.*;
import static simbolo.tipoDato.BOOLEANO;
import static simbolo.tipoDato.CADENA;
import static simbolo.tipoDato.CARACTER;
import static simbolo.tipoDato.DECIMAL;
import static simbolo.tipoDato.ENTERO;


public class Suma extends Instruccion {
    private Instruccion opIzq;
    private Instruccion opDer;

    public Suma(Instruccion opIzq, Instruccion opDer, int linea, int col) {
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
                        return (int) valorIzq + (int) valorDer;
                        
                    }
                    
                    case DECIMAL -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (int) valorIzq + (double) valorDer;
                        
                    }
                    
                    case BOOLEANO -> {
                        this.tipo.setTipo(tipoDato.ENTERO);
                        int valorBool = (boolean) valorDer ? 1 : 0;
                        return (int) valorIzq + valorBool;
                        
                    }
                    
                    case CARACTER -> {
                        this.tipo.setTipo(tipoDato.ENTERO);
                        char caracter = valorDer instanceof Character ? (char) valorDer : valorDer.toString().charAt(0);
                        return (int) valorIzq + (int) caracter;
                        
                    }
                    
                    case CADENA -> {
                        this.tipo.setTipo(tipoDato.CADENA);
                        return String.valueOf(valorIzq) + valorDer.toString();
                        
                    }
                    
                    default -> {
                        return new Errores("error", "suma invalida", this.linea, this.col);
                        
                    }
                        
                }
                
            }
            
            case DECIMAL -> {
                switch(tipoDer){
                    case ENTERO -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (double) valorIzq + (int) valorDer;
                        
                    }
                    
                    case DECIMAL -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (double) valorIzq + (double) valorDer;
                        
                    }
                    
                    case BOOLEANO -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        double valorBool = (boolean) valorDer ? 1 : 0;
                        return (double) valorIzq + valorBool;
                        
                    }
                    
                    case CARACTER -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        char caracter = valorDer instanceof Character ? (char) valorDer : valorDer.toString().charAt(0);
                        return (double) valorIzq + (int) caracter;
                        
                    }
                    
                    case CADENA -> {
                        this.tipo.setTipo(tipoDato.CADENA);
                        return String.valueOf(valorIzq) + valorDer.toString();
                        
                    }
                    
                    default -> {
                        return new Errores("error", "suma invalida", this.linea, this.col);
                        
                    }
                        
                }
                
            }
            
            case BOOLEANO -> {
                int valorBool = (boolean) valorIzq ? 1 : 0;
                switch(tipoDer){
                    case ENTERO -> {
                        this.tipo.setTipo(tipoDato.ENTERO);
                        return (int) valorBool + (int) valorDer;
                        
                    }
                    
                    case DECIMAL -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (double) valorBool + (double) valorDer;
                        
                    }
                    
                    case CADENA -> {
                        this.tipo.setTipo(tipoDato.CADENA);
                        return String.valueOf(valorBool) + valorDer.toString();
                        
                    }
                    
                    default -> {
                        return new Errores("error", "suma invalida", this.linea, this.col);
                        
                    }
                        
                }
                
            }
            
            case CARACTER -> {
                switch(tipoDer){
                    case ENTERO -> {
                        this.tipo.setTipo(tipoDato.ENTERO);
                        
                    }
                    
                    case DECIMAL -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        
                    }
                    
                    case CARACTER -> {
                        this.tipo.setTipo(tipoDato.CADENA);
                        
                    }
                    
                    case CADENA -> {
                        this.tipo.setTipo(tipoDato.CADENA);
                        
                    }
                    
                    default -> {
                        return new Errores("error", "suma invalida", this.linea, this.col);
                        
                    }
                        
                }
                
            }
            
            case CADENA -> {
                switch(tipoDer){
                    case ENTERO -> {
                        this.tipo.setTipo(tipoDato.CADENA);
                        return valorIzq.toString() + String.valueOf(valorDer);
                        
                    }
                    
                    case DECIMAL -> {
                        this.tipo.setTipo(tipoDato.CADENA);
                        return valorIzq.toString() + String.valueOf(valorDer);
                        
                    }
                    
                    case BOOLEANO -> {
                        this.tipo.setTipo(tipoDato.CADENA);
                        int valorBool = (boolean) valorDer ? 1 : 0;
                        return valorIzq.toString() + String.valueOf(valorBool);
                        
                    }
                    
                    case CARACTER -> {
                        this.tipo.setTipo(tipoDato.CADENA);
                        return valorIzq.toString() + valorDer.toString();
                        
                    }
                    
                    case CADENA -> {
                        this.tipo.setTipo(tipoDato.CADENA);
                        return valorIzq.toString() + valorDer.toString();
                        
                    }
                    
                    default -> {
                        return new Errores("error", "suma invalida", this.linea, this.col);
                        
                    }
                        
                }
                
            }
            
            default -> {
                return new Errores("error", "suma invalida", this.linea, this.col);
                
            }
                
        }
        
        return new Errores("error", "suma invalida", this.linea, this.col);
        
    }
    
    
}

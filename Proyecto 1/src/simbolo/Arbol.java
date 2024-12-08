package simbolo;

import abstracto.Instruccion;
import excepciones.Errores;
import java.util.LinkedList;


public class Arbol {
    private LinkedList<Instruccion> instrucciones;
    private String consola;
    private LinkedList<Errores> errores;
    
    public Arbol(LinkedList<Instruccion> instrucciones){
        this.instrucciones = instrucciones;
        consola = "";
        this.errores = new LinkedList<>();
        
    }

    public LinkedList<Instruccion> getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(LinkedList<Instruccion> instrucciones) {
        this.instrucciones = instrucciones;
    }

    public String getConsola() {
        return consola;
    }
    
    
    
    public void Imprimir(String valor){
        this.consola += valor + "\n";
        
    }
    
    
}
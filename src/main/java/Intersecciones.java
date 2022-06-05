import java.util.ArrayList;

/**
 *
 * @author Diego Garcia
 */
public class Intersecciones {
    
    private Superficie superficie;
    private ArrayList<Desplazador> movimientos;
    private ArrayList<Interseccion> intersecciones;
    private int k;
    private boolean actuar;
    
    public Intersecciones(Superficie superficie, ArrayList<Desplazador> movimientos){
        
        this.superficie = superficie;
        this.movimientos = movimientos;
        intersecciones = new ArrayList<>();
        k = 0;
        actuar = false;
    }
    
    public boolean estaActuando(){
        
        return actuar;
    }
    
    public void setActuar(boolean actuar){
        
        this.actuar = actuar;
    }
    
    public void actualizar(){
        
        if(actuar && movimientos.isEmpty()){
            if(k < intersecciones.size()){
                Interseccion interseccion = intersecciones.get(k);
                if(interseccion.verificarInterseccion()){
                    Desplazador desplazador = interseccion.computarMejorSolucion();
                    if(desplazador.iniciar()){
                        desplazador.darPaso();
                        movimientos.add(desplazador);
                    }
                }
                k++;
            }else{
                intersecciones = superficie.computarIntersecciones();
                k = 0;
                if(intersecciones.isEmpty()){
                    actuar = false;
                }
            }
        }
    }
}

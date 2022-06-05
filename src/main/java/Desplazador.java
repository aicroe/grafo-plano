import java.awt.Point;

/**
 * 
 * @author Diego Garcia
 */
public class Desplazador{

    private VerticeJC vertice;
    private boolean desplazando;
    private double distRecorrida;
    private double distObjetivo;
    private double curso;
    private double velocidad;
    
    public Desplazador(VerticeJC vertice, Point objetivo){
    
        this.vertice = vertice;
        desplazando = false;
        distRecorrida = 0;
        distObjetivo = Geometria.calcularDistancia(vertice.getCentro(), objetivo);
        curso = Geometria.calcularCurso(vertice.getCentro(), objetivo);
        velocidad = distObjetivo / VerticeJC.TIEMPO_MOVIMIENTO;
    }
    public Desplazador(VerticeJC vertice, double distObjetivo, double curso){
    
        this.vertice = vertice;
        desplazando = false;
        distRecorrida = 0;
        this.distObjetivo = distObjetivo;
        this.curso = curso;
        velocidad = distObjetivo / VerticeJC.TIEMPO_MOVIMIENTO;
    }
    
    public boolean estaDesplazando(){
    
        return desplazando;
    }
    
    public boolean iniciar(){
    
        if(!desplazando && distObjetivo > 0 && distRecorrida == 0){
            return desplazando = true;
        }
        return false;
    }
    
    public void darPaso(){
    
        if(desplazando){
            double compHoriz = velocidad * Math.cos(curso);
            double compVerti = velocidad * Math.sin(curso);
            Point punto = vertice.getCentro();
            punto.translate(
                    Geometria.aproximar(compHoriz), 
                    Geometria.aproximar(compVerti));
            distRecorrida += velocidad;
            desplazando = distRecorrida < distObjetivo;
        }
    }
}

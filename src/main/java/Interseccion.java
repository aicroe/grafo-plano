import java.awt.Point;

/**
 *
 * @author Diego Garcia
 */
public class Interseccion {
    
    private Point punto;
    private AristaJC aristaA;
    private AristaJC aristaB;
    
    public Interseccion(Point punto, AristaJC aristaA, AristaJC aristaB){
        
        this.punto = punto;
        this.aristaA = aristaA;
        this.aristaB = aristaB;
    }
    
    public boolean verificarInterseccion(){
        
        return aristaA.computarInterseccion(aristaB) != null;
    }
    
    public Desplazador computarMejorSolucion(){
        
        VerticeJC verticeMover = null;
        Point objetivo = null;
        double distAAI = Geometria.calcularDistancia(
                aristaA.getVerticeA().getCentro(), punto);
        double distABI = Geometria.calcularDistancia(
                aristaA.getVerticeB().getCentro(), punto);
        double distBAI = Geometria.calcularDistancia(
                aristaB.getVerticeA().getCentro(), punto);
        double distBBI = Geometria.calcularDistancia(
                aristaB.getVerticeB().getCentro(), punto);
        if(distAAI >= distABI && distAAI >= distBAI && distAAI >= distBBI){
            verticeMover = aristaA.getVerticeA();
            objetivo = Geometria.calcularPuntoIntermedio(
                    punto, aristaA.getVerticeB().getCentro());
        }else if(distABI >= distAAI && distABI >= distBAI && distABI >= distBBI){
            verticeMover = aristaA.getVerticeB();
            objetivo = Geometria.calcularPuntoIntermedio(
                    punto, aristaA.getVerticeA().getCentro());
        }else if(distBAI >= distAAI && distBAI >= distABI && distBAI >= distBBI){
            verticeMover = aristaB.getVerticeA();
            objetivo = Geometria.calcularPuntoIntermedio(
                    punto, aristaB.getVerticeB().getCentro());
        }else if(distBBI >= distAAI && distBBI >= distABI && distBBI >= distBAI){
            verticeMover = aristaB.getVerticeB();
            objetivo = Geometria.calcularPuntoIntermedio(
                    punto, aristaB.getVerticeA().getCentro());
        }
        return new Desplazador(verticeMover, objetivo);
    }
    
    public boolean sonIguales(Interseccion intersc){
        
        Interseccion estaIntersec = aristaA.computarInterseccion(aristaB);
        Interseccion otraIntersec = intersc.aristaA.computarInterseccion(intersc.aristaB);
        if(estaIntersec != null && otraIntersec != null){
            return estaIntersec.punto.equals(otraIntersec.punto);
        }
        return false;
    }
}

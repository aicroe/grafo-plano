import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JComponent;

/**
 *
 * @author Diego Garcia
 */
public class AristaJC extends JComponent
{
    private VerticeJC verticeA;
    private VerticeJC verticeB;
    
    public AristaJC(VerticeJC verticeA, VerticeJC verticeB)
    {
        this.verticeA = verticeA;
        this.verticeB = verticeB;
    }
    
    public VerticeJC getVerticeA()
    {
        return verticeA;
    }
    
    public VerticeJC getVerticeB() 
    {
        return verticeB;
    }
    
    public Interseccion computarInterseccion(AristaJC arista){
        
        if(verticeA.getCentro().equals(verticeB.getCentro()) ||
                arista.verticeA.getCentro().equals(arista.verticeB.getCentro())){
            return null;
        }
        double x1 = verticeA.getCentro().x;
        double y1 = verticeA.getCentro().y;
        double m1 = Geometria.calcularPendiente(
                verticeA.getCentro(), verticeB.getCentro());
        double x2 = arista.verticeA.getCentro().x;
        double y2 = arista.verticeA.getCentro().y;
        double m2 = Geometria.calcularPendiente(
                arista.verticeA.getCentro(), arista.verticeB.getCentro());
        
        double x = (y2 + m1*x1 - y1 - m2*x2) / (m1 - m2);
        if(Double.isFinite(x) && !compartenVertice(arista)){
            double y = y1 + m1 * (x - x1);
            Point puntoIntersec = new Point(
                    Geometria.aproximar(x), 
                    Geometria.aproximar(y));
            if(Geometria.estaPuntoEnRect(puntoIntersec, 
                    verticeA.getCentro(), 
                    verticeB.getCentro()) &&
                    Geometria.estaPuntoEnRect(puntoIntersec, 
                            arista.verticeA.getCentro(), 
                            arista.verticeB.getCentro())){
                
                return new Interseccion(puntoIntersec, this, arista);
            }
        }
        return null;
    }
    
    private boolean compartenVertice(AristaJC arista){
        
        return (arista.verticeA.getCentro().equals(verticeA.getCentro())) ||
               (arista.verticeB.getCentro().equals(verticeB.getCentro())) ||
               (arista.verticeB.getCentro().equals(verticeA.getCentro())) ||
               (arista.verticeA.getCentro().equals(verticeB.getCentro())) ||
               (arista.verticeB.getCentro().equals(verticeB.getCentro())) || 
               (arista.verticeA.getCentro().equals(verticeA.getCentro())) ||
               (arista.verticeA.getCentro().equals(verticeB.getCentro())) ||
               (arista.verticeB.getCentro().equals(verticeA.getCentro()));
    }
    
    @Override
    public void paint(Graphics graficos)
    {
        graficos.setColor(Color.BLACK);
        graficos.drawLine(
                getVerticeA().getCentro().x, getVerticeA().getCentro().y, 
                getVerticeB().getCentro().x, getVerticeB().getCentro().y);
    }
}

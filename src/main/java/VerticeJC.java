import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JComponent;

/**
 *
 * @author Diego Garcia
 */
public class VerticeJC extends JComponent
{
    private int indice;
    private String nombre;
    private Color color;
    private final Point centro;
    private static final int radio = 20;
    public static final double TIEMPO_MOVIMIENTO = 50.0;

    public VerticeJC(int indice, String nombre, Point centro)
    {
        this.indice = indice;
        this.nombre = nombre;
        this.centro = centro;
        color = Color.YELLOW;
    }
    
    public void setColor(Color color)
    {
        this.color = color;
    }

    public int getIndice()
    {
        return indice;
    }
    
    public String getNombre()
    {
        return nombre;
    }

    public Point getCentro()
    {
        return centro;
    }

    @Override
    public boolean contains(Point punto)
    {
        double xi = punto.x;
        double yi = punto.y;
        double xf = centro.x;
        double yf = centro.y;
        double distancia = Math.sqrt(Math.pow(xf - xi, 2) + Math.pow(yf - yi, 2));
        return distancia <= radio;
    }

    @Override
    public void paint(Graphics graficos)
    {
        graficos.setColor(color);
        graficos.fillOval(centro.x - radio / 2, centro.y - radio / 2, radio, radio);
        graficos.setColor(Color.BLACK);
        graficos.drawOval(centro.x - radio / 2, centro.y - radio / 2, radio, radio);
        graficos.drawString(nombre, centro.x - 3, centro.y + 4);
    }
}

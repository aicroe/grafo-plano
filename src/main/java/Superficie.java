import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Diego Garcia
 */
public class Superficie extends Canvas
{
    private ArrayList<AristaJC> aristas;
    private ArrayList<VerticeJC> vertices;

    public Superficie(){
        
        aristas = new ArrayList<>();
        vertices = new ArrayList<>();
    }

    public synchronized void vaciar(){
        
        aristas = new ArrayList<>();
        vertices = new ArrayList<>();
    }

    public synchronized void aniadirVertice(VerticeJC vertice){
        
        vertices.add(vertice);
    }
    
    public synchronized void aniadirArista(AristaJC arista){
        
        aristas.add(arista);
    }

    public synchronized VerticeJC getVerticePresionado(Point punto){
        
        VerticeJC verticePresionado = null;
        int i = 0;
        while (verticePresionado == null && i < vertices.size()) {
            VerticeJC actual = vertices.get(i);
            if (actual.contains(punto)) {
                verticePresionado = actual;
            } else {
                i++;
            }
        }
        return verticePresionado;
    }
    
    public synchronized ArrayList<Interseccion> computarIntersecciones(){
        
        ArrayList<Interseccion> intersecciones = new ArrayList<>();
        for(int i = 0; i< aristas.size(); i++){
            AristaJC arista = aristas.get(i);
            for(int j = i + 1; j< aristas.size(); j++){
                AristaJC posibleIntersc = aristas.get(j);
                Interseccion intersc = arista.computarInterseccion(posibleIntersc);
                if(intersc != null){
                    intersecciones.add(intersc);
                }
            }
        }
        return intersecciones;
    }
    
    public synchronized ArrayList<Desplazador> computarExpansionGrafo(double distancia){
        
        ArrayList<Desplazador> expancion = new ArrayList<>();
        for(AristaJC arista: aristas){
            expancion.add(computarExpansionArista(
                    arista.getVerticeA(), arista.getVerticeB(), distancia));
            expancion.add(computarExpansionArista(
                    arista.getVerticeB(), arista.getVerticeA(), distancia));
        }
        return expancion;
    }
    
    private Desplazador computarExpansionArista(VerticeJC a, VerticeJC b, double distancia){
        
        double direccion = Geometria.calcularCurso(b.getCentro(), a.getCentro());
        return new Desplazador(a, distancia, direccion);
    }

    @Override
    public synchronized void paint(Graphics graficos){
        
        Dimension tamanio = getSize();
        graficos.setColor(new Color(220, 220, 220));
        graficos.clearRect(0, 0, tamanio.width, tamanio.height);
        graficos.fillRect(0, 0, tamanio.width, tamanio.height);
        for(AristaJC artista : aristas){
            artista.paint(graficos);
        }
        for(VerticeJC vertice: vertices){
            vertice.paint(graficos);
        }
    }
    
    @Override
    public synchronized void update(Graphics graficos){
        
        paint(graficos);
    }
}

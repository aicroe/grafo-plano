import java.awt.Point;

/**
 *
 * @author Diego Garcia
 */
public class Geometria {
    
    public static double calcularPendiente(Point a, Point b){
        
        double x1 = a.x;
        double y1 = a.y;
        double x2 = b.x;
        double y2 = b.y;
        return (y2 - y1) / (x2 - x1);
    }
    
    public static double calcularDistancia(Point a, Point b){
        
        double x1 = a.x;
        double y1 = a.y;
        double x2 = b.x;
        double y2 = b.y;
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }
    
    public static boolean estaPuntoEnRect(Point punto, Point lim1, Point lim2){
        
        int x = punto.x;
        int y = punto.y;
        int x1 = lim1.x;
        int y1 = lim1.y;
        int x2 = lim2.x;
        int y2 = lim2.y;
        return (x <= x2 && x >= x1 && y <= y2 && y >= y1) ||
                (x <= x1 && x >= x2 && y <= y2 && y >= y1) ||
                (x <= x2 && x >= x1 && y <= y1 && y >= y2) ||
                (x <= x1 && x >= x2 && y <= y1 && y >= y2);
    }
    
    public static Point calcularPuntoIntermedio(Point a, Point b){
        
        double x1 = a.x;
        double y1 = a.y;
        double x2 = b.x;
        double y2 = b.y;
        return new Point(aproximar(x1 + x2)/2, aproximar(y1 + y2)/2);
    }
    
    public static int aproximar(double numero){
        
        int valorTruncado = (int) numero;
        if(numero - valorTruncado >= 0.5){
            return valorTruncado + 1;
        }
        return valorTruncado;
    }
    
    public static double calcularCurso(Point origen, Point objetivo){
        
        double dx = objetivo.x - origen.x;
        double dy = objetivo.y - origen.y;
        double curso = Math.atan(dy / dx);
        if(dx < 0){
            curso += Math.PI;
        }
        return curso;
    }
    
}

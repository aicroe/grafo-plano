import java.util.LinkedList;

/**
 *
 * @author Diego Garcia
 */
public class Pila<T>
{
    private LinkedList<T> lista;
    
    public Pila()
    {
        lista = new LinkedList<>();
    }
    
    public void push(T elemento)
    {
        lista.push(elemento);
    }
    
    public T pop()
    {
        if(!estaVacia()){
            return lista.pop();
        }else{
            return null;
        }
    }
    
    public boolean estaVacia()
    {
        return lista.isEmpty();
    }
}

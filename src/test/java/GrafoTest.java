import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Diego Garcia
 */
public class GrafoTest
{
    
    @Test
    public void crearGrafo()
    {
        Grafo<Integer> grafo = new Grafo<>();
        
        assertNotNull(grafo);
    }
    
    @Test
    public void crearGrafoVacio()
    {
        Grafo<Object> grafo = new Grafo<>();
        
        assertTrue(grafo.estaVacio());
    }
    
    @Test
    public void crearGrafoDeCadenasVacio()
    {
        Grafo<String> grafo = new Grafo<>();
        
        assertTrue(grafo.estaVacio());
    }
    
    @Test
    public void grafoNoVacio()
    {
        Grafo<String> grafo = new Grafo<>();
        
        grafo.insertar("A");
        
        assertFalse(grafo.estaVacio());
    }
    
    @Test
    public void insertarVertice()
    {
        Grafo<String> grafo = new Grafo<>();
        
        grafo.insertar("A");
        
        assertEquals(0, grafo.indiceDeVertice("A"));
    }
    
    @Test
    public void insertarVerticeNulo()
    {
        Grafo<String> grafo = new Grafo<>();
        
        grafo.insertar(null);
        
        assertFalse(grafo.estaVertice(null));
    }
    
    @Test
    public void grafoVacioCeroVertices()
    {
        Grafo<String> grafo = new Grafo<>();
        
        assertEquals(0, grafo.getNumVertices());
    }
    
    @Test
    public void insertarCincoVertices()
    {
        Grafo<String> grafo = new Grafo<>();
        
        grafo.insertar("A");
        grafo.insertar("B");
        grafo.insertar("C");
        grafo.insertar("D");
        grafo.insertar("E");
        
        assertEquals(5, grafo.getNumVertices());
    }
    
    @Test
    public void insertarVerticeRepetido_NoEntra()
    {
        Grafo<String> grafo = new Grafo<>();
        
        grafo.insertar("A");
        grafo.insertar("B");
        grafo.insertar("A");
        
        assertTrue(grafo.estaVertice("A"));
        assertEquals(2, grafo.getNumVertices());
    }
    
    @Test
    public void crearArista()
    {
        Grafo<String> grafo = new Grafo<>();
        
        grafo.insertar("A");
        grafo.insertar("B");
        grafo.insertar("C");
        
        grafo.crearArista(1, 2);
        
        assertTrue(grafo.sonArista(1, 2));
    }
    
    @Test
    public void crearAristaAlRevez()
    {
        Grafo<String> grafo = new Grafo<>();
        
        grafo.insertar("A");
        grafo.insertar("B");
        grafo.insertar("C");
        
        grafo.crearArista(2, 1);
        
        assertTrue(grafo.sonArista(1, 2));
    }
    
    @Test
    public void crearAristaPorReferencia()
    {
        Grafo<String> grafo = new Grafo<>();
        
        grafo.insertar("A");
        grafo.insertar("B");
        grafo.insertar("C");
        
        grafo.crearAristaPorRef("A", "B");
        
        assertTrue(grafo.sonAristaPorRef("A", "B"));
    }
    
    @Test
    public void crearAristaConVerticeNoValido()
    {
        Grafo<String> grafo = new Grafo<>();
        
        grafo.insertar("A");
        
        grafo.crearAristaPorRef("A", "B");
        
        assertFalse(grafo.sonAristaPorRef("A", "B"));
    }
    
    @Test
    public void gradoDeUnVertice()
    {
        Grafo<String> grafo = new Grafo<>();
        
        grafo.insertar("A");
        
        grafo.crearArista(0, 0);
        
        assertEquals(2, grafo.gradoVertice(0));
    }
    
    @Test
    public void verticeConGrafoCinco()
    {
        Grafo<String> grafo = new Grafo<>();
        
        grafo.insertar("A");
        grafo.insertar("B");
        grafo.insertar("C");
        
        grafo.crearArista(0, 1);
        grafo.crearArista(0, 2);
        grafo.crearArista(0, 1);
        grafo.crearArista(0, 2);
        grafo.crearArista(0, 1);
        
        assertEquals(5, grafo.gradoVertice(0));
    }
    
    @Test
    public void gradoPorReferencia()
    {
        Grafo<String> grafo = new Grafo<>();
        
        grafo.insertar("A");
        grafo.insertar("B");
        grafo.insertar("C");
        
        grafo.crearAristaPorRef("A", "B");
        grafo.crearAristaPorRef("B", "C");
        grafo.crearAristaPorRef("B", "A");
        grafo.crearAristaPorRef("C", "A");
           
        assertEquals(3, grafo.gradoVerticePorRef("A"));
    }
    
    @Test
    public void eliminarConUno()
    {
        Grafo<String> grafo = new Grafo<>();
        
        grafo.insertar("A");
        grafo.eliminarVertice(0);
        
        assertFalse(grafo.estaVertice("A"));
    }
    
    @Test
    public void eliminarAlPrincipio()
    {
        Grafo<String> grafo = new Grafo<>();
        
        grafo.insertar("A");
        grafo.insertar("B");
        grafo.insertar("C");
        
        grafo.eliminarVertice(0);
        
        assertFalse(grafo.estaVertice("A"));
    }
    
    @Test
    public void eliminarAlMedio()
    {
        Grafo<String> grafo = new Grafo<>();
        
        grafo.insertar("A");
        grafo.insertar("B");
        grafo.insertar("C");
        
        grafo.eliminarVertice(1);
        
        assertFalse(grafo.estaVertice("B"));
    }
    
    @Test
    public void eliminarAlFinal()
    {
        Grafo<String> grafo = new Grafo<>();
        
        grafo.insertar("A");
        grafo.insertar("B");
        grafo.insertar("C");
        
        grafo.eliminarVertice(2);
        
        assertFalse(grafo.estaVertice("C"));
    }
    
    @Test
    public void eliminarTodosLosVertices()
    {
        Grafo<String> grafo = new Grafo<>();
        
        grafo.insertar("A");
        grafo.insertar("B");
        grafo.insertar("C");
        
        grafo.eliminarVertice(0);
        grafo.eliminarVertice(0);
        grafo.eliminarVertice(0);
        
        assertFalse(grafo.estaVertice("A"));
        assertFalse(grafo.estaVertice("B"));
        assertFalse(grafo.estaVertice("C"));
        assertTrue(grafo.estaVacio());
    }
    
    @Test
    public void eliminarPorReferencia()
    {
        Grafo<String> grafo = new Grafo<>();
        
        grafo.insertar("A");
        grafo.insertar("B");
        grafo.insertar("C");
        
        grafo.eliminarVerticePorRef("A");
        
        assertFalse(grafo.estaVertice("A"));
    }
    
    @Test
    public void eliminarArista()
    {
        Grafo<String> grafo = new Grafo<>();
        
        grafo.insertar("A");
        grafo.insertar("B");
        
        grafo.crearAristaPorRef("A", "B");
        grafo.eliminarArista(0, 1);
        
        assertFalse(grafo.sonArista(0, 1));
    }
    
    @Test
    public void vaciarGrafoVacio()
    {
        Grafo<String> grafo = new Grafo<>();
        
        grafo.vaciar();
        
        assertTrue(grafo.estaVacio());
    }
    
    @Test
    public void vaciarGrafoConVertices()
    {
        Grafo<String> grafo = new Grafo<>();
        
        grafo.insertar("A");
        grafo.insertar("B");
        grafo.crearAristaPorRef("A", "B");
        grafo.vaciar();
      
        assertTrue(grafo.estaVacio());
    }
    
    @Test
    public void grafoNoEsCompleto()
    {
        Grafo<String> grafo = new Grafo<>();
        
        assertFalse(grafo.esGrafoCompleto());
    }
    
    @Test
    public void grafoEsCompletoK2()
    {
        Grafo<String> grafo = new Grafo<>();
        
        grafo.insertar("A");
        grafo.insertar("B");
        
        grafo.crearArista(0, 1);
        
        assertTrue(grafo.esGrafoCompleto());
    }
    
    @Test
    public void conjuntosBipartidosNulo()
    {
        Grafo<String> grafo = new Grafo<>();
        
        assertNull(grafo.conjuntosBipartidos());
    }
    
    @Test
    public void compararConjuntosBipartidosParaDosVertices()
    {
        Grafo<String> grafo = new Grafo<>();
        
        grafo.insertar("A");
        grafo.insertar("B");
        
        grafo.crearArista(0, 1);

        assertArrayEquals(new int[]{1, 2}, grafo.conjuntosBipartidos());
    }
    
    @Test
    public void compararConjuntosBipartido()
    {
        Grafo<String> grafo = new Grafo<>();
        
        grafo.insertar("A");
        grafo.insertar("B");
        grafo.insertar("C");
        grafo.insertar("D");
        grafo.insertar("E");
        
        
        grafo.crearAristaPorRef("A", "E");
        grafo.crearAristaPorRef("B", "E");
        grafo.crearAristaPorRef("C", "E");
        grafo.crearAristaPorRef("D", "E");

        assertArrayEquals(new int[]{1, 1, 1, 1, 2}, grafo.conjuntosBipartidos());
    }
    
    @Test
    public void grafoVacioNoEsBipartido()
    {
        Grafo<String> grafo = new Grafo<>();
        
        assertFalse(grafo.esGrafoBipartido());
    }
    
    @Test
    public void grafoConDosVerticesNoAdyacentesNoEsBipartido()
    {
        Grafo<String> grafo = new Grafo<>();
        
        grafo.insertar("A");
        grafo.insertar("B");
        
        assertFalse(grafo.esGrafoBipartido());
    }
    
    @Test
    public void grafoNoEsBipartido()
    {
        Grafo<String> grafo = new Grafo<>();
        
        grafo.insertar("A");
        grafo.insertar("B");
        grafo.insertar("C");
        grafo.insertar("D");
        grafo.insertar("E");
        
        grafo.crearAristaPorRef("A", "B");
        grafo.crearAristaPorRef("A", "E");
        grafo.crearAristaPorRef("A", "D");
        grafo.crearAristaPorRef("B", "E");
        grafo.crearAristaPorRef("B", "C");
        grafo.crearAristaPorRef("C", "E");
        grafo.crearAristaPorRef("C", "D");
        grafo.crearAristaPorRef("D", "E");
        
        assertFalse(grafo.esGrafoBipartido());
    }
    
    @Test
    public void grafoConDosVerticesAyacentesSiempreEsBipartido()
    {
        Grafo<String> grafo = new Grafo<>();
        
        grafo.insertar("A");
        grafo.insertar("B");
        
        grafo.crearAristaPorRef("A", "B");
        
        assertTrue(grafo.esGrafoBipartido());
    }
    
    @Test
    public void grafoEsBipartito()
    {
        Grafo<String> grafo = new Grafo<>();
        
        grafo.insertar("A");
        grafo.insertar("B");
        grafo.insertar("C");
        grafo.insertar("D");
        grafo.insertar("E");
        
        
        grafo.crearAristaPorRef("A", "E");
        grafo.crearAristaPorRef("B", "E");
        grafo.crearAristaPorRef("C", "E");
        grafo.crearAristaPorRef("D", "E");
        
        assertTrue(grafo.esGrafoBipartido());
    }
    
    @Test
    public void grafoNoEsBipartidoCompleto()
    {
        Grafo<String> grafo = new Grafo<>();
        
        grafo.insertar("A");
        grafo.insertar("B");
        grafo.insertar("C");
        grafo.insertar("D");
        grafo.insertar("E");
        
        grafo.crearAristaPorRef("A", "D");
        grafo.crearAristaPorRef("A", "E");
        grafo.crearAristaPorRef("B", "C");
        grafo.crearAristaPorRef("B", "D");
        
        assertFalse(grafo.esGrafoBipartidoCompleto());
    }
    
    
    @Test
    public void grafoEsBipartidoCompleto()
    {
        Grafo<String> grafo = new Grafo<>();
        
        grafo.insertar("A");
        grafo.insertar("B");
        grafo.insertar("C");
        grafo.insertar("D");
        grafo.insertar("E");
        
        grafo.crearAristaPorRef("A", "B");
        grafo.crearAristaPorRef("A", "C");
        grafo.crearAristaPorRef("A", "E");
        grafo.crearAristaPorRef("D", "B");
        grafo.crearAristaPorRef("D", "C");
        grafo.crearAristaPorRef("D", "E");
        
        assertTrue(grafo.esGrafoBipartidoCompleto());
    }
    
    @Test
    public void verticesNoSonCiclo()
    {
        Grafo<String> grafo = new Grafo<>();
        
        grafo.insertar("A");
        grafo.insertar("B");
        grafo.insertar("C");
        
        assertFalse(grafo.sonCiclo(0, 1, 2));
    }
    
    @Test
    public void verticesSonCiclo()
    {
        Grafo<String> grafo = new Grafo<>();
        
        grafo.insertar("A");
        grafo.insertar("B");
        grafo.insertar("C");
        
        grafo.crearAristaPorRef("A", "B");
        grafo.crearAristaPorRef("A", "C");
        grafo.crearAristaPorRef("B", "C");
        
        assertTrue(grafo.sonCiclo(0, 1, 2));
    }
    
    @Test
    public void grafoNoContieneCicloTamTres()
    {
        Grafo<String> grafo = new Grafo<>();
        
        grafo.insertar("A");
        grafo.insertar("B");
        grafo.insertar("C");
        
        assertFalse(grafo.hayCicloTamTres());
    }
    
    @Test
    public void grafoContieneCicloTamTres()
    {
        Grafo<String> grafo = new Grafo<>();
        
        grafo.insertar("A");
        grafo.insertar("B");
        grafo.insertar("C");
        
        grafo.crearAristaPorRef("A", "B");
        grafo.crearAristaPorRef("A", "C");
        grafo.crearAristaPorRef("B", "C");
        
        assertTrue(grafo.hayCicloTamTres());
    }
    
    @Test
    public void grafoGrandeTieneCicloTamTres()
    {
        Grafo<String> grafo = new Grafo<>();
        
        grafo.insertar("A");
        grafo.insertar("B");
        grafo.insertar("C");
        grafo.insertar("D");
        grafo.insertar("E");
        grafo.insertar("F");
        grafo.insertar("G");
        grafo.insertar("H");
        grafo.insertar("I");
        grafo.insertar("J");
        grafo.insertar("K");
        grafo.insertar("L");
        grafo.insertar("M");
        
        grafo.crearAristaPorRef("A", "B");
        grafo.crearAristaPorRef("A", "K");
        grafo.crearAristaPorRef("A", "J");
        grafo.crearAristaPorRef("A", "E");
        grafo.crearAristaPorRef("B", "C");
        grafo.crearAristaPorRef("B", "J");
        grafo.crearAristaPorRef("B", "G");
        grafo.crearAristaPorRef("C", "E");
        grafo.crearAristaPorRef("C", "D");
        grafo.crearAristaPorRef("D", "E");
        grafo.crearAristaPorRef("D", "F");
        grafo.crearAristaPorRef("F", "G");
        grafo.crearAristaPorRef("F", "H");
        grafo.crearAristaPorRef("E", "J");
        grafo.crearAristaPorRef("E", "L");
        grafo.crearAristaPorRef("G", "I");
        grafo.crearAristaPorRef("G", "K");
        grafo.crearAristaPorRef("H", "M");
        grafo.crearAristaPorRef("H", "I");
        grafo.crearAristaPorRef("I", "K");
        grafo.crearAristaPorRef("I", "J");
        grafo.crearAristaPorRef("K", "J");
        grafo.crearAristaPorRef("L", "M");
        
        assertTrue(grafo.hayCicloTamTres());
    }
    
    @Test
    public void accederElmtoAlPrincio()
    {
        Grafo<String> grafo = new Grafo<>();
        
        grafo.insertar("A");
        grafo.insertar("B");
        grafo.insertar("C");
        
        assertEquals("A", grafo.accederElmtoPos(0));
    }
    
    @Test
    public void accederElmtoAlMedio()
    {
        Grafo<String> grafo = new Grafo<>();
        
        grafo.insertar("A");
        grafo.insertar("B");
        grafo.insertar("C");
        
        assertEquals("B", grafo.accederElmtoPos(1));
    }
    
    @Test
    public void accederElmtoAlFinal()
    {
        Grafo<String> grafo = new Grafo<>();
        
        grafo.insertar("A");
        grafo.insertar("B");
        grafo.insertar("C");
        
        assertEquals("C", grafo.accederElmtoPos(2));
    }
    
    @Test
    public void accederElmtoConIndiceNoValido()
    {
        Grafo<String> grafo = new Grafo<>();
        
        grafo.insertar("A");
        
        assertNull(grafo.accederElmtoPos(7));
    }
}

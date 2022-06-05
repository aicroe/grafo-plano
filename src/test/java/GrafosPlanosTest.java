import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Diego Garcia
 */
public class GrafosPlanosTest
{
     @Test
     public void eliminarSubdivisiones_NoHay()
     {
         Grafo<String> grafo = new Grafo<>();
         
         grafo.insertar("A");
         grafo.insertar("B");
         
         grafo.crearAristaPorRef("A", "B");
         
         Grafo<String> subdivido = grafo.eliminarSubdivisiones();
         
         assertTrue(subdivido.estaVertice("A"));
         assertTrue(subdivido.estaVertice("B"));
         assertTrue(subdivido.sonAristaPorRef("A", "B"));
     }
     
     @Test
     public void eliminarSubdiviones_ConUnaDivision_UnVerticeEliminado()
     {
         Grafo<String> grafo = new Grafo<>();
         
         grafo.insertar("A");
         grafo.insertar("B");
         grafo.insertar("C");
         
         grafo.crearAristaPorRef("A", "B");
         grafo.crearAristaPorRef("B", "C");
         
         Grafo<String> subdividido = grafo.eliminarSubdivisiones();
         
         assertTrue(subdividido.estaVertice("A"));
         assertTrue(subdividido.estaVertice("C"));
         assertFalse(subdividido.estaVertice("B"));
         assertTrue(subdividido.sonAristaPorRef("A", "C"));
     }
     
     @Test
     public void eliminarSubdiviones_GrafoLineaDe4Vert_SeEliminan2Vert()
     {
         Grafo<String> grafo = new Grafo<>();
         
         grafo.insertar("A");
         grafo.insertar("B");
         grafo.insertar("C");
         grafo.insertar("D");
         
         grafo.crearAristaPorRef("A", "B");
         grafo.crearAristaPorRef("B", "C");
         grafo.crearAristaPorRef("C", "D");
         
         Grafo<String> subdividido = grafo.eliminarSubdivisiones();
         
         assertEquals(2, subdividido.getNumVertices());
         assertEquals(1, subdividido.getNumAristas());
     }
     
     @Test
     public void eliminarSubdiviones_ConK3_NoSeEliminan()
     {
         Grafo<String> grafo = new Grafo<>();
         
         grafo.insertar("A");
         grafo.insertar("B");
         grafo.insertar("C");
         
         grafo.crearAristaPorRef("A", "B");
         grafo.crearAristaPorRef("B", "C");
         grafo.crearAristaPorRef("C", "A");
         
         Grafo<String> subdividido = grafo.eliminarSubdivisiones();
         
         assertTrue(subdividido.estaVertice("A"));
         assertTrue(subdividido.estaVertice("B"));
         assertTrue(subdividido.estaVertice("C"));
         assertTrue(subdividido.sonAristaPorRef("A", "B"));
         assertTrue(subdividido.sonAristaPorRef("A", "C"));
         assertTrue(subdividido.sonAristaPorRef("B", "C"));
     }
     
     @Test
     public void eliminarSubdiviones_ConGrafoCicloDe6Vert_SeEliminan3Vert()
     {
         Grafo<String> grafo = new Grafo<>();
         
         grafo.insertar("A");
         grafo.insertar("B");
         grafo.insertar("C");
         grafo.insertar("D");
         grafo.insertar("E");
         grafo.insertar("F");
         
         grafo.crearAristaPorRef("A", "D");
         grafo.crearAristaPorRef("D", "B");
         grafo.crearAristaPorRef("B", "E");
         grafo.crearAristaPorRef("E", "C");
         grafo.crearAristaPorRef("C", "F");
         grafo.crearAristaPorRef("F", "A");
         
         Grafo<String> subdividido = grafo.eliminarSubdivisiones();
         
         assertEquals(3, subdividido.getNumVertices());
         assertEquals(3, subdividido.getNumAristas());
     }
     
     @Test
     public void eliminarSubdiviones_ConGrafoCicloDe6Vert_GrafoOriginalInmutado()
     {
         Grafo<String> grafo = new Grafo<>();
         
         grafo.insertar("A");
         grafo.insertar("B");
         grafo.insertar("C");
         grafo.insertar("D");
         grafo.insertar("E");
         grafo.insertar("F");
         
         grafo.crearAristaPorRef("A", "D");
         grafo.crearAristaPorRef("D", "B");
         grafo.crearAristaPorRef("B", "E");
         grafo.crearAristaPorRef("E", "C");
         grafo.crearAristaPorRef("C", "F");
         grafo.crearAristaPorRef("F", "A");
         
         grafo.eliminarSubdivisiones();
         
         assertTrue(grafo.estaVertice("A"));
         assertTrue(grafo.estaVertice("B"));
         assertTrue(grafo.estaVertice("C"));
         assertTrue(grafo.estaVertice("D"));
         assertTrue(grafo.estaVertice("E"));
         assertTrue(grafo.estaVertice("F"));
         assertTrue(grafo.sonAristaPorRef("A", "D"));
         assertTrue(grafo.sonAristaPorRef("D", "B"));
         assertTrue(grafo.sonAristaPorRef("B", "E"));
         assertTrue(grafo.sonAristaPorRef("E", "C"));
         assertTrue(grafo.sonAristaPorRef("C", "F"));
         assertTrue(grafo.sonAristaPorRef("F", "A"));
     }
     
     @Test
     public void esGrafoPlano_GrafoConCincoVertDisperso_EsPlano()
     {
         Grafo<String> grafo = new Grafo<>();
         
         grafo.insertar("A");
         grafo.insertar("B");
         grafo.insertar("C");
         grafo.insertar("D");
         grafo.insertar("E");
         
         grafo.crearAristaPorRef("A", "B");
         grafo.crearAristaPorRef("A", "C");
         grafo.crearAristaPorRef("A", "D");
         grafo.crearAristaPorRef("C", "D");
         
         assertTrue(grafo.esGrafoPlano());
     }
     
     @Test
     public void esGrafoPlano_K5_NoEsPlano()
     {
         Grafo<String> grafo = new Grafo<>();
         
         grafo.insertar("A");
         grafo.insertar("B");
         grafo.insertar("C");
         grafo.insertar("D");
         grafo.insertar("E");
         
         grafo.crearAristaPorRef("A", "B");
         grafo.crearAristaPorRef("A", "C");
         grafo.crearAristaPorRef("A", "D");
         grafo.crearAristaPorRef("A", "E");
         grafo.crearAristaPorRef("B", "C");
         grafo.crearAristaPorRef("B", "D");
         grafo.crearAristaPorRef("B", "E");
         grafo.crearAristaPorRef("C", "D");
         grafo.crearAristaPorRef("C", "E");
         grafo.crearAristaPorRef("D", "E");
         
         assertFalse(grafo.esGrafoPlano());
     }
     
     @Test
     public void esGrafoPlano_GrafoFaltaUnaAristaParaK5_EsPlano()
     {
         Grafo<String> grafo = new Grafo<>();
         
         grafo.insertar("A");
         grafo.insertar("B");
         grafo.insertar("C");
         grafo.insertar("D");
         grafo.insertar("E");
         
         grafo.crearAristaPorRef("A", "B");
         grafo.crearAristaPorRef("A", "C");
         grafo.crearAristaPorRef("A", "D");
         grafo.crearAristaPorRef("A", "E");
         grafo.crearAristaPorRef("B", "C");
         grafo.crearAristaPorRef("B", "D");
         grafo.crearAristaPorRef("B", "E");
         grafo.crearAristaPorRef("C", "D");
         grafo.crearAristaPorRef("C", "E");
         //grafo.crearAristaPorRef("D", "E");
         
         assertTrue(grafo.esGrafoPlano());
     }
     
     @Test
     public void esGrafoPlano_GrafoConK5Dentro_NoEsPlano()
     {
         Grafo<String> grafo = new Grafo<>();
         
         grafo.insertar("F");
         grafo.insertar("A");
         grafo.insertar("B");
         grafo.insertar("C");
         grafo.insertar("D");
         grafo.insertar("E");
         grafo.insertar("G");
         
         grafo.crearAristaPorRef("F", "A");
         grafo.crearAristaPorRef("F", "D");
         grafo.crearAristaPorRef("A", "B");
         grafo.crearAristaPorRef("A", "C");
         grafo.crearAristaPorRef("A", "D");
         grafo.crearAristaPorRef("A", "E");
         grafo.crearAristaPorRef("B", "C");
         grafo.crearAristaPorRef("B", "D");
         grafo.crearAristaPorRef("B", "E");
         grafo.crearAristaPorRef("C", "D");
         grafo.crearAristaPorRef("C", "E");
         grafo.crearAristaPorRef("D", "E");
         grafo.crearAristaPorRef("G", "C");
         grafo.crearAristaPorRef("G", "B");
         
         assertFalse(grafo.esGrafoPlano());
     }
     
     @Test
     public void esGrafoPlano_K33_NoEsPlano()
     {
         Grafo<String> grafo = new Grafo<>();
         
         grafo.insertar("A");
         grafo.insertar("B");
         grafo.insertar("C");
         grafo.insertar("D");
         grafo.insertar("E");
         grafo.insertar("F");
         
         grafo.crearAristaPorRef("A", "D");
         grafo.crearAristaPorRef("A", "E");
         grafo.crearAristaPorRef("A", "F");
         grafo.crearAristaPorRef("B", "D");
         grafo.crearAristaPorRef("B", "E");
         grafo.crearAristaPorRef("B", "F");
         grafo.crearAristaPorRef("C", "D");
         grafo.crearAristaPorRef("C", "E");
         grafo.crearAristaPorRef("C", "F");
         
         assertFalse(grafo.esGrafoPlano());
     }
     @Test
     public void esGrafoPlano_GrafoFaltaUnaAristaParaK33_EsPlano()
     {
         Grafo<String> grafo = new Grafo<>();
         
         grafo.insertar("A");
         grafo.insertar("B");
         grafo.insertar("C");
         grafo.insertar("D");
         grafo.insertar("E");
         grafo.insertar("F");
         
         grafo.crearAristaPorRef("A", "D");
         grafo.crearAristaPorRef("A", "E");
         grafo.crearAristaPorRef("A", "F");
         grafo.crearAristaPorRef("B", "D");
         grafo.crearAristaPorRef("B", "E");
         grafo.crearAristaPorRef("B", "F");
         grafo.crearAristaPorRef("C", "D");
         grafo.crearAristaPorRef("C", "E");
         //grafo.crearAristaPorRef("C", "F");
         
         assertFalse(grafo.hayAlgunSubGrafoK33());
         assertTrue(grafo.esGrafoPlano());
     }
     
     @Test
     public void esGrafoPlano_GrafoConK33Dentro_NoEsPlano()
     {
         Grafo<String> grafo = new Grafo<>();
         
         grafo.insertar("H");
         grafo.insertar("A");
         grafo.insertar("B");
         grafo.insertar("C");
         grafo.insertar("D");
         grafo.insertar("E");
         grafo.insertar("F");
         grafo.insertar("G");
         
         grafo.crearAristaPorRef("A", "D");
         grafo.crearAristaPorRef("A", "E");
         grafo.crearAristaPorRef("A", "F");
         grafo.crearAristaPorRef("B", "D");
         grafo.crearAristaPorRef("B", "E");
         grafo.crearAristaPorRef("B", "F");
         grafo.crearAristaPorRef("C", "D");
         grafo.crearAristaPorRef("C", "E");
         grafo.crearAristaPorRef("C", "F");
         grafo.crearAristaPorRef("H", "G");
         grafo.crearAristaPorRef("G", "C");
         
         assertFalse(grafo.esGrafoPlano());
     }
     
     @Test
     public void esGrafoPlano_SubdivisionDeK5_NoEsPlano()
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
         
         grafo.crearAristaPorRef("A", "B");
         grafo.crearAristaPorRef("A", "I");
         grafo.crearAristaPorRef("I", "C");
         grafo.crearAristaPorRef("A", "G");
         grafo.crearAristaPorRef("G", "D");
         grafo.crearAristaPorRef("A", "E");
         grafo.crearAristaPorRef("B", "C");
         grafo.crearAristaPorRef("B", "F");
         grafo.crearAristaPorRef("F", "D");
         grafo.crearAristaPorRef("B", "E");
         grafo.crearAristaPorRef("C", "H");
         grafo.crearAristaPorRef("H", "D");
         grafo.crearAristaPorRef("C", "E");
         grafo.crearAristaPorRef("D", "E");
         
         assertFalse(grafo.esGrafoPlano());
     }
     
     @Test
     public void esGrafoPlano_SubdivisionEspecialesDeK5_NoEsPlano()
     {
         Grafo<String> grafo = new Grafo<>();
         
         grafo.insertar("A");
         grafo.insertar("B");
         grafo.insertar("C");
         grafo.insertar("D");
         grafo.insertar("E");
         grafo.insertar("F");
         grafo.insertar("G");
         
         grafo.crearAristaPorRef("A", "F");
         grafo.crearAristaPorRef("F", "E");
         grafo.crearAristaPorRef("F", "G");
         grafo.crearAristaPorRef("A", "C");
         grafo.crearAristaPorRef("A", "B");
         grafo.crearAristaPorRef("A", "D");
         grafo.crearAristaPorRef("B", "C");
         grafo.crearAristaPorRef("B", "D");
         grafo.crearAristaPorRef("B", "E");
         grafo.crearAristaPorRef("C", "D");
         grafo.crearAristaPorRef("C", "E");
         grafo.crearAristaPorRef("E", "D");
         
         assertTrue(grafo.hayAlgunSubGrafoK5());
         assertFalse(grafo.esGrafoPlano());
     }
     
     @Test
     public void esGrafoPlano_SubdivisionDeK33_NoEsPlano()
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
         
         grafo.crearAristaPorRef("A", "G");
         grafo.crearAristaPorRef("G", "D");
         grafo.crearAristaPorRef("A", "E");
         grafo.crearAristaPorRef("A", "I");
         grafo.crearAristaPorRef("I", "F");
         grafo.crearAristaPorRef("B", "D");
         grafo.crearAristaPorRef("B", "E");
         grafo.crearAristaPorRef("B", "H");
         grafo.crearAristaPorRef("H", "F");
         grafo.crearAristaPorRef("C", "D");
         grafo.crearAristaPorRef("C", "E");
         grafo.crearAristaPorRef("C", "F");
         
         assertFalse(grafo.esGrafoPlano());
     }
     
     @Test
     public void esGrafoPlano_K6_NoEsPlano()
     {
         Grafo<String> grafo = new Grafo<>();
         
         grafo.insertar("A");
         grafo.insertar("B");
         grafo.insertar("C");
         grafo.insertar("D");
         grafo.insertar("E");
         grafo.insertar("F");
         
         grafo.crearAristaPorRef("A", "B");
         grafo.crearAristaPorRef("A", "C");
         grafo.crearAristaPorRef("A", "D");
         grafo.crearAristaPorRef("A", "E");
         grafo.crearAristaPorRef("A", "F");
         grafo.crearAristaPorRef("B", "C");
         grafo.crearAristaPorRef("B", "D");
         grafo.crearAristaPorRef("B", "E");
         grafo.crearAristaPorRef("B", "F");
         grafo.crearAristaPorRef("C", "D");
         grafo.crearAristaPorRef("C", "E");
         grafo.crearAristaPorRef("C", "F");
         grafo.crearAristaPorRef("D", "E");
         grafo.crearAristaPorRef("D", "F");
         grafo.crearAristaPorRef("F", "E");
         
         assertFalse(grafo.esGrafoPlano());
     }
     
     @Test
     public void esGrafoPlano_K5ConSubdivisionesEspeciales_NoEsPlano()
     {
         Grafo<String> grafo = new Grafo<>();
         
         grafo.insertar("A");
         grafo.insertar("B");
         grafo.insertar("C");
         grafo.insertar("D");
         grafo.insertar("E");
         grafo.insertar("F");
         
         grafo.crearAristaPorRef("A", "B");
         grafo.crearAristaPorRef("A", "C");
         grafo.crearAristaPorRef("A", "F");
         grafo.crearAristaPorRef("F", "D");
         grafo.crearAristaPorRef("F", "E");
         grafo.crearAristaPorRef("B", "C");
         grafo.crearAristaPorRef("B", "D");
         grafo.crearAristaPorRef("B", "E");
         grafo.crearAristaPorRef("C", "D");
         grafo.crearAristaPorRef("C", "E");
         grafo.crearAristaPorRef("D", "E");
         
         assertFalse(grafo.esGrafoPlano());
     }
     
     @Test
     public void esGrafoPlano_K33ConSubdivisionesEspeciales_NoEsPlano()
     {
         Grafo<String> grafo = new Grafo<>();
         
         grafo.insertar("A");
         grafo.insertar("B");
         grafo.insertar("C");
         grafo.insertar("D");
         grafo.insertar("E");
         grafo.insertar("F");
         grafo.insertar("G");
         
         grafo.crearAristaPorRef("A", "G");
         grafo.crearAristaPorRef("G", "D");
         grafo.crearAristaPorRef("G", "E");
         grafo.crearAristaPorRef("A", "F");
         grafo.crearAristaPorRef("B", "D");
         grafo.crearAristaPorRef("B", "E");
         grafo.crearAristaPorRef("B", "F");
         grafo.crearAristaPorRef("C", "D");
         grafo.crearAristaPorRef("C", "E");
         grafo.crearAristaPorRef("C", "F");
         
         assertTrue(grafo.hayAlgunSubGrafoK33());
         assertFalse(grafo.esGrafoPlano());
     }
     
     @Test
     public void esGrafoPlano_GrafoConSubdivisionesEspeciales_NoEsPlano()
     {
         Grafo<String> grafo = new Grafo<>();
         
         grafo.insertar("A");
         grafo.insertar("B");
         grafo.insertar("C");
         grafo.insertar("D");
         grafo.insertar("E");
         grafo.insertar("F");
         grafo.insertar("G");
         
         grafo.crearAristaPorRef("A", "B");
         grafo.crearAristaPorRef("A", "D");
         grafo.crearAristaPorRef("A", "E");
         grafo.crearAristaPorRef("A", "F");
         grafo.crearAristaPorRef("A", "G");
         grafo.crearAristaPorRef("B", "C");
         grafo.crearAristaPorRef("B", "F");
         grafo.crearAristaPorRef("B", "G");
         grafo.crearAristaPorRef("C", "E");
         grafo.crearAristaPorRef("C", "G");
         grafo.crearAristaPorRef("D", "F");
         grafo.crearAristaPorRef("D", "G");
         grafo.crearAristaPorRef("E", "F");
         
         assertFalse(grafo.esGrafoPlano());
     }
     
     @Test
     public void esGrafoPlano_GrafoConSubdivisionesEspecialesMenosUnaArista_EsPlano()
     {
         Grafo<String> grafo = new Grafo<>();
         
         grafo.insertar("A");
         grafo.insertar("B");
         grafo.insertar("C");
         grafo.insertar("D");
         grafo.insertar("E");
         grafo.insertar("F");
         grafo.insertar("G");
         
         grafo.crearAristaPorRef("A", "B");
         grafo.crearAristaPorRef("A", "D");
         grafo.crearAristaPorRef("A", "E");
         grafo.crearAristaPorRef("A", "F");
         grafo.crearAristaPorRef("A", "G");
         grafo.crearAristaPorRef("B", "C");
         //grafo.crearAristaPorRef("B", "F");
         grafo.crearAristaPorRef("B", "G");
         grafo.crearAristaPorRef("C", "E");
         grafo.crearAristaPorRef("C", "G");
         grafo.crearAristaPorRef("D", "F");
         grafo.crearAristaPorRef("D", "G");
         grafo.crearAristaPorRef("E", "F");
         
         assertTrue(grafo.esGrafoPlano());
     }
     
     
     @Test
     public void esGrafoPlano_GrafoCon13Vert24Aristas_NoEsPlano()
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
        grafo.crearAristaPorRef("C", "K");
        
        assertFalse(grafo.esGrafoPlano());
     }
     
     @Test
     public void esGrafoPlano_GrafoCon13Vert23Aristas_EsPlano()
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
        //grafo.crearAristaPorRef("B", "J");
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
        grafo.crearAristaPorRef("C", "K");
        
        assertTrue(grafo.esGrafoPlano());
     }
}

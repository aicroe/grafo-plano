import java.util.ArrayList;
import java.util.Arrays;

/**
 * Grafo no dirigido, multigrafo.
 * 
 * @author Diego Garcia
 */
public class Grafo<T>
{
    private ArrayList<T> vertices;
    private int[][] matrizAdy;
    
    public Grafo()
    {
        vertices = new ArrayList<>();
        matrizAdy = new int[0][0];
    }
    
    public boolean estaVacio()
    {
        return vertices.isEmpty();
    }
    
    public int getNumVertices()
    {
        return vertices.size();
    }
    
    public int getNumAristas()
    {
        return getSumGrados() / 2;
    }
    
    public boolean esGrafo()
    {
        return getSumGrados() % 2 == 0;
    }
    
    public int indiceDeVertice(T vertice)
    {
        return vertices.indexOf(vertice);
    }
    
    public boolean estaVertice(T vertice)
    {
        return indiceDeVertice(vertice) >= 0;
    }
    
    public void vaciar()
    {
        vertices = new ArrayList<>();
        matrizAdy = new int[0][0];
    }
    
    public T accederElmtoPos(int pos)
    {
        if(pos >= 0 && pos < getNumVertices()){
            return vertices.get(pos);
        }else{
            return null;
        }
    }
    
    public void insertar(T vertice)
    {
        if(vertice != null && !estaVertice(vertice)){
            vertices.add(vertice);
            if(vertices.size() != matrizAdy.length){
                int[][] matriz = matrizAdy;
                matrizAdy = new int[matriz.length + 1][matriz.length + 1];
        
                for(int i = 0; i< matriz.length; i++){
                    for(int j = 0; j< matriz.length; j++){
                        matrizAdy[i][j] = matriz[i][j];
                    }
                }
            }
        }
    }
    
    public void crearArista(int indiceVertA, int indiceVertB)
    {
        matrizAdy[indiceVertA][indiceVertB]++;
        matrizAdy[indiceVertB][indiceVertA]++;
    }
    
    public void crearAristaPorRef(T verticeA, T verticeB)
    {
        int indiceA = indiceDeVertice(verticeA);
        int indiceB = indiceDeVertice(verticeB);
        if(indiceA >= 0 && indiceB >= 0){
            crearArista(indiceA, indiceB);
        }
    }
    
    public boolean sonArista(int indiceA, int indiceB)
    {
        return matrizAdy[indiceA][indiceB] > 0 && matrizAdy[indiceB][indiceA] > 0;
    }
    
    public boolean sonAristaPorRef(T verticeA, T verticeB)
    {
        int indiceA = indiceDeVertice(verticeA);
        int indiceB = indiceDeVertice(verticeB);
        if(indiceA >= 0 && indiceB >= 0){
            return sonArista(indiceA, indiceB);
        }else{
            return false;
        }
    }
    
    public int gradoVertice(int vertice)
    {
        int grado = 0;
        for(int i = 0; i< getNumVertices(); i++){
            grado += matrizAdy[i][vertice];
        }
        return grado;
    }
    
    public int gradoVerticePorRef(T vertice)
    {
        int indice = indiceDeVertice(vertice);
        if(indice >= 0){
            return gradoVertice(indice);
        }else{
            return -1;
        }
    }
    
    public int getSumGrados()
    {
        int grados = 0;
        for(int i = 0; i< getNumVertices(); i++){
            grados += gradoVertice(i);
        }
        return grados;
    }
    
    public void eliminarVertice(int vertice)
    {
        int numVertices = getNumVertices();
        vertices.remove(vertice);
        recorrerMatrizEnHorizontal(vertice, numVertices);
        recorrerMatrizEnVertical(vertice, numVertices);
    }
    
    private void recorrerMatrizEnHorizontal(int indice, int numVertices)
    {
        for(int i = 0; i< numVertices; i++){
            for(int j = indice; j < numVertices - 1; j++){
                matrizAdy[i][j] = matrizAdy[i][j + 1];
            }
            matrizAdy[i][numVertices - 1] = 0;
        }
    }
    
    private void recorrerMatrizEnVertical(int indice, int numVertices)
    {
        for(int i = indice; i< numVertices - 1; i++){
            matrizAdy[i] = matrizAdy[i + 1];
        }
        matrizAdy[numVertices - 1] = new int[matrizAdy.length];
    }
    
    public void eliminarVerticePorRef(T vertice)
    {
        int indice = indiceDeVertice(vertice);
        if(indice >= 0){
            eliminarVertice(indice);
        }
    }
    
    public void eliminarArista(int indiceA, int indiceB)
    {
        matrizAdy[indiceA][indiceB]--;
        matrizAdy[indiceB][indiceA]--;
    }
    
    public void eliminarAristaPorRef(T verticeA, T verticeB)
    {
        int indiceA = indiceDeVertice(verticeA);
        int indiceB = indiceDeVertice(verticeB);
        if(indiceA >= 0 && indiceB >= 0 && sonArista(indiceA, indiceB)){
            eliminarArista(indiceA, indiceB);
        }
    }
    
    public boolean esGrafoCompleto()
    {
        int i = 0;
        boolean esCompleto =  getNumVertices() >= 2;
        while(esCompleto && i < getNumVertices()){
            esCompleto = gradoVertice(i) == getNumVertices() - 1;
            i++;
        }
        
        return esCompleto;
    }
    
    public int[] conjuntosBipartidos()
    {
        int[] grupos = new int[getNumVertices()];
        int grupoActual = 0;
        
        for(int i = 0; i < getNumVertices(); i++){
            if(grupos[i] == 0){
                grupoActual++;
                grupos[i] = grupoActual;
                for(int j = 0; j < getNumVertices(); j++){
                    if(grupos[j] == 0 && !sonArista(i, j)){
                        grupos[j] = grupoActual;
                    }
                }
            }
        }
        return (grupoActual == 2)? grupos : null;
    }
    
    public boolean esGrafoBipartido()
    {
        return conjuntosBipartidos() != null;
    }
    
    public boolean esGrafoBipartidoCompleto()
    {
        int[] conjuntos = conjuntosBipartidos();
        if(conjuntos != null){
            boolean esCompleto = true;
            int i = 0; 
            while(esCompleto && i< conjuntos.length){
                if(conjuntos[i] == 1){
                    int j = 0;
                    while(esCompleto && j< conjuntos.length){
                        if(conjuntos[j] == 2){
                            esCompleto = sonArista(i, j);
                        }
                        j++;
                    }
                }
                i++;
            }
            return esCompleto;
        }else{
            return false;
        }
    }
    
    public int[] adyacentesVertice(int vertice)
    {
        int[] adyacentes = new int[matrizAdy.length];
        int indice = 0;
        for(int i = 0; i< matrizAdy.length; i++){
            if(matrizAdy[i][vertice] > 0){
                adyacentes[indice] = i;
                indice++;
            }
        }
        return Arrays.copyOfRange(adyacentes, 0, indice);
    }
    
    public boolean hayCicloTamTres()
    {
        if(getNumVertices() >= 3){
            boolean hayCiclo = false;
            int i = 0;
            while(!hayCiclo && i< getNumVertices()){
                int j = i + 1;
                while(!hayCiclo && j< getNumVertices()){
                    int k = j + 1;
                    while(!hayCiclo && k< getNumVertices()){
                        hayCiclo = sonCiclo(i, j, k);
                        k++;
                    }
                    j++;
                }
                i++;
            }
            return hayCiclo;
        }else{
            return false;
        }
    }
    
    public boolean esGrafoPlano()
    {
        if(getNumVertices() >= 5){
            Grafo<T> grafo = eliminarSubdivisiones();
            if(!grafo.hayAlgunSubGrafoK5()){
                if(grafo.getNumVertices() >= 6){
                    return !grafo.hayAlgunSubGrafoK33();
                }
            }else{
                return false;
            }
        }
        return !estaVacio();
    }
    
    public Grafo<T> eliminarSubdivisiones()
    {
        Grafo<T> grafo = this.clone();
        for(int i = 0; i< grafo.getNumVertices(); i++){
            if(grafo.gradoVertice(i) == 2){
                int[] adyacentes = new int[]{-1, -1};
                int j = 0;
                while(adyacentes[1] == -1){
                    if(i != j && grafo.sonArista(i, j)){
                        if(adyacentes[0] == -1){
                            adyacentes[0] = j;
                        }else{
                            adyacentes[1] = j;
                        }
                    }
                    j++;
                }
                if(!grafo.sonCiclo(i, adyacentes[0], adyacentes[1])){
                    grafo.crearArista(adyacentes[0], adyacentes[1]);
                    grafo.eliminarVertice(i);
                    i--;
                }
            }
        }
        return grafo;
    }
    
    public boolean sonCiclo(int i, int j, int k)
    {
        return sonArista(i, j) && sonArista(j, k) && sonArista(k, i);
    }
    
    public boolean hayAlgunSubGrafoK5()
    {
        boolean hay = false;
        for(int a = 0; a < getNumVertices() && !hay; a++){
            for(int b = a + 1; b < getNumVertices() && !hay; b++ ){
                for(int c = b + 1; c < getNumVertices() && !hay; c++){
                    for(int d = c + 1; d < getNumVertices() && !hay; d++){
                        for(int e = d + 1; e < getNumVertices() && !hay; e++){
                            assert getNumVertices() >= 5;
                            int[] subgrafo = new int[5];
                            subgrafo[0] = a;
                            subgrafo[1] = b;
                            subgrafo[2] = c;
                            subgrafo[3] = d;
                            subgrafo[4] = e;
                            
                            hay = esSubGrafoK5(subgrafo);
                        }
                    }
                }
            }
        }
        return hay;
    }
    
    private boolean esSubGrafoK5(int[] subgrafo) 
    {
        final boolean[] marcados = marcarVerticesNoValidos(subgrafo);
        int i = 0;
        boolean hay = true;
        while(hay && i< subgrafo.length){
            int actual = subgrafo[i];
            int j = 0;
            while(hay && j < subgrafo.length){
                int posibleArista = subgrafo[j];
                if(actual != posibleArista){
                    hay = sonArista(actual, posibleArista);
                    if(!hay){
                        boolean[] evitar = Arrays.copyOf(marcados, marcados.length);
                        hay = buscarEnProfundidadUnCamino(actual, posibleArista, subgrafo, evitar);
                    }
                }
                j++;
            }
            i++;
        }
        return hay;
    }
    
    public boolean hayAlgunSubGrafoK33()
    {
        boolean hay = false;
        
        for(int a = 0; a < getNumVertices() && !hay; a++){
            for(int b= 0; b< getNumVertices(); b++){
                if(b != a){
                    for(int c = 0; c< getNumVertices() && !hay; c++){
                        if(c != a && c != b){
                            for(int d = 0; d< getNumVertices() && !hay; d++){
                                if(d != a && d != b && d != c){
                                    for(int e = 0; e< getNumVertices() && !hay; e++){
                                        if(e != a && e != b && e != c && e!= d){
                                            for(int f = 0; f< getNumVertices() && !hay; f++){
                                                if(f != a && f != b && f != c && f!= d && f!= e){
                                                    int[] subgrafo = new int[6];
                                                    subgrafo[0] = a;
                                                    subgrafo[1] = b;
                                                    subgrafo[2] = c;
                                                    subgrafo[3] = d;
                                                    subgrafo[4] = e;
                                                    subgrafo[5] = f;
                                                    
                                                    hay = esSubGrafoK33(subgrafo);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return hay;
    }
    
    private boolean esSubGrafoK33(int[] subgrafo)
    {
        final boolean[] marcados = marcarVerticesNoValidos(subgrafo);
        boolean hay = true;
        int i = 0;
        while(hay && i< 3){
            int actual = subgrafo[i];
            int j = 3;
            while(hay && j< 6){
                int posibleArista = subgrafo[j];
                if(actual != posibleArista){
                    hay = sonArista(actual, posibleArista);
                    if(!hay){
                        boolean[] evitar = Arrays.copyOf(marcados, marcados.length);
                        hay = buscarEnProfundidadUnCamino(actual, posibleArista, subgrafo, evitar);
                    }
                }
                j++;
            }
            i++;
        }
        return hay;
    }
    
    private boolean[] marcarVerticesNoValidos(int[] subgrafo)
    {
        final int maxNumAdyPorVert = 3;
        
        boolean[] marcados = new boolean[getNumVertices()];
        for(int i = 0; i< getNumVertices(); i++){
            int numAdyacentes = 0;
            for(int j = 0; j < subgrafo.length; j++){
                int vertSubgrafo = subgrafo[j];
                if(!estaVertEnSubgrafo(i, subgrafo)&& i !=  vertSubgrafo){
                    if(sonArista(i, vertSubgrafo)){
                        numAdyacentes++;
                    }else if(buscarEnProfundidadUnCamino(i, vertSubgrafo, subgrafo, new boolean[getNumVertices()])){
                        numAdyacentes++;
                    }
                }
            }
            marcados[i] = numAdyacentes > maxNumAdyPorVert;
        }
        return marcados;
    }
    
    private boolean estaVertEnSubgrafo(int vertice, int[] subgrafo)
    {
        int i = 0;
        boolean esta = false;
        while(!esta && i< subgrafo.length){
            esta = subgrafo[i] == vertice;
            i++;
        }
        return esta;
    }
    
    private boolean buscarEnProfundidadUnCamino(int inicio, int destino, int[] subgrafo, boolean[] marcados)
    {
        for(int i = 0; i < subgrafo.length; i++){
            marcados[subgrafo[i]] = true;
        }
        boolean exito = false;
        Pila<Integer> pila = new Pila<>();
        marcados[inicio] = true;
        marcados[destino] = false;
        pila.push(inicio);
        
        while(!exito && !pila.estaVacia()){
            int actual = pila.pop();
            int j = 0;
            while(!exito && j< getNumVertices()){
                if(!marcados[j] && sonArista(actual, j)){
                    if(j == destino){
                        exito = true;
                    }else{
                        marcados[j] = true;
                        pila.push(j);
                    }
                }
                j++;
            }
        }
        
        return exito;
    }
    
    @Override
    public Grafo<T> clone()
    {
        Grafo<T> grafo = new Grafo<T>();
        int[][] matrizAdy = new int[this.matrizAdy.length][0];
        for(int i = 0; i< matrizAdy.length; i++){
            matrizAdy[i] = Arrays.copyOf(this.matrizAdy[i], this.matrizAdy[i].length);
        }
        grafo.matrizAdy = matrizAdy;
        grafo.vertices = new ArrayList<>(this.vertices);
            
        return grafo;
    }
    
    @Override
    public String toString()
    {
        String cadena = "";
        for(int i = 0; i < vertices.size(); i++){
            cadena += Arrays.toString(matrizAdy[i]) + "\n";
        }
        
        return cadena;
    }
}

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

/**
 *
 * @author Diego Garcia
 */
public class GrafoUI extends JFrame{
    
    private Grafo<String> grafo;
    private Superficie superficie;
    private Animador animador;
    private Intersecciones intersecciones;
    private ArrayList<Desplazador> movimientos;
    private VerticeJC verticeAnterior;
    private char nombreVertice;
    
    private JMenuItem acercade;
    private JButton esGrafoPlano;
    private JButton borrarGrafo;
    private JButton expandir;
    private JLabel mensajero;
    
    public GrafoUI(Grafo<String> grafo){
        
        this.grafo = grafo;
        superficie = new Superficie();
        verticeAnterior = null;
        movimientos = new ArrayList<>();
        animador = new Animador();
        intersecciones = new Intersecciones(superficie, movimientos);
        nombreVertice = 'A';
        
        acercade = new JMenuItem("Acerca de ...");
        esGrafoPlano = new JButton("Es Grafo Plano");
        borrarGrafo = new JButton("Borrar Grafo");
        expandir = new JButton("Expandir");
        mensajero = new JLabel(" ", JLabel.CENTER);
    }
    
    public void iniciar(){
        
        construir();
        configurarComponentes();
        animador.start();
    }
    
    private void construir(){
        
        JMenuBar barramenu = new JMenuBar();
        JMenu ayuda = new JMenu("Ayuda");
        ayuda.add(acercade);
        barramenu.add(ayuda);
        setJMenuBar(barramenu);
        
        JPanel contenedor = new JPanel(new BorderLayout());
        JPanel panelCentral = new JPanel(new GridLayout(1, 1));
        panelCentral.add(superficie);
        JPanel panelSur = new JPanel(new BorderLayout());
        panelSur.setBorder(new EtchedBorder());
        JPanel panelBotones = new JPanel();
        panelBotones.add(esGrafoPlano);
        panelBotones.add(borrarGrafo);
        panelBotones.add(expandir);
        panelSur.add(panelBotones, BorderLayout.WEST);
        panelSur.add(mensajero, BorderLayout.CENTER);
        
        contenedor.add(panelCentral, BorderLayout.CENTER);
        contenedor.add(panelSur, BorderLayout.SOUTH);
        setContentPane(contenedor);
        pack();
    }

    private void crearArista(VerticeJC verticeA, VerticeJC verticeB){
        
        grafo.crearArista(verticeA.getIndice(), verticeB.getIndice());
        AristaJC arista = new AristaJC(verticeA, verticeB);
        superficie.aniadirArista(arista);
    }

    private void crearVertice(Point punto){
        
        String nombre = String.valueOf(nombreVertice);
        grafo.insertar(nombre);
        nombreVertice++;
        VerticeJC vertice = 
                new VerticeJC(grafo.indiceDeVertice(nombre), nombre, punto);
        superficie.aniadirVertice(vertice);
    }

    private void configurarComponentes(){
        
        superficie.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (!intersecciones.estaActuando()) {
                    Point punto = e.getPoint();
                    VerticeJC verticePresionado = 
                            superficie.getVerticePresionado(punto);
                    if (verticePresionado != null) {
                        if (verticeAnterior == null) {
                            verticeAnterior = verticePresionado;
                            verticeAnterior.setColor(Color.RED);
                        } else {
                            verticeAnterior.setColor(Color.YELLOW);
                            crearArista(verticeAnterior, verticePresionado);
                            verticeAnterior = null;
                        }
                    } else {
                        if (verticeAnterior == null) {
                            crearVertice(punto);
                        } else {
                            verticeAnterior.setColor(Color.YELLOW);
                            verticeAnterior = null;
                        }
                    }
                }
            }
        });
        esGrafoPlano.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean esGrafoPlano = grafo.esGrafoPlano();
                if (esGrafoPlano) {
                    mensajero.setForeground(Color.GREEN);
                    mensajero.setText("Es Grafo Plano");
                    intersecciones.setActuar(true);
                } else {
                    mensajero.setForeground(Color.RED);
                    mensajero.setText("No es Grafo Plano");
                }
                Thread hilo = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(3000);
                            mensajero.setText(" ");
                        } catch (InterruptedException ex) {
                            System.out.println(ex);
                        }
                    }
                });
                hilo.start();
            }
        });
        borrarGrafo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                grafo.vaciar();
                superficie.vaciar();
                movimientos.clear();
                verticeAnterior = null;
                nombreVertice = 'A';
            }
        });
        acercade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(superficie, 
                        "Desarrollado por: Diego Alexander Garcia Cuchallo", 
                        "Acerca de ...", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        expandir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Desplazador> desplazadores = 
                        superficie.computarExpansionGrafo(150);
                for(Desplazador desplazador: desplazadores){
                    if(desplazador.iniciar()){
                        desplazador.darPaso();
                        movimientos.add(desplazador);
                    }
                }
            }
        });
    }
    
    private void actualizarMovimientos(){
        
        for(int i = 0; i< movimientos.size(); i++){
            Desplazador desplazador = movimientos.get(i);
            if(desplazador.estaDesplazando()){
                desplazador.darPaso();
            }else{
                movimientos.remove(i);
            }
        }
    }
    
    private void actualizarBotones(){
        
        esGrafoPlano.setEnabled(!intersecciones.estaActuando() && !grafo.estaVacio());
        borrarGrafo.setEnabled(!intersecciones.estaActuando() && !grafo.estaVacio());
        expandir.setEnabled(!grafo.estaVacio());
    }
    
    private class Animador extends Thread{
        
        @Override
        public void run(){
            while(true){
                try{
                    actualizarMovimientos();
                    intersecciones.actualizar();
                    actualizarBotones();
                    superficie.repaint();
                    sleep(20);
                }catch(InterruptedException ex){
                    System.out.println(ex);
                }
            }
        }
    }
}

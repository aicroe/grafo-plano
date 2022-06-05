import javax.swing.UIManager;

/**
 *
 * @author Diego Garcia
 */
public class GrafoPlano
{
    
    public static void main(String[] args)
    {
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch(Exception ex){
        }finally{
            GrafoUI ui = new GrafoUI(new Grafo<>());
            ui.setDefaultCloseOperation(GrafoUI.EXIT_ON_CLOSE);
            ui.setTitle("Grafos Planos");
            ui.iniciar();
            ui.setSize(500, 500);
            ui.setVisible(true);
        }
    }
}

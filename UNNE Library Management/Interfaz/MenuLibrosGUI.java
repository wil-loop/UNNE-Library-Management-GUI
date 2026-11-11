 
import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Write a description of class MenuLibrosGUI here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MenuLibrosGUI extends JMenu
{
    public MenuLibrosGUI(){
        // CREA EL MENU
        super("Libros");
        
        // OPCIONES DE MENU DE LIBROS
        JMenuItem registrar = new JMenuItem("Registrar libro");
        JMenuItem listarLibros = new JMenuItem("Listar libros");
        JMenuItem listarTitulos = new JMenuItem("Listar titulos");
        
        // AGREGA LAS OPCIONES DEL MENU AL MENÚ DE LIBROS
        super.add(registrar);
        super.add(listarLibros);
        super.add(listarTitulos);
        
        // DETECCION DE EVENTOS
        
        registrar.addActionListener(e -> registrarLibro());
        listarLibros.addActionListener(e -> listarTitulos());
    }
    
    private void registrarLibro(){
        JFrame ventanaRegistrarLibro = new VentanaRegistrarLibro();
        ventanaRegistrarLibro.setVisible(true);
    }
    
    private void listarTitulos(){
        System.out.println("Hola");
    }
}

import biblioteca.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Write a description of class VentanaRegistrarLibro here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class VentanaRegistrarLibro extends JFrame
{
    public VentanaRegistrarLibro(){
        super("Registrar Libro");
        super.setSize(500,300);
        super.setLocationRelativeTo(null);
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10)); 
        
        JLabel textoTitulo = new JLabel("Titulo del libro");
        JTextField inputTitulo = new JTextField();
        
        JLabel textoEditorial = new JLabel("Editorial del libro");
        JTextField inputEditorial = new JTextField();
        
        JLabel textoEdicion = new JLabel("Edicion del libro");
        JTextField inputEdicion = new JTextField();
        
        JLabel textoAnio= new JLabel("Año de publicación");
        JTextField inputAnio = new JTextField();
        
        JButton guardar = new JButton("Guardar");
        JLabel resultado = new JLabel("");
        
        // DEFINE EL EVENTO QUE DESENCADENA EL BOTÓN GUARDAR
        guardar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
                String titulo = inputTitulo.getText();
                String editorial = inputEditorial.getText();
                int edicionInt = Integer.parseInt(inputEdicion.getText());
                int anioInt = Integer.parseInt(inputAnio.getText());
                
                Libro libro = new Libro(titulo, editorial, edicionInt, anioInt);
                resultado.setText("El libro se guardó correctamente!");
                
            }
        });
        
        panel.add(textoTitulo);
        panel.add(inputTitulo);
        
        panel.add(textoEditorial);
        panel.add(inputEditorial);
        
        panel.add(textoEdicion);
        panel.add(inputEdicion);
        
        panel.add(textoAnio);
        panel.add(inputAnio);
        
        panel.add(guardar);
        panel.add(resultado);
        
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        super.add(panel);
        
    }
}

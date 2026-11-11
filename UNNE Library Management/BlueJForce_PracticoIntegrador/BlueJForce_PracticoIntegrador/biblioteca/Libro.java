package biblioteca;

import java.util.ArrayList;

/**
 * Representa un libro perteneciente a una biblioteca.
 * Almacena sus datos y el historial de préstamos realizados.
 *
 * @author Subgrupo 3
 * @version 1.0
 */
public class Libro
{
    // Atributos
    private String titulo;
    private String editorial;
    private int edicion;
    private int anio;
    private ArrayList<Prestamo> prestamos;

    /**
     * Construye un objeto Libro con los datos proporcionados.
     * Inicializa la lista de préstamos vacía.
     *
     * @param p_titulo El título del libro.
     * @param p_editorial La editorial del libro.
     * @param p_edicion El número de edición del libro.
     * @param p_anio El año de publicación del libro.
     */
    public Libro(String p_titulo, String p_editorial,int p_edicion, int p_anio)
    {
        this.setTitulo(p_titulo);
        this.setEditorial(p_editorial);
        this.setEdicion(p_edicion);
        this.setAnio(p_anio);
        this.setPrestamos(new ArrayList<Prestamo>());
    }

    /**
     * Obtiene el título del libro.
     *
     * @return El título del libro.
     */
    public String getTitulo()
    {
        return this.titulo;
    }

    /**
     * Modifica el título del libro.
     *
     * @param p_titulo El nuevo título del libro.
     */
    public void setTitulo(String p_titulo)
    {
        this.titulo = p_titulo;
    }

    /**
     * Obtiene la editorial del libro.
     *
     * @return La editorial del libro.
     */
    public String getEditorial()
    {
        return this.editorial;
    }

    /**
     * Modifica la editorial del libro.
     *
     * @param p_editorial La nueva editorial del libro.
     */
    public void setEditorial(String p_editorial)
    {
        this.editorial = p_editorial;
    }

    /**
     * Obtiene el número de edición del libro.
     *
     * @return El número de edición del libro.
     */
    public int getEdicion()
    {
        return this.edicion;
    }

    /**
     * Modifica el número de edición del libro.
     *
     * @param p_edicion El nuevo número de edición del libro.
     */
    public void setEdicion(int p_edicion)
    {
        this.edicion = p_edicion;
    }

    /**
     * Obtiene el año de publicación del libro.
     *
     * @return El año de publicación del libro.
     */
    public int getAnio()
    {
        return this.anio;
    }

    /**
     * Modifica el año de publicación del libro.
     *
     * @param p_anio El nuevo año de publicación del libro.
     */
    public void setAnio(int p_anio)
    {
        this.anio = p_anio;
    }

    /**
     * Obtiene la lista de préstamos del libro.
     *
     * @return La lista de préstamos del libro.
     */
    public ArrayList<Prestamo> getPrestamos()
    {
        return this.prestamos;
    }

    /**
     * Modifica la lista de préstamos del libro.
     *
     * @param p_prestamos La nueva lista de préstamos.
     */
    public void setPrestamos(ArrayList<Prestamo> p_prestamos)
    {
        this.prestamos = p_prestamos;
    }
    
    /**
     * Verifica si el libro se encuentra prestado.
     * Un libro está prestado cuando su último préstamono tiene registrada una fecha de 
     * devolución.
     *
     * @return true si el libro está prestado, false en caso contrario.
     */
    public boolean prestado(){
        Prestamo ultimo = this.ultimoPrestamo();

        if (ultimo == null) {
            return false;
        }

        return ultimo.getFechaDevolucion() == null;
    }
    /**
     * Obtiene el último préstamo registrado del libro.
     *
     * @return El último préstamo realizado o null si no existen préstamos registrados.
     */
    public Prestamo ultimoPrestamo(){
        if (this.getPrestamos().isEmpty()) {
            return null;
        }

        int ultimaPosicion = this.getPrestamos().size() - 1;
        return this.getPrestamos().get(ultimaPosicion);
    }
    /**
     * Devuelve una representación del libro indicando su título.
     *
     * @return Una cadena con el título del libro.
     */
    @Override
    public String toString (){
    return "Titulo:" + this.getTitulo();
    }
}
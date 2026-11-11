package biblioteca;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Clase abstracta que define los atributos y el comportamiento general de un socio de la biblioteca.
 * Administra la identificación personal, el plazo reglamentario de préstamo y el historial de préstamos,
 * permitiendo validar la habilitación para solicitar nuevos ejemplares.
 * 
 * @author Fabregat Malena Ayelen
 * @version 1.0
 */
public abstract class Socio {
    // ATRIBUTOS
    private int dniSocio;
    private int diasPrestamo;
    private String nombre;
    private ArrayList<Prestamo> prestamos;

    // CONSTRUCTORES
    /**
     * Constructor para inicializar una instancia de Socio con su DNI, nombre y plazo límite de días,
     * inicializando además la lista de préstamos vacía.
     * 
     * @param p_dniSocio Número de Documento Nacional de Identidad del socio.
     * @param p_nombre Nombre y apellido del socio.
     * @param p_diasPrestamo Cantidad de días reglamentarios permitidos para cada préstamo.
     */
    public Socio(int p_dniSocio, String p_nombre, int p_diasPrestamo) {
        this.setDniSocio(p_dniSocio);
        this.setNombre(p_nombre);
        this.setDiasPrestamo(p_diasPrestamo);
        this.setPrestamos(new ArrayList<Prestamo>());
    }

    // SETTERS
    /**
     * Asigna el número de DNI del socio.
     * 
     * @param p_dniSocio Número de documento.
     */
    private void setDniSocio(int p_dniSocio) {
        this.dniSocio = p_dniSocio;
    }

    /**
     * Asigna la cantidad de días permitidos para el préstamo de libros.
     * 
     * @param p_diasPrestamo Plazo en días.
     */
    public void setDiasPrestamo(int p_diasPrestamo) {
        this.diasPrestamo = p_diasPrestamo;
    }

    /**
     * Asigna el nombre del socio.
     * 
     * @param p_nombre Nombre y apellido.
     */
    private void setNombre(String p_nombre) {
        this.nombre = p_nombre;
    }

    /**
     * Asigna la colección de préstamos asociados al socio.
     * 
     * @param p_prestamos Colección de objetos Prestamo.
     */
    private void setPrestamos(ArrayList<Prestamo> p_prestamos) {
        this.prestamos = p_prestamos;
    }

    // GETTERS
    /**
     * Obtiene el número de DNI del socio.
     * 
     * @return Número de documento del socio.
     */
    public int getDniSocio() {
        return this.dniSocio;
    }

    /**
     * Obtiene la cantidad de días de préstamo fijados para el socio.
     * 
     * @return Días reglamentarios de préstamo.
     */
    public int getDiasPrestamo() {
        return this.diasPrestamo;
    }

    /**
     * Obtiene el nombre del socio.
     * 
     * @return Nombre y apellido del socio.
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Obtiene la lista de todos los préstamos registrados por el socio.
     * 
     * @return Lista de préstamos (ArrayList de Prestamo).
     */
    public ArrayList<Prestamo> getPrestamos() {
        return this.prestamos;
    }

    // DEMÁS MÉTODOS
    /**
     * Agrega un nuevo préstamo a la lista de préstamos del socio.
     * 
     * @param p_prestamo Objeto Prestamo a registrar.
     * @return true si el préstamo se agregó correctamente a la lista; false en caso contrario.
     */
    public boolean addPrestamo(Prestamo p_prestamo) {
        return this.getPrestamos().add(p_prestamo);
    }

    /**
     * Contabiliza cuántos libros tiene actualmente en su poder el socio (aquellos cuya fecha de devolución es nula).
     * 
     * @return Cantidad de libros pendientes de devolución.
     */
    public int cantLibrosPrestados() {
        int cantidad = 0;
        for (Prestamo p : this.getPrestamos()) {
            if (p.getFechaDevolucion() == null) {
                cantidad++;
            }
        }
        return cantidad;
    }

    /**
     * Verifica si el socio se encuentra en condiciones de retirar un nuevo libro.
     * Un socio puede pedir si no posee ningún préstamo pendiente con el plazo reglamentario vencido a la fecha actual.
     * 
     * @return true si está habilitado para solicitar libros; false si tiene préstamos vencidos.
     */
    public boolean puedePedir() {
        Calendar fechaActual = Calendar.getInstance();
        for (Prestamo p : this.getPrestamos()) {
            if (p.getFechaDevolucion() == null && p.vencido(fechaActual)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Retorna una representación textual con los datos principales del socio:
     * DNI, nombre, tipo de socio y cantidad de libros en su poder.
     * 
     * @return Cadena formateada con la información del socio.
     */
    @Override
    public String toString() {
        return "D.N.I.: " + this.getDniSocio() + " || " + this.getNombre() + " (" + this.soyDeLaClase() +
               ") || Libros Prestados: " + this.cantLibrosPrestados();
    }

    // MÉTODOS ABSTRACTOS
    /**
     * Retorna la denominación de la clase concreta o categoría a la que pertenece el socio.
     * Debe ser implementado por las subclases (ej. "Estudiante", "Docente").
     * 
     * @return Cadena con la categoría del socio.
     */
    public abstract String soyDeLaClase();
}

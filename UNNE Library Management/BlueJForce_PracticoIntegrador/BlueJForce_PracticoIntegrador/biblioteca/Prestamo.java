package biblioteca; 
import java.util.Calendar;

/**
 * Clase Prestamo
 * @author Fabregat Malena Ayelen
 */
public class Prestamo {
    private Calendar fechaRetiro;
    private Calendar fechaDevolucion;
    private Libro libro;
    private Socio socio;

    /**
     * Metodo constructor
     * @param p_fechaRetiro, p_socio, p_libro
     */
    public Prestamo(Calendar p_fechaRetiro, Socio p_socio, Libro p_libro) {
        this.setFechaRetiro(p_fechaRetiro);
        this.setSocio(p_socio);
        this.setLibro(p_libro);
        this.setFechaDevolucion(null); 
    }

    /**
     * Setters
     */
    private void setFechaRetiro(Calendar p_fechaRetiro) {
        this.fechaRetiro = p_fechaRetiro;
    }

    private void setFechaDevolucion(Calendar p_fechaDevolucion) {
        this.fechaDevolucion = p_fechaDevolucion;
    }

    private void setSocio(Socio p_socio) {
        this.socio = p_socio;
    }

    private void setLibro(Libro p_libro) {
        this.libro = p_libro;
    }

    /**
     * Getters
     */
    public Calendar getFechaRetiro() {
        return this.fechaRetiro;
    }

    public Calendar getFechaDevolucion() {
        return this.fechaDevolucion;
    }

    public Socio getSocio() {
        return this.socio;
    }

    public Libro getLibro() {
        return this.libro;
    }

    /**
     * Metodo registrarFechaDevolucion
     * @param p_fechaDevolucion
     */
    public void registrarFechaDevolucion(Calendar p_fechaDevolucion) {
        this.setFechaDevolucion(p_fechaDevolucion);
    }

    /**
     * Metodo vencido
     * @param p_fecha
     * @return TRUE si la fecha recibida es posterior a la fecha límite
     */
    public boolean vencido(Calendar p_fecha) {
        Calendar fechaVencimiento = (Calendar) this.getFechaRetiro().clone();
        fechaVencimiento.add(Calendar.DAY_OF_YEAR, this.getSocio().getDiasPrestamo());
        return p_fecha.after(fechaVencimiento);
    }

    /**
     * Metodo toString
     * @return concatenacion de datos
     */
   
    public String toString() {
        String strRetiro = String.format("%04d/%02d/%02d",
            this.getFechaRetiro().get(Calendar.YEAR),
            this.getFechaRetiro().get(Calendar.MONTH) + 1,
            this.getFechaRetiro().get(Calendar.DAY_OF_MONTH));

        String strDevolucion = (this.getFechaDevolucion() != null)
            ? String.format("%04d/%02d/%02d",
                this.getFechaDevolucion().get(Calendar.YEAR),
                this.getFechaDevolucion().get(Calendar.MONTH) + 1,
                this.getFechaDevolucion().get(Calendar.DAY_OF_MONTH))
            : "Pendiente";

        return "Retiro: " + strRetiro + " - Devolución: " + strDevolucion + "\n"
             + "Libro: " + this.getLibro().getTitulo() + "\n"
             + "Socio: " + this.getSocio().getNombre();
    }
}

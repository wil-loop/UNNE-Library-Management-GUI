package biblioteca;
import java.util.Calendar;
import java.util.GregorianCalendar;
/**
 * Representa a un docente que hereda las características y comportamientos de un socio. Posee un área de especializacion
 * y dispone de un plazo de préstamo de libros de cinco días, sujeto a modificaciones con condiciones de responsabilidad
 * 
 * @author Cantero, William Francisco
 * @version 04/11/2026
 */
public class Docente extends Socio
{
    private String area;
    
    /**
     * Construye un docente
     * 
     * @param p_area Área de especialización del docente
     * @param p_dni DNI del docente
     * @param p_nombre Nombre del docente
     */
    public Docente(int p_dni, String p_nombre, String p_area){
        super(p_dni, p_nombre, 5);
        this.setArea(p_area);
    }
    
    /**
     * Asiga el área pasada por parámetro al atributo area del docente
     * 
     * @param p_area Área de especialización del docente
     */
    private void setArea(String p_area){
        this.area  = p_area;
    }
    
    /**
     * Devuelve el área de especialización del docente
     * 
     * @return Valor interno del atributo area
     */
    public String getArea(){
        return this.area;
    }
    
    /**
     * Comprueba si el docente cumple las condiciones de responsabilidad
     * 
     * @return true si no tiene préstamos vencidos ni devoluciones vencidas, false en caso contrario
     */
    public boolean esResponsable() {
        
        return super.puedePedir() && this.puedePedir();
    }
        
    /** Comprueba si el docente cumple las condiciones para realizar préstamos de libros
     * 
     * @return true si no tiene ninguna devolución vencida, false en caso contrario
     */
    @Override
    public boolean puedePedir(){
        if (this.getPrestamos() != null){         
            for (Prestamo p : this.getPrestamos()) {
                if (p.getFechaDevolucion() != null &&
                    p.vencido(p.getFechaDevolucion())) {
                    return false;
                }
            }
        }
        return true;
    }
    
    /** Incrementa la cantidad de días permitidos para los préstamos
     * si el docente cumple con las condiciones de responsabilidad
     * 
     * @param p_dias La cantidad de días a agregar
     */
    public void cambiarDiasDePrestamo(int p_dias){
        if (p_dias > 0 && this.esResponsable()){
            this.setDiasPrestamo(this.getDiasPrestamo() + p_dias);
        }
    }
    
    /** 
     * Identifica la clase a la que pertenece el objeto
     * 
     * @return Cadena de caracteres "Docente"
     */
    @Override
    public String soyDeLaClase(){
        return "Docente";
    }
}
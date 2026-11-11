package biblioteca;
/**
 * Representa un estudiante con características y comportamientos de socio. Posee un atributo carrera
 * y dispone de un plazo de préstamo de libros de 20 días
 * 
 * @author Cantero, William Francisco
 * @version 04/11/2026
 */
public class Estudiante extends Socio
{
    private String carrera;
    
    /**
     * Construye un estudiante 
     * 
     * @param p_carrera Carrera a la que pertenece el estudiante
     * @param p_dni Dni del estudiante
     * @param p_nombre Nombre del estudiante
     */
    public Estudiante(int p_dni, String p_nombre, String p_carrera){
        super(p_dni, p_nombre, 20);
        this.setCarrera(p_carrera);
    }
    
    /**
     * Asigna la carrera pasada por párametro al atributo carrera del estudiante
     * 
     * @param p_carrera La carrera que cursa el estudiante
     */
    private void setCarrera(String p_carrera){
        this.carrera = p_carrera;
    }
    
    /**
     * Devuelve la carrera que cursa el estudiante
     * 
     * @return Valor interno del atributo carrera
     */
    public String getCarrera(){
        return this.carrera;
    }
    
    /**
     * Comprueba si el estudiante cumple las condiciones de realizar préstamos de libros
     * 
     * @return true si no tiene préstamos vencidos ni si posee más de 3 libros prestados, false en caso contrario
     */
    @Override
    public boolean puedePedir(){
        return super.puedePedir() && (this.cantLibrosPrestados () <= 3);
    }
    
    /**
     * Identifica la clase a la que pertenece el objeto
     * 
     * @return Cadena de caracteres "Estudiante"
     */
    public String soyDeLaClase(){
        return "Estudiante";
    }
}
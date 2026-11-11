    package biblioteca;
import java.util.*;

/**
 * Clase Biblioteca
 * @author Ivan Jeremias Caraballo
 * @author Ivo Cespedes Stefani
 * @author Nahuel Federico Soto
 * 
 * @version 1.0
 */
public class Biblioteca {
    private String nombre;
    private ArrayList<Socio> socios;
    private ArrayList<Libro> libros;

    /**
     * Crea una biblioteca con el nombre indicado.
     * Inicializa las colecciones de socios y libros como listas vacías,
     * para evitar un NullPointerException al agregar elementos.
     *
     * @param p_nombre el nombre de la biblioteca
     */
    public Biblioteca(String p_nombre) {
        this.setNombre(p_nombre);
        this.setSocios(new ArrayList<Socio>());
        this.setLibros(new ArrayList<Libro>());
    }

    /**
     * Devuelve el nombre de la biblioteca.
     *
     * @return el nombre de la biblioteca
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Asigna el nombre de la biblioteca.
     *
     * @param p_nombre el nuevo nombre de la biblioteca
     */
    private void setNombre(String p_nombre) {
        this.nombre = p_nombre;
    }

    /**
     * Devuelve la colección de socios (estudiantes y docentes) de la biblioteca.
     *
     * @return la lista de socios
     */
    public ArrayList<Socio> getSocios() {
        return this.socios;
    }

    /**
     * Asigna la colección de socios de la biblioteca.
     *
     * @param p_socios la lista de socios a asignar
     */
    private void setSocios(ArrayList<Socio> p_socios) {
        this.socios = p_socios;
    }

    /**
     * Devuelve la colección de libros (ejemplares) de la biblioteca.
     *
     * @return la lista de libros
     */
    public ArrayList<Libro> getLibros() {
        return this.libros;
    }

    /**
     * Asigna la colección de libros de la biblioteca.
     *
     * @param p_libros la lista de libros a asignar
     */
    private void setLibros(ArrayList<Libro> p_libros) {
        this.libros = p_libros;
    }

    //Agregado y quitado de colecciones
    /**
     * Agrega un libro (ejemplar) a la colección de libros de la biblioteca.
     * Se permite agregar varios ejemplares del mismo título.
     *
     * @param p_libro el libro a agregar
     */
    public void agregarLibro(Libro p_libro) {
        this.getLibros().add(p_libro);
    }

    /**
     * Quita un libro de la colección de libros de la biblioteca.
     * Si el libro no está en la colección, no hace nada.
     *
     * @param p_libro el libro a quitar
     */
    public void quitarLibro(Libro p_libro) {
        this.getLibros().remove(p_libro);
    }
    
    /**
     * Agrega un socio (estudiante o docente) a la colección de socios.
     *
     * @param p_socio el socio a agregar
     */
    public void agregarSocio(Socio p_socio) {
        this.getSocios().add(p_socio);
    }
    
    /**
     * Quita un socio de la colección de socios de la biblioteca.
     * Si el socio no está en la colección, no hace nada.
     *
     * @param p_socio el socio a quitar
     */
    public void quitarSocio(Socio p_socio) {
        this.getSocios().remove(p_socio);
    }
    
    //Demas metodos de la clase Biblioteca
    
    /**
     * Da de alta un nuevo libro (ejemplar) en la biblioteca.
     * Crea el objeto Libro con los datos recibidos y lo agrega a la colección.
     * Se permiten varios ejemplares con el mismo título.
     *
     * @param p_titulo    título del libro
     * @param p_edicion   número de edición
     * @param p_editorial editorial del libro
     * @param p_anio      año de publicación
     */
    public void nuevoLibro(String p_titulo, int p_edicion, String p_editorial, int p_anio) {
        Libro libroAniadir = new Libro(p_titulo, p_editorial, p_edicion, p_anio);
        this.agregarLibro(libroAniadir);
    }
    
    /**
     * Da de alta un nuevo socio de tipo Estudiante.
     * Crea el objeto Estudiante con los datos recibidos y lo agrega
     * a la colección de socios de la biblioteca.
     *
     * @param p_dniSocio el DNI del estudiante
     * @param p_nombre   nombre y apellido del estudiante
     * @param p_carrera  carrera que cursa el estudiante
     */
    public void nuevoSocioEstudiante(int p_dniSocio, String p_nombre, String p_carrera) {
        Socio estudiante = new Estudiante(p_dniSocio, p_nombre, p_carrera);
        this.agregarSocio(estudiante);
    }

    /**
     * Da de alta un nuevo socio de tipo Docente.
     * Crea el objeto Docente con los datos recibidos y lo agrega
     * a la colección de socios de la biblioteca.
     *
     * @param p_dniSocio el DNI del docente
     * @param p_nombre   nombre y apellido del docente
     * @param p_area     área en la que se desempeña el docente
     */
    public void nuevoSocioDocente(int p_dniSocio, String p_nombre, String p_area) {
        Socio docente = new Docente(p_dniSocio, p_nombre, p_area);
        this.agregarSocio(docente);
    }

    /**
     * Presta un libro a un socio.
     * Valida que el socio pueda pedir (puedePedir()) y que el libro no esté
     * prestado. Si se cumple, crea el préstamo con la fecha de retiro y lo
     * agrega a la ficha del libro y a la lista de préstamos del socio.
     *
     * @param p_fechaRetiro fecha del prestamo
     * @param p_socio       socio que solicita el libro
     * @param p_libro       libro solicitado
     * @return true si el préstamo se realizó, false en caso contrario
     */
    public boolean prestarLibro(Calendar p_fechaRetiro, Socio p_socio, Libro p_libro) {
        if (!p_socio.puedePedir() || p_libro.prestado()) {
            return false;
        }
        Prestamo prestamo = new Prestamo(p_fechaRetiro, p_socio, p_libro);
        p_libro.getPrestamos().add(prestamo);
        p_socio.addPrestamo(prestamo);
        return true;
    }
    
    /**
     * Registra la devolución de un libro asignando la fecha actual al préstamo vigente.
     *
     * @param p_libro libro que se devuelve
     * @throws LibroNoPrestadoException si el libro no se encuentra prestado
     */
    public void devolverLibro(Libro p_libro) throws LibroNoPrestadoException {
        if (!p_libro.prestado()) {
            throw new LibroNoPrestadoException("El libro " + p_libro.getTitulo()
                + " no se puede devolver ya que se encuentra en la biblioteca");
        }
        p_libro.ultimoPrestamo().registrarFechaDevolucion(Calendar.getInstance());
    }
    
    /**
     * Cuenta la cantidad de socios de un tipo determinado.
     * El tipo se obtiene de cada socio con soyDeLaClase() (por ejemplo,
     * "Estudiante" o "Docente") y se compara sin distinguir mayúsculas
     * de minúsculas.
     *
     * @param p_objeto el tipo de socio a contar
     * @return la cantidad de socios de ese tipo (0 si no hay ninguno)
     */
    public int cantidadDeSociosPorTipo(String p_objeto) {
        int cantidad = 0;
        for (Socio socio : this.getSocios()) {
            if (socio.soyDeLaClase().equalsIgnoreCase(p_objeto)) {
                cantidad++;
            }
        }
        return cantidad;
    }
    
    /**
     * Devuelve los préstamos vencidos al día de la fecha: libros prestados
     * cuyo plazo ya venció.
     *
     * @return colección con los préstamos vencidos
     */
    public ArrayList<Prestamo> prestamosVencidos() {
        ArrayList<Prestamo> vencidos = new ArrayList<Prestamo>();
        Calendar hoy = Calendar.getInstance();
        for (Libro libro : this.getLibros()) {
            if (libro.prestado() && libro.ultimoPrestamo().vencido(hoy)) {
                vencidos.add(libro.ultimoPrestamo());
            }
        }
        return vencidos;
    }
    
    /**
     * Devuelve los docentes responsables (nunca tuvieron ni tienen un préstamo vencido).
     *
     * @return colección con los docentes responsables
     */
    public ArrayList<Docente> docentesResponsables() {
        ArrayList<Docente> responsables = new ArrayList<Docente>();
        for (Socio socio : this.getSocios()) {
            if (socio instanceof Docente && ((Docente) socio).esResponsable()) {
                responsables.add((Docente) socio);
            }
        }
        return responsables;
    }
    
    /**
     * Devuelve el nombre del socio que tiene en su poder el libro indicado.
     * El préstamo vigente es el último préstamo del libro, y desde él se
     * obtiene el socio.
     *
     * @param p_libro el libro a consultar
     * @return el nombre del socio que tiene el libro
     * @throws LibroNoPrestadoException si el libro no está prestado
     */
    public String quienTieneElLibro(Libro p_libro) throws LibroNoPrestadoException{
        if(!p_libro.prestado()){
            throw new LibroNoPrestadoException("El libro se encuentra en la biblioteca");
        }
        
        return p_libro.ultimoPrestamo().getSocio().getNombre();
    }
    
    /**
     * Busca un socio de la biblioteca por su número de documento.
     * Recorre la colección de socios y compara el DNI de cada uno.
     *
     * @param p_dni el DNI del socio a buscar
     * @return el socio que tiene ese DNI, o null si no se encuentra
     */
    public Socio buscarSocio(int p_dni) {
        for (Socio socio : this.getSocios()) {
            
            if (socio.getDniSocio() == p_dni) {
                return socio;
            }
        }
        return null;
    }

    
    //Listados
    /**
     * Genera un listado de todos los libros de la colección,
     * indicando su numeración, información y estado de préstamo.
     * 
     * @return Cadena de caracteres con el listado de libros y su estado.
     */
    public String listaDeLibros(){
        int i=1;
        String listado = "\t Lista de Libros: \n\n";
        for(Libro li: this.getLibros()){
            listado+= i + ") " + li.toString() + " || Prestado: (";
            if(li.prestado()){
                listado+= "Si) \n";
            }else{
                listado+= "No) \n";
            }
            i++;
        }
        return listado;
    }
    
    
    /**
     * Genera un listado de los títulos de los libros de la colección,
     * evitando mostrar títulos repetidos.
     * @return Cadena de caracteres con los títulos únicos de los libros.
     */
    public String listaDeTitulos(){
        HashSet<String> titulos = new HashSet<String>();
        for(Libro li: this.getLibros()){
            titulos.add(li.getTitulo());
        }
        String titu = "Titulos Disponibles: \n";
        for(String t: titulos){
            titu += "* " + t + "\n";
        }
        return titu;
    } 
    
    
    /**
     * Genera el listado de todos los socios de la biblioteca (Formato1).
     * <p>
     * Cada socio se muestra numerado en una línea, usando el toString() de la
     * clase Socio. Al final se agrega un pie con la cantidad de socios de cada
     * tipo (Estudiante y Docente), obtenida con cantidadDeSociosPorTipo().
     *
     * @return un String con el listado de socios y el resumen por tipo
     */
    public String listaDeSocios() {
        StringBuilder listado = new StringBuilder();
        listado.append("Lista de Socios:\n");
    
        // Delega el formato de cada línea individual al método toString() de la clase Socio
        for (int i = 0; i < this.getSocios().size(); i++) {
            listado.append((i + 1)).append(") ").append(this.getSocios().get(i).toString()).append("\n");
        }
    
        // Pie de la lista reutilizando el método cantidadDeSociosPorTipo
        listado.append("**************************************\n");
        listado.append("Cantidad de Socios del tipo Estudiante: ").append(this.cantidadDeSociosPorTipo("Estudiante")).append("\n");
        listado.append("Cantidad de Socios del tipo Docente: ").append(this.cantidadDeSociosPorTipo("Docente")).append("\n");
        listado.append("**************************************");
    
        return listado.toString();
    }
    
    /**
     * Genera el listado de docentes responsables
     *
     * @return String con el listado de docentes responsables
     */
    public String listaDeDocentesResponsables() {
    String listaResponsables = "\tLista de Docentes Responsables:\n\n";
    for (Docente docente : this.docentesResponsables()) {
        listaResponsables = listaResponsables + "* " + docente.toString() + "\n";
    }
    return listaResponsables;
    }
}

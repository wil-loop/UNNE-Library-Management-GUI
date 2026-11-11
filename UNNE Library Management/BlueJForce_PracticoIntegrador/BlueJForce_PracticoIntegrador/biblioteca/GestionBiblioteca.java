package biblioteca;
import java.util.*;

/**
 * Programa principal del Sistema de Gestión de Biblioteca.
 * Contiene el menú principal, los métodos auxiliares de lectura por teclado
 * y los submenús de libros, socios, préstamos, devoluciones y consultas.
 * @author Cantero, William Francisco
 * @author Cañete Zacarías, Ana Paula
 * @author Caraballo, Ivan Jeremias
 * @author Cespedes Stefani, Ivo
 * @author Fabregat, Malena Ayelen
 * @author Soto, Nahuel Federico
 * 
 * @version 1.0
 */
public class GestionBiblioteca
{
    private static Scanner teclado = new Scanner(System.in);
    private static Biblioteca biblioteca;

    /**
     * Punto de entrada del programa. Pide el nombre de la biblioteca,
     * la crea y muestra el menú principal.
     *
     * @param args argumentos de línea de comandos (no se usan)
     */
    public static void main(String[] args){
        String nombre = leerTexto("Nombre de la Biblioteca: "); 
        biblioteca = new Biblioteca(nombre);
        mostrarMenuPrincipal();
    }

    // =============================================
    // INTEGRANTE 1 - MENU PRINCIPAL Y METODOS AUXILIARES
    // =============================================

    /**
     * Muestra el menú principal y deriva a cada submenú según la opción
     * elegida. Se repite hasta que el usuario ingresa 0 (salir).
     */
    private static void mostrarMenuPrincipal() {
        int opcion;
        do {
            System.out.println("\n--- SISTEMA DE GESTION DE BIBLIOTECA ---");
            System.out.println("1. Gestion de Libros");
            System.out.println("2. Gestion de Socios");
            System.out.println("3. Registro de Prestamos");
            System.out.println("4. Devoluciones y Vencimientos");
            System.out.println("5. Menu de Consultas");
            System.out.println("6. Menu de Docentes");
            System.out.println("0. Salir");

            opcion = leerEntero("Ingrese una opcion: ");

            switch (opcion) {
                case 1:
                    menuLibros();
                    break;
                case 2:
                    menuSocios();
                    break;
                case 3:
                    menuPrestamos();
                    break;
                case 4:
                    devolverLibroMenu();
                    break;
                case 5:
                    menuConsultas();
                    break;
                case 6:
                    menuDocentes();
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opcion incorrecta. Intente nuevamente.");
            }
        } while (opcion != 0);
    }

    /**
     * Lee un número entero por teclado. Si lo ingresado no es un entero
     * válido, muestra un error y vuelve a pedirlo hasta que sea correcto.
     *
     * @param mensaje texto que se muestra al pedir el dato
     * @return el número entero ingresado
     */
    public static int leerEntero(String mensaje) {
        int numero = -1;
        boolean error;
        do {// Manejo de exepciones para los enteros
            error = false;
            try {
                System.out.print(mensaje); 
                numero = Integer.parseInt(teclado.nextLine().trim()); // nextLine() para q no se bugee el buffer de Scanner
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un numero entero valido.");
                error = true;
            }
        } while (error);
        return numero;
    }

    /**
     * Lee un número entero estrictamente positivo (mayor a cero).
     * Si el valor es menor o igual a cero, muestra un error y lo vuelve a pedir.
     *
     * @param mensaje texto que se muestra al pedir el dato
     * @return el número entero positivo ingresado
     */
    public static int leerPositivo(String mensaje) {
        int numero;
        do {
            numero = leerEntero(mensaje); //reusa el mismo leerEntero
            if (numero <= 0) {
                System.out.println("Error: El numero debe ser mayor a cero.");
            }
        } while (numero <= 0);
        return numero;
    }

    /**
     * Lee una línea de texto por teclado, sin espacios al inicio ni al final.
     *
     * @param mensaje texto que se muestra al pedir el dato
     * @return el texto ingresado
     */
    public static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return teclado.nextLine().trim();
    }

    /**
     * Pide por teclado año, mes y día y devuelve la fecha como Calendar.
     * El mes se ingresa de 1 a 12 y se convierte internamente al rango 0-11
     * que usa Calendar. No valida que el día o el mes sean reales: como
     * GregorianCalendar es flexible, una fecha como 31/02 se corre al mes siguiente.
     *
     * @param mensaje texto que se muestra antes de pedir los datos
     * @return un Calendar con la fecha ingresada
     */
    public static Calendar leerFecha(String mensaje) { // Pide dia, mes y año para crear y devolver el Calendar
        System.out.println(mensaje);
        int anio = leerPositivo("  Ingrese el anio (ej. 2026): ");

        int mes = leerPositivo("  Ingrese el mes (1-12): ") - 1; // A Calendar se le pasa el mes de 0 a 11, por eso le resto 1
        int dia = leerPositivo("  Ingrese el dia (1-31): ");

        return new GregorianCalendar(anio, mes, dia);
    }

    // =============================================
    // INTEGRANTE 2 - GESTION DE LIBROS
    // =============================================

    /**
     * Muestra el menu de gestion de libros.
     * Permite registrar libros, listar ejemplares
     * y consultar los titulos de la biblioteca.
     */
    private static void menuLibros()
    {
        int opcion;

        do {
            System.out.println("\n--- MENU DE LIBROS ---");
            System.out.println("1. Registrar libro");
            System.out.println("2. Listar libros");
            System.out.println("3. Listar titulos");
            System.out.println("0. Volver al menu principal");

            opcion = leerEntero("Seleccione una opcion: ");

            switch (opcion) {
                case 1:
                    registrarLibro();
                    break;

                case 2:
                    mostrarLibros();
                    break;

                case 3:
                    mostrarTitulos();
                    break;

                case 0:
                    System.out.println(
                        "Volviendo al menu principal...");
                    break;

                default:
                    System.out.println(
                        "Opcion incorrecta. Intente nuevamente.");
            }

        } while (opcion != 0);
    }

    /**
     * Solicita los datos de un libro por teclado
     * y lo registra en la biblioteca.
     */
    private static void registrarLibro()
    {
        System.out.println("\n--- REGISTRAR LIBRO ---");

        String titulo = leerTexto("Titulo: ");
        String editorial = leerTexto("Editorial: ");
        int edicion = leerPositivo("Edicion: ");
        int anio = leerPositivo("Anio: ");

        biblioteca.nuevoLibro(titulo, edicion, editorial, anio);

        System.out.println("Libro registrado correctamente.");
    }

    /**
     * Muestra todos los libros de la biblioteca.
     */
    private static void mostrarLibros()
    {
        System.out.println("\n--- LISTA DE LIBROS ---");

        if (biblioteca.getLibros().isEmpty()) {
            System.out.println("No hay libros registrados.");
        }
        else {
            System.out.println(biblioteca.listaDeLibros());
        }
    }

    /**
     * Muestra los titulos disponibles en la biblioteca.
     */
    private static void mostrarTitulos()
    {
        System.out.println("\n--- LISTA DE TITULOS ---");

        if (biblioteca.getLibros().isEmpty()) {
            System.out.println("No hay titulos registrados.");
        }
        else {
            System.out.println(biblioteca.listaDeTitulos());
        }
    }

    // =============================================
    // INTEGRANTE 3 - GESTION DE SOCIOS
    // =============================================

    /**
     * Muestra el menú de gestión de socios. Permite registrar estudiantes
     * y docentes, mostrar todos los socios y contarlos por tipo.
     * Se repite hasta que el usuario ingresa 0 (volver).
     */
    public static void menuSocios(){
        int opcion;
        do {
            System.out.println("\n--- GESTIÓN DE SOCIOS ---");
            System.out.println("1. Registrar estudiante");
            System.out.println("2. Registrar docente");
            System.out.println("3. Mostrar socios");
            System.out.println("4. Cantidad de socios por tipo");
            System.out.println("0. Volver");
            opcion = leerEntero("Opción: ");
            switch (opcion) {
                case 1: 
                    registrarEstudiante(); 
                    break;
                case 2: 
                    registrarDocente(); 
                    break;
                case 3: 
                    if (biblioteca.getSocios().isEmpty()) {
                        System.out.println("No hay socios registrados.");
                    } else {
                        System.out.println(biblioteca.listaDeSocios());
                    }
                    break;
                case 4: 
                    cantidadSociosPorTi();
                    break;
                case 0:
                    break;
                default: System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    /**
     * Verifica si ya existe un socio registrado con el DNI indicado.
     *
     * @param p_biblio biblioteca donde se busca el socio
     * @param p_dni DNI a verificar
     * @return true si ya hay un socio con ese DNI, false en caso contrario
     */
    public static boolean existeSocio(Biblioteca p_biblio, int p_dni) {
        return p_biblio.buscarSocio(p_dni) != null;
    }

    /**
     * Pide por teclado nombre, DNI y carrera de un estudiante y lo registra
     * en la biblioteca. Si el DNI ya pertenece a otro socio, informa el error
     * y no lo registra.
     */
    public static void registrarEstudiante() {
        String nombre = leerTexto("Nombre: ");
        int dni = leerPositivo("DNI: ");
        if (existeSocio(biblioteca, dni)) {
            System.out.println("Ya existe un socio con ese DNI.");
            return;
        }
        String carrera = leerTexto("Carrera: ");
        biblioteca.nuevoSocioEstudiante(dni, nombre, carrera);
        System.out.println("Estudiante registrado.");
    }

    /**
     * Pide por teclado nombre, DNI y área de un docente y lo registra
     * en la biblioteca. Si el DNI ya pertenece a otro socio, informa el error
     * y no lo registra.
     */
    public static void registrarDocente() {
        String nombre = leerTexto("Nombre: ");
        int dni = leerPositivo("DNI: ");
        if (existeSocio(biblioteca, dni)) {
            System.out.println("Ya existe un socio con ese DNI.");
            return;
        }
        String area = leerTexto("Area: ");
        biblioteca.nuevoSocioDocente(dni, nombre, area);
        System.out.println("Docente registrado.");
    }

    /**
     * Muestra la cantidad de socios según el tipo elegido por el usuario:
     * docentes, estudiantes o el total de socios.
     */
    public static void cantidadSociosPorTi(){
        int opcion;
        int docentes = biblioteca.cantidadDeSociosPorTipo("Docente");
        int estudiantes = biblioteca.cantidadDeSociosPorTipo("Estudiante");
        System.out.println("\nCual tipo de socios desea contar:");
        System.out.println("1-Docentes \n2-Estudiantes \n3-Todos los socios");
        opcion = leerEntero("Opcion: ");
        if(opcion == 1){
            System.out.println("Hay " + docentes + " Socios Docentes");
        }else if (opcion == 2){
            System.out.println("Hay " + estudiantes + " Socios Estudiantes");
        }else if(opcion == 3){
            System.out.println("Hay " + (estudiantes + docentes) + " Socios");
        }else{
            System.out.println("Opcion invalida, volviendo al inicio");
        }
    }

    // =============================================
    // INTEGRANTE 4 - GESTION DE PRESTAMOS
    // =============================================
    /**
     * Submenu de gestion de prestamos.
     */
    public static void menuPrestamos(){
        int opcion;
        do {
            System.out.println();
            System.out.println("===== GESTION DE PRESTAMOS =====");
            System.out.println("1. Registrar prestamo");
            System.out.println("0. Volver al menu principal");
            opcion = leerEntero("Opcion: ");

            switch (opcion) {
                case 1:
                    registrarPrestamo();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }
        } while (opcion != 0);
    }

    /**
     * Pide el DNI del socio, el libro y la fecha de retiro, y solicita
     * el prestamo a la biblioteca informando el resultado.
     */
    public static void registrarPrestamo()
    {
        // 1) Seleccionar socio por DNI
        int dni = leerEntero("DNI del socio: ");
        Socio socio = biblioteca.buscarSocio(dni);
        if (socio == null) {
            System.out.println("No existe un socio con D.N.I. " + dni + ".");
            return;
        }

        // 2) Seleccionar libro
        Libro libro = seleccionarLibro();
        if (libro == null) {
            System.out.println("Operacion cancelada.");
            return;
        }

        // 3) Fecha de retiro
        Calendar fechaRetiro = leerFecha("Fecha de retiro (dd/mm/aaaa): ");

        // 4) Pedir el prestamo y comprobar el resultado
        boolean prestado = biblioteca.prestarLibro(fechaRetiro, socio, libro);
        if (prestado) {
            System.out.println("Prestamo registrado: " + libro.getTitulo()
                + " -> " + socio.getNombre());
        } else if (libro.prestado()) {
            System.out.println("No se pudo registrar: el libro \""
                + libro.getTitulo() + "\" ya se encuentra prestado.");
        } else {
            System.out.println("No se pudo registrar: " + socio.getNombre()
                + " no puede pedir libros (tiene prestamos vencidos o supero el limite).");
        }
    }

    /**
     * Muestra la lista numerada de libros y permite elegir uno.
     *
     * @return el Libro elegido, o null si no hay libros o el usuario cancela (0)
     */
    public static Libro seleccionarLibro()
    {
        ArrayList<Libro> libros = biblioteca.getLibros();
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados en la biblioteca.");
            return null;
        }

        System.out.println("Libros disponibles:");
        for (int i = 0; i < libros.size(); i++) {
            Libro l = libros.get(i);
            System.out.println((i + 1) + ") " + l.getTitulo()
                + " || Prestado: (" + (l.prestado() ? "Si" : "No") + ")");
        }

        int numero;
        do {
            numero = leerEntero("Numero de libro (0 para cancelar): ");
            if (numero < 0 || numero > libros.size()) {
                System.out.println("Numero invalido.");
            }
        } while (numero < 0 || numero > libros.size());

        if (numero == 0) {
            return null;
        }
        return libros.get(numero - 1);
    }

    // =============================================
    // INTEGRANTE 5 - DEVOLUCIONES Y VENCIMIENTOS
    // =============================================

    /**
     * Submenú de devoluciones y vencimientos.
     * Permite gestionar la devolución de libros y consultar préstamos vencidos.
     */
    public static void devolverLibroMenu() {
        int opcion = -1;
        do {
            System.out.println("\n--- GESTIÓN DE DEVOLUCIONES Y VENCIMIENTOS ---");
            System.out.println("1. Devolver un libro");
            System.out.println("2. Consultar préstamos vencidos");
            System.out.println("3. Demostración de error (LibroNoPrestadoException)");
            System.out.println("0. Volver al menú principal");

            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1:
                    procesarDevolucion();
                    break;
                case 2:
                    mostrarVencidos();
                    break;
                case 3:
                    probarDevoluciones();
                    break;
                case 0:
                    System.out.println("Volviendo al menú anterior...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 0);
    }

    /**
     * Ejecuta el registro de la devolución del libro seleccionado por el usuario.
     * Captura LibroNoPrestadoException si el libro no figura como prestado.
     */
    private static void procesarDevolucion() {
        System.out.println("\n*** DEVOLVER LIBRO ***");

        Libro libro = seleccionarLibro();
        if (libro == null) {
            System.out.println("Operación cancelada o libro no disponible.");
            return;
        }

        try {
            biblioteca.devolverLibro(libro);
            System.out.println("El libro \"" + libro.getTitulo() + "\" ha sido devuelto exitosamente.");
        } catch (LibroNoPrestadoException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    /**
     * Consulta y lista todos los préstamos que se encuentran vencidos al día de la fecha.
     */
    public static void mostrarVencidos() {
        System.out.println("\n*** PRÉSTAMOS VENCIDOS ***");
        java.util.ArrayList<Prestamo> prestamosVencidos = biblioteca.prestamosVencidos();

        if (prestamosVencidos == null || prestamosVencidos.isEmpty()) {
            System.out.println("No hay préstamos vencidos a la fecha.");
        } else {
            System.out.println("Total de préstamos vencidos: " + prestamosVencidos.size());
            for (Prestamo p : prestamosVencidos) {
                System.out.println("----------------------------------------");
                System.out.println(p.toString());
            }
            System.out.println("----------------------------------------");
        }
    }

    /**
     * Demostración requerida para verificar el manejo de LibroNoPrestadoException.
     */
    public static void probarDevoluciones() {
        System.out.println("\n*** DEMOSTRACIÓN DE EXCEPCIÓN: DEVOLVER LIBRO NO PRESTADO ***");

        Libro libroPrueba = new Libro("Libro Sin Préstamo", "Editorial Demo", 1, 2024);

        System.out.println("Intentando devolver un ejemplar que no está prestado...");
        try {
            biblioteca.devolverLibro(libroPrueba);
            System.out.println("El libro fue devuelto.");
        } catch (LibroNoPrestadoException e) {
            System.out.println("Excepción capturada correctamente -> " + e.getMessage());
        }
    }

    // =============================================
    // INTEGRANTE 6 - CONSULTAS Y DOCENTES
    // =============================================
    /**
     * Muestra el menú de consultas de la biblioteca.
     * Permite consultar la cantidad de estudiantes registrados
     * y conocer qué socio tiene prestado un determinado libro.
     * El menú se mantiene activo hasta que el usuario selecciona
     * la opción de volver.
     */
    private static void menuConsultas(){
        int opcion;

        do {
            System.out.println("\n--- CONSULTAS ---");
            System.out.println("1. Cantidad de estudiantes");
            System.out.println("2. Consultar quién tiene un libro");
            System.out.println("0. Volver");

            opcion = leerEntero("Ingrese una opción: ");

            switch (opcion) {
                case 1:
                    mostrarCantidadEstudiantes();
                    break;
                case 2:
                    consultarQuienTiene();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 0);
    }   

    /**
     * Obtiene y muestra la cantidad de estudiantes registrados    
     * en la biblioteca, delegando el conteo a la clase Biblioteca.
     */
    private static void mostrarCantidadEstudiantes(){
        int cantidad = biblioteca.cantidadDeSociosPorTipo("Estudiante");

        System.out.println("Cantidad de estudiantes: " + cantidad);
    }

    /**
     * Solicita al usuario el título de un libro y busca todos los
     * ejemplares que coincidan en la colección de libros de la biblioteca.
     *
     * Para cada ejemplar encontrado, consulta quién lo tiene prestado
     * e imprime el nombre del socio correspondiente.
     *
     * Si no encuentra ejemplares con el título buscado, informa la situación.
     * Si ninguno de los ejemplares encontrados está prestado, también
     * lo comunica al usuario.
     *
     * Captura la excepción LibroNoPrestadoException cuando un ejemplar
     * no se encuentra prestado.
     */
    private static void consultarQuienTiene(){
        String tituloBuscado;
        tituloBuscado = leerTexto("Ingrese el titulo del libro buscado: ");
        boolean encontrado = false;
        boolean prestado = false;
        for (Libro l: biblioteca.getLibros()){
            if (tituloBuscado.equalsIgnoreCase(l.getTitulo())){

                encontrado = true;
                try {
                    String nombreSocio = biblioteca.quienTieneElLibro(l);

                    System.out.println("El libro lo tiene: " + nombreSocio);

                    prestado = true;
                }
                catch (LibroNoPrestadoException e) {
                    //Este ejemplar no está prestado, se sigue con los demas
                }
            }
        }

        if (!encontrado){
            System.out.println("El libro buscado no se encontró.");
            return;
        } else if (!prestado){
            System.out.println("Ningún ejemplar de ese título está prestado");
        }

    }

    /**
     * Muestra el menú de gestión de docentes.
     * Permite premiar a un docente o consultar la lista de
     * docentes responsables registrados en la biblioteca.
     * El menú se repite hasta que el usuario decide regresar.
     */
    private static void menuDocentes(){
        int opcion;

        do {
            System.out.println("\n--- MENU DOCENTES ---");
            System.out.println("1. Premiar docente");
            System.out.println("2. Mostrar lista de docentes responsables");
            System.out.println("0. Volver");

            opcion = leerEntero("Ingrese una opción: ");

            switch (opcion) {
                case 1:
                    premiarDocente();
                    break;

                case 2:
                    System.out.println(biblioteca.listaDeDocentesResponsables());
                    break;

                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;

                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
                    break;
            }

        } while (opcion != 0);
    }

    /**
     * Busca un docente mediante su DNI y le permite incrementar
     * la cantidad de días permitidos para los préstamos.
     *
     * Verifica que el socio exista, que sea de tipo Docente
     * y que cumpla las condiciones para ser considerado responsable.
     * También valida que la cantidad de días adicionales ingresada
     * sea positiva.
     *
     * Si se cumplen todas las condiciones, otorga el premio
     * modificando los días permitidos para sus préstamos.
     */
    private static void premiarDocente(){
        int dni;
        int dias;

        dni = leerEntero("Ingrese el DNI del docente: ");

        Socio socioBuscado = biblioteca.buscarSocio(dni);

        if (socioBuscado == null){
            System.out.println("No se encontró ningún socio con ese DNI.");
            return;
        }

        if (!(socioBuscado instanceof Docente)) {
            System.out.println("El socio ingresado no es un docente.");
            return;
        }

        Docente docenteBuscado = (Docente) socioBuscado;

        if (!docenteBuscado.esResponsable()){
            System.out.println("El docente no cumple las condiciones para recibir el premio.");
            return;
        }

        dias = leerEntero("Ingrese la cantidad de días adicionales: ");

        if (dias <= 0) {
            System.out.println("La cantidad de días debe ser mayor que cero.");
            return;
        }

        docenteBuscado.cambiarDiasDePrestamo(dias);

        System.out.println("Premio otorgado correctamente.");
        System.out.println("Nuevo período de préstamo: " + docenteBuscado.getDiasPrestamo() + " días.");
    }
}
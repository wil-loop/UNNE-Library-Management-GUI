package biblioteca;
 
/**
 * Excepción que se lanza cuando se intenta realizar una operación 
 * sobre un libro que no se encuentra prestado. crea la exepciones.
 * 
 * @author Ivan Jeremias Caraballo
 * @version 1.0
 */
public class LibroNoPrestadoException extends Exception
{
/** Construye una excepción sin mensaje ni causa especificada. */
public LibroNoPrestadoException() {
super();
}

/**
Construye una excepción con un mensaje descriptivo.
@param mensaje Descripción del motivo por el cual se produjo la excepción.
*/
public LibroNoPrestadoException(String mensaje) {
super(mensaje);
}

/**
Construye una excepción con un mensaje descriptivo y una causa asociada.
@param mensaje Descripción del motivo por el cual se produjo la excepción.
@param causa Excepción original que provocó esta excepción.
*/
public LibroNoPrestadoException(String mensaje, Throwable causa) {
super(mensaje, causa);
}

/**
Construye una excepción a partir de una causa.
@param causa Excepción original que provocó esta excepción.
*/
public LibroNoPrestadoException(Throwable causa) {
super(causa);
}
}

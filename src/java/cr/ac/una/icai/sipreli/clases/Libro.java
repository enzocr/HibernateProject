package cr.ac.una.icai.sipreli.clases;
// Generated Aug 9, 2020 6:38:33 PM by Hibernate Tools 4.3.1
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Libro generated by hbm2java
 */
public class Libro implements java.io.Serializable {

    private Integer isbn;
    private String nombre;
    private String autor;
    private Integer anoPublicacion;
    private String editorial;
    private String genero;

    public Libro() {
    }

    public Libro(Integer isbn) {
        this.isbn = isbn;
    }

    public Libro(Integer isbn, String nombre, String autor, Integer anoPublicacion, String editorial, String genero, Set<Prestamo> prestamos) {
        this.isbn = isbn;
        this.nombre = nombre;
        this.autor = autor;
        this.anoPublicacion = anoPublicacion;
        this.editorial = editorial;
        this.genero = genero;
    }

    public Integer getIsbn() {
        return this.isbn;
    }

    public void setIsbn(Integer isbn) {
        this.isbn = isbn;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAutor() {
        return this.autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Integer getAnoPublicacion() {
        return this.anoPublicacion;
    }

    public void setAnoPublicacion(Integer anoPublicacion) {
        this.anoPublicacion = anoPublicacion;
    }

    public String getEditorial() {
        return this.editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getGenero() {
        return this.genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.isbn);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Libro other = (Libro) obj;
        return true;
    }

}

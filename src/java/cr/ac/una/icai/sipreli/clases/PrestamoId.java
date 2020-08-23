package cr.ac.una.icai.sipreli.clases;
// Generated Aug 9, 2020 6:38:33 PM by Hibernate Tools 4.3.1

import java.util.Objects;

/**
 * PrestamoId generated by hbm2java
 */
public class PrestamoId implements java.io.Serializable {

    private Libro libro;
    private Estudiante estudiante;

    public PrestamoId() {
        this.libro = new Libro();
        this.estudiante = new Estudiante();
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.libro);
        hash = 71 * hash + Objects.hashCode(this.estudiante);
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
        final PrestamoId other = (PrestamoId) obj;
        if (!Objects.equals(this.libro, other.libro)) {
            return false;
        }
        if (!Objects.equals(this.estudiante, other.estudiante)) {
            return false;
        }
        return true;
    }

}
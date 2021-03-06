package cr.ac.una.icai.sipreli.clases;
// Generated Aug 9, 2020 6:38:33 PM by Hibernate Tools 4.3.1

import java.util.Date;
import java.util.Objects;

/**
 * Prestamo generated by hbm2java
 */
public class Prestamo implements java.io.Serializable {

    private PrestamoId id;
    private Date fechaPrestamo;
    private Date fechaDevolucion;

    public Prestamo() {
        this.id = new PrestamoId();
    }

    public PrestamoId getId() {
        return this.id;
    }

    public void setId(PrestamoId id) {
        this.id = id;
    }

    public Date getFechaPrestamo() {
        return this.fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public Date getFechaDevolucion() {
        return this.fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        final Prestamo other = (Prestamo) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}

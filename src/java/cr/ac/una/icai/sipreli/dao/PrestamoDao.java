/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.icai.sipreli.dao;

import cr.ac.una.icai.sipreli.clases.Estudiante;
import cr.ac.una.icai.sipreli.clases.Libro;
import cr.ac.una.icai.sipreli.clases.Prestamo;
import cr.ac.una.icai.sipreli.dao.interfaces.IPrestamoDao;
import hibernate.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author Enzo Quartino Zamora
 * <github.com/enzocr || email: enzoquartino@gmail.com>
 */
public class PrestamoDao implements IPrestamoDao {

    private Session session;

    public PrestamoDao() {

    }

    @Override
    public Integer insert(Prestamo obj) {

        try {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = this.session.beginTransaction();
            obj.setFechaPrestamo(new java.sql.Timestamp(obj.getFechaPrestamo().getTime()));
            obj.setFechaDevolucion(new java.sql.Timestamp(obj.getFechaDevolucion().getTime()));
            this.session.save(obj);
            tx.commit();
            return 0;
        } catch (ConstraintViolationException cvex) {
            cvex.printStackTrace();
            return 1;
        } catch (Exception ex) {
            ex.printStackTrace();
            return 2;
        }
    }

    @Override
    public List<Estudiante> getStudentsByBook(Integer isbn) {
        try {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = this.session.beginTransaction();
            List<Prestamo> matchingStudents = this.session.createQuery("From Prestamo where isbn = " + isbn).list();
            List<Estudiante> students = new ArrayList<>();
            matchingStudents.stream().map((temp) -> (Estudiante) this.session.createQuery("From Estudiante where carnet="
                    + temp.getId().getEstudiante().getCarnet()).list().get(0)).forEachOrdered((estudiante) -> {
                students.add(estudiante);
            });

            return students;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Libro> getBooksByStudent(Integer carnet) {
        try {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = this.session.beginTransaction();
            List<Prestamo> matchingBooks = this.session.createQuery("From Prestamo where carnet = " + carnet).list();
            List<Libro> books = new ArrayList<>();
//            int i = 0;
//            for (Prestamo temp : matchingBooks) {
//                Libro libro = (Libro) this.session.createQuery("From Libro where isbn="
//                        + matchingBooks.get(i).getLibro().getIsbn()).list().get(0);
//                books.add(libro);
//                i++;
//            }
            matchingBooks.stream().map((temp) -> (Libro) this.session.createQuery("From Libro where isbn="
                    + temp.getId().getLibro().getIsbn()).list().get(0)).forEachOrdered((libro) -> {
                books.add(libro);
            });

            return books;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}

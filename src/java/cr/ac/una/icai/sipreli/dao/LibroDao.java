/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.icai.sipreli.dao;

import cr.ac.una.icai.sipreli.clases.Libro;
import cr.ac.una.icai.sipreli.dao.interfaces.ILibroDao;
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
public class LibroDao implements ILibroDao {

    private Session session;

    public LibroDao() {

    }

    @Override
    public Integer insert(Libro obj) {
        try {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = this.session.beginTransaction();
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
    public boolean update(Libro obj) {
        try {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = this.session.beginTransaction();
            this.session.merge(obj);
            tx.commit();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Libro> getAll() {
        try {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = this.session.beginTransaction();
            List<Libro> list = this.session.createQuery("From Libro").list();
            this.session.close();
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Libro getByIsbn(Integer isbn) {
        try {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = this.session.beginTransaction();
            List<Libro> list = this.session.createQuery("from Libro where isbn = " + isbn).list();
            this.session.close();
            if (list.isEmpty()) {
                return null;
            } else {
                return list.get(0);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Libro> getByName(String name) {
        try {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = this.session.beginTransaction();
            List<Libro> list = this.session.createQuery("from Libro where nombre like '%" + name + "%'").list();
            this.session.close();
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean remove(Libro obj) {
        try {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = this.session.beginTransaction();
            this.session.delete(obj);
            tx.commit();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

}

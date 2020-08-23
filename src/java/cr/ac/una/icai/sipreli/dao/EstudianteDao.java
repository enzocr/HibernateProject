/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.icai.sipreli.dao;

import cr.ac.una.icai.sipreli.clases.Estudiante;
import cr.ac.una.icai.sipreli.dao.interfaces.IEstudianteDao;
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
public class EstudianteDao implements IEstudianteDao {

    private Session session;

    public EstudianteDao() {

    }

    @Override
    public Integer insert(Estudiante obj) {

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
    public boolean update(Estudiante obj) {
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
    public List<Estudiante> getAll() {
        try {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = this.session.beginTransaction();
            List<Estudiante> list = this.session.createQuery("From Estudiante").list();
            this.session.close();
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Estudiante getByCarnet(Integer carnet) {
        try {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = this.session.beginTransaction();
            List<Estudiante> list = this.session.createQuery("from Estudiante where carnet = " + carnet).list();
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
    public List<Estudiante> getByName(String name) {
        try {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = this.session.beginTransaction();
            List<Estudiante> list = this.session.createQuery("from Estudiante where nombre like '%" + name + "%'").list();
            this.session.close();
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public boolean remove(Estudiante obj) {
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

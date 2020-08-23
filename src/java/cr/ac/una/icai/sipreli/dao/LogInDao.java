/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.icai.sipreli.dao;

import cr.ac.una.icai.sipreli.dao.interfaces.ILogInDao;
import cr.ac.una.icai.sipreli.clases.UsuarioLogin;
import hibernate.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Enzo Quartino Zamora
 * <github.com/enzocr || email: enzoquartino@gmail.com>
 */
public class LogInDao implements ILogInDao {

    private Session session;

    public LogInDao() {

    }

    @Override
    public UsuarioLogin userExists(UsuarioLogin user) {

        try {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = this.session.beginTransaction();
            List<UsuarioLogin> list = this.session.createQuery("SELECT * FROM usuario_login").list();
            this.session.close();
            for (UsuarioLogin u : list) {
                if (u.getUsuario().equals(user.getUsuario())
                        && u.getContrasena().equals(user.getContrasena())) {
                    return user;
                }
            }
            if (list.isEmpty()) {
                return null;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return null;
    }
}

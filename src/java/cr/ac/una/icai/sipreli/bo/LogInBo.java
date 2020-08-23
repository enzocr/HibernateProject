/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.icai.sipreli.bo;

import cr.ac.una.icai.sipreli.bo.interfaces.ILogInBo;
import cr.ac.una.icai.sipreli.clases.UsuarioLogin;
import cr.ac.una.icai.sipreli.dao.interfaces.ILogInDao;

/**
 *
 * @author Enzo Quartino Zamora
 * <github.com/enzocr || email: enzoquartino@gmail.com>
 */
public class LogInBo implements ILogInBo {

    private ILogInDao logInDao;

    @Override
    public UsuarioLogin userExists(UsuarioLogin user) {
        return logInDao.userExists(user);
    }

    public ILogInDao getLogInDao() {
        return logInDao;
    }

    public void setLogInDao(ILogInDao logInDao) {
        this.logInDao = logInDao;
    }

}

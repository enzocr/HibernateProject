/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.icai.sipreli.dao.interfaces;

import cr.ac.una.icai.sipreli.clases.UsuarioLogin;

/**
 *
 * @author Enzo Quartino Zamora
 * <github.com/enzocr || email: enzoquartino@gmail.com>
 */
public interface ILogInDao {

    UsuarioLogin userExists(UsuarioLogin user);
}

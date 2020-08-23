/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.icai.sipreli.beans;

import cr.ac.una.icai.sipreli.bo.LogInBo;
import cr.ac.una.icai.sipreli.clases.UsuarioLogin;
import cr.ac.una.icai.sipreli.utilidades.Utilidades;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Enzo Quartino Zamora
 * <github.com/enzocr || email: enzoquartino@gmail.com>
 */
public class LogInBean implements Serializable {

    private UsuarioLogin user;
    private LogInBo logInBo;

    public LogInBean() {
        this.user = new UsuarioLogin();
    }

    public String userExists() {
        if (validateData()) {
            UsuarioLogin obj = getLogInBo().userExists(user);
            if (obj.getUsuario() != null) {
                goToIndex();
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, Utilidades.obtenerMsj("msj.infoIncorrect"), ""));

            }
        }

        return "";
    }

    public String goToIndex() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml?init=true");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return "index";
    }

    public boolean validateData() {
        boolean flag = true;
        if (user.getUsuario().trim().length() == 0) {
            flag = false;
            FacesContext.getCurrentInstance().addMessage("form:txtUsername",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "", Utilidades.obtenerMsj("validate.username")));
        }
        if (user.getContrasena().trim().length() == 0) {
            flag = false;
            FacesContext.getCurrentInstance().addMessage("form:txtPass",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "", Utilidades.obtenerMsj("validate.pass")));
        }

        return flag;
    }

    public String clean() {
        this.restartBean();
        return "";
    }

    private void restartBean() {
        this.user = new UsuarioLogin();
    }

    public UsuarioLogin getUser() {
        return user;
    }

    public void setUser(UsuarioLogin user) {
        this.user = user;
    }

    public LogInBo getLogInBo() {
        return logInBo;
    }

    public void setLogInBo(LogInBo logInBo) {
        this.logInBo = logInBo;
    }

}

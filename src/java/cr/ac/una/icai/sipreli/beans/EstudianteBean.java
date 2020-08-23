/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.icai.sipreli.beans;

import cr.ac.una.icai.sipreli.bo.EstudianteBo;
import cr.ac.una.icai.sipreli.clases.Estudiante;
import cr.ac.una.icai.sipreli.utilidades.Utilidades;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author Enzo Quartino Zamora
 * <github.com/enzocr || email: enzoquartino@gmail.com>
 */
public class EstudianteBean implements Serializable {

    private Estudiante estudiante;
    private EstudianteBo estudianteBo;
    private List<Estudiante> lista;
    private boolean modificando;

    public EstudianteBean() {
        this.estudiante = new Estudiante();
        this.modificando = false;
        this.lista = new ArrayList<>();
    }

    public String insert() {
        if (validateData()) {
            int res = this.estudianteBo.insert(estudiante);

            switch (res) {
                case 0:
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO, Utilidades.obtenerMsj("msj.insertedSuccessfully"), ""));
                    break;
                case 1:
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO, Utilidades.obtenerMsj("msj.cantConnBD"), ""));
                    break;
                case 2:
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO, Utilidades.obtenerMsj("msj.error"), ""));
                    break;
                case 3:
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO, Utilidades.obtenerMsj("msj.alreadyExists"), ""));
                    break;
            }
            restartBean();

        }
        return "";
    }

    public String update() {
        if (validateData()) {
            boolean res = this.estudianteBo.update(estudiante);

            if (res) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, Utilidades.obtenerMsj("msj.updatedSuccessfully"), ""));
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, Utilidades.obtenerMsj("msj.error"), ""));
            }

            restartBean();
        }
        return "";
    }

    public String delete(Estudiante obj) {
        boolean res = this.estudianteBo.remove(obj);

        if (res) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, Utilidades.obtenerMsj("msj.deletedSuccessfully"), ""));

        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, Utilidades.obtenerMsj("msj.error"), ""));
        }
        restartBean();
        return "";
    }

    public String select(Estudiante obj) {
        this.estudiante = obj;
        this.modificando = true;
        return "";
    }

    public List<SelectItem> fillSchools() {
        List<SelectItem> comboList = new ArrayList<>();
        comboList.add(new SelectItem(0, Utilidades.obtenerMsj("select.option")));
        comboList.add(new SelectItem(1, "Informática"));
        comboList.add(new SelectItem(2, "Biología"));
        comboList.add(new SelectItem(3, "Matemática"));
        comboList.add(new SelectItem(4, "Música"));
        return comboList;
    }

    public List<SelectItem> fillStudents() {
        List<SelectItem> list = new ArrayList<>();
        List<Estudiante> listaLibros = estudianteBo.getAll();
        Estudiante obj = new Estudiante();
        obj.setCarnet(0);
        obj.setNombre(Utilidades.obtenerMsj("select.option"));
        list.add(new SelectItem(obj, obj.getNombre()));
        for (int i = 0; i < listaLibros.size(); i++) {
            Estudiante student = listaLibros.get(i);
            list.add(new SelectItem(student, student.getCarnet()+ " " + student.getNombre()));
        }

        return list;
    }

    public String getByCarnet() {
        if (estudiante.getCarnet() == null) {
            FacesContext.getCurrentInstance().addMessage("form:txtCarnet",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "", Utilidades.obtenerMsj("validate.carnet")));
        } else {
            Estudiante obj = estudianteBo.getByCarnet(this.estudiante.getCarnet());
            if (obj == null) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_FATAL, Utilidades.obtenerMsj("msj.infoNotFound"), ""));
            } else {
                this.lista.clear();
                this.lista.add(obj);
            }
        }
        return "";
    }

    public String getByName() {
        if (this.estudiante.getNombre().trim().length() == 0) {
            FacesContext.getCurrentInstance().addMessage("form:txtNombre",
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "", Utilidades.obtenerMsj("validate.name")));
        } else {
            this.lista = this.estudianteBo.getByName(this.estudiante.getNombre());
            if (this.lista.isEmpty()) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_FATAL, Utilidades.obtenerMsj("msj.infoNotFound"), ""));
                this.lista = new ArrayList();
            }
        }
        return "";
    }

    public String clean() {
        this.restartBean();
        return "";
    }

    private void restartBean() {
        this.estudiante = new Estudiante();
        this.modificando = false;
        this.lista = new ArrayList<>();
    }

    public String changeLanguage() {
        Locale loc = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        if (loc.getLanguage().equals("es")) {
            FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale("en"));
        } else {
            FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale("es"));
        }
        return "";
    }

    public boolean validateData() {
        boolean flag = true;
        if (estudiante.getCarnet() == null) {
            flag = false;
            FacesContext.getCurrentInstance().addMessage("form:txtCarnet",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "", Utilidades.obtenerMsj("validate.carnet")));
        }
        if (estudiante.getNombre().trim().length() == 0) {
            flag = false;
            FacesContext.getCurrentInstance().addMessage("form:txtNombre",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "", Utilidades.obtenerMsj("validate.name")));
        }
        if (estudiante.getTelefono() == null) {
            flag = false;
            FacesContext.getCurrentInstance().addMessage("form:txtTelefono",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "", Utilidades.obtenerMsj("validate.phone")));
        }
        if (estudiante.getDireccion().trim().length() == 0) {
            flag = false;
            FacesContext.getCurrentInstance().addMessage("form:txtDireccion",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "", Utilidades.obtenerMsj("validate.address")));
        }

        if (estudiante.getEmail().trim().length() == 0) {
            flag = false;
            FacesContext.getCurrentInstance().addMessage("form:txtEmail",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "", Utilidades.obtenerMsj("validate.email")));
        }
        if (estudiante.getEscuela().trim().length() == 0) {
            flag = false;
            FacesContext.getCurrentInstance().addMessage("form:cbEscuela",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "", Utilidades.obtenerMsj("validate.school")));
        }

        return flag;
    }

    public String goTo() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("mantEstudiante.xhtml?init=true");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return "mantEstudiante";
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public EstudianteBo getEstudianteBo() {
        return estudianteBo;
    }

    public void setEstudianteBo(EstudianteBo estudianteBo) {
        this.estudianteBo = estudianteBo;
    }

    public List<Estudiante> getLista() {
        if (lista.isEmpty()) {
            lista = this.estudianteBo.getAll();
        }
        return lista;
    }

    public void setLista(List<Estudiante> lista) {
        this.lista = lista;
    }

    public boolean isModificando() {
        return modificando;
    }

    public void setModificando(boolean modificando) {
        this.modificando = modificando;
    }

}

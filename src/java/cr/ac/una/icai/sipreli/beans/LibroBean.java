/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.icai.sipreli.beans;

import cr.ac.una.icai.sipreli.bo.LibroBo;
import cr.ac.una.icai.sipreli.clases.Libro;
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
public class LibroBean implements Serializable {

    private Libro libro;
    private LibroBo libroBo;
    private List<Libro> lista;
    private boolean modificando;

    public LibroBean() {
        this.libro = new Libro();
        this.modificando = false;
        this.lista = new ArrayList<>();
    }

    public String insert() {
        if (validateData()) {
            int res = this.libroBo.insert(libro);

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
            boolean res = this.libroBo.update(libro);

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

    public String delete(Libro obj) {
        boolean res = this.libroBo.remove(obj);

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

    public String select(Libro obj) {
        this.libro = obj;
        this.modificando = true;
        return "";
    }

    public List<SelectItem> fillGenres() {
        List<SelectItem> comboList = new ArrayList<>();
        comboList.add(new SelectItem(0, Utilidades.obtenerMsj("select.option")));
        comboList.add(new SelectItem(1, "Novela"));
        comboList.add(new SelectItem(2, "Comedia"));
        comboList.add(new SelectItem(3, "Poesía"));
        comboList.add(new SelectItem(4, "Educativo"));
        return comboList;
    }

    public List<SelectItem> fillBooks() {
        List<SelectItem> list = new ArrayList<>();
        List<Libro> listaLibros = libroBo.getAll();
        Libro obj = new Libro();
        obj.setIsbn(0);
        obj.setNombre(Utilidades.obtenerMsj("select.option"));
        list.add(new SelectItem(obj, obj.getNombre()));
        for (int i = 0; i < listaLibros.size(); i++) {
            Libro book = listaLibros.get(i);
            list.add(new SelectItem(book, book.getIsbn() + " " + book.getNombre()));
        }
        return list;
    }

    public String getByIsbn() {
        if (libro.getIsbn() == null) {
            FacesContext.getCurrentInstance().addMessage("form:txtIsbn",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "", Utilidades.obtenerMsj("validate.isbn")));
        } else {
            Libro obj = libroBo.getByIsbn(this.libro.getIsbn());
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
        if (this.libro.getNombre().trim().length() == 0) {
            FacesContext.getCurrentInstance().addMessage("form:txtNombre",
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "", Utilidades.obtenerMsj("validate.name")));
        } else {
            this.lista = this.libroBo.getByName(this.libro.getNombre());
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
        this.libro = new Libro();
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
        if (libro.getIsbn() == null) {
            flag = false;
            FacesContext.getCurrentInstance().addMessage("form:txtIsbn",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "", Utilidades.obtenerMsj("validate.isbn")));
        }
        if (libro.getNombre().trim().length() == 0) {
            flag = false;
            FacesContext.getCurrentInstance().addMessage("form:txtNombre",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "", Utilidades.obtenerMsj("validate.name")));
        }
        if (libro.getAutor() == null) {
            flag = false;
            FacesContext.getCurrentInstance().addMessage("form:txtAutor",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "", Utilidades.obtenerMsj("validate.author")));
        }
        if (libro.getAnoPublicacion() == null) {
            flag = false;
            FacesContext.getCurrentInstance().addMessage("form:txtPublicacion",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "", Utilidades.obtenerMsj("validate.year")));
        }

        if (libro.getEditorial().trim().length() == 0) {
            flag = false;
            FacesContext.getCurrentInstance().addMessage("form:txtEditorial",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "", Utilidades.obtenerMsj("validate.editorial")));
        }
        if (libro.getGenero().trim().length() == 0) {
            flag = false;
            FacesContext.getCurrentInstance().addMessage("form:cbGenero",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "", Utilidades.obtenerMsj("validate.genre")));
        }

        return flag;
    }

    public String goTo() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("mantLibro.xhtml?init=true");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return "mantLibro";
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public LibroBo getLibroBo() {
        return libroBo;
    }

    public void setLibroBo(LibroBo libroBo) {
        this.libroBo = libroBo;
    }

    public List<Libro> getLista() {
        if (lista.isEmpty()) {
            lista = this.libroBo.getAll();
        }
        return lista;
    }

    public void setLista(List<Libro> lista) {
        this.lista = lista;
    }

    public boolean isModificando() {
        return modificando;
    }

    public void setModificando(boolean modificando) {
        this.modificando = modificando;
    }

}

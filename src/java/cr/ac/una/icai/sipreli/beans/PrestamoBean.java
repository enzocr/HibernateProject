/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.icai.sipreli.beans;

import cr.ac.una.icai.sipreli.bo.PrestamoBo;
import cr.ac.una.icai.sipreli.clases.Estudiante;
import cr.ac.una.icai.sipreli.clases.Libro;
import cr.ac.una.icai.sipreli.clases.Prestamo;
import cr.ac.una.icai.sipreli.utilidades.Utilidades;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Enzo Quartino Zamora
 * <github.com/enzocr || email: enzoquartino@gmail.com>
 */
public class PrestamoBean implements Serializable {

    private Prestamo prestamo;
    private PrestamoBo prestamoBo;
    private List<Prestamo> lista;
    private List<Estudiante> listaEstudiantes;
    private List<Libro> listaLibros;

    public PrestamoBean() {
        this.prestamo = new Prestamo();
        this.lista = new ArrayList<>();
        this.listaEstudiantes = new ArrayList<>();
        this.listaLibros = new ArrayList<>();
    }

    public String insert() {
        if (validateData()) {
            prestamo.setFechaPrestamo(new Date());
            prestamo.setFechaDevolucion(Utilidades.addDays(new Date()));
            int res = this.prestamoBo.insert(prestamo);

            switch (res) {
                case 0:
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO, Utilidades.obtenerMsj("Devolver el " + prestamo.getFechaDevolucion()), ""));
                    break;
                case 1:
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO, "Ya se encuentra prestado", ""));
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

    public String getBooksByStudent() {
        if (this.prestamo.getId().getEstudiante().getCarnet() == null) {
            FacesContext.getCurrentInstance().addMessage("form:txtCarnet",
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "", Utilidades.obtenerMsj("validate.name")));
        } else {
            this.listaLibros = this.prestamoBo.getBooksByStudent(this.prestamo.getId().getEstudiante().getCarnet());
            if (this.lista.isEmpty()) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_FATAL, Utilidades.obtenerMsj("msj.infoNotFound"), ""));
                this.lista = new ArrayList();
            }
        }
        return "";
    }

    public String generateReportBooksByStudent() {

        try {
            FacesContext context = FacesContext.getCurrentInstance();

            context.getExternalContext().getSessionMap().put("carnetAEnviarAlServlet", this.prestamo.getId().getEstudiante().getCarnet());

            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
            RequestDispatcher dispatcher = request.getRequestDispatcher("/ServletReporteLibrosPorEstudiante");
            dispatcher.forward(request, response);
            return "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public String generateReportStudentsByBook() {

        try {
            FacesContext context = FacesContext.getCurrentInstance();

            context.getExternalContext().getSessionMap().put("isbnAEnviarAlServlet", this.prestamo.getId().getLibro().getIsbn());

            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
            RequestDispatcher dispatcher = request.getRequestDispatcher("/ServletReporteEstudiantesPorLibro");
            dispatcher.forward(request, response);
            return "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public String getStudentsByBook() {
        if (this.prestamo.getId().getLibro().getIsbn() == null) {
            FacesContext.getCurrentInstance().addMessage("form:txtIsbn",
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "", Utilidades.obtenerMsj("validate.name")));
        } else {
            this.listaEstudiantes = this.prestamoBo.getStudentsByBook(this.prestamo.getId().getLibro().getIsbn());
            if (this.lista.isEmpty()) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_FATAL, Utilidades.obtenerMsj("msj.infoNotFound"), ""));
                this.lista = new ArrayList();
            }
        }
        return "";
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

    public String goToBooks() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("verLibrosPorEstudiante.xhtml?init=true");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return "verLibrosPorEstudiante";
    }

    public String goToStudents() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("verEstudiantesPorLibro.xhtml?init=true");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return "verEstudiantesPorLibro";
    }

    public String goToRegistration() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("registroPrestamo.xhtml?init=true");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return "registroPrestamo";
    }

    public String clean() {
        this.restartBean();
        return "";
    }

    private void restartBean() {
        this.prestamo = new Prestamo();
        this.lista = new ArrayList<>();
        this.listaEstudiantes = new ArrayList<>();
        this.listaLibros = new ArrayList<>();
    }

    public boolean validateData() {
        boolean flag = true;
        if (prestamo.getId().getEstudiante().getCarnet() == null) {
            flag = false;
            FacesContext.getCurrentInstance().addMessage("form:txtCarnet",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "", Utilidades.obtenerMsj("validate.carnet")));
        }
        if (prestamo.getId().getLibro().getIsbn() == null) {
            flag = false;
            FacesContext.getCurrentInstance().addMessage("form:txtIsbn",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "", Utilidades.obtenerMsj("validate.isbn")));
        }

        return flag;
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }

    public PrestamoBo getPrestamoBo() {
        return prestamoBo;
    }

    public void setPrestamoBo(PrestamoBo prestamoBo) {
        this.prestamoBo = prestamoBo;
    }

    public List<Prestamo> getLista() {
        return lista;
    }

    public void setLista(List<Prestamo> lista) {
        this.lista = lista;
    }

    public List<Estudiante> getListaEstudiantes() {
        if (listaEstudiantes.isEmpty()) {
            listaEstudiantes = this.prestamoBo.getStudentsByBook(prestamo.getId().getEstudiante().getCarnet());
        }
        return listaEstudiantes;
    }

    public void setListaEstudiantes(List<Estudiante> listaEstudiantes) {
        this.listaEstudiantes = listaEstudiantes;
    }

    public List<Libro> getListaLibros() {
        if (listaLibros.isEmpty()) {
            listaLibros = this.prestamoBo.getBooksByStudent(prestamo.getId().getLibro().getIsbn());
        }
        return listaLibros;
    }

    public void setListaLibros(List<Libro> listaLibros) {
        this.listaLibros = listaLibros;
    }

}

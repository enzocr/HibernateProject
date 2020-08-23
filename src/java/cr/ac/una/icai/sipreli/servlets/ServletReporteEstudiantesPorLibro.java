/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.icai.sipreli.servlets;

import hibernate.HibernateUtil;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;

/**
 *
 * @author Enzo Quartino Zamora
 * <github.com/enzocr || email: enzoquartino@gmail.com>
 */
public class ServletReporteEstudiantesPorLibro extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            //********    SE DECLARAN E INICIALIZAN VARIABLES   **********//
            OutputStream servletOutputStream = response.getOutputStream();
            HashMap parametrosReporte = new HashMap();
            InputStream reportStream = null;
            String nombreArchivo = "";
            String nombreJasper = "";
            FacesContext ctx = FacesContext.getCurrentInstance();
            Map sessionMap = ctx.getExternalContext().getSessionMap();
            //**************************************************************************//

            Integer isbn = (Integer) sessionMap.get("isbnAEnviarAlServlet");  //****  SE OBTIENEN LOS VALORES QUE VIENEN DEL BEAN  ******//

            //HASHMAP PARA LOS PARAMETROS DEL REPORTE
            parametrosReporte.put("isbn", isbn); //***  SE PASA EL PARAMETRO AL REPORTE-> EL 1 ES EL NOMBRE DEL PAREMETRO EN EL REPORTE, EL 2 ES EL VALOR QUE VA   *******//

            //********INDICA CUAL ES EL JASPER QUE SE VA A UTILIZAR Y EL NOMBRE QUE TENDRA EL ARCHIVO**********//
            response.setContentType("application/pdf");     //******  SE INDICA EL FORMATO QUE TENDRA EL REPORTE -> PDF ******************//
            //response.setContentType("application/vnd.ms-excel"); //******  SE INDICA EL FORMATO QUE TENDRA EL REPORTE -> XLS ******************//

            nombreJasper = "reporteEstudiantesPorLibro.jasper";               //******  SE ESPECIFICA EL NOMBRE DEL JASPER A UTILIZAR ******************//
            nombreArchivo = "Reporte_Estudiantes_Por_Libro.pdf";         //******  SE ESPECIFICA EL NOMBRE DEL REPORTE A GENERAR ******************//

            ctx.responseComplete();
            reportStream = this.getServletConfig().getServletContext().getResourceAsStream("/reportes/" + nombreJasper);
            response.setHeader("Content-disposition", "attachment;filename=" + nombreArchivo);

            //**********  SE OBTIENE LA CONEXION PARA PASARLA AL REPORTE  **********//            
            Session session = HibernateUtil.getSessionFactory().openSession();
            SessionImpl sessionImpl = (SessionImpl) session;
            Connection conn = sessionImpl.connection();
            //**************************************************************************//

            //**********  SE GENERA EL REPORTE Y CIERRA LA CONEXION  **********//
            JasperRunManager.runReportToPdfStream(reportStream, servletOutputStream, parametrosReporte, conn);
            //generarArchivoXls(reportStream, servletOutputStream, parametrosReporte, conn);
            conn.close();
            //**************************************************************************//

            //**********  DESPLIEGA EL REPORTE  **********//
            servletOutputStream.flush();
            servletOutputStream.close();
            //**************************************************************************//

        } catch (Throwable e) {
            e.printStackTrace();
        }

    }

    public static void generarArchivoXls(InputStream reportStream, OutputStream servletOutputStream, HashMap parametrosReporte, Connection conn) throws JRException {

        //EN EL CASO DE EXCEL, SE IGNORA LA PAGINACION PARA QUE SALGA EN UNA SOLA "TIRA".
        parametrosReporte.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.TRUE);

        JasperPrint jPrint = JasperFillManager.fillReport(reportStream, parametrosReporte, conn);

        //CODIGO PARA EXCEL:
        JRXlsExporter exportadorXLS = new JRXlsExporter();
        exportadorXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT, jPrint);
        exportadorXLS.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, servletOutputStream);
        exportadorXLS.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
        exportadorXLS.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
        exportadorXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
        exportadorXLS.setParameter(JRXlsExporterParameter.IS_FONT_SIZE_FIX_ENABLED, Boolean.TRUE);
        exportadorXLS.exportReport();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

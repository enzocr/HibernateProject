/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.icai.sipreli.bo.interfaces;

import cr.ac.una.icai.sipreli.clases.Estudiante;
import cr.ac.una.icai.sipreli.clases.Libro;
import cr.ac.una.icai.sipreli.clases.Prestamo;
import java.util.List;

/**
 *
 * @author Enzo Quartino Zamora
 * <github.com/enzocr || email: enzoquartino@gmail.com>
 */
public interface IPrestamoBo {

    Integer insert(Prestamo obj);

    List<Libro> getBooksByStudent(Integer carnet);

    List<Estudiante> getStudentsByBook(Integer isbn);
}

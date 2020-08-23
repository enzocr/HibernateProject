/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.icai.sipreli.bo;

import cr.ac.una.icai.sipreli.bo.interfaces.IPrestamoBo;
import cr.ac.una.icai.sipreli.clases.Estudiante;
import cr.ac.una.icai.sipreli.clases.Libro;
import cr.ac.una.icai.sipreli.clases.Prestamo;
import cr.ac.una.icai.sipreli.dao.interfaces.IPrestamoDao;
import java.util.List;

/**
 *
 * @author Enzo Quartino Zamora
 * <github.com/enzocr || email: enzoquartino@gmail.com>
 */
public class PrestamoBo implements IPrestamoBo {

    private IPrestamoDao prestamoDao;

    public PrestamoBo() {

    }

    @Override
    public Integer insert(Prestamo obj) {
        return prestamoDao.insert(obj);
    }

    @Override
    public List<Libro> getBooksByStudent(Integer carnet) {
        return prestamoDao.getBooksByStudent(carnet);
    }

    @Override
    public List<Estudiante> getStudentsByBook(Integer isbn) {
        return prestamoDao.getStudentsByBook(isbn);
    }

    public IPrestamoDao getPrestamoDao() {
        return prestamoDao;
    }

    public void setPrestamoDao(IPrestamoDao prestamoDao) {
        this.prestamoDao = prestamoDao;
    }

}

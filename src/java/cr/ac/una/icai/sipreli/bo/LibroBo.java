/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.icai.sipreli.bo;

import cr.ac.una.icai.sipreli.bo.interfaces.ILibroBo;
import cr.ac.una.icai.sipreli.clases.Libro;
import cr.ac.una.icai.sipreli.dao.interfaces.ILibroDao;
import java.util.List;

/**
 *
 * @author Enzo Quartino Zamora
 * <github.com/enzocr || email: enzoquartino@gmail.com>
 */
public class LibroBo implements ILibroBo {

    private ILibroDao libroDao;

    public LibroBo() {

    }

    @Override
    public Integer insert(Libro obj) {
        return libroDao.insert(obj);
    }

    @Override
    public boolean update(Libro obj) {
        return libroDao.update(obj);
    }

    @Override
    public List<Libro> getAll() {
        return libroDao.getAll();
    }

    @Override
    public Libro getByIsbn(Integer isbn) {
        return libroDao.getByIsbn(isbn);
    }

    @Override
    public List<Libro> getByName(String name) {
        return libroDao.getByName(name);
    }

    @Override
    public boolean remove(Libro obj) {
        return libroDao.remove(obj);
    }

    public ILibroDao getLibroDao() {
        return libroDao;
    }

    public void setLibroDao(ILibroDao libroDao) {
        this.libroDao = libroDao;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.icai.sipreli.bo.interfaces;

import cr.ac.una.icai.sipreli.clases.Libro;
import java.util.List;

/**
 *
 * @author Enzo Quartino Zamora
 * <github.com/enzocr || email: enzoquartino@gmail.com>
 */
public interface ILibroBo {

    Integer insert(Libro obj);

    boolean update(Libro obj);

    List<Libro> getAll();

    Libro getByIsbn(Integer isbn);

    List<Libro> getByName(String name);

    boolean remove(Libro obj);
}

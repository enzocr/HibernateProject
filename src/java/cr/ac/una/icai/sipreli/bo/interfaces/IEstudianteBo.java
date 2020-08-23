/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.icai.sipreli.bo.interfaces;

import cr.ac.una.icai.sipreli.clases.Estudiante;
import java.util.List;

/**
 *
 * @author Enzo Quartino Zamora
 * <github.com/enzocr || email: enzoquartino@gmail.com>
 */
public interface IEstudianteBo {

    Integer insert(Estudiante obj);

    boolean update(Estudiante obj);

    List<Estudiante> getAll();

    Estudiante getByCarnet(Integer id);

    List<Estudiante> getByName(String name);

    boolean remove(Estudiante obj);

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.icai.sipreli.bo;

import cr.ac.una.icai.sipreli.bo.interfaces.IEstudianteBo;
import cr.ac.una.icai.sipreli.clases.Estudiante;
import cr.ac.una.icai.sipreli.dao.interfaces.IEstudianteDao;
import java.util.List;

/**
 *
 * @author Enzo Quartino Zamora
 * <github.com/enzocr || email: enzoquartino@gmail.com>
 */
public class EstudianteBo implements IEstudianteBo {

    private IEstudianteDao estudianteDao;

    public EstudianteBo() {

    }

    @Override
    public Integer insert(Estudiante obj) {
        return estudianteDao.insert(obj);
    }

    @Override
    public boolean update(Estudiante obj) {
        return estudianteDao.update(obj);
    }

    @Override
    public List<Estudiante> getAll() {
        return estudianteDao.getAll();
    }

    @Override
    public Estudiante getByCarnet(Integer id) {
        return estudianteDao.getByCarnet(id);
    }

    @Override
    public List<Estudiante> getByName(String name) {
        return estudianteDao.getByName(name);
    }

    @Override
    public boolean remove(Estudiante obj) {
        return estudianteDao.remove(obj);
    }

    public IEstudianteDao getEstudianteDao() {
        return estudianteDao;
    }

    public void setEstudianteDao(IEstudianteDao estudianteDao) {
        this.estudianteDao = estudianteDao;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;
import javax.ejb.Local;
import pojos.Receta;

/**
 *
 * @author Sebastian
 */
@Local
public interface RecetaFacadeLocal {

    void create(Receta receta);

    void edit(Receta receta);

    void remove(Receta receta);

    Receta find(Object id);

    List<Receta> findAll();

    List<Receta> findRange(int[] range);

    int count();
    
}

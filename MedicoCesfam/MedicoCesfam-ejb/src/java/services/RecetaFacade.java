/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pojos.Paciente;
import pojos.Receta;

/**
 *
 * @author Sebastian
 */
@Stateless
public class RecetaFacade extends AbstractFacade<Receta> implements RecetaFacadeLocal {

    @PersistenceContext(unitName = "jndiCesfam")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RecetaFacade() {
        super(Receta.class);
    }
}

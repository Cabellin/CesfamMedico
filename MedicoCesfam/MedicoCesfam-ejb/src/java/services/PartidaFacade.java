/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pojos.Partida;

/**
 *
 * @author Sebastian
 */
@Stateless
public class PartidaFacade extends AbstractFacade<Partida> implements PartidaFacadeLocal {

    @PersistenceContext(unitName = "jndiCesfam")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PartidaFacade() {
        super(Partida.class);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import pojos.Paciente;
import services.PacienteFacadeLocal;

/**
 *
 * @author Sebastian
 */
@Named(value = "pacienteBean")
@ManagedBean
@SessionScoped
public class PacienteBean implements Serializable {

    @EJB
    private PacienteFacadeLocal pacienteFacade;
    
    private Paciente paciente;
    private List<Paciente> filtrados;
            
    public PacienteBean() {
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public List<Paciente> getPacientes() {
        return pacienteFacade.findAll();
    }

    public String obtenerSexo(){
        if(paciente.getSexo().equals('M')||paciente.getSexo().equals('m')){
            return "Masculino";
        }else{
            return "Femenino";
        }
    }
    
    public List<Paciente> getFiltrados() {
        return filtrados;
    }

    public void setFiltrados(List<Paciente> filtrados) {
        this.filtrados = filtrados;
    }
    
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import pojos.Receta;
import services.RecetaFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.persistence.PersistenceContext;
import org.primefaces.event.DragDropEvent;
import pojos.Medicamento;
import pojos.Paciente;
import pojos.RecetaMedicamento;
import pojos.RecetaMedicamentoPK;
import services.MedicamentoFacadeLocal;
import services.PacienteFacadeLocal;

/**
 *
 * @author Pelao
 */
@Named(value = "recetaBean")
@ManagedBean
@SessionScoped
public class RecetaBean implements Serializable {

    @EJB
    private PacienteFacadeLocal pacienteFacade;

    @EJB
    private MedicamentoFacadeLocal medicamentoFacade;

    @EJB
    private RecetaFacadeLocal recetaFacade;

    private Receta receta;
    private Paciente paciente;
    private Medicamento medicamento;
    private RecetaMedicamento recmed;
    private List<RecetaMedicamento> seleccionados;
    private List<Medicamento> medicamentosBd;

    public RecetaBean() {
        paciente = new Paciente();
        receta = new Receta();
        seleccionados = new ArrayList<RecetaMedicamento>();
        medicamentosBd = new ArrayList<Medicamento>();
    }

    public RecetaMedicamento getRecmed() {
        return recmed;
    }

    public void setRecmed(RecetaMedicamento recmed) {
        this.recmed = recmed;
    }

    public Medicamento getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Receta getReceta() {
        return receta;
    }

    public void setReceta(Receta receta) {
        this.receta = receta;
    }

    public List<RecetaMedicamento> getSeleccionados() {
        return seleccionados;
    }

    public void setSeleccionados(List<RecetaMedicamento> seleccionados) {
        this.seleccionados = seleccionados;
    }

    public List<Medicamento> getMedicamentosBd() {
        return medicamentosBd;
    }

    public void setMedicamentosBd(List<Medicamento> medicamentosBd) {
        this.medicamentosBd = medicamentosBd;
    }

    public List<String> getUnidadesP() {
        ArrayList<String> u = new ArrayList<String>();
        u.add("Minutos");
        u.add("Horas");
        u.add("Días");
        return u;
    }

    public List<String> getUnidadesE() {
        ArrayList<String> u = new ArrayList<String>();
        u.add("Días");
        u.add("Semanas");
        u.add("Meses");
        u.add("Años");
        return u;
    }

//    public List<Receta> getRecetasPaciente(){
//        List<Receta> recetas = recetaFacade.findByPaciente(paciente);
//        return recetas;
//    }
    
    public String agregarReceta(Paciente p) {
        paciente = p;
        return "receta?faces-redirect=true";
    }

    public String verRecetas(Paciente p) {
        paciente = p;
        return "controlPaciente?faces-redirect=true";
    }

    private BigInteger obtenerId() {
        BigInteger id;
        String idS;
        Date now = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMddHHmmss");
        idS = ft.format(now);
        id = BigInteger.valueOf(Long.parseLong(idS));
        return id;
    }

    public void onDrop(DragDropEvent ddEvent) {
        Medicamento m = ((Medicamento) ddEvent.getData());
        //receta.setId(BigDecimal.valueOf(obtenerId().longValue()));
        RecetaMedicamento rm = new RecetaMedicamento();
        rm.setMedicamento(m);

        seleccionados.add(rm);
        medicamentosBd.remove(m);
    }

    public void quitarMedicamento(RecetaMedicamento rm) {
        medicamentosBd.add(rm.getMedicamento());
        seleccionados.remove(rm);
    }

    public String crearReceta() {
        try {
            Receta r = new Receta();
            r.setFecha(new Date());
            r.setHora(new Date());
            r.setEstado("Pendiente");
            r.setPacienteRut(receta.getPacienteRut());
            r.setUsuarioNomUsu(receta.getUsuarioNomUsu());
            r.setRecetaMedicamentoList(seleccionados);
            this.recetaFacade.create(r);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Medicamento Agregado exitosamente!!!"));
            medicamento = new Medicamento();
            return "partida";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Debe editar los datos de los compuestos", ""));
            return "PasoDos";
        }
    }
}

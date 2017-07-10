/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigDecimal;
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
import pojos.Usuario;
import services.MedicamentoFacadeLocal;
import services.PacienteFacadeLocal;
import services.UsuarioFacadeLocal;

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

    @EJB
    private UsuarioFacadeLocal usuarioFacade;

    private Receta receta;
    private Paciente paciente;
    private Medicamento medicamento;
    private RecetaMedicamento recmed;
    private List<RecetaMedicamento> seleccionados;
    private List<Medicamento> medicamentosBd;
    private List<Medicamento> filtrado;

    public RecetaBean() {
        paciente = new Paciente();
        receta = new Receta();
        seleccionados = new ArrayList<RecetaMedicamento>();
        medicamentosBd = new ArrayList<Medicamento>();
    }

    public List<Medicamento> getFiltrado() {
        return filtrado;
    }

    public void setFiltrado(List<Medicamento> filtrado) {
        this.filtrado = filtrado;
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
        return medicamentoFacade.findAll();
    }

    public void setMedicamentosBd(List<Medicamento> medicamentosBd) {
        this.medicamentosBd = medicamentosBd;
    }

    public List<String> getUnidadesP() {
        ArrayList<String> u = new ArrayList<String>();
        u.add("Seleccione");
        u.add("Minutos");
        u.add("Horas");
        u.add("Días");
        return u;
    }

    public List<String> getUnidadesE() {
        ArrayList<String> u = new ArrayList<String>();
        u.add("Seleccione");
        u.add("Días");
        u.add("Semanas");
<<<<<<< HEAD
        u.add("Mes");
=======
        u.add("Meses");
>>>>>>> 309a4ee8f8a8c0ce013433bb81990f5a7d37ebf5
        return u;
    }

    public List<String> getUnidadesC() {
        ArrayList<String> u = new ArrayList<String>();
        u.add("Seleccione");
        u.add("mg");
        u.add("gr");
        u.add("ml");
        u.add("lt");
        u.add("ui");
        return u;
    }

//    public List<Receta> getRecetasPaciente(){
//        List<Receta> recetas = recetaFacade.findByPaciente(paciente);
//        return recetas;
//    }
    public String agregarReceta(Paciente p) {
        paciente = p;
        receta.setId(obtenerId());
        FacesContext context = FacesContext.getCurrentInstance();
        Usuario u = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
        receta.setUsuarioNomUsu(usuarioFacade.find(u.getNomUsu()));
        return "receta?faces-redirect=true";
    }

    public String cancelar() {
        Limpiar();
        return "index?faces-redirect=true";
    }

    public String verRecetas(Paciente p) {
        paciente = p;
        return "controlPaciente?faces-redirect=true";
    }

    private BigDecimal obtenerId() {
        BigDecimal id;
        String idS;
        Date now = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMddHHmmss");
        idS = ft.format(now);
        id = BigDecimal.valueOf(Long.parseLong(idS));
        return id;
    }

    public void onDrop(DragDropEvent ddEvent) {
        Medicamento m = ((Medicamento) ddEvent.getData());
        RecetaMedicamento rm = new RecetaMedicamento(new RecetaMedicamentoPK(m.getCodigo(), BigInteger.valueOf(receta.getId().longValue())), null, null, null, null, null, null, BigInteger.ZERO);
<<<<<<< HEAD
=======
        rm.setEstado("Pendiente");
>>>>>>> 309a4ee8f8a8c0ce013433bb81990f5a7d37ebf5
        rm.setMedicamento(m);
        rm.setReceta(receta);

        seleccionados.add(rm);
        medicamentosBd.remove(m);
    }

    public void quitarMedicamento(RecetaMedicamento rm) {
        medicamentosBd.add(rm.getMedicamento());
        seleccionados.remove(rm);
    }

    public void verificarUnidadP() throws Exception {
        for (RecetaMedicamento temp : seleccionados) {
            if (temp.getUnidadP() == null || temp.getUnidadP().equals("Seleccione") || temp.getUnidadP().equals("")) {
                throw new Exception("Verificar unidad de Periodicidad");
            }
        }
    }

    public void verificarUnidadC() throws Exception {
        for (RecetaMedicamento temp : seleccionados) {
            if (temp.getUnidadC() == null || temp.getUnidadC().equals("Seleccione") || temp.getUnidadC().equals("")) {
                throw new Exception("Verificar unidad de Cantidad");
            }
        }
    }

    public void verificarUnidadE() throws Exception {
        for (RecetaMedicamento temp : seleccionados) {
            if (temp.getUnidadE() == null || temp.getUnidadE().equals("Seleccione") || temp.getUnidadE().equals("")) {
                throw new Exception("Verificar unidad de Extensión");
            }
        }
    }

    private void validarUnidades() throws Exception {
        for (RecetaMedicamento temp : seleccionados) {
            if (temp.getUnidadP().equals("Días") && temp.getUnidadE().equals("Días")) {
                int com = temp.getUnidadP().compareTo(temp.getUnidadE());
                if (com == 1) {
                    throw new Exception("La periodicidad no puede ser mayor a la extensión");
                }
            }
        }
    }

    private void validarMes() throws Exception {
        for (RecetaMedicamento temp : seleccionados) {
            if (temp.getUnidadE().equals("Mes")) {
                if (temp.getExtension().intValue() > 1) {
                    throw new Exception("La extensión no puede ser mayor a un mes");
                }
            }
        }
    }

    private void validarSemanas() throws Exception {
        for (RecetaMedicamento temp : seleccionados) {
            if (temp.getUnidadE().equals("Semanas")) {
                if (temp.getExtension().intValue() > 5) {
                    throw new Exception("La extensión no puede ser mayor a un mes");
                }
            }
        }
    }

    private void validarDías() throws Exception {
        for (RecetaMedicamento temp : seleccionados) {
            if (temp.getUnidadE().equals("Días")) {
                if (temp.getExtension().intValue() > 31) {
                    throw new Exception("La extensión no puede ser mayor a un mes");
                }
            }
        }
    }

    public void verificarCantidad() throws Exception {
        for (RecetaMedicamento temp : seleccionados) {
            if (temp.getCantidad() == null || temp.getCantidad().intValue() == 0) {
                throw new Exception("Verificar cantidad");
            }
        }
    }

    public void verificarPeriodicidad() throws Exception {
        for (RecetaMedicamento temp : seleccionados) {
            if (temp.getPeriodicidad() == null || temp.getPeriodicidad().intValue() == 0) {
                throw new Exception("Verificar periodicidad");
            }
        }
    }

    public void verificarExtension() throws Exception {
        for (RecetaMedicamento temp : seleccionados) {
            if (temp.getExtension() == null || temp.getExtension().intValue() == 0) {
                throw new Exception("Verificar extesión");
            }
        }
    }

    public void verificarMedicamento() throws Exception {
        if (seleccionados.isEmpty()) {
            throw new Exception("Debe seleccionar medicamento");
        }
    }

    public void cantidadTotal() {
        for (RecetaMedicamento temp : seleccionados) {
            switch (temp.getUnidadP()) {
                case "Minutos":
                    if (temp.getUnidadE().equals("Días")) {
                        temp.setCantTotal(temp.getExtension().multiply(BigInteger.valueOf(1440)));
                        temp.setCantTotal(temp.getCantTotal().divide(temp.getPeriodicidad()));
                    } else if (temp.getUnidadE().equals("Semanas")) {
                        temp.setCantTotal(temp.getExtension().multiply(BigInteger.valueOf(10080)));
                        temp.setCantTotal(temp.getCantTotal().divide(temp.getPeriodicidad()));
                    } else if (temp.getUnidadE().equals("Mes")) {
                        temp.setCantTotal(temp.getExtension().multiply(BigInteger.valueOf(43200)));
                        temp.setCantTotal(temp.getCantTotal().divide(temp.getPeriodicidad()));
                    }
                    break;
                case "Horas":
                    if (temp.getUnidadE().equals("Días")) {
                        temp.setCantTotal(temp.getExtension().multiply(BigInteger.valueOf(24)));
                        temp.setCantTotal(temp.getCantTotal().divide(temp.getPeriodicidad()));
                    } else if (temp.getUnidadE().equals("Semanas")) {
                        temp.setCantTotal(temp.getExtension().multiply(BigInteger.valueOf(168)));
                        temp.setCantTotal(temp.getCantTotal().divide(temp.getPeriodicidad()));
                    } else if (temp.getUnidadE().equals("Mes")) {
                        temp.setCantTotal(temp.getExtension().multiply(BigInteger.valueOf(720)));
                        temp.setCantTotal(temp.getCantTotal().divide(temp.getPeriodicidad()));
                    }
                    break;
                case "Días":
                    if (temp.getUnidadE().equals("Días")) {
                        temp.setCantTotal(temp.getExtension().multiply(BigInteger.valueOf(1)));
                        temp.setCantTotal(temp.getCantTotal().divide(temp.getPeriodicidad()));
                    } else if (temp.getUnidadE().equals("Semanas")) {
                        temp.setCantTotal(temp.getExtension().multiply(BigInteger.valueOf(7)));
                        temp.setCantTotal(temp.getCantTotal().divide(temp.getPeriodicidad()));
                    } else if (temp.getUnidadE().equals("Mes")) {
                        temp.setCantTotal(temp.getExtension().multiply(BigInteger.valueOf(30)));
                        temp.setCantTotal(temp.getCantTotal().divide(temp.getPeriodicidad()));
                    }
                    break;
                default:
                    break;
            }
        }
    }

    public String crearReceta() {
        try {
            verificarMedicamento();
            verificarCantidad();
            verificarUnidadC();
            verificarPeriodicidad();
            verificarUnidadP();
            verificarExtension();
            verificarUnidadE();
            validarUnidades();
            validarMes();
            validarSemanas();
            validarDías();
            Receta r = new Receta();
            r.setId(BigDecimal.valueOf(receta.getId().longValue()));
            r.setFecha(new Date());
            r.setHora(new Date());
            r.setEstado("Pendiente");
            cantidadTotal();
            r.setPacienteRut(pacienteFacade.find(paciente.getRut()));
            r.setUsuarioNomUsu(receta.getUsuarioNomUsu());
            r.setRecetaMedicamentoList(seleccionados);
            this.recetaFacade.create(r);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Receta Agregada exitosamente!!!"));
            Limpiar();
            return "index";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error: " + e.getMessage(), ""));
            return "receta";
        }
    }

    private void Limpiar() {
        medicamento = new Medicamento();
        seleccionados = new ArrayList<>();
        medicamentosBd = medicamentoFacade.findAll();
    }

    boolean color = false;

    public String Color(Medicamento m) {
        Medicamento med = medicamentoFacade.find(m.getCodigo());
        if (med.getStockDisponible() == BigInteger.ZERO) {
            color = true;
        } else {
            color = false;
        }
        if (color) {
            return "#FF0000";
        } else {
            return "#056A28";
        }
    }
}

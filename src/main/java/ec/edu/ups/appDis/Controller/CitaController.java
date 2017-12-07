package ec.edu.ups.appDis.Controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Init;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import ec.edu.ups.appDis.DAO.CitaDAO;
import ec.edu.ups.appDis.DAO.MedicoDAO;
import ec.edu.ups.appDis.DAO.PacienteDAO;
import ec.edu.ups.appDis.Modelo.Cita;
import ec.edu.ups.appDis.Modelo.Medico;
import ec.edu.ups.appDis.Modelo.Paciente;

@ManagedBean(name="citabean")
@SessionScoped
public class CitaController implements Serializable {
	private static final long serialVersionUID = 1L;

	private Cita newCita;
	private Paciente newPaciente;
	private Medico newMedico;
	private List<Cita> listaCita = new ArrayList<>();

	@Inject
	private CitaDAO citDao;
	@Inject
	private MedicoDAO medDao;
	@Inject
	private PacienteDAO pacDao;
	
	private Integer idPaciente;
	private Integer idMedico;
	
	public CitaController() {
		newCita = new Cita();
		newMedico = new Medico();
		newPaciente = new Paciente();
		listaCita = new ArrayList<>();
	}
	@Init
	public void iniciar() {
		
		newCita = new Cita();
		newMedico = new Medico();
		newPaciente = new Paciente();
		listaCita = new ArrayList<>();
	}
	
	public Cita getNewCita() {
		return newCita;
	}
	public void setNewCita(Cita newCita) {
		this.newCita = newCita;
	}
	public Paciente getNewPaciente() {
		return newPaciente;
	}
	public void setNewPaciente(Paciente newPaciente) {
		this.newPaciente = newPaciente;
	}
	public Medico getNewMedico() {
		return newMedico;
	}
	public void setNewMedico(Medico newMedico) {
		this.newMedico = newMedico;
	}

	public CitaDAO getCitDao() {
		return citDao;
	}
	public void setCitDao(CitaDAO citDao) {
		this.citDao = citDao;
	}
	public MedicoDAO getMedDao() {
		return medDao;
	}
	public void setMedDao(MedicoDAO medDao) {
		this.medDao = medDao;
	}
	public PacienteDAO getPacDao() {
		return pacDao;
	}
	public void setPacDao(PacienteDAO pacDao) {
		this.pacDao = pacDao;
	}
	
	public Integer getIdPaciente() {
		return idPaciente;
	}
	public void setIdPaciente(Integer idPaciente) {
		this.idPaciente = idPaciente;
	}
	public Integer getIdMedico() {
		return idMedico;
	}
	public void setIdMedico(Integer idMedico) {
		this.idMedico = idMedico;
	}
	/**
	 * Nos pemite insertar y guardar la cita
	 * @return
	 */
	public String guardarCita() {
	
		newCita.setMedicomedid(medDao.leerMedico(idMedico));
		newCita.setPacientepacid(pacDao.leerPaciente(idPaciente));
		newCita.setCitEstado(true);
		citDao.guardarCitas(newCita);
		
		return "listadoCitas";
	}
	
	public String nuevaCita() {
		iniciar();
		return "crearCita";
	}
	
	/**
	 * Nos permite listar las citas guardadas
	 * @return
	 */
	public List<Cita> getListaCita() {
		listaCita = citDao.listacitas();
		return listaCita;
	}
	public void setListaCita(List<Cita> listaCita) {
		this.listaCita = listaCita;
	}
	
	/**
	 * Nos permite actualizar los parametros de la Cita
	 * @param cita
	 * @return
	 */
	public String actualizarCita(Cita cita){
		newCita = cita;
		newMedico = cita.getMedicomedid();
		newPaciente = cita.getPacientepacid();
		return "actualizarCita";
	}
	
	/**
	 * Guarda la actualizacion de la Cita
	 * @return
	 */
	public String guardaraActualizacionCita() {
		newCita.setMedicomedid(medDao.leerMedico(idMedico));
		newCita.setPacientepacid(pacDao.leerPaciente(idPaciente));
		citDao.actualizarCita(newCita);
		return "listadoCitas";
	}
	
	/**
	 * Nos permite cancelar la Cita
	 * @param cita
	 */
	public void cancelarCita(Cita cita) {

		cita.setCitEstado(false);
		citDao.actualizarCita(cita);
	}
	
	/**
	 * Nos permite activar la cita si esta desactivada
	 * @param cita
	 */
	public void activarCita(Cita cita) {

		cita.setCitEstado(true);
		citDao.actualizarCita(cita);
	}
	/**
	 * Nos permite eliminar la cita
	 * @param cita
	 */
	public void eliminarCita(Cita cita){
		citDao.eliminarCita(cita.getCitId());
	}
	public String verlistaCitas() {
		return "listadoCitas";
	}
	
}

package ec.edu.ups.appDis.Controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Init;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import ec.edu.ups.appDis.DAO.PacienteDAO;
import ec.edu.ups.appDis.DAO.UsuarioDAO;
import ec.edu.ups.appDis.Modelo.Medico;
import ec.edu.ups.appDis.Modelo.Paciente;
import ec.edu.ups.appDis.Modelo.Usuario;

@ManagedBean(name="pacientebean")
@SessionScoped
public class PacienteController implements Serializable {
	private static final long serialVersionUID = 1L;

	private Usuario newUsuario;
	private Paciente newPaciente = new Paciente();
	private List<Paciente> listaPaciente = new ArrayList<>();

	@Inject
	private PacienteDAO pacDao;
	@Inject
	private UsuarioDAO usrDao;
	

	public PacienteController() {
		newUsuario = new Usuario();
		newPaciente = new Paciente();
		listaPaciente = new ArrayList<>();
	}
	@Init
	public void iniciar() {
		
		newUsuario = new Usuario();
		newPaciente = new Paciente();
		listaPaciente = new ArrayList<>();
	}


	public Usuario getNewUsuario() {
		return newUsuario;
	}
	public void setNewUsuario(Usuario newUsuario) {
		this.newUsuario = newUsuario;
	}
	public Paciente getNewPaciente() {
		return newPaciente;
	}
	public void setNewPaciente(Paciente newPaciente) {
		this.newPaciente = newPaciente;
	}
	public List<Paciente> getListaPaciente() {
		listaPaciente = pacDao.listadopacientes();
		return listaPaciente;
	}
	public void setListaPaciente(List<Paciente> listaPaciente) {
		this.listaPaciente = listaPaciente;
	}
	public PacienteDAO getPacDao() {
		return pacDao;
	}
	public void setPacDao(PacienteDAO pacDao) {
		this.pacDao = pacDao;
	}
	public UsuarioDAO getUsrDao() {
		return usrDao;
	}
	public void setUsrDao(UsuarioDAO usrDao) {
		this.usrDao = usrDao;
	}
	/**
	 * Nos permite guardar e insertar el paciente
	 * @return
	 */
	public String guardarPaciente() {
	System.out.println(newUsuario.toString());
		usrDao.insertarUsuario(newUsuario);
		newPaciente.setUsuariousid(newUsuario);
		pacDao.guardarPaciente(newPaciente);
		
		return "listadoPacientes";
	}
	
	public String nuevoPaciente() {
		iniciar();
		return "crearPaciente";
	}
	
	public void eliminarPaciente(Paciente paciente){
		pacDao.eliminarPaciente(paciente.getPacId());
	}
	public String verlistaPaciente() {
		return "listadoPacientes";
	}
	public String actualizarPaciente(Paciente paciente){
		newPaciente = paciente;
		newUsuario = paciente.getUsuario();
		return "actualizarPaciente";
	}
	public String guardaraActualizacionPaciente() {
		pacDao.actualizarPaciente(newPaciente);
		usrDao.actualizarUsuario(newUsuario);
		return "listadoPacientes";
	}
}

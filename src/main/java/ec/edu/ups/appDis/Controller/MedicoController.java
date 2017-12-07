package ec.edu.ups.appDis.Controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Init;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import ec.edu.ups.appDis.DAO.EspecialidadDAO;
import ec.edu.ups.appDis.DAO.MedicoDAO;
import ec.edu.ups.appDis.DAO.UsuarioDAO;
import ec.edu.ups.appDis.Modelo.Especialidad;
import ec.edu.ups.appDis.Modelo.Medico;
import ec.edu.ups.appDis.Modelo.Usuario;

@ManagedBean(name="medicobean")
@SessionScoped
public class MedicoController implements Serializable {
	private static final long serialVersionUID = 1L;

	private Especialidad newEspecialidad;
	private Usuario newUsuario;
	private Medico newMedico;
	private List<Medico> listaMedico = new ArrayList<>();

	@Inject
	private EspecialidadDAO espDao;
	@Inject
	private MedicoDAO medDao;
	@Inject
	private UsuarioDAO usrDao;
	
	private Integer idEspecialidad;

	public MedicoController() {
		newUsuario = new Usuario();
		newMedico = new Medico();
		listaMedico = new ArrayList<>();
	}
	@Init
	public void iniciar() {
		
		newUsuario = new Usuario();
		newMedico = new Medico();
		listaMedico = new ArrayList<>();
	}

	public Especialidad getNewEspecialidad() {
		return newEspecialidad;
	}
	public void setNewEspecialidad(Especialidad newEspecialidad) {
		this.newEspecialidad = newEspecialidad;
	}
	public Usuario getNewUsuario() {
		return newUsuario;
	}
	public void setNewUsuario(Usuario newUsuario) {
		this.newUsuario = newUsuario;
	}
	public Medico getNewMedico() {
		return newMedico;
	}
	public void setNewMedico(Medico newMedico) {
		this.newMedico = newMedico;
	}
	public List<Medico> getListaMedico() {
		listaMedico = medDao.listadomedicos();
		return listaMedico;
	}
	public void setListaMedico(List<Medico> listaMedico) {
		this.listaMedico = listaMedico;
	}
	public EspecialidadDAO getEspDao() {
		return espDao;
	}
	public void setEspDao(EspecialidadDAO espDao) {
		this.espDao = espDao;
	}
	public MedicoDAO getMedDao() {
		return medDao;
	}
	public void setMedDao(MedicoDAO medDao) {
		this.medDao = medDao;
	}
	public UsuarioDAO getUsrDao() {
		return usrDao;
	}
	public void setUsrDao(UsuarioDAO usrDao) {
		this.usrDao = usrDao;
	}
	public String guardarMedico() {
	System.out.println(newUsuario.toString());
		usrDao.insertarUsuario(newUsuario);
		newMedico.setUsuariousid(newUsuario);
		newMedico.setEspecialidad(espDao.leerEspecialidad(idEspecialidad));
		medDao.guardarMedico(newMedico);
		
		return "listadoMedicos";
	}
	
	public String nuevoMedico() {
		iniciar();
		return "crearMedico";
	}
	
	public String actualizarMedico(Medico medico){
		newMedico = medico;
		newUsuario = medico.getUsuario();
		return "actualizarMedico";
	}
	
	public String guardaraActualizacionMedico() {
		newMedico.setEspecialidad(espDao.leerEspecialidad(idEspecialidad));
		medDao.actualizarMedico(newMedico);
		usrDao.actualizarUsuario(newUsuario);
		return "listadoMedicos";
	}
	
	public void eliminarMedico(Medico medico){
		medDao.eliminarMedico(medico.getMedId());
	   //usrDao.eliminarUsuario(idUser);
	}
	public String verlistaMedico() {
		return "listadoMedicos";
	}
	public Integer getIdEspecialidad() {
		return idEspecialidad;
	}
	public void setIdEspecialidad(Integer idEspecialidad) {
		this.idEspecialidad = idEspecialidad;
	}
	
	
}

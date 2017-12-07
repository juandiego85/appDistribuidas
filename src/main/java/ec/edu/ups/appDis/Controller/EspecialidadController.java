package ec.edu.ups.appDis.Controller;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Init;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import ec.edu.ups.appDis.DAO.EspecialidadDAO;
import ec.edu.ups.appDis.DAO.UsuarioDAO;
import ec.edu.ups.appDis.Modelo.Especialidad;
import ec.edu.ups.appDis.Modelo.Usuario;

@ManagedBean(name="especialidadbean")
@SessionScoped
public class EspecialidadController  implements Serializable {
	private static final long serialVersionUID = 1L;

	private Especialidad newEspecialidad = new Especialidad();
	private List<Especialidad> listaEspecialidades = new ArrayList<>();
	
	@Inject
	private EspecialidadDAO espDao;
	
	public EspecialidadController() {
		
	}
	
	@Init
	public void iniciar() {
		newEspecialidad = new Especialidad();
		listaEspecialidades = new ArrayList<>();
	}

	public Especialidad getNewEspecialidad() {
		return newEspecialidad;
	}

	public void setNewEspecialidad(Especialidad newEspecialidad) {
		this.newEspecialidad = newEspecialidad;
	}

	public EspecialidadDAO getEspDao() {
		return espDao;
	}

	public void setEspDao(EspecialidadDAO espDao) {
		this.espDao = espDao;
	}
	
	public String guardarEspecialidad() {
		boolean res;
		res= espDao.insertarEspecialidad(newEspecialidad);
		return "listadoEspecialidades";
	}
	
	public String nuevaEspecialidad() {
		iniciar();
		return "crearEspecialidad";
	}
	
	public String actualizarEspecialidad(Especialidad especialidad){
		newEspecialidad = especialidad;
		return "actualizarEspecialidad";
	}
	
	public String guardaraActualizacionEspecialidad() {
		espDao.actualizarEspecialidad(newEspecialidad);
		return "listadoEspecialidades";
	}
	
	public void eliminarEspecialidad(Especialidad especialidad){
		espDao.eliminarEspecialidad(especialidad.getEspId());
	}
	
	public List<Especialidad> getListaEspecialidades() {
		listaEspecialidades = espDao.listadoespecialidades();
		return listaEspecialidades;
	}

	public void setListaEspecialidades(List<Especialidad> listaEspecialidades) {
		this.listaEspecialidades = listaEspecialidades;
	}

	public String verlistaEspecialidades() {
		return "listadoEspecialidades";
	}


}

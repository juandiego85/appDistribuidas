package ec.edu.ups.appDis.DAO;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.appDis.Modelo.Especialidad;


@Stateful
public class EspecialidadDAO implements Serializable{

	private static final long serialVersionUID = 1L;
	@Inject
	private Logger log;
	@Inject 
	private EntityManager em;
	
	
	public boolean insertarEspecialidad(Especialidad especialidad) {
		try {
			System.out.println("Insertando especialidad");
			em.persist(especialidad);
			return true;
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERROR al Insertar /////////////// especialidad");
			e.printStackTrace();
			return false;
		}
		
	}
	
	public void actualizarEspecialidad (Especialidad especialidad) {
			
		em.merge(especialidad);
	}
	
	public void eliminarEspecialidad(int espId) {
		
		Especialidad especialidad= em.find(Especialidad.class, espId);
		em.remove(especialidad);
	}
	
	public Especialidad leerEspecialidad(int espId) {
		return em.find(Especialidad.class, espId);
	}
	
	public List<Especialidad> listadoespecialidades(){
		String sql = "SELECT esp FROM Especialidad esp ";
		Query query = em.createQuery(sql, Especialidad.class);
		List<Especialidad> listadoespecialidades = query.getResultList();
		
		return listadoespecialidades;
	}

	public void guardarEspecialidades(Especialidad especialidad) {
		Especialidad esp = leerEspecialidad(especialidad.getEspId());
		if( esp == null)
			insertarEspecialidad(especialidad);
		else
			actualizarEspecialidad(especialidad);
	}	
}

package ec.edu.ups.appDis.DAO;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.appDis.Modelo.Paciente;
import ec.edu.ups.appDis.Modelo.Usuario;

@Stateful
public class PacienteDAO implements Serializable{

	private static final long serialVersionUID = 1L;
	@Inject
	private Logger log;
	@Inject 
	private EntityManager em;

	public boolean insertarPaciente(Paciente paciente) {
		try {
			System.out.println("Insertando al paciente");
			em.persist(paciente);
			return true;
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERROR al Insertar /////////////// paciente");
			e.printStackTrace();
			return false;
		}
	}
	
	public void actualizarPaciente (Paciente paciente) {
			em.merge(paciente);
	}
	
	public void eliminarPaciente(int pacId) {
			Paciente paciente = em.find(Paciente.class, pacId);
			em.remove(paciente);
	}
	
	public Paciente leerPaciente(int pacId) {
		return em.find(Paciente.class, pacId);
	}
	
	public void actualizar2Paciente(Usuario usuario, Paciente paciente) {
		em.merge(paciente);
		em.merge(usuario);
	}
	
	public List<Paciente> listadopacientes(){
		String sql = "SELECT paci FROM Paciente paci ";
		Query query = em.createQuery(sql, Paciente.class);
		List<Paciente> listadopacientes = query.getResultList();
		
		return listadopacientes;
	}

	public void guardarPaciente(Paciente paciente) {
		
		if( paciente.getPacId() == null)
			insertarPaciente(paciente);
		else
			actualizarPaciente(paciente);
	}

}

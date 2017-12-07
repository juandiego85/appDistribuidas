package ec.edu.ups.appDis.DAO;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.appDis.Modelo.Medico;
import ec.edu.ups.appDis.Modelo.Usuario;


@Stateful
public class MedicoDAO implements Serializable{

	private static final long serialVersionUID = 1L;
	@Inject
	private Logger log;
	@Inject 
	private EntityManager em;
	
	public boolean insertarMedico(Medico medico) {
		try {
			System.out.println("Insertando al Medico");
			em.persist(medico);
			return true;
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERROR al Insertar /////////////// medico");
			e.printStackTrace();
			return false;
		}
		
	}
	
	public void actualizarMedico (Medico medico) {
		//System.out.println("Actualizando> "+ medico.toString());
			em.merge(medico);
	}
	
	public void eliminarMedico(int medId) {
			em.remove(em.find(Medico.class, medId));
			em.flush();
	}
	public Medico leerMedico(int medId) {
		return em.find(Medico.class, medId);
	}
	
	public void actualizar2Medico(Usuario usuario, Medico medico) {
		em.merge(medico);
		em.merge(usuario);
	}
	
	public List<Medico> listadomedicos(){
		String sql = "SELECT me FROM Medico me ";
		Query query = em.createQuery(sql, Medico.class);
		List<Medico> listadomedicos = query.getResultList();
		System.out.println("Tenemos" +listadomedicos.size());
		return listadomedicos;
	}

	public void guardarMedico(Medico medico) {
		
		if( medico.getMedId() == null)
			insertarMedico(medico);
		else
			actualizarMedico(medico);
	}
}

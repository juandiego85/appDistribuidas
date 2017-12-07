package ec.edu.ups.appDis.DAO;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.appDis.Modelo.Cita;

@Stateful
public class CitaDAO implements Serializable{

	private static final long serialVersionUID = 1L;
	@Inject
	private Logger log;
	@Inject 
	private EntityManager em;
	
	public boolean insertarCita(Cita cita) {
		try {
			System.out.println("Insertando al Cita");
			em.persist(cita);
			return true;
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERROR al Insertar /////////////// Cita");
			e.printStackTrace();
			return false;
		}
		
	}
	
	public void actualizarCita (Cita cita) {
			em.merge(cita);
	}
	
	public void eliminarCita(int citId) {
			Cita cita = em.find(Cita.class, citId);
			em.remove(cita);
	}
	
	public Cita leerCita(int citId) {
		return em.find(Cita.class, citId);
	}
	
	public List<Cita> listacitas(){
		String sql = "SELECT ci FROM Cita ci ";
		Query query = em.createQuery(sql, Cita.class);
		List<Cita> listadocitas = query.getResultList();
		
		return listadocitas;
	}

	public void guardarCitas(Cita cita) {
		
		if( cita.getCitId() == null)
			insertarCita(cita);
		else
			actualizarCita(cita);
	}
}


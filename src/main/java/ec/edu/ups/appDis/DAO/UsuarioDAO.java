package ec.edu.ups.appDis.DAO;

import java.util.List;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.appDis.Modelo.Medico;
import ec.edu.ups.appDis.Modelo.Usuario;

@Stateful
public class UsuarioDAO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Inject
	private Logger log;
	@Inject 
	private EntityManager em;
	
	public boolean insertarUsuario(Usuario usuario) {
		try {
			System.out.println("Insertando a la usuario");
			em.persist(usuario);
			return true;
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERROR al Insertar /////////////// usuario");
			e.printStackTrace();
			return false;
		}
		
	}
	
	public void actualizarUsuario (Usuario usuario) {
			em.merge(usuario);
	}
	
	public void eliminarUsuario(int usID) {
			Usuario usuario = em.find(Usuario.class, usID);
			em.remove(usuario);
	}
	
	public Usuario leerUsuario(int usID) {
		return em.find(Usuario.class, usID);
	}
	
	public void actualizar2Usuario(Usuario usuario, Medico medico) {
		em.merge(medico);
		em.merge(usuario);
	}
	
	public List<Usuario> listadoUsuarios(){
		String sql = "SELECT u FROM Usuario u ";
		Query query = em.createQuery(sql, Usuario.class);
		List<Usuario> listadousuarios = query.getResultList();
		
		return listadousuarios;
	}

	public void guardarUsuario(Usuario usuario) {
	
		if( usuario.getUsId() == null)
			insertarUsuario(usuario);
		else
			actualizarUsuario(usuario);
	}

	
}

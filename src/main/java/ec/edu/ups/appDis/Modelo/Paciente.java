package ec.edu.ups.appDis.Modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

@Entity
public class Paciente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "pac_id")
    private Integer pacId;
   
 
    @ManyToOne
    @JoinColumn(name = "Usuario_us_id", referencedColumnName = "us_id")
    private Usuario usuario;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pacientepacid")
    private List<Cita> citaList;

    public Paciente() {
    }

	public Integer getPacId() {
		return pacId;
	}

	public void setPacId(Integer pacId) {
		this.pacId = pacId;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuariousid(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Cita> getCitaList() {
		return citaList;
	}

	public void setCitaList(List<Cita> citaList) {
		this.citaList = citaList;
	}

	@Override
	public String toString() {
		return "Paciente [pacId=" + pacId + ", usuario=" + usuario + ", citaList=" + citaList + "]";
	}


    
}

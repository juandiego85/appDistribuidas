package ec.edu.ups.appDis.Modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

@Entity
public class Medico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "med_id")
    private Integer medId;
    
    @JoinColumn(name = "Especialidad_esp_id")
    @ManyToOne(fetch=FetchType.EAGER)
    private Especialidad especialidad;
    
    @JoinColumn(name = "Usuario_us_id")
    @ManyToOne
    private Usuario usuario;
    
    @OneToMany( mappedBy = "medicomedid",orphanRemoval=true)
    private List<Cita> citaList;

    public Medico() {
    }

	public Integer getMedId() {
		return medId;
	}

	public void setMedId(Integer medId) {
		this.medId = medId;
	}

	public Especialidad getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
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
		return "Medico [medId=" + medId + ", especialidad=" + especialidad + ", usuario=" + usuario + ", citaList="
				+ citaList + "]";
	}

}

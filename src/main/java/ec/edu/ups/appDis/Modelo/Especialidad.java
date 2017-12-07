package ec.edu.ups.appDis.Modelo;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.List;

@Entity
public class Especialidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "esp_id")
    private Integer espId;
    
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "esp_nombreEspecialidad")
    private String espnombreEspecialidad;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "especialidad", fetch=FetchType.EAGER)
    private List<Medico> medicoList;

    public Especialidad() {
    }

	public Integer getEspId() {
		return espId;
	}

	public void setEspId(Integer espId) {
		this.espId = espId;
	}

	public String getEspnombreEspecialidad() {
		return espnombreEspecialidad;
	}

	public void setEspnombreEspecialidad(String espnombreEspecialidad) {
		this.espnombreEspecialidad = espnombreEspecialidad;
	}

	public List<Medico> getMedicoList() {
		return medicoList;
	}

	public void setMedicoList(List<Medico> medicoList) {
		this.medicoList = medicoList;
	}

	@Override
	public String toString() {
		return "Especialidad [espId=" + espId + ", espnombreEspecialidad=" + espnombreEspecialidad + ", medicoList="
				+ medicoList + "]";
	}


    
}

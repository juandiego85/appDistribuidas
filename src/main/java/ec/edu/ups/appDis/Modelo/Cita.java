package ec.edu.ups.appDis.Modelo;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.Date;

@Entity
public class Cita implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "cit_id")
    private Integer citId;
    
    @NotNull
    @Column(name = "cit_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date citFecha;
 
    @NotNull
    @Column(name = "cit_estado")
    private boolean citEstado;
    
    @JoinColumn(name = "Medico_med_id", referencedColumnName = "med_id")
    @ManyToOne
    private Medico medicomedid;
    
    @JoinColumn(name = "Paciente_pac_id", referencedColumnName = "pac_id")
    @ManyToOne
    private Paciente pacientepacid;

    public Cita() {
    }

	public Integer getCitId() {
		return citId;
	}

	public void setCitId(Integer citId) {
		this.citId = citId;
	}

	public Date getCitFecha() {
		return citFecha;
	}

	public void setCitFecha(Date citFecha) {
		this.citFecha = citFecha;
	}


	public boolean isCitEstado() {
		return citEstado;
	}

	public void setCitEstado(boolean citEstado) {
		this.citEstado = citEstado;
	}

	public Medico getMedicomedid() {
		return medicomedid;
	}

	public void setMedicomedid(Medico medicomedid) {
		this.medicomedid = medicomedid;
	}

	public Paciente getPacientepacid() {
		return pacientepacid;
	}

	public void setPacientepacid(Paciente pacientepacid) {
		this.pacientepacid = pacientepacid;
	}

	@Override
	public String toString() {
		return "Cita [citId=" + citId + ", citFecha=" + citFecha + ", citEstado=" + citEstado + ", medicomedid="
				+ medicomedid + ", pacientepacid=" + pacientepacid + "]";
	}


    
}

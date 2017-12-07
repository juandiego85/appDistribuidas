package ec.edu.ups.appDis.Modelo;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.ext.ParamConverter.Lazy;

import org.hibernate.validator.constraints.Email;

import java.util.ArrayList;
import java.util.List;


@Entity
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
   
    @Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "us_id")
    private Integer usId;
   
    @NotNull
    @Size(min = 10, max = 10)
    @Column(name = "us_cedula")
    private String usCedula;

    @NotNull
    @Size(min = 3, max = 20)
    @Column(name = "us_nombre")
    private String usNombre;

    @NotNull
    @Size(min = 3, max = 20)
    @Column(name = "us_apellido")
    private String usApellido;

    @NotNull
    @Size(min = 7, max = 10)
    @Digits(fraction=0, integer=12)
    @Column(name = "us_telefono")
    private String usTelefono;

    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "us_correo")
    @Email
    private String usCorreo;

    @NotNull
    @Size(min = 6, max = 15)
    @Column(name = "us_clave")
    private String usClave;

  //bi-directional many-to-one association to paciente
    @OneToMany(mappedBy = "usuario")
    private List<Paciente> paciente ;
    
  //bi-directional many-to-one association to medico
    @OneToMany( mappedBy = "usuario")
    private List<Medico> medico ;
    
    
    
    public Usuario() {
    }

    
    
	public List<Paciente> getPaciente() {
		return paciente;
	}



	public void setPaciente(List<Paciente> paciente) {
		this.paciente = paciente;
	}



	public List<Medico> getMedico() {
		return medico;
	}



	public void setMedico(List<Medico> medico) {
		this.medico = medico;
	}



	public Integer getUsId() {
		return usId;
	}

	public void setUsId(Integer usId) {
		this.usId = usId;
	}

	public String getUsCedula() {
		return usCedula;
	}

	public void setUsCedula(String usCedula) {
		this.usCedula = usCedula;
	}

	public String getUsNombre() {
		return usNombre;
	}

	public void setUsNombre(String usNombre) {
		this.usNombre = usNombre;
	}

	public String getUsApellido() {
		return usApellido;
	}

	public void setUsApellido(String usApellido) {
		this.usApellido = usApellido;
	}

	public String getUsTelefono() {
		return usTelefono;
	}

	public void setUsTelefono(String usTelefono) {
		this.usTelefono = usTelefono;
	}

	public String getUsCorreo() {
		return usCorreo;
	}

	public void setUsCorreo(String usCorreo) {
		this.usCorreo = usCorreo;
	}

	public String getUsClave() {
		return usClave;
	}

	public void setUsClave(String usClave) {
		this.usClave = usClave;
	}

	@Override
	public String toString() {
		return "Usuario [usId=" + usId + ", usCedula=" + usCedula + ", usNombre=" + usNombre + ", usApellido="
				+ usApellido + ", usTelefono=" + usTelefono + ", usCorreo=" + usCorreo + ", usClave=" + usClave
				+ ", paciente=" + paciente + ", medico=" + medico + "]";
	}


}

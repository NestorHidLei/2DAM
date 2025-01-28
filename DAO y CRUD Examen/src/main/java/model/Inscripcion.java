package model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "inscripciones")
public class Inscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inscripcion")
    private int idInscripcion;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_curso", nullable = false)
    private Curso curso;

    @Column(name = "fecha_inscripcion")
    private Date fechaInscripcion;
    
    public Inscripcion(Usuario usuario, Curso curso, Date fechaInscripcion) {
		super();
		this.usuario = usuario;
		this.curso = curso;
		this.fechaInscripcion = fechaInscripcion;
	}

	// Getters and Setters
    public int getIdInscripcion() {
        return idInscripcion;
    }

    public void setIdInscripcion(int idInscripcion) {
        this.idInscripcion = idInscripcion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Date getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

	@Override
	public String toString() {
		return "Inscripcion [idInscripcion=" + idInscripcion + ", usuario=" + usuario + ", curso=" + curso
				+ ", fechaInscripcion=" + fechaInscripcion + "]";
	}
    
    
}

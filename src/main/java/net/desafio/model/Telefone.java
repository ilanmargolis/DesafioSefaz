package net.desafio.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Telefone {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int ddd;
	private String numero;
	private String tipo;

	@ManyToOne
	@JoinColumn(name="usuario_id")
	private Usuario usuario;
	
	// Construtores
	public Telefone() {
	}

	public Telefone(int id, int ddd, String numero, String tipo, Usuario usuario) {
		super();
		this.id = id;
		this.ddd = ddd;
		this.numero = numero;
		this.tipo = tipo;
		this.usuario = usuario;
	}

	// Métodos get e set	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDdd() {
		return ddd;
	}

	public void setDdd(int ddd) {
		this.ddd = ddd;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Telefone [id=" + id + ", ddd=" + ddd + ", numero=" + numero + ", tipo=" + tipo + ", usuario=" + usuario
				+ "]";
	}
}

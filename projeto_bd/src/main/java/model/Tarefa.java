package model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name = "TB_TAREFAS")
@Entity
public class Tarefa {
	
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	
	@Column
	private String titulo;
	@Column
	private String descricao;
	@Column
	private LocalDate prazo;
	@Column
	private boolean concluida;
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario owner;
	@ManyToOne
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;
	
	public Tarefa() {
		
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public LocalDate getPrazo() {
		return prazo;
	}
	public void setPrazo(LocalDate prazo) {
		this.prazo = prazo;
	}
	public boolean isConcluida() {
		return concluida;
	}
	public void setConcluida(boolean concluida) {
		this.concluida = concluida;
	}
	public Usuario getOwner() {
		return owner;
	}
	public void setOwner(Usuario usuario) {
		this.owner = usuario;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	
	
	
}

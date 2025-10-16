package model;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table(name = "TB_TAREFAS")
public class Tarefa {

	@Id
    private Integer id;  

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private LocalDate prazo;

    @Column(nullable = false)
    private boolean concluida;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario owner;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    public Tarefa() {}
    
    //gera IDs aleatórios de 4 digitos
    @PrePersist
    public void gerarId() {
        if (id == null) {
            id = (int) (1000 + Math.random() * 9000); 
        }
    }

    // Getters e Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public LocalDate getPrazo() { return prazo; }
    public void setPrazo(LocalDate prazo) { this.prazo = prazo; }

    public boolean isConcluida() { return concluida; }
    public void setConcluida(boolean concluida) { this.concluida = concluida; }

    public Usuario getOwner() { return owner; }
    public void setOwner(Usuario owner) { this.owner = owner; }

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }
}

package model;

import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "TB_USUARIOS")
public class Usuario {

	@Id
    private Integer id; 

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "owner")
    private List<Tarefa> tarefas;

    public Usuario() {}
    
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

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public List<Tarefa> getTarefas() { return tarefas; }
    public void setTarefas(List<Tarefa> tarefas) { this.tarefas = tarefas; }

    @Override
    public String toString() {
        return nome;
    }
}

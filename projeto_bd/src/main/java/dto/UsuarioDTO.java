package dto;

/* DTO para transferir dados de usuário entre a interface e o Service/Controller */

public class UsuarioDTO {
    private Integer id;
    private String nome;
    private String email;

    public UsuarioDTO() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
}

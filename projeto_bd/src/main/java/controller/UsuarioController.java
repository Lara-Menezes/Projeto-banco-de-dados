package controller;

import model.Usuario;
import service.UsuarioService;

/*
 * Usuario controller tem a função de chamar o usuario service (que tem os serviços da regra de negócio)
 * para salvar um usuário. Essa classe instancia um objeto final do usuario service e faz a lógica para salvar o usuário
 * se o nome do usuário e o email forem vazios ele retorna "Preencha todos os campos" e caso contrário ele cria um novo usuário
 * através da classe usuário service indicando um nome e email para ela (Entidade usuário) e retornando "Usuário cadastrado com sucesso"
 */
public class UsuarioController {

    private final UsuarioService usuarioService = new UsuarioService();

    public String salvarUsuario(String nome, String email) {
        if (nome == null || nome.isBlank() || email == null || email.isBlank())
            return "Preencha todos os campos!";

        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuarioService.criarUsuario(usuario);
        return "Usuário cadastrado com sucesso!";
    }
    
    public String atualizarUsuario(Long id, String nome, String email) {
    	if(id == null) return "id inválido!";
    	if(nome == null || nome.isBlank() || email == null || email.isBlank()) 
    		return "Preencha os campos!";
    	
    	Usuario usuario = new Usuario();
    	usuario.setId(id);
    	usuario.setNome(nome);
    	usuario.setEmail(email);
    	usuarioService.atualizarUsuario(usuario);
    	return "Sucesso na atualização de usuário!";
    }
}

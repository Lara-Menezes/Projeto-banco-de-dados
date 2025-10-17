package controller;

import dto.UsuarioDTO;
import model.Usuario;
import service.UsuarioService;

import java.util.List;

/*
 * Usuario controller tem a função de chamar o usuario service (que tem os serviços da regra de negócio)
 * para salvar um usuário. Essa classe instancia um objeto final do usuario service e faz a lógica para salvar o usuário
 * se o nome do usuário e o email forem vazios ele retorna "Preencha todos os campos" e caso contrário ele cria um novo usuário
 * através da classe usuário service indicando um nome e email para ela (Entidade usuário) e retornando "Usuário cadastrado com sucesso"
 */
public class UsuarioController {

    private final UsuarioService usuarioService = new UsuarioService();

    public String salvarUsuario(UsuarioDTO dto) {
        try {
        	usuarioService.criarUsuario(dto);
            return "Usuário cadastrado com sucesso!";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        } catch (Exception e) {
            return "Erro ao salvar usuário: " + e.getMessage();
        }
    }
    
    public String atualizarUsuario(Integer id, UsuarioDTO dto) {
        try {
        	usuarioService.atualizarUsuario(id, dto);
            return "Usuário atualizado com sucesso!";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        } catch (Exception e) {
            return "Erro ao atualizar usuário: " + e.getMessage();
        }
    }

    public String deletarUsuario(Integer id) {
        try {
        	usuarioService.excluirUsuario(id);
            return "Usuário deletado com sucesso!";   
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        } catch (Exception e) {
            return "Erro ao deletar usuário: " + e.getMessage();
        }
    }

    public List<Usuario> ListarUsuarios(){
        return usuarioService.listarUsuarios();
    }
}

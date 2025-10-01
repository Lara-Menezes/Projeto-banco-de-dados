package controller;

import model.Usuario;
import service.UsuarioService;

public class UsuarioController {
    private final UsuarioService usuarioService = new UsuarioService();

    public String salvarUsuario(String nome, String email) {
        if (nome == null || nome.isBlank() || email == null || email.isBlank()) {
            return "Preencha todos os campos!";
        }

        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setEmail(email);

        usuarioService.criarUsuario(usuario);

        return "Usuário cadastrado com sucesso!";
    }
}


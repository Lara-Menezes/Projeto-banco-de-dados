package controller;

import model.Categoria;
import service.CategoriaService;

public class CategoriaController {
    private final CategoriaService categoriaService = new CategoriaService();

    public String salvarCategoria(String nome) {
        if (nome == null || nome.isBlank()) {
            return "Digite o nome da categoria!";
        }

        Categoria categoria = new Categoria();
        categoria.setNome(nome);

        categoriaService.criarCategoria(categoria);

        return "Categoria cadastrada com sucesso!";
    }
}


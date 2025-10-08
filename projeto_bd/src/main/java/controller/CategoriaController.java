package controller;

import model.Categoria;
import service.CategoriaService;

/*
 * Categoria controller tem a função de chamar o categoria service (que tem os serviços da regra de negócio)
 * para salvar uma categoria. Essa classe instancia um objeto final do categoria service e faz a lógica para salvar a categoria
 * se o nome da categoria e os campos forem vazios ele retorna "digite o nome da categoria" e caso contrário ele cria uma nova categoria
 * através da classe categoria service indicando um nome para ela e retornando "categoria cadastrada com sucesso"
 */
public class CategoriaController {

    private final CategoriaService categoriaService = new CategoriaService();

    public String salvarCategoria(String nome) {
        if (nome == null || nome.isBlank()) return "Digite o nome da categoria!";

        Categoria categoria = new Categoria();
        categoria.setNome(nome);
        categoriaService.criarCategoria(categoria);
        return "Categoria cadastrada com sucesso!";
    }
}

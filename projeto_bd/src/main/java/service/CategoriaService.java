package service;

import dao.CategoriaDAO;
import model.Categoria;
import java.util.List;

public class CategoriaService {

    private final CategoriaDAO categoriaDAO = new CategoriaDAO();

    public void criarCategoria(Categoria categoria) {
        categoriaDAO.salvar(categoria);
    }

    public Categoria buscarCategoria(Integer id) {
        return categoriaDAO.buscarPorId(id);
    }

    public List<Categoria> listarCategorias() {
        return categoriaDAO.listarTodos();
    }

    public void atualizarCategoria(Categoria categoria) {
        categoriaDAO.atualizar(categoria);
    }

    public void excluirCategoria(Integer id) {
        categoriaDAO.excluir(id);
    }
}

package dao;

import model.Categoria;
/*
 * Classe responsável por extender os comportamentos genéricos do dao genérico para persistir as categorias
 */
public class CategoriaDAO extends GenericDAO<Categoria> {
    public CategoriaDAO() {
        super(Categoria.class);
    }
}

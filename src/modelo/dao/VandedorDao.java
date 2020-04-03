package modelo.dao;

import java.util.List;

import modelo.entidade.Vendedor;

public interface VandedorDao {
	
	void insert(Vendedor obj);
	void upadate(Vendedor obj);
	void deleteById(Integer id);
	Vendedor findById(Integer id);
	List<Vendedor> findAll();
}

package modelo.dao;

import java.util.List;

import modelo.entidade.Vendedor;

public interface VendedorDao {
	
	void insert(Vendedor obj);
	void upadate(Vendedor obj);
	void deleteById(Integer id);
	Vendedor findById(Integer id);
	List<Vendedor> findAll();
}

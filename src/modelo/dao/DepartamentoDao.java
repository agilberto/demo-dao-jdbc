package modelo.dao;

import java.util.List;

import modelo.entidade.Departamento;

public interface DepartamentoDao {
	
	void insert(Departamento obj);
	void upadate(Departamento obj);
	void deleteById(Integer id);
	Departamento findById(Integer id);
	List<Departamento> findAll();
	
	

}

package aplicacao;

import java.util.Date;

import modelo.dao.DaoFactory;
import modelo.dao.VendedorDao;
import modelo.entidade.Departamento;
import modelo.entidade.Vendedor;

public class Programa {

	public static void main(String[] args) {
		
	VendedorDao vendedorDao = DaoFactory.createVendedorDao();
	
	System.out.println("=== TEST 1: Vendedor findById ====");
	
	Vendedor vendedor = vendedorDao.findById(3);
			
	System.out.println(vendedor);
	
		  
	}

}

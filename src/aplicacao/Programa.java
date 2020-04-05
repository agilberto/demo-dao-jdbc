package aplicacao;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import modelo.dao.DaoFactory;
import modelo.dao.VendedorDao;
import modelo.entidade.Departamento;
import modelo.entidade.Vendedor;

public class Programa {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
	VendedorDao vendedorDao = DaoFactory.createVendedorDao();
	
	System.out.println("=== TEST 1: Vendedor findById ====");
	
	Vendedor vendedor = vendedorDao.findById(3);
			
	System.out.println(vendedor);
	
	System.out.println("\n=== TEST 2: Vendedor findByDepartamento ====");
	
	
	Departamento departamento = new Departamento(2,null);
	List<Vendedor> list = vendedorDao.FindByDepartamento(departamento);
	
	for (Vendedor obj : list) {
		
		System.out.println(obj);
	}
	
	System.out.println("\n=== TEST 3: Vendedor findByAll ====");

	list = vendedorDao.findAll();
	
	for (Vendedor obj : list) {
		
		System.out.println(obj);
	}
	
	System.out.println("\n=== TEST 4: Vendedor insert ====");
    Vendedor newVendedor = new Vendedor(null, "Antonio Gilberto da Silva", "agilberto20111@hotmail.com", new Date(), 12000.00, departamento);
	
	vendedorDao.insert(newVendedor);
	System.out.println("Inserido novo id = " + newVendedor.getId());
	
	System.out.println("\n=== TEST 5: Vendedor Update ====");
	vendedor = vendedorDao.findById(1);
	vendedor.setNome("Leonrado");
	vendedor.setEmail("leo@gmail.com");
	vendedorDao.upadate(vendedor);
	System.out.println("Update completado");
	
	System.out.println("\n=== TEST 6: Vendedor Delete ====");
	System.out.println("Entre com o id para testar a deleção");
	int id = sc.nextInt();
	
	vendedorDao.deleteById(id);
	
	System.out.println("Deleção completada");
	
	sc.close();
			
			
			
			
			
	}

	
}

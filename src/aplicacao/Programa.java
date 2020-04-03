package aplicacao;

import java.util.Date;

import modelo.entidade.Departamento;
import modelo.entidade.Vendedor;

public class Programa {

	public static void main(String[] args) {
		 
		Departamento obj = new Departamento(1,"Livros");
		System.out.println(obj);
		
		Vendedor vendedor = new Vendedor(21, "Gilberto", "agilberto20111@hotmail.com", new Date(), 3000.0, obj);
		System.out.println(vendedor);
	}

}

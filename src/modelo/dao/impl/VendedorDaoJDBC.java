package modelo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import modelo.dao.VendedorDao;
import modelo.entidade.Departamento;
import modelo.entidade.Vendedor;

public class VendedorDaoJDBC implements VendedorDao {
	
	private Connection conn;
	
	public VendedorDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Vendedor obj) {
		 
		PreparedStatement st = null;
		
		try {
			
			st = conn.prepareStatement("insert into seller (Name, Email, "
										+ "BirthDate,BaseSalary, DepartmentId) "
										+ "values (?,?,?,?, ?)", 
										Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, obj.getNome());
			st.setString(2, obj.getEmail());
		    st.setDate(3, new java.sql.Date(obj.getDataNascimento().getTime()));
		 	st.setDouble(4, obj.getSalarioBase());
		 	st.setInt(5, obj.getDepartamento().getId());
			
			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				
			 ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
			}else {
				throw new DbException("Erro inesperado, nenhuma linha foi afetadada");
			}
			
		}catch(SQLException e){
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			 
		}
	}

	@Override
	public void upadate(Vendedor obj) {
		 
		PreparedStatement st = null;
		
		try {
			
			st = conn.prepareStatement("Update seller set Name=?, Email=?, BirthDate=?, "
									 + "BaseSalary=?, DepartmentId=? where id=? ");
										 
			st.setString(1, obj.getNome());
			st.setString(2, obj.getEmail());
		    st.setDate(3, new java.sql.Date(obj.getDataNascimento().getTime()));
		 	st.setDouble(4, obj.getSalarioBase());
		 	st.setInt(5, obj.getDepartamento().getId());
		    st.setInt(6, obj.getId());
			
			st.executeUpdate();

		}catch(SQLException e){
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void deleteById(Integer id) {
		 
		PreparedStatement st = null;
		
		try {
			 
			st = conn.prepareStatement("delete from seller where Id= ?");
			st.setInt(1, id);
			st.executeUpdate();
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
		
		
		
	}

	@Override
	public Vendedor findById(Integer id) {
		 
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
		st = conn.prepareStatement("SELECT seller.*,department.Name as DepName  FROM seller "
					             + "INNER JOIN department  ON seller.DepartmentId = department.Id"
					             + "  WHERE seller.Id = ? ");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if (rs.next()) {
				
				Departamento dep = instantiateDepartment(rs);
				Vendedor obj = instantiateVendedor(rs, dep);
				return obj;
				
			}
			
			return null;
			 
		} catch(SQLException e) {
			
			throw new DbException(e.getMessage());
		  
	}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
	private Vendedor instantiateVendedor(ResultSet rs, Departamento dep) throws SQLException {
	    Vendedor obj = new Vendedor();
		
		obj.setId(rs.getInt("Id"));
		obj.setNome(rs.getString("Name"));
		obj.setEmail(rs.getString("Email"));
		obj.setSalarioBase(rs.getDouble("BaseSalary"));
		obj.setDataNascimento(rs.getDate("BirthDate"));
		obj.setDepartamento(dep);
		return obj;
	}

	private Departamento instantiateDepartment(ResultSet rs) throws SQLException {
		 
		Departamento dep = new Departamento();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setNome(rs.getString("DepName"));
		return dep;
	}

	@Override
	public List<Vendedor> findAll() {
	 
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
		st = conn.prepareStatement("SELECT seller.*,department.Name as DepName  FROM seller "
					             + "INNER JOIN department  ON seller.DepartmentId = department.Id "
					             + "order by Name");
			 
			rs = st.executeQuery();
			
			List<Vendedor> list = new ArrayList<>();
			Map<Integer, Departamento> map = new HashMap<>();
			
			while (rs.next()) {
				Departamento dep = map.get(rs.getInt("DepartmentId"));
				if (dep == null) {
					dep = instantiateDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}
				 
				Vendedor obj = instantiateVendedor(rs, dep);
				list.add(obj);
			}
			
			return list;
			 
		} catch(SQLException e) {
			
			throw new DbException(e.getMessage());
		  
	}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Vendedor> FindByDepartamento(Departamento departamento) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
		st = conn.prepareStatement("SELECT seller.*,department.Name as DepName  FROM seller "
					             + "INNER JOIN department  ON seller.DepartmentId = department.Id"
					             + "  WHERE department.Id = ? order by Name");
					 
			st.setInt(1, departamento.getId());
			rs = st.executeQuery();
			
			List<Vendedor> list = new ArrayList<>();
			Map<Integer, Departamento> map = new HashMap<>();
			
			while (rs.next()) {
				Departamento dep = map.get(rs.getInt("DepartmentId"));
				if (dep == null) {
					dep = instantiateDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}
				 
				Vendedor obj = instantiateVendedor(rs, dep);
				list.add(obj);
			}
			
			return list;
			 
		} catch(SQLException e) {
			
			throw new DbException(e.getMessage());
	}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}

	}
	 
}

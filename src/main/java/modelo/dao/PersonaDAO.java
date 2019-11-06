package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import modelo.ConnectionManager;
import modelo.pojo.Persona;

public class PersonaDAO {

	private static PersonaDAO INSTANCE = null;

	private static final String SQL_GETALL = "SELECT id, nombre, apellido1, apellido2, email, dni FROM personas;";
	private static final String SQL_INSERT = "INSERT INTO personas (nombre, apellido1, apellido2, email, dni) VALUES (?, ?, ?, ?, ?);";
	private static final String SQL_UPDATE = "UPDATE personas SET nombre = ?, apellido1 = ?, apellido2 = ?, email = ?, dni = ? WHERE id = ?;";
	private static final String SQL_GET_BY_ID = "SELECT id, nombre, apellido1, apellido2, email, dni FROM personas WHERE id = ?;";
	private static final String SQL_DELETE = "DELETE FROM personas WHERE id = ?;";

	private PersonaDAO() {
		super();
	}

	public static PersonaDAO getInstance() {

		if (INSTANCE == null) {
			INSTANCE = new PersonaDAO();
		}

		return INSTANCE;
	}

	public ArrayList<Persona> getAll() {

		ArrayList<Persona> listaPersonas = new ArrayList<Persona>();

		try (Connection con = ConnectionManager.getConnection(); // Obtenemos conexion con la BBDD
				PreparedStatement pst = con.prepareStatement(SQL_GETALL); // Preparamos la sql a ejecutar
				ResultSet rs = pst.executeQuery()) { // Ejecuta la sql

			while (rs.next()) {
				listaPersonas.add(mapper(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaPersonas;
	}

	public boolean crear(Persona pojo) {

		boolean res = false;

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {
			pst.setString(1, pojo.getNombre());
			pst.setString(2, pojo.getApellido1());
			pst.setString(3, pojo.getApellido2());
			pst.setString(4, pojo.getEmail());
			pst.setString(5, pojo.getDni());

			int affectedRows = pst.executeUpdate();
			if (affectedRows == 1) {
				ResultSet claveGenerada = pst.getGeneratedKeys();
				if (claveGenerada.next()) {
					pojo.setId(claveGenerada.getInt(1));
				}
				res = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}

	public boolean modificar(Persona pojo) {
		boolean res = false;

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_UPDATE)) {
			pst.setString(1, pojo.getNombre());
			pst.setString(2, pojo.getApellido1());
			pst.setString(3, pojo.getApellido2());
			pst.setString(4, pojo.getEmail());
			pst.setString(5, pojo.getDni());
			pst.setInt(6, pojo.getId());

			int affectedRows = pst.executeUpdate();
			if (affectedRows == 1) {
				res = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}

	public Persona getById(int id) {
		Persona res = new Persona();

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_GET_BY_ID)) {

			pst.setInt(1, id);

			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					res = mapper(rs);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return res;
	}

	public boolean delete(int id) {
		
		boolean res = false;
		
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_DELETE)) {

			pst.setInt(1, id);

			int affectedRows = pst.executeUpdate();
			if (affectedRows == 1) {
				res = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return res;
	}

	private Persona mapper(ResultSet rs) throws SQLException {
		Persona p = new Persona(); // Persona en blanco

		p.setId(rs.getInt("id"));
		p.setNombre(rs.getString("nombre"));
		p.setApellido1(rs.getString("apellido1"));
		p.setApellido2(rs.getString("apellido2"));
		p.setEmail(rs.getString("email"));
		p.setDni(rs.getString("dni")); // Persona completa
		
		return p;
	}
}
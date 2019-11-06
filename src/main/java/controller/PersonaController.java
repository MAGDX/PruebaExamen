package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.dao.PersonaDAO;
import modelo.pojo.Persona;

/**
 * Servlet implementation class PersonaController
 */
@WebServlet("/personas")
public class PersonaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static final String VIEW_INDEX = "index.jsp";
	public static final String VIEW_FORM = "formulario.jsp";
	public static String view = VIEW_INDEX;
	
	public static final String OP_LISTAR = "0";
	public static final String OP_DETALLE = "1";
	public static final String OP_GUARDAR = "2";
	public static final String OP_ELIMINAR = "3";
	public static final String OP_NUEVO = "4";
	
	private static PersonaDAO personaDAO;
	
	@Override
	public void init() throws ServletException {
		super.init();
		personaDAO = PersonaDAO.getInstance();
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		
		if (op == null) {
			op = OP_LISTAR;
		}

		switch (op) {
		case OP_DETALLE:
			detalle(request, response);
			break;

		case OP_GUARDAR:
			guardar(request, response);
			break;

		case OP_ELIMINAR:
			eliminar(request, response);
			break;

		case OP_NUEVO:
			nuevo(request, response);
			break;

		default:
			listar(request, response);
			break;
		}

		request.getRequestDispatcher(view).forward(request, response);
	}

	private static void listar(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("listaPersonas", personaDAO.getAll());
		
		view = VIEW_INDEX;
	}

	private void detalle(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		
		request.setAttribute("persona", personaDAO.getById(id));
		
		view = VIEW_FORM;
	}

	private void nuevo(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("persona", new Persona());
		
		view = VIEW_FORM;
	}

	private void guardar(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		String nombre = request.getParameter("nombre");
		String apellido1 = request.getParameter("apellido1");
		String apellido2 = request.getParameter("apellido2");
		String email = request.getParameter("email");
		String dni = request.getParameter("dni");
		
		Persona p = new Persona(id, nombre, apellido1, apellido2, email, dni);
		
		if(id > 0 ) {
			if(personaDAO.modificar(p)) {
				request.setAttribute("mensaje", "Registro modificado con exito!");
			}else {
				request.setAttribute("mensaje", "Registro no modificado!");
			}
		}else {
			if(personaDAO.crear(p)) {
				request.setAttribute("mensaje", "Registro creado con exito!");
			}else {
				request.setAttribute("mensaje", "Registro no creado!");
			}
		}
		
		request.setAttribute("persona", p);
		
		view = VIEW_FORM;
	}

	private void eliminar(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		
		if(personaDAO.delete(id)){
			request.setAttribute("mensaje", "Registro eliminado con exito!");
		}else {
			request.setAttribute("mensaje", "Registro no eliminado!");
		}
		
		listar(request, response);
	}
}
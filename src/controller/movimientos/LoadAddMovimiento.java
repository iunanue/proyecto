package controller.movimientos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.business.GestorCuentasService;
import model.business.GestorMovimientosService;
import model.business.GestorUsuariosService;
import model.classes.ClaseGasto;
import model.classes.ClaseIngreso;
import model.classes.Cuenta;
import model.classes.Usuario;
import model.data.Connect;

/**
 * Servlet implementation class NuevoMovimiento
 */
@WebServlet("/protected_area/loadAddMovimiento")
public class LoadAddMovimiento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Connect c = new Connect();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadAddMovimiento() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		List<Usuario> listaUsuarios = GestorUsuariosService.getInstance().getUsers();
		request.setAttribute("listaUsuarios", listaUsuarios);
		 
		List<ClaseIngreso> listaClaseIngreso = GestorMovimientosService.getInstance().getClaseIngreso();
		request.setAttribute("listaClaseIngreso", listaClaseIngreso);
		 
		List<ClaseGasto> listaClaseGasto = GestorMovimientosService.getInstance().getClaseGasto();
		request.setAttribute("listaClaseGasto", listaClaseGasto);
		 
		List<Cuenta> listaCuentas = GestorCuentasService.getInstance().getCuentas();		 
		request.setAttribute("listaCuentas", listaCuentas);
		 
		request.getRequestDispatcher("/protected_area/registrarMovimiento.jsp").forward(request, response);	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.ClaseGasto;
import classes.ClaseIngreso;
import classes.Cuenta;
import classes.Usuario;
import model.Connect;

/**
 * Servlet implementation class NuevoMovimiento
 */
@WebServlet("/protected_area/registrarMovimiento")
public class RegistrarMovimiento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Connect c = new Connect();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrarMovimiento() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		List<Usuario> listaUsuarios = c.getIDao().getUsers();
		 for(Usuario usuario: listaUsuarios){
	            System.out.println(usuario.getUsername());
	        }
		 request.setAttribute("listaUsuarios", listaUsuarios);
		 
		 List<ClaseIngreso> listaClaseIngreso = c.getIDao().getClaseIngreso();
		 for(ClaseIngreso claseingreso: listaClaseIngreso){
	            System.out.println(claseingreso.getDescripcion());
	        }
		 request.setAttribute("listaClaseIngreso", listaClaseIngreso);
		 
		 List<ClaseGasto> listaClaseGasto = c.getIDao().getClaseGasto();
		 for(ClaseGasto clasegasto: listaClaseGasto){
	            System.out.println(clasegasto.getDescripcion());
	        }
		 request.setAttribute("listaClaseGasto", listaClaseGasto);
		 
		 List<Cuenta> listaCuentas = c.getIDao().getCuentas();
		 for(Cuenta cuenta: listaCuentas){
	            System.out.println(cuenta.getDescripcion());
	        }
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

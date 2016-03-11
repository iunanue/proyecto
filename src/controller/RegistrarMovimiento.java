package controller;

import java.io.IOException;
import java.util.ArrayList;

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
		
//		ArrayList<Usuario> listaUsers = c.getDao().getUsers();
//		 for(Usuario usuario: listaUsers){
//	            System.out.println(usuario.getUsername());
//	        }
//		 request.setAttribute("listaUsers", listaUsers);
//		 
//		 ArrayList<ClaseIngreso> listaClaseIngreso = c.getDao().getClaseIngreso();
//		 for(ClaseIngreso claseingreso: listaClaseIngreso){
//	            System.out.println(claseingreso.getDescripcion());
//	        }
//		 request.setAttribute("listaClaseIngreso", listaClaseIngreso);
//		 
//		 ArrayList<ClaseGasto> listaClaseGasto = c.getDao().getClaseGasto();
//		 for(ClaseGasto clasegasto: listaClaseGasto){
//	            System.out.println(clasegasto.getDescripcion());
//	        }
//		 request.setAttribute("listaClaseGasto", listaClaseGasto);
//		 
//		 ArrayList<Cuenta> listaCuentas = c.getDao().getCuentas();
//		 for(Cuenta cuenta: listaCuentas){
//	            System.out.println(cuenta.getDescripcion());
//	        }
//		 request.setAttribute("listaCuentas", listaCuentas);
		 
		
		
		
		request.getRequestDispatcher("/protected_area/registrarMovimiento.jsp").forward(request, response);	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

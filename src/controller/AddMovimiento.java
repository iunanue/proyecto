package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddMovimiento
 */
@WebServlet("/protected_area/addMovimiento")
public class AddMovimiento extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	String tipo;
	Date fecha;
	int id_clase;
	String username;
	int id_cuenta;
	float importe;
	String descripcion;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddMovimiento() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		this.tipo = request.getParameter("tipo");
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			this.fecha = sdf.parse(request.getParameter("fecha"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(tipo.equals("ingreso"))
		{
			this.id_clase =  Integer.parseInt(request.getParameter("claseIngreso"));
		}
		if(tipo.equals("gasto"))
		{
			this.id_clase =  Integer.parseInt(request.getParameter("claseGasto"));
		}
		
		this.username = request.getParameter("username");
		
		this.id_cuenta = Integer.parseInt(request.getParameter("cuenta"));
		
		this.importe = Float.parseFloat(request.getParameter("importe"));
		
		this.descripcion = request.getParameter("descripcion");
		

		System.out.println(tipo);
		System.out.println(fecha);
		System.out.println(id_clase);
		System.out.println(username);
		System.out.println(id_cuenta);
		System.out.println(importe);
		System.out.println(descripcion);

		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}

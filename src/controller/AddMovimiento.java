package controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Connect;

/**
 * Servlet implementation class AddMovimiento
 */
@WebServlet("/protected_area/addMovimiento")
public class AddMovimiento extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Connect c = new Connect();
	
	String tipo;
	Timestamp fecha;
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
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date aux = null;
		try {
			aux = format.parse(request.getParameter("fecha"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fecha = new Timestamp(aux.getTime());
		
//		proceso inverso
//		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//		String string  = dateFormat.format(new Date());
//		System.out.println(string);
		
		
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
		System.out.println("Fecha " + fecha);
		System.out.println("Fecha param " + request.getParameter("fecha"));
		System.out.println(id_clase);
		System.out.println(username);
		System.out.println(id_cuenta);
		System.out.println(importe);
		System.out.println(descripcion);

		
		c.getDao().addMovimiento(tipo,fecha,id_clase,username,id_cuenta,importe,descripcion);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}

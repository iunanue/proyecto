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




/**
 * Servlet implementation class GenerarConsultaMovimientos
 */
@WebServlet("/protected_area/generarConsultaMovimientos")
public class GenerarConsultaMovimientos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
//	Connect c = new Connect();
	
	boolean filtroFecha;
	boolean filtroTipo;
	boolean filtroClase;
	boolean filtroUsuario;
	boolean filtroCuenta;
	
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
    public GenerarConsultaMovimientos() {
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
		if(request.getParameter("filtroFecha").equals("fecha_si")){
			filtroFecha = true;
		}
		else{
			filtroFecha = false;
		}
		
		if(request.getParameter("filtroTipo").equals("tipo_si")){
			filtroTipo = true;
		}
		else{
			filtroTipo = false;
		}
		
		if(request.getParameter("filtroClase").equals("clase_si")){
			filtroClase = true;
		}
		else{
			filtroClase = false;
		}
		
		if(request.getParameter("filtroUsuario").equals("usuario_si")){
			filtroUsuario = true;
		}
		else{
			filtroUsuario = false;
		}
		
		if(request.getParameter("filtroCuenta").equals("cuenta_si")){
			filtroCuenta = true;
		}
		else{
			filtroCuenta = false;
		}

		System.out.println(filtroFecha);
		System.out.println(filtroTipo);
		System.out.println(filtroClase);
		System.out.println(filtroUsuario);
		System.out.println(filtroCuenta);
		
//		this.tipo = request.getParameter("tipo");
//		
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//		Date aux = null;
//		try {
//			aux = format.parse(request.getParameter("fecha"));
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		fecha = new Timestamp(aux.getTime());
//		
////		proceso inverso
////		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
////		String string  = dateFormat.format(new Date());
////		System.out.println(string);
//		
//		
//		if(tipo.equals("Ingreso"))
//		{
//			this.id_clase =  Integer.parseInt(request.getParameter("claseIngreso"));
//		}
//		if(tipo.equals("Gasto"))
//		{
//			this.id_clase =  Integer.parseInt(request.getParameter("claseGasto"));
//		}
//		
//		this.username = request.getParameter("username");
//		
//		this.id_cuenta = Integer.parseInt(request.getParameter("cuenta"));
//		
//		this.importe = Float.parseFloat(request.getParameter("importe"));
//		
//		this.descripcion = request.getParameter("descripcion");
//		
//
//		System.out.println(tipo);
//		System.out.println("Fecha " + fecha);
//		System.out.println("Fecha param " + request.getParameter("fecha"));
//		System.out.println(id_clase);
//		System.out.println(username);
//		System.out.println(id_cuenta);
//		System.out.println(importe);
//		System.out.println(descripcion);
//
////		Movimiento movimiento = new Movimiento(tipo,fecha,id_clase,username,id_cuenta,importe,descripcion);
////		c.getIDao().addMovimiento(movimiento);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}

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

import classes.Movimiento;
import model.Connect;

/**
 * Servlet implementation class UpdateMovimiento
 */
@WebServlet("/protected_area/updateMovimiento")
public class UpdateMovimiento extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	Connect c = new Connect();
	
	Movimiento movimiento;
	int id_movimiento;
	String tipo;
	Timestamp fecha;
	int id_clase;
	String username;
	int id_cuenta;
	float importe;
	String descripcion;
	
	String mensaje = "";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateMovimiento() {
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
		this.id_movimiento =  Integer.parseInt(request.getParameter("id_movimiento"));
		if (checkForm(request, response))
		{
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
			
//			proceso inverso
//			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//			String string  = dateFormat.format(new Date());
//			System.out.println(string);
			
			
			if(tipo.equals("Ingreso"))
			{
				this.id_clase =  Integer.parseInt(request.getParameter("claseIngreso"));
			}
			if(tipo.equals("Gasto"))
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

			Movimiento movimientoAntiguo = c.getIDao().getMovimiento(id_movimiento);
			Movimiento movimientoActualizado = new Movimiento(id_movimiento,tipo,fecha,id_clase,username,id_cuenta,importe,descripcion);
			c.getIDao().updateMovimiento(movimientoAntiguo,movimientoActualizado);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		else
		{
			movimiento = c.getIDao().getMovimiento(id_movimiento);
			request.setAttribute("movimiento", movimiento);
			request.getSession().setAttribute("mensaje", mensaje);
			request.getRequestDispatcher("/protected_area/loadUpdateMovimiento").forward(request, response);
		}

	}
	private boolean checkForm(HttpServletRequest request, HttpServletResponse response) {
		if ((request.getParameter("fecha").equals(""))||(request.getParameter("importe").equals(""))) {
			mensaje = "Rellene todos los campos por favor.";
			System.out.println(mensaje);
			return false;
		}
		else{
			return true;
		}

	}

}

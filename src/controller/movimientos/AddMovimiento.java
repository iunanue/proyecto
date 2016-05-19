package controller.movimientos;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.routines.EmailValidator;

import model.business.GestorMovimientosService;
import model.classes.ClaseGasto;
import model.classes.ClaseIngreso;
import model.classes.Cuenta;
import model.classes.Movimiento;
import model.classes.Usuario;
import model.data.Connect;

/**
 * Servlet implementation class AddMovimiento
 */
@WebServlet("/protected_area/addMovimiento")
public class AddMovimiento extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Connect c = new Connect();
	
	int id_tipoMovimiento;
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
	
		
		
		if (checkForm(request, response))
		{
			this.id_tipoMovimiento = Integer.parseInt(request.getParameter("id_tipoMovimiento"));
			
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
			
			
			if(id_tipoMovimiento==1)
			{
				this.id_clase =  Integer.parseInt(request.getParameter("claseIngreso"));
			}
			if(id_tipoMovimiento==2)
			{
				this.id_clase =  Integer.parseInt(request.getParameter("claseGasto"));
			}
			
			this.username = request.getParameter("username");
			
			this.id_cuenta = Integer.parseInt(request.getParameter("cuenta"));
			
			this.importe = Float.parseFloat(request.getParameter("importe"));
			
			this.descripcion = request.getParameter("descripcion");
			GestorMovimientosService.getInstance().addMovimiento(id_tipoMovimiento,fecha,id_clase,username,id_cuenta,importe,descripcion);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		else
		{
			request.getSession().setAttribute("mensaje", mensaje);
			request.getRequestDispatcher("/protected_area/registrarMovimiento").forward(request, response);
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

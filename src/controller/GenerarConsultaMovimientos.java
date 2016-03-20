package controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.Movimiento;
import model.Connect;




/**
 * Servlet implementation class GenerarConsultaMovimientos
 */
@WebServlet("/protected_area/generarConsultaMovimientos")
public class GenerarConsultaMovimientos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Connect c = new Connect();
	
//	String consulta = "";
	List <String> consulta;
	String mensaje = "";
	
	boolean filtroFecha;
	boolean filtroTipo;
	boolean filtroClase;
	boolean filtroUsuario;
	boolean filtroCuenta;
	
	String tipo;
	Timestamp fechaInicio;
	Timestamp fechaFin;
	int id_clase;
	String username;
	int id_cuenta;
	
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
		
		
		if (checkForm(request, response))
		{
			if(filtroFecha == true){
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Date aux = null;
				try {
					aux = format.parse(request.getParameter("fecha_inicio"));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.fechaInicio = new Timestamp(aux.getTime());
				
				try {
					aux = format.parse(request.getParameter("fecha_fin"));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.fechaFin = new Timestamp(aux.getTime());
			}
			if(checkFechas(request, response)){
				
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				String fechaI  = dateFormat.format(fechaInicio);
				String fechaF  = dateFormat.format(fechaFin);
				
				consulta = new ArrayList<String>();
				consulta.add(fechaI + " a " + fechaF);
				
				if(filtroTipo == true){
					this.tipo = request.getParameter("tipo");
					consulta.add(tipo);
				}
				
				if(filtroClase == true){
					if(request.getParameter("claseIngreso") != null)
					{
						this.id_clase =  Integer.parseInt(request.getParameter("claseIngreso"));
						consulta.add(c.getIDao().getClaseIngreso().get(id_clase-1).getDescripcion());
					}
					else{
						this.id_clase =  Integer.parseInt(request.getParameter("claseGasto"));
						consulta.add(c.getIDao().getClaseGasto().get(id_clase-1).getDescripcion());					}
				}
				
				if(filtroUsuario == true){
					this.username = request.getParameter("username");
					consulta.add(username);
				}
				
				if(filtroCuenta == true){
					this.id_cuenta = Integer.parseInt(request.getParameter("cuenta"));
					consulta.add(c.getIDao().getCuenta(id_cuenta).getDescripcion());
				}
				
				
				request.getSession().setAttribute("consulta", consulta);
				
				List<Movimiento> listaMovimientos = c.getIDao().getGenerarConsultaMovimientos(filtroFecha,filtroTipo,filtroClase,filtroUsuario,filtroCuenta,tipo,fechaInicio,fechaFin,id_clase,username,id_cuenta);
				
				request.setAttribute("listaMovimientos", listaMovimientos);
				request.getRequestDispatcher("/protected_area/verConsultaMovimientos").forward(request, response);
			}
			else
			{
				request.getSession().setAttribute("mensaje", mensaje);
				request.getRequestDispatcher("/protected_area/loadConsultaMovimientos").forward(request, response);
			}
		}
		else
		{
			request.getSession().setAttribute("mensaje", mensaje);
			request.getRequestDispatcher("/protected_area/loadConsultaMovimientos").forward(request, response);
		}
			
	}
	private boolean checkForm(HttpServletRequest request, HttpServletResponse response) {
		if(filtroFecha == true){
			if ((request.getParameter("fecha_inicio").equals(""))||(request.getParameter("fecha_fin").equals(""))) {
				mensaje = "Escoja las fechas que correspondan.";
				System.out.println(mensaje);
				return false;
			}
			else{
				return true;
			}
		}
		else{
			return true;
		}
		

	}
	private boolean checkFechas(HttpServletRequest request, HttpServletResponse response) {
		if(filtroFecha == true){
			if (fechaInicio.after(fechaFin)) {
				mensaje = "La Fecha Inicio no puede ser mayor que la Fecha Fin.";
				System.out.println(mensaje);
				return false;
			}
			else{
				return true;
			}
		}
		else{
			return true;
		}

	}

}

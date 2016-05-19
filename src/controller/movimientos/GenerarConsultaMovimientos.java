package controller.movimientos;

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

import model.business.GestorCuentasService;
import model.business.GestorMovimientosService;
import model.classes.Movimiento;
import model.data.Connect;

/**
 * Servlet implementation class GenerarConsultaMovimientos
 */
@WebServlet("/protected_area/generarConsultaMovimientos")
public class GenerarConsultaMovimientos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connect c = new Connect();

	// String consulta = "";
	List<String> consulta;
	String mensaje = "";

	boolean filtroFecha;
	boolean filtroTipo;
	boolean filtroClase;
	boolean filtroUsuario;
	boolean filtroCuenta;

	int id_tipoMovimiento;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		if (request.getParameter("filtroFecha").equals("fecha_si")) {
			filtroFecha = true;
		} else {
			filtroFecha = false;
		}

		if (request.getParameter("filtroTipo").equals("tipo_si")) {
			filtroTipo = true;
		} else {
			filtroTipo = false;
		}

		if (request.getParameter("filtroClase").equals("clase_si")) {
			filtroClase = true;
		} else {
			filtroClase = false;
		}

		if (request.getParameter("filtroUsuario").equals("usuario_si")) {
			filtroUsuario = true;
		} else {
			filtroUsuario = false;
		}

		if (request.getParameter("filtroCuenta").equals("cuenta_si")) {
			filtroCuenta = true;
		} else {
			filtroCuenta = false;
		}

		System.out.println(filtroFecha);
		System.out.println(filtroTipo);
		System.out.println(filtroClase);
		System.out.println(filtroUsuario);
		System.out.println(filtroCuenta);

		if (checkForm(request, response)) {
			consulta = new ArrayList<String>();
			if (filtroFecha == true) {
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

				// aqui
				System.out.println("\nDentro");

				if (checkFechas(request, response)) {

					System.out.println("\nDentro bien");
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					String fechaI = dateFormat.format(fechaInicio);
					String fechaF = dateFormat.format(fechaFin);
			
					consulta.add(fechaI + " a " + fechaF);
				} else {
					System.out.println("\nDentro mal");
					request.getSession().setAttribute("mensaje", mensaje);
					request.getRequestDispatcher("/protected_area/loadConsultaMovimientos").forward(request, response);
				}
			} else {
				consulta.add(null);
				System.out.println("\nFuera");
			}
			if (filtroTipo == true) {
				this.id_tipoMovimiento = Integer.parseInt(request.getParameter("id_tipoMovimiento"));
				consulta.add(GestorMovimientosService.getInstance().getTiposMovimiento().get(id_tipoMovimiento-1).getDescripcion());
				if (filtroClase == true) {
					this.id_tipoMovimiento = Integer.parseInt(request.getParameter("id_tipoMovimiento"));
					if (id_tipoMovimiento==1) {
						System.out.println("claseingreso");
						this.id_clase = Integer.parseInt(request.getParameter("claseIngreso"));
						consulta.add(GestorMovimientosService.getInstance().getClaseIngreso().get(id_clase - 1).getDescripcion());
						System.out.println(" \n" +GestorMovimientosService.getInstance().getClaseIngreso().get(id_clase - 1).getDescripcion());
					} else {
						System.out.println("clasegasto");
						this.id_clase = Integer.parseInt(request.getParameter("claseGasto"));
						consulta.add(GestorMovimientosService.getInstance().getClaseGasto().get(id_clase - 1).getDescripcion());
						System.out.println(" \n" +GestorMovimientosService.getInstance().getClaseGasto().get(id_clase - 1).getDescripcion());
					}
				}
				else
				{
					consulta.add(null);
				}
			}
			else
			{
				consulta.add(null);
				consulta.add(null);
			}

			if (filtroUsuario == true) {
				this.username = request.getParameter("username");
				consulta.add(username);
			}
			else{
				consulta.add(null);
			}

			if (filtroCuenta == true) {
				this.id_cuenta = Integer.parseInt(request.getParameter("cuenta"));
				consulta.add(GestorCuentasService.getInstance().getCuenta(id_cuenta).getDescripcion());
			}
			else{
				consulta.add(null);
			}
			
			request.getSession().setAttribute("consulta", consulta);
			
			System.out.println(fechaInicio);

			List<Movimiento> listaMovimientos = GestorMovimientosService.getInstance().getGenerarConsultaMovimientos(filtroFecha,
					filtroTipo, filtroClase, filtroUsuario, filtroCuenta, id_tipoMovimiento, fechaInicio, fechaFin, id_clase,
					username, id_cuenta);

			request.setAttribute("listaMovimientos", listaMovimientos);
			request.getRequestDispatcher("/protected_area/verConsultaMovimientos").forward(request, response);
			

		} else {
			request.getSession().setAttribute("mensaje", mensaje);
			request.getRequestDispatcher("/protected_area/loadConsultaMovimientos").forward(request, response);
		}

	}

	private boolean checkForm(HttpServletRequest request, HttpServletResponse response) {
		if (filtroFecha == true) {
			if ((request.getParameter("fecha_inicio").equals("")) || (request.getParameter("fecha_fin").equals(""))) {
				mensaje = "Escoja las fechas que correspondan.";
				System.out.println(mensaje);
				return false;
			} else {
				return true;
			}
		} else {
			return true;
		}

	}

	private boolean checkFechas(HttpServletRequest request, HttpServletResponse response) {
		if (filtroFecha == true) {
			if (fechaInicio.after(fechaFin)) {
				mensaje = "La Fecha Inicio no puede ser mayor que la Fecha Fin.";
				System.out.println(mensaje);
				return false;
			} else {
				return true;
			}
		} else {
			return true;
		}

	}

}

package controller.analisis;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.business.GestorAnalisisService;
import model.business.GestorCuentasService;
import model.business.GestorMovimientosService;
import model.classes.ClaseGasto;
import model.classes.ClaseIngreso;
import model.classes.Cuenta;
import model.classes.Movimiento;
import model.data.Connect;

/**
 * Servlet implementation class AnalisisPersonalizado
 */
@WebServlet("/protected_area/analisisPersonalizado")
public class AnalisisPersonalizado extends HttpServlet {
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

	String tipo;
	Timestamp fechaInicio;
	Timestamp fechaFin;
	int id_clase;
	String username;
	int id_cuenta;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AnalisisPersonalizado() {
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
			if (filtroUsuario == true) {
				this.username = request.getParameter("username");
				consulta.add(username);
			} else {
				consulta.add(null);
			}

			if (filtroCuenta == true) {
				this.id_cuenta = Integer.parseInt(request.getParameter("cuenta"));
				consulta.add(GestorCuentasService.getInstance().getCuenta(id_cuenta).getDescripcion());
			} else {
				consulta.add(null);
			}

			request.getSession().setAttribute("consulta", consulta);

			
			GestorAnalisisService.getInstance().analisisPersonalizado(filtroFecha,false, false, filtroUsuario, filtroCuenta, null, fechaInicio, fechaFin, 0,username, id_cuenta);
			
			
			request.setAttribute("listaMeses", GestorAnalisisService.getInstance().getListaMeses());
			request.setAttribute("listaMesesIngresos", GestorAnalisisService.getInstance().getListaMesesIngresos());
			request.setAttribute("listaMesesGastos", GestorAnalisisService.getInstance().getListaMesesGastos());
			request.setAttribute("listaMesesBeneficios", GestorAnalisisService.getInstance().getListaMesesBeneficios());
			request.setAttribute("listaMovimientosYear", GestorAnalisisService.getInstance().getListaMovimientosYear());
			request.setAttribute("listaIngresosYear", GestorAnalisisService.getInstance().getListaIngresosYear());
			request.setAttribute("listaGastosYear", GestorAnalisisService.getInstance().getListaGastosYear());
			request.setAttribute("totalIngresosYear", GestorAnalisisService.getInstance().getTotalIngresosYear());
			request.setAttribute("totalGastosYear", GestorAnalisisService.getInstance().getTotalGastosYear());
			request.setAttribute("beneficioYear", GestorAnalisisService.getInstance().getBeneficioYear());
			request.setAttribute("listaMovimientosMonth", GestorAnalisisService.getInstance().getListaMovimientosMonth());
			request.setAttribute("listaIngresosMonth", GestorAnalisisService.getInstance().getListaIngresosMonth());
			request.setAttribute("listaGastosMonth", GestorAnalisisService.getInstance().getListaGastosMonth());
			request.setAttribute("totalIngresosMonth", GestorAnalisisService.getInstance().getTotalIngresosMonth());
			request.setAttribute("totalGastosMonth", GestorAnalisisService.getInstance().getTotalGastosMonth());
			request.setAttribute("beneficioMonth", GestorAnalisisService.getInstance().getBeneficioMonth());
			request.setAttribute("listaClaseIngreso", GestorMovimientosService.getInstance().getClaseIngreso());
			request.setAttribute("listaClaseGasto", GestorMovimientosService.getInstance().getClaseGasto());
			request.setAttribute("listaClaseIngresoYear", GestorAnalisisService.getInstance().getListaClaseIngresoYear());
			request.setAttribute("listaClaseIngresoMonth", GestorAnalisisService.getInstance().getListaClaseIngresoMonth());
			request.setAttribute("listaClaseGastoYear", GestorAnalisisService.getInstance().getListaClaseGastoYear());
			request.setAttribute("listaClaseGastoMonth", GestorAnalisisService.getInstance().getListaClaseGastoMonth());
			request.setAttribute("listaCuentas", GestorCuentasService.getInstance().getCuentas());
			
			
			
			request.setAttribute("listaMovimientosPersonalizado", GestorAnalisisService.getInstance().getListaMovimientosPersonalizado());
			request.setAttribute("listaIngresosPersonalizado",GestorAnalisisService.getInstance().getListaIngresosPersonalizado() );
			request.setAttribute("listaGastosPersonalizado", GestorAnalisisService.getInstance().getListaGastosPersonalizado());
			request.setAttribute("totalIngresosPersonalizado", GestorAnalisisService.getInstance().getTotalIngresosPersonalizado());
			request.setAttribute("totalGastosPersonalizado", GestorAnalisisService.getInstance().getTotalGastosPersonalizado());
			request.setAttribute("beneficioPersonalizado", GestorAnalisisService.getInstance().getBeneficioPersonalizado());
			request.setAttribute("listaClaseIngresoPersonalizado", GestorAnalisisService.getInstance().getListaClaseIngresoPersonalizado());
			request.setAttribute("listaClaseGastoPersonalizado", GestorAnalisisService.getInstance().getListaClaseGastoPersonalizado());
			


			request.getRequestDispatcher("/protected_area/analisisPersonalizado.jsp").forward(request, response);

		} else {
			System.out.println("llega a mensaje");
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

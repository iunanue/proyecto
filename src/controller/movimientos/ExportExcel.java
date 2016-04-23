package controller.movimientos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;

import model.business.GestorMovimientosService;
import model.classes.ClaseGasto;
import model.classes.ClaseIngreso;
import model.classes.Cuenta;
import model.classes.Movimiento;
import model.data.Connect;

/**
 * Servlet implementation class exportExcel
 */
@WebServlet("/protected_area/exportExcel")
public class ExportExcel extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connect c = new Connect();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExportExcel() {
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

		@SuppressWarnings("unchecked")
		List<Movimiento> listaMovimientos = (List<Movimiento>) request.getSession().getAttribute("listaMovimientos");

		for(int i=0;i<listaMovimientos.size();i++){
			System.out.println("ID: "+ listaMovimientos.get(i).getId_movimiento());
		}
		
		GestorMovimientosService.getInstance().exportExcel(listaMovimientos);

		request.getSession().setAttribute("listaMovimientos",null);
		
		request.getRequestDispatcher("index.jsp").forward(request, response);

	}

	}
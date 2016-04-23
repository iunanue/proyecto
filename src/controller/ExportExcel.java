package controller;

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

			// c.getIDao().getMovimientos();
			List<ClaseIngreso> listaClaseIngreso = c.getIDao().getClaseIngreso();
			List<ClaseGasto> listaClaseGasto = c.getIDao().getClaseGasto();
			List<Cuenta> listaCuentas = c.getIDao().getCuentas();

			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("Sample sheet");

			HSSFCellStyle cellStyle = workbook.createCellStyle();
			cellStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
			cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

			HSSFFont font = workbook.createFont();
			font.setFontHeightInPoints((short) 10);
			font.setColor(IndexedColors.BLACK.getIndex());
			font.setBold(true);
			font.setItalic(false);

			cellStyle.setFont(font);

			Row cabecera = sheet.createRow(0);

			Cell cabecera_id_movimiento = cabecera.createCell(0);
			cabecera_id_movimiento.setCellValue("id");
			cabecera_id_movimiento.setCellStyle(cellStyle);

			Cell cabecera_tipo = cabecera.createCell(1);
			cabecera_tipo.setCellValue("Tipo");
			cabecera_tipo.setCellStyle(cellStyle);

			Cell cabecera_fecha = cabecera.createCell(2);
			cabecera_fecha.setCellValue("Fecha");
			cabecera_fecha.setCellStyle(cellStyle);

			Cell cabecera_clase = cabecera.createCell(3);
			cabecera_clase.setCellValue("Clase");
			cabecera_clase.setCellStyle(cellStyle);

			Cell cabecera_usuario = cabecera.createCell(4);
			cabecera_usuario.setCellValue("Usuario");
			cabecera_usuario.setCellStyle(cellStyle);

			Cell cabecera_cuenta = cabecera.createCell(5);
			cabecera_cuenta.setCellValue("Cuenta");
			cabecera_cuenta.setCellStyle(cellStyle);

			Cell cabecera_importe = cabecera.createCell(6);
			cabecera_importe.setCellValue("Importe");
			cabecera_importe.setCellStyle(cellStyle);

			Cell cabecera_descripcion = cabecera.createCell(7);
			cabecera_descripcion.setCellValue("Descripcion");
			cabecera_descripcion.setCellStyle(cellStyle);

			float total = 0;
			
			for (int i = 1; i <= listaMovimientos.size(); i++) {
				Row row = sheet.createRow(i);

				Cell id_movimiento = row.createCell(0);
				id_movimiento.setCellValue(listaMovimientos.get(i - 1).getId_movimiento());

				Cell tipo = row.createCell(1);
				tipo.setCellValue(listaMovimientos.get(i - 1).getTipo());

				Cell fecha = row.createCell(2);
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				String date = dateFormat.format(listaMovimientos.get(i - 1).getFecha());
				fecha.setCellValue(date);

				Cell clase = row.createCell(3);
				String clase_descripcion = null;
				if (listaMovimientos.get(i - 1).getTipo().equals("Ingreso")) {
					clase_descripcion = listaClaseIngreso.get(listaMovimientos.get(i - 1).getId_clase() - 1)
							.getDescripcion();
					total = total + listaMovimientos.get(i-1).getImporte();
				}
				if (listaMovimientos.get(i - 1).getTipo().equals("Gasto")) {
					clase_descripcion = listaClaseGasto.get(listaMovimientos.get(i - 1).getId_clase() - 1)
							.getDescripcion();
					total = total - listaMovimientos.get(i-1).getImporte();
				}
				clase.setCellValue(clase_descripcion);

				Cell username = row.createCell(4);
				username.setCellValue(listaMovimientos.get(i - 1).getUsername());

				Cell id_cuenta = row.createCell(5);
				id_cuenta.setCellValue(
						listaCuentas.get(listaMovimientos.get(i - 1).getId_cuenta() - 1).getDescripcion());

				Cell importe = row.createCell(6);
				importe.setCellValue(listaMovimientos.get(i - 1).getImporte());

				Cell descripcion = row.createCell(7);
				descripcion.setCellValue(listaMovimientos.get(i - 1).getDescripcion());
				
			}
			
			Row row = sheet.createRow(listaMovimientos.size()+1);
	
			Cell a = row.createCell(0);
			a.setCellValue("");		

			Cell b = row.createCell(1);
			b.setCellValue("");
			
			Cell c = row.createCell(2);
			c.setCellValue("");
			
			Cell d = row.createCell(3);
			d.setCellValue("");
			
			Cell e = row.createCell(4);
			e.setCellValue("");
			
			Cell f = row.createCell(5);
			f.setCellValue("TOTAL");
			f.setCellStyle(cellStyle);

			HSSFCellStyle cellStyleTotal = workbook.createCellStyle();
			
			HSSFFont fontTotal = workbook.createFont();
			fontTotal.setFontHeightInPoints((short) 10);
			fontTotal.setColor(IndexedColors.BLACK.getIndex());
			fontTotal.setBold(true);
			fontTotal.setItalic(false);

			cellStyleTotal.setFont(fontTotal);
			
			Cell g = row.createCell(6);
			if(total>0){
				cellStyleTotal.setFillForegroundColor(HSSFColor.GREEN.index);
				cellStyleTotal.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				g.setCellValue("+" + total);
			}
			if(total<0){
				cellStyleTotal.setFillForegroundColor(HSSFColor.RED.index);
				cellStyleTotal.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				g.setCellValue(total);
			}
			if(total == 0){
				cellStyleTotal.setFillForegroundColor(HSSFColor.YELLOW.index);
				cellStyleTotal.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				g.setCellValue(total);
			}
			g.setCellStyle(cellStyleTotal);


			Cell h = row.createCell(7);
			h.setCellValue("");
			

			sheet.autoSizeColumn(0);
			sheet.autoSizeColumn(1);
			sheet.autoSizeColumn(2);
			sheet.autoSizeColumn(3);
			sheet.autoSizeColumn(4);
			sheet.autoSizeColumn(5);
			sheet.autoSizeColumn(6);
			sheet.autoSizeColumn(7);
			try {
				FileOutputStream out = new FileOutputStream(new File("C:\\Users\\ALUMNO\\Downloads\\movimientos.xls"));

				workbook.write(out);
				out.close();
				System.out.println("Excel written successfully..");

			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			request.getRequestDispatcher("index.jsp").forward(request, response);

		}

	}
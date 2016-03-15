package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import classes.Movimiento;
import model.Connect;

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
		
		List <Movimiento> listaMovimientos = c.getIDao().getMovimientos();
		
		
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Sample sheet");
		 
		Map<String, Object[]> data = new HashMap<String, Object[]>();
		data.put("1", new Object[] {"Emp No.", "Name", "Salary"});
		data.put("2", new Object[] {1d, "John", 1500000d});
		data.put("3", new Object[] {2d, "Sam", 800000d});
		data.put("4", new Object[] {3d, "Dean", 700000d});
		 
		
		for(int i=0;i<listaMovimientos.size();i++){
			Row row = sheet.createRow(i);
			Cell id_movimiento = row.createCell(i);
			id_movimiento.setCellValue(listaMovimientos.get(i).getId_movimiento());
			Cell tipo = row.createCell(1);
			tipo.setCellValue(listaMovimientos.get(i).getTipo());
			Cell fecha = row.createCell(2);
			fecha.setCellValue(listaMovimientos.get(i).getFecha());
			Cell id_clase = row.createCell(3);
			id_clase.setCellValue(listaMovimientos.get(i).getId_clase());
			Cell id_cuenta = row.createCell(4);
			id_cuenta.setCellValue(listaMovimientos.get(i).getId_cuenta());
			Cell importe = row.createCell(5);
			importe.setCellValue(listaMovimientos.get(i).getImporte());
			Cell descripcion = row.createCell(6);
			descripcion.setCellValue(listaMovimientos.get(i).getDescripcion());
		}
		
//		Set<String> keyset = data.keySet();
//		int rownum = 0;
//		for (String key : keyset) {
//		    Row row = sheet.createRow(rownum++);
//		    Object [] objArr = data.get(key);
//		    int cellnum = 0;
//		    for (Object obj : objArr) {
//		        Cell cell = row.createCell(cellnum++);
//		        if(obj instanceof Date) 
//		            cell.setCellValue((Date)obj);
//		        else if(obj instanceof Boolean)
//		            cell.setCellValue((Boolean)obj);
//		        else if(obj instanceof String)
//		            cell.setCellValue((String)obj);
//		        else if(obj instanceof Double)
//		            cell.setCellValue((Double)obj);
//		    }
//		}
//		 
		try {
		    FileOutputStream out = 
            new FileOutputStream(new File("C:\\Users\\ALUMNO\\Downloads\\movimientos.xls"));

		    workbook.write(out);
		    out.close();
		    System.out.println("Excel written successfully..");
		     
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
	}

}

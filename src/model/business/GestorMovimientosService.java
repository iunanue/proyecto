package model.business;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;

import model.classes.ClaseGasto;
import model.classes.ClaseIngreso;
import model.classes.Cuenta;
import model.classes.Movimiento;
import model.data.Connect;

public class GestorMovimientosService {

	private static GestorMovimientosService gestorMovimientosService = null;

	public static GestorMovimientosService getInstance() {
		if (gestorMovimientosService == null) {
			gestorMovimientosService = new GestorMovimientosService();
		}
		return gestorMovimientosService;
	}
	
	public void addMovimiento(String tipo,Timestamp fecha,int id_clase,String username,int id_cuenta,float importe,String descripcion){
		Movimiento movimiento = new Movimiento(tipo,fecha,id_clase,username,id_cuenta,importe,descripcion);
		Connect.getIDao().addMovimiento(movimiento);
	}
	
	public List<Movimiento> getMovimientos(){
		return Connect.getIDao().getMovimientos();
	}
	
	public Movimiento getMovimiento(int id_movimiento){
		return Connect.getIDao().getMovimiento(id_movimiento);
	}
	
	public void updateMovimiento(Movimiento movimientoAntiguo,Movimiento movimientoActualizado){
		Connect.getIDao().updateMovimiento(movimientoAntiguo, movimientoActualizado);
	}
	
	public void deleteMovimiento(Movimiento entity){
		Connect.getIDao().deleteMovimiento(entity);
	}
	
	public List<ClaseIngreso> getClaseIngreso(){
		return Connect.getIDao().getClaseIngreso();
	}

	public List<ClaseGasto> getClaseGasto(){
		return Connect.getIDao().getClaseGasto();
	}
	
	public void exportExcel(List<Movimiento> listaMovimientos){
		List<ClaseIngreso> listaClaseIngreso = getClaseIngreso();
		List<ClaseGasto> listaClaseGasto = getClaseGasto();
		List<Cuenta> listaCuentas = Connect.getIDao().getCuentas();

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
	}
	
}

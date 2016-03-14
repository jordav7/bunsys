package ec.com.dlc.bunsys.util.test;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import ec.com.dlc.bunsys.util.JRArrayDataSource;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

public class TestPdf {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestPdf t = new TestPdf();
		t.pdf();
	}
	
	
	
	public void pdf(){
        JasperReport jasperReport;
        JasperPrint jasperPrint;                
        try
        {
          //se carga el reporte
          URL  in=this.getClass().getResource( "/ec/com/dlc/bunsys/commons/reports/factura.jasper" );
          System.out.println("url :::: "+in.getPath());
          jasperReport=(JasperReport)JRLoader.loadObject(in);
          
          Map<String, Object> param = new HashMap<String, Object>();
          param.put("dirProvee", "test direccion prov");
          param.put("rucProvee", "1721087524");
          param.put("numDocumento", "0010010002525");
          param.put("numAutoriza", "");
          param.put("fechaAutoriza", "");
          param.put("ambiente", "");
          param.put("emision", "");
          param.put("claveAcceso", "00");
          param.put("razonProvee", "");
          param.put("razonCliente", "");
          param.put("rucCliente", "");
          param.put("fechaEmision", "");
          param.put("fechaEmiComp", "");
          param.put("motivo", "");
          param.put("numComprobante", "");
          param.put("imgLogo", "");
          param.put("totalGravCero", new BigDecimal(0));
          param.put("totalGravDoce", new BigDecimal(0));
          param.put("importeIva", new BigDecimal(0));
          param.put("total", new BigDecimal(0));
          param.put("totalGravCero", new BigDecimal(0));
          param.put("numContribuyente", "");
          param.put("master", "");
          param.put("house", "");
          param.put("airline", "");
          param.put("dae", "");
          param.put("marcacion", "");
          param.put("consignatario", "");
          
          
          //se procesa el archivo jasper
          jasperPrint = JasperFillManager.fillReport(jasperReport, param, this.createDatasourceDet() );
          //se crea el archivo PDF
<<<<<<< HEAD
          JasperExportManager.exportReportToPdfFile( jasperPrint, "C:/Users/LuisH/Desktop/RESPALDO FACTURAEL/firmas/reporte.pdf");
=======
          JasperExportManager.exportReportToPdfFile( jasperPrint, "C:/Users/DAVID/Desktop/fe/reportes/reporte.pdf");
>>>>>>> 6aad317a82996c5469498a3307afc7abb7c3e40d
        }
        catch (JRException ex)
        {
          System.err.println( "Error iReport: " + ex.getMessage() );
        }
  }
	
	public static JRDataSource createDatasourceDet(){
		
		Collection<Object[]> c = new ArrayList<Object[]>();
		c.add(new Object[]{new Integer(3), "DES PRODUCTO",new BigDecimal(0.06),new BigDecimal(3.00),new Integer(200)});
		c.add(new Object[]{new Integer(2), "DES PRODUCTO 1",new BigDecimal(0.06),new BigDecimal(3.00),new Integer(200)});
		c.add(new Object[]{new Integer(5), "DES PRODUCTO 2",new BigDecimal(0.06),new BigDecimal(3.00),new Integer(200)});

		JRDataSource dt = new JRArrayDataSource(c);

		return dt;
	}

}

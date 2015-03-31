package ec.com.dlc.bunsys.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class JRArrayDataSource implements JRDataSource, Serializable{
	private Iterator iterator = null;
	private Object currentData = null;
//	private List<DetalleUtil> listaDetalle = new ArrayList<DetalleUtil>();
	private Collection data = null;
	
	@Override
	public Object getFieldValue(JRField arg0) throws JRException {
		int index = Integer.parseInt(arg0.getDescription());
		return ((Object[])currentData)[index];
	}

	@Override
	public boolean next() throws JRException {
		// TODO Auto-generated method stub
		boolean hasNext = false;
		if (this.iterator != null){
		     hasNext = this.iterator.hasNext();
		     if (hasNext)
		    	 this.currentData = this.iterator.next();
		}
		return hasNext;
	}
	
	public JRArrayDataSource(String in) {
		in += "1,1,1,1,1";
		List<Object[]> res = new ArrayList<Object[]>();
		for(int i = 0; i < 5; i++)
			res.add(in.split(","));
		this.data = res;

		if (this.data != null){
		    this.iterator = this.data.iterator();
		}
	}
	
	public JRArrayDataSource(Collection beanCollection) {
		this.data = beanCollection;

		if (this.data != null){
		    this.iterator = this.data.iterator();
		}
	}
	

}

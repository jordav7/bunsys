package ec.com.dlc.web.factura.datamanager;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ec.com.dlc.bunsys.entity.administracion.Tadmcatalogo;
import ec.com.dlc.bunsys.entity.administracion.pk.TadmcatalogoPK;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccabfactura;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccabproforma;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccliente;
import ec.com.dlc.bunsys.entity.facturacion.Tfacdetfactura;
import ec.com.dlc.bunsys.entity.facturacion.pk.TfaccabfacturaPK;
import ec.com.dlc.bunsys.entity.facturacion.pk.TfacclientePK;
import ec.com.dlc.bunsys.entity.facturacion.pk.TfacdetfacturaPK;
import ec.com.dlc.bunsys.entity.inventario.Tinvproducto;
import ec.com.dlc.bunsys.entity.inventario.pk.TinvproductoPK;
import ec.com.dlc.bunsys.entity.seguridad.Tsyspersona;
import ec.com.dlc.web.datamanager.base.BaseDatamanager;

@ManagedBean(name="facturaDataManager")
@SessionScoped
public class FacturaDataManager extends BaseDatamanager {


	@Override
	public String getIdDatamanager() {
		return "facturaDataManager";
	}

	/**
	 * Factura para ser guaradada
	 */
	private Tfaccabfactura tfaccabfactura;
	/**
	 * lista de proformas
	 */
	private List<Tfaccabproforma>tfaccabproformaList;
	/**
	 * atributo para realizar la busqueda de las proformas
	 */
	private String numeroproforma;
	
	private boolean formaPago1;
	
    private boolean formaPago2;
	
	public Tfaccabfactura getTfaccabfactura() {
		return tfaccabfactura;
	}

	public void setTfaccabfactura(Tfaccabfactura tfaccabfactura) {
		this.tfaccabfactura = tfaccabfactura;
	}

	public List<Tfaccabproforma> getTfaccabproformaList() {
		return tfaccabproformaList;
	}

	public void setTfaccabproformaList(List<Tfaccabproforma> tfaccabproformaList) {
		this.tfaccabproformaList = tfaccabproformaList;
	}

	public String getNumeroproforma() {
		return numeroproforma;
	}

	public void setNumeroproforma(String numeroproforma) {
		this.numeroproforma = numeroproforma;
	}

	public boolean isFormaPago1() {
		return formaPago1;
	}

	public void setFormaPago1(boolean formaPago1) {
		this.formaPago1 = formaPago1;
	}

	public boolean isFormaPago2() {
		return formaPago2;
	}

	public void setFormaPago2(boolean formaPago2) {
		this.formaPago2 = formaPago2;
	}

}

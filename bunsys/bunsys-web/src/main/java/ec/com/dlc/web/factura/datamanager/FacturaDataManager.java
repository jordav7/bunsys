package ec.com.dlc.web.factura.datamanager;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
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
import ec.com.dlc.web.datamanager.login.LoginDatamanager;

@ManagedBean(name="facturaDataManager")
@SessionScoped
public class FacturaDataManager extends BaseDatamanager {


	@Override
	public String getIdDatamanager() {
		return "facturaDataManager";
	}

	@ManagedProperty(value="#{loginDatamanager}")
	private LoginDatamanager loginDatamanager;
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
    /**
     * forma de pago en efectivo
     */
    private Double cheque;
    private Double efectivo;
    private Double transferencia;
    private Double tarjetaCredito;
    /**
     * institucion bancaria
     */
    private String institucionCheque;
    private String institucionTransferencia;
    private String institucionTarjetaCredito;
    /**
     * Numero de pagos a credito
     */
    private Integer numeropagos;
    
    private Collection<Tadmcatalogo> institucion;
    
	/**
	 * 
	 * @return
	 */
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

	public Double getCheque() {
		return cheque;
	}

	public void setCheque(Double cheque) {
		this.cheque = cheque;
	}

	public Double getEfectivo() {
		return efectivo;
	}

	public void setEfectivo(Double efectivo) {
		this.efectivo = efectivo;
	}

	public Double getTransferencia() {
		return transferencia;
	}

	public void setTransferencia(Double transferencia) {
		this.transferencia = transferencia;
	}

	public Double getTarjetaCredito() {
		return tarjetaCredito;
	}

	public void setTarjetaCredito(Double tarjetaCredito) {
		this.tarjetaCredito = tarjetaCredito;
	}

	public LoginDatamanager getLoginDatamanager() {
		return loginDatamanager;
	}

	public void setLoginDatamanager(LoginDatamanager loginDatamanager) {
		this.loginDatamanager = loginDatamanager;
	}

	public String getInstitucionCheque() {
		return institucionCheque;
	}

	public void setInstitucionCheque(String institucionCheque) {
		this.institucionCheque = institucionCheque;
	}

	public String getInstitucionTransferencia() {
		return institucionTransferencia;
	}

	public void setInstitucionTransferencia(String institucionTransferencia) {
		this.institucionTransferencia = institucionTransferencia;
	}

	public String getInstitucionTarjetaCredito() {
		return institucionTarjetaCredito;
	}

	public void setInstitucionTarjetaCredito(String institucionTarjetaCredito) {
		this.institucionTarjetaCredito = institucionTarjetaCredito;
	}

	public Collection<Tadmcatalogo> getInstitucion() {
		return institucion;
	}

	public void setInstitucion(Collection<Tadmcatalogo> institucion) {
		this.institucion = institucion;
	}

	public Integer getNumeropagos() {
		return numeropagos;
	}

	public void setNumeropagos(Integer numeropagos) {
		this.numeropagos = numeropagos;
	}


}

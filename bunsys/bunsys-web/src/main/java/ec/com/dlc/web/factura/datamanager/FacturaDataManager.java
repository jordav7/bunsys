package ec.com.dlc.web.factura.datamanager;

import java.math.BigDecimal;
import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import ec.com.dlc.bunsys.entity.administracion.Tadmcatalogo;
import ec.com.dlc.bunsys.entity.administracion.Tadmcompania;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccabfactura;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccliente;
import ec.com.dlc.bunsys.entity.facturacion.Tfacdetfactura;
<<<<<<< HEAD
=======
import ec.com.dlc.bunsys.entity.facturacion.Tfacdetproforma;
>>>>>>> 6aad317a82996c5469498a3307afc7abb7c3e40d
import ec.com.dlc.bunsys.entity.inventario.Tinvproducto;
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
	
	private Tinvproducto tinvproducto;
	
	private Tfacdetfactura tfacdetfactura;
	
	private Tfaccliente tfaccliente;
	
	private Tadmcompania tadmcompania ;
	
	/**
	 * Aerolineas
	 */
	private Collection<Tadmcatalogo> aerolineasCatalogo;
	/**
	 * pices type
	 */
	private Collection<Tadmcatalogo> catalogoPicesType;
	
	/**
	 * fob
	 */
	private Collection<Tadmcatalogo> catalogofob;
	/**
	 * carguera
	 */
	private Collection<Tadmcatalogo> catalogocarguera;
	/**
	 * distriti vuelo
	 */
	private Collection<Tadmcatalogo> catalogodistritovuelo;
	
	private boolean formaPago1;
	
    private boolean formaPago2;
    /**
     * forma de pago en efectivo
     */
    private BigDecimal cheque;
    private BigDecimal efectivo;
    private BigDecimal transferencia;
    private BigDecimal tarjetaCredito;
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
    
    private String AccionAux;
    
    private Boolean editable;
    
    private String numeroproforma;
    
    private Collection<Tfacdetfactura>detfacturaEliminar;
    
    private String password;
    
	public Tfaccabfactura getTfaccabfactura() {
		return tfaccabfactura;
	}

	public void setTfaccabfactura(Tfaccabfactura tfaccabfactura) {
		this.tfaccabfactura = tfaccabfactura;
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

	public BigDecimal getCheque() {
		return cheque;
	}

	public void setCheque(BigDecimal cheque) {
		this.cheque = cheque;
	}

	public BigDecimal getEfectivo() {
		return efectivo;
	}

	public void setEfectivo(BigDecimal efectivo) {
		this.efectivo = efectivo;
	}

	public BigDecimal getTransferencia() {
		return transferencia;
	}

	public void setTransferencia(BigDecimal transferencia) {
		this.transferencia = transferencia;
	}

	public BigDecimal getTarjetaCredito() {
		return tarjetaCredito;
	}

	public void setTarjetaCredito(BigDecimal tarjetaCredito) {
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

	public String getAccionAux() {
		return AccionAux;
	}

	public void setAccionAux(String accionAux) {
		AccionAux = accionAux;
	}

	public Boolean getEditable() {
		return editable;
	}

	public void setEditable(Boolean editable) {
		this.editable = editable;
	}

	public Tinvproducto getTinvproducto() {
		return tinvproducto;
	}

	public void setTinvproducto(Tinvproducto tinvproducto) {
		this.tinvproducto = tinvproducto;
	}

	public Tfacdetfactura getTfacdetfactura() {
		return tfacdetfactura;
	}

	public void setTfacdetfactura(Tfacdetfactura tfacdetfactura) {
		this.tfacdetfactura = tfacdetfactura;
	}

	public Tfaccliente getTfaccliente() {
		return tfaccliente;
	}

	public void setTfaccliente(Tfaccliente tfaccliente) {
		this.tfaccliente = tfaccliente;
	}

	public Collection<Tadmcatalogo> getAerolineasCatalogo() {
		return aerolineasCatalogo;
	}

	public void setAerolineasCatalogo(Collection<Tadmcatalogo> aerolineasCatalogo) {
		this.aerolineasCatalogo = aerolineasCatalogo;
	}

	public Collection<Tadmcatalogo> getCatalogoPicesType() {
		return catalogoPicesType;
	}

	public void setCatalogoPicesType(Collection<Tadmcatalogo> catalogoPicesType) {
		this.catalogoPicesType = catalogoPicesType;
	}

	public String getNumeroproforma() {
		return numeroproforma;
	}

	public void setNumeroproforma(String numeroproforma) {
		this.numeroproforma = numeroproforma;
	}

	public Tadmcompania getTadmcompania() {
		return tadmcompania;
	}

	public void setTadmcompania(Tadmcompania tadmcompania) {
		this.tadmcompania = tadmcompania;
	}

	public Collection<Tfacdetfactura> getDetfacturaEliminar() {
		return detfacturaEliminar;
	}

	public void setDetfacturaEliminar(Collection<Tfacdetfactura> detfacturaEliminar) {
		this.detfacturaEliminar = detfacturaEliminar;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<Tadmcatalogo> getCatalogofob() {
		return catalogofob;
	}

	public void setCatalogofob(Collection<Tadmcatalogo> catalogofob) {
		this.catalogofob = catalogofob;
	}

	public Collection<Tadmcatalogo> getCatalogocarguera() {
		return catalogocarguera;
	}

	public void setCatalogocarguera(Collection<Tadmcatalogo> catalogocarguera) {
		this.catalogocarguera = catalogocarguera;
	}

	public Collection<Tadmcatalogo> getCatalogodistritovuelo() {
		return catalogodistritovuelo;
	}

	public void setCatalogodistritovuelo(
			Collection<Tadmcatalogo> catalogodistritovuelo) {
		this.catalogodistritovuelo = catalogodistritovuelo;
	}


}

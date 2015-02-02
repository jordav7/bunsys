package ec.com.dlc.bunsys.entity.facturacion;

import java.io.Serializable;

import javax.persistence.*;

import ec.com.dlc.bunsys.entity.facturacion.pk.TfacdetdevolucionePK;

import java.math.BigDecimal;


/**
 * The persistent class for the tfacdetdevoluciones database table.
 * 
 */
@Entity
@Table(name="tfacdetdevoluciones")
@NamedQuery(name="Tfacdetdevolucione.findAll", query="SELECT t FROM Tfacdetdevolucione t")
public class Tfacdetdevolucione extends ec.com.dlc.bunsys.entity.base.BaseEntity<T>  {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TfacdetdevolucionePK id;

	private String apta;

	private Integer aptacodigo;

	private BigDecimal cantidad;

	private String codigoproductos;

	private BigDecimal descuento;

	private String ice;

	private Integer icecodigo;

	private String irbpnr;

	private Integer irbpnrcodigo;

	private String iva;

	private Integer ivacodigo;

	private String nandina;

	private BigDecimal preciounitario;

	private String unidadventa;

	private Integer unidadventacodigo;

	//bi-directional many-to-one association to Tfaccabdevolucione
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="codigocompania", referencedColumnName="codigocompania"),
		@JoinColumn(name="numerodevoluciones", referencedColumnName="numerodevoluciones")
		})
	private Tfaccabdevolucione tfaccabdevolucione;

	public Tfacdetdevolucione() {
	}

	public TfacdetdevolucionePK getId() {
		return this.id;
	}

	public void setId(TfacdetdevolucionePK id) {
		this.id = id;
	}

	public String getApta() {
		return this.apta;
	}

	public void setApta(String apta) {
		this.apta = apta;
	}

	public Integer getAptacodigo() {
		return this.aptacodigo;
	}

	public void setAptacodigo(Integer aptacodigo) {
		this.aptacodigo = aptacodigo;
	}

	public BigDecimal getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	public String getCodigoproductos() {
		return this.codigoproductos;
	}

	public void setCodigoproductos(String codigoproductos) {
		this.codigoproductos = codigoproductos;
	}

	public BigDecimal getDescuento() {
		return this.descuento;
	}

	public void setDescuento(BigDecimal descuento) {
		this.descuento = descuento;
	}

	public String getIce() {
		return this.ice;
	}

	public void setIce(String ice) {
		this.ice = ice;
	}

	public Integer getIcecodigo() {
		return this.icecodigo;
	}

	public void setIcecodigo(Integer icecodigo) {
		this.icecodigo = icecodigo;
	}

	public String getIrbpnr() {
		return this.irbpnr;
	}

	public void setIrbpnr(String irbpnr) {
		this.irbpnr = irbpnr;
	}

	public Integer getIrbpnrcodigo() {
		return this.irbpnrcodigo;
	}

	public void setIrbpnrcodigo(Integer irbpnrcodigo) {
		this.irbpnrcodigo = irbpnrcodigo;
	}

	public String getIva() {
		return this.iva;
	}

	public void setIva(String iva) {
		this.iva = iva;
	}

	public Integer getIvacodigo() {
		return this.ivacodigo;
	}

	public void setIvacodigo(Integer ivacodigo) {
		this.ivacodigo = ivacodigo;
	}

	public String getNandina() {
		return this.nandina;
	}

	public void setNandina(String nandina) {
		this.nandina = nandina;
	}

	public BigDecimal getPreciounitario() {
		return this.preciounitario;
	}

	public void setPreciounitario(BigDecimal preciounitario) {
		this.preciounitario = preciounitario;
	}

	public String getUnidadventa() {
		return this.unidadventa;
	}

	public void setUnidadventa(String unidadventa) {
		this.unidadventa = unidadventa;
	}

	public Integer getUnidadventacodigo() {
		return this.unidadventacodigo;
	}

	public void setUnidadventacodigo(Integer unidadventacodigo) {
		this.unidadventacodigo = unidadventacodigo;
	}

	public Tfaccabdevolucione getTfaccabdevolucione() {
		return this.tfaccabdevolucione;
	}

	public void setTfaccabdevolucione(Tfaccabdevolucione tfaccabdevolucione) {
		this.tfaccabdevolucione = tfaccabdevolucione;
	}

}
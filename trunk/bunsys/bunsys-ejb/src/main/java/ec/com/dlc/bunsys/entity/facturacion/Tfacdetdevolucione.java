package ec.com.dlc.bunsys.entity.facturacion;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import ec.com.dlc.bunsys.entity.administracion.Tadmcatalogo;
import ec.com.dlc.bunsys.entity.base.BaseEntity;
import ec.com.dlc.bunsys.entity.facturacion.pk.TfacdetdevolucionePK;
import ec.com.dlc.bunsys.entity.inventario.Tinvproducto;


/**
 * The persistent class for the tfacdetdevoluciones database table.
 * 
 */
@Entity
@Table(name="tfacdetdevoluciones")
@NamedQuery(name="Tfacdetdevolucione.findAll", query="SELECT t FROM Tfacdetdevolucione t")
public class Tfacdetdevolucione extends BaseEntity<TfacdetdevolucionePK>  {
	private static final long serialVersionUID = 1L;

	@Column
	private String apta;

	@Column
	private Integer aptacodigo;

	@Column
	private BigDecimal cantidad;

	@Column
	private String codigoproductos;

	@Column
	private BigDecimal descuento;

	@Column
	private String ice;

	@Column
	private Integer icecodigo;

	@Column
	private String irbpnr;

	@Column
	private Integer irbpnrcodigo;

	@Column
	private String iva;

	@Column
	private Integer ivacodigo;

	@Column
	private String nandina;

	@Column
	private BigDecimal preciounitario;

	@Column
	private String unidadventa;

	@Column
	private Integer unidadventacodigo;

	//bi-directional many-to-one association to Tfaccabdevolucione
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="codigocompania", referencedColumnName="codigocompania"),
		@JoinColumn(name="numerodevoluciones", referencedColumnName="numerodevoluciones")
		})
	private Tfaccabdevolucione tfaccabdevolucione;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="apta", referencedColumnName="codigocatalogo"),
		@JoinColumn(name="aptacodigo", referencedColumnName="codigotipocatalogo"),
		@JoinColumn(name="codigocompania", referencedColumnName="codigocompania")
		})
	private Tadmcatalogo tadmatpa;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="unidadventa", referencedColumnName="codigocatalogo"),
		@JoinColumn(name="unidadventacodigo", referencedColumnName="codigotipocatalogo"),
		@JoinColumn(name="codigocompania", referencedColumnName="codigocompania")
		})
	private Tadmcatalogo tadmunidadventa;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="iva", referencedColumnName="codigocatalogo"),
		@JoinColumn(name="ivacodigo", referencedColumnName="codigotipocatalogo"),
		@JoinColumn(name="codigocompania", referencedColumnName="codigocompania")
		})
	private Tadmcatalogo tadmiva;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="ice", referencedColumnName="codigocatalogo"),
		@JoinColumn(name="icecodigo", referencedColumnName="codigotipocatalogo"),
		@JoinColumn(name="codigocompania", referencedColumnName="codigocompania")
		})
	private Tadmcatalogo tadmice;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="irbpnr", referencedColumnName="codigocatalogo"),
		@JoinColumn(name="irbpnrcodigo", referencedColumnName="codigotipocatalogo"),
		@JoinColumn(name="codigocompania", referencedColumnName="codigocompania")
		})
	private Tadmcatalogo tadmirbpnr;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="codigoproductos", referencedColumnName="codigoproductos"),
		@JoinColumn(name="codigocompania", referencedColumnName="codigocompania")
		})
	private Tinvproducto tinvproducto;
	
	public Tfacdetdevolucione() {
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

	public Tadmcatalogo getTadmatpa() {
		return tadmatpa;
	}

	public void setTadmatpa(Tadmcatalogo tadmatpa) {
		this.tadmatpa = tadmatpa;
	}

	public Tadmcatalogo getTadmunidadventa() {
		return tadmunidadventa;
	}

	public void setTadmunidadventa(Tadmcatalogo tadmunidadventa) {
		this.tadmunidadventa = tadmunidadventa;
	}

	public Tadmcatalogo getTadmiva() {
		return tadmiva;
	}

	public void setTadmiva(Tadmcatalogo tadmiva) {
		this.tadmiva = tadmiva;
	}

	public Tadmcatalogo getTadmice() {
		return tadmice;
	}

	public void setTadmice(Tadmcatalogo tadmice) {
		this.tadmice = tadmice;
	}

	public Tadmcatalogo getTadmirbpnr() {
		return tadmirbpnr;
	}

	public void setTadmirbpnr(Tadmcatalogo tadmirbpnr) {
		this.tadmirbpnr = tadmirbpnr;
	}

	public Tinvproducto getTinvproducto() {
		return tinvproducto;
	}

	public void setTinvproducto(Tinvproducto tinvproducto) {
		this.tinvproducto = tinvproducto;
	}

}
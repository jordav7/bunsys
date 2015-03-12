package ec.com.dlc.bunsys.entity.facturacion;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import ec.com.dlc.bunsys.commons.listener.GenerateIdListener;
import ec.com.dlc.bunsys.entity.administracion.Tadmcatalogo;
import ec.com.dlc.bunsys.entity.base.BaseEntity;
import ec.com.dlc.bunsys.entity.facturacion.pk.TfacdetfacturaPK;
import ec.com.dlc.bunsys.entity.inventario.Tinvproducto;


/**
 * The persistent class for the tfacdetfactura database table.
 * 
 */
@Entity
@EntityListeners(GenerateIdListener.class)
@Table(name="tfacdetfactura")
public class Tfacdetfactura extends BaseEntity<TfacdetfacturaPK> {
	private static final long serialVersionUID = 1L;
	
	@Column
	private String unidadventa;
	@Column
	private Integer unidadventacodigo;
	@Column
	private String iva;
	@Column
	private Integer ivacodigo;
	@Column
	private String ice;
	@Column
	private Integer icecodigo;
	@Column
	private String irbpnr;
	@Column
	private Integer irbpnrcodigo;
	@Column
	private String atpa;
	@Column
	private Integer atpacodigo;
	@Column
	private String numerofactura;
	@Column
	private String codigoproductos;
	@Column
	private Double cantidad;
	@Column
	private Double preciounitario;
	@Column
	private BigDecimal descuento;
	@Column
	private String nandina;
	@Column
	private Double eqfullboxes;
	@Column
	private Double stemsbunch;
	@Column
	private Double totalbunch;
	@Column
	private Double totalstems;
	@Column
	private Double total;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="atpa", referencedColumnName="codigocatalogo", insertable=false, updatable=false),
		@JoinColumn(name="atpacodigo", referencedColumnName="codigotipocatalogo",insertable=false, updatable=false),
		@JoinColumn(name="codigocompania", referencedColumnName="codigocompania",insertable=false, updatable=false)
		})
	private Tadmcatalogo tadmatpa;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="unidadventa", referencedColumnName="codigocatalogo",insertable=false, updatable=false),
		@JoinColumn(name="unidadventacodigo", referencedColumnName="codigotipocatalogo",insertable=false, updatable=false),
		@JoinColumn(name="codigocompania", referencedColumnName="codigocompania",insertable=false, updatable=false)
		})
	private Tadmcatalogo tadmunidadventa;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="iva", referencedColumnName="codigocatalogo",insertable=false, updatable=false),
		@JoinColumn(name="ivacodigo", referencedColumnName="codigotipocatalogo",insertable=false, updatable=false),
		@JoinColumn(name="codigocompania", referencedColumnName="codigocompania",insertable=false, updatable=false)
		})
	private Tadmcatalogo tadmiva;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="ice", referencedColumnName="codigocatalogo",insertable=false, updatable=false),
		@JoinColumn(name="icecodigo", referencedColumnName="codigotipocatalogo",insertable=false, updatable=false),
		@JoinColumn(name="codigocompania", referencedColumnName="codigocompania",insertable=false, updatable=false)
		})
	private Tadmcatalogo tadmice;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="irbpnr", referencedColumnName="codigocatalogo",insertable=false, updatable=false),
		@JoinColumn(name="irbpnrcodigo", referencedColumnName="codigotipocatalogo",insertable=false, updatable=false),
		@JoinColumn(name="codigocompania", referencedColumnName="codigocompania",insertable=false, updatable=false)
		})
	private Tadmcatalogo tadmirbpnr;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="codigoproductos", referencedColumnName="codigoproductos",insertable=false, updatable=false),
		@JoinColumn(name="codigocompania", referencedColumnName="codigocompania",insertable=false, updatable=false)
		})
	private Tinvproducto tinvproducto;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="numerofactura", referencedColumnName="numerofactura",insertable=false, updatable=false),
		@JoinColumn(name="codigocompania", referencedColumnName="codigocompania",insertable=false, updatable=false)
		})
	private Tfaccabfactura tfaccabfactura;
	
	public Tfacdetfactura() {
		this.pk = new TfacdetfacturaPK();
	}

	public String getUnidadventa() {
		return unidadventa;
	}

	public void setUnidadventa(String unidadventa) {
		this.unidadventa = unidadventa;
	}

	public Integer getUnidadventacodigo() {
		return unidadventacodigo;
	}

	public void setUnidadventacodigo(Integer unidadventacodigo) {
		this.unidadventacodigo = unidadventacodigo;
	}

	public String getIva() {
		return iva;
	}

	public void setIva(String iva) {
		this.iva = iva;
	}

	public Integer getIvacodigo() {
		return ivacodigo;
	}

	public void setIvacodigo(Integer ivacodigo) {
		this.ivacodigo = ivacodigo;
	}

	public String getIce() {
		return ice;
	}

	public void setIce(String ice) {
		this.ice = ice;
	}

	public Integer getIcecodigo() {
		return icecodigo;
	}

	public void setIcecodigo(Integer icecodigo) {
		this.icecodigo = icecodigo;
	}

	public String getIrbpnr() {
		return irbpnr;
	}

	public void setIrbpnr(String irbpnr) {
		this.irbpnr = irbpnr;
	}

	public Integer getIrbpnrcodigo() {
		return irbpnrcodigo;
	}

	public void setIrbpnrcodigo(Integer irbpnrcodigo) {
		this.irbpnrcodigo = irbpnrcodigo;
	}

	public String getAtpa() {
		return atpa;
	}

	public void setAtpa(String atpa) {
		this.atpa = atpa;
	}

	public Integer getAtpacodigo() {
		return atpacodigo;
	}

	public void setAtpacodigo(Integer atpacodigo) {
		this.atpacodigo = atpacodigo;
	}

	public String getNumerofactura() {
		return numerofactura;
	}

	public void setNumerofactura(String numerofactura) {
		this.numerofactura = numerofactura;
	}

	public String getCodigoproductos() {
		return codigoproductos;
	}

	public void setCodigoproductos(String codigoproductos) {
		this.codigoproductos = codigoproductos;
	}

	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	public Double getPreciounitario() {
		return preciounitario;
	}

	public void setPreciounitario(Double preciounitario) {
		this.preciounitario = preciounitario;
	}

	public BigDecimal getDescuento() {
		return descuento;
	}

	public void setDescuento(BigDecimal descuento) {
		this.descuento = descuento;
	}

	public String getNandina() {
		return nandina;
	}

	public void setNandina(String nandina) {
		this.nandina = nandina;
	}

	public Double getEqfullboxes() {
		return eqfullboxes;
	}

	public void setEqfullboxes(Double eqfullboxes) {
		this.eqfullboxes = eqfullboxes;
	}

	public Double getStemsbunch() {
		return stemsbunch;
	}

	public void setStemsbunch(Double stemsbunch) {
		this.stemsbunch = stemsbunch;
	}

	public Double getTotalbunch() {
		return totalbunch;
	}

	public void setTotalbunch(Double totalbunch) {
		this.totalbunch = totalbunch;
	}

	public Double getTotalstems() {
		return totalstems;
	}

	public void setTotalstems(Double totalstems) {
		this.totalstems = totalstems;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
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

	public Tfaccabfactura getTfaccabfactura() {
		return tfaccabfactura;
	}

	public void setTfaccabfactura(Tfaccabfactura tfaccabfactura) {
		this.tfaccabfactura = tfaccabfactura;
	}
	
}
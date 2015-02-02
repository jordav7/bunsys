package ec.com.dlc.bunsys.entity.inventario;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ec.com.dlc.bunsys.entity.administracion.Tadmcatalogo;
import ec.com.dlc.bunsys.entity.base.BaseEntity;
import ec.com.dlc.bunsys.entity.inventario.pk.TinvproductoPK;


/**
 * The persistent class for the tinvproducto database table.
 * 
 */
@Entity
public class Tinvproducto  extends BaseEntity<TinvproductoPK>{
	private static final long serialVersionUID = 1L;

	@Column
	private String atpa;

	@Column
	private Integer atpacodigo;

	@Column
	private String codigoauxiliar;
	
	@Column
	private String codigoprincipal;

	@Column
	private String color;

	@Column
	private Integer colorcodigo;

	@Column
	private String estado;

	@Column
	private Integer estadocodigo;

	@Column
	@Temporal(TemporalType.DATE)
	private Date fechacaducidad;

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
	private String nombre;

	@Column
	private Double peso;

	@Column
	private Double preciounitario;

	@Column
	private String tipoproducto;

	@Column
	private Integer tipoproductocodigo;

	@Column
	private String unidadcompra;

	@Column
	private Integer unidadcompracodigo;

	@Column
	private String unidadventa;

	@Column
	private Integer unidadventacodigo;
	
	@Column
	private String nandina;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="codigocompania", referencedColumnName="codigocompania", insertable=false, updatable=false),
		@JoinColumn(name="iva", referencedColumnName="codigocatalogo", insertable=false, updatable=false),
		@JoinColumn(name="ivacodigo", referencedColumnName="codigotipocatalogo", insertable=false, updatable=false)
	})
	private Tadmcatalogo tadmiva;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="codigocompania", referencedColumnName="codigocompania", insertable=false, updatable=false),
		@JoinColumn(name="ice", referencedColumnName="codigocatalogo", insertable=false, updatable=false),
		@JoinColumn(name="icecodigo", referencedColumnName="codigotipocatalogo", insertable=false, updatable=false)
	})
	private Tadmcatalogo tadmice;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="codigocompania", referencedColumnName="codigocompania", insertable=false, updatable=false),
		@JoinColumn(name="irbpnr", referencedColumnName="codigocatalogo", insertable=false, updatable=false),
		@JoinColumn(name="irbpnrcodigo", referencedColumnName="codigotipocatalogo", insertable=false, updatable=false)
	})
	private Tadmcatalogo tadmirbpnr;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="codigocompania", referencedColumnName="codigocompania", insertable=false, updatable=false),
		@JoinColumn(name="tipoproducto", referencedColumnName="codigocatalogo", insertable=false, updatable=false),
		@JoinColumn(name="tipoproductocodigo", referencedColumnName="codigotipocatalogo", insertable=false, updatable=false)
	})
	private Tadmcatalogo tadmtipoproducto;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="codigocompania", referencedColumnName="codigocompania", insertable=false, updatable=false),
		@JoinColumn(name="unidadcompra", referencedColumnName="codigocatalogo", insertable=false, updatable=false),
		@JoinColumn(name="unidadcompracodigo", referencedColumnName="codigotipocatalogo", insertable=false, updatable=false)
	})
	private Tadmcatalogo tadmunidadcompra;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="codigocompania", referencedColumnName="codigocompania", insertable=false, updatable=false),
		@JoinColumn(name="color", referencedColumnName="codigocatalogo", insertable=false, updatable=false),
		@JoinColumn(name="colorcodigo", referencedColumnName="codigotipocatalogo", insertable=false, updatable=false)
	})
	private Tadmcatalogo tadmcolor;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="codigocompania", referencedColumnName="codigocompania", insertable=false, updatable=false),
		@JoinColumn(name="estado", referencedColumnName="codigocatalogo", insertable=false, updatable=false),
		@JoinColumn(name="estadocodigo", referencedColumnName="codigotipocatalogo", insertable=false, updatable=false)
	})
	private Tadmcatalogo tadmestado;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="codigocompania", referencedColumnName="codigocompania", insertable=false, updatable=false),
		@JoinColumn(name="unidadventa", referencedColumnName="codigocatalogo", insertable=false, updatable=false),
		@JoinColumn(name="unidadventacodigo", referencedColumnName="codigotipocatalogo", insertable=false, updatable=false)
	})
	private Tadmcatalogo tadmunidadventa;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="codigocompania", referencedColumnName="codigocompania", insertable=false, updatable=false),
		@JoinColumn(name="atpa", referencedColumnName="codigocatalogo", insertable=false, updatable=false),
		@JoinColumn(name="atpacodigo", referencedColumnName="codigotipocatalogo", insertable=false, updatable=false)
	})
	private Tadmcatalogo tadmatpa;
	
	public Tinvproducto() {
		this.pk = new TinvproductoPK();
	}

	public String getAtpa() {
		return this.atpa;
	}

	public void setAtpa(String atpa) {
		this.atpa = atpa;
	}

	public Integer getAtpacodigo() {
		return this.atpacodigo;
	}

	public void setAtpacodigo(Integer atpacodigo) {
		this.atpacodigo = atpacodigo;
	}

	public String getCodigoauxiliar() {
		return this.codigoauxiliar;
	}

	public void setCodigoauxiliar(String codigoauxiliar) {
		this.codigoauxiliar = codigoauxiliar;
	}

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getColorcodigo() {
		return this.colorcodigo;
	}

	public void setColorcodigo(Integer colorcodigo) {
		this.colorcodigo = colorcodigo;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getEstadocodigo() {
		return this.estadocodigo;
	}

	public void setEstadocodigo(Integer estadocodigo) {
		this.estadocodigo = estadocodigo;
	}

	public Date getFechacaducidad() {
		return this.fechacaducidad;
	}

	public void setFechacaducidad(Date fechacaducidad) {
		this.fechacaducidad = fechacaducidad;
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

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getPeso() {
		return this.peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public Double getPreciounitario() {
		return this.preciounitario;
	}

	public void setPreciounitario(Double preciounitario) {
		this.preciounitario = preciounitario;
	}

	public String getTipoproducto() {
		return this.tipoproducto;
	}

	public void setTipoproducto(String tipoproducto) {
		this.tipoproducto = tipoproducto;
	}

	public Integer getTipoproductocodigo() {
		return this.tipoproductocodigo;
	}

	public void setTipoproductocodigo(Integer tipoproductocodigo) {
		this.tipoproductocodigo = tipoproductocodigo;
	}

	public String getUnidadcompra() {
		return this.unidadcompra;
	}

	public void setUnidadcompra(String unidadcompra) {
		this.unidadcompra = unidadcompra;
	}

	public Integer getUnidadcompracodigo() {
		return this.unidadcompracodigo;
	}

	public void setUnidadcompracodigo(Integer unidadcompracodigo) {
		this.unidadcompracodigo = unidadcompracodigo;
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

	public Tadmcatalogo getTadmtipoproducto() {
		return tadmtipoproducto;
	}

	public void setTadmtipoproducto(Tadmcatalogo tadmtipoproducto) {
		this.tadmtipoproducto = tadmtipoproducto;
	}

	public Tadmcatalogo getTadmunidadcompra() {
		return tadmunidadcompra;
	}

	public void setTadmunidadcompra(Tadmcatalogo tadmunidadcompra) {
		this.tadmunidadcompra = tadmunidadcompra;
	}

	public Tadmcatalogo getTadmcolor() {
		return tadmcolor;
	}

	public void setTadmcolor(Tadmcatalogo tadmcolor) {
		this.tadmcolor = tadmcolor;
	}

	public Tadmcatalogo getTadmestado() {
		return tadmestado;
	}

	public void setTadmestado(Tadmcatalogo tadmestado) {
		this.tadmestado = tadmestado;
	}

	public Tadmcatalogo getTadmunidadventa() {
		return tadmunidadventa;
	}

	public void setTadmunidadventa(Tadmcatalogo tadmunidadventa) {
		this.tadmunidadventa = tadmunidadventa;
	}

	public Tadmcatalogo getTadmatpa() {
		return tadmatpa;
	}

	public void setTadmatpa(Tadmcatalogo tadmatpa) {
		this.tadmatpa = tadmatpa;
	}

	public String getCodigoprincipal() {
		return codigoprincipal;
	}

	public void setCodigoprincipal(String codigoprincipal) {
		this.codigoprincipal = codigoprincipal;
	}

	public String getNandina() {
		return nandina;
	}

	public void setNandina(String nandina) {
		this.nandina = nandina;
	}

}
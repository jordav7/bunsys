package ec.com.dlc.bunsys.entity.facturacion;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ec.com.dlc.bunsys.entity.administracion.Tadmcatalogo;
import ec.com.dlc.bunsys.entity.administracion.Tadmparamsri;
import ec.com.dlc.bunsys.entity.base.BaseEntity;
import ec.com.dlc.bunsys.entity.facturacion.pk.TfaccabfacturaPK;


/**
 * The persistent class for the tfaccabfactura database table.
 * 
 */
@Entity
@Table(name="tfaccabfactura")
public class Tfaccabfactura extends BaseEntity<TfaccabfacturaPK> {
	private static final long serialVersionUID = 1L;

	@Column
	private String codigocliente;
	
	@Column
	private String estado;

	@Column
	private Integer estadocodigo;

	@Column
	@Temporal(TemporalType.DATE)
	private Date fechafactura;

	@Column
	private BigDecimal total;
	
	@Column
	private BigDecimal subtotalneto;
	
	@Column
	private BigDecimal subtotaliva;
	
	@Column
	private BigDecimal subtotalbase;
	
	@Column
	private BigDecimal subtotalnoiva;
	
	@Column
	private BigDecimal subtotalexcentoiva;
	
	@Column
	private BigDecimal porcentajedesc;
	
	@Column
	private BigDecimal totaldescuento;
	
	@Column
	private BigDecimal porcentajeice;
	
	@Column
	private BigDecimal valorice;
	
	@Column
	private BigDecimal porcentajeirbpnr;
	
	@Column
	private BigDecimal porcentajeiva;
	
	@Column
	private BigDecimal iva;
	
	@Column
	private String countrycode;
	
	@Column
	private String area;
	
	@Column
	private BigDecimal valorirbpnr;
	
	@Column
	private String estadosri;

	@Column
	private Integer estadosricodigo;
	
	@Column
	private String numeroproforma;
	
	@Column
	private String claveacceso;
	
	@Column
	private String fob ;
	
	@Column
	private Integer  fobcodigo ;
	
    
	public Tfaccabfactura() {
		this.pk = new TfaccabfacturaPK();
	}


	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="estado", referencedColumnName="codigocatalogo", insertable=false, updatable=false),
		@JoinColumn(name="estadocodigo", referencedColumnName="codigotipocatalogo", insertable=false, updatable=false),
		@JoinColumn(name="codigocompania", referencedColumnName="codigocompania", insertable=false, updatable=false)
	})
	private Tadmcatalogo tadmestado;


	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="codigocliente", referencedColumnName="codigocliente", insertable=false, updatable=false),
		@JoinColumn(name="codigocompania", referencedColumnName="codigocompania", insertable=false, updatable=false)
	})
	private Tfaccliente tfaccliente;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="estadosri", referencedColumnName="codigoparamsri", insertable=false, updatable=false),
		@JoinColumn(name="estadosricodigo", referencedColumnName="codigotipoparamsri", insertable=false, updatable=false),
	})
	private Tadmparamsri tadmestadosri;

	//bi-directional many-to-one association to Tfacdetdevolucione
	@OneToMany(mappedBy="tfaccabfactura")
	private Collection<Tfacdetfactura> tfacdetfacturas;

	//bi-directional many-to-one association to Tfacdetdevolucione
	@OneToMany(mappedBy="tfaccabfactura")
	private Collection<Tfacformapago> tfacformapagos;
	
	//bi-directional many-to-one association to Tfacdetdevolucione
	@OneToMany(mappedBy="tfaccabfactura")
	private Collection<Tfaccuentasxcobrar>tfaccuentasxcobrars;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="fob", referencedColumnName="codigocatalogo", insertable=false, updatable=false),
		@JoinColumn(name="fobcodigo", referencedColumnName="codigotipocatalogo", insertable=false, updatable=false),
		@JoinColumn(name="codigocompania", referencedColumnName="codigocompania", insertable=false, updatable=false)
	})
	private Tadmcatalogo tadmfob;


	public String getCodigocliente() {
		return codigocliente;
	}

	public void setCodigocliente(String codigocliente) {
		this.codigocliente = codigocliente;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getEstadocodigo() {
		return estadocodigo;
	}

	public void setEstadocodigo(Integer estadocodigo) {
		this.estadocodigo = estadocodigo;
	}

	public Date getFechafactura() {
		return fechafactura;
	}

	public void setFechafactura(Date fechafactura) {
		this.fechafactura = fechafactura;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getSubtotalneto() {
		return subtotalneto;
	}

	public void setSubtotalneto(BigDecimal subtotalneto) {
		this.subtotalneto = subtotalneto;
	}

	public BigDecimal getSubtotaliva() {
		return subtotaliva;
	}

	public void setSubtotaliva(BigDecimal subtotaliva) {
		this.subtotaliva = subtotaliva;
	}

	public BigDecimal getSubtotalbase() {
		return subtotalbase;
	}

	public void setSubtotalbase(BigDecimal subtotalbase) {
		this.subtotalbase = subtotalbase;
	}

	public BigDecimal getSubtotalnoiva() {
		return subtotalnoiva;
	}

	public void setSubtotalnoiva(BigDecimal subtotalnoiva) {
		this.subtotalnoiva = subtotalnoiva;
	}

	public BigDecimal getSubtotalexcentoiva() {
		return subtotalexcentoiva;
	}

	public void setSubtotalexcentoiva(BigDecimal subtotalexcentoiva) {
		this.subtotalexcentoiva = subtotalexcentoiva;
	}

	public BigDecimal getPorcentajedesc() {
		return porcentajedesc;
	}

	public void setPorcentajedesc(BigDecimal porcentajedesc) {
		this.porcentajedesc = porcentajedesc;
	}

	public BigDecimal getTotaldescuento() {
		return totaldescuento;
	}

	public void setTotaldescuento(BigDecimal totaldescuento) {
		this.totaldescuento = totaldescuento;
	}

	public BigDecimal getPorcentajeice() {
		return porcentajeice;
	}

	public void setPorcentajeice(BigDecimal porcentajeice) {
		this.porcentajeice = porcentajeice;
	}

	public BigDecimal getValorice() {
		return valorice;
	}

	public void setValorice(BigDecimal valorice) {
		this.valorice = valorice;
	}

	public BigDecimal getPorcentajeirbpnr() {
		return porcentajeirbpnr;
	}

	public void setPorcentajeirbpnr(BigDecimal porcentajeirbpnr) {
		this.porcentajeirbpnr = porcentajeirbpnr;
	}

	public BigDecimal getPorcentajeiva() {
		return porcentajeiva;
	}

	public void setPorcentajeiva(BigDecimal porcentajeiva) {
		this.porcentajeiva = porcentajeiva;
	}

	public BigDecimal getIva() {
		return iva;
	}

	public void setIva(BigDecimal iva) {
		this.iva = iva;
	}

	public String getCountrycode() {
		return countrycode;
	}

	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public BigDecimal getValorirbpnr() {
		return valorirbpnr;
	}

	public void setValorirbpnr(BigDecimal valorirbpnr) {
		this.valorirbpnr = valorirbpnr;
	}

	public String getEstadosri() {
		return estadosri;
	}

	public void setEstadosri(String estadosri) {
		this.estadosri = estadosri;
	}

	public Integer getEstadosricodigo() {
		return estadosricodigo;
	}

	public void setEstadosricodigo(Integer estadosricodigo) {
		this.estadosricodigo = estadosricodigo;
	}

	public String getNumeroproforma() {
		return numeroproforma;
	}

	public void setNumeroproforma(String numeroproforma) {
		this.numeroproforma = numeroproforma;
	}

	public String getClaveacceso() {
		return claveacceso;
	}

	public void setClaveacceso(String claveacceso) {
		this.claveacceso = claveacceso;
	}

	public String getFob() {
		return fob;
	}

	public void setFob(String fob) {
		this.fob = fob;
	}

	public Integer getFobcodigo() {
		return fobcodigo;
	}

	public void setFobcodigo(Integer fobcodigo) {
		this.fobcodigo = fobcodigo;
	}

	public Tadmcatalogo getTadmestado() {
		return tadmestado;
	}

	public void setTadmestado(Tadmcatalogo tadmestado) {
		this.tadmestado = tadmestado;
	}

	public Tfaccliente getTfaccliente() {
		return tfaccliente;
	}

	public void setTfaccliente(Tfaccliente tfaccliente) {
		this.tfaccliente = tfaccliente;
	}

	public Tadmparamsri getTadmestadosri() {
		return tadmestadosri;
	}

	public void setTadmestadosri(Tadmparamsri tadmestadosri) {
		this.tadmestadosri = tadmestadosri;
	}

	public Collection<Tfacdetfactura> getTfacdetfacturas() {
		return tfacdetfacturas;
	}

	public void setTfacdetfacturas(Collection<Tfacdetfactura> tfacdetfacturas) {
		this.tfacdetfacturas = tfacdetfacturas;
	}

	public Collection<Tfacformapago> getTfacformapagos() {
		return tfacformapagos;
	}

	public void setTfacformapagos(Collection<Tfacformapago> tfacformapagos) {
		this.tfacformapagos = tfacformapagos;
	}

	public Collection<Tfaccuentasxcobrar> getTfaccuentasxcobrars() {
		return tfaccuentasxcobrars;
	}

	public void setTfaccuentasxcobrars(
			Collection<Tfaccuentasxcobrar> tfaccuentasxcobrars) {
		this.tfaccuentasxcobrars = tfaccuentasxcobrars;
	}

	public Tadmcatalogo getTadmfob() {
		return tadmfob;
	}

	public void setTadmfob(Tadmcatalogo tadmfob) {
		this.tadmfob = tadmfob;
	}
	
}
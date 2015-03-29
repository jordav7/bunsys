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
	private String airline;

	@Column
	private Integer airlinecodigo;
	
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
	private String referendo;
	@Column
	private String farmcode;
	@Column
	@Temporal(TemporalType.DATE)
	private Date fechaembarque;
	@Column
	private String masterawb;
	@Column
	private String houseawb;
	@Column
	private String dae;
	@Column
	private String consignee;
	@Column
	private String fixedprice;
	@Column
	private String freightforwarder;
	@Column
	private BigDecimal totalpices;
	@Column
	private BigDecimal totaleqfullboxes;
	@Column
	private BigDecimal totalbunch;
	@Column
	private BigDecimal totalstems;
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
	@Column
	private String  carguera;
	@Column
	private Integer  cargueracodigo;
    @Column
	private String distritovuelo ;
    @Column
	private Integer  distritovuelocodigo;
    
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
		@JoinColumn(name="airline", referencedColumnName="codigocatalogo", insertable=false, updatable=false),
		@JoinColumn(name="airlinecodigo", referencedColumnName="codigotipocatalogo", insertable=false, updatable=false),
		@JoinColumn(name="codigocompania", referencedColumnName="codigocompania", insertable=false, updatable=false)
	})
	private Tadmcatalogo tadmairline;
	
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
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="carguera", referencedColumnName="codigocatalogo", insertable=false, updatable=false),
		@JoinColumn(name="cargueracodigo", referencedColumnName="codigotipocatalogo", insertable=false, updatable=false),
		@JoinColumn(name="codigocompania", referencedColumnName="codigocompania", insertable=false, updatable=false)
	})
	private Tadmcatalogo tadmcarguera;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="distritovuelo", referencedColumnName="codigocatalogo", insertable=false, updatable=false),
		@JoinColumn(name="distritovuelocodigo", referencedColumnName="codigotipocatalogo", insertable=false, updatable=false),
		@JoinColumn(name="codigocompania", referencedColumnName="codigocompania", insertable=false, updatable=false)
	})
	private Tadmcatalogo tadmdistritovuelo;
	
	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	public Integer getAirlinecodigo() {
		return airlinecodigo;
	}

	public void setAirlinecodigo(Integer airlinecodigo) {
		this.airlinecodigo = airlinecodigo;
	}

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


	public String getReferendo() {
		return referendo;
	}

	public void setReferendo(String referendo) {
		this.referendo = referendo;
	}

	public String getFarmcode() {
		return farmcode;
	}

	public void setFarmcode(String farmcode) {
		this.farmcode = farmcode;
	}

	public Date getFechaembarque() {
		return fechaembarque;
	}

	public void setFechaembarque(Date fechaembarque) {
		this.fechaembarque = fechaembarque;
	}


	public String getMasterawb() {
		return masterawb;
	}

	public void setMasterawb(String masterawb) {
		this.masterawb = masterawb;
	}

	public String getHouseawb() {
		return houseawb;
	}

	public void setHouseawb(String houseawb) {
		this.houseawb = houseawb;
	}

	public String getDae() {
		return dae;
	}

	public void setDae(String dae) {
		this.dae = dae;
	}

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public String getFixedprice() {
		return fixedprice;
	}

	public void setFixedprice(String fixedprice) {
		this.fixedprice = fixedprice;
	}

	public String getFreightforwarder() {
		return freightforwarder;
	}

	public void setFreightforwarder(String freightforwarder) {
		this.freightforwarder = freightforwarder;
	}

	public BigDecimal getTotalpices() {
		return totalpices;
	}

	public void setTotalpices(BigDecimal totalpices) {
		this.totalpices = totalpices;
	}

	public BigDecimal getTotaleqfullboxes() {
		return totaleqfullboxes;
	}

	public void setTotaleqfullboxes(BigDecimal totaleqfullboxes) {
		this.totaleqfullboxes = totaleqfullboxes;
	}

	public BigDecimal getTotalbunch() {
		return totalbunch;
	}

	public void setTotalbunch(BigDecimal totalbunch) {
		this.totalbunch = totalbunch;
	}

	public BigDecimal getTotalstems() {
		return totalstems;
	}

	public void setTotalstems(BigDecimal totalstems) {
		this.totalstems = totalstems;
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

	public Tadmcatalogo getTadmestado() {
		return tadmestado;
	}

	public void setTadmestado(Tadmcatalogo tadmestado) {
		this.tadmestado = tadmestado;
	}

	public Tadmcatalogo getTadmairline() {
		return tadmairline;
	}

	public void setTadmairline(Tadmcatalogo tadmairline) {
		this.tadmairline = tadmairline;
	}

	public Collection<Tfacdetfactura> getTfacdetfacturas() {
		return tfacdetfacturas;
	}

	public void setTfacdetfacturas(Collection<Tfacdetfactura> tfacdetfacturas) {
		this.tfacdetfacturas = tfacdetfacturas;
	}

	public Tfaccliente getTfaccliente() {
		return tfaccliente;
	}

	public void setTfaccliente(Tfaccliente tfaccliente) {
		this.tfaccliente = tfaccliente;
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

	public Tadmparamsri getTadmestadosri() {
		return tadmestadosri;
	}

	public void setTadmestadosri(Tadmparamsri tadmestadosri) {
		this.tadmestadosri = tadmestadosri;
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

	public String getCarguera() {
		return carguera;
	}

	public void setCarguera(String carguera) {
		this.carguera = carguera;
	}

	public Integer getCargueracodigo() {
		return cargueracodigo;
	}

	public void setCargueracodigo(Integer cargueracodigo) {
		this.cargueracodigo = cargueracodigo;
	}

	public String getDistritovuelo() {
		return distritovuelo;
	}

	public void setDistritovuelo(String distritovuelo) {
		this.distritovuelo = distritovuelo;
	}

	public Integer getDistritovuelocodigo() {
		return distritovuelocodigo;
	}

	public void setDistritovuelocodigo(Integer distritovuelocodigo) {
		this.distritovuelocodigo = distritovuelocodigo;
	}

	public Tadmcatalogo getTadmfob() {
		return tadmfob;
	}

	public void setTadmfob(Tadmcatalogo tadmfob) {
		this.tadmfob = tadmfob;
	}

	public Tadmcatalogo getTadmcarguera() {
		return tadmcarguera;
	}

	public void setTadmcarguera(Tadmcatalogo tadmcarguera) {
		this.tadmcarguera = tadmcarguera;
	}

	public Tadmcatalogo getTadmdistritovuelo() {
		return tadmdistritovuelo;
	}

	public void setTadmdistritovuelo(Tadmcatalogo tadmdistritovuelo) {
		this.tadmdistritovuelo = tadmdistritovuelo;
	}
	
}
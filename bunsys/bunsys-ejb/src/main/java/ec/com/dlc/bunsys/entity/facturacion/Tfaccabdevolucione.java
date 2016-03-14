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
import ec.com.dlc.bunsys.entity.base.BaseEntity;
import ec.com.dlc.bunsys.entity.facturacion.pk.TfaccabdevolucionePK;


/**
 * The persistent class for the tfaccabdevoluciones database table.
 * 
 */
@Entity
@Table(name="tfaccabdevoluciones")
public class Tfaccabdevolucione extends BaseEntity<TfaccabdevolucionePK>  {
	private static final long serialVersionUID = 1L;

<<<<<<< HEAD
	
=======
	@Column
	private String airline;
	
	@Column
	private Integer airlinecodigo;
	
	@Column
	private String referendo;
	
	@Column
	private String farmcode;
>>>>>>> 6aad317a82996c5469498a3307afc7abb7c3e40d
	
	@Column
	@Temporal(TemporalType.DATE)
	private Date fechaembarque;
	
	@Column
<<<<<<< HEAD
=======
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
>>>>>>> 6aad317a82996c5469498a3307afc7abb7c3e40d
	private String countrycode;
	
	@Column
	private String area;
	
	@Column
	private String codigocliente;

	@Column
	private String estado;

	@Column
	private Integer estadocodigo;

	@Column
	private String estadosri;

	@Column
	private Integer estadosricodigo;

	@Column
	@Temporal(TemporalType.DATE)
	private Date fechadevolucion;

	@Column
	private String formapago;

	@Column
	private Integer formapagocodigo;

	@Column
	private BigDecimal iva;

	@Column
	private String numerofactura;

	@Column
	private String observacion;

	@Column
	private BigDecimal pocentajeirbpnr;

	@Column
	private BigDecimal porcentajedesc;

	@Column
	private BigDecimal porcentajeice;

	@Column
	private BigDecimal porcentajeiva;

	@Column
	private String seriedevolucion;

	@Column
	private BigDecimal subtotalbase;

	@Column
	private BigDecimal subtotalexcentoiva;

	@Column
	private BigDecimal subtotaliva;

	@Column
	private BigDecimal subtotalneto;

	@Column
	private BigDecimal subtotalnoiva;

	@Column
	private BigDecimal total;

	@Column
	private BigDecimal totaldescuento;

	@Column
	private BigDecimal valorice;

	@Column
	private BigDecimal valorirbpnr;
<<<<<<< HEAD

    @Column
	private String  claveacceso;
    
=======
	
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

    @ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="airline", referencedColumnName="codigocatalogo", insertable=false, updatable=false),
		@JoinColumn(name="airlinecodigo", referencedColumnName="codigotipocatalogo", insertable=false, updatable=false),
		@JoinColumn(name="codigocompania", referencedColumnName="codigocompania", insertable=false, updatable=false)
	})
    private Tadmcatalogo tadmairline;
>>>>>>> 6aad317a82996c5469498a3307afc7abb7c3e40d
    
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="formapago", referencedColumnName="codigocatalogo", insertable=false, updatable=false),
		@JoinColumn(name="formapagocodigo", referencedColumnName="codigotipocatalogo", insertable=false, updatable=false),
		@JoinColumn(name="codigocompania", referencedColumnName="codigocompania", insertable=false, updatable=false)
	})
	private Tadmcatalogo tadmformapago;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="estadosri", referencedColumnName="codigocatalogo", insertable=false, updatable=false),
		@JoinColumn(name="estadosricodigo", referencedColumnName="codigotipocatalogo", insertable=false, updatable=false),
		@JoinColumn(name="codigocompania", referencedColumnName="codigocompania", insertable=false, updatable=false)
	})
	private Tadmcatalogo tadmestadosri;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="estado", referencedColumnName="codigocatalogo", insertable=false, updatable=false),
		@JoinColumn(name="estadocodigo", referencedColumnName="codigotipocatalogo", insertable=false, updatable=false),
		@JoinColumn(name="codigocompania", referencedColumnName="codigocompania", insertable=false, updatable=false)
	})
	private Tadmcatalogo tadmestado;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="numerofactura", referencedColumnName="numerofactura", insertable=false, updatable=false),
		@JoinColumn(name="codigocompania", referencedColumnName="codigocompania", insertable=false, updatable=false)
	})
	private Tfaccabfactura tfaccabfactura;
	

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="codigocliente", referencedColumnName="codigocliente", insertable=false, updatable=false),
		@JoinColumn(name="codigocompania", referencedColumnName="codigocompania", insertable=false, updatable=false)
	})
	private Tfaccliente tfaccliente;
	
	//bi-directional many-to-one association to Tfacdetdevolucione
	@OneToMany(mappedBy="tfaccabdevolucione")
	private Collection<Tfacdetdevolucione> tfacdetdevoluciones;

	//bi-directional many-to-one association to Tfacformapagodev
	@OneToMany(mappedBy="tfaccabdevolucione")
	private Collection<Tfacformapagodev> tfacformapagodevs;

	
<<<<<<< HEAD
=======
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
	
>>>>>>> 6aad317a82996c5469498a3307afc7abb7c3e40d
	public Tfaccabdevolucione() {
		this.pk = new TfaccabdevolucionePK();
	}


	public String getCodigocliente() {
		return codigocliente;
	}


	public void setCodigocliente(String codigocliente) {
		this.codigocliente = codigocliente;
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

	public String getEstadosri() {
		return this.estadosri;
	}

	public void setEstadosri(String estadosri) {
		this.estadosri = estadosri;
	}

	public Integer getEstadosricodigo() {
		return this.estadosricodigo;
	}

	public void setEstadosricodigo(Integer estadosricodigo) {
		this.estadosricodigo = estadosricodigo;
	}

	public Date getFechadevolucion() {
		return this.fechadevolucion;
	}

	public void setFechadevolucion(Date fechadevolucion) {
		this.fechadevolucion = fechadevolucion;
	}

	public String getFormapago() {
		return this.formapago;
	}

	public void setFormapago(String formapago) {
		this.formapago = formapago;
	}

	public Integer getFormapagocodigo() {
		return this.formapagocodigo;
	}

	public void setFormapagocodigo(Integer formapagocodigo) {
		this.formapagocodigo = formapagocodigo;
	}

	public BigDecimal getIva() {
		return this.iva;
	}

	public void setIva(BigDecimal iva) {
		this.iva = iva;
	}

	public String getNumerofactura() {
		return this.numerofactura;
	}

	public void setNumerofactura(String numerofactura) {
		this.numerofactura = numerofactura;
	}

	public String getObservacion() {
		return this.observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public BigDecimal getPocentajeirbpnr() {
		return this.pocentajeirbpnr;
	}

	public void setPocentajeirbpnr(BigDecimal pocentajeirbpnr) {
		this.pocentajeirbpnr = pocentajeirbpnr;
	}

	public BigDecimal getPorcentajedesc() {
		return this.porcentajedesc;
	}

	public void setPorcentajedesc(BigDecimal porcentajedesc) {
		this.porcentajedesc = porcentajedesc;
	}

	public BigDecimal getPorcentajeice() {
		return this.porcentajeice;
	}

	public void setPorcentajeice(BigDecimal porcentajeice) {
		this.porcentajeice = porcentajeice;
	}

	public BigDecimal getPorcentajeiva() {
		return this.porcentajeiva;
	}

	public void setPorcentajeiva(BigDecimal porcentajeiva) {
		this.porcentajeiva = porcentajeiva;
	}

	public String getSeriedevolucion() {
		return this.seriedevolucion;
	}

	public void setSeriedevolucion(String seriedevolucion) {
		this.seriedevolucion = seriedevolucion;
	}

	public BigDecimal getSubtotalbase() {
		return this.subtotalbase;
	}

	public void setSubtotalbase(BigDecimal subtotalbase) {
		this.subtotalbase = subtotalbase;
	}

	public BigDecimal getSubtotalexcentoiva() {
		return this.subtotalexcentoiva;
	}

	public void setSubtotalexcentoiva(BigDecimal subtotalexcentoiva) {
		this.subtotalexcentoiva = subtotalexcentoiva;
	}

	public BigDecimal getSubtotaliva() {
		return this.subtotaliva;
	}

	public void setSubtotaliva(BigDecimal subtotaliva) {
		this.subtotaliva = subtotaliva;
	}

	public BigDecimal getSubtotalneto() {
		return this.subtotalneto;
	}

	public void setSubtotalneto(BigDecimal subtotalneto) {
		this.subtotalneto = subtotalneto;
	}

	public BigDecimal getSubtotalnoiva() {
		return this.subtotalnoiva;
	}

	public void setSubtotalnoiva(BigDecimal subtotalnoiva) {
		this.subtotalnoiva = subtotalnoiva;
	}

	public BigDecimal getTotal() {
		return this.total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getTotaldescuento() {
		return this.totaldescuento;
	}

	public void setTotaldescuento(BigDecimal totaldescuento) {
		this.totaldescuento = totaldescuento;
	}

	public BigDecimal getValorice() {
		return this.valorice;
	}

	public void setValorice(BigDecimal valorice) {
		this.valorice = valorice;
	}

	public BigDecimal getValorirbpnr() {
		return this.valorirbpnr;
	}

	public void setValorirbpnr(BigDecimal valorirbpnr) {
		this.valorirbpnr = valorirbpnr;
	}

	public Collection<Tfacdetdevolucione> getTfacdetdevoluciones() {
		return this.tfacdetdevoluciones;
	}

	public void setTfacdetdevoluciones(Collection<Tfacdetdevolucione> tfacdetdevoluciones) {
		this.tfacdetdevoluciones = tfacdetdevoluciones;
	}

	public Tfacdetdevolucione addTfacdetdevolucione(Tfacdetdevolucione tfacdetdevolucione) {
		getTfacdetdevoluciones().add(tfacdetdevolucione);
		tfacdetdevolucione.setTfaccabdevolucione(this);

		return tfacdetdevolucione;
	}

	public Tfacdetdevolucione removeTfacdetdevolucione(Tfacdetdevolucione tfacdetdevolucione) {
		getTfacdetdevoluciones().remove(tfacdetdevolucione);
		tfacdetdevolucione.setTfaccabdevolucione(null);

		return tfacdetdevolucione;
	}

	public Collection<Tfacformapagodev> getTfacformapagodevs() {
		return this.tfacformapagodevs;
	}

	public void setTfacformapagodevs(Collection<Tfacformapagodev> tfacformapagodevs) {
		this.tfacformapagodevs = tfacformapagodevs;
	}

	public Tfacformapagodev addTfacformapagodev(Tfacformapagodev tfacformapagodev) {
		getTfacformapagodevs().add(tfacformapagodev);
		tfacformapagodev.setTfaccabdevolucione(this);

		return tfacformapagodev;
	}

	public Tfacformapagodev removeTfacformapagodev(Tfacformapagodev tfacformapagodev) {
		getTfacformapagodevs().remove(tfacformapagodev);
		tfacformapagodev.setTfaccabdevolucione(null);

		return tfacformapagodev;
	}

	public Tadmcatalogo getTadmformapago() {
		return tadmformapago;
	}

	public void setTadmformapago(Tadmcatalogo tadmformapago) {
		this.tadmformapago = tadmformapago;
	}

	public Tadmcatalogo getTadmestadosri() {
		return tadmestadosri;
	}

	public void setTadmestadosri(Tadmcatalogo tadmestadosri) {
		this.tadmestadosri = tadmestadosri;
	}

	public Tadmcatalogo getTadmestado() {
		return tadmestado;
	}

	public void setTadmestado(Tadmcatalogo tadmestado) {
		this.tadmestado = tadmestado;
	}

	public Tfaccabfactura getTfaccabfactura() {
		return tfaccabfactura;
	}

	public void setTfaccabfactura(Tfaccabfactura tfaccabfactura) {
		this.tfaccabfactura = tfaccabfactura;
	}

	public Tfaccliente getTfaccliente() {
		return tfaccliente;
	}

	public void setTfaccliente(Tfaccliente tfaccliente) {
		this.tfaccliente = tfaccliente;
	}

<<<<<<< HEAD
=======

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


>>>>>>> 6aad317a82996c5469498a3307afc7abb7c3e40d
	public Date getFechaembarque() {
		return fechaembarque;
	}


	public void setFechaembarque(Date fechaembarque) {
		this.fechaembarque = fechaembarque;
	}


<<<<<<< HEAD
=======
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


>>>>>>> 6aad317a82996c5469498a3307afc7abb7c3e40d
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


<<<<<<< HEAD
	public String getClaveacceso() {
		return claveacceso;
	}


	public void setClaveacceso(String claveacceso) {
		this.claveacceso = claveacceso;
=======
	public Tadmcatalogo getTadmairline() {
		return tadmairline;
	}


	public void setTadmairline(Tadmcatalogo tadmairline) {
		this.tadmairline = tadmairline;
>>>>>>> 6aad317a82996c5469498a3307afc7abb7c3e40d
	}

}
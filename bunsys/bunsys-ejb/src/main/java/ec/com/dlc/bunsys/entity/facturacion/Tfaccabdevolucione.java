package ec.com.dlc.bunsys.entity.facturacion;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

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

	@Column
	private Integer codigocliente;

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

	public Tfaccabdevolucione() {
	}

	public Integer getCodigocliente() {
		return this.codigocliente;
	}

	public void setCodigocliente(Integer codigocliente) {
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

}
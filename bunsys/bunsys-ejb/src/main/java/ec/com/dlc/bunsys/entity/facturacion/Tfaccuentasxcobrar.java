package ec.com.dlc.bunsys.entity.facturacion;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ec.com.dlc.bunsys.commons.listener.GenerateIdListener;
import ec.com.dlc.bunsys.entity.administracion.Tadmcatalogo;
import ec.com.dlc.bunsys.entity.facturacion.pk.TfaccuentasxcobrarPK;


/**
 * The persistent class for the tfaccuentasxcobrar database table.
 * 
 */
@Entity
@NamedQuery(name="Tfaccuentasxcobrar.findAll", query="SELECT t FROM Tfaccuentasxcobrar t")
@Table(name="tfaccuentasxcobrar")
@EntityListeners(GenerateIdListener.class)
public class Tfaccuentasxcobrar extends ec.com.dlc.bunsys.entity.base.BaseEntity<TfaccuentasxcobrarPK>  {
	private static final long serialVersionUID = 1L;

	@Column
	private Integer codigocliente;

	@Column
	private String estado;

	@Column
	private Integer estadocodigo;

	@Column
	@Temporal(TemporalType.DATE)
	private Date fechaemision;

	@Column
	@Temporal(TemporalType.DATE)
	private Date fechapago;

	@Column
	@Temporal(TemporalType.DATE)
	private Date fecharegistro;

	@Column
	@Temporal(TemporalType.DATE)
	private Date fechavence;

	@Column
	private String numdoc;

	@Column
	private String numerofactura;

	@Column
	private Integer numeropago;

	@Column
	private String referencia;

	@Column
	private BigDecimal saldo;

	@Column
	private BigDecimal valor;

	@Column
	private BigDecimal valorfactura;
	
	@Column
	private String concepto;
	
	@Column
	private String tipodoc;
	
	@Column
	private Integer tipodoccodigo;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="codigocompania", referencedColumnName="codigocompania", insertable=false, updatable=false),
		@JoinColumn(name="estado",referencedColumnName="codigocatalogo",insertable=false, updatable=false),
		@JoinColumn(name="estadocodigo",referencedColumnName="codigotipocatalogo",insertable=false, updatable=false)
	})
	private Tadmcatalogo tadmestado;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="codigocompania", referencedColumnName="codigocompania",insertable=false, updatable=false),
		@JoinColumn(name="codigocliente", referencedColumnName="codigocliente", insertable=false, updatable=false)
	})
	private Tfaccliente tfaccliente;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="codigocompania", referencedColumnName="codigocompania",insertable=false, updatable=false),
		@JoinColumn(name="numerofactura", referencedColumnName="numerofactura", insertable=false, updatable=false)
	})
	private Tfaccabfactura tfaccabfactura;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="codigocompania", referencedColumnName="codigocompania", insertable=false, updatable=false),
		@JoinColumn(name="tipodoc", referencedColumnName="codigocatalogo", insertable=false, updatable=false),
		@JoinColumn(name="tipodoccodigo", referencedColumnName="codigotipocatalogo", insertable=false, updatable=false)
	})
	private Tadmcatalogo tadmtipodoc;
	
	public Tfaccuentasxcobrar() {
		pk= new TfaccuentasxcobrarPK();
	}


	public Integer getCodigocliente() {
		return codigocliente;
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

	public Date getFechaemision() {
		return this.fechaemision;
	}

	public void setFechaemision(Date fechaemision) {
		this.fechaemision = fechaemision;
	}

	public Date getFechapago() {
		return this.fechapago;
	}

	public void setFechapago(Date fechapago) {
		this.fechapago = fechapago;
	}

	public Date getFecharegistro() {
		return this.fecharegistro;
	}

	public void setFecharegistro(Date fecharegistro) {
		this.fecharegistro = fecharegistro;
	}

	public Date getFechavence() {
		return this.fechavence;
	}

	public void setFechavence(Date fechavence) {
		this.fechavence = fechavence;
	}

	public String getNumdoc() {
		return this.numdoc;
	}

	public void setNumdoc(String numdoc) {
		this.numdoc = numdoc;
	}

	public String getNumerofactura() {
		return this.numerofactura;
	}

	public void setNumerofactura(String numerofactura) {
		this.numerofactura = numerofactura;
	}

	public Integer getNumeropago() {
		return this.numeropago;
	}

	public void setNumeropago(Integer numeropago) {
		this.numeropago = numeropago;
	}

	public String getReferencia() {
		return this.referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public BigDecimal getSaldo() {
		return this.saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public BigDecimal getValor() {
		return this.valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getValorfactura() {
		return this.valorfactura;
	}

	public void setValorfactura(BigDecimal valorfactura) {
		this.valorfactura = valorfactura;
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

	public Tfaccabfactura getTfaccabfactura() {
		return tfaccabfactura;
	}

	public void setTfaccabfactura(Tfaccabfactura tfaccabfactura) {
		this.tfaccabfactura = tfaccabfactura;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public String getTipodoc() {
		return tipodoc;
	}

	public void setTipodoc(String tipodoc) {
		this.tipodoc = tipodoc;
	}

	public Integer getTipodoccodigo() {
		return tipodoccodigo;
	}

	public void setTipodoccodigo(Integer tipodoccodigo) {
		this.tipodoccodigo = tipodoccodigo;
	}

	public Tadmcatalogo getTadmtipodoc() {
		return tadmtipodoc;
	}

	public void setTadmtipodoc(Tadmcatalogo tadmtipodoc) {
		this.tadmtipodoc = tadmtipodoc;
	}
	
	
}
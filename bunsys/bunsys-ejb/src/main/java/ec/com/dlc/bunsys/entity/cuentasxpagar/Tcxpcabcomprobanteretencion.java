package ec.com.dlc.bunsys.entity.cuentasxpagar;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ec.com.dlc.bunsys.entity.base.BaseEntity;
import ec.com.dlc.bunsys.entity.cuentasxpagar.pk.TcxpcabcomprobanteretencionPK;


/**
 * The persistent class for the tcxpcabcomprobanteretencion database table.
 * 
 */
@Entity
@NamedQuery(name="Tcxpcabcomprobanteretencion.findAll", query="SELECT t FROM Tcxpcabcomprobanteretencion t")
public class Tcxpcabcomprobanteretencion extends BaseEntity<TcxpcabcomprobanteretencionPK>  {
	private static final long serialVersionUID = 1L;

	@Column
	private String anioperiodocontable;

	@Column
	private String estado;

	@Column
	private Integer estadocodigo;

	@Column
	private Integer estadocomprobantecodigo;

	@Column
	@Temporal(TemporalType.DATE)
	private Date fechacomprobanteretencion;

	@Column
	@Temporal(TemporalType.DATE)
	private Date fechaemisionfacprov;

	@Column
	private String mesperiodocontable;

	@Column
	private String numerofacturaprov;

	@Column
	private String observacion;

	@Column
	private String seriefacturaprov;

	@Column
	private BigDecimal totalretencion;

	//bi-directional many-to-one association to Tcxpproveedor
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="codigocompania", referencedColumnName="codigocompania", insertable=false, updatable=false),
		@JoinColumn(name="codigoproveedor", referencedColumnName="codigoproveedor", insertable=false, updatable=false)
		})
	private Tcxpproveedor tcxpproveedor;

	//bi-directional many-to-one association to Tcxpdetcomprobanteretencion
	@OneToMany(mappedBy="tcxpcabcomprobanteretencion")
	private Set<Tcxpdetcomprobanteretencion> tcxpdetcomprobanteretencions;

	public Tcxpcabcomprobanteretencion() {
	}

	public String getAnioperiodocontable() {
		return this.anioperiodocontable;
	}

	public void setAnioperiodocontable(String anioperiodocontable) {
		this.anioperiodocontable = anioperiodocontable;
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

	public Integer getEstadocomprobantecodigo() {
		return this.estadocomprobantecodigo;
	}

	public void setEstadocomprobantecodigo(Integer estadocomprobantecodigo) {
		this.estadocomprobantecodigo = estadocomprobantecodigo;
	}

	public Date getFechacomprobanteretencion() {
		return this.fechacomprobanteretencion;
	}

	public void setFechacomprobanteretencion(Date fechacomprobanteretencion) {
		this.fechacomprobanteretencion = fechacomprobanteretencion;
	}

	public Date getFechaemisionfacprov() {
		return this.fechaemisionfacprov;
	}

	public void setFechaemisionfacprov(Date fechaemisionfacprov) {
		this.fechaemisionfacprov = fechaemisionfacprov;
	}

	public String getMesperiodocontable() {
		return this.mesperiodocontable;
	}

	public void setMesperiodocontable(String mesperiodocontable) {
		this.mesperiodocontable = mesperiodocontable;
	}

	public String getNumerofacturaprov() {
		return this.numerofacturaprov;
	}

	public void setNumerofacturaprov(String numerofacturaprov) {
		this.numerofacturaprov = numerofacturaprov;
	}

	public String getObservacion() {
		return this.observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getSeriefacturaprov() {
		return this.seriefacturaprov;
	}

	public void setSeriefacturaprov(String seriefacturaprov) {
		this.seriefacturaprov = seriefacturaprov;
	}

	public BigDecimal getTotalretencion() {
		return this.totalretencion;
	}

	public void setTotalretencion(BigDecimal totalretencion) {
		this.totalretencion = totalretencion;
	}

	public Tcxpproveedor getTcxpproveedor() {
		return this.tcxpproveedor;
	}

	public void setTcxpproveedor(Tcxpproveedor tcxpproveedor) {
		this.tcxpproveedor = tcxpproveedor;
	}

	public Set<Tcxpdetcomprobanteretencion> getTcxpdetcomprobanteretencions() {
		return this.tcxpdetcomprobanteretencions;
	}

	public void setTcxpdetcomprobanteretencions(Set<Tcxpdetcomprobanteretencion> tcxpdetcomprobanteretencions) {
		this.tcxpdetcomprobanteretencions = tcxpdetcomprobanteretencions;
	}

	public Tcxpdetcomprobanteretencion addTcxpdetcomprobanteretencion(Tcxpdetcomprobanteretencion tcxpdetcomprobanteretencion) {
		getTcxpdetcomprobanteretencions().add(tcxpdetcomprobanteretencion);
		tcxpdetcomprobanteretencion.setTcxpcabcomprobanteretencion(this);

		return tcxpdetcomprobanteretencion;
	}

	public Tcxpdetcomprobanteretencion removeTcxpdetcomprobanteretencion(Tcxpdetcomprobanteretencion tcxpdetcomprobanteretencion) {
		getTcxpdetcomprobanteretencions().remove(tcxpdetcomprobanteretencion);
		tcxpdetcomprobanteretencion.setTcxpcabcomprobanteretencion(null);

		return tcxpdetcomprobanteretencion;
	}

}
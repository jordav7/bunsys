package ec.com.dlc.bunsys.entity.cuentasxpagar;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

import ec.com.dlc.bunsys.entity.base.BaseEntity;
import ec.com.dlc.bunsys.entity.cuentasxpagar.pk.TcxpdetcomprobanteretencionPK;


/**
 * The persistent class for the tcxpdetcomprobanteretencion database table.
 * 
 */
@Entity
@NamedQuery(name="Tcxpdetcomprobanteretencion.findAll", query="SELECT t FROM Tcxpdetcomprobanteretencion t")
public class Tcxpdetcomprobanteretencion extends BaseEntity<TcxpdetcomprobanteretencionPK>  {
	private static final long serialVersionUID = 1L;

	@Column
	private BigDecimal baseimponible;

	@Column
	private String descripcion;

	@Column
	private String impuestoaretener;

	@Column
	private Integer impuestoaretenercodigo;

	@Column
	private BigDecimal porcentajeretencion;

	@Column
	private String retencion;

	@Column
	private Integer retencioncodigo;

	@Column
	private String tipodocumento;

	@Column
	private Integer tipodocumentocodigo;

	@Column
	private BigDecimal valorretenido;
	
	@Column
	private String codigocabcomprobanteretencion;

	//bi-directional many-to-one association to Tcxpcabcomprobanteretencion
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="codigocabcomprobanteretencion", referencedColumnName="codigocabcomprobanteretencion", insertable=false, updatable=false),
		@JoinColumn(name="codigocompania", referencedColumnName="codigocompania", insertable=false, updatable=false)
		})
	private Tcxpcabcomprobanteretencion tcxpcabcomprobanteretencion;

	public Tcxpdetcomprobanteretencion() {
	}

	public BigDecimal getBaseimponible() {
		return this.baseimponible;
	}

	public void setBaseimponible(BigDecimal baseimponible) {
		this.baseimponible = baseimponible;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getImpuestoaretener() {
		return this.impuestoaretener;
	}

	public void setImpuestoaretener(String impuestoaretener) {
		this.impuestoaretener = impuestoaretener;
	}

	public Integer getImpuestoaretenercodigo() {
		return this.impuestoaretenercodigo;
	}

	public void setImpuestoaretenercodigo(Integer impuestoaretenercodigo) {
		this.impuestoaretenercodigo = impuestoaretenercodigo;
	}

	public BigDecimal getPorcentajeretencion() {
		return this.porcentajeretencion;
	}

	public void setPorcentajeretencion(BigDecimal porcentajeretencion) {
		this.porcentajeretencion = porcentajeretencion;
	}

	public String getRetencion() {
		return this.retencion;
	}

	public void setRetencion(String retencion) {
		this.retencion = retencion;
	}

	public Integer getRetencioncodigo() {
		return this.retencioncodigo;
	}

	public void setRetencioncodigo(Integer retencioncodigo) {
		this.retencioncodigo = retencioncodigo;
	}

	public String getTipodocumento() {
		return this.tipodocumento;
	}

	public void setTipodocumento(String tipodocumento) {
		this.tipodocumento = tipodocumento;
	}

	public Integer getTipodocumentocodigo() {
		return this.tipodocumentocodigo;
	}

	public void setTipodocumentocodigo(Integer tipodocumentocodigo) {
		this.tipodocumentocodigo = tipodocumentocodigo;
	}

	public BigDecimal getValorretenido() {
		return this.valorretenido;
	}

	public void setValorretenido(BigDecimal valorretenido) {
		this.valorretenido = valorretenido;
	}

	public Tcxpcabcomprobanteretencion getTcxpcabcomprobanteretencion() {
		return this.tcxpcabcomprobanteretencion;
	}

	public void setTcxpcabcomprobanteretencion(Tcxpcabcomprobanteretencion tcxpcabcomprobanteretencion) {
		this.tcxpcabcomprobanteretencion = tcxpcabcomprobanteretencion;
	}

	public String getCodigocabcomprobanteretencion() {
		return codigocabcomprobanteretencion;
	}

	public void setCodigocabcomprobanteretencion(
			String codigocabcomprobanteretencion) {
		this.codigocabcomprobanteretencion = codigocabcomprobanteretencion;
	}

}
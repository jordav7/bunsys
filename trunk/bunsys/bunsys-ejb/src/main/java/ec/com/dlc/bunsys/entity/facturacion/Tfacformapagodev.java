package ec.com.dlc.bunsys.entity.facturacion;

import java.io.Serializable;

import javax.persistence.*;

import ec.com.dlc.bunsys.entity.facturacion.pk.TfacformapagodevPK;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the tfacformapagodev database table.
 * 
 */
@Entity
@NamedQuery(name="Tfacformapagodev.findAll", query="SELECT t FROM Tfacformapagodev t")
public class Tfacformapagodev extends ec.com.dlc.bunsys.entity.base.BaseEntity<T>  {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TfacformapagodevPK id;

	@Temporal(TemporalType.DATE)
	private Date fechapago;

	private String institucion;

	private Integer institucioncodigo;

	private String numerodocumento;

	private String tipoformapago;

	private Integer tipoformapagocodigo;

	private BigDecimal valor;

	//bi-directional many-to-one association to Tfaccabdevolucione
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="codigocompania", referencedColumnName="codigocompania"),
		@JoinColumn(name="numerodevoluciones", referencedColumnName="numerodevoluciones")
		})
	private Tfaccabdevolucione tfaccabdevolucione;

	public Tfacformapagodev() {
	}

	public TfacformapagodevPK getId() {
		return this.id;
	}

	public void setId(TfacformapagodevPK id) {
		this.id = id;
	}

	public Date getFechapago() {
		return this.fechapago;
	}

	public void setFechapago(Date fechapago) {
		this.fechapago = fechapago;
	}

	public String getInstitucion() {
		return this.institucion;
	}

	public void setInstitucion(String institucion) {
		this.institucion = institucion;
	}

	public Integer getInstitucioncodigo() {
		return this.institucioncodigo;
	}

	public void setInstitucioncodigo(Integer institucioncodigo) {
		this.institucioncodigo = institucioncodigo;
	}

	public String getNumerodocumento() {
		return this.numerodocumento;
	}

	public void setNumerodocumento(String numerodocumento) {
		this.numerodocumento = numerodocumento;
	}

	public String getTipoformapago() {
		return this.tipoformapago;
	}

	public void setTipoformapago(String tipoformapago) {
		this.tipoformapago = tipoformapago;
	}

	public Integer getTipoformapagocodigo() {
		return this.tipoformapagocodigo;
	}

	public void setTipoformapagocodigo(Integer tipoformapagocodigo) {
		this.tipoformapagocodigo = tipoformapagocodigo;
	}

	public BigDecimal getValor() {
		return this.valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Tfaccabdevolucione getTfaccabdevolucione() {
		return this.tfaccabdevolucione;
	}

	public void setTfaccabdevolucione(Tfaccabdevolucione tfaccabdevolucione) {
		this.tfaccabdevolucione = tfaccabdevolucione;
	}

}
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
import ec.com.dlc.bunsys.entity.base.BaseEntity;
import ec.com.dlc.bunsys.entity.facturacion.pk.TfacdetproformaPK;
import ec.com.dlc.bunsys.entity.facturacion.pk.TfacformapagoPK;


/**
 * The persistent class for the tfacformapago database table.
 * 
 */
@Entity
@Table(name="tfacformapago")
@EntityListeners(GenerateIdListener.class)
public class Tfacformapago extends BaseEntity<TfacformapagoPK>  {
	private static final long serialVersionUID = 1L;

	@Column
	private String numerofactura;
	@Column
	private String tipoformapago;
	@Column
	private Integer tipoformapagocodigo;
	@Column
	private String institucion;
	@Column
	private Integer institucioncodigo;
	@Column
	@Temporal(TemporalType.DATE)
	private Date fechapago;
	@Column
	private String numerodocumento;
	@Column
	private BigDecimal valor;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="tipoformapago", referencedColumnName="codigocatalogo" , insertable=false, updatable=false),
		@JoinColumn(name="tipoformapagocodigo", referencedColumnName="codigotipocatalogo" , insertable=false, updatable=false),
		@JoinColumn(name="codigocompania", referencedColumnName="codigocompania" , insertable=false, updatable=false)
		})
	private Tadmcatalogo tadmtipoformapago;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="institucion", referencedColumnName="codigocatalogo", insertable=false, updatable=false),
		@JoinColumn(name="institucioncodigo", referencedColumnName="codigotipocatalogo", insertable=false, updatable=false),
		@JoinColumn(name="codigocompania", referencedColumnName="codigocompania", insertable=false, updatable=false)
		})
	private Tadmcatalogo tadminstitucion;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="codigocompania", referencedColumnName="codigocompania" , insertable=false, updatable=false),
		@JoinColumn(name="numerofactura", referencedColumnName="numerofactura" , insertable=false, updatable=false)
		})
	private Tfaccabfactura tfaccabfactura;

	public Tfacformapago() {
		this.pk = new TfacformapagoPK();
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


	public Tadmcatalogo getTadmtipoformapago() {
		return tadmtipoformapago;
	}

	public void setTadmtipoformapago(Tadmcatalogo tadmtipoformapago) {
		this.tadmtipoformapago = tadmtipoformapago;
	}

	public String getNumerofactura() {
		return numerofactura;
	}

	public void setNumerofactura(String numerofactura) {
		this.numerofactura = numerofactura;
	}

	public Tadmcatalogo getTadminstitucion() {
		return tadminstitucion;
	}

	public void setTadminstitucion(Tadmcatalogo tadminstitucion) {
		this.tadminstitucion = tadminstitucion;
	}

	public Tfaccabfactura getTfaccabfactura() {
		return tfaccabfactura;
	}

	public void setTfaccabfactura(Tfaccabfactura tfaccabfactura) {
		this.tfaccabfactura = tfaccabfactura;
	}


}
package ec.com.dlc.bunsys.entity.facturacion;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ec.com.dlc.bunsys.entity.administracion.Tadmcatalogo;
import ec.com.dlc.bunsys.entity.base.BaseEntity;
import ec.com.dlc.bunsys.entity.facturacion.pk.TfacclientePK;
import ec.com.dlc.bunsys.entity.seguridad.Tsyspersona;


/**
 * The persistent class for the tfacclientes database table.
 * 
 */
@Entity
@Table(name="tfacclientes")
public class Tfaccliente extends BaseEntity<TfacclientePK>{
	private static final long serialVersionUID = 1L;

	@Column
	private Double limitecredito;

	@Column
	private Double porcentajedescuento;
	
	@Column
	private String formapago;
	
	@Column
	private Integer formapagocodigo;
	
	@Column
	private String grupocliente;
	
	@Column
	private Integer grupoclientecodigo;
	
	@Column
	private Integer codigopersona;

	//bi-directional many-to-one association to Tfaccabfactura
	@OneToMany(mappedBy="tfaccliente")
	private Set<Tfaccabfactura> tfaccabfacturas;
	
	//bi-directional many-to-one association to Tadmcatalogo
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="codigocompania", referencedColumnName="codigocompania", insertable=false, updatable=false),
		@JoinColumn(name="formapago", referencedColumnName="codigocatalogo", insertable=false, updatable=false),
		@JoinColumn(name="formapagocodigo", referencedColumnName="codigotipocatalogo", insertable=false, updatable=false)
		})
	private Tadmcatalogo tadmformapago;

	//bi-directional many-to-one association to Tadmcatalogo
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="codigocompania", referencedColumnName="codigocompania", insertable=false, updatable=false),
		@JoinColumn(name="grupocliente", referencedColumnName="codigocatalogo", insertable=false, updatable=false),
		@JoinColumn(name="grupoclientecodigo", referencedColumnName="codigotipocatalogo", insertable=false, updatable=false)
		})
	private Tadmcatalogo tadmgrupocliente;

	//bi-directional many-to-one association to Tsyspersona
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="codigocompania", referencedColumnName="codigocompania", insertable=false, updatable=false),
		@JoinColumn(name="codigopersona", referencedColumnName="codigopersona", insertable=false, updatable=false)
		})
	private Tsyspersona tsyspersona;

	public Tfaccliente() {
		this.pk = new TfacclientePK();
	}

	public Double getLimitecredito() {
		return this.limitecredito;
	}

	public void setLimitecredito(Double limitecredito) {
		this.limitecredito = limitecredito;
	}

	public Double getPorcentajedescuento() {
		return this.porcentajedescuento;
	}

	public void setPorcentajedescuento(Double porcentajedescuento) {
		this.porcentajedescuento = porcentajedescuento;
	}

	public Set<Tfaccabfactura> getTfaccabfacturas() {
		return this.tfaccabfacturas;
	}

	public void setTfaccabfacturas(Set<Tfaccabfactura> tfaccabfacturas) {
		this.tfaccabfacturas = tfaccabfacturas;
	}

	public Tfaccabfactura addTfaccabfactura(Tfaccabfactura tfaccabfactura) {
		getTfaccabfacturas().add(tfaccabfactura);
		tfaccabfactura.setTfaccliente(this);

		return tfaccabfactura;
	}

	public Tfaccabfactura removeTfaccabfactura(Tfaccabfactura tfaccabfactura) {
		getTfaccabfacturas().remove(tfaccabfactura);
		tfaccabfactura.setTfaccliente(null);

		return tfaccabfactura;
	}

	public Tadmcatalogo getTadmformapago() {
		return this.tadmformapago;
	}

	public void setTadmformapago(Tadmcatalogo tadmformapago) {
		this.tadmformapago = tadmformapago;
	}

	public Tadmcatalogo getTadmgrupocliente() {
		return this.tadmgrupocliente;
	}

	public void setTadmgrupocliente(Tadmcatalogo tadmgrupocliente) {
		this.tadmgrupocliente = tadmgrupocliente;
	}

	public Tsyspersona getTsyspersona() {
		return this.tsyspersona;
	}

	public void setTsyspersona(Tsyspersona tsyspersona) {
		this.tsyspersona = tsyspersona;
	}

	public String getFormapago() {
		return formapago;
	}

	public void setFormapago(String formapago) {
		this.formapago = formapago;
	}

	public Integer getFormapagocodigo() {
		return formapagocodigo;
	}

	public void setFormapagocodigo(Integer formapagocodigo) {
		this.formapagocodigo = formapagocodigo;
	}

	public String getGrupocliente() {
		return grupocliente;
	}

	public void setGrupocliente(String grupocliente) {
		this.grupocliente = grupocliente;
	}

	public Integer getGrupoclientecodigo() {
		return grupoclientecodigo;
	}

	public void setGrupoclientecodigo(Integer grupoclientecodigo) {
		this.grupoclientecodigo = grupoclientecodigo;
	}

	public Integer getCodigopersona() {
		return codigopersona;
	}

	public void setCodigopersona(Integer codigopersona) {
		this.codigopersona = codigopersona;
	}

}
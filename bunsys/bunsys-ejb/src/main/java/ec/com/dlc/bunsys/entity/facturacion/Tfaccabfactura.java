package ec.com.dlc.bunsys.entity.facturacion;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ec.com.dlc.bunsys.entity.administracion.Tadmcatalogo;
import ec.com.dlc.bunsys.entity.base.BaseEntity;
import ec.com.dlc.bunsys.entity.facturacion.pk.TfaccabfacturaPK;


/**
 * The persistent class for the tfaccabfactura database table.
 * 
 */
@Entity
public class Tfaccabfactura extends BaseEntity<TfaccabfacturaPK> {
	private static final long serialVersionUID = 1L;

	@Column
	@Temporal(TemporalType.DATE)
	private Date fechafactura;

	
	@Column
	private String referendum;
	
	@Column
	private String aerolinea;
	
	@Column
	private Integer aerolineacodigo;
	
	@Column
	private Integer codigocliente;

	//bi-directional many-to-one association to Tadmcatalogo
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="aerolinea", referencedColumnName="codigocatalogo", insertable=false, updatable=false),
		@JoinColumn(name="aerolineacodigo", referencedColumnName="codigotipocatalogo", insertable=false, updatable=false),
		@JoinColumn(name="codigocompania", referencedColumnName="codigocompania", insertable=false, updatable=false)
		})
	private Tadmcatalogo tadmcatalogo;

	//bi-directional many-to-one association to Tfaccliente
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="codigocliente", referencedColumnName="codigocliente", insertable=false, updatable=false),
		@JoinColumn(name="codigocompania", referencedColumnName="codigocompania", insertable=false, updatable=false)
		})
	private Tfaccliente tfaccliente;

	//bi-directional many-to-one association to Tfaccliente
	@OneToMany(mappedBy="tfaccabfactura")
	private Set<Tfacdetfactura> tfacdetfacturas;
		
	public Tfaccabfactura() {
	}

	public Date getFechafactura() {
		return this.fechafactura;
	}

	public void setFechafactura(Date fechafactura) {
		this.fechafactura = fechafactura;
	}

	public String getReferendum() {
		return this.referendum;
	}

	public void setReferendum(String referendum) {
		this.referendum = referendum;
	}

	public Tadmcatalogo getTadmcatalogo() {
		return this.tadmcatalogo;
	}

	public void setTadmcatalogo(Tadmcatalogo tadmcatalogo) {
		this.tadmcatalogo = tadmcatalogo;
	}

	public Tfaccliente getTfaccliente() {
		return this.tfaccliente;
	}

	public void setTfaccliente(Tfaccliente tfaccliente) {
		this.tfaccliente = tfaccliente;
	}

	public Set<Tfacdetfactura> getTfacdetfacturas() {
		return tfacdetfacturas;
	}

	public void setTfacdetfacturas(Set<Tfacdetfactura> tfacdetfacturas) {
		this.tfacdetfacturas = tfacdetfacturas;
	}

	public String getAerolinea() {
		return aerolinea;
	}

	public void setAerolinea(String aerolinea) {
		this.aerolinea = aerolinea;
	}

	public Integer getAerolineacodigo() {
		return aerolineacodigo;
	}

	public void setAerolineacodigo(Integer aerolineacodigo) {
		this.aerolineacodigo = aerolineacodigo;
	}

	public Integer getCodigocliente() {
		return codigocliente;
	}

	public void setCodigocliente(Integer codigocliente) {
		this.codigocliente = codigocliente;
	}

}
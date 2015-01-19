package ec.com.dlc.bunsys.entity.administracion;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import ec.com.dlc.bunsys.entity.administracion.pk.TadmtipocatalogoPK;
import ec.com.dlc.bunsys.entity.base.BaseEntity;


/**
 * The persistent class for the tadmtipocatalogo database table.
 * 
 */
@Entity
public class Tadmtipocatalogo extends BaseEntity<TadmtipocatalogoPK> {
	private static final long serialVersionUID = 1L;

	@Column
	private String descripcion;

	@Column
	private String estado;

	//bi-directional many-to-one association to Tadmcatalogo
	@OneToMany(mappedBy="tadmtipocatalogo")
	private Set<Tadmcatalogo> tadmcatalogos;

	//bi-directional many-to-one association to Tadmcompania
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigocompania", insertable=false, updatable=false)
	private Tadmcompania tadmcompania;

	public Tadmtipocatalogo() {
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Set<Tadmcatalogo> getTadmcatalogos() {
		return this.tadmcatalogos;
	}

	public void setTadmcatalogos(Set<Tadmcatalogo> tadmcatalogos) {
		this.tadmcatalogos = tadmcatalogos;
	}

	public Tadmcatalogo addTadmcatalogo(Tadmcatalogo tadmcatalogo) {
		getTadmcatalogos().add(tadmcatalogo);
		tadmcatalogo.setTadmtipocatalogo(this);

		return tadmcatalogo;
	}

	public Tadmcatalogo removeTadmcatalogo(Tadmcatalogo tadmcatalogo) {
		getTadmcatalogos().remove(tadmcatalogo);
		tadmcatalogo.setTadmtipocatalogo(null);

		return tadmcatalogo;
	}

	public Tadmcompania getTadmcompania() {
		return this.tadmcompania;
	}

	public void setTadmcompania(Tadmcompania tadmcompania) {
		this.tadmcompania = tadmcompania;
	}

}
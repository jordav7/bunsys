package ec.com.dlc.bunsys.entity.administracion;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ec.com.dlc.bunsys.entity.administracion.pk.TadmrolePK;
import ec.com.dlc.bunsys.entity.base.BaseEntity;


/**
 * The persistent class for the tadmroles database table.
 * 
 */
@Entity
@Table(name="tadmroles")
public class Tadmrole extends BaseEntity<TadmrolePK>{
	private static final long serialVersionUID = 1L;

	@Column
	private String descripcion;

	//bi-directional many-to-one association to Tadmcompania
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigocompania", insertable=false, updatable=false)
	private Tadmcompania tadmcompania;

	//bi-directional many-to-many association to Tadmusuario
	@OneToMany(mappedBy="tadmrole")
	private Collection<Tadmrolusuario> tadmrolusuarios;

	public Tadmrole() {
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Tadmcompania getTadmcompania() {
		return this.tadmcompania;
	}

	public void setTadmcompania(Tadmcompania tadmcompania) {
		this.tadmcompania = tadmcompania;
	}

	public Collection<Tadmrolusuario> getTadmrolusuarios() {
		return tadmrolusuarios;
	}

	public void setTadmrolusuarios(Collection<Tadmrolusuario> tadmrolusuarios) {
		this.tadmrolusuarios = tadmrolusuarios;
	}

}
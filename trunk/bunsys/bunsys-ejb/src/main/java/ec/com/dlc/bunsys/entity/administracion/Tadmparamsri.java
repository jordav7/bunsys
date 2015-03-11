package ec.com.dlc.bunsys.entity.administracion;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import ec.com.dlc.bunsys.entity.administracion.pk.TadmparamsriPK;
import ec.com.dlc.bunsys.entity.base.BaseEntity;


/**
 * The persistent class for the tadmparamsri database table.
 * 
 */
@Entity
public class Tadmparamsri extends BaseEntity<TadmparamsriPK>  {
	private static final long serialVersionUID = 1L;

	@Column
	private String cxc;

	@Column
	private String cxp;

	@Column
	private String descripcion;

	@Column
	private String estado;

	@Column
	private String observacion;

	//bi-directional many-to-one association to Tadmtipoparamsri
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigotipoparamsri", insertable=false, updatable=false)
	private Tadmtipoparamsri tadmtipoparamsri;

	@OneToMany(mappedBy="tadmtipoambiente")
	private Set<Tadmcompania> tadmcompania;
	
	public Tadmparamsri() {
	}

	public String getCxc() {
		return this.cxc;
	}

	public void setCxc(String cxc) {
		this.cxc = cxc;
	}

	public String getCxp() {
		return this.cxp;
	}

	public void setCxp(String cxp) {
		this.cxp = cxp;
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

	public String getObservacion() {
		return this.observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Tadmtipoparamsri getTadmtipoparamsri() {
		return this.tadmtipoparamsri;
	}

	public void setTadmtipoparamsri(Tadmtipoparamsri tadmtipoparamsri) {
		this.tadmtipoparamsri = tadmtipoparamsri;
	}

	public Set<Tadmcompania> getTadmcompania() {
		return tadmcompania;
	}

	public void setTadmcompania(Set<Tadmcompania> tadmcompania) {
		this.tadmcompania = tadmcompania;
	}

	
}
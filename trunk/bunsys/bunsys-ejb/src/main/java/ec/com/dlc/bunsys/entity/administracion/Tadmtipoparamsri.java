package ec.com.dlc.bunsys.entity.administracion;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import ec.com.dlc.bunsys.entity.administracion.pk.TadmtipoparamsriPK;
import ec.com.dlc.bunsys.entity.base.BaseEntity;


/**
 * The persistent class for the tadmtipoparamsri database table.
 * 
 */
@Entity
public class Tadmtipoparamsri extends BaseEntity<TadmtipoparamsriPK>  {
	private static final long serialVersionUID = 1L;

	@Column
	private String descripcion;

	//bi-directional many-to-one association to Tadmparamsri
	@OneToMany(mappedBy="tadmtipoparamsri")
	private List<Tadmparamsri> tadmparamsris;

	public Tadmtipoparamsri() {
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Tadmparamsri> getTadmparamsris() {
		return this.tadmparamsris;
	}

	public void setTadmparamsris(List<Tadmparamsri> tadmparamsris) {
		this.tadmparamsris = tadmparamsris;
	}

	public Tadmparamsri addTadmparamsri(Tadmparamsri tadmparamsri) {
		getTadmparamsris().add(tadmparamsri);
		tadmparamsri.setTadmtipoparamsri(this);

		return tadmparamsri;
	}

	public Tadmparamsri removeTadmparamsri(Tadmparamsri tadmparamsri) {
		getTadmparamsris().remove(tadmparamsri);
		tadmparamsri.setTadmtipoparamsri(null);

		return tadmparamsri;
	}

}
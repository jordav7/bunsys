package ec.com.dlc.bunsys.entity.administracion.pk;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import ec.com.dlc.bunsys.entity.base.BasePK;

/**
 * The primary key class for the tadmroles database table.
 * 
 */
@Embeddable
public class TadmrolePK extends BasePK{
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column
	private Integer codigorol;

	@Column
	private Integer codigocompania;

	public TadmrolePK() {
	}
	public Integer getCodigorol() {
		return this.codigorol;
	}
	public void setCodigorol(Integer codigorol) {
		this.codigorol = codigorol;
	}
	public Integer getCodigocompania() {
		return this.codigocompania;
	}
	public void setCodigocompania(Integer codigocompania) {
		this.codigocompania = codigocompania;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TadmrolePK)) {
			return false;
		}
		TadmrolePK castOther = (TadmrolePK)other;
		return 
			this.codigorol.equals(castOther.codigorol)
			&& this.codigocompania.equals(castOther.codigocompania);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codigorol.hashCode();
		hash = hash * prime + this.codigocompania.hashCode();
		
		return hash;
	}
}
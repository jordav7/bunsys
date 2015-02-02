package ec.com.dlc.bunsys.entity.administracion.pk;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import ec.com.dlc.bunsys.entity.base.BasePK;

/**
 * The primary key class for the tadmparamsri database table.
 * 
 */
@Embeddable
public class TadmparamsriPK extends BasePK{
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column
	private String codigoparamsri;

	@Column
	private Integer codigotipoparamsri;

	public TadmparamsriPK() {
	}
	public String getCodigoparamsri() {
		return this.codigoparamsri;
	}
	public void setCodigoparamsri(String codigoparamsri) {
		this.codigoparamsri = codigoparamsri;
	}
	public Integer getCodigotipoparamsri() {
		return this.codigotipoparamsri;
	}
	public void setCodigotipoparamsri(Integer codigotipoparamsri) {
		this.codigotipoparamsri = codigotipoparamsri;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TadmparamsriPK)) {
			return false;
		}
		TadmparamsriPK castOther = (TadmparamsriPK)other;
		return 
			this.codigoparamsri.equals(castOther.codigoparamsri)
			&& this.codigotipoparamsri.equals(castOther.codigotipoparamsri);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codigoparamsri.hashCode();
		hash = hash * prime + this.codigotipoparamsri.hashCode();
		
		return hash;
	}
}
package ec.com.dlc.bunsys.entity.facturacion.pk;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import ec.com.dlc.bunsys.entity.base.BasePK;

/**
 * The primary key class for the Tfaccabproforma database table.
 * 
 */
@Embeddable
public class TfaccabproformaPK extends BasePK {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column
	private String numeroproforma;

	@Column
	private Integer codigocompania;

	public TfaccabproformaPK() {
	}

	public String getNumeroproforma() {
		return numeroproforma;
	}

	public void setNumeroproforma(String numeroproforma) {
		this.numeroproforma = numeroproforma;
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
		if (!(other instanceof TfaccabproformaPK)) {
			return false;
		}
		TfaccabproformaPK castOther = (TfaccabproformaPK)other;
		return 
			this.numeroproforma.equals(castOther.numeroproforma)
			&& this.codigocompania.equals(castOther.codigocompania);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.numeroproforma.hashCode();
		hash = hash * prime + this.codigocompania.hashCode();
		
		return hash;
	}
}
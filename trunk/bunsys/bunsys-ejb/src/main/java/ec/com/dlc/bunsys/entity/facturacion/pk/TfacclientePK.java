package ec.com.dlc.bunsys.entity.facturacion.pk;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import ec.com.dlc.bunsys.entity.base.BasePK;

/**
 * The primary key class for the tfacclientes database table.
 * 
 */
@Embeddable
public class TfacclientePK extends BasePK{
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column
	private Integer codigocliente;

	@Column
	private Integer codigocompania;

	public TfacclientePK() {
	}
	public Integer getCodigocliente() {
		return this.codigocliente;
	}
	public void setCodigocliente(Integer codigocliente) {
		this.codigocliente = codigocliente;
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
		if (!(other instanceof TfacclientePK)) {
			return false;
		}
		TfacclientePK castOther = (TfacclientePK)other;
		return 
			this.codigocliente.equals(castOther.codigocliente)
			&& this.codigocompania.equals(castOther.codigocompania);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codigocliente.hashCode();
		hash = hash * prime + this.codigocompania.hashCode();
		
		return hash;
	}
}
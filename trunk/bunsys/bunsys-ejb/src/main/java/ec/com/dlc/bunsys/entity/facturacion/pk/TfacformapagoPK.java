package ec.com.dlc.bunsys.entity.facturacion.pk;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import ec.com.dlc.bunsys.commons.annotations.SequenceDatabase;
import ec.com.dlc.bunsys.commons.enumeration.TypeGenerator;
import ec.com.dlc.bunsys.entity.base.BasePK;

/**
 * The primary key class for the tfacformapago database table.
 * 
 */
@Embeddable
public class TfacformapagoPK extends BasePK {
	//default serial version id, required for serializable classes.codigocompania
	
	private static final long serialVersionUID = 1L;

	@Column
	@SequenceDatabase(sequenceName="tfacformapago_codigoformapago_seq", typeGenerator=TypeGenerator.SEQUENCE)
	private Integer codigoformapago;

	@Column
	private Integer codigocompania;


	public TfacformapagoPK() {
	}
	
	public Integer getCodigoformapago() {
		return codigoformapago;
	}

	public void setCodigoformapago(Integer codigoformapago) {
		this.codigoformapago = codigoformapago;
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
		if (!(other instanceof TfacformapagoPK)) {
			return false;
		}
		TfacformapagoPK castOther = (TfacformapagoPK)other;
		return 
			this.codigoformapago.equals(castOther.codigoformapago)
			&& this.codigocompania.equals(castOther.codigocompania);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codigoformapago.hashCode();
		hash = hash * prime + this.codigocompania.hashCode();
		return hash;
	}
}
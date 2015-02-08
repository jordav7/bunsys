package ec.com.dlc.bunsys.entity.seguridad.pk;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import ec.com.dlc.bunsys.entity.base.BasePK;

/**
 * The primary key class for the tsyspersona database table.
 * 
 */
@Embeddable
public class TsyspersonaPK extends BasePK {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@SequenceGenerator(name="seq_persona", sequenceName="tsyspersona_codigopersona_seq", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_persona")
	@Column
	private Integer codigopersona;

	@Column
	private Integer codigocompania;

	public TsyspersonaPK() {
	}
	public Integer getCodigopersona() {
		return this.codigopersona;
	}
	public void setCodigopersona(Integer codigopersona) {
		this.codigopersona = codigopersona;
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
		if (!(other instanceof TsyspersonaPK)) {
			return false;
		}
		TsyspersonaPK castOther = (TsyspersonaPK)other;
		return 
			this.codigopersona.equals(castOther.codigopersona)
			&& this.codigocompania.equals(castOther.codigocompania);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codigopersona.hashCode();
		hash = hash * prime + this.codigocompania.hashCode();
		
		return hash;
	}
}
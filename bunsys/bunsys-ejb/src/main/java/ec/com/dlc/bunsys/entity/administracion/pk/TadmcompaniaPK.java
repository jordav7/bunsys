package ec.com.dlc.bunsys.entity.administracion.pk;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.UpdateTimestamp;

import ec.com.dlc.bunsys.entity.base.BasePK;

@Embeddable
public class TadmcompaniaPK extends BasePK {

	private static final long serialVersionUID = 5381779887640715823L;
	
	@Column(updatable=false)
	@SequenceGenerator(name="TADMCOMPANIA_CODIGOCOMPANIA_GENERATOR", sequenceName="TADMCOMPANIA_CODIGOCOMPANIA_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TADMCOMPANIA_CODIGOCOMPANIA_GENERATOR")
	private Integer codigocompania;

	public Integer getCodigocompania() {
		return codigocompania;
	}

	public void setCodigocompania(Integer codigocompania) {
		this.codigocompania = codigocompania;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codigocompania == null) ? 0 : codigocompania.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TadmcompaniaPK other = (TadmcompaniaPK) obj;
		if (codigocompania == null) {
			if (other.codigocompania != null)
				return false;
		} else if (!codigocompania.equals(other.codigocompania))
			return false;
		return true;
	}
	
	
}

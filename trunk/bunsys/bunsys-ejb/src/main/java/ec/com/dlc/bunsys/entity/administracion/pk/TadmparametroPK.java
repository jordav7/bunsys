package ec.com.dlc.bunsys.entity.administracion.pk;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import ec.com.dlc.bunsys.entity.base.BasePK;

@Embeddable
public class TadmparametroPK extends BasePK {

	private static final long serialVersionUID = 8167874273110076619L;

	@Column
	@SequenceGenerator(name="TADMPARAMETROS_CODIGOPARAMETROS_GENERATOR", sequenceName="TADMPARAMETROS_CODIGOPARAMETROS_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TADMPARAMETROS_CODIGOPARAMETROS_GENERATOR")
	private Integer codigoparametros;
	
	@Column
	private Integer codigocompania;

	public Integer getCodigoparametros() {
		return codigoparametros;
	}

	public void setCodigoparametros(Integer codigoparametros) {
		this.codigoparametros = codigoparametros;
	}

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
		result = prime
				* result
				+ ((codigoparametros == null) ? 0 : codigoparametros.hashCode());
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
		TadmparametroPK other = (TadmparametroPK) obj;
		if (codigocompania == null) {
			if (other.codigocompania != null)
				return false;
		} else if (!codigocompania.equals(other.codigocompania))
			return false;
		if (codigoparametros == null) {
			if (other.codigoparametros != null)
				return false;
		} else if (!codigoparametros.equals(other.codigoparametros))
			return false;
		return true;
	}
	
	
}

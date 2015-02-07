package ec.com.dlc.bunsys.entity.administracion.pk;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import ec.com.dlc.bunsys.entity.base.BasePK;

public class TadmtipoparamsriPK extends BasePK {

	private static final long serialVersionUID = 3821406388793809415L;

	@SequenceGenerator(name="TADMTIPOPARAMSRI_CODIGOTIPOPARAMSRI_GENERATOR", sequenceName="TADMTIPOPARAMSRI_CODIGOTIPOPARAMSRI_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TADMTIPOPARAMSRI_CODIGOTIPOPARAMSRI_GENERATOR")
	private Integer codigotipoparamsri;

	public Integer getCodigotipoparamsri() {
		return codigotipoparamsri;
	}

	public void setCodigotipoparamsri(Integer codigotipoparamsri) {
		this.codigotipoparamsri = codigotipoparamsri;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((codigotipoparamsri == null) ? 0 : codigotipoparamsri
						.hashCode());
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
		TadmtipoparamsriPK other = (TadmtipoparamsriPK) obj;
		if (codigotipoparamsri == null) {
			if (other.codigotipoparamsri != null)
				return false;
		} else if (!codigotipoparamsri.equals(other.codigotipoparamsri))
			return false;
		return true;
	}
}

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
}

package ec.com.dlc.bunsys.entity.administracion.pk;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import ec.com.dlc.bunsys.entity.base.BasePK;

@Embeddable
public class TadmcompaniaPK extends BasePK {

	private static final long serialVersionUID = 5381779887640715823L;
	
	@Column
	@SequenceGenerator(name="TADMCOMPANIA_CODIGOCOMPANIA_GENERATOR", sequenceName="TADMCOMPANIA_CODIGOCOMPANIA_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TADMCOMPANIA_CODIGOCOMPANIA_GENERATOR")
	private Integer codigocompania;

	public Integer getCodigocompania() {
		return codigocompania;
	}

	public void setCodigocompania(Integer codigocompania) {
		this.codigocompania = codigocompania;
	}
}

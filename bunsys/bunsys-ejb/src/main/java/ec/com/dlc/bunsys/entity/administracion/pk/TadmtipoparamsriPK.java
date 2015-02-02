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
}

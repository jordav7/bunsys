package ec.com.dlc.bunsys.entity.administracion.pk;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import ec.com.dlc.bunsys.entity.base.BasePK;

/**
 * The primary key class for the tadmconversionunidad database table.
 * 
 */
@Embeddable
public class TadmconversionunidadPK extends BasePK {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column
	private Integer codigocoversionunidad;

	@Column
	private Integer codigocompania;

	public TadmconversionunidadPK() {
	}
	public Integer getCodigocoversionunidad() {
		return this.codigocoversionunidad;
	}
	public void setCodigocoversionunidad(Integer codigocoversionunidad) {
		this.codigocoversionunidad = codigocoversionunidad;
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
		if (!(other instanceof TadmconversionunidadPK)) {
			return false;
		}
		TadmconversionunidadPK castOther = (TadmconversionunidadPK)other;
		return 
			this.codigocoversionunidad.equals(castOther.codigocoversionunidad)
			&& this.codigocompania.equals(castOther.codigocompania);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codigocoversionunidad.hashCode();
		hash = hash * prime + this.codigocompania.hashCode();
		
		return hash;
	}
}
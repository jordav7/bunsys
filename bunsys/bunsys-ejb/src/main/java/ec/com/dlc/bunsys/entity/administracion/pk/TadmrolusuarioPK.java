package ec.com.dlc.bunsys.entity.administracion.pk;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import ec.com.dlc.bunsys.entity.base.BasePK;

@Embeddable
public class TadmrolusuarioPK extends BasePK {
	private static final long serialVersionUID = 1L;

	@Column
	private Integer codigorol;

	@Column
	private Integer codigocompania;

	@Column
	private Integer codigousuario;

	public TadmrolusuarioPK() {
	}
	public Integer getCodigorol() {
		return this.codigorol;
	}
	public void setCodigorol(Integer codigorol) {
		this.codigorol = codigorol;
	}
	public Integer getCodigocompania() {
		return this.codigocompania;
	}
	public void setCodigocompania(Integer codigocompania) {
		this.codigocompania = codigocompania;
	}
	public Integer getCodigousuario() {
		return this.codigousuario;
	}
	public void setCodigousuario(Integer codigousuario) {
		this.codigousuario = codigousuario;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TadmrolusuarioPK)) {
			return false;
		}
		TadmrolusuarioPK castOther = (TadmrolusuarioPK)other;
		return 
			this.codigorol.equals(castOther.codigorol)
			&& this.codigocompania.equals(castOther.codigocompania)
			&& this.codigousuario.equals(castOther.codigousuario);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codigorol.hashCode();
		hash = hash * prime + this.codigocompania.hashCode();
		hash = hash * prime + this.codigousuario.hashCode();
		
		return hash;
	}
}

package ec.com.dlc.bunsys.entity.administracion.pk;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import ec.com.dlc.bunsys.commons.annotations.SequenceDatabase;
import ec.com.dlc.bunsys.commons.enumeration.TypeGenerator;
import ec.com.dlc.bunsys.entity.base.BasePK;

/**
 * The primary key class for the tadmusuario database table.
 * 
 */
@Embeddable
public class TadmusuarioPK extends BasePK {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@SequenceDatabase(sequenceName="tadmusuario_codigousuario_seq", typeGenerator=TypeGenerator.SEQUENCE)
	@Column
	private Integer codigousuario;

	@Column
	private Integer codigocompania;

	public TadmusuarioPK() {
	}
	public Integer getCodigousuario() {
		return this.codigousuario;
	}
	public void setCodigousuario(Integer codigousuario) {
		this.codigousuario = codigousuario;
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
		if (!(other instanceof TadmusuarioPK)) {
			return false;
		}
		TadmusuarioPK castOther = (TadmusuarioPK)other;
		return 
			this.codigousuario.equals(castOther.codigousuario)
			&& this.codigocompania.equals(castOther.codigocompania);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codigousuario.hashCode();
		hash = hash * prime + this.codigocompania.hashCode();
		
		return hash;
	}
}
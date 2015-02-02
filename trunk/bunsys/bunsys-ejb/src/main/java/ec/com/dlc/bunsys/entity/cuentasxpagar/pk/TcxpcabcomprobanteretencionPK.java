package ec.com.dlc.bunsys.entity.cuentasxpagar.pk;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import ec.com.dlc.bunsys.entity.base.BasePK;

/**
 * The primary key class for the tcxpcabcomprobanteretencion database table.
 * 
 */
@Embeddable
public class TcxpcabcomprobanteretencionPK extends BasePK {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column
	private String codigocabcomprobanteretencion;

	@Column
	private Integer codigocompania;

	public TcxpcabcomprobanteretencionPK() {
	}
	public String getCodigocabcomprobanteretencion() {
		return this.codigocabcomprobanteretencion;
	}
	public void setCodigocabcomprobanteretencion(String codigocabcomprobanteretencion) {
		this.codigocabcomprobanteretencion = codigocabcomprobanteretencion;
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
		if (!(other instanceof TcxpcabcomprobanteretencionPK)) {
			return false;
		}
		TcxpcabcomprobanteretencionPK castOther = (TcxpcabcomprobanteretencionPK)other;
		return 
			this.codigocabcomprobanteretencion.equals(castOther.codigocabcomprobanteretencion)
			&& this.codigocompania.equals(castOther.codigocompania);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codigocabcomprobanteretencion.hashCode();
		hash = hash * prime + this.codigocompania.hashCode();
		
		return hash;
	}
}
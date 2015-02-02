package ec.com.dlc.bunsys.entity.cuentasxpagar.pk;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import ec.com.dlc.bunsys.entity.base.BasePK;

/**
 * The primary key class for the tcxpdetcomprobanteretencion database table.
 * 
 */
@Embeddable
public class TcxpdetcomprobanteretencionPK extends BasePK{
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column
	private String codigodetcomprobanteretencion;

	@Column
	private Integer codigocompania;

	public TcxpdetcomprobanteretencionPK() {
	}
	public String getCodigodetcomprobanteretencion() {
		return this.codigodetcomprobanteretencion;
	}
	public void setCodigodetcomprobanteretencion(String codigodetcomprobanteretencion) {
		this.codigodetcomprobanteretencion = codigodetcomprobanteretencion;
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
		if (!(other instanceof TcxpdetcomprobanteretencionPK)) {
			return false;
		}
		TcxpdetcomprobanteretencionPK castOther = (TcxpdetcomprobanteretencionPK)other;
		return 
			this.codigodetcomprobanteretencion.equals(castOther.codigodetcomprobanteretencion)
			&& this.codigocompania.equals(castOther.codigocompania);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codigodetcomprobanteretencion.hashCode();
		hash = hash * prime + this.codigocompania.hashCode();
		
		return hash;
	}
}
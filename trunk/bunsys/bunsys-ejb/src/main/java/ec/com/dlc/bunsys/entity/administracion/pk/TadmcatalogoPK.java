package ec.com.dlc.bunsys.entity.administracion.pk;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import ec.com.dlc.bunsys.entity.base.BasePK;

/**
 * The primary key class for the tadmcatalogo database table.
 * 
 */
@Embeddable
public class TadmcatalogoPK extends BasePK {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column
	private String codigocatalogo;

	@Column
	private Integer codigotipocatalogo;

	@Column
	private Integer codigocompania;

	public TadmcatalogoPK() {
	}
	public String getCodigocatalogo() {
		return this.codigocatalogo;
	}
	public void setCodigocatalogo(String codigocatalogo) {
		this.codigocatalogo = codigocatalogo;
	}
	public Integer getCodigotipocatalogo() {
		return this.codigotipocatalogo;
	}
	public void setCodigotipocatalogo(Integer codigotipocatalogo) {
		this.codigotipocatalogo = codigotipocatalogo;
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
		if (!(other instanceof TadmcatalogoPK)) {
			return false;
		}
		TadmcatalogoPK castOther = (TadmcatalogoPK)other;
		return 
			this.codigocatalogo.equals(castOther.codigocatalogo)
			&& this.codigotipocatalogo.equals(castOther.codigotipocatalogo)
			&& this.codigocompania.equals(castOther.codigocompania);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codigocatalogo.hashCode();
		hash = hash * prime + this.codigotipocatalogo.hashCode();
		hash = hash * prime + this.codigocompania.hashCode();
		
		return hash;
	}
}
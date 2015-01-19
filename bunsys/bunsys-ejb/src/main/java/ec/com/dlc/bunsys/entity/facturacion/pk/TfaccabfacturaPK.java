package ec.com.dlc.bunsys.entity.facturacion.pk;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import ec.com.dlc.bunsys.entity.base.BasePK;

/**
 * The primary key class for the tfaccabfactura database table.
 * 
 */
@Embeddable
public class TfaccabfacturaPK extends BasePK{
	
	private static final long serialVersionUID = 1L;

	@Column
	private Integer codigocompania;

	@Column
	private Integer codigocabfactura;

	public TfaccabfacturaPK() {
	}
	public Integer getCodigocompania() {
		return this.codigocompania;
	}
	public void setCodigocompania(Integer codigocompania) {
		this.codigocompania = codigocompania;
	}
	public Integer getCodigocabfactura() {
		return this.codigocabfactura;
	}
	public void setCodigocabfactura(Integer codigocabfactura) {
		this.codigocabfactura = codigocabfactura;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TfaccabfacturaPK)) {
			return false;
		}
		TfaccabfacturaPK castOther = (TfaccabfacturaPK)other;
		return 
			this.codigocompania.equals(castOther.codigocompania)
			&& this.codigocabfactura.equals(castOther.codigocabfactura);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codigocompania.hashCode();
		hash = hash * prime + this.codigocabfactura.hashCode();
		
		return hash;
	}
}
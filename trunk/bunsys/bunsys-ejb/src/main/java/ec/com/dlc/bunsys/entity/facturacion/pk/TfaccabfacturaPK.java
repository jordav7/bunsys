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
	private String numerofactura;

	public TfaccabfacturaPK() {
	}
	public Integer getCodigocompania() {
		return this.codigocompania;
	}
	public void setCodigocompania(Integer codigocompania) {
		this.codigocompania = codigocompania;
	}

	
	public String getNumerofactura() {
		return numerofactura;
	}
	public void setNumerofactura(String numerofactura) {
		this.numerofactura = numerofactura;
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
			&& this.numerofactura.equals(castOther.numerofactura);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codigocompania.hashCode();
		hash = hash * prime + this.numerofactura.hashCode();
		
		return hash;
	}
}
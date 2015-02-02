package ec.com.dlc.bunsys.entity.cuentasxpagar.pk;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import ec.com.dlc.bunsys.entity.base.BasePK;

/**
 * The primary key class for the tcxpproveedor database table.
 * 
 */
@Embeddable
public class TcxpproveedorPK extends BasePK {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column
	private String codigoproveedor;

	@Column
	private Integer codigocompania;

	public TcxpproveedorPK() {
	}
	public String getCodigoproveedor() {
		return this.codigoproveedor;
	}
	public void setCodigoproveedor(String codigoproveedor) {
		this.codigoproveedor = codigoproveedor;
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
		if (!(other instanceof TcxpproveedorPK)) {
			return false;
		}
		TcxpproveedorPK castOther = (TcxpproveedorPK)other;
		return 
			this.codigoproveedor.equals(castOther.codigoproveedor)
			&& this.codigocompania.equals(castOther.codigocompania);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codigoproveedor.hashCode();
		hash = hash * prime + this.codigocompania.hashCode();
		
		return hash;
	}
}
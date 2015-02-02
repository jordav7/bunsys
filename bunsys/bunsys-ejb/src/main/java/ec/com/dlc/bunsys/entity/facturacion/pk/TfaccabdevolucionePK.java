package ec.com.dlc.bunsys.entity.facturacion.pk;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import ec.com.dlc.bunsys.entity.base.BasePK;

/**
 * The primary key class for the tfaccabdevoluciones database table.
 * 
 */
@Embeddable
public class TfaccabdevolucionePK extends BasePK {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column
	private String numerodevoluciones;

	@Column
	private Integer codigocompania;

	public TfaccabdevolucionePK() {
	}
	public String getNumerodevoluciones() {
		return this.numerodevoluciones;
	}
	public void setNumerodevoluciones(String numerodevoluciones) {
		this.numerodevoluciones = numerodevoluciones;
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
		if (!(other instanceof TfaccabdevolucionePK)) {
			return false;
		}
		TfaccabdevolucionePK castOther = (TfaccabdevolucionePK)other;
		return 
			this.numerodevoluciones.equals(castOther.numerodevoluciones)
			&& this.codigocompania.equals(castOther.codigocompania);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.numerodevoluciones.hashCode();
		hash = hash * prime + this.codigocompania.hashCode();
		
		return hash;
	}
}
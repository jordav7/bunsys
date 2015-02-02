package ec.com.dlc.bunsys.entity.facturacion.pk;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the tfacformapagodev database table.
 * 
 */
@Embeddable
public class TfacformapagodevPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column
	private Integer codigoformapagodev;

	@Column
	private Integer codigocompania;

	@Column
	private String numerodevoluciones;

	public TfacformapagodevPK() {
	}
	public Integer getCodigoformapagodev() {
		return this.codigoformapagodev;
	}
	public void setCodigoformapagodev(Integer codigoformapagodev) {
		this.codigoformapagodev = codigoformapagodev;
	}
	public Integer getCodigocompania() {
		return this.codigocompania;
	}
	public void setCodigocompania(Integer codigocompania) {
		this.codigocompania = codigocompania;
	}
	public String getNumerodevoluciones() {
		return this.numerodevoluciones;
	}
	public void setNumerodevoluciones(String numerodevoluciones) {
		this.numerodevoluciones = numerodevoluciones;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TfacformapagodevPK)) {
			return false;
		}
		TfacformapagodevPK castOther = (TfacformapagodevPK)other;
		return 
			this.codigoformapagodev.equals(castOther.codigoformapagodev)
			&& this.codigocompania.equals(castOther.codigocompania)
			&& this.numerodevoluciones.equals(castOther.numerodevoluciones);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codigoformapagodev.hashCode();
		hash = hash * prime + this.codigocompania.hashCode();
		hash = hash * prime + this.numerodevoluciones.hashCode();
		
		return hash;
	}
}
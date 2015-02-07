package ec.com.dlc.bunsys.entity.facturacion.pk;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import ec.com.dlc.bunsys.entity.base.BasePK;

/**
 * The primary key class for the tfacdetfactura database table.
 * 
 */
@Embeddable
public class TfacdetfacturaPK extends BasePK{
	
	private static final long serialVersionUID = 1L;

	@Column
	private Integer codigocompania;

	@Column
	@SequenceGenerator(name="tfacdetfactura_seq", sequenceName="secfacdet")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tfacdetfactura_seq")
	private Integer codigodetfactura;

	public TfacdetfacturaPK() {
	}
	public Integer getCodigocompania() {
		return this.codigocompania;
	}
	public void setCodigocompania(Integer codigocompania) {
		this.codigocompania = codigocompania;
	}
	
	public Integer getCodigodetfactura() {
		return codigodetfactura;
	}
	public void setCodigodetfactura(Integer codigodetfactura) {
		this.codigodetfactura = codigodetfactura;
	}
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TfacdetfacturaPK)) {
			return false;
		}
		TfacdetfacturaPK castOther = (TfacdetfacturaPK)other;
		return 
			this.codigocompania.equals(castOther.codigocompania)
			&& this.codigodetfactura.equals(castOther.codigodetfactura);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codigocompania.hashCode();
		hash = hash * prime + this.codigodetfactura.hashCode();
		
		return hash;
	}
}
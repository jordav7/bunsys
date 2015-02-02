package ec.com.dlc.bunsys.entity.inventario.pk;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import ec.com.dlc.bunsys.entity.base.BasePK;

/**
 * The primary key class for the tinvproducto database table.
 * 
 */
@Embeddable
public class TinvproductoPK extends BasePK {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@SequenceGenerator(name="seqCodigoArticulo", sequenceName="tinvproducto_codigoproductos_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column
	private Integer codigoproductos;

	@Column
	private Integer codigocompania;

	public TinvproductoPK() {
	}
	public Integer getCodigoproductos() {
		return this.codigoproductos;
	}
	public void setCodigoproductos(Integer codigoproductos) {
		this.codigoproductos = codigoproductos;
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
		if (!(other instanceof TinvproductoPK)) {
			return false;
		}
		TinvproductoPK castOther = (TinvproductoPK)other;
		return 
			this.codigoproductos.equals(castOther.codigoproductos)
			&& this.codigocompania.equals(castOther.codigocompania);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codigoproductos.hashCode();
		hash = hash * prime + this.codigocompania.hashCode();
		
		return hash;
	}
}
package ec.com.dlc.bunsys.entity.facturacion.pk;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import ec.com.dlc.bunsys.commons.annotations.SequenceDatabase;
import ec.com.dlc.bunsys.commons.enumeration.TypeGenerator;
import ec.com.dlc.bunsys.entity.base.BasePK;

/**
 * The primary key class for the tfacdetdevoluciones database table.
 * 
 */
@Embeddable
public class TfacdetproformaPK extends BasePK {
	private static final long serialVersionUID = 1L;

	@Column
	@SequenceDatabase(sequenceName="tfacdetproforma_codigodetalleprof_seq", typeGenerator=TypeGenerator.SEQUENCE)
	private Integer codigodetalleprof ;

	@Column
	private Integer codigocompania;


	public TfacdetproformaPK() {
	}
	public Integer getCodigocompania() {
		return this.codigocompania;
	}
	public void setCodigocompania(Integer codigocompania) {
		this.codigocompania = codigocompania;
	}
	

	public Integer getCodigodetalleprof() {
		return codigodetalleprof;
	}
	public void setCodigodetalleprof(Integer codigodetalleprof) {
		this.codigodetalleprof = codigodetalleprof;
	}
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TfacdetdevolucionePK)) {
			return false;
		}
		TfacdetproformaPK castOther = (TfacdetproformaPK)other;
		return 
			this.codigodetalleprof.equals(castOther.codigodetalleprof)
			&& this.codigocompania.equals(castOther.codigocompania);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codigodetalleprof.hashCode();
		hash = hash * prime + this.codigocompania.hashCode();
		
		return hash;
	}
}
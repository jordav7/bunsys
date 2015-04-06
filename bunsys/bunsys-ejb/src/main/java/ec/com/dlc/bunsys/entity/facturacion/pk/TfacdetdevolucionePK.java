package ec.com.dlc.bunsys.entity.facturacion.pk;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import ec.com.dlc.bunsys.commons.annotations.SequenceDatabase;
import ec.com.dlc.bunsys.commons.enumeration.TypeGenerator;
import ec.com.dlc.bunsys.entity.base.BasePK;

/**
 * The primary key class for the tfacdetdevoluciones database table.
 * 
 */
@Embeddable
public class TfacdetdevolucionePK extends BasePK {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column
	private String numerodevoluciones;

	@Column
	private Integer codigocompania;

	@SequenceDatabase(sequenceName = "tfacdetdevolucione_renglondetdevoluciones_seq", typeGenerator = TypeGenerator.SEQUENCE)
	@Column
	private Integer renglondetdevoluciones;

	public TfacdetdevolucionePK() {
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
	public Integer getRenglondetdevoluciones() {
		return this.renglondetdevoluciones;
	}
	public void setRenglondetdevoluciones(Integer renglondetdevoluciones) {
		this.renglondetdevoluciones = renglondetdevoluciones;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TfacdetdevolucionePK)) {
			return false;
		}
		TfacdetdevolucionePK castOther = (TfacdetdevolucionePK)other;
		return 
			this.numerodevoluciones.equals(castOther.numerodevoluciones)
			&& this.codigocompania.equals(castOther.codigocompania)
			&& this.renglondetdevoluciones.equals(castOther.renglondetdevoluciones);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.numerodevoluciones.hashCode();
		hash = hash * prime + this.codigocompania.hashCode();
		hash = hash * prime + this.renglondetdevoluciones.hashCode();
		
		return hash;
	}
}
package ec.com.dlc.bunsys.entity.facturacion.pk;

import javax.persistence.Column;

import ec.com.dlc.bunsys.entity.base.BasePK;

public class TfaccuentasxcobrarPK extends BasePK{
	private static final long serialVersionUID = 3676827398935560579L;

	@Column
	private String codigocuenxcobr;
	
	@Column
	private Integer codigocompania;
	
	public TfaccuentasxcobrarPK() {
	}

	public String getCodigocuenxcobr() {
		return codigocuenxcobr;
	}

	public void setCodigocuenxcobr(String codigocuenxcobr) {
		this.codigocuenxcobr = codigocuenxcobr;
	}

	public Integer getCodigocompania() {
		return codigocompania;
	}

	public void setCodigocompania(Integer codigocompania) {
		this.codigocompania = codigocompania;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codigocompania == null) ? 0 : codigocompania.hashCode());
		result = prime * result
				+ ((codigocuenxcobr == null) ? 0 : codigocuenxcobr.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TfaccuentasxcobrarPK other = (TfaccuentasxcobrarPK) obj;
		if (codigocompania == null) {
			if (other.codigocompania != null)
				return false;
		} else if (!codigocompania.equals(other.codigocompania))
			return false;
		if (codigocuenxcobr == null) {
			if (other.codigocuenxcobr != null)
				return false;
		} else if (!codigocuenxcobr.equals(other.codigocuenxcobr))
			return false;
		return true;
	}
	
}

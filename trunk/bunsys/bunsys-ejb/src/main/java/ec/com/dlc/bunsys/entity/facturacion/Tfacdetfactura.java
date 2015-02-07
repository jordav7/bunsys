package ec.com.dlc.bunsys.entity.facturacion;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import ec.com.dlc.bunsys.entity.base.BaseEntity;
import ec.com.dlc.bunsys.entity.facturacion.pk.TfacdetfacturaPK;
import ec.com.dlc.bunsys.entity.inventario.Tinvproducto;


/**
 * The persistent class for the tfacdetfactura database table.
 * 
 */
@Entity
public class Tfacdetfactura extends BaseEntity<TfacdetfacturaPK> {
	private static final long serialVersionUID = 1L;


	@Column
	private String picestype;

	@Column
	private Double totalpices;
	
	@Column
	private Double eqfullboxes;
	
	@Column
	private String nandina;
	
	@Column
	private Double stemsbnuch;
	
	@Column
	private Double totalstemsbnuch;
	
	@Column
	private Double totalstems;
	
	@Column
	private Double totalprice;
	
	@Column
	private String codigoproductos;
	@Column
	private String numerofactura;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="codigoproductos", referencedColumnName="codigoproductos", insertable=false, updatable=false),
		@JoinColumn(name="codigocompania", referencedColumnName="codigocompania", insertable=false, updatable=false)
		})
	private Tinvproducto tinvproducto;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="numerofactura", referencedColumnName="numerofactura", insertable=false, updatable=false),
		@JoinColumn(name="codigocompania", referencedColumnName="codigocompania", insertable=false, updatable=false)
		})
	private Tfaccabfactura tfaccabfactura;
	
	public Tfacdetfactura() {
	}

	public String getPicestype() {
		return picestype;
	}

	public void setPicestype(String picestype) {
		this.picestype = picestype;
	}

	public Double getTotalpices() {
		return totalpices;
	}

	public void setTotalpices(Double totalpices) {
		this.totalpices = totalpices;
	}

	public Double getEqfullboxes() {
		return eqfullboxes;
	}

	public void setEqfullboxes(Double eqfullboxes) {
		this.eqfullboxes = eqfullboxes;
	}

	public String getNandina() {
		return nandina;
	}

	public void setNandina(String nandina) {
		this.nandina = nandina;
	}

	public Double getStemsbnuch() {
		return stemsbnuch;
	}

	public void setStemsbnuch(Double stemsbnuch) {
		this.stemsbnuch = stemsbnuch;
	}

	public Double getTotalstemsbnuch() {
		return totalstemsbnuch;
	}

	public void setTotalstemsbnuch(Double totalstemsbnuch) {
		this.totalstemsbnuch = totalstemsbnuch;
	}

	public Double getTotalstems() {
		return totalstems;
	}

	public void setTotalstems(Double totalstems) {
		this.totalstems = totalstems;
	}

	public Double getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(Double totalprice) {
		this.totalprice = totalprice;
	}

	public String getCodigoproductos() {
		return codigoproductos;
	}

	public void setCodigoproductos(String codigoproductos) {
		this.codigoproductos = codigoproductos;
	}

	public Tinvproducto getTinvproducto() {
		return tinvproducto;
	}

	public void setTinvproducto(Tinvproducto tinvproducto) {
		this.tinvproducto = tinvproducto;
	}

	public Tfaccabfactura getTfaccabfactura() {
		return tfaccabfactura;
	}

	public void setTfaccabfactura(Tfaccabfactura tfaccabfactura) {
		this.tfaccabfactura = tfaccabfactura;
	}

	public String getNumerofactura() {
		return numerofactura;
	}

	public void setNumerofactura(String numerofactura) {
		this.numerofactura = numerofactura;
	}


	
}
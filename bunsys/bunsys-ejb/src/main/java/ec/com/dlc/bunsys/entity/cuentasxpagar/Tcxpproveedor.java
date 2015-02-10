package ec.com.dlc.bunsys.entity.cuentasxpagar;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import ec.com.dlc.bunsys.entity.administracion.Tadmcatalogo;
import ec.com.dlc.bunsys.entity.administracion.Tadmparamsri;
import ec.com.dlc.bunsys.entity.base.BaseEntity;
import ec.com.dlc.bunsys.entity.cuentasxpagar.pk.TcxpproveedorPK;
import ec.com.dlc.bunsys.entity.inventario.pk.TinvproductoPK;
import ec.com.dlc.bunsys.entity.seguridad.Tsyspersona;


/**
 * The persistent class for the tcxpproveedor database table.
 * 
 */
@Entity
public class Tcxpproveedor extends BaseEntity<TcxpproveedorPK>  {
	private static final long serialVersionUID = 1L;

	@Column
	private Integer codigopersona;

	@Column
	private String contribuyentesri;

	@Column
	private Integer contribuyentesricodigo;

	@Column
	private String direccionestablecimiento;

	@Column
	private String grupoproveedor;

	@Column
	private Integer grupoproveedorcodigo;

	@Column
	private String tipodocumento;

	@Column
	private Integer tipodocumentocodigo;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="contribuyentesri", referencedColumnName="codigocatalogo", insertable=false, updatable=false),
		@JoinColumn(name="contribuyentesricodigo", referencedColumnName="codigotipocatalogo", insertable=false, updatable=false),
		@JoinColumn(name="codigocompania", referencedColumnName="codigocompania", insertable=false, updatable=false)
		})
	private Tadmcatalogo tadmcontribuyente;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="grupoproveedor", referencedColumnName="codigocatalogo", insertable=false, updatable=false),
		@JoinColumn(name="grupoproveedorcodigo", referencedColumnName="codigotipocatalogo", insertable=false, updatable=false),
		@JoinColumn(name="codigocompania", referencedColumnName="codigocompania", insertable=false, updatable=false)
		})
	private Tadmcatalogo tadmgrupoproveedor;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="codigopersona", referencedColumnName="codigopersona", insertable=false, updatable=false),
		@JoinColumn(name="codigocompania", referencedColumnName="codigocompania", insertable=false, updatable=false)
		})
	private Tsyspersona tsyspersona;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="tipodocumento", referencedColumnName="codigoparamsri", insertable=false, updatable=false),
		@JoinColumn(name="tipodocumentocodigo", referencedColumnName="codigotipoparamsri", insertable=false, updatable=false)
		})
	private Tadmparamsri tadmparamsri;
	//bi-directional many-to-one association to Tcxpcabcomprobanteretencion
	@OneToMany(mappedBy="tcxpproveedor")
	private Collection<Tcxpcabcomprobanteretencion> tcxpcabcomprobanteretencions;

	public Tcxpproveedor() {
		this.pk = new TcxpproveedorPK();
	}

	public Integer getCodigopersona() {
		return this.codigopersona;
	}

	public void setCodigopersona(Integer codigopersona) {
		this.codigopersona = codigopersona;
	}

	public String getContribuyentesri() {
		return this.contribuyentesri;
	}

	public void setContribuyentesri(String contribuyentesri) {
		this.contribuyentesri = contribuyentesri;
	}

	public Integer getContribuyentesricodigo() {
		return this.contribuyentesricodigo;
	}

	public void setContribuyentesricodigo(Integer contribuyentesricodigo) {
		this.contribuyentesricodigo = contribuyentesricodigo;
	}

	public String getDireccionestablecimiento() {
		return this.direccionestablecimiento;
	}

	public void setDireccionestablecimiento(String direccionestablecimiento) {
		this.direccionestablecimiento = direccionestablecimiento;
	}

	public String getGrupoproveedor() {
		return this.grupoproveedor;
	}

	public void setGrupoproveedor(String grupoproveedor) {
		this.grupoproveedor = grupoproveedor;
	}

	public Integer getGrupoproveedorcodigo() {
		return this.grupoproveedorcodigo;
	}

	public void setGrupoproveedorcodigo(Integer grupoproveedorcodigo) {
		this.grupoproveedorcodigo = grupoproveedorcodigo;
	}

	public Integer getTipodocumentocodigo() {
		return this.tipodocumentocodigo;
	}

	public void setTipodocumentocodigo(Integer tipodocumentocodigo) {
		this.tipodocumentocodigo = tipodocumentocodigo;
	}

	public Collection<Tcxpcabcomprobanteretencion> getTcxpcabcomprobanteretencions() {
		return this.tcxpcabcomprobanteretencions;
	}

	public void setTcxpcabcomprobanteretencions(Collection<Tcxpcabcomprobanteretencion> tcxpcabcomprobanteretencions) {
		this.tcxpcabcomprobanteretencions = tcxpcabcomprobanteretencions;
	}

	public Tcxpcabcomprobanteretencion addTcxpcabcomprobanteretencion(Tcxpcabcomprobanteretencion tcxpcabcomprobanteretencion) {
		getTcxpcabcomprobanteretencions().add(tcxpcabcomprobanteretencion);
		tcxpcabcomprobanteretencion.setTcxpproveedor(this);

		return tcxpcabcomprobanteretencion;
	}

	public Tcxpcabcomprobanteretencion removeTcxpcabcomprobanteretencion(Tcxpcabcomprobanteretencion tcxpcabcomprobanteretencion) {
		getTcxpcabcomprobanteretencions().remove(tcxpcabcomprobanteretencion);
		tcxpcabcomprobanteretencion.setTcxpproveedor(null);

		return tcxpcabcomprobanteretencion;
	}

	public Tadmcatalogo getTadmcontribuyente() {
		return tadmcontribuyente;
	}

	public void setTadmcontribuyente(Tadmcatalogo tadmcontribuyente) {
		this.tadmcontribuyente = tadmcontribuyente;
	}

	public Tadmcatalogo getTadmgrupoproveedor() {
		return tadmgrupoproveedor;
	}

	public void setTadmgrupoproveedor(Tadmcatalogo tadmgrupoproveedor) {
		this.tadmgrupoproveedor = tadmgrupoproveedor;
	}

	public Tsyspersona getTsyspersona() {
		return tsyspersona;
	}

	public void setTsyspersona(Tsyspersona tsyspersona) {
		this.tsyspersona = tsyspersona;
	}

	public Tadmparamsri getTadmparamsri() {
		return tadmparamsri;
	}

	public void setTadmparamsri(Tadmparamsri tadmparamsri) {
		this.tadmparamsri = tadmparamsri;
	}

	public String getTipodocumento() {
		return tipodocumento;
	}

	public void setTipodocumento(String tipodocumento) {
		this.tipodocumento = tipodocumento;
	}

}
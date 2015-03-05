package ec.com.dlc.bunsys.entity.administracion;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import ec.com.dlc.bunsys.entity.administracion.pk.TadmcatalogoPK;
import ec.com.dlc.bunsys.entity.base.BaseEntity;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccabfactura;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccliente;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccuentasxcobrar;
import ec.com.dlc.bunsys.entity.inventario.Tinvproducto;
import ec.com.dlc.bunsys.entity.seguridad.Tsyspersona;


/**
 * The persistent class for the tadmcatalogo database table.
 * 
 */
@Entity
public class Tadmcatalogo extends BaseEntity<TadmcatalogoPK>{
	private static final long serialVersionUID = 1L;

	@Column
	private String descripcion;
	
	@Column
	private String valor;

	@Column
	private String estado;

	//bi-directional many-to-one association to Tadmtipocatalogo
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="codigocompania", referencedColumnName="codigocompania", insertable=false, updatable=false),
		@JoinColumn(name="codigotipocatalogo", referencedColumnName="codigotipocatalogo", insertable=false, updatable=false)
		})
	private Tadmtipocatalogo tadmtipocatalogo;

	//bi-directional many-to-one association to Tadmparametro
	@OneToMany(mappedBy="tadmestado")
	private Set<Tadmparametro> tadmparametroestado;

	//bi-directional many-to-one association to Tadmparametro
	@OneToMany(mappedBy="tadmeditable")
	private Set<Tadmparametro> tadmparametroeditable;

	//bi-directional many-to-one association to Tadmusuario
	@OneToMany(mappedBy="tadmcatalogo")
	private Set<Tadmusuario> tadmusuarios;

	//bi-directional many-to-one association to Tfaccabfactura
	@OneToMany(mappedBy="tadmairline")
	private Set<Tfaccabfactura> tfaccabfacturas;

	//bi-directional many-to-one association to Tfaccliente
	@OneToMany(mappedBy="tadmformapago")
	private Set<Tfaccliente> tfacclienteformapago;

	//bi-directional many-to-one association to Tfaccliente
	@OneToMany(mappedBy="tadmgrupocliente")
	private Set<Tfaccliente> tfacclientegrupo;

	//bi-directional many-to-one association to Tinvproducto
	@OneToMany(mappedBy="tadmtipoproducto")
	private Set<Tinvproducto> tinvproductotipo;

	//bi-directional many-to-one association to Tinvproducto
	@OneToMany(mappedBy="tadmatpa")
	private Set<Tinvproducto> tinvproductoatpa;

	//bi-directional many-to-one association to Tinvproducto
	@OneToMany(mappedBy="tadmcolor")
	private Set<Tinvproducto> tinvproductocolor;

	//bi-directional many-to-one association to Tinvproducto
	@OneToMany(mappedBy="tadmunidadcompra")
	private Set<Tinvproducto> tinvproductounidadcompra;

	//bi-directional many-to-one association to Tinvproducto
	@OneToMany(mappedBy="tadmiva")
	private Set<Tinvproducto> tinvproductoiva;

	//bi-directional many-to-one association to Tinvproducto
	@OneToMany(mappedBy="tadmunidadventa")
	private Set<Tinvproducto> tinvproductounidadventa;

	//bi-directional many-to-one association to Tinvproducto
	@OneToMany(mappedBy="tadmirbpnr")
	private Set<Tinvproducto> tinvproductoirpbn;

	//bi-directional many-to-one association to Tinvproducto
	@OneToMany(mappedBy="tadmice")
	private Set<Tinvproducto> tinvproductoice;

	//bi-directional many-to-one association to Tinvproducto
	@OneToMany(mappedBy="tadmestado")
	private Set<Tinvproducto> tinvproductoestado;

	//bi-directional many-to-one association to Tsyspersona
	@OneToMany(mappedBy="tadmestado")
	private Set<Tsyspersona> tsyspersonasestado;

	//bi-directional many-to-one association to Tsyspersona
	@OneToMany(mappedBy="tadmtipopersona")
	private Set<Tsyspersona> tsyspersonastipopersona;
	
	@OneToMany(mappedBy="tadmestado")
	private Set<Tfaccuentasxcobrar> tfaccuentasxcobrarestado;
	
	@OneToMany(mappedBy="tadmtipodoc")
	private Set<Tfaccuentasxcobrar> tfaccuentasxcobrartipodoc; 

	public Tadmcatalogo() {
		this.pk = new TadmcatalogoPK();
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Tadmtipocatalogo getTadmtipocatalogo() {
		return this.tadmtipocatalogo;
	}

	public void setTadmtipocatalogo(Tadmtipocatalogo tadmtipocatalogo) {
		this.tadmtipocatalogo = tadmtipocatalogo;
	}

	public Set<Tadmparametro> getTadmparametroestado() {
		return this.tadmparametroestado;
	}

	public void setTadmparametroestado(Set<Tadmparametro> tadmparametroestado) {
		this.tadmparametroestado = tadmparametroestado;
	}

	public Tadmparametro addTadmparametroestado(Tadmparametro tadmparametroestado) {
		getTadmparametroestado().add(tadmparametroestado);
		tadmparametroestado.setTadmestado(this);

		return tadmparametroestado;
	}

	public Tadmparametro removeTadmparametroestado(Tadmparametro tadmparametroestado) {
		getTadmparametroestado().remove(tadmparametroestado);
		tadmparametroestado.setTadmestado(null);

		return tadmparametroestado;
	}

	public Set<Tadmparametro> getTadmparametroeditable() {
		return this.tadmparametroeditable;
	}

	public void setTadmparametroeditable(Set<Tadmparametro> tadmparametroeditable) {
		this.tadmparametroeditable = tadmparametroeditable;
	}

	public Tadmparametro addTadmparametroeditable(Tadmparametro tadmparametroeditable) {
		getTadmparametroeditable().add(tadmparametroeditable);
		tadmparametroeditable.setTadmeditable(this);

		return tadmparametroeditable;
	}

	public Tadmparametro removeTadmparametroeditable(Tadmparametro tadmparametroeditable) {
		getTadmparametroeditable().remove(tadmparametroeditable);
		tadmparametroeditable.setTadmeditable(null);

		return tadmparametroeditable;
	}

	public Set<Tadmusuario> getTadmusuarios() {
		return this.tadmusuarios;
	}

	public void setTadmusuarios(Set<Tadmusuario> tadmusuarios) {
		this.tadmusuarios = tadmusuarios;
	}

	public Tadmusuario addTadmusuario(Tadmusuario tadmusuario) {
		getTadmusuarios().add(tadmusuario);
		tadmusuario.setTadmcatalogo(this);

		return tadmusuario;
	}

	public Tadmusuario removeTadmusuario(Tadmusuario tadmusuario) {
		getTadmusuarios().remove(tadmusuario);
		tadmusuario.setTadmcatalogo(null);

		return tadmusuario;
	}

	public Set<Tfaccabfactura> getTfaccabfacturas() {
		return this.tfaccabfacturas;
	}

	public void setTfaccabfacturas(Set<Tfaccabfactura> tfaccabfacturas) {
		this.tfaccabfacturas = tfaccabfacturas;
	}

	public Tfaccabfactura addTfaccabfactura(Tfaccabfactura tfaccabfactura) {
		getTfaccabfacturas().add(tfaccabfactura);
		tfaccabfactura.setTadmairline(this);

		return tfaccabfactura;
	}

	public Tfaccabfactura removeTfaccabfactura(Tfaccabfactura tfaccabfactura) {
		getTfaccabfacturas().remove(tfaccabfactura);
		tfaccabfactura.setTadmairline(null);

		return tfaccabfactura;
	}

	public Set<Tfaccliente> getTfacclienteformapago() {
		return this.tfacclienteformapago;
	}

	public void setTfacclienteformapago(Set<Tfaccliente> tfacclienteformapago) {
		this.tfacclienteformapago = tfacclienteformapago;
	}

	public Tfaccliente addTfacclienteformapago(Tfaccliente tfacclienteformapago) {
		getTfacclienteformapago().add(tfacclienteformapago);
		tfacclienteformapago.setTadmformapago(this);

		return tfacclienteformapago;
	}

	public Tfaccliente removeTfacclienteformapago(Tfaccliente tfacclienteformapago) {
		getTfacclienteformapago().remove(tfacclienteformapago);
		tfacclienteformapago.setTadmformapago(null);

		return tfacclienteformapago;
	}

	public Set<Tfaccliente> getTfacclientegrupo() {
		return this.tfacclientegrupo;
	}

	public void setTfacclientegrupo(Set<Tfaccliente> tfacclientegrupo) {
		this.tfacclientegrupo = tfacclientegrupo;
	}

	public Tfaccliente addTfacclientegrupo(Tfaccliente tfacclientegrupo) {
		getTfacclientegrupo().add(tfacclientegrupo);
		tfacclientegrupo.setTadmgrupocliente(this);

		return tfacclientegrupo;
	}

	public Tfaccliente removeTfacclientegrupo(Tfaccliente tfacclientegrupo) {
		getTfacclientegrupo().remove(tfacclientegrupo);
		tfacclientegrupo.setTadmgrupocliente(null);

		return tfacclientegrupo;
	}

	public Set<Tinvproducto> getTinvproductotipo() {
		return this.tinvproductotipo;
	}

	public void setTinvproductotipo(Set<Tinvproducto> tinvproductotipo) {
		this.tinvproductotipo = tinvproductotipo;
	}

	public Tinvproducto addTinvproductotipo(Tinvproducto tinvproductotipo) {
		getTinvproductotipo().add(tinvproductotipo);
		tinvproductotipo.setTadmtipoproducto(this);

		return tinvproductotipo;
	}

	public Tinvproducto removeTinvproductotipo(Tinvproducto tinvproductotipo) {
		getTinvproductotipo().remove(tinvproductotipo);
		tinvproductotipo.setTadmtipoproducto(null);

		return tinvproductotipo;
	}

	public Set<Tinvproducto> getTinvproductoatpa() {
		return this.tinvproductoatpa;
	}

	public void setTinvproductoatpa(Set<Tinvproducto> tinvproductoatpa) {
		this.tinvproductoatpa = tinvproductoatpa;
	}

	public Tinvproducto addTinvproductoatpa(Tinvproducto tinvproductoatpa) {
		getTinvproductoatpa().add(tinvproductoatpa);
		tinvproductoatpa.setTadmatpa(this);

		return tinvproductoatpa;
	}

	public Tinvproducto removeTinvproductoatpa(Tinvproducto tinvproductoatpa) {
		getTinvproductoatpa().remove(tinvproductoatpa);
		tinvproductoatpa.setTadmatpa(null);

		return tinvproductoatpa;
	}

	public Set<Tinvproducto> getTinvproductocolor() {
		return this.tinvproductocolor;
	}

	public void setTinvproductocolor(Set<Tinvproducto> tinvproductocolor) {
		this.tinvproductocolor = tinvproductocolor;
	}

	public Tinvproducto addTinvproductocolor(Tinvproducto tinvproductocolor) {
		getTinvproductocolor().add(tinvproductocolor);
		tinvproductocolor.setTadmcolor(this);

		return tinvproductocolor;
	}

	public Tinvproducto removeTinvproductocolor(Tinvproducto tinvproductocolor) {
		getTinvproductocolor().remove(tinvproductocolor);
		tinvproductocolor.setTadmcolor(null);

		return tinvproductocolor;
	}

	public Set<Tinvproducto> getTinvproductounidadcompra() {
		return this.tinvproductounidadcompra;
	}

	public void setTinvproductounidadcompra(Set<Tinvproducto> tinvproductounidadcompra) {
		this.tinvproductounidadcompra = tinvproductounidadcompra;
	}

	public Tinvproducto addTinvproductounidadcompra(Tinvproducto tinvproductounidadcompra) {
		getTinvproductounidadcompra().add(tinvproductounidadcompra);
		tinvproductounidadcompra.setTadmunidadcompra(this);

		return tinvproductounidadcompra;
	}

	public Tinvproducto removeTinvproductounidadcompra(Tinvproducto tinvproductounidadcompra) {
		getTinvproductounidadcompra().remove(tinvproductounidadcompra);
		tinvproductounidadcompra.setTadmunidadcompra(null);

		return tinvproductounidadcompra;
	}

	public Set<Tinvproducto> getTinvproductoiva() {
		return this.tinvproductoiva;
	}

	public void setTinvproductoiva(Set<Tinvproducto> tinvproductoiva) {
		this.tinvproductoiva = tinvproductoiva;
	}

	public Tinvproducto addTinvproductoiva(Tinvproducto tinvproductoiva) {
		getTinvproductoiva().add(tinvproductoiva);
		tinvproductoiva.setTadmiva(this);

		return tinvproductoiva;
	}

	public Tinvproducto removeTinvproductoiva(Tinvproducto tinvproductoiva) {
		getTinvproductoiva().remove(tinvproductoiva);
		tinvproductoiva.setTadmiva(null);

		return tinvproductoiva;
	}

	public Set<Tinvproducto> getTinvproductounidadventa() {
		return this.tinvproductounidadventa;
	}

	public void setTinvproductounidadventa(Set<Tinvproducto> tinvproductounidadventa) {
		this.tinvproductounidadventa = tinvproductounidadventa;
	}

	public Tinvproducto addTinvproductounidadventa(Tinvproducto tinvproductounidadventa) {
		getTinvproductounidadventa().add(tinvproductounidadventa);
		tinvproductounidadventa.setTadmunidadventa(this);

		return tinvproductounidadventa;
	}

	public Tinvproducto removeTinvproductounidadventa(Tinvproducto tinvproductounidadventa) {
		getTinvproductounidadventa().remove(tinvproductounidadventa);
		tinvproductounidadventa.setTadmunidadventa(null);

		return tinvproductounidadventa;
	}

	public Set<Tinvproducto> getTinvproductoirpbn() {
		return this.tinvproductoirpbn;
	}

	public void setTinvproductoirpbn(Set<Tinvproducto> tinvproductoirpbn) {
		this.tinvproductoirpbn = tinvproductoirpbn;
	}

	public Tinvproducto addTinvproductoirpbn(Tinvproducto tinvproductoirpbn) {
		getTinvproductoirpbn().add(tinvproductoirpbn);
		tinvproductoirpbn.setTadmirbpnr(this);

		return tinvproductoirpbn;
	}

	public Tinvproducto removeTinvproductoirpbn(Tinvproducto tinvproductoirpbn) {
		getTinvproductoirpbn().remove(tinvproductoirpbn);
		tinvproductoirpbn.setTadmirbpnr(null);

		return tinvproductoirpbn;
	}

	public Set<Tinvproducto> getTinvproductoice() {
		return this.tinvproductoice;
	}

	public void setTinvproductoice(Set<Tinvproducto> tinvproductoice) {
		this.tinvproductoice = tinvproductoice;
	}

	public Tinvproducto addTinvproductoice(Tinvproducto tinvproductoice) {
		getTinvproductoice().add(tinvproductoice);
		tinvproductoice.setTadmice(this);

		return tinvproductoice;
	}

	public Tinvproducto removeTinvproductoice(Tinvproducto tinvproductoice) {
		getTinvproductoice().remove(tinvproductoice);
		tinvproductoice.setTadmice(null);

		return tinvproductoice;
	}

	public Set<Tinvproducto> getTinvproductoestado() {
		return this.tinvproductoestado;
	}

	public void setTinvproductoestado(Set<Tinvproducto> tinvproductoestado) {
		this.tinvproductoestado = tinvproductoestado;
	}

	public Tinvproducto addTinvproductoestado(Tinvproducto tinvproductoestado) {
		getTinvproductoestado().add(tinvproductoestado);
		tinvproductoestado.setTadmestado(this);

		return tinvproductoestado;
	}

	public Tinvproducto removeTinvproductoestado(Tinvproducto tinvproductoestado) {
		getTinvproductoestado().remove(tinvproductoestado);
		tinvproductoestado.setTadmestado(null);

		return tinvproductoestado;
	}

	public Set<Tsyspersona> getTsyspersonasestado() {
		return this.tsyspersonasestado;
	}

	public void setTsyspersonasestado(Set<Tsyspersona> tsyspersonasestado) {
		this.tsyspersonasestado = tsyspersonasestado;
	}

	public Tsyspersona addTsyspersonasestado(Tsyspersona tsyspersonasestado) {
		getTsyspersonasestado().add(tsyspersonasestado);
		tsyspersonasestado.setTadmestado(this);

		return tsyspersonasestado;
	}

	public Tsyspersona removeTsyspersonasestado(Tsyspersona tsyspersonasestado) {
		getTsyspersonasestado().remove(tsyspersonasestado);
		tsyspersonasestado.setTadmestado(null);

		return tsyspersonasestado;
	}

	public Set<Tsyspersona> getTsyspersonastipopersona() {
		return this.tsyspersonastipopersona;
	}

	public void setTsyspersonastipopersona(Set<Tsyspersona> tsyspersonastipopersona) {
		this.tsyspersonastipopersona = tsyspersonastipopersona;
	}

	public Tsyspersona addTsyspersonastipopersona(Tsyspersona tsyspersonastipopersona) {
		getTsyspersonastipopersona().add(tsyspersonastipopersona);
		tsyspersonastipopersona.setTadmtipopersona(this);

		return tsyspersonastipopersona;
	}

	public Tsyspersona removeTsyspersonastipopersona(Tsyspersona tsyspersonastipopersona) {
		getTsyspersonastipopersona().remove(tsyspersonastipopersona);
		tsyspersonastipopersona.setTadmtipopersona(null);

		return tsyspersonastipopersona;
	}

	public Set<Tfaccuentasxcobrar> getTfaccuentasxcobrarestado() {
		return tfaccuentasxcobrarestado;
	}

	public void setTfaccuentasxcobrarestado(
			Set<Tfaccuentasxcobrar> tfaccuentasxcobrarestado) {
		this.tfaccuentasxcobrarestado = tfaccuentasxcobrarestado;
	}

	public Set<Tfaccuentasxcobrar> getTfaccuentasxcobrartipodoc() {
		return tfaccuentasxcobrartipodoc;
	}

	public void setTfaccuentasxcobrartipodoc(
			Set<Tfaccuentasxcobrar> tfaccuentasxcobrartipodoc) {
		this.tfaccuentasxcobrartipodoc = tfaccuentasxcobrartipodoc;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
	
	

}
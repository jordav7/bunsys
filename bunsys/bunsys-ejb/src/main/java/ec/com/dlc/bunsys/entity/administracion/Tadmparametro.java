package ec.com.dlc.bunsys.entity.administracion;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ec.com.dlc.bunsys.entity.administracion.pk.TadmparametroPK;
import ec.com.dlc.bunsys.entity.base.BaseEntity;


/**
 * The persistent class for the tadmparametros database table.
 * 
 */
@Entity
@Table(name="tadmparametros")
public class Tadmparametro extends BaseEntity<TadmparametroPK> {
	private static final long serialVersionUID = 1L;

	@Column
	private String descripcion;

	@Column
	private String valor;
	
	@Column
	private String estado;
	
	@Column
	private Integer estadocodigo;
	
	@Column
	private String editable;
	
	@Column
	private Integer editablecodigo;
	
	//bi-directional many-to-one association to Tadmcatalogo
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="codigocompania", referencedColumnName="codigocompania", insertable=false, updatable=false),
		@JoinColumn(name="estado", referencedColumnName="codigocatalogo", insertable=false, updatable=false),
		@JoinColumn(name="estadocodigo", referencedColumnName="codigotipocatalogo", insertable=false, updatable=false)
		})
	private Tadmcatalogo tadmestado;

	//bi-directional many-to-one association to Tadmcatalogo
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="codigocompania", referencedColumnName="codigocompania", insertable=false, updatable=false),
		@JoinColumn(name="editable", referencedColumnName="codigocatalogo", insertable=false, updatable=false),
		@JoinColumn(name="editablecodigo", referencedColumnName="codigotipocatalogo", insertable=false, updatable=false)
		})
	private Tadmcatalogo tadmeditable;

	public Tadmparametro() {
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getValor() {
		return this.valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public Tadmcatalogo getTadmestado() {
		return this.tadmestado;
	}

	public void setTadmestado(Tadmcatalogo tadmestado) {
		this.tadmestado = tadmestado;
	}

	public Tadmcatalogo getTadmeditable() {
		return this.tadmeditable;
	}

	public void setTadmeditable(Tadmcatalogo tadmeditable) {
		this.tadmeditable = tadmeditable;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getEstadocodigo() {
		return estadocodigo;
	}

	public void setEstadocodigo(Integer estadocodigo) {
		this.estadocodigo = estadocodigo;
	}

	public String getEditable() {
		return editable;
	}

	public void setEditable(String editable) {
		this.editable = editable;
	}

	public Integer getEditablecodigo() {
		return editablecodigo;
	}

	public void setEditablecodigo(Integer editablecodigo) {
		this.editablecodigo = editablecodigo;
	}

}
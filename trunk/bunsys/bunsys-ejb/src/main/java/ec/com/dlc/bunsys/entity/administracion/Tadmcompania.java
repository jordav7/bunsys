package ec.com.dlc.bunsys.entity.administracion;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import ec.com.dlc.bunsys.entity.administracion.pk.TadmcompaniaPK;
import ec.com.dlc.bunsys.entity.base.BaseEntity;


/**
 * The persistent class for the tadmcompania database table.
 * 
 */
@Entity
@Table(name="tadmcompania")
public class Tadmcompania extends BaseEntity<TadmcompaniaPK>{
	private static final long serialVersionUID = 1L;

	@Column
	private String codigoestablecimiento;

	@Column
	private String codigopuntoemision;

	@Column
	private String correo;

	@Column
	private String direccionestablecimiento;

	@Column
	private String direccionmatriz;

	@Column
	private String idcontador;

	@Column
	@Type(type="org.hibernate.type.BinaryType")
//	@Lob
	private  byte[] logocompania;

	@Column
	private String nombrecomercial;

	@Column
	private String numeroresolucion;

	@Column
	private String obligacioncontabilidad;

	@Column
	private String razonsocial;

	@Column
	private String ruc;

	@Column
	private String telefono;

	@Column
	private Integer tiemporespuesta;

	@Column
	private Integer tipoidcontador;
	
	@Column
	private String telefonocelular;
	
	@Column
	private String tipocompania;

	@Column
	private String tipoambiente;

	@Column
	private Integer tipoambientecodigo;
	
	//bi-directional many-to-one association to Tadmrole
	@OneToMany(mappedBy="tadmcompania")
	private Collection<Tadmrole> tadmroles;

	//bi-directional many-to-one association to Tadmtipocatalogo
	@OneToMany(mappedBy="tadmcompania")
	private Collection<Tadmtipocatalogo> tadmtipocatalogos;

	//bi-directional many-to-one association to Tadmusuario
	@OneToMany(mappedBy="tadmcompania")
	private Collection<Tadmusuario> tadmusuarios;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name="tipoambiente", referencedColumnName="codigoparamsri", insertable=false, updatable=false),
			@JoinColumn(name="tipoambientecodigo", referencedColumnName="codigotipoparamsri", insertable=false, updatable=false)
	})
	private Tadmparamsri tadmtipoambiente;
	
	public Tadmcompania() {
	}

	public String getCodigoestablecimiento() {
		return this.codigoestablecimiento;
	}

	public void setCodigoestablecimiento(String codigoestablecimiento) {
		this.codigoestablecimiento = codigoestablecimiento;
	}

	public String getCodigopuntoemision() {
		return this.codigopuntoemision;
	}

	public void setCodigopuntoemision(String codigopuntoemision) {
		this.codigopuntoemision = codigopuntoemision;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDireccionestablecimiento() {
		return this.direccionestablecimiento;
	}

	public void setDireccionestablecimiento(String direccionestablecimiento) {
		this.direccionestablecimiento = direccionestablecimiento;
	}

	public String getDireccionmatriz() {
		return this.direccionmatriz;
	}

	public void setDireccionmatriz(String direccionmatriz) {
		this.direccionmatriz = direccionmatriz;
	}

	public String getIdcontador() {
		return this.idcontador;
	}

	public void setIdcontador(String idcontador) {
		this.idcontador = idcontador;
	}

	public String getNombrecomercial() {
		return this.nombrecomercial;
	}

	public void setNombrecomercial(String nombrecomercial) {
		this.nombrecomercial = nombrecomercial;
	}

	public String getNumeroresolucion() {
		return this.numeroresolucion;
	}

	public void setNumeroresolucion(String numeroresolucion) {
		this.numeroresolucion = numeroresolucion;
	}

	public String getObligacioncontabilidad() {
		return this.obligacioncontabilidad;
	}

	public void setObligacioncontabilidad(String obligacioncontabilidad) {
		this.obligacioncontabilidad = obligacioncontabilidad;
	}

	public String getRazonsocial() {
		return this.razonsocial;
	}

	public void setRazonsocial(String razonsocial) {
		this.razonsocial = razonsocial;
	}

	public String getRuc() {
		return this.ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Integer getTiemporespuesta() {
		return this.tiemporespuesta;
	}

	public void setTiemporespuesta(Integer tiemporespuesta) {
		this.tiemporespuesta = tiemporespuesta;
	}

	public Integer getTipoidcontador() {
		return this.tipoidcontador;
	}

	public void setTipoidcontador(Integer tipoidcontador) {
		this.tipoidcontador = tipoidcontador;
	}

	public Collection<Tadmrole> getTadmroles() {
		return this.tadmroles;
	}

	public void setTadmroles(Collection<Tadmrole> tadmroles) {
		this.tadmroles = tadmroles;
	}

	public Collection<Tadmtipocatalogo> getTadmtipocatalogos() {
		return this.tadmtipocatalogos;
	}

	public void setTadmtipocatalogos(Collection<Tadmtipocatalogo> tadmtipocatalogos) {
		this.tadmtipocatalogos = tadmtipocatalogos;
	}

	public Collection<Tadmusuario> getTadmusuarios() {
		return this.tadmusuarios;
	}

	public void setTadmusuarios(Collection<Tadmusuario> tadmusuarios) {
		this.tadmusuarios = tadmusuarios;
	}

	public byte[] getLogocompania() {
		return logocompania;
	}

	public void setLogocompania(byte[] logocompania) {
		this.logocompania = logocompania;
	}

	public String getTelefonocelular() {
		return telefonocelular;
	}

	public void setTelefonocelular(String telefonocelular) {
		this.telefonocelular = telefonocelular;
	}

	public String getTipocompania() {
		return tipocompania;
	}

	public void setTipocompania(String tipocompania) {
		this.tipocompania = tipocompania;
	}

	public String getTipoambiente() {
		return tipoambiente;
	}

	public void setTipoambiente(String tipoambiente) {
		this.tipoambiente = tipoambiente;
	}

	public Integer getTipoambientecodigo() {
		return tipoambientecodigo;
	}

	public void setTipoambientecodigo(Integer tipoambientecodigo) {
		this.tipoambientecodigo = tipoambientecodigo;
	}

	public Tadmparamsri getTadmtipoambiente() {
		return tadmtipoambiente;
	}

	public void setTadmtipoambiente(Tadmparamsri tadmtipoambiente) {
		this.tadmtipoambiente = tadmtipoambiente;
	}

	
}
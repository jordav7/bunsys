package ec.com.dlc.bunsys.entity.seguridad;

import java.io.Serializable;

import javax.persistence.*;

import ec.com.dlc.bunsys.commons.listener.GenerateIdListener;
import ec.com.dlc.bunsys.entity.administracion.Tadmcatalogo;
import ec.com.dlc.bunsys.entity.administracion.Tadmusuario;
import ec.com.dlc.bunsys.entity.base.BaseEntity;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccliente;
import ec.com.dlc.bunsys.entity.seguridad.pk.TsyspersonaPK;

import java.util.Set;


/**
 * The persistent class for the tsyspersona database table.
 * 
 */
@Entity
@EntityListeners(GenerateIdListener.class)
public class Tsyspersona extends BaseEntity<TsyspersonaPK>{
	private static final long serialVersionUID = 1L;

    @Column
    private String nombres;
    
    @Column
    private String apellidos;
        
	@Column
	private String contacto;

	@Column
	private String correo;

	@Column
	private String direccion;

	@Column
	private String extension;

	@Column
	private String identificacion;

	@Column
	private String observacion;

	@Column
	private String telefonocelular;

	@Column
	private String telefonoconvencional;

	@Column
	private String telefonotrabajo;
	
	@Column
	private String estado;
	
	@Column
	private Integer estadocodigo;
	
	@Column
	private String tipoid;
	
	@Column
	private Integer tipoidcodigo;

	//bi-directional many-to-one association to Tadmusuario
	@OneToMany(mappedBy="tsyspersona")
	private Set<Tadmusuario> tadmusuarios;

	//bi-directional many-to-one association to Tfaccliente
	@OneToMany(mappedBy="tsyspersona")
	private Set<Tfaccliente> tfacclientes;

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
		@JoinColumn(name="tipoid", referencedColumnName="codigocatalogo", insertable=false, updatable=false),
		@JoinColumn(name="tipoidcodigo", referencedColumnName="codigotipocatalogo", insertable=false, updatable=false)
		})
	private Tadmcatalogo tadmtipopersona;

	public Tsyspersona() {
		this.pk = new TsyspersonaPK();
	}

	public String getContacto() {
		return this.contacto;
	}

	public void setContacto(String contacto) {
		this.contacto = contacto;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getExtension() {
		return this.extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getIdentificacion() {
		return this.identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getObservacion() {
		return this.observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getTelefonocelular() {
		return this.telefonocelular;
	}

	public void setTelefonocelular(String telefonocelular) {
		this.telefonocelular = telefonocelular;
	}

	public String getTelefonoconvencional() {
		return this.telefonoconvencional;
	}

	public void setTelefonoconvencional(String telefonoconvencional) {
		this.telefonoconvencional = telefonoconvencional;
	}

	public String getTelefonotrabajo() {
		return this.telefonotrabajo;
	}

	public void setTelefonotrabajo(String telefonotrabajo) {
		this.telefonotrabajo = telefonotrabajo;
	}

	public Set<Tadmusuario> getTadmusuarios() {
		return this.tadmusuarios;
	}

	public void setTadmusuarios(Set<Tadmusuario> tadmusuarios) {
		this.tadmusuarios = tadmusuarios;
	}

	public Tadmusuario addTadmusuario(Tadmusuario tadmusuario) {
		getTadmusuarios().add(tadmusuario);
		tadmusuario.setTsyspersona(this);

		return tadmusuario;
	}

	public Tadmusuario removeTadmusuario(Tadmusuario tadmusuario) {
		getTadmusuarios().remove(tadmusuario);
		tadmusuario.setTsyspersona(null);

		return tadmusuario;
	}

	public Set<Tfaccliente> getTfacclientes() {
		return this.tfacclientes;
	}

	public void setTfacclientes(Set<Tfaccliente> tfacclientes) {
		this.tfacclientes = tfacclientes;
	}

	public Tfaccliente addTfaccliente(Tfaccliente tfaccliente) {
		getTfacclientes().add(tfaccliente);
		tfaccliente.setTsyspersona(this);

		return tfaccliente;
	}

	public Tfaccliente removeTfaccliente(Tfaccliente tfaccliente) {
		getTfacclientes().remove(tfaccliente);
		tfaccliente.setTsyspersona(null);

		return tfaccliente;
	}

	public Tadmcatalogo getTadmestado() {
		return this.tadmestado;
	}

	public void setTadmestado(Tadmcatalogo tadmestado) {
		this.tadmestado = tadmestado;
	}

	public Tadmcatalogo getTadmtipopersona() {
		return this.tadmtipopersona;
	}

	public void setTadmtipopersona(Tadmcatalogo tadmtipopersona) {
		this.tadmtipopersona = tadmtipopersona;
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

	public String getTipoid() {
		return tipoid;
	}

	public void setTipoid(String tipoid) {
		this.tipoid = tipoid;
	}

	public Integer getTipoidcodigo() {
		return tipoidcodigo;
	}

	public void setTipoidcodigo(Integer tipoidcodigo) {
		this.tipoidcodigo = tipoidcodigo;
	}

        public String getNombres() {
            return nombres;
        }

        public void setNombres(String nombres) {
            this.nombres = nombres;
        }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

}
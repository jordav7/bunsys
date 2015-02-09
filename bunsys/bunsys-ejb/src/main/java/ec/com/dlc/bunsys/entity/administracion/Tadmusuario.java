package ec.com.dlc.bunsys.entity.administracion;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ec.com.dlc.bunsys.commons.listener.GenerateIdListener;
import ec.com.dlc.bunsys.entity.administracion.pk.TadmusuarioPK;
import ec.com.dlc.bunsys.entity.base.BaseEntity;
import ec.com.dlc.bunsys.entity.seguridad.Tsyspersona;


/**
 * The persistent class for the tadmusuario database table.
 * 
 */
@Entity
@EntityListeners(GenerateIdListener.class)
public class Tadmusuario extends BaseEntity<TadmusuarioPK> {
	private static final long serialVersionUID = 1L;

	@Column
	@Temporal(TemporalType.DATE)
	private Date fecharegistro;

	@Column
	private String password;

	@Column
	private String usuario;
	
	@Column
	private Integer estadocodigo;
	
	@Column
	private String estado;
	
	@Column
	private Integer codigopersona;

	//bi-directional many-to-many association to Tadmrole
	@OneToMany(mappedBy="tadmusuario")
	private Collection<Tadmrolusuario> tadmrolusuarios;

	//bi-directional many-to-one association to Tadmcatalogo
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="codigocompania", referencedColumnName="codigocompania", insertable=false, updatable=false),
		@JoinColumn(name="estado", referencedColumnName="codigocatalogo", insertable=false, updatable=false),
		@JoinColumn(name="estadocodigo", referencedColumnName="codigotipocatalogo", insertable=false, updatable=false)
		})
	private Tadmcatalogo tadmcatalogo;

	//bi-directional many-to-one association to Tadmcompania
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigocompania", insertable=false, updatable=false)
	private Tadmcompania tadmcompania;

	//bi-directional many-to-one association to Tsyspersona
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="codigocompania", referencedColumnName="codigocompania", insertable=false, updatable=false),
		@JoinColumn(name="codigopersona", referencedColumnName="codigopersona", insertable=false, updatable=false)
		})
	private Tsyspersona tsyspersona;

	public Tadmusuario() {
		this.pk = new TadmusuarioPK();
	}

	public Date getFecharegistro() {
		return this.fecharegistro;
	}

	public void setFecharegistro(Date fecharegistro) {
		this.fecharegistro = fecharegistro;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Tadmcatalogo getTadmcatalogo() {
		return this.tadmcatalogo;
	}

	public void setTadmcatalogo(Tadmcatalogo tadmcatalogo) {
		this.tadmcatalogo = tadmcatalogo;
	}

	public Tadmcompania getTadmcompania() {
		return this.tadmcompania;
	}

	public void setTadmcompania(Tadmcompania tadmcompania) {
		this.tadmcompania = tadmcompania;
	}

	public Tsyspersona getTsyspersona() {
		return this.tsyspersona;
	}

	public void setTsyspersona(Tsyspersona tsyspersona) {
		this.tsyspersona = tsyspersona;
	}

	public Collection<Tadmrolusuario> getTadmrolusuarios() {
		return tadmrolusuarios;
	}

	public void setTadmrolusuarios(Collection<Tadmrolusuario> tadmrolusuarios) {
		this.tadmrolusuarios = tadmrolusuarios;
	}

	public Integer getEstadocodigo() {
		return estadocodigo;
	}

	public void setEstadocodigo(Integer estadocodigo) {
		this.estadocodigo = estadocodigo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getCodigopersona() {
		return codigopersona;
	}

	public void setCodigopersona(Integer codigopersona) {
		this.codigopersona = codigopersona;
	}

}
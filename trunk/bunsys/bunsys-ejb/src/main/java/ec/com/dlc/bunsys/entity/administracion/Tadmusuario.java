package ec.com.dlc.bunsys.entity.administracion;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ec.com.dlc.bunsys.entity.administracion.pk.TadmusuarioPK;
import ec.com.dlc.bunsys.entity.base.BaseEntity;
import ec.com.dlc.bunsys.entity.seguridad.Tsyspersona;


/**
 * The persistent class for the tadmusuario database table.
 * 
 */
@Entity
public class Tadmusuario extends BaseEntity<TadmusuarioPK> {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	private Date fecharegistro;

	private String password;

	private String usuario;

	//bi-directional many-to-many association to Tadmrole
	@OneToMany(mappedBy="tadmusuario")
	private Collection<Tadmrolusuario> tadmrolusuarios;

	//bi-directional many-to-one association to Tadmcatalogo
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="codigocompania", referencedColumnName="codigocompania", insertable=false, updatable=false),
		@JoinColumn(name="estado", referencedColumnName="codigocatalogo", insertable=false, updatable=false),
		@JoinColumn(name="estadotipo", referencedColumnName="codigotipocatalogo", insertable=false, updatable=false)
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

}
package ec.com.dlc.bunsys.entity.administracion;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import ec.com.dlc.bunsys.entity.administracion.pk.TadmrolusuarioPK;
import ec.com.dlc.bunsys.entity.base.BaseEntity;


/**
 * The persistent class for the tadmrolusuario database table.
 * 
 */
@Entity
public class Tadmrolusuario extends BaseEntity<TadmrolusuarioPK> {
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
				@JoinColumn(name="codigorol", referencedColumnName="codigorol", insertable=false, updatable=false),
				@JoinColumn(name="codigocompania", referencedColumnName="codigocompania", insertable=false, updatable=false)})
	private Tadmrole tadmrole;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="codigousuario", referencedColumnName="codigousuario", insertable=false, updatable=false),
		@JoinColumn(name="codigocompania", referencedColumnName="codigocompania", insertable=false, updatable=false)})
	private Tadmusuario tadmusuario;
	
	public Tadmrolusuario() {
	}

	public Tadmrole getTadmrole() {
		return tadmrole;
	}

	public void setTadmrole(Tadmrole tadmrole) {
		this.tadmrole = tadmrole;
	}

	public Tadmusuario getTadmusuario() {
		return tadmusuario;
	}

	public void setTadmusuario(Tadmusuario tadmusuario) {
		this.tadmusuario = tadmusuario;
	}

}
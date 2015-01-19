package ec.com.dlc.bunsys.facade;

import java.util.Collection;

import javax.ejb.Local;

import ec.com.dlc.bunsys.entity.administracion.Tadmcompania;

/**
 * Acceso principal a la aplicaci&oacute;n desde la vista, todos los servicios deben ser invocados desde este punto<br/>
 * Se basa en el patr&oacute;n <code>Application Service</code>
 * @author DAVID
 */
@Local
public interface BunsysService {

	public Collection<Tadmcompania> obtenerCompanias() ;
}

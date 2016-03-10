package ec.com.dlc.web.controller.cobranza;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import ec.com.dlc.bunsys.entity.facturacion.Tfaccabfactura;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccliente;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccuentasxcobrar;
import ec.com.dlc.bunsys.entity.seguridad.Tsyspersona;
import ec.com.dlc.bunsys.facade.BunsysService;
import ec.com.dlc.web.controller.base.BaseController;
import ec.com.dlc.web.datamanager.base.BaseDatamanager;
import ec.com.dlc.web.datamanager.cobranza.CobrosDatamanager;

@ManagedBean
@ViewScoped
public class CobrosController extends BaseController {

	@ManagedProperty(value="#{cobrosDatamanager}")
	private CobrosDatamanager cxcDatamanager;
	
	@Inject
	private BunsysService bunsysService;

	@Override
	public BaseDatamanager getDatamanager() {
		// TODO Auto-generated method stub
		return cxcDatamanager;
	}

	@Override
	public void inicializar() {
		// TODO Auto-generated method stub
		cxcDatamanager.setCxcSearch(new Tfaccuentasxcobrar());
		cxcDatamanager.getCxcSearch().setTfaccabfactura(new Tfaccabfactura());
		cxcDatamanager.getCxcSearch().setTfaccliente(new Tfaccliente());
		cxcDatamanager.getCxcSearch().getTfaccliente().setTsyspersona(new Tsyspersona());
		
	}
	
	public void buscar() {
		cxcDatamanager.setCxcColl(bunsysService.obtenerFacturasCredito(cxcDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania(),
				cxcDatamanager.getCxcSearch().getNumerofactura(), 
				cxcDatamanager.getCxcSearch().getTfaccliente().getTsyspersona().getIdentificacion(),
				cxcDatamanager.getCxcSearch().getTfaccliente().getTsyspersona().getNombres(),
				cxcDatamanager.getCxcSearch().getTfaccliente().getTsyspersona().getApellidos(),
				cxcDatamanager.getCxcSearch().getFechaemision(),
				cxcDatamanager.getCxcSearch().getFechavence(), 
				cxcDatamanager.getCxcSearch().getFechapago(),
				cxcDatamanager.getCxcSearch().getNumdoc()));
	}

	public CobrosDatamanager getCxcDatamanager() {
		return cxcDatamanager;
	}

	public void setCxcDatamanager(CobrosDatamanager cxcDatamanager) {
		this.cxcDatamanager = cxcDatamanager;
	}

}

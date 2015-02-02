package ec.com.dlc.web.datamanager.base;

/**
 * Datamanager general todos los manejadores de datos lo utilizan
 * @author DAVID
 */
public abstract class BaseDatamanager {

	private Boolean inicializado = Boolean.FALSE;
	
	/**
	 * Retorna el nombre del datamanager 
	 * @return
	 */
	public abstract String getIdDatamanager();

	public Boolean getInicializado() {
		return inicializado;
	}

	public void setInicializado(Boolean inicializado) {
		this.inicializado = inicializado;
	}
	
}

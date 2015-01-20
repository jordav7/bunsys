package ec.com.dlc.bunsys.dao.exception;

/**
 * Maneja las excepciones originadas por el DAO gen&eacute;rico
 * @author DAVID
 *
 */
public class DaoExcepcion extends RuntimeException {

	private static final long serialVersionUID = 3831815701014270003L;

	public DaoExcepcion() {
		super();
	}

	public DaoExcepcion(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DaoExcepcion(String message, Throwable cause) {
		super(message, cause);
	}

	public DaoExcepcion(String message) {
		super(message);
	}

	public DaoExcepcion(Throwable cause) {
		super(cause);
	}

}

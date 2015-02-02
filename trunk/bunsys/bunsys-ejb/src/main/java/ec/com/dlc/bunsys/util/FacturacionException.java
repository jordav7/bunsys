package ec.com.dlc.bunsys.util;

public class FacturacionException extends RuntimeException {

	private static final long serialVersionUID = -8833941128892724417L;

	public FacturacionException() {
		super();
	}

	public FacturacionException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public FacturacionException(String message, Throwable cause) {
		super(message, cause);
	}

	public FacturacionException(String message) {
		super(message);
	}

	public FacturacionException(Throwable cause) {
		super(cause);
	}

}

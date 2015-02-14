package ec.com.dlc.web.commons.listener;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.jboss.logging.Logger;

public class LifeCycleListener implements PhaseListener {

	private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger(LifeCycleListener.class);

	@Override
	public void afterPhase(PhaseEvent event) {
		log.info("AFTER PHASE: "+event.getPhaseId());
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		log.info("BEFORE PHASE: "+event.getPhaseId());
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}

}

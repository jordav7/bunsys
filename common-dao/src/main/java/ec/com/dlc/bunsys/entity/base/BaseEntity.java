package ec.com.dlc.bunsys.entity.base;

import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;

import ec.com.dlc.bunsys.entity.common.EntityCommonImpl;

@MappedSuperclass
public class BaseEntity<T extends BasePK> extends EntityCommonImpl {

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	protected T pk;

	public T getPk() {
		return pk;
	}

	public void setPk(T pk) {
		this.pk = pk;
	}
}

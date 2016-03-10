package ec.com.dlc.web.menu.datamanager;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ec.com.dlc.web.datamanager.base.BaseDatamanager;

@ManagedBean(name="menuDataManager")
@SessionScoped
public class MenuDataManager extends BaseDatamanager{

	@Override
	public String getIdDatamanager() {
		return "menuDataManager";
	}

}

package com.thhy.project.usermodule.util;

import com.thhy.project.usermodule.vo.DefaultMenuInfo;
import com.thhy.project.usermodule.vo.Meta;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class SysMenuUtil {
	public static List<DefaultMenuInfo> getSysRouteList(List<DefaultMenuInfo> temps, int parentId) {
		List<DefaultMenuInfo> routes = new ArrayList<>();
		for (DefaultMenuInfo sysRouteTemp : temps) {
			if (sysRouteTemp.getParentId() == parentId) {
				DefaultMenuInfo route = new DefaultMenuInfo();
				route.setMenuId(sysRouteTemp.getMenuId());
				route.setLevel(sysRouteTemp.getLevel());
				route.setParentId(sysRouteTemp.getParentId());
				route.setComponent(!StringUtils.hasText(sysRouteTemp.getComponent()) ? null : sysRouteTemp.getComponent());
				route.setHidden(sysRouteTemp.getHidden());
				Meta meta = sysRouteTemp.getMeta();
				if (meta != null) {
					meta.setIcon(!StringUtils.hasText(meta.getIcon()) ? null : meta.getIcon());
					meta.setTitle(!StringUtils.hasText(meta.getTitle()) ? null : meta.getTitle());
				}
				route.setMeta(meta);
				route.setName(!StringUtils.hasText(sysRouteTemp.getName()) ? null : sysRouteTemp.getName());
				route.setPath(!StringUtils.hasText(sysRouteTemp.getPath()) ? null : sysRouteTemp.getPath());
				route.setRedirect(!StringUtils.hasText(sysRouteTemp.getRedirect()) ? null : sysRouteTemp.getRedirect());
				List<DefaultMenuInfo> tempList = new ArrayList<>(temps);
				tempList.remove(sysRouteTemp);
				route.setChildren(getSysRouteList(tempList, sysRouteTemp.getMenuId()));
				routes.add(route);
			}
		}
		return routes;
	}
}

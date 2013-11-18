package org.openpos.wifi;

import org.openpos.utils.LegacyFactory;
import org.openpos.utils.ResourcesHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WifiResources extends ResourcesHandler {

	private static final String WIFI_KEY = "Wifi.Key";
	private static final String WIFI_LAST_MODIFIED = "Wifi.LastModified";
	private static final String WIFI_PROMOTION = "Wifi.Promotion";
	private static final String WIFI_FB = "Wifi.Fb";

	@Autowired
	public WifiResources(LegacyFactory legacyFactory) {
		super(legacyFactory.getAppView());
	}

	public boolean hasWifiKey() {
		return !"".equals(getWifiKey());
	}

	public String getWifiKey() {
		return getResourceAsString(WIFI_KEY);
	}

	public void setWifiKey(String key) {
		setResourceAsString(WIFI_KEY, key);
	}

	public long getLastModified() {
		String lm = getResourceAsString(WIFI_LAST_MODIFIED);
		return Long.parseLong(lm);
	}

	public void setLastModified(long time) {
		setResourceAsString(WIFI_LAST_MODIFIED, String.valueOf(time));
	}
}

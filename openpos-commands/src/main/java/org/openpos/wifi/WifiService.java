package org.openpos.wifi;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WifiService {

	@Autowired
	private WifiResources wifiResources;

	@PostConstruct
	public void afterPropertiesSet() throws Exception {
		if (wifiResources.hasWifiKey()) {
			long now = System.currentTimeMillis();
			long lastModified = wifiResources.getLastModified();
			double lastModifiedDays = ((double)(now - lastModified)) / (1000 * 60 * 60 * 24);
			if (lastModifiedDays > 6) {
				updateWifi();
			}
		}
		else
			updateWifi();
	}

	private void updateWifi() {
		wifiResources.setWifiKey(generateNewWpaKey());
		wifiResources.setLastModified(System.currentTimeMillis());
	}

	public String generateNewWpaKey() {
		return RandomStringUtils.random(12);
	}
}

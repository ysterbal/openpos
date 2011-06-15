package org.openpos;

import java.awt.EventQueue;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.LookAndFeel;
import javax.swing.UIManager;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.api.SubstanceSkin;
import org.springframework.stereotype.Component;

import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppConfig;
import com.openbravo.pos.forms.JRootFrame;
import com.openbravo.pos.forms.JRootKiosk;
import com.openbravo.pos.instance.InstanceQuery;

@Component
public class StartPOS implements Runnable {

	private static Logger logger = Logger.getLogger("com.openbravo.pos.forms.StartPOS");

	private String[] args;

	public StartPOS() {
	}

	public StartPOS(String[] args) {
		this.args = args;
	}

	public static boolean registerApp() {

		InstanceQuery i = null;
		try {
			i = new InstanceQuery();
			i.getAppMessage().restoreWindow();
			return false;
		}
		catch (Exception e) {
			return true;
		}
	}

	public void setArgs(String[] args) {
		this.args = args;
	}

	@Override
	public void run() {

		if (!registerApp()) {
			System.exit(1);
		}

		AppConfig config = new AppConfig(args);
		config.load();

		// set Locale.
		String slang = config.getProperty("user.language");
		String scountry = config.getProperty("user.country");
		String svariant = config.getProperty("user.variant");
		if (slang != null && !slang.equals("") && scountry != null && svariant != null) {
			Locale.setDefault(new Locale(slang, scountry, svariant));
		}

		// Set the format patterns
		Formats.setIntegerPattern(config.getProperty("format.integer"));
		Formats.setDoublePattern(config.getProperty("format.double"));
		Formats.setCurrencyPattern(config.getProperty("format.currency"));
		Formats.setPercentPattern(config.getProperty("format.percent"));
		Formats.setDatePattern(config.getProperty("format.date"));
		Formats.setTimePattern(config.getProperty("format.time"));
		Formats.setDateTimePattern(config.getProperty("format.datetime"));

		// Set the look and feel.
		try {

			Object laf = Class.forName(config.getProperty("swing.defaultlaf")).newInstance();

			if (laf instanceof LookAndFeel) {
				UIManager.setLookAndFeel((LookAndFeel)laf);
			}
			else if (laf instanceof SubstanceSkin) {
				SubstanceLookAndFeel.setSkin((SubstanceSkin)laf);
			}
		}
		catch (Exception e) {
			logger.log(Level.WARNING, "Cannot set look and feel", e);
		}

		String screenmode = config.getProperty("machine.screenmode");
		if ("fullscreen".equals(screenmode)) {
			JRootKiosk rootkiosk = new JRootKiosk();
			rootkiosk.initFrame(config);
		}
		else {
			JRootFrame rootframe = new JRootFrame();
			rootframe.initFrame(config);
		}
	}

	public static void main(final String args[]) {
		EventQueue.invokeLater(new StartPOS(args));
	}
}

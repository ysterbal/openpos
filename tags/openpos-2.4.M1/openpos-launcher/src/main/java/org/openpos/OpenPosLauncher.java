package org.openpos;

import java.awt.EventQueue;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OpenPosLauncher {

	public static ApplicationContext applicationContext;

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static void main(String[] args) {
		applicationContext = new AnnotationConfigApplicationContext("org.openpos", "com.openbravo");
		StartPOS startPOS = applicationContext.getBean(StartPOS.class);
		startPOS.setArgs(args);
		EventQueue.invokeLater(startPOS);
	}
}

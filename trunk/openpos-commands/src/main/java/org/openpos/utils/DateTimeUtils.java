package org.openpos.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateTimeUtils {

	public static String[] createYearsArray(int startingYear) {
		final String[] years = new String[Calendar.getInstance().get(Calendar.YEAR) - startingYear + 1];
		for (int y = 0; y < years.length; y++) {
			years[y] = String.valueOf(startingYear + y);
		}
		return years;
	}

	public static String[] createMonthArray() {
		String[] months = new String[12];
		SimpleDateFormat sdf = new SimpleDateFormat("MMMMM");
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 1);
		for (int month = 0; month < months.length; month++) {
			c.set(Calendar.MONTH, month);
			months[month] = sdf.format(c.getTime());
		}
		return months;
	}

}

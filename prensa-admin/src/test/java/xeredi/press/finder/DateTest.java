package xeredi.press.finder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.junit.Test;

public final class DateTest {
	@Test
	public void test() {
		testDate("EEE, dd MMM yyyy HH:mm:ss Z", "en", "Sun, 09 Jul 2017 13:12:23 +0200");
		testDate("EEE MMM dd HH:mm:ss yyyy", "en", "Mon Jan 12 21:00:00 2015");
		testDate("EEE,dd MMM yyyy HH:mm:ss", "en", "Sun,25 Mar 2018 06:25:46");
	}

	private void testDate(final String format, final String language, final String dateString) {
		try {
			new SimpleDateFormat(format, new Locale(language)).parse(dateString);
		} catch (ParseException ex) {
			ex.printStackTrace(System.err);
		}
	}
}

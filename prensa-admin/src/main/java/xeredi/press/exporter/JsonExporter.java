package xeredi.press.exporter;

import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class JsonExporter.
 */
public abstract class JsonExporter {

	/**
	 * Gets the string.
	 *
	 * @param value
	 *            the value
	 * @return the string
	 */
	protected final String getString(final String value) {
		return value == null ? "NULL" : "'" + value + "'";
	}

	/**
	 * Gets the number.
	 *
	 * @param value
	 *            the value
	 * @return the number
	 */
	protected final String getNumber(final Number value) {
		return value == null ? "NULL" : value.toString();
	}

	/**
	 * Gets the date.
	 *
	 * @param value
	 *            the value
	 * @return the date
	 */
	protected final String getDate(final Date value) {
		return value == null ? "NULL" : "'" + value.toString() + "'";
	}

	/**
	 * Gets the boolean.
	 *
	 * @param value
	 *            the value
	 * @return the boolean
	 */
	protected final String getBoolean(final Boolean value) {
		return value == null ? "NULL" : (value ? "1" : "0");
	}

}

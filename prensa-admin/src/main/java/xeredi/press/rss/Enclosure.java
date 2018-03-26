package xeredi.press.rss;

import lombok.Data;

/**
 * Instantiates a new enclosure.
 */
@Data
public final class Enclosure {
	
	/** The url. */
	private String url;
	
	/** The length. */
	private String length;
	
	/** The type. */
	private String type;
}

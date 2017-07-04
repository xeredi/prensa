package xeredi.press.model;

import lombok.Data;

// TODO: Auto-generated Javadoc
/**
 * Instantiates a new publisher.
 */
@Data
public final class Publisher {

	/** The id. */
	private Long id;

	/** The country. */
	private String country;

	/** The language. */
	private String language;

	/** The name. */
	private String name;

	/** The web url. */
	private String webUrl;

	/** The logo url. */
	private String logoUrl;
}

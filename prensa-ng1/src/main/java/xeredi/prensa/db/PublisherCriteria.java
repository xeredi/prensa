package xeredi.prensa.db;

import org.bson.types.ObjectId;

import lombok.Data;

// TODO: Auto-generated Javadoc
/**
 * Instantiates a new Publisher criteria.
 */
@Data
public final class PublisherCriteria {

	/** The id. */
	private ObjectId id;

	/** The name. */
	private String name;

	/** The url. */
	private String url;
}

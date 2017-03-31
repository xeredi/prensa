package xeredi.prensa.db;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;

import lombok.Data;

// TODO: Auto-generated Javadoc
/**
 * Instantiates a new feed criteria.
 */
@Data
public final class FeedCriteria {

	/** The id. */
	@Id
	private ObjectId id;

	/** The publisher id. */
	private ObjectId publisherId;

	/** The podcast. */
	private Boolean podcast;

	/** The url. */
	private String url;

	/** The feed type. */
	private String feedType;

	/** The language. */
	private String language;
}

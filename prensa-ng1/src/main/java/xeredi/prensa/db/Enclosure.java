package xeredi.prensa.db;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.IndexOptions;
import org.mongodb.morphia.annotations.Indexes;

import lombok.Data;

// TODO: Auto-generated Javadoc
/**
 * The Class Enclosure.
 */
@Entity("enclosure")
@Indexes(@Index(fields = @Field("entryId"), options = @IndexOptions(name = "ix_encl_entryId", unique = false)))

/**
 * Instantiates a new enclosure.
 */
@Data
public final class Enclosure {

	/** The id. */
	@Id
	private ObjectId id;

	/** The entry id. */
	private ObjectId entryId;

	/** The type. */
	private String type;

	/** The length. */
	private Long length;

	/** The url. */
	private String url;
}

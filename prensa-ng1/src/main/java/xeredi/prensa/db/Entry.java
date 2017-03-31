package xeredi.prensa.db;

import java.util.HashSet;
import java.util.Set;

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
 * The Class Entry.
 */
@Entity(value = "entry", noClassnameStored = true)
@Indexes({
		@Index(fields = @Field("publisherId"), options = @IndexOptions(name = "ix_entr_publisherId", unique = false)),
		@Index(fields = @Field("uri"), options = @IndexOptions(name = "ix_entr_uri", unique = true)) })
@Data
public final class Entry {

	/** The id. */
	@Id
	private ObjectId id;

	/** The publisher id. */
	private ObjectId publisherId;

	/** The feed ids. */
	private final Set<ObjectId> feedIds = new HashSet<>();

	/** The author. */
	private String author;

	/** The comments. */
	private String comments;

	/** The description. */
	private String description;

	/** The description type. */
	private String descriptionType;

	// /** The link. */
	// private String link;

	/** The title. */
	private String title;

	/** The uri. */
	private String uri;

	/** The media title. */
	private String mediaTitle;

	/** The media description. */
	private String mediaDescription;

	/** The media th url. */
	private String mediaThUrl;

	/** The media th type. */
	private String mediaThType;

	/** The media th width. */
	private String mediaThWidth;

	/** The media th height. */
	private String mediaThHeight;

	/** The media cnt url. */
	private String mediaCntUrl;

	/** The media cnt type. */
	private String mediaCntType;

	/** The media cnt width. */
	private String mediaCntWidth;

	/** The media cnt height. */
	private String mediaCntHeight;
}

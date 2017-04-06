package xeredi.prensa.db;

import java.util.Date;

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
 * The Class Feed.
 */
@Entity(value = "feed", noClassnameStored = true)
@Indexes({
		@Index(fields = @Field("publisherId"), options = @IndexOptions(name = "ix_feed_publisherId", unique = false)),
		@Index(fields = @Field("url"), options = @IndexOptions(name = "ix_feed_url", unique = true)) })
@Data
public final class Feed {

	/** The id. */
	@Id
	private ObjectId id;

	/** The publisher id. */
	private ObjectId publisherId;

	private Boolean podcast;

	/** The url. */
	private String url;

	/** The author. */
	private String author;

	/** The copyright. */
	private String copyright;

	/** The description. */
	private String description;

	/** The encoding. */
	private String encoding;

	/** The feed type. */
	private String feedType;

	/** The generator. */
	private String generator;

	/** The language. */
	private String language;

	/** The link. */
	private String link;

	/** The published date. */
	private Date publishedDate;

	/** The title. */
	private String title;

    private String subtitle;

	/** The uri. */
	private String uri;

	/** The im url. */
	private String imUrl;

	/** The im width. */
	private Integer imWidth;

	/** The im height. */
	private Integer imHeight;
}

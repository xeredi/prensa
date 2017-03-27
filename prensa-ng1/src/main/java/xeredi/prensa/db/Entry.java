package xeredi.prensa.db;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.IndexOptions;
import org.mongodb.morphia.annotations.Indexes;

import lombok.Data;

@Entity("entry")
@Indexes(@Index(fields = @Field("publisherId"), options = @IndexOptions(name = "ix_entr_publisherId", unique = false)))
@Data
public final class Entry {

	/** The id. */
	@Id
	private ObjectId id;

	/** The publisher id. */
	private ObjectId publisherId;

	private String author;

	private String comments;

	private String description;

	private String descriptionType;

	private String link;

	private String title;

	private String uri;

	private String mediaTitle;

	private String mediaDescription;

	private String mediaThUrl;

	private String mediaThType;

	private String mediaThWidth;

	private String mediaThHeight;

	private String mediaCntUrl;

	private String mediaCntType;

	private String mediaCntWidth;

	private String mediaCntHeight;
}

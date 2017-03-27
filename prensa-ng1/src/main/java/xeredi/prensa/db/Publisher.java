package xeredi.prensa.db;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.IndexOptions;
import org.mongodb.morphia.annotations.Indexes;

import com.mongodb.MongoClient;

import lombok.Data;
import xeredi.prensa.dao.PublisherDAO;
import xeredi.prensa.morphia.DatastoreLocator;

// TODO: Auto-generated Javadoc
/**
 * The Class Publisher.
 */
@Entity("publisher")
@Indexes(@Index(fields = @Field("name"), options = @IndexOptions(name = "ix_pblr_name", unique = true)))
@Data
public class Publisher {

	/**
	 * Instantiates a new publisher.
	 */
	public Publisher() {
		super();
	}

	/**
	 * Instantiates a new publisher.
	 *
	 * @param aname
	 *            the aname
	 * @param arssHomeUrl
	 *            the arss home url
	 */
	public Publisher(final String aname, final String arssHomeUrl) {
		super();

		this.name = aname;
		this.rssHomeUrl = arssHomeUrl;
	}

	/** The id. */
	@Id
	private ObjectId id;

	/** The name. */
	private String name;

	/** The rss home url. */
	private String rssHomeUrl;

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(final String[] args) {
		final PublisherDAO publisherDAO = new PublisherDAO(DatastoreLocator.findDatastore());

		publisherDAO.insert(new Publisher("Onda Cero", "http://www.ondacero.es/podcast/"));
		publisherDAO.insert(new Publisher("Cadena SER", "http://cadenaser.com/ser/podcasts/"));
		publisherDAO.insert(new Publisher("BOE", "https://www.boe.es/rss/"));
		publisherDAO.insert(new Publisher("Marca", "http://www.marca.com/deporte/rss/"));
	}
}

package xeredi.prensa.dao;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import lombok.NonNull;
import xeredi.prensa.db.Feed;

// TODO: Auto-generated Javadoc
/**
 * The Class FeedDAO.
 */
public final class FeedDAO {

	/** The datastore. */
	private final @NonNull Datastore datastore;

	/**
	 * Instantiates a new feed DAO.
	 *
	 * @param adatastore
	 *            the adatastore
	 */
	public FeedDAO(final @NonNull Datastore adatastore) {
		super();
		this.datastore = adatastore;
	}

	/**
	 * Select by id.
	 *
	 * @param id
	 *            the id
	 * @return the feed
	 */
	public Feed selectById(final @NonNull ObjectId id) {
		return datastore.createQuery(Feed.class).field("_id").equal(id).get();
	}

	/**
	 * Select by url.
	 *
	 * @param url
	 *            the url
	 * @return the feed
	 */
	public Feed selectByUrl(final @NonNull String url) {
		return datastore.createQuery(Feed.class).field("url").equal(url).get();
	}

	/**
	 * Exists.
	 *
	 * @param feed
	 *            the feed
	 * @return true, if successful
	 */
	public boolean exists(final @NonNull Feed feed) {
		return selectByUrl(feed.getUrl()) != null;
	}

	/**
	 * Insert.
	 *
	 * @param feed
	 *            the feed
	 */
	public void insert(final @NonNull Feed feed) {
		if (exists(feed)) {
			throw new Error("Duplicate: " + feed);
		}

		datastore.save(feed);
	}

	/**
	 * Update.
	 *
	 * @param feed
	 *            the feed
	 */
	public void update(final @NonNull Feed feed) {
		final Query<Feed> query = datastore.createQuery(Feed.class).field("url").equal(feed.getUrl());

		final UpdateOperations<Feed> operations = datastore.createUpdateOperations(Feed.class);

		if (feed.getAuthor() != null) {
			operations.set("author", feed.getAuthor());
		}
		if (feed.getCopyright() != null) {
			operations.set("copyright", feed.getCopyright());
		}
		if (feed.getDescription() != null) {
			operations.set("description", feed.getDescription());
		}
		if (feed.getEncoding() != null) {
			operations.set("encoding", feed.getEncoding());
		}
		if (feed.getFeedType() != null) {
			operations.set("feedType", feed.getFeedType());
		}
		if (feed.getGenerator() != null) {
			operations.set("generator", feed.getGenerator());
		}
		if (feed.getImHeight() != null) {
			operations.set("imHeight", feed.getImHeight());
		}
		if (feed.getImWidth() != null) {
			operations.set("imWidth", feed.getImWidth());
		}
		if (feed.getLanguage() != null) {
			operations.set("language", feed.getLanguage());
		}
		if (feed.getLink() != null) {
			operations.set("link", feed.getLink());
		}
		if (feed.getPodcast() != null) {
			operations.set("podcast", feed.getPodcast());
		}
		if (feed.getPublishedDate() != null) {
			operations.set("publishedDate", feed.getPublishedDate());
		}
		if (feed.getPublisherId() != null) {
			operations.set("publisherId", feed.getPublisherId());
		}
		if (feed.getTitle() != null) {
			operations.set("title", feed.getTitle());
		}
		if (feed.getUri() != null) {
			operations.set("uri", feed.getUri());
		}

		datastore.update(query, operations);
	}

}

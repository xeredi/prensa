package xeredi.prensa.dao;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import lombok.NonNull;
import xeredi.prensa.db.Entry;

// TODO: Auto-generated Javadoc
/**
 * The Class EntryDAO.
 */
public final class EntryDAO {

	/** The datastore. */
	private final @NonNull Datastore datastore;

	/**
	 * Instantiates a new entry DAO.
	 *
	 * @param adatastore
	 *            the adatastore
	 */
	public EntryDAO(final @NonNull Datastore adatastore) {
		super();
		this.datastore = adatastore;
	}

	/**
	 * Select by id.
	 *
	 * @param id
	 *            the id
	 * @return the entry
	 */
	public Entry selectById(final @NonNull ObjectId id) {
		return datastore.createQuery(Entry.class).field("_id").equal(id).get();
	}

	/**
	 * Select by link.
	 *
	 * @param link
	 *            the link
	 * @return the entry
	 */
	public Entry selectByUri(final @NonNull String uri) {
		return datastore.createQuery(Entry.class).field("uri").equal(uri).get();
	}

	/**
	 * Exists.
	 *
	 * @param entry
	 *            the entry
	 * @return true, if successful
	 */
	public boolean exists(final @NonNull Entry entry) {
		return selectByUri(entry.getUri()) != null;
	}

	/**
	 * Insert.
	 *
	 * @param entry
	 *            the entry
	 */
	public void insert(final @NonNull Entry entry) {
		if (exists(entry)) {
			throw new Error("Duplicate: " + entry);
		}

		datastore.save(entry);
	}

	/**
	 * Update.
	 *
	 * @param entry
	 *            the entry
	 */
	public void update(final @NonNull Entry entry) {
		final Entry updated = datastore.createQuery(Entry.class).field("uri").equal(entry.getUri()).get();

		if (updated == null) {
			throw new Error("Entry not found: " + entry);
		}

		final UpdateOperations<Entry> operations = datastore.createUpdateOperations(Entry.class);

		if (entry.getFeedIds() != null) {
			for (final ObjectId objectId : entry.getFeedIds()) {
				operations.addToSet("feedIds", objectId);
			}
		}

		if (entry.getAuthor() != null) {
			operations.set("author", entry.getAuthor());
		}
		if (entry.getComments() != null) {
			operations.set("comments", entry.getComments());
		}
		if (entry.getDescription() != null) {
			operations.set("description", entry.getDescription());
		}
		if (entry.getDescriptionType() != null) {
			operations.set("descriptionType", entry.getDescriptionType());
		}
		if (entry.getMediaCntHeight() != null) {
			operations.set("mediaCntHeight", entry.getMediaCntHeight());
		}
		if (entry.getMediaCntType() != null) {
			operations.set("mediaCntType", entry.getMediaCntType());
		}
		if (entry.getMediaCntUrl() != null) {
			operations.set("mediaCntUrl", entry.getMediaCntUrl());
		}
		if (entry.getMediaCntWidth() != null) {
			operations.set("mediaCntWidth", entry.getMediaCntWidth());
		}
		if (entry.getMediaDescription() != null) {
			operations.set("mediaDescription", entry.getMediaDescription());
		}
		if (entry.getMediaThHeight() != null) {
			operations.set("mediaThHeight", entry.getMediaThHeight());
		}
		if (entry.getMediaThType() != null) {
			operations.set("mediaThType", entry.getMediaThType());
		}
		if (entry.getMediaThUrl() != null) {
			operations.set("mediaThUrl", entry.getMediaThUrl());
		}
		if (entry.getMediaThWidth() != null) {
			operations.set("mediaThWidth", entry.getMediaThWidth());
		}
		if (entry.getMediaTitle() != null) {
			operations.set("mediaTitle", entry.getMediaTitle());
		}
		if (entry.getPublisherId() != null) {
			operations.set("publisherId", entry.getPublisherId());
		}
		if (entry.getTitle() != null) {
			operations.set("title", entry.getTitle());
		}

		datastore.update(updated, operations);

		entry.setId(updated.getId());
	}

}

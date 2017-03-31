package xeredi.prensa.dao;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import lombok.NonNull;
import xeredi.prensa.db.Enclosure;
import xeredi.prensa.db.Entry;

// TODO: Auto-generated Javadoc
/**
 * The Class EnclosureDAO.
 */
public final class EnclosureDAO {

	/** The datastore. */
	private final @NonNull Datastore datastore;

	/**
	 * Instantiates a new enclosure DAO.
	 *
	 * @param adatastore
	 *            the adatastore
	 */
	public EnclosureDAO(final @NonNull Datastore adatastore) {
		super();
		this.datastore = adatastore;
	}

	/**
	 * Select by id.
	 *
	 * @param id
	 *            the id
	 * @return the enclosure
	 */
	public Enclosure selectById(final @NonNull ObjectId id) {
		return datastore.createQuery(Enclosure.class).field("_id").equal(id).get();
	}

	/**
	 * Select by url.
	 *
	 * @param entryId
	 *            the entry id
	 * @param url
	 *            the url
	 * @return the enclosure
	 */
	public Enclosure selectByUrl(final @NonNull ObjectId entryId, final @NonNull String url) {
		return datastore.createQuery(Enclosure.class).field("entryId").equal(entryId).field("url").equal(url).get();
	}

	/**
	 * Exists.
	 *
	 * @param enclosure
	 *            the enclosure
	 * @return true, if successful
	 */
	public boolean exists(final @NonNull Enclosure enclosure) {
		return selectByUrl(enclosure.getEntryId(), enclosure.getUrl()) != null;
	}

	/**
	 * Insert.
	 *
	 * @param enclosure
	 *            the enclosure
	 */
	public void insert(final @NonNull Enclosure enclosure) {
		if (exists(enclosure)) {
			throw new Error("Duplicate: " + enclosure);
		}

		datastore.save(enclosure);
	}

	/**
	 * Update.
	 *
	 * @param enclosure
	 *            the enclosure
	 */
	public void update(final @NonNull Enclosure enclosure) {
		final Query<Enclosure> query = datastore.createQuery(Enclosure.class).field("entryId")
				.equal(enclosure.getEntryId()).field("url").equal(enclosure.getUrl());
		final UpdateOperations<Enclosure> operations = datastore.createUpdateOperations(Enclosure.class);

		if (enclosure.getLength() != null) {
			operations.set("length", enclosure.getLength());
		}
		if (enclosure.getType() != null) {
			operations.set("type", enclosure.getType());
		}

		datastore.update(query, operations);
	}

}

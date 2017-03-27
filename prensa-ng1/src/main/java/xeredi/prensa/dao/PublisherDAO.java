package xeredi.prensa.dao;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import lombok.NonNull;
import xeredi.prensa.db.Publisher;
import xeredi.prensa.db.PublisherCriteria;

// TODO: Auto-generated Javadoc
/**
 * The Class PublisherDAO.
 */
public final class PublisherDAO {

	/** The datastore. */
	private final @NonNull Datastore datastore;

	/**
	 * Instantiates a new publisher DAO.
	 *
	 * @param adatastore
	 *            the adatastore
	 */
	public PublisherDAO(final @NonNull Datastore adatastore) {
		super();
		this.datastore = adatastore;
	}

	/**
	 * Select by id.
	 *
	 * @param id
	 *            the id
	 * @return the publisher
	 */
	public Publisher selectById(final @NonNull ObjectId id) {
		return datastore.createQuery(Publisher.class).field("_id").equal(id).get();
	}

	/**
	 * Select list.
	 *
	 * @param criteria the criteria
	 * @return the list
	 */
	public List<Publisher> selectList(final @NonNull PublisherCriteria criteria) {
		final Query<Publisher> query = datastore.createQuery(Publisher.class);

		if (criteria.getId() != null) {
			query.field("_id").equal(criteria.getId());
		}
		if (criteria.getName() != null) {
			query.field("name").equal(criteria.getName());
		}
		if (criteria.getUrl() != null) {
			query.field("url").equal(criteria.getUrl());
		}

		return query.asList();
	}

	/**
	 * Select by name.
	 *
	 * @param name
	 *            the name
	 * @return the publisher
	 */
	public Publisher selectByName(final @NonNull String name) {
		return datastore.createQuery(Publisher.class).field("name").equal(name).get();
	}

	/**
	 * Exists.
	 *
	 * @param publisher
	 *            the publisher
	 * @return true, if successful
	 */
	public boolean exists(final @NonNull Publisher publisher) {
		return selectByName(publisher.getName()) != null;
	}

	/**
	 * Insert.
	 *
	 * @param publisher
	 *            the publisher
	 */
	public void insert(final @NonNull Publisher publisher) {
		if (exists(publisher)) {
			throw new Error("Duplicate: " + publisher);
		}

		datastore.save(publisher);
	}
}

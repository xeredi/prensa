package xeredi.prensa.morphia;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;

// TODO: Auto-generated Javadoc
/**
 * The Class DatastoreLocator.
 */
public final class DatastoreLocator {

	/**
	 * Find datastore.
	 *
	 * @return the datastore
	 */
	public static Datastore findDatastore() {
		final Morphia morphia = new Morphia();
		final Datastore datastore = morphia.createDatastore(new MongoClient(), "press");

		datastore.ensureIndexes();

		return datastore;
	}

}

package xeredi.prensa.export;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.mapping.Mapper;

import xeredi.prensa.dao.FeedDAO;
import xeredi.prensa.db.Feed;
import xeredi.prensa.db.FeedCriteria;
import xeredi.prensa.morphia.DatastoreLocator;

public final class FeedExport {
	public void export(final List<Feed> feeds, final OutputStream stream) throws IOException {
		final Mapper mapper = new Mapper();

		stream.write('[');

		final Iterator<Feed> iterator = feeds.iterator();

		while (iterator.hasNext()) {
			stream.write(mapper.toDBObject(iterator.next()).toString().getBytes());

			if (iterator.hasNext()) {
				stream.write(',');
			}
		}

		stream.write(']');
	}

	public static void main(final String[] args) {
		final Datastore datastore = DatastoreLocator.findDatastore();
		final FeedDAO feedDAO = new FeedDAO(datastore);
		final FeedExport feedExport = new FeedExport();

		try (final OutputStream outputStream = new FileOutputStream("/home/xeredi/feeds.generated.json")) {
			feedExport.export(feedDAO.selectList(new FeedCriteria()), outputStream);
		} catch (final IOException ex) {
			ex.printStackTrace(System.err);
		}
	}
}

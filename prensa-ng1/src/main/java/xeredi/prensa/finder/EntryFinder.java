package xeredi.prensa.finder;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;

import org.jdom2.Element;
import org.mongodb.morphia.Datastore;
import org.xml.sax.InputSource;

import com.rometools.rome.feed.synd.SyndEnclosure;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;

import xeredi.prensa.dao.EnclosureDAO;
import xeredi.prensa.dao.EntryDAO;
import xeredi.prensa.db.Enclosure;
import xeredi.prensa.db.Entry;
import xeredi.prensa.db.Feed;
import xeredi.prensa.morphia.DatastoreLocator;

// TODO: Auto-generated Javadoc
/**
 * The Class ItemFinder.
 */
public final class EntryFinder {

	/**
	 * Find items.
	 *
	 * @param datastore
	 *            the datastore
	 * @param feed
	 *            the feed
	 */
	public void findEntries(final Datastore datastore, final Feed feed) {
		final SyndFeedInput input = new SyndFeedInput();

		final EntryDAO entryDAO = new EntryDAO(datastore);
		final EnclosureDAO enclosureDAO = new EnclosureDAO(datastore);

		try (final InputStream is = new URL(feed.getUrl()).openConnection().getInputStream()) {
			// System.out.println("Get: " +
			// feed.getUrl());

			final InputSource source = new InputSource(is);
			final SyndFeed syndFeed = input.build(source);

			for (final SyndEntry syndEntry : syndFeed.getEntries()) {
				final Entry entry = new Entry();

				entry.setPublisherId(feed.getPublisherId());
				entry.getFeedIds().add(feed.getId());

				entry.setAuthor(syndEntry.getAuthor());
				entry.setComments(syndEntry.getComments());
				entry.setTitle(syndEntry.getTitle());
				// entry.setLink(syndEntry.getLink());
				entry.setUri(syndEntry.getUri());

				if (syndEntry.getDescription() != null) {
					entry.setDescriptionType(syndEntry.getDescription().getType());
					entry.setDescription(syndEntry.getDescription().getValue());
				}

				// System.out.println("Entry: " + syndEntry);

				for (final Element element : syndEntry.getForeignMarkup()) {
					if ("media".equals(element.getNamespace().getPrefix()) && "title".equals(element.getName())) {
						entry.setMediaTitle(element.getText());
					}
					if ("media".equals(element.getNamespace().getPrefix()) && "description".equals(element.getName())) {
						entry.setMediaDescription(element.getText());
					}
					if ("media".equals(element.getNamespace().getPrefix()) && "thumbnail".equals(element.getName())) {
						entry.setMediaThUrl(element.getAttributeValue("url"));
						entry.setMediaThType(element.getAttributeValue("type"));
						entry.setMediaThWidth(element.getAttributeValue("width"));
						entry.setMediaThHeight(element.getAttributeValue("height"));
					}
					if ("media".equals(element.getNamespace().getPrefix()) && "content".equals(element.getName())) {
						entry.setMediaCntUrl(element.getAttributeValue("url"));
						entry.setMediaCntType(element.getAttributeValue("type"));
						entry.setMediaCntWidth(element.getAttributeValue("width"));
						entry.setMediaCntHeight(element.getAttributeValue("height"));
					}
					if ("itunes".equals(element.getNamespace().getPrefix()) && "summary".equals(element.getName())) {
						System.out.println("Element: " + element.getName());
						System.out.println("Attributes: " + element.getAttributes());
					}
					if ("itunes".equals(element.getNamespace().getPrefix()) && "image".equals(element.getName())) {
						System.out.println("Element: " + element.getName());
						System.out.println("Attributes: " + element.getAttributes());
					}
				}

				if (entry.getUri() == null) {
					System.err.println("URI null for Entry: " + entry);
				} else {
					if (entryDAO.exists(entry)) {
						entryDAO.update(entry);
					} else {
						entryDAO.insert(entry);
					}

					for (final SyndEnclosure syndEnclosure : syndEntry.getEnclosures()) {
						final Enclosure enclosure = new Enclosure();

						enclosure.setEntryId(entry.getId());

						enclosure.setLength(syndEnclosure.getLength());
						enclosure.setType(syndEnclosure.getType());
						enclosure.setUrl(syndEnclosure.getUrl());

						if (enclosureDAO.exists(enclosure)) {
							enclosureDAO.update(enclosure);
						} else {
							enclosureDAO.insert(enclosure);
						}
					}
				}
			}

			System.out.println("OK: " + feed.getUrl());
		} catch (final MalformedURLException ex) {
			System.err.println("MalformedURLException with: " + feed.getUrl());
		} catch (final FeedException ex) {
			System.err.println("FeedException with: " + feed.getUrl());
		} catch (final IllegalArgumentException ex) {
			System.err.println("IllegalArgumentException with: " + feed.getUrl());
		} catch (final IOException ex) {
			System.err.println("IOException with: " + feed.getUrl());
		}
	}

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(final String[] args) {
		final long start = Calendar.getInstance().getTimeInMillis();

		final EntryFinder itemFinder = new EntryFinder();
		final Datastore datastore = DatastoreLocator.findDatastore();

		for (final Feed feed : datastore.createQuery(Feed.class).asList()) {
			itemFinder.findEntries(datastore, feed);
		}

		System.out.println((Calendar.getInstance().getTimeInMillis() - start) + " ms.");
	}

}

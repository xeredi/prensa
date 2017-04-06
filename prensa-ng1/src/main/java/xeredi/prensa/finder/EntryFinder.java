package xeredi.prensa.finder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
    private static final Log LOG = LogFactory.getLog(EntryFinder.class);

    private static final String RSS_HOME = "/home/xeredi/git/prensa/prensa-ng1/src/main/webapp/rss/";

    /**
     * Find items.
     *
     * @param datastore
     *            the datastore
     * @param feed
     *            the feed
     */
    public void findEntries(final Datastore datastore, final Feed feed) throws MalformedURLException {
        final SyndFeedInput input = new SyndFeedInput();

        final EntryDAO entryDAO = new EntryDAO(datastore);
        final EnclosureDAO enclosureDAO = new EnclosureDAO(datastore);

        final URL url = new URL(feed.getUrl());
        final File file = new File(RSS_HOME + url.getHost() + url.getPath());

        file.getParentFile().mkdirs();

        try (final InputStream is = url.openConnection().getInputStream()) {
            // LOG.debug("Get: " +
            // feed.getUrl());

            try {
                IOUtils.copy(url.openStream(), new FileOutputStream(file));
            } catch (final IOException ex) {
                LOG.error("IOException with: " + feed.getUrl());
                LOG.error(ex.getMessage());
            }

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

                // LOG.debug("Entry: " + syndEntry);

                for (final Element element : syndEntry.getForeignMarkup()) {
                    switch (element.getNamespace().getPrefix()) {
                    case "media":
                        switch (element.getName()) {
                        case "title":
                            entry.setMediaTitle(element.getText());

                            break;
                        case "description":
                            entry.setMediaDescription(element.getText());

                            break;
                        case "thumbnail":
                            entry.setMediaThUrl(element.getAttributeValue("url"));
                            entry.setMediaThType(element.getAttributeValue("type"));
                            entry.setMediaThWidth(element.getAttributeValue("width"));
                            entry.setMediaThHeight(element.getAttributeValue("height"));

                            break;
                        case "content":
                            entry.setMediaCntUrl(element.getAttributeValue("url"));
                            entry.setMediaCntType(element.getAttributeValue("type"));
                            entry.setMediaCntWidth(element.getAttributeValue("width"));
                            entry.setMediaCntHeight(element.getAttributeValue("height"));

                            break;
                        case "keywords":
                            break;
                        default:
                            LOG.debug("Unknown media Element: " + element.getName() + ", Attributes: "
                                    + element.getAttributes());

                            break;
                        }

                        break;
                    case "itunes":
                        switch (element.getName()) {
                        case "image":
                            if (entry.getMediaCntUrl() == null) {
                                entry.setMediaCntUrl(element.getText());
                            }

                            break;
                        case "summary":
                            if (entry.getDescription() == null) {
                                entry.setDescription(element.getText());
                            }

                            break;
                        case "author":
                            if (entry.getAuthor() == null) {
                                entry.setAuthor(element.getText());
                            }

                            break;
                        case "subtitle":
                            entry.setSubtitle(element.getText());

                            break;
                        case "duration":
                            break;
                        case "explicit":
                            break;
                        default:
                            LOG.debug("Unknown itunes Element: " + element.getName() + ", Attributes: "
                                    + element.getAttributes());

                            break;
                        }

                        break;
                    case "feedburner":
                        switch (element.getName()) {
                        case "origLink":
                            // if (entry.getLink() == null) {
                            // entry.setLink(element.getText());
                            // }

                            break;
                        default:
                            LOG.debug("Unknown feedburner Element: " + element.getName() + ", Attributes: "
                                    + element.getAttributes());

                            break;
                        }

                        break;
                    case "slash":
                        switch (element.getName()) {
                        case "comments":
                            break;
                        default:
                            LOG.debug("Unknown slash Element: " + element.getName() + ", Attributes: "
                                    + element.getAttributes());

                            break;
                        }

                        break;
                    case "wfw":
                        switch (element.getName()) {
                        case "commentRss":
                            break;
                        default:
                            LOG.debug("Unknown wfw Element: " + element.getName() + ", Attributes: "
                                    + element.getAttributes());

                            break;
                        }

                        break;
                    default:
                        LOG.debug("Unknown Prefix: " + element.getNamespace().getPrefix() + ", Name: "
                                + element.getName() + ", Attributes: " + element.getAttributes());

                        break;
                    }
                }

                if (entry.getUri() == null) {
                    LOG.error("URI null for Entry: " + entry);
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

            LOG.info("OK: " + feed.getUrl());

            // LOG.info("Host: " + url.getHost());
            // LOG.info("Path: " + url.getPath());
        } catch (final FeedException ex) {
            LOG.error("FeedException with: " + feed.getUrl());
            LOG.error(ex.getMessage());
        } catch (final IllegalArgumentException ex) {
            LOG.error("IllegalArgumentException with: " + feed.getUrl());
            LOG.error(ex.getMessage());
        } catch (final IOException ex) {
            LOG.error("IOException with: " + feed.getUrl());
            LOG.error(ex.getMessage());
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
            try {
                itemFinder.findEntries(datastore, feed);
            } catch (final MalformedURLException ex) {
                LOG.fatal("MalformedURLException with: " + feed.getUrl());
                LOG.fatal(ex.getMessage());
            }
        }

        LOG.info((Calendar.getInstance().getTimeInMillis() - start) + " ms.");
    }

}

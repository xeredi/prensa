package xeredi.prensa.finder;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.validator.routines.UrlValidator;
import org.jdom2.Element;
import org.mongodb.morphia.Datastore;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;

import xeredi.prensa.dao.FeedDAO;
import xeredi.prensa.dao.PublisherDAO;
import xeredi.prensa.db.Feed;
import xeredi.prensa.db.Publisher;
import xeredi.prensa.db.PublisherCriteria;
import xeredi.prensa.morphia.DatastoreLocator;

// TODO: Auto-generated Javadoc
/**
 * The Class ChannelFinder.
 */
public final class FeedFinder {

    /** The Constant HREF_TOKEN. */
    public static final String HREF_TOKEN = "href";

    /** The Constant FIRST_LIMIT_TOKEN. */
    public static final String FIRST_LIMIT_TOKEN = "\"";

    /** The Constant SECOND_LIMIT_TOKEN. */
    public static final String SECOND_LIMIT_TOKEN = "\"";

    /** The Constant CHANNEL_FILE_EXTENSION. */
    public static final String CHANNEL_FILE_EXTENSION = "xml";

    /**
     * Find channels.
     *
     * @param publisher
     *            the publisher
     * @throws SAXException
     *             the SAX exception
     * @throws ParserConfigurationException
     *             the parser configuration exception
     * @throws MalformedURLException
     *             the malformed URL exception
     */
    public void findFeeds(final Publisher publisher)
            throws SAXException, ParserConfigurationException, MalformedURLException {
        final URL webUrl = new URL(publisher.getRssHomeUrl());
        final SyndFeedInput input = new SyndFeedInput();
        final UrlValidator urlValidator = new UrlValidator();

        final Datastore datastore = DatastoreLocator.findDatastore();
        final FeedDAO feedDAO = new FeedDAO(datastore);
        final Set<String> urlProcessedSet = new HashSet<>();

        try (final Scanner scanner = new Scanner(new URL(publisher.getRssHomeUrl()).openStream())) {
            while (scanner.hasNextLine()) {
                final String line = scanner.nextLine();
                int startPosition = 0;

                do {
                    System.out.print('.');

                    final int hrefPosition = line.indexOf(HREF_TOKEN, startPosition);

                    if (hrefPosition >= startPosition) {
                        startPosition = hrefPosition;

                        final int firstLimit = line.indexOf(FIRST_LIMIT_TOKEN, hrefPosition);

                        if (firstLimit >= hrefPosition) {
                            startPosition = firstLimit;

                            final int secondLimit = line.indexOf(SECOND_LIMIT_TOKEN, firstLimit + 1);

                            if (secondLimit > firstLimit) {
                                startPosition = secondLimit;

                                final String fileUrl = line.substring(firstLimit + 1, secondLimit).trim();

                                String channelUrl = null;

                                if (fileUrl.startsWith("http") || fileUrl.startsWith("www")) {
                                    channelUrl = fileUrl;
                                } else if (fileUrl.startsWith("/")) {
                                    channelUrl = webUrl.getProtocol() + "://" + webUrl.getHost() + fileUrl;
                                } else {
                                    channelUrl = webUrl.getProtocol() + "://" + webUrl.getHost()
                                            + webUrl.getPath().substring(0, webUrl.getPath().lastIndexOf("/") + 1)
                                            + fileUrl;
                                }

                                if (!channelUrl.endsWith("css") && !channelUrl.endsWith("js")
                                        && !channelUrl.endsWith("gif") && !channelUrl.endsWith("png")
                                        && !channelUrl.endsWith("ico") && !channelUrl.endsWith("json")
                                        && channelUrl.startsWith("http")) {

                                    if (!urlProcessedSet.contains(channelUrl)) {
                                        System.out.println("channelUrl: " + channelUrl);

                                        urlProcessedSet.add(channelUrl);

                                        if (urlValidator.isValid(channelUrl)) {
                                            final HttpURLConnection urlConnection = (HttpURLConnection) (new URL(
                                                    channelUrl).openConnection());

                                            urlConnection.setConnectTimeout(5000);
                                            urlConnection.setReadTimeout(5000);

                                            try {
                                                urlConnection.connect();

                                                try (final InputStream is = urlConnection.getInputStream()) {
                                                    // System.out.println("Get: " +
                                                    // channelUrl);

                                                    final InputSource source = new InputSource(is);
                                                    final SyndFeed syndFeed = input.build(source);

                                                    final Feed feed = new Feed();

                                                    feed.setPublisherId(publisher.getId());
                                                    feed.setUrl(channelUrl);

                                                    feed.setAuthor(syndFeed.getAuthor());
                                                    feed.setCopyright(syndFeed.getCopyright());
                                                    feed.setDescription(syndFeed.getDescription());
                                                    feed.setEncoding(syndFeed.getEncoding());
                                                    feed.setFeedType(syndFeed.getFeedType());
                                                    feed.setGenerator(syndFeed.getGenerator());
                                                    feed.setLanguage(syndFeed.getLanguage());
                                                    feed.setLink(syndFeed.getLink());
                                                    feed.setPublishedDate(syndFeed.getPublishedDate());
                                                    feed.setTitle(syndFeed.getTitle());
                                                    feed.setUri(syndFeed.getUri());

                                                    if (syndFeed.getImage() != null) {
                                                        feed.setImUrl(syndFeed.getImage().getUrl());
                                                        feed.setImWidth(syndFeed.getImage().getWidth());
                                                        feed.setImHeight(syndFeed.getImage().getHeight());
                                                    }

                                                    feed.setPodcast(false);

                                                    for (final Element element : syndFeed.getForeignMarkup()) {
                                                        if ("itunes".equals(element.getNamespace().getPrefix())) {
                                                            feed.setPodcast(true);
                                                        }

                                                        if ("image".equals(element.getName())) {
                                                            System.out.println("Element: " + element.getName());
                                                            System.out
                                                                    .println("Attributes: " + element.getAttributes());

                                                            feed.setImUrl(element.getAttributeValue("href"));
                                                        }
                                                    }

                                                    if (feedDAO.exists(feed)) {
                                                        feedDAO.update(feed);
                                                    } else {
                                                        feedDAO.insert(feed);
                                                    }

                                                    System.out.println("OK: " + channelUrl);
                                                } catch (final MalformedURLException ex) {
                                                } catch (final FeedException ex) {
                                                    System.err.println("FeedException with: " + channelUrl);
                                                } catch (final IllegalArgumentException ex) {
                                                    System.err.println("IllegalArgumentException with: " + channelUrl);
                                                } catch (final IOException ex) {
                                                    System.err.println("IOException with: " + channelUrl);
                                                }
                                            } catch (final IOException ex) {
                                                System.err.println("IOException with: " + channelUrl);
                                            } finally {
                                                urlConnection.disconnect();
                                            }
                                        } else {
                                            System.err.println("Invalid URL: " + channelUrl);
                                        }
                                    }
                                }
                            } else {
                                // System.out.println("No more channels");

                                startPosition = -1;
                            }
                        } else {
                            // System.out.println("No more channels");

                            startPosition = -1;
                        }
                    } else {
                        // System.out.println("No more channels");

                        startPosition = -1;
                    }
                } while (startPosition > 0);
            }
        } catch (final IOException ex) {
            System.err.println("Error accediendo a la url: " + publisher.getRssHomeUrl());
            ex.printStackTrace(System.err);
        }
    }

    /**
     * The main method.
     *
     * @param args
     *            the arguments
     */
    public static void main(final String[] args) {
        final FeedFinder feedFinder = new FeedFinder();

        final long start = Calendar.getInstance().getTimeInMillis();

        try {
            final Datastore datastore = DatastoreLocator.findDatastore();
            final PublisherDAO publisherDAO = new PublisherDAO(datastore);

            for (final Publisher publisher : publisherDAO.selectList(new PublisherCriteria())) {
                feedFinder.findFeeds(publisher);
            }

            // channelFinder.findChannels("http://www.hola.com/rss/");
            //
            // channelFinder.findChannels(
            // "http://www.madrid.org/cs/Satellite?c=Page&cid=1191581021136&language=es&mid=1191580922057&op=3&pagename=ComunidadMadrid/Estructura");
            // channelFinder.findChannels(
            // "https://www.xunta.gal/diario-oficial-galicia/mostrarContenido.do?ruta=rss.htm&titulo=dog.menu.rss&esEstatico=true&compMenu=10500&key_confirmacion=&lang=es");
            //
            // // channelFinder.findChannels("http://cincodias.com/rss/");
            // channelFinder.findChannels("http://www.expansion.com/rss/");
            //
            // channelFinder.findChannels("http://www.20minutos.es/sindicacion/");
            // channelFinder.findChannels("http://www.lavanguardia.com/rss");
            // channelFinder.findChannels("http://rss.elmundo.es/rss/");
            // //
            // channelFinder.findChannels("http://servicios.elpais.com/rss/");
            // //
            // channelFinder.findChannels("http://www.nytimes.com/services/xml/rss/index.html");
            // channelFinder.findChannels("http://www.eldiario.es/Feeds.html");
            // channelFinder.findChannels("http://www.marca.com/deporte/rss/");

        } catch (final SAXException ex) {
            ex.printStackTrace(System.err);
        } catch (final ParserConfigurationException ex) {
            ex.printStackTrace(System.err);
        } catch (final MalformedURLException ex) {
            ex.printStackTrace(System.err);
        }

        System.out.println((Calendar.getInstance().getTimeInMillis() - start) + " ms.");
    }
}
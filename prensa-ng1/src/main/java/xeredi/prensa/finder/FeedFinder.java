package xeredi.prensa.finder;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.validator.routines.UrlValidator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.mongodb.morphia.Datastore;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.rometools.rome.feed.synd.SyndEntry;
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

    /** The Constant LOG. */
    private static final Log LOG = LogFactory.getLog(FeedFinder.class);

    /** The Constant HREF_TOKEN. */
    public static final String HREF_TOKEN = "href";

    /** The Constant FIRST_LIMIT_TOKEN. */
    public static final String FIRST_LIMIT_TOKEN = "\"";

    /** The Constant SECOND_LIMIT_TOKEN. */
    public static final String SECOND_LIMIT_TOKEN = "\"";

    /** The Constant CHANNEL_FILE_EXTENSION. */
    public static final String CHANNEL_FILE_EXTENSION = "xml";

    /** The datastore. */
    private final Datastore datastore;

    /**
     * Instantiates a new feed finder.
     */
    public FeedFinder() {
        super();

        datastore = DatastoreLocator.findDatastore();
    }

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
        LOG.info("PUBLISHER: " + publisher.getName());

        final Set<String> urlProcessedSet = new HashSet<>();

        if (publisher.getChannelUrlList() != null) {

            for (final String channelUrl : publisher.getChannelUrlList()) {
                try {
                    LOG.info("Load specific channel: " + channelUrl);

                    loadFeed(publisher, channelUrl);

                    urlProcessedSet.add(channelUrl);
                } catch (final MalformedURLException ex) {
                    LOG.fatal("MalformedURLException with: " + channelUrl);
                    LOG.fatal(ex.getMessage());
                } catch (final FeedException ex) {
                    LOG.fatal("FeedException with: " + channelUrl);
                    LOG.fatal(ex.getMessage());
                } catch (final IllegalArgumentException ex) {
                    LOG.fatal("IllegalArgumentException with: " + channelUrl);
                    LOG.fatal(ex.getMessage());
                } catch (final IOException ex) {
                    LOG.fatal("IOException with: " + channelUrl);
                    LOG.fatal(ex.getMessage());
                }
            }
        }

        if (publisher.getRssHomeUrl() != null) {
            LOG.info("Scan looking for channels: " + publisher.getRssHomeUrl());

            try {
                final URL webUrl = new URL(publisher.getRssHomeUrl());
                final UrlValidator urlValidator = new UrlValidator();

                final Document document = Jsoup.parse(new URL(publisher.getRssHomeUrl()), 30000);
                final Elements links = document.select("a[href]");
                final Iterator<Element> iterator = links.iterator();

                while (iterator.hasNext()) {
                    final Element link = iterator.next();
                    final String fileUrl = link.attr("href").trim();

                    // LOG.debug("fileUrl: " + fileUrl);
                    String channelUrl = null;

                    if (fileUrl.startsWith("http") || fileUrl.startsWith("www") || fileUrl.startsWith("itpc://")) {
                        channelUrl = fileUrl;
                    } else if (fileUrl.startsWith("//")) {
                        channelUrl = webUrl.getProtocol() + ":" + fileUrl;
                    } else if (fileUrl.startsWith("/")) {
                        channelUrl = webUrl.getProtocol() + "://" + webUrl.getHost() + fileUrl;
                    } else {
                        channelUrl = webUrl.getProtocol() + "://" + webUrl.getHost()
                                + webUrl.getPath().substring(0, webUrl.getPath().lastIndexOf("/") + 1) + fileUrl;
                    }

                    if (!urlProcessedSet.contains(channelUrl)) {
                        urlProcessedSet.add(channelUrl);

                        if (urlValidator.isValid(channelUrl)) {
                            // LOG.debug("channelUrl: " + channelUrl);

                            try {
                                loadFeed(publisher, channelUrl);
                            } catch (final MalformedURLException ex) {
                            } catch (final FeedException ex) {
                            } catch (final IllegalArgumentException ex) {
                                LOG.error("IllegalArgumentException with: " + channelUrl + ", fileUrl: " + fileUrl);
                                LOG.error(ex.getMessage());
                            } catch (final IOException ex) {
                                LOG.error("IOException with: " + channelUrl + ", fileUrl: " + fileUrl);
                                LOG.error(ex.getMessage());
                            }
                        } else {
                            LOG.error("Invalid URL: " + channelUrl + ", fileUrl: " + fileUrl);
                        }
                    }
                }
            } catch (final IOException ex) {
                LOG.error("Error accediendo a la url: " + publisher.getRssHomeUrl());
                LOG.error(ex, ex);
            }
        }
    }

    /**
     * Load feed.
     *
     * @param publisher
     *            the publisher
     * @param channelUrl
     *            the channel url
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws MalformedURLException
     *             the malformed URL exception
     * @throws FeedException
     *             the feed exception
     * @throws IllegalArgumentException
     *             the illegal argument exception
     */
    private void loadFeed(final Publisher publisher, final String channelUrl)
            throws IOException, MalformedURLException, FeedException, IllegalArgumentException {
        final URL url = new URL(channelUrl);
        final HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        urlConnection.setConnectTimeout(5000);
        urlConnection.setReadTimeout(5000);

        urlConnection.connect();

        try (final InputStream is = urlConnection.getInputStream()) {
            // LOG.debug("Get: " + channelUrl);

            final SyndFeedInput input = new SyndFeedInput(true, new Locale(publisher.getRssLang()));
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

            for (final org.jdom2.Element element : syndFeed.getForeignMarkup()) {
                if ("itunes".equals(element.getNamespace().getPrefix())) {
                    feed.setPodcast(true);
                }

                // if ("image".equals(element.getName())) {
                // LOG.debug("Element: " + element.getName());
                // LOG.debug("Attributes: " + element.getAttributes());
                //
                // feed.setImUrl(element.getAttributeValue("href"));
                // }

                switch (element.getNamespace().getPrefix()) {
                case "itunes":
                    switch (element.getName()) {
                    case "image":
                        if (feed.getImUrl() == null) {
                            feed.setImUrl(element.getAttributeValue("href"));
                        }

                        break;
                    case "subtitle":
                        feed.setSubtitle(element.getText());

                        break;
                    case "summary":
                        if (feed.getDescription() == null) {
                            feed.setDescription(element.getText());
                        }

                        break;
                    case "author":
                        if (feed.getAuthor() == null) {
                            feed.setAuthor(element.getText());
                        }

                        break;
                    case "owner":
                        break;
                    case "category":
                        break;
                    case "explicit":
                        break;
                    case "new-feed-url":
                        break;
                    case "keywords":
                        break;
                    case "block":
                        break;
                    case "link":
                        break;
                    default:
                        LOG.debug("Unknown itunes Element: " + element.getName() + ", Attributes: "
                                + element.getAttributes());

                        break;
                    }

                    break;
                case "atom":
                    switch (element.getName()) {
                    case "link":
                        break;
                    default:
                        LOG.debug("Unknown atom Element: " + element.getName() + ", Attributes: "
                                + element.getAttributes());

                        break;
                    }

                    break;
                case "atom10":
                    switch (element.getName()) {
                    case "link":
                        break;
                    default:
                        LOG.debug("Unknown atom10 Element: " + element.getName() + ", Attributes: "
                                + element.getAttributes());

                        break;
                    }

                    break;
                case "feedburner":
                    switch (element.getName()) {
                    case "info":
                        break;
                    case "feedburnerHostname":
                        break;
                    case "emailServiceId":
                        break;
                    default:
                        LOG.debug("Unknown feedburner Element: " + element.getName() + ", Attributes: "
                                + element.getAttributes());

                        break;
                    }

                    break;
                case "geo":
                    switch (element.getName()) {
                    case "lat":
                        break;
                    case "long":
                        break;
                    default:
                        LOG.debug("Unknown geo Element: " + element.getName() + ", Attributes: "
                                + element.getAttributes());

                        break;
                    }

                    break;
                case "sy":
                    switch (element.getName()) {
                    case "updatePeriod":
                        break;
                    case "updateFrequency":
                        break;
                    default:
                        LOG.debug("Unknown sy Element: " + element.getName() + ", Attributes: "
                                + element.getAttributes());

                        break;
                    }

                    break;
                default:
                    LOG.debug("Unknown Prefix: " + element.getNamespace().getPrefix() + ", Name: " + element.getName()
                            + ", Attributes: " + element.getAttributes());

                    break;
                }
            }

            if (publisher.getValidType() == null || feed.getFeedType().startsWith(publisher.getValidType())) {
                final FeedDAO feedDAO = new FeedDAO(datastore);

                if (isDeprecated(syndFeed, 90)) {
                    LOG.info("Deprecated: " + channelUrl);
                } else {
                    if (feedDAO.exists(feed)) {
                        feedDAO.update(feed);
                    } else {
                        feedDAO.insert(feed);
                    }

                    LOG.info("OK: " + channelUrl);
                }
            } else {
                LOG.info("Not Valid Type: " + channelUrl);
            }
        }
    }

    /**
     * Checks if is deprecated.
     *
     * @param syndFeed
     *            the synd feed
     * @param daysNumber
     *            the days number
     * @return true, if is deprecated
     */
    private boolean isDeprecated(final SyndFeed syndFeed, final int daysNumber) {
        final Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.DAY_OF_MONTH, -daysNumber);

        for (final SyndEntry syndEntry : syndFeed.getEntries()) {
            if (syndEntry.getPublishedDate() == null && syndEntry.getUpdatedDate() == null) {
                LOG.error("Null Date: " + syndEntry.getPublishedDate());
            } else if (syndEntry.getPublishedDate() != null
                    && calendar.getTime().before(syndEntry.getPublishedDate())) {
                return false;
            } else if (syndEntry.getUpdatedDate() != null && calendar.getTime().before(syndEntry.getUpdatedDate())) {
                return false;
            }
        }

        return true;
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
            LOG.error(ex, ex);
        } catch (final ParserConfigurationException ex) {
            LOG.error(ex, ex);
        } catch (final MalformedURLException ex) {
            LOG.error(ex, ex);
        }

        LOG.info((Calendar.getInstance().getTimeInMillis() - start) + " ms.");
    }
}

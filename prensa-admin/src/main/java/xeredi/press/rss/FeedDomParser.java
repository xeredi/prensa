package xeredi.press.rss;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import com.google.inject.Guice;
import com.google.inject.Injector;

import xeredi.press.finder.FeedFinder;
import xeredi.press.model.Feed;
import xeredi.press.model.FeedCriteria;
import xeredi.press.model.Publisher;
import xeredi.press.model.PublisherCriteria;
import xeredi.press.model.service.FeedService;
import xeredi.press.model.service.PublisherService;
import xeredi.press.model.util.mybatis.PressGuiceModule;

// TODO: Auto-generated Javadoc
/**
 * The Class FeedDomParser.
 */
public final class FeedDomParser {

	/** The Constant LOG. */
	private static final Log LOG = LogFactory.getLog(FeedDomParser.class);

	/** The Constant DATE_FORMATS. */
	private static final List<String> DATE_FORMATS = Arrays.asList("EEE, dd MMM yyyy HH:mm:ss Z",
			"EEE, dd MMM yyyy HH:mm Z", "EEE,dd MMM yyyy HH:mm:ss", "yyyy-MM-dd'T'HH:mm:ss'Z'",
			"yyyy-MM-dd'T'HH:mm:ssXXX", "yyyy-MM-dd HH:mm:ss");

	/**
	 * Parses the.
	 *
	 * @param url
	 *            the url
	 * @return the feed
	 * @throws ParserConfigurationException
	 *             the parser configuration exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws SAXException
	 *             the SAX exception
	 */
	public Channel parse(final String url) throws ParserConfigurationException, IOException, SAXException {
		LOG.info("parse: " + url);

		final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		dbf.setNamespaceAware(true);

		final DocumentBuilder db = dbf.newDocumentBuilder();
		final Document doc = db.parse(new URL(url).openStream());

		final Element root = doc.getDocumentElement();

		info(root);

		if ("rss".equals(root.getLocalName())) {
			for (int i = 0; i < root.getChildNodes().getLength(); i++) {
				final Node child = root.getChildNodes().item(i);

				if ("channel".equals(child.getNodeName())) {
					final Channel channel = findChannel(child);

					LOG.debug("channel: " + channel);

					return channel;
				}
			}
		} else if ("feed".equals(root.getLocalName())) {
			final Channel channel = findChannel(root);

			LOG.debug("feed: " + channel);

			return channel;
		}

		LOG.error("RSS not found:  " + url);

		return null;
	}

	/**
	 * Find rss.
	 *
	 * @param element
	 *            the element
	 * @return the rss
	 */
	private Rss findRss(final Element element) {
		final Rss rss = new Rss();

		for (int i = 0; i < element.getChildNodes().getLength(); i++) {
			final Node child = element.getChildNodes().item(i);

			if ("channel".equals(child.getNodeName())) {
				rss.getChannelList().add(findChannel(child));
			}
		}

		return rss;
	}

	/**
	 * Find channel.
	 *
	 * @param node
	 *            the node
	 * @return the channel
	 */
	private Channel findChannel(final Node node) {
		info(node);

		final Channel channel = new Channel();

		for (int i = 0; i < node.getChildNodes().getLength(); i++) {
			final Node child = node.getChildNodes().item(i);

			switch (child.getNodeName()) {
			case "atom:link":
			case "ttl":
			case "link":
			case "copyright":
			case "rights":
			case "itunes:image":
			case "itunes:category":
			case "itunes:email":
			case "sy:updatePeriod":
			case "sy:updateFrequency":
			case "atom10:link":
			case "feedburner:info":
			case "feedburner:emailServiceId":
			case "feedburner:feedburnerHostname":
			case "feedburner:feedFlare":
			case "feedburner:browserFriendly":
			case "docs":
			case "managingEditor":
			case "id":
			case "category":
			case "atom:icon":
			case "dc:creator":
			case "hasNextPage":
			case "categorySlug":
			case "#comment":
				break;
			case "title":
				channel.setTitle(child.getTextContent());

				break;
			case "subtitle":
			case "description":
				channel.setDescription(child.getTextContent());

				break;
			case "language":
				channel.setLanguage(child.getTextContent());

				break;
			case "author":
			case "itunes:author":
				channel.setAuthor(child.getTextContent());

				break;
			case "itunes:owner":
				channel.setOwner(child.getTextContent());

				break;
			case "generator":
				channel.setGenerator(child.getTextContent());

				break;
			case "updated":
			case "lastBuildDate":
				channel.setLastBuildDate(findDate(child.getTextContent()));

				break;
			case "pubDate":
				channel.setPubDate(findDate(child.getTextContent()));

				break;
			case "logo":
				channel.setLogo(child.getTextContent());

				break;
			case "image":
				channel.setImage(findChannelImage(child));

				break;
			case "itunes:explicit":
				if (channel.getItunesChannel() == null) {
					channel.setItunesChannel(new ItunesChannel());
				}

				channel.getItunesChannel().setExplicit(child.getTextContent());

				break;
			case "itunes:type":
				if (channel.getItunesChannel() == null) {
					channel.setItunesChannel(new ItunesChannel());
				}

				channel.getItunesChannel().setType(child.getTextContent());

				break;
			case "entry":
			case "item":
				channel.getItemList().add(findItem(child));

				break;
			case "#text":
				break;
			default:
				LOG.warn("Unknown element: " + child.getNodeName() + ", value: " + child.getTextContent());

				break;
			}
		}

		return channel;
	}

	/**
	 * Find channel image.
	 *
	 * @param node
	 *            the node
	 * @return the channel image
	 */
	private ChannelImage findChannelImage(final Node node) {
		info(node);

		final ChannelImage image = new ChannelImage();

		for (int i = 0; i < node.getChildNodes().getLength(); i++) {
			final Node child = node.getChildNodes().item(i);

			switch (child.getNodeName()) {
			case "width":
			case "height":
			case "description":
			case "url":
				image.setUrl(child.getTextContent());

				break;
			case "title":
				image.setTitle(child.getTextContent());

				break;
			case "link":
				image.setLink(child.getTextContent());

				break;
			case "#text":
				break;
			default:
				LOG.warn("Unknown element: " + child.getNodeName() + ", value: " + child.getTextContent());

				break;
			}
		}

		return image;
	}

	/**
	 * Find item.
	 *
	 * @param node
	 *            the node
	 * @return the item
	 */
	private Item findItem(final Node node) {
		info(node);

		final Item item = new Item();

		for (int i = 0; i < node.getChildNodes().getLength(); i++) {
			final Node child = node.getChildNodes().item(i);

			switch (child.getNodeName()) {
			case "title":
			case "summary":
			case "atom:link":
			case "link":
			case "description":
			case "author":
			case "guid":
			case "comments":
			case "content:encoded":
			case "content":
			case "dc:creator":
			case "dc:publisher":
			case "itunes:season":
			case "itunes:episode":
			case "category":
			case "slash:comments":
			case "wfw:commentRss":
			case "feedburner:origLink":
			case "#comment":
			case "articleSlug":
			case "categorySlug":
			case "source_id":
			case "id":
			case "dc:rightsHolder":
			case "dc:identifier":
			case "ingested":
			case "itunes:author":
			case "ss:slideshow":
			case "media:description":
			case "media:credit":
			case "media:group": // FIXME Video
				break;
			case "itunes:duration":
				if (item.getItunesItem() == null) {
					item.setItunesItem(new ItunesItem());
				}

				item.getItunesItem().setDuration(child.getTextContent());

				break;
			case "itunes:episodeType":
				if (item.getItunesItem() == null) {
					item.setItunesItem(new ItunesItem());
				}

				item.getItunesItem().setEpisodeType(child.getTextContent());

				break;
			case "dc:date":
			case "pubDate":
			case "published":
			case "mi:dateTimeWritten":
				item.setPubDate(findDate(child.getTextContent()));

				break;
			case "modified":
			case "dc:modified":
			case "updated":
				item.setModified(findDate(child.getTextContent()));

				break;
			case "enclosure":
				item.getEnclosureList().add(findEnclosure(child));

				break;
			case "media:title":
			case "media:thumbnail":
			case "media:content":
			case "media:keywords":
				break;
			case "#text":
				break;
			default:
				LOG.warn("Unknown element: " + child.getNodeName() + ", value: " + child.getTextContent());

				break;
			}
		}

		return item;
	}

	/**
	 * Find date.
	 *
	 * @param value
	 *            the value
	 * @return the date
	 */
	private Date findDate(final String value) {
		if (value == null || value.trim().isEmpty()) {
			return null;
		}

		for (final String dateFormatString : DATE_FORMATS) {
			try {
				return new SimpleDateFormat(dateFormatString).parse(value.trim());
			} catch (ParseException ex) {
			}
		}

		LOG.warn("Error getting date: " + value);

		return null;
	}

	private String findSelfLink(final Node node) {
		info(node);

		final Enclosure enclosure = new Enclosure();

		for (int i = 0; i < node.getAttributes().getLength(); i++) {
			final Node child = node.getAttributes().item(i);

			switch (child.getNodeName()) {
			case "href":
				return child.getTextContent();
			case "rel":
				break;
			case "type":
				break;
			default:
				LOG.warn("Unknown element: " + child.getNodeName() + ", value: " + child.getTextContent());

				break;
			}
		}

		for (int i = 0; i < node.getChildNodes().getLength(); i++) {
			final Node child = node.getChildNodes().item(i);

			switch (child.getNodeName()) {
			case "#text":
				break;
			default:
				LOG.warn("Unknown element: " + child.getNodeName() + ", value: " + child.getTextContent());

				break;
			}
		}

		return null;
	}

	/**
	 * Find enclosure.
	 *
	 * @param node
	 *            the node
	 * @return the enclosure
	 */
	private Enclosure findEnclosure(final Node node) {
		info(node);

		final Enclosure enclosure = new Enclosure();

		for (int i = 0; i < node.getAttributes().getLength(); i++) {
			final Node child = node.getAttributes().item(i);

			switch (child.getNodeName()) {
			case "length":
				enclosure.setLength(child.getTextContent());

				break;
			case "type":
				enclosure.setType(child.getTextContent());

				break;
			case "url":
				enclosure.setUrl(child.getTextContent());

				break;
			case "#text":
				break;
			default:
				LOG.warn("Unknown element: " + child.getNodeName() + ", value: " + child.getTextContent());

				break;
			}
		}

		for (int i = 0; i < node.getChildNodes().getLength(); i++) {
			final Node child = node.getChildNodes().item(i);

			switch (child.getNodeName()) {
			case "#text":
				break;
			default:
				LOG.warn("Unknown element: " + child.getNodeName() + ", value: " + child.getTextContent());

				break;
			}
		}

		return enclosure;
	}

	/**
	 * Info.
	 *
	 * @param element
	 *            the element
	 */
	private void info(final Element element) {
		LOG.debug("name: " + element.getLocalName());

		for (int i = 0; i < element.getChildNodes().getLength(); i++) {
			final Node child = element.getChildNodes().item(i);

			if (!"#text".equals(child.getNodeName())) {
				LOG.debug("node: " + child.getNodeName());
			}
		}
	}

	/**
	 * Info.
	 *
	 * @param node
	 *            the node
	 */
	private void info(final Node node) {
		LOG.debug("name: " + node.getLocalName());

		for (int i = 0; i < node.getChildNodes().getLength(); i++) {
			final Node child = node.getChildNodes().item(i);

			if (!"#text".equals(child.getNodeName())) {
				LOG.debug("node: " + child.getNodeName());
			}
		}
	}

	/**
	 * The main method.
	 *
	 * @param argv
	 *            the arguments
	 */
	public static void main(String argv[]) {

		final Injector injector = Guice.createInjector(new PressGuiceModule());
		final PublisherService pblrService = injector.getInstance(PublisherService.class);
		final FeedService feedService = injector.getInstance(FeedService.class);

		final Calendar calendar = Calendar.getInstance();

		calendar.add(Calendar.MONTH, -1);

		final Date minDate = calendar.getTime();

		final FeedDomParser feedParser = new FeedDomParser();

		for (final Feed feed : feedService.selectList(new FeedCriteria())) {
			try {
				feedParser.parse(feed.getUrl());

				Thread.sleep(100);
			} catch (Exception e) {
				LOG.error(e, e);
			}
		}

		// feedParser.parse("http://www.publico.es/");
		// feedParser.parse("https://www.eldiario.es/rss/cultura/");
		// feedParser.parse("https://www.muyhistoria.es/rss");
	}

}

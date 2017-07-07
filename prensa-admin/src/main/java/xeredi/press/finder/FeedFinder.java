package xeredi.press.finder;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
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
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;

import xeredi.press.model.Feed;
import xeredi.press.model.Publisher;
import xeredi.press.model.PublisherCriteria;
import xeredi.press.model.service.FeedService;
import xeredi.press.model.service.PublisherService;
import xeredi.press.model.util.mybatis.PressGuiceModule;

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

	/**
	 * Instantiates a new feed finder.
	 */
	public FeedFinder() {
		super();
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
	public List<Feed> findFeeds(final Publisher publisher)
			throws SAXException, ParserConfigurationException, MalformedURLException {
		LOG.info("PUBLISHER: " + publisher.getName());

		final Set<String> urlProcessedSet = new HashSet<>();
		final List<Feed> feeds = new ArrayList<>();

		// if (publisher.getChannelUrlList() != null) {
		//
		// for (final String channelUrl : publisher.getChannelUrlList()) {
		// try {
		// LOG.info("Load specific channel: " + channelUrl);
		//
		// loadFeed(publisher, channelUrl);
		//
		// urlProcessedSet.add(channelUrl);
		// } catch (final MalformedURLException ex) {
		// LOG.fatal("MalformedURLException with: " + channelUrl);
		// LOG.fatal(ex.getMessage());
		// } catch (final FeedException ex) {
		// LOG.fatal("FeedException with: " + channelUrl);
		// LOG.fatal(ex.getMessage());
		// } catch (final IllegalArgumentException ex) {
		// LOG.fatal("IllegalArgumentException with: " + channelUrl);
		// LOG.fatal(ex.getMessage());
		// } catch (final IOException ex) {
		// LOG.fatal("IOException with: " + channelUrl);
		// LOG.fatal(ex.getMessage());
		// }
		// }
		// }

		switch (publisher.getWebType()) {
		case "html":
			LOG.info("Scan looking for channels: " + publisher.getWebUrl());

			try {
				final URL webUrl = new URL(publisher.getWebUrl());
				final UrlValidator urlValidator = new UrlValidator();

				final Document document = Jsoup.parse(new URL(publisher.getWebUrl()), 30000);
				final Elements links = document.select("a[href]");
				final Iterator<Element> iterator = links.iterator();

				while (iterator.hasNext()) {
					final Element link = iterator.next();
					final String fileUrl = link.attr("href").trim();

					LOG.debug("fileUrl: " + fileUrl);

					if (!fileUrl.startsWith("javascript:") && !fileUrl.startsWith("itpc://")) {
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
									final Feed feed = findFeed(publisher, channelUrl);

									feeds.add(feed);
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
				}
			} catch (final IOException ex) {
				LOG.error("Error accediendo a la url: " + publisher.getWebUrl());
				LOG.error(ex, ex);
			}

			break;
		default:
			try {
				final Feed feed = findFeed(publisher, publisher.getWebUrl());

				feeds.add(feed);
			} catch (final MalformedURLException ex) {
				LOG.error("MalformedURLException with: " + publisher.getWebUrl());
				LOG.error(ex.getMessage());
			} catch (final FeedException ex) {
				LOG.error("FeedException with: " + publisher.getWebUrl());
				LOG.error(ex.getMessage());
			} catch (final IllegalArgumentException ex) {
				LOG.error("IllegalArgumentException with: " + publisher.getWebUrl());
				LOG.error(ex.getMessage());
			} catch (final IOException ex) {
				LOG.error("IOException with: " + publisher.getWebUrl());
				LOG.error(ex.getMessage());
			}

			break;
		}

		return feeds;
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
	private Feed findFeed(final Publisher publisher, final String channelUrl)
			throws IOException, MalformedURLException, FeedException, IllegalArgumentException {
		final URL url = new URL(channelUrl);
		final HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

		urlConnection.setConnectTimeout(5000);
		urlConnection.setReadTimeout(5000);

		urlConnection.connect();

		try (final InputStream is = urlConnection.getInputStream()) {
			// LOG.debug("Get: " + channelUrl);

			final SyndFeedInput input = new SyndFeedInput(true, new Locale(publisher.getLanguage()));
			final InputSource source = new InputSource(is);
			final SyndFeed syndFeed = input.build(source);

			final Feed feed = new Feed();

			feed.setPblrId(publisher.getId());
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

			return feed;
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
		System.out.println("Start test");

		final FeedFinder feedFinder = new FeedFinder();
		final Injector injector = Guice.createInjector(new PressGuiceModule());
		final PublisherService pblrService = injector.getInstance(PublisherService.class);
		final FeedService feedService = injector.getInstance(FeedService.class);

		for (final Publisher pblr : pblrService.selectList(new PublisherCriteria())) {
			System.out.println(pblr);

			try {
				for (final Feed feed : feedFinder.findFeeds(pblr)) {
					System.out.println(feed);

					feed.setImUrl(pblr.getLogoUrl());

					if (feedService.exists(feed)) {
						feedService.update(feed);
					} else {
						feedService.insert(feed);
					}
				}
			} catch (final Exception ex) {
				ex.printStackTrace(System.err);
			}
		}

		System.out.println("End test");
	}
}

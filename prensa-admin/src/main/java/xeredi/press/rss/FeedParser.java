package xeredi.press.rss;

import java.io.IOException;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.SAXException;

// TODO: Auto-generated Javadoc
/**
 * The Class FeedParser.
 */
public final class FeedParser {
	/** The Constant LOG. */
	private static final Log LOG = LogFactory.getLog(FeedParser.class);

	/**
	 * Parses the.
	 *
	 * @param url
	 *            the url
	 * @return the rss
	 * @throws SAXException
	 *             the SAX exception
	 * @throws ParserConfigurationException
	 *             the parser configuration exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public Rss parse(final String url) throws SAXException, ParserConfigurationException, IOException {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();

		/** The handler. */
		RssHandler handler = new RssHandler();

		// saxParser.parse(new URL("http://www.publico.es/").openStream(), handler);
		saxParser.parse(new URL(url).openStream(), handler);

		return handler.getRss();
	}

	/**
	 * The main method.
	 *
	 * @param argv
	 *            the arguments
	 */
	public static void main(String argv[]) {
		try {
			final FeedParser feedParser = new FeedParser();

			// saxParser.parse(new URL("http://www.publico.es/").openStream(), handler);
			feedParser.parse("https://www.eldiario.es/rss/cultura");
			// feedParser.parse("https://www.muyhistoria.es/rss");
		} catch (Exception e) {
			LOG.error(e, e);
		}
	}
}

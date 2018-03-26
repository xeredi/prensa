package xeredi.press.finder;

import java.io.FileInputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import xeredi.press.model.Publisher;

// TODO: Auto-generated Javadoc
/**
 * The Class ChannelTest.
 */
public final class ChannelTest {

	/** The Constant LOG. */
	private static final Log LOG = LogFactory.getLog(ChannelTest.class);

	/**
	 * Test channel.
	 */
	@Test
	public void testChannel() {
		final FeedFinder feedFinder = new FeedFinder();
		final Publisher publisher = new Publisher();

		publisher.setLanguage("es");

		try {
			LOG.info("feed: " + feedFinder.findFeed(publisher, "https://www.eldiario.es/rss/cultura"));
			LOG.info("feed: " + feedFinder.findFeed(publisher, "https://www.muyhistoria.es/rss"));
		} catch (final Exception ex) {
			LOG.error(ex, ex);
		}
	}

}

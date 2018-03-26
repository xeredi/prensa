package xeredi.press.rss;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

// TODO: Auto-generated Javadoc
/**
 * The Class RssHandler.
 */
public final class RssHandler extends DefaultHandler {
	/** The Constant LOG. */
	private static final Log LOG = LogFactory.getLog(RssHandler.class);

	private String elementName;

	/** The rss. */
	Rss rss = null;
	Channel channel = null;

	/**
	 * Gets the rss.
	 *
	 * @return the rss
	 */
	public Rss getRss() {
		return rss;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void startDocument() throws SAXException {
		LOG.info("startDocument");

		rss = new Rss();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void endDocument() throws SAXException {
		LOG.info("endDocument");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		this.elementName = qName;

		LOG.info("startElement: " + qName);
/*
		final RssElement rssElement = RssElement.valueOf(qName);
		switch (rssElement) {
		case rss:
			rss = new Rss();

			break;
		case channel:
			channel = new Channel();

			break;
		default:
			break;
		}
*/
		
		/*
		 * LOG.info("startElement: " + qName);
		 * 
		 * if (attributes != null) { attributes.getLength();
		 * 
		 * for (int i = 0; i < attributes.getLength(); i++) { LOG.info("attributes: " +
		 * i + ", " + attributes.getLocalName(i) + ", " + attributes.getValue(i)); } }
		 */
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		LOG.info("endElement: " + qName);
/*
		final RssElement rssElement = RssElement.valueOf(qName);
		switch (rssElement) {
		case channel:
			rss.getChannelList().add(channel);

			channel = new Channel();

			break;
		default:
			break;
		}
*/
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		final String characters = new String(ch, start, length).trim();

		if (!characters.isEmpty()) {
			LOG.info("characters: " + characters);
/*
			final RssElement rssElement = RssElement.valueOf(elementName);
			switch (rssElement) {
			case title:
				channel.setTitle(characters);

				break;
			case link:
				channel.setLink(characters);

				break;
			case description:
				channel.setDescription(characters);

				break;
			case language:
				channel.setLanguage(characters);

				break;
			case copyright:
				channel.setCopyright(characters);

				break;
			case ttl:
				channel.setTtl(characters);

				break;
			default:
				break;
			}
*/
		}
	}

}

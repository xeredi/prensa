package xeredi.prensa.csv;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import xeredi.prensa.model.Cathegory;
import xeredi.prensa.model.Channel;
import xeredi.prensa.model.Publisher;

// TODO: Auto-generated Javadoc
/**
 * The Class ChannelSaxUtil.
 */
public final class ChannelDomUtil {

	/**
	 * Find publisher dependencies.
	 *
	 * @param publisherList
	 *            the publisher list
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws ParserConfigurationException
	 *             the parser configuration exception
	 * @throws SAXException
	 *             the SAX exception
	 */
	public static final void findPublisherDependencies(final Collection<Publisher> publisherList)
			throws IOException, ParserConfigurationException, SAXException {
		for (final Publisher publisher : publisherList) {
			if (publisher.getDefaultChannel() != null) {
				findChannelDependencies(publisher.getDefaultChannel());
			}

			for (final Cathegory cathegory : publisher.getCathegoriesMap().values()) {
				for (final Channel channel : cathegory.getChannelsMap().values()) {
					findChannelDependencies(channel);
				}
			}
		}
	}

	/**
	 * Find channel dependencies.
	 *
	 * @param channel
	 *            the channel
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws ParserConfigurationException
	 *             the parser configuration exception
	 * @throws SAXException
	 *             the SAX exception
	 */
	public static final void findChannelDependencies(final Channel channel)
			throws IOException, ParserConfigurationException, SAXException {
		System.out.println("Loading dependencies from: " + channel.getUrl());

		final File file = new File("/home/xeredi/git/prensa/prensa-ng1/src/main/webapp/" + channel.getUrl());
		final DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		final Document doc = dBuilder.parse(file);

		final Element root = doc.getDocumentElement();

		if (!root.hasChildNodes()) {
			throw new Error("No Channels");
		}

		for (int i = 0; i < root.getChildNodes().getLength(); i++) {
			final Node node = root.getChildNodes().item(i);

			if ("channel".equals(node.getNodeName())) {
				for (int j = 0; j < node.getChildNodes().getLength(); j++) {
					final Node channelChild = node.getChildNodes().item(j);
					final String childName = channelChild.getNodeName();
					final String childText = channelChild.getTextContent();
					final short childType = channelChild.getNodeType();

					switch (childName) {
					case "feedpress:locale":
					case "language":
						System.out.println("node: " + childName + " - value: " + childText + " - type: " + childType);

						channel.setLanguage(childText);

						break;
					case "title":
						System.out.println("node: " + childName + " - value: " + childText + " - type: " + childType);

						channel.setName(childText);

						break;
					case "description":
						System.out.println("node: " + childName + " - value: " + childText + " - type: " + childType);

						channel.setDescription(childText);

						break;
					case "ttl":
						System.out.println("node: " + childName + " - value: " + childText + " - type: " + childType);

						channel.setTtl(childText);

						break;
					case "image":
						System.out.println("node: " + childName + " - value: " + childText + " - type: " + childType);

						break;
					case "item":
					case "#text":
					case "atom:link":
					case "link":
					case "copyright":
					case "category":
					case "pubDate":
					case "lastBuildDate":
						// No Action

						break;
					default:
						System.out.println(
								"Unknown node: " + childName + " - value: " + childText + " - type: " + childType);

						break;
					}
				}
			}
		}
	}
}

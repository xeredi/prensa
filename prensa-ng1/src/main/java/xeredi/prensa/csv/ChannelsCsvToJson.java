package xeredi.prensa.csv;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import xeredi.prensa.model.Cathegory;
import xeredi.prensa.model.Channel;
import xeredi.prensa.model.Publisher;

// TODO: Auto-generated Javadoc
/**
 * The Class ChannelsCsvToJson.
 */
public class ChannelsCsvToJson {

	/**
	 * Load csv channels.
	 *
	 * @param csvUrl
	 *            the csv url
	 * @return the collection
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private final Collection<Publisher> loadCsvChannels(final String csvUrl) throws IOException {
		final Map<String, Publisher> publisherMap = new HashMap<>();

		try (final FileInputStream is = new FileInputStream(csvUrl)) {
			final List<String> lines = IOUtils.readLines(is, "UTF-8");

			for (final String line : lines) {
				final String[] tokens = line.split(",");

				int i = 0;

				final String language = tokens[i++];
				final String countryCode = tokens[i++];
				final String publisherName = tokens[i++];
				final String cathegoryName = tokens[i++];
				final String channelUrl = tokens[i++];

				if (!publisherMap.containsKey(publisherName)) {
					final Publisher publisher = new Publisher();

					publisher.setCountryCode(countryCode);
					publisher.setName(publisherName);

					publisherMap.put(publisherName, publisher);
				}

				final Publisher publisher = publisherMap.get(publisherName);

				if (cathegoryName == null || cathegoryName.isEmpty()) {
					final Channel channel = new Channel();

					channel.setUrl(channelUrl);

					publisher.setDefaultChannel(channel);
				} else {
					if (!publisher.getCathegoriesMap().containsKey(cathegoryName)) {
						final Cathegory cathegory = new Cathegory();

						cathegory.setName(cathegoryName);

						publisher.getCathegoriesMap().put(cathegoryName, cathegory);
						publisher.getCathegoriesList().add(cathegoryName);
					}

					final Cathegory cathegory = publisher.getCathegoriesMap().get(cathegoryName);

					if (!cathegory.getChannelsMap().containsKey(channelUrl)) {
						final Channel channel = new Channel();

						channel.setUrl(channelUrl);

						cathegory.getChannelsList().add(channelUrl);
						cathegory.getChannelsMap().put(channelUrl, channel);
					}
				}
			}
		}

		return publisherMap.values();
	}

	/**
	 * Save json channels.
	 *
	 * @param url
	 *            the url
	 * @param publisherList
	 *            the publisher list
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private void saveJsonChannels(final String url, final Collection<Publisher> publisherList) throws IOException {
		final StringBuffer buffer = new StringBuffer();

		buffer.append("[");

		final Iterator<Publisher> publisherIterator = publisherList.iterator();

		while (publisherIterator.hasNext()) {
			final Publisher publisher = publisherIterator.next();

			buffer.append("{");

			buffer.append(generateJsonFieldname("name")).append(generateJsonValue(publisher.getName()));
			buffer.append(",").append(generateJsonFieldname("countryCode"))
					.append(generateJsonValue(publisher.getCountryCode()));

			if (publisher.getDefaultChannel() != null) {
				final Channel channel = publisher.getDefaultChannel();

				buffer.append(",").append(generateJsonFieldname("defaultChannel")).append("{");
				buffer.append(generateJsonFieldname("url")).append(generateJsonValue(channel.getUrl()));
				buffer.append(",").append(generateJsonFieldname("language"))
						.append(generateJsonValue(channel.getLanguage()));
				buffer.append(",").append(generateJsonFieldname("name")).append(generateJsonValue(channel.getName()));
				buffer.append("}");
			}

			if (!publisher.getCathegoriesList().isEmpty()) {
				buffer.append(",").append(generateJsonFieldname("cathegories")).append("[");

				final Iterator<String> cathegoriesIterator = publisher.getCathegoriesList().iterator();

				while (cathegoriesIterator.hasNext()) {
					final Cathegory cathegory = publisher.getCathegoriesMap().get(cathegoriesIterator.next());

					buffer.append("{");

					buffer.append(generateJsonFieldname("name")).append(generateJsonValue(cathegory.getName()));

					if (!cathegory.getChannelsList().isEmpty()) {
						buffer.append(",").append(generateJsonFieldname("channels")).append("[");

						final Iterator<String> channelsIterator = cathegory.getChannelsList().iterator();

						while (channelsIterator.hasNext()) {
							final Channel channel = cathegory.getChannelsMap().get(channelsIterator.next());

							buffer.append("{");

							buffer.append(generateJsonFieldname("url")).append(generateJsonValue(channel.getUrl()));
							buffer.append(",").append(generateJsonFieldname("language"))
									.append(generateJsonValue(channel.getLanguage()));
							buffer.append(",").append(generateJsonFieldname("name"))
									.append(generateJsonValue(channel.getName()));

							buffer.append("}");

							if (channelsIterator.hasNext()) {
								buffer.append(",");
							}
						}

						buffer.append("]");
					}

					buffer.append("}");

					if (cathegoriesIterator.hasNext()) {
						buffer.append(",");
					}
				}

				buffer.append("]");
			}

			buffer.append("}");

			if (publisherIterator.hasNext()) {
				buffer.append(",");
			}
		}

		buffer.append("]");

		try (final FileOutputStream fos = new FileOutputStream(url)) {
			fos.write(buffer.toString().getBytes());
		}

		System.out.println(buffer);
	}

	/**
	 * Generate json value.
	 *
	 * @param value
	 *            the value
	 * @return the string
	 */
	private String generateJsonValue(final Object value) {
		return "\"" + (value == null ? "" : String.valueOf(value)) + "\"";
	}

	private String generateJsonFieldname(final String fieldname) {
		return "\"" + String.valueOf(fieldname) + "\":";
	}

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(final String[] args) {
		try {
			final ChannelsCsvToJson channelsCsvToJson = new ChannelsCsvToJson();
			final Collection<Publisher> publisherList = channelsCsvToJson
					.loadCsvChannels("/home/xeredi/git/prensa/prensa-ng1/src/main/webapp/conf/channels.csv");

			channelsCsvToJson.saveJsonChannels("/home/xeredi/git/prensa/prensa-ng1/src/main/webapp/conf/channels.json",
					publisherList);

			System.out.println(publisherList);
		} catch (final IOException ex) {
			ex.printStackTrace(System.err);
		}
	}
}

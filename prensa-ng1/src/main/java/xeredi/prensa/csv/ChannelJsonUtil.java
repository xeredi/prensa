package xeredi.prensa.csv;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import xeredi.prensa.model.Cathegory;
import xeredi.prensa.model.Channel;
import xeredi.prensa.model.Publisher;

// TODO: Auto-generated Javadoc
/**
 * The Class ChannelJsonUtil.
 */
public final class ChannelJsonUtil {
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
	public static final void saveJsonChannels(final String url, final Collection<Publisher> publisherList)
			throws IOException {
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
				buffer.append(",").append(generateJsonFieldname("description"))
						.append(generateJsonValue(channel.getDescription()));
				buffer.append(",").append(generateJsonFieldname("ttl")).append(generateJsonValue(channel.getTtl()));
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
							buffer.append(",").append(generateJsonFieldname("description"))
									.append(generateJsonValue(channel.getDescription()));
							buffer.append(",").append(generateJsonFieldname("ttl"))
									.append(generateJsonValue(channel.getTtl()));

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
	private static final String generateJsonValue(final Object value) {
		return "\"" + (value == null ? "" : String.valueOf(value)) + "\"";
	}

	/**
	 * Generate json fieldname.
	 *
	 * @param fieldname
	 *            the fieldname
	 * @return the string
	 */
	private static final String generateJsonFieldname(final String fieldname) {
		return "\"" + String.valueOf(fieldname) + "\":";
	}

}

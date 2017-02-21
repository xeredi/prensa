package xeredi.prensa.csv;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import xeredi.prensa.model.Cathegory;
import xeredi.prensa.model.Channel;
import xeredi.prensa.model.Publisher;

// TODO: Auto-generated Javadoc
/**
 * The Class ChannelCsvUtil.
 */
public final class ChannelCsvUtil {
	/**
	 * Load csv channels.
	 *
	 * @param csvUrl
	 *            the csv url
	 * @return the collection
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static final Collection<Publisher> loadCsvChannels(final String csvUrl) throws IOException {
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

}

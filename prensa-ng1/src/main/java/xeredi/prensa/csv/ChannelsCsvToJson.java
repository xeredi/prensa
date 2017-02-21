package xeredi.prensa.csv;

import java.util.Collection;

import xeredi.prensa.model.Publisher;

// TODO: Auto-generated Javadoc
/**
 * The Class ChannelsCsvToJson.
 */
public class ChannelsCsvToJson {

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(final String[] args) {
		try {
			final Collection<Publisher> publisherList = ChannelCsvUtil
					.loadCsvChannels("/home/xeredi/git/prensa/prensa-ng1/src/main/webapp/conf/channels.csv");

			ChannelDomUtil.findPublisherDependencies(publisherList);

			ChannelJsonUtil.saveJsonChannels("/home/xeredi/git/prensa/prensa-ng1/src/main/webapp/conf/channels.json",
					publisherList);

			System.out.println(publisherList);
		} catch (final Exception ex) {
			ex.printStackTrace(System.err);
		}
	}
}

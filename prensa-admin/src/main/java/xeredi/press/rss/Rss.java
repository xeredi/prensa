package xeredi.press.rss;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

// TODO: Auto-generated Javadoc
/**
 * The Class Rss.
 */
public final class Rss {

	/** The channel list. */
	final List<Channel> channelList = new ArrayList<>();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * Gets the channel list.
	 *
	 * @return the channel list
	 */
	public List<Channel> getChannelList() {
		return channelList;
	}
}

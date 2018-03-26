package xeredi.press.rss;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import lombok.Data;

// TODO: Auto-generated Javadoc
/**
 * The Class Channel.
 */

/**
 * Instantiates a new channel.
 */
@Data
public final class Channel {

	/** The title. */
	private String title;

	/** The description. */
	private String description;

	/** The language. */
	private String language;

	/** The author. */
	private String author;

	/** The generator. */
	private String generator;

	private String owner;

	private String logo;

	/** The last build date. */
	private Date lastBuildDate;

	private Date pubDate;

	/** The image. */
	private ChannelImage image;

	/** The itunes channel. */
	private ItunesChannel itunesChannel;

	/** The item list. */
	private final List<Item> itemList = new ArrayList<>();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}

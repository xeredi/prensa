package xeredi.press.rss;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;

// TODO: Auto-generated Javadoc
/**
 * The Class Item.
 */
@Data
public final class Item {

	/** The pub date. */
	private Date pubDate;

	private Date modified;

	/** The itunes item. */
	private ItunesItem itunesItem;

	/** The enclosure list. */
	private final List<Enclosure> enclosureList = new ArrayList<>();
}

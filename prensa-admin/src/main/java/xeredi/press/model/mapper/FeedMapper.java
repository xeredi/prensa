package xeredi.press.model.mapper;

import xeredi.press.model.Feed;

// TODO: Auto-generated Javadoc
/**
 * The Interface FeedMapper.
 */
public interface FeedMapper {

	/**
	 * Exists.
	 *
	 * @param feed
	 *            the feed
	 * @return true, if successful
	 */
	boolean exists(final Feed feed);

	/**
	 * Insert.
	 *
	 * @param feed
	 *            the feed
	 */
	void insert(final Feed feed);

	/**
	 * Update.
	 *
	 * @param feed
	 *            the feed
	 * @return the int
	 */
	int update(final Feed feed);
}

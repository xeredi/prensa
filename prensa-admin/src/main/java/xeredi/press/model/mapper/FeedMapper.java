package xeredi.press.model.mapper;

import java.util.List;

import xeredi.press.model.Feed;
import xeredi.press.model.FeedCriteria;

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

	/**
	 * Select list.
	 *
	 * @param criteria
	 *            the criteria
	 * @return the list
	 */
	List<Feed> selectList(final FeedCriteria criteria);
}

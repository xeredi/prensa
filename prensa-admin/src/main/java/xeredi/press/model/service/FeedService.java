package xeredi.press.model.service;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.ExecutorType;
import org.mybatis.guice.transactional.Transactional;

import xeredi.press.model.Feed;
import xeredi.press.model.FeedCriteria;
import xeredi.press.model.mapper.FeedMapper;

// TODO: Auto-generated Javadoc
/**
 * The Class FeedService.
 */
@Transactional(executorType = ExecutorType.REUSE)
public class FeedService {

	/** The feed mapper. */
	@Inject
	private FeedMapper feedMapper;

	/**
	 * Exists.
	 *
	 * @param feed
	 *            the feed
	 * @return true, if successful
	 */
	public boolean exists(final Feed feed) {
		return feedMapper.exists(feed);
	}

	/**
	 * Insert.
	 *
	 * @param feed
	 *            the feed
	 */
	public void insert(final Feed feed) {
		feedMapper.insert(feed);
	}

	/**
	 * Update.
	 *
	 * @param feed
	 *            the feed
	 * @return the int
	 */
	public int update(final Feed feed) {
		return feedMapper.update(feed);
	}

	/**
	 * Select list.
	 *
	 * @param criteria
	 *            the criteria
	 * @return the list
	 */
	public List<Feed> selectList(final FeedCriteria criteria) {
		return feedMapper.selectList(criteria);
	}

}

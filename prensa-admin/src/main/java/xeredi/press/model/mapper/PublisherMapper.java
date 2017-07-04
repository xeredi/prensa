package xeredi.press.model.mapper;

import java.util.List;

import xeredi.press.model.Publisher;
import xeredi.press.model.PublisherCriteria;

// TODO: Auto-generated Javadoc
/**
 * The Interface PublisherMapper.
 */
public interface PublisherMapper {

	/**
	 * Select list.
	 *
	 * @param criteria the criteria
	 * @return the list
	 */
	List<Publisher> selectList(final PublisherCriteria criteria);
}

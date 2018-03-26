package xeredi.press.model.service;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.ExecutorType;
import org.mybatis.guice.transactional.Transactional;

import xeredi.press.model.Category;
import xeredi.press.model.mapper.CategoryMapper;

// TODO: Auto-generated Javadoc
/**
 * The Class CategoryService.
 */
@Transactional(executorType = ExecutorType.REUSE)
public class CategoryService {

	/** The feed mapper. */
	@Inject
	private CategoryMapper ctgrMapper;

	/**
	 * Select list.
	 *
	 * @return the list
	 */
	public List<Category> selectList() {
		return ctgrMapper.selectList();
	}
}

package xeredi.press.model.service;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.ExecutorType;
import org.mybatis.guice.transactional.Transactional;

import com.google.inject.Guice;
import com.google.inject.Injector;

import xeredi.press.model.Publisher;
import xeredi.press.model.PublisherCriteria;
import xeredi.press.model.mapper.PublisherMapper;
import xeredi.press.model.util.mybatis.PressGuiceModule;

// TODO: Auto-generated Javadoc
/**
 * The Class PublisherService.
 */
@Transactional(executorType = ExecutorType.REUSE)
public class PublisherService {

	/** The pblr mapper. */
	@Inject
	private PublisherMapper pblrMapper;

	/**
	 * Select list.
	 *
	 * @return the list
	 */
	public List<Publisher> selectList() {
		final PublisherCriteria pblrCriteria = new PublisherCriteria();

		return pblrMapper.selectList(pblrCriteria);
	}

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(final String[] args) {
		System.out.println("Start test");

		final Injector injector = Guice.createInjector(new PressGuiceModule());
		final PublisherService pblrService = injector.getInstance(PublisherService.class);

		for (final Publisher pblr : pblrService.selectList()) {
			System.out.println(pblr);
		}

		System.out.println("End test");
	}

}

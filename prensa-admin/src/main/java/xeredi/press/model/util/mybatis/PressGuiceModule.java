package xeredi.press.model.util.mybatis;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.guice.XMLMyBatisModule;

// TODO: Auto-generated Javadoc
/**
 * The Class ArgoGuiceModule.
 */
public final class PressGuiceModule extends XMLMyBatisModule {

	/** The Constant LOG. */
	private static final Log LOG = LogFactory.getLog(PressGuiceModule.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void initialize() {
		LOG.info("Initialising PressGuiceModule");

		setClassPathResource("mybatis-config.xml");
	}
}

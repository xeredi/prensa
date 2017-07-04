package xeredi.press.model.util.mybatis;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.datasource.pooled.PooledDataSourceFactory;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating DbcpDataSource objects.
 */
public class DbcpDataSourceFactory extends PooledDataSourceFactory {
	private static final Log LOG = LogFactory.getLog(DbcpDataSourceFactory.class);

	/**
	 * Instantiates a new dbcp data source factory.
	 */
	public DbcpDataSourceFactory() {
		LOG.info("Init DataSource");

		this.dataSource = new BasicDataSource();
	}
}

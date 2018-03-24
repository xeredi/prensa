package xeredi.press.exporter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import com.google.inject.Guice;
import com.google.inject.Injector;

import xeredi.press.model.Feed;
import xeredi.press.model.FeedCriteria;
import xeredi.press.model.service.FeedService;
import xeredi.press.model.util.mybatis.PressGuiceModule;

// TODO: Auto-generated Javadoc
/**
 * The Class FeedExporter.
 */
public final class FeedExporter extends JsonExporter {

	/**
	 * Export feeds.
	 */
	public void exportFeeds() throws IOException {
		final Injector injector = Guice.createInjector(new PressGuiceModule());
		final FeedService feedService = injector.getInstance(FeedService.class);

		try (final PrintWriter writer = new PrintWriter("/home/xeredi/git/ionic/prensa/src/assets/json/feeds.json")) {
			writer.println("{\"data\": [");

			final Iterator<Feed> iterator = feedService.selectList(new FeedCriteria()).iterator();

			while (iterator.hasNext()) {
				final Feed feed = iterator.next();

				final StringBuilder line = new StringBuilder();

				line.append("{");

				// line.append(getFieldName("id")).append(getNumber(feed.getId()));
				line/*.append(",")*/.append(getFieldName("publicador")).append(getString(feed.getPblr().getWebUrl()));
				if (feed.getAuthor() != null) {
					line.append(",").append(getFieldName("autor")).append(getString(feed.getAuthor()));
				}
				if (feed.getCopyright() != null) {
					line.append(",").append(getFieldName("copyright")).append(getString(feed.getCopyright()));
				}
				if (feed.getDescription() != null) {
					// line.append(",").append(getFieldName("descripcion")).append(getString(feed.getDescription()));
				}
				if (feed.getEncoding() != null) {
					line.append(",").append(getFieldName("encoding")).append(getString(feed.getEncoding()));
				}
				if (feed.getImUrl() != null) {
					line.append(",").append(getFieldName("icon")).append(getString(feed.getImUrl()));
				}
				if (feed.getLanguage() != null) {
					line.append(",").append(getFieldName("idioma")).append(getString(feed.getLanguage()));
				}
				if (feed.getLink() != null) {
					line.append(",").append(getFieldName("link")).append(getString(feed.getLink()));
				}
				if (feed.getSubtitle() != null) {
					line.append(",").append(getFieldName("subtitulo")).append(getString(feed.getSubtitle()));
				}
				if (feed.getTitle() != null) {
					line.append(",").append(getFieldName("titulo")).append(getString(feed.getTitle()));
				}
				if (feed.getUri() != null) {
					line.append(",").append(getFieldName("uri")).append(getString(feed.getUri()));
				}
				if (feed.getUrl() != null) {
					line.append(",").append(getFieldName("url")).append(getString(feed.getUrl()));
				}
				if (feed.getPodcast() != null) {
					line.append(",").append(getFieldName("podcast")).append(getBoolean((feed.getPodcast())));
				}
				if (feed.getPublishedDate() != null) {
					line.append(",").append(getFieldName("fecha")).append(getNumber(feed.getPublishedDate().getTime()));
				}

				line.append("}");

				/*
				 * writer.println("\"INSERT INTO feed_feed(" +
				 * "feed_pk, feed_pblr_pk, feed_url, feed_author, feed_copyright, feed_encoding, feed_feedType, feed_generator"
				 * +
				 * ", feed_language, feed_link, feed_publishedDate, feed_title, feed_uri, feed_imUrl, feed_imHeight, feed_imWidth, feed_podcast"
				 * + ") VALUES (" + feedExporter.getNumber(feed.getId()) + ", " +
				 * feedExporter.getNumber(feed.getPblrId()) + ", " +
				 * feedExporter.getString(feed.getUrl()) + ", " +
				 * feedExporter.getString(feed.getAuthor()) + ", " +
				 * feedExporter.getString(feed.getCopyright()) + ", " +
				 * feedExporter.getString(feed.getEncoding()) + ", " +
				 * feedExporter.getString(feed.getFeedType()) + ", " +
				 * feedExporter.getString(feed.getGenerator()) + ", " +
				 * feedExporter.getString(feed.getLanguage()) + ", " +
				 * feedExporter.getString(feed.getLink()) + ", " +
				 * feedExporter.getDate(feed.getPublishedDate()) + ", " +
				 * feedExporter.getString(feed.getTitle()) + ", " +
				 * feedExporter.getString(feed.getUri()) + ", " +
				 * feedExporter.getString(feed.getImUrl()) + ", " +
				 * feedExporter.getNumber(feed.getImHeight()) + ", " +
				 * feedExporter.getNumber(feed.getImWidth()) + ", " +
				 * feedExporter.getBoolean(feed.getPodcast()) + ")\"");
				 */
				writer.println(line);

				if (iterator.hasNext()) {
					writer.print(",");
				}
			}

			writer.println("]}");
		}
	}

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void main(final String[] args) throws IOException {
		System.out.println("Start test");

		final FeedExporter exporter = new FeedExporter();

		exporter.exportFeeds();

		System.out.println("End test");
	}
}

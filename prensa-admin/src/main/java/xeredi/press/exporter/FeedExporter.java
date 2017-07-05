package xeredi.press.exporter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Iterator;

import com.google.inject.Guice;
import com.google.inject.Injector;

import xeredi.press.model.Feed;
import xeredi.press.model.FeedCriteria;
import xeredi.press.model.service.FeedService;
import xeredi.press.model.util.mybatis.PressGuiceModule;

public final class FeedExporter {
	public void exportFeeds() {

	}

	private String getString(final String value) {
		return value == null ? "NULL" : "'" + value + "'";
	}

	private String getNumber(final Number value) {
		return value == null ? "NULL" : value.toString();
	}

	private String getDate(final Date value) {
		return value == null ? "NULL" : "'" + value.toString() + "'";
	}

	private String getBoolean(final Boolean value) {
		return value == null ? "NULL" : (value ? "1" : "0");
	}

	public static void main(final String[] args) throws IOException {
		System.out.println("Start test");

		final FeedExporter feedExporter = new FeedExporter();
		final Injector injector = Guice.createInjector(new PressGuiceModule());
		final FeedService feedService = injector.getInstance(FeedService.class);

		try (final PrintWriter writer = new PrintWriter(
				"/home/xeredi/git/prensa/prensa-mobile/www/assets/feeds.json")) {
			writer.println("{\"feeds\": [");

			final Iterator<Feed> iterator = feedService.selectList(new FeedCriteria()).iterator();

			while (iterator.hasNext()) {
				final Feed feed = iterator.next();

				writer.println("\"INSERT INTO feed_feed("
						+ "feed_pk, feed_pblr_pk, feed_url, feed_author, feed_copyright, feed_description, feed_encoding, feed_feedType, feed_generator"
						+ ", feed_language, feed_link, feed_publishedDate, feed_title, feed_uri, feed_imUrl, feed_imHeight, feed_imWidth, feed_podcast"
						+ ", feed_subtitle) VALUES (" + feedExporter.getNumber(feed.getId()) + ", "
						+ feedExporter.getNumber(feed.getPblrId()) + ", " + feedExporter.getString(feed.getUrl()) + ", "
						+ feedExporter.getString(feed.getAuthor()) + ", " + feedExporter.getString(feed.getCopyright())
						+ ", " + feedExporter.getString(feed.getDescription()) + ", "
						+ feedExporter.getString(feed.getEncoding()) + ", " + feedExporter.getString(feed.getFeedType())
						+ ", " + feedExporter.getString(feed.getGenerator()) + ", "
						+ feedExporter.getString(feed.getLanguage()) + ", " + feedExporter.getString(feed.getLink())
						+ ", " + feedExporter.getDate(feed.getPublishedDate()) + ", "
						+ feedExporter.getString(feed.getTitle()) + ", " + feedExporter.getString(feed.getUri()) + ", "
						+ feedExporter.getString(feed.getImUrl()) + ", " + feedExporter.getNumber(feed.getImHeight())
						+ ", " + feedExporter.getNumber(feed.getImWidth()) + ", "
						+ feedExporter.getBoolean(feed.getPodcast()) + ", " + feedExporter.getString(feed.getSubtitle())
						+ ")\"");

				if (iterator.hasNext()) {
					writer.print(",");
				}
			}

			writer.println("]}");
		}

		for (final Feed feed : feedService.selectList(new FeedCriteria())) {
		}

		System.out.println("End test");
	}
}

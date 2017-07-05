package xeredi.press.exporter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import com.google.inject.Guice;
import com.google.inject.Injector;

import xeredi.press.model.Publisher;
import xeredi.press.model.PublisherCriteria;
import xeredi.press.model.service.PublisherService;
import xeredi.press.model.util.mybatis.PressGuiceModule;

public final class PublisherExporter extends JsonExporter {

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

		final PublisherExporter exporter = new PublisherExporter();
		final Injector injector = Guice.createInjector(new PressGuiceModule());
		final PublisherService pblrService = injector.getInstance(PublisherService.class);

		try (final PrintWriter writer = new PrintWriter(
				"/home/xeredi/git/prensa/prensa-mobile/www/assets/publishers.json")) {
			writer.println("{\"publishers\": [");

			final Iterator<Publisher> iterator = pblrService.selectList(new PublisherCriteria()).iterator();

			while (iterator.hasNext()) {
				final Publisher item = iterator.next();

				writer.println(
						"\"INSERT INTO publisher_pblr(pblr_pk, pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webUrl, pblr_logoUrl) VALUES ("
								+ exporter.getNumber(item.getId()) + ", " + exporter.getNumber(item.getCtgrId()) + ", "
								+ exporter.getString(item.getLanguage()) + ", " + exporter.getString(item.getCountry())
								+ ", " + exporter.getString(item.getName()) + ", "
								+ exporter.getString(item.getWebUrl()) + ", " + exporter.getString(item.getLogoUrl())
								+ ")\"");

				if (iterator.hasNext()) {
					writer.print(",");
				}
			}

			writer.println("]}");
		}

		System.out.println("End test");
	}

}

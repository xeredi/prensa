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

	public void export() throws IOException {
		final Injector injector = Guice.createInjector(new PressGuiceModule());
		final PublisherService pblrService = injector.getInstance(PublisherService.class);

		try (final PrintWriter writer = new PrintWriter(
				"/home/xeredi/git/ionic/prensa/src/assets/json/publishers.json")) {
			writer.println("{\"data\": [");

			final Iterator<Publisher> iterator = pblrService.selectList(new PublisherCriteria()).iterator();

			while (iterator.hasNext()) {
				final Publisher item = iterator.next();

				final StringBuilder builder = new StringBuilder();

				builder.append("{");
				builder.append(getFieldName("id")).append(getNumber(item.getId()));
				builder.append(",").append(getFieldName("idioma")).append(getString(item.getLanguage()));
				builder.append(",").append(getFieldName("pais")).append(getString(item.getCountry()));
				builder.append(",").append(getFieldName("url")).append(getString(item.getWebUrl()));
				builder.append(",").append(getFieldName("logo")).append(getString(item.getLogoUrl()));
				builder.append(",").append(getFieldName("nombre")).append(getString(item.getName()));
				builder.append(",").append(getFieldName("tipo")).append(getString(item.getWebType()));
				builder.append(",").append(getFieldName("categoria")).append(getString(item.getCtgr().getName()));
				builder.append("}");

				/*
				 * writer.println(
				 * "\"INSERT INTO publisher_pblr(pblr_pk, pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webUrl, pblr_logoUrl) VALUES ("
				 * + getNumber(item.getId()) + ", " + getNumber(item.getCtgrId()) + ", " +
				 * getString(item.getLanguage()) + ", " + getString(item.getCountry()) + ", " +
				 * getString(item.getName()) + ", " + getString(item.getWebUrl()) + ", " +
				 * getString(item.getLogoUrl()) + ")\"");
				 */

				if (iterator.hasNext()) {
					builder.append(",");
				}

				writer.println(builder);
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

		final PublisherExporter exporter = new PublisherExporter();

		exporter.export();

		System.out.println("End test");
	}

}

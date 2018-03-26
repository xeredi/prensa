package xeredi.press.exporter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import com.google.inject.Guice;
import com.google.inject.Injector;

import xeredi.press.model.Category;
import xeredi.press.model.service.CategoryService;
import xeredi.press.model.util.mybatis.PressGuiceModule;

public final class CategoryExporter extends JsonExporter {
	public void export() throws IOException {
		final Injector injector = Guice.createInjector(new PressGuiceModule());
		final CategoryService ctgrService = injector.getInstance(CategoryService.class);

		try (final PrintWriter writer = new PrintWriter(
				"/home/xeredi/git/ionic/prensa/src/assets/json/categories.json")) {
			writer.println("{\"data\": [");

			final Iterator<Category> iterator = ctgrService.selectList().iterator();

			while (iterator.hasNext()) {
				final Category item = iterator.next();

				final StringBuilder builder = new StringBuilder();

				builder.append("{");
				builder.append(getFieldName("nombre")).append(getString(item.getName()));
				builder.append(",").append(getFieldName("icono")).append(getString(item.getIcon()));
				builder.append("}");

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

		final CategoryExporter exporter = new CategoryExporter();

		exporter.export();

		System.out.println("End test");
	}

}

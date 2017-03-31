package xeredi.prensa.db;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.IndexOptions;
import org.mongodb.morphia.annotations.Indexes;

import lombok.Data;
import xeredi.prensa.dao.PublisherDAO;
import xeredi.prensa.morphia.DatastoreLocator;

// TODO: Auto-generated Javadoc
/**
 * The Class Publisher.
 */
@Entity(value = "publisher", noClassnameStored = true)
@Indexes({ @Index(fields = @Field("name"), options = @IndexOptions(name = "ix_pblr_name", unique = true)) })
@Data
public class Publisher {

    /**
     * Instantiates a new publisher.
     */
    public Publisher() {
        super();
    }

    /**
     * Instantiates a new publisher.
     *
     * @param aname
     *            the aname
     * @param arssHomeUrl
     *            the arss home url
     */
    public Publisher(final String aname, final String arssHomeUrl) {
        super();

        this.name = aname;
        this.rssHomeUrl = arssHomeUrl;
    }

    /** The id. */
    @Id
    private ObjectId id;

    /** The name. */
    private String name;

    /** The rss home url. */
    private String rssHomeUrl;

    /**
     * The main method.
     *
     * @param args
     *            the arguments
     */
    public static void main(final String[] args) {
        final PublisherDAO publisherDAO = new PublisherDAO(DatastoreLocator.findDatastore());

        publisherDAO.insert(new Publisher("ABC", "http://www.abc.es/rss/"));
        publisherDAO.insert(new Publisher("El Pais", "http://servicios.elpais.com/rss/"));
        publisherDAO.insert(new Publisher("El Mundo", "http://rss.elmundo.es/rss/"));
        publisherDAO.insert(new Publisher("La Vanguardia", "http://www.lavanguardia.com/rss"));
        publisherDAO.insert(new Publisher("El Periodico", "http://www.elperiodico.com/es/rss/portada_rss.shtml"));
        publisherDAO.insert(new Publisher("eldiario.es", "http://www.eldiario.es/Feeds.html"));
        publisherDAO.insert(new Publisher("Faro de Vigo", "http://www.farodevigo.es/servicios/rss/rss.jsp"));

        publisherDAO.insert(new Publisher("Hola!", "http://www.hola.com/rss/"));
        publisherDAO.insert(new Publisher("Lecturas", "http://www.lecturas.com/"));
        publisherDAO.insert(new Publisher("Diez Minutos", "http://www.diezminutos.es/informacion/a1145/rss-feeds/"));
        publisherDAO.insert(new Publisher("Interviu", "http://www.interviu.es/servicios/rss"));
        publisherDAO.insert(new Publisher("QMD - Que Me Dices", "http://quemedices.diezminutos.es/listado_rss"));
        publisherDAO.insert(new Publisher("Mia", "http://www.miarevista.es/"));
        publisherDAO.insert(new Publisher("Revista LOVE", "http://www.revistalove.es/"));
        publisherDAO.insert(new Publisher("Auto Hebdo Sport", "http://www.autohebdosport.es/"));
        publisherDAO.insert(new Publisher("Autopista", "http://www.autopista.es/"));
        publisherDAO.insert(new Publisher("Tiempo", "http://www.tiempodehoy.com/servicios/rss"));
        publisherDAO.insert(new Publisher("Moticiclismo", "http://www.motociclismo.es/"));

        publisherDAO.insert(new Publisher("Muy Interesante", "http://www.muyinteresante.es/"));
        publisherDAO.insert(new Publisher("Quo", "http://www.quo.es/"));

        publisherDAO.insert(new Publisher("Onda Cero", "http://www.ondacero.es/podcast/"));
        publisherDAO.insert(new Publisher("Cadena SER", "http://cadenaser.com/ser/podcasts/"));

        publisherDAO.insert(new Publisher("BOE", "https://www.boe.es/rss/"));

        publisherDAO.insert(new Publisher("Marca", "http://www.marca.com/deporte/rss/"));
        publisherDAO.insert(new Publisher("Diario AS", "http://as.com/rss/"));
        publisherDAO.insert(new Publisher("Diario Sport", "http://www.sport.es/es/rss/"));
        publisherDAO.insert(new Publisher("Mundo Deportivo",
                "http://www.mundodeportivo.com/20080115/noticias-rss_53429321533.html"));
        publisherDAO.insert(new Publisher("Dxt Campeón", "http://www.dxtcampeon.com/rss/listado"));

        publisherDAO.insert(new Publisher("Rokambol", "http://rokambol.com/"));
        publisherDAO.insert(new Publisher("Huffington Post", "http://www.huffingtonpost.es/"));



    }
}

package xeredi.prensa.db;

import java.util.Arrays;
import java.util.List;

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

/*
 * (non-Javadoc)
 *
 * @see java.lang.Object#toString()
 */
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
     * @param ahomeUrl
     *            the ahome url
     * @param arssHomeUrl
     *            the arss home url
     * @param avalidType
     *            the avalid type
     * @param achannelUrlList
     *            the achannel url list
     */
    public Publisher(final String aname, final String ahomeUrl, final String arssHomeUrl, final String avalidType,
            final List<String> achannelUrlList) {
        super();

        this.name = aname;
        this.homeUrl = ahomeUrl;
        this.rssHomeUrl = arssHomeUrl;
        this.validType = avalidType;
        this.channelUrlList = achannelUrlList;
    }

    /** The id. */
    @Id
    private ObjectId id;

    /** The name. */
    private String name;

    /** The home url. */
    private String homeUrl;

    /** The rss home url. */
    private String rssHomeUrl;

    /** The valid type. */
    private String validType;

    /** The channel url list. */
    private List<String> channelUrlList;

    /**
     * The main method.
     *
     * @param args
     *            the arguments
     */
    public static void main(final String[] args) {
        final PublisherDAO publisherDAO = new PublisherDAO(DatastoreLocator.findDatastore());

        publisherDAO.insert(new Publisher("PC Actual", "http://www.pcactual.com/", null, "rss",
                Arrays.asList("http://www.pcactual.com/feeds/rss.html")));
        publisherDAO.insert(new Publisher("InfoLibre", "http://www.infolibre.es/", null, "rss",
                Arrays.asList("http://www.infolibre.es/index.php/mod.portadas/mem.rss")));
        publisherDAO.insert(new Publisher("BBC Mundo", "http://www.bbc.com/mundo", null, "rss",
                Arrays.asList("http://feeds.bbci.co.uk/mundo/rss.xml")));
        publisherDAO.insert(new Publisher("OCU", "https://www.ocu.org/", null, "rss",
                Arrays.asList("http://feeds.feedburner.com/Ocu-Organizacion-Consumidores")));

        publisherDAO.insert(new Publisher("Mi Casa", "http://www.micasarevista.com/", null, "rss",
                Arrays.asList("http://www.micasarevista.com/rss/feed/micasa")));
        publisherDAO.insert(new Publisher("Emprendedores", "http://www.emprendedores.es/",
                "http://www.emprendedores.es/listado-rss", "rss", null));
        publisherDAO.insert(new Publisher("Fotogramas", "http://www.fotogramas.es/", null, "rss",
                Arrays.asList("http://www.fotogramas.es/rss/feed/fotogramas")));
        publisherDAO.insert(new Publisher("Harper's Bazaar", "http://www.harpersbazaar.es/", null, "rss",
                Arrays.asList("http://www.harpersbazaar.es/rss/feed/site")));
        publisherDAO.insert(new Publisher("Nuevo Estilo", "http://nuevo-estilo.micasarevista.com/", null, "rss",
                Arrays.asList("http://nuevo-estilo.micasarevista.com/rss/feed/nuevoestilo")));
        publisherDAO.insert(new Publisher("TelePrograma", "http://teleprograma.diezminutos.es/", null, "rss",
                Arrays.asList("http://teleprograma.diezminutos.es/rss/feed/site")));
        publisherDAO.insert(new Publisher("EGO", "http://www.egorevista.es/", null, "rss",
                Arrays.asList("http://www.egorevista.es/rss/feed/site")));
        publisherDAO.insert(new Publisher("Marca Motor", "http://www.marcamotor.com/revista.html", null, "rss",
                Arrays.asList("http://estaticos.marcamotor.com/rss/revista.xml")));
        publisherDAO.insert(new Publisher("Divinity", "http://www.divinity.es/", null, "rss",
                Arrays.asList("http://www.divinity.es/rss/")));
        publisherDAO.insert(new Publisher("Muy Historia", "http://www.muyhistoria.es/", null, "rss",
                Arrays.asList("http://www.muyhistoria.es/rss")));
        publisherDAO.insert(new Publisher("Sport Life", "http://www.sportlife.es/", null, "rss",
                Arrays.asList("http://api.motorpress-iberica.es/rss/sportlife/")));
        publisherDAO.insert(new Publisher("Ser Padres", "http://www.serpadres.es/", null, "rss",
                Arrays.asList("http://feeds.feedburner.com/serpadres")));
        publisherDAO.insert(new Publisher("Marie Claire", "http://www.marie-claire.es/", null, "rss",
                Arrays.asList("http://www.marie-claire.es/rss")));
        publisherDAO.insert(new Publisher("Ciclismo a Fondo", "http://www.ciclismoafondo.es/", null, "rss",
                Arrays.asList("http://api.motorpress-iberica.es/rss/ciclismoafondo")));
        publisherDAO.insert(new Publisher("Runner's World", "http://www.runners.es/", null, "rss",
                Arrays.asList("http://api.motorpress-iberica.es/rss/runners")));
        publisherDAO.insert(new Publisher("Motociclismo", "http://www.motociclismo.es/", null, "rss",
                Arrays.asList("http://api.motorpress-iberica.es/rss/motociclismo")));
        publisherDAO.insert(new Publisher("Digital Camera", "http://www.digitalcamera.es/", null, "rss",
                Arrays.asList("http://www.digitalcamera.es/feed/")));
        publisherDAO.insert(new Publisher("Primera Linea", "http://www.primeralinea.es/", null, "rss",
                Arrays.asList("http://www.primeralinea.es/feed/")));
        publisherDAO.insert(new Publisher("Stilo", "http://www.stilo.es/", "http://www.stilo.es/rss", "rss", null));
        publisherDAO.insert(new Publisher("Interviu", "http://www.interviu.es/", "http://www.interviu.es/servicios/rss",
                "rss", null));

        publisherDAO.insert(
                new Publisher("Mujer Hoy", "http://www.mujerhoy.com/", "http://www.mujerhoy.com/rss/", "rss", null));

        publisherDAO.insert(new Publisher("Hola!", "http://www.hola.com/", null, "rss",
                Arrays.asList("http://www.hola.com/rss.xml")));
        publisherDAO.insert(new Publisher("Lecturas", "http://www.lecturas.com/", null, null,
                Arrays.asList("http://www.lecturas.com/feeds/rss.html")));
        publisherDAO.insert(new Publisher("Diez Minutos", "http://www.diezminutos.es/",
                "http://www.diezminutos.es/informacion/a1145/rss-feeds/", "rss", null));

        publisherDAO.insert(new Publisher("QMD - Que Me Dices", "http://quemedices.diezminutos.es/",
                "http://quemedices.diezminutos.es/listado_rss", "rss", null));
        publisherDAO.insert(new Publisher("Mia", "http://www.miarevista.es/", null, "rss",
                Arrays.asList("http://www.miarevista.es/rss")));
        publisherDAO.insert(new Publisher("Revista LOVE", "http://www.revistalove.es/", null, null,
                Arrays.asList("http://www.revistalove.es/feed/")));
        publisherDAO.insert(new Publisher("Auto Hebdo Sport", "http://www.autohebdosport.es/", null, "rss",
                Arrays.asList("http://www.autohebdosport.es/feed/")));
        publisherDAO.insert(new Publisher("Autopista", "http://www.autopista.es/", null, "rss",
                Arrays.asList("http://api.motorpress-iberica.es/rss/autopista/")));
        publisherDAO.insert(new Publisher("Muy Interesante", "http://www.muyinteresante.es/", null, "rss",
                Arrays.asList("http://feeds.feedburner.com/Muyinteresantees")));
        publisherDAO.insert(new Publisher("Quo", "http://www.quo.es/", null, "rss",
                Arrays.asList("http://www.quo.es/rss/feed/site")));
        publisherDAO.insert(new Publisher("National Geographic", "http://www.nationalgeographic.com.es/", null, "rss",
                Arrays.asList("http://www.nationalgeographic.com.es/feeds/rss.html")));
        publisherDAO.insert(new Publisher("Vogue", "http://www.vogue.es/", null, "atom",
                Arrays.asList("http://www.vogue.es/home.xml")));
        publisherDAO.insert(new Publisher("Elle", "http://www.elle.es/", null, "rss",
                Arrays.asList("http://www.elle.es/rss/all.xml")));

        publisherDAO.insert(
                new Publisher("Onda Cero", "http://www.ondacero.es/", "http://www.ondacero.es/podcast/", "rss", null));
        publisherDAO.insert(new Publisher("Cadena SER", "http://cadenaser.com/", "http://cadenaser.com/ser/podcasts/",
                "rss", null));
        publisherDAO.insert(new Publisher("Cadena COPE", "http://www.cope.es/", null, "rss",
                Arrays.asList("http://www.cope.es/api/es/programas/herrera-en-cope/audios/rss.xml",
                        "http://www.cope.es/api/es/programas/mediodia-cope/audios/rss.xml",
                        "http://www.cope.es/api/es/programas/la-tarde/audios/rss.xml",
                        "http://www.cope.es/api/es/programas/la-linterna/audios/rss.xml",
                        "http://www.cope.es/api/es/programas/el-partidazo-de-cope/audios/rss.xml",
                        "http://www.cope.es/api/es/programas/tiempo-de-juego/audios/rss.xml")));

        // publisherDAO.insert(new Publisher("El Día", "http://www.eldia.es/", "http://eldia.es/rss/", "rss",
        // null));
        // publisherDAO.insert(new Publisher("ABC", "http://www.abc.es/", "http://www.abc.es/rss/", "rss",
        // null));

        publisherDAO.insert(new Publisher("20 minutos", "http://www.20minutos.es/",
                "http://www.20minutos.es/sindicacion/", "rss", null));
        // publisherDAO.insert(new Publisher("El Pais", "http://servicios.elpais.com/rss/", "rss", null));
        // publisherDAO.insert(new Publisher("El Mundo", "http://rss.elmundo.es/rss/", "rss", null));
        // publisherDAO.insert(new Publisher("La Vanguardia", "http://www.lavanguardia.com/rss", "rss",
        // null));
        // publisherDAO.insert(
        // new Publisher("El Periodico", "http://www.elperiodico.com/es/rss/portada_rss.shtml", "rss", null));
        // // publisherDAO.insert(new Publisher("ABC", "http://www.abc.es/rss/", "rss", null));
        // publisherDAO.insert(new Publisher("El Correo", "http://www.elcorreo.com/bizkaia/rss/", "atom",
        // null));
        // publisherDAO.insert(new Publisher("La Nueva España", "http://www.lne.es/servicios/rss/rss.jsp",
        // "rss",
        // null));
        // publisherDAO
        // .insert(new Publisher("Faro de Vigo", "http://www.farodevigo.es/servicios/rss/rss.jsp", "rss",
        // null));
        // publisherDAO.insert(new Publisher("Levante", "http://www.levante-emv.com/servicios/rss/rss.jsp",
        // "rss",
        // null));
        // publisherDAO.insert(new Publisher("La Razón", "http://www.larazon.es/feeds", "rss", null));
        // publisherDAO.insert(new Publisher("Informacion Alicante",
        // "http://www.diarioinformacion.com/servicios/rss/rss.jsp", "rss", null));
        // publisherDAO.insert(new Publisher("Diario Vasco", "http://www.diariovasco.com/rss/", "atom",
        // null));
        // publisherDAO
        // .insert(new Publisher("El Norte de Castilla", "http://www.elnortedecastilla.es/rss/", "atom",
        // null));
        // publisherDAO.insert(new Publisher("La Verdad", "http://www.laverdad.es/murcia/rss/", "atom",
        // null));
        // publisherDAO.insert(new Publisher("Ultima Hora", "http://ultimahora.es/", "atom", null));
        // publisherDAO.insert(new Publisher("El Comercio", "http://www.elcomercio.es/rss/", "atom", null));
        // publisherDAO
        // .insert(new Publisher("La Provincia", "http://www.laprovincia.es/servicios/rss/rss.jsp", "rss",
        // null));
        // // publisherDAO.insert(new Publisher("El Día", "http://eldia.es/rss/", "rss", null));
        // publisherDAO.insert(new Publisher("El Diario Montañes", "http://www.eldiariomontanes.es/rss/",
        // "atom",
        // null));
        // publisherDAO.insert(new Publisher("Ideal de Granada", "http://www.ideal.es/granada/rss/", "atom",
        // null));
        // publisherDAO.insert(new Publisher("Ideal de Jaen", "http://www.ideal.es/jaen/rss/", "atom", null));
        // publisherDAO.insert(new Publisher("Ideal de Almeria", "http://www.ideal.es/almeria/rss/", "atom",
        // null));
        // publisherDAO.insert(new Publisher("Hoy Diario de Extremadura", "http://www.hoy.es/rss/", "atom",
        // null));
        // publisherDAO.insert(new Publisher("Diario Sur", "http://www.diariosur.es/rss/", "atom", null));
        // publisherDAO.insert(new Publisher("Diario de Leon", "http://www.diariodeleon.es/info/rss.php",
        // "rss", null));
        // publisherDAO.insert(new Publisher("Las Provincias", "http://www.lasprovincias.es/rss/", "atom",
        // null));
        // publisherDAO.insert(new Publisher("El Punt Avui", "http://www.elpuntavui.cat/serveis/rss.html",
        // "rss",
        // null));
        // publisherDAO.insert(new Publisher("Canarias 7", "http://www.canarias7.es/servicios/rss.cfm", "rss",
        // null));
        // publisherDAO.insert(new Publisher("Diario de Mallorca",
        // "http://www.diariodemallorca.es/servicios/rss/rss.jsp",
        // "rss", null));
        // publisherDAO.insert(new Publisher("Ara", "http://www.ara.cat/rss.html", "rss", null));
        // publisherDAO.insert(new Publisher("El Progreso", "http://elprogreso.galiciae.com/", "rss", null));
        // publisherDAO.insert(
        // new Publisher("Mediterraneo", "http://www.elperiodicomediterraneo.com/info/rss.php", "rss", null));
        // publisherDAO.insert(new Publisher("Diario de Burgos", "http://www.diariodeburgos.es/rss.aspx",
        // "rss", null));
        // publisherDAO.insert(new Publisher("La Región", "http://www.laregion.es/rss/listado", "rss", null));
        // publisherDAO.insert(new Publisher("Diario La Rioja", "http://www.larioja.com/rss/", "atom", null));
        // publisherDAO.insert(new Publisher("Córdoba", "http://www.diariocordoba.com/info/rss.php", "rss",
        // null));
        // publisherDAO.insert(new Publisher("La Gaceta de Salamanca",
        // "http://www.lagacetadesalamanca.es/servicios/rss/rss.jsp", "rss", null));
        // publisherDAO.insert(new Publisher("La Voz de Almería", "http://www.lavozdealmeria.es/rss.asp",
        // null, null));
        // publisherDAO
        // .insert(new Publisher("Diario de Pontevedra", "http://diariodepontevedra.galiciae.com/", "rss",
        // null));
        //
        // publisherDAO.insert(new Publisher("Expansion", "http://www.expansion.com/rss/", "rss", null));
        // publisherDAO
        // .insert(new Publisher("Inversión & Finanzas", "http://www.finanzas.com/rss/index.html", "rss",
        // null));
        //
        // publisherDAO.insert(new Publisher("eldiario.es", "http://www.eldiario.es/Feeds.html", "rss",
        // null));
        //
        //
        // publisherDAO.insert(new Publisher("BOE", "https://www.boe.es/rss/", "rss", null));
        //
        // publisherDAO.insert(new Publisher("Marca", "http://www.marca.com/deporte/rss/", "rss", null));
        // publisherDAO.insert(new Publisher("Diario AS", "http://as.com/rss/", "rss", null));
        // publisherDAO.insert(new Publisher("Diario Sport", "http://www.sport.es/es/rss/", "rss", null));
        // publisherDAO.insert(new Publisher("Mundo Deportivo",
        // "http://www.mundodeportivo.com/20080115/noticias-rss_53429321533.html", "rss", null));
        // publisherDAO.insert(new Publisher("Dxt Campeón", "http://www.dxtcampeon.com/rss/listado", "rss",
        // null));
        //
        // publisherDAO.insert(new Publisher("Rokambol", "http://rokambol.com/", "rss", null));
        // publisherDAO.insert(new Publisher("Huffington Post", "http://www.huffingtonpost.es/", "rss",
        // null));

        publisherDAO.insert(
                new Publisher("Telva", "http://www.telva.com/", "http://www.telva.com/estaticas/rss/", "rss", null));
        publisherDAO.insert(new Publisher("Car and Driver", "http://www.caranddriverthef1.com/",
                "http://www.caranddriverthef1.com/informacion/rss", "rss", null));
        publisherDAO.insert(new Publisher("Tiempo", "http://www.tiempodehoy.com/",
                "http://www.tiempodehoy.com/servicios/rss", null, null));

        publisherDAO.insert(
                new Publisher("RT", "https://actualidad.rt.com/", "https://actualidad.rt.com/feed", "rss", null));
    }
}

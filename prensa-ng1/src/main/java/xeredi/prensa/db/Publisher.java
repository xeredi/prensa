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
    public Publisher(final String arssLang, final String aname, final String ahomeUrl, final String arssHomeUrl,
            final String avalidType, final List<String> achannelUrlList) {
        super();

        this.rssLang = arssLang;
        this.name = aname;
        this.homeUrl = ahomeUrl;
        this.rssHomeUrl = arssHomeUrl;
        this.validType = avalidType;
        this.channelUrlList = achannelUrlList;
    }

    /** The id. */
    @Id
    private ObjectId id;

    private String rssLang;

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

        publisherDAO.insert(new Publisher("es", "Cadena COPE", "http://www.cope.es/", null, "rss",
                Arrays.asList("http://www.cope.es/api/es/programas/herrera-en-cope/audios/rss.xml",
                        "http://www.cope.es/api/es/programas/mediodia-cope/audios/rss.xml",
                        "http://www.cope.es/api/es/programas/la-tarde/audios/rss.xml",
                        "http://www.cope.es/api/es/programas/la-linterna/audios/rss.xml",
                        "http://www.cope.es/api/es/programas/el-partidazo-de-cope/audios/rss.xml",
                        "http://www.cope.es/api/es/programas/tiempo-de-juego/audios/rss.xml")));
        publisherDAO.insert(new Publisher("en", "Onda Cero", "http://www.ondacero.es/",
                "http://www.ondacero.es/podcast/", "rss", null));
        publisherDAO.insert(new Publisher("en", "Cadena SER", "http://cadenaser.com/",
                "http://cadenaser.com/ser/podcasts/", "rss", null));
        publisherDAO.insert(new Publisher("en", "Radio 3", "http://www.rtve.es/radio/radio3/",
                "http://www.rtve.es/programas_radio3/rss.php", "rss", null));
        publisherDAO.insert(new Publisher("en", "Europa FM", "http://www.europafm.com/",
                "http://www.europafm.com/podcast/", "rss", null));
        publisherDAO
                .insert(new Publisher("en", "Rac 1", "http://www.rac1.cat/", "http://www.rac1.cat/rss", "rss", null));
        publisherDAO.insert(new Publisher("en", "Catalunya Radio", "http://www.ccma.cat/catradio/",
                "http://www.ccma.cat/catradio/podcast/", "rss", null));

        publisherDAO.insert(new Publisher("en", "PC Actual", "http://www.pcactual.com/", null, "rss",
                Arrays.asList("http://www.pcactual.com/feeds/rss.html")));
        publisherDAO.insert(new Publisher("en", "InfoLibre", "http://www.infolibre.es/", null, "rss",
                Arrays.asList("http://www.infolibre.es/index.php/mod.portadas/mem.rss")));
        publisherDAO.insert(new Publisher("en", "La Marea", "http://www.lamarea.com/", null, "rss",
                Arrays.asList("http://www.lamarea.com/feed/")));
        publisherDAO.insert(new Publisher("en", "El Confidencial", "http://www.elconfidencial.com/",
                "http://www.elconfidencial.com/rss/", "rss", null));
        publisherDAO.insert(new Publisher("en", "Europa Press", "http://www.europapress.es/",
                "http://www.europapress.es/contenidosrss.aspx", "rss", null));
        publisherDAO.insert(new Publisher("en", "Reuters", "http://www.reuters.com/",
                "http://www.reuters.com/tools/rss", "rss", null));

        publisherDAO.insert(new Publisher("en", "BBC Mundo", "http://www.bbc.com/mundo", null, "rss",
                Arrays.asList("http://feeds.bbci.co.uk/mundo/rss.xml")));
        publisherDAO.insert(new Publisher("en", "OCU", "https://www.ocu.org/", null, "rss",
                Arrays.asList("http://feeds.feedburner.com/Ocu-Organizacion-Consumidores")));

        publisherDAO.insert(new Publisher("en", "Mi Casa", "http://www.micasarevista.com/", null, "rss",
                Arrays.asList("http://www.micasarevista.com/rss/feed/micasa")));
        publisherDAO.insert(new Publisher("en", "Emprendedores", "http://www.emprendedores.es/",
                "http://www.emprendedores.es/listado-rss", "rss", null));
        publisherDAO.insert(new Publisher("en", "Fotogramas", "http://www.fotogramas.es/", null, "rss",
                Arrays.asList("http://www.fotogramas.es/rss/feed/fotogramas")));
        publisherDAO.insert(new Publisher("en", "Harper's Bazaar", "http://www.harpersbazaar.es/", null, "rss",
                Arrays.asList("http://www.harpersbazaar.es/rss/feed/site")));
        publisherDAO.insert(new Publisher("en", "Nuevo Estilo", "http://nuevo-estilo.micasarevista.com/", null, "rss",
                Arrays.asList("http://nuevo-estilo.micasarevista.com/rss/feed/nuevoestilo")));
        publisherDAO.insert(new Publisher("en", "TelePrograma", "http://teleprograma.diezminutos.es/", null, "rss",
                Arrays.asList("http://teleprograma.diezminutos.es/rss/feed/site")));
        publisherDAO.insert(new Publisher("en", "EGO", "http://www.egorevista.es/", null, "rss",
                Arrays.asList("http://www.egorevista.es/rss/feed/site")));
        publisherDAO.insert(new Publisher("en", "Marca Motor", "http://www.marcamotor.com/revista.html", null, "rss",
                Arrays.asList("http://estaticos.marcamotor.com/rss/revista.xml")));
        publisherDAO.insert(new Publisher("en", "Divinity", "http://www.divinity.es/", null, "rss",
                Arrays.asList("http://www.divinity.es/rss/")));
        publisherDAO.insert(new Publisher("en", "Muy Historia", "http://www.muyhistoria.es/", null, "rss",
                Arrays.asList("http://www.muyhistoria.es/rss")));
        publisherDAO.insert(new Publisher("en", "Sport Life", "http://www.sportlife.es/", null, "rss",
                Arrays.asList("http://api.motorpress-iberica.es/rss/sportlife/")));
        publisherDAO.insert(new Publisher("en", "Ser Padres", "http://www.serpadres.es/", null, "rss",
                Arrays.asList("http://feeds.feedburner.com/serpadres")));
        publisherDAO.insert(new Publisher("en", "Marie Claire", "http://www.marie-claire.es/", null, "rss",
                Arrays.asList("http://www.marie-claire.es/rss")));
        publisherDAO.insert(new Publisher("en", "Ciclismo a Fondo", "http://www.ciclismoafondo.es/", null, "rss",
                Arrays.asList("http://api.motorpress-iberica.es/rss/ciclismoafondo")));
        publisherDAO.insert(new Publisher("en", "Runner's World", "http://www.runners.es/", null, "rss",
                Arrays.asList("http://api.motorpress-iberica.es/rss/runners")));
        publisherDAO.insert(new Publisher("en", "Motociclismo", "http://www.motociclismo.es/", null, "rss",
                Arrays.asList("http://api.motorpress-iberica.es/rss/motociclismo")));
        publisherDAO.insert(new Publisher("en", "Digital Camera", "http://www.digitalcamera.es/", null, "rss",
                Arrays.asList("http://www.digitalcamera.es/feed/")));
        publisherDAO.insert(new Publisher("en", "Primera Linea", "http://www.primeralinea.es/", null, "rss",
                Arrays.asList("http://www.primeralinea.es/feed/")));
        publisherDAO
                .insert(new Publisher("en", "Stilo", "http://www.stilo.es/", "http://www.stilo.es/rss", "rss", null));
        publisherDAO.insert(new Publisher("en", "Interviu", "http://www.interviu.es/",
                "http://www.interviu.es/servicios/rss", "rss", null));

        publisherDAO.insert(new Publisher("en", "Mujer Hoy", "http://www.mujerhoy.com/", "http://www.mujerhoy.com/rss/",
                "rss", null));

        publisherDAO.insert(new Publisher("en", "Hola!", "http://www.hola.com/", null, "rss",
                Arrays.asList("http://www.hola.com/rss.xml")));
        publisherDAO.insert(new Publisher("en", "Lecturas", "http://www.lecturas.com/", null, null,
                Arrays.asList("http://www.lecturas.com/feeds/rss.html")));
        publisherDAO.insert(new Publisher("en", "Diez Minutos", "http://www.diezminutos.es/",
                "http://www.diezminutos.es/informacion/a1145/rss-feeds/", "rss", null));

        publisherDAO.insert(new Publisher("en", "QMD - Que Me Dices", "http://quemedices.diezminutos.es/",
                "http://quemedices.diezminutos.es/listado_rss", "rss", null));
        publisherDAO.insert(new Publisher("en", "Mia", "http://www.miarevista.es/", null, "rss",
                Arrays.asList("http://www.miarevista.es/rss")));
        publisherDAO.insert(new Publisher("en", "Revista LOVE", "http://www.revistalove.es/", null, null,
                Arrays.asList("http://www.revistalove.es/feed/")));
        publisherDAO.insert(new Publisher("en", "Auto Hebdo Sport", "http://www.autohebdosport.es/", null, "rss",
                Arrays.asList("http://www.autohebdosport.es/feed/")));
        publisherDAO.insert(new Publisher("en", "Autopista", "http://www.autopista.es/", null, "rss",
                Arrays.asList("http://api.motorpress-iberica.es/rss/autopista/")));
        publisherDAO.insert(new Publisher("en", "Muy Interesante", "http://www.muyinteresante.es/", null, "rss",
                Arrays.asList("http://feeds.feedburner.com/Muyinteresantees")));
        publisherDAO.insert(new Publisher("en", "Quo", "http://www.quo.es/", null, "rss",
                Arrays.asList("http://www.quo.es/rss/feed/site")));
        publisherDAO.insert(new Publisher("en", "National Geographic", "http://www.nationalgeographic.com.es/", null,
                "rss", Arrays.asList("http://www.nationalgeographic.com.es/feeds/rss.html")));
        publisherDAO.insert(new Publisher("en", "Vogue", "http://www.vogue.es/", null, "atom",
                Arrays.asList("http://www.vogue.es/home.xml")));
        publisherDAO.insert(new Publisher("en", "Elle", "http://www.elle.es/", null, "rss",
                Arrays.asList("http://www.elle.es/rss/all.xml")));

        publisherDAO.insert(new Publisher("en", "20 minutos", "http://www.20minutos.es/",
                "http://www.20minutos.es/sindicacion/", "rss", null));
        publisherDAO.insert(
                new Publisher("en", "El Pais", "http://elpais.com/", "http://servicios.elpais.com/rss/", "rss", null));
        publisherDAO.insert(
                new Publisher("en", "El Mundo", "http://www.elmundo.es/", "http://rss.elmundo.es/rss/", "rss", null));
        publisherDAO.insert(new Publisher("en", "La Vanguardia", "http://www.lavanguardia.com/",
                "http://www.lavanguardia.com/rss", "rss", null));
        publisherDAO.insert(new Publisher("en", "El Periodico", "http://www.elperiodico.com/es/",
                "http://www.elperiodico.com/es/rss/portada_rss.shtml", "rss", null));
        // publisherDAO.insert(new Publisher("en", "ABC", "http://www.abc.es/", "http://www.abc.es/rss/",
        // "rss", null));
        publisherDAO.insert(new Publisher("en", "El Correo", "http://www.elcorreo.com/",
                "http://www.elcorreo.com/bizkaia/rss/", "atom", null));
        publisherDAO.insert(new Publisher("en", "La Nueva España", "http://www.lne.es/",
                "http://www.lne.es/servicios/rss/rss.jsp", "rss", null));
        publisherDAO.insert(new Publisher("en", "Faro de Vigo", "http://www.farodevigo.es/",
                "http://www.farodevigo.es/servicios/rss/rss.jsp", "rss", null));
        publisherDAO.insert(new Publisher("en", "Levante", "http://www.levante-emv.com/",
                "http://www.levante-emv.com/servicios/rss/rss.jsp", "rss", null));
        publisherDAO.insert(
                new Publisher("en", "La Razón", "http://www.larazon.es/", "http://www.larazon.es/feeds", "rss", null));
        publisherDAO.insert(new Publisher("en", "Informacion Alicante", "http://www.diarioinformacion.com/",
                "http://www.diarioinformacion.com/servicios/rss/rss.jsp", "rss", null));
        publisherDAO.insert(new Publisher("en", "Diario Vasco", "http://www.diariovasco.com/",
                "http://www.diariovasco.com/rss/", "atom", null));
        publisherDAO.insert(new Publisher("en", "El Norte de Castilla", "http://www.elnortedecastilla.es/",
                "http://www.elnortedecastilla.es/rss/", "atom", null));
        publisherDAO.insert(new Publisher("en", "La Verdad", "http://www.laverdad.es/",
                "http://www.laverdad.es/murcia/rss/", "atom", null));
        publisherDAO.insert(new Publisher("en", "Ultima Hora", "http://ultimahora.es/", null, "atom",
                Arrays.asList("http://ultimahora.es/mallorca/feed.rss")));
        publisherDAO.insert(new Publisher("en", "El Comercio", "http://www.elcomercio.es/",
                "http://www.elcomercio.es/rss/", "atom", null));
        publisherDAO.insert(new Publisher("en", "La Provincia", "http://www.laprovincia.es/",
                "http://www.laprovincia.es/servicios/rss/rss.jsp", "rss", null));
        // publisherDAO.insert(new Publisher("en", "El Día", "http://www.eldia.es/", "http://eldia.es/rss/",
        // "rss", null));
        publisherDAO.insert(new Publisher("en", "El Diario Montañes", "http://www.eldiariomontanes.es/",
                "http://www.eldiariomontanes.es/rss/", "atom", null));
        publisherDAO.insert(new Publisher("en", "Ideal de Granada", "http://www.ideal.es/granada/",
                "http://www.ideal.es/granada/rss/", "atom", null));
        publisherDAO.insert(new Publisher("en", "Ideal de Jaen", "http://www.ideal.es/jaen/",
                "http://www.ideal.es/jaen/rss/", "atom", null));
        publisherDAO.insert(new Publisher("en", "Ideal de Almería", "http://www.ideal.es/almeria/",
                "http://www.ideal.es/almeria/rss/", "atom", null));
        publisherDAO.insert(new Publisher("en", "Hoy Diario de Extremadura", "http://www.hoy.es/",
                "http://www.hoy.es/rss/", "atom", null));
        publisherDAO.insert(new Publisher("en", "Diario Sur", "http://www.diariosur.es/",
                "http://www.diariosur.es/rss/", "atom", null));
        publisherDAO.insert(new Publisher("en", "Diario de Leon", "http://www.diariodeleon.es/",
                "http://www.diariodeleon.es/info/rss.php", "rss", null));
        publisherDAO.insert(new Publisher("en", "Las Provincias", "http://www.lasprovincias.es/",
                "http://www.lasprovincias.es/rss/", "atom", null));
        publisherDAO.insert(new Publisher("en", "El Punt Avui", "http://www.elpuntavui.cat/",
                "http://www.elpuntavui.cat/serveis/rss.html", "rss", null));
        publisherDAO.insert(new Publisher("en", "Canarias 7", "http://www.canarias7.es/",
                "http://www.canarias7.es/servicios/rss.cfm", "rss", null));
        publisherDAO.insert(new Publisher("en", "Diario de Mallorca", "http://www.diariodemallorca.es/",
                "http://www.diariodemallorca.es/servicios/rss/rss.jsp", "rss", null));
        publisherDAO
                .insert(new Publisher("en", "Ara", "http://www.ara.cat/", "http://www.ara.cat/rss.html", "rss", null));
        publisherDAO.insert(new Publisher("en", "El Progreso", "http://elprogreso.galiciae.com/", null, "rss",
                Arrays.asList("http://elprogreso.galiciae.com/noticias/rss")));
        publisherDAO.insert(new Publisher("en", "Mediterraneo", "http://www.elperiodicomediterraneo.com/",
                "http://www.elperiodicomediterraneo.com/info/rss.php", "rss", null));
        publisherDAO.insert(new Publisher("en", "Diario de Burgos", "http://www.diariodeburgos.es/",
                "http://www.diariodeburgos.es/rss.aspx", "rss", null));
        publisherDAO.insert(new Publisher("en", "La Región", "http://www.laregion.es/",
                "http://www.laregion.es/rss/listado", "rss", null));
        publisherDAO.insert(new Publisher("en", "Diario La Rioja", "http://www.larioja.com/",
                "http://www.larioja.com/rss/", "atom", null));
        publisherDAO.insert(new Publisher("en", "Córdoba", "http://www.diariocordoba.com/",
                "http://www.diariocordoba.com/info/rss.php", "rss", null));
        publisherDAO.insert(new Publisher("en", "La Gaceta de Salamanca", "http://www.lagacetadesalamanca.es/",
                "http://www.lagacetadesalamanca.es/servicios/rss/rss.jsp", "rss", null));
        publisherDAO.insert(new Publisher("en", "La Voz de Almería", "http://www.lavozdealmeria.es", null, null,
                Arrays.asList("http://www.lavozdealmeria.es/rss/rss30.html",
                        "http://www.lavozdealmeria.es/rss/rss32.html", "http://www.lavozdealmeria.es/rss/rss33.html",
                        "http://www.lavozdealmeria.es/rss/rss34.html", "http://www.lavozdealmeria.es/rss/rss35.html",
                        "http://www.lavozdealmeria.es/rss/rss36.html", "http://www.lavozdealmeria.es/rss/rss38.html")));
        publisherDAO.insert(new Publisher("en", "Diario de Pontevedra", "http://diariodepontevedra.galiciae.com/", null,
                "rss", Arrays.asList("http://diariodepontevedra.galiciae.com/noticias/rss")));

        publisherDAO.insert(new Publisher("en", "Expansion", "http://www.expansion.com/",
                "http://www.expansion.com/rss/", "rss", null));
        publisherDAO.insert(new Publisher("en", "Inversión & Finanzas", "http://www.finanzas.com/",
                "http://www.finanzas.com/rss/index.html", "rss", null));
        publisherDAO.insert(new Publisher("en", "El Economista", "http://www.eleconomista.es/",
                "http://www.eleconomista.es/rss/index.php", "rss", null));

        publisherDAO.insert(new Publisher("en", "eldiario.es", "http://www.eldiario.es/",
                "http://www.eldiario.es/Feeds.html", "rss", null));
        publisherDAO.insert(new Publisher("en", "Público", "http://www.publico.es/", null, "rss",
                Arrays.asList("http://www.publico.es/rss/", "http://www.publico.es/rss/internacional",
                        "http://www.publico.es/rss/espana", "http://www.publico.es/rss/economia",
                        "http://www.publico.es/rss/ciencias", "http://www.publico.es/rss/culturas",
                        "http://www.publico.es/rss/deportes", "http://www.publico.es/rss/gente")));

        publisherDAO.insert(new Publisher("en", "BOE", "https://www.boe.es/", "https://www.boe.es/rss/", "rss", null));

        publisherDAO.insert(new Publisher("en", "Marca", "http://www.marca.com/", "http://www.marca.com/deporte/rss/",
                "rss", null));
        publisherDAO.insert(new Publisher("en", "Diario AS", "http://as.com/", "http://as.com/rss/", "rss", null));
        publisherDAO.insert(new Publisher("en", "Diario Sport", "http://www.sport.es/es/",
                "http://www.sport.es/es/rss/", "rss", null));
        publisherDAO.insert(new Publisher("en", "Mundo Deportivo", "http://www.mundodeportivo.com/",
                "http://www.mundodeportivo.com/20080115/noticias-rss_53429321533.html", "rss", null));
        publisherDAO.insert(new Publisher("en", "Dxt Campeón", "http://www.dxtcampeon.com/",
                "http://www.dxtcampeon.com/rss/listado", "rss", null));

        publisherDAO.insert(new Publisher("en", "Rokambol", "http://rokambol.com/", null, "rss",
                Arrays.asList("http://feeds.feedburner.com/rokambol/SOUo")));
        publisherDAO.insert(new Publisher("en", "Huffington Post", "http://www.huffingtonpost.es/", null, "rss",
                Arrays.asList("http://www.huffingtonpost.es/feeds/index.xml")));

        publisherDAO.insert(new Publisher("en", "Telva", "http://www.telva.com/", "http://www.telva.com/estaticas/rss/",
                "rss", null));
        publisherDAO.insert(new Publisher("en", "Car and Driver", "http://www.caranddriverthef1.com/",
                "http://www.caranddriverthef1.com/informacion/rss", "rss", null));
        publisherDAO.insert(new Publisher("en", "Tiempo", "http://www.tiempodehoy.com/",
                "http://www.tiempodehoy.com/servicios/rss", null, null));

        publisherDAO.insert(
                new Publisher("en", "RT", "https://actualidad.rt.com/", "https://actualidad.rt.com/feed", "rss", null));
    }
}

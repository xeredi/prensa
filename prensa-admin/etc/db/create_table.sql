DROP TABLE IF EXISTS category_ctgr;

CREATE TABLE category_ctgr(
	ctgr_pk INTEGER PRIMARY KEY
	, ctgr_name VARCHAR(20)
	, ctgr_icon VARCHAR(20)
);

INSERT INTO category_ctgr(ctgr_pk, ctgr_name, ctgr_icon) VALUES (1, 'Radio', 'musical-notes');
INSERT INTO category_ctgr(ctgr_pk, ctgr_name, ctgr_icon) VALUES (2, 'Prensa General', 'paper');
INSERT INTO category_ctgr(ctgr_pk, ctgr_name, ctgr_icon) VALUES (3, 'Prensa Digital', 'finger-print');
INSERT INTO category_ctgr(ctgr_pk, ctgr_name, ctgr_icon) VALUES (4, 'Prensa Internacional', 'globe');
INSERT INTO category_ctgr(ctgr_pk, ctgr_name, ctgr_icon) VALUES (5, 'Deportes', 'football');
INSERT INTO category_ctgr(ctgr_pk, ctgr_name, ctgr_icon) VALUES (6, 'Economia', 'logo-usd');
INSERT INTO category_ctgr(ctgr_pk, ctgr_name, ctgr_icon) VALUES (7, 'Cultura y Ciencia', 'logo-android');
INSERT INTO category_ctgr(ctgr_pk, ctgr_name, ctgr_icon) VALUES (8, 'Viajes', 'paper-plane');
INSERT INTO category_ctgr(ctgr_pk, ctgr_name, ctgr_icon) VALUES (9, 'Motor', 'car');
INSERT INTO category_ctgr(ctgr_pk, ctgr_name, ctgr_icon) VALUES (10, 'Corazón', 'heart');




DROP TABLE IF EXISTS publisher_pblr;

CREATE TABLE publisher_pblr(
	pblr_pk INTEGER PRIMARY KEY AUTOINCREMENT
	, pblr_ctgr_pk INTEGER
	, pblr_lang VARCHAR(2)
	, pblr_country VARCHAR(2)
	, pblr_name VARCHAR(50)
	, pblr_webType VARCHAR(5)
	, pblr_webUrl VARCHAR(200)
	, pblr_logoUrl VARCHAR(200)
);

INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webType, pblr_webUrl, pblr_logoUrl) VALUES (
	7, 'es', 'ES', 'Muy Interesante', 'feed', 'http://feeds.feedburner.com/Muyinteresantees', 'https://upload.wikimedia.org/wikipedia/commons/thumb/a/ab/Muy_Interesante_logo.jpg/1024px-Muy_Interesante_logo.jpg');
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webType, pblr_webUrl, pblr_logoUrl) VALUES (
	7, 'es', 'ES', 'Muy Historia', 'feed', 'https://www.muyhistoria.es/rss', 'https://estaticos.muyhistoria.es/images/muy_historia_logo_.png');
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webType, pblr_webUrl, pblr_logoUrl) VALUES (
	7, 'es', 'ES', 'National Geographic', 'feed', 'http://www.nationalgeographic.com.es/feeds/rss.html', 'https://upload.wikimedia.org/wikipedia/commons/thumb/6/6a/National-Geographic-Logo.svg/800px-National-Geographic-Logo.svg.png');
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webType, pblr_webUrl, pblr_logoUrl) VALUES (
	9, 'es', 'ES', 'Motociclismo', 'feed', 'http://api.motorpress-iberica.es/rss/motociclismo', 'http://motorpress.suscripcionesrevistas.es/img/header/logo-motorpress.svg');
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webType, pblr_webUrl, pblr_logoUrl) VALUES (
	10, 'es', 'ES', 'Hola!', 'feed', 'http://www.hola.com/rss.xml', 'http://www.hola.com/imagenes/noticias-de-actualidad/2012/08/13/hola-revista.jpg');
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webType, pblr_webUrl, pblr_logoUrl) VALUES (
	6, 'es', 'ES', 'Expansion', 'html', 'http://www.expansion.com/rss/', 'http://e01-expansion.uecdn.es/assets/desktop/master/img/logos/logo_expansion_portada.png');
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webType, pblr_webUrl, pblr_logoUrl) VALUES (
	5, 'es', 'ES', 'Marca', 'html', 'http://www.marca.com/deporte/rss/', 'http://e00-marca.uecdn.es/assets/v6/img/logo-marca.png');
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webType, pblr_webUrl, pblr_logoUrl) VALUES (
	5, 'es', 'ES', 'Sport', 'html', 'http://www.sport.es/es/rss/', 'http://www.sport.es/img/logo.png');
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webType, pblr_webUrl, pblr_logoUrl) VALUES (
	5, 'es', 'ES', 'AS', 'html', 'https://as.com/rss/', 'https://upload.wikimedia.org/wikipedia/commons/thumb/5/51/Logo_diario_AS.svg/640px-Logo_diario_AS.svg.png');
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webType, pblr_webUrl, pblr_logoUrl) VALUES (
	4, 'es', 'ES', 'New York Times', 'html', 'http://www.nytimes.com/services/xml/rss/index.html', 'https://static1.squarespace.com/static/547fad3be4b01c408c292fe5/55524dd7e4b092ca32b26354/55524dd8e4b0c14095c83e2c/1431457287081/the-new-york-times-logo-vert.png?format=300w');
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webType, pblr_webUrl, pblr_logoUrl) VALUES (
	3, 'es', 'ES', 'Huffington Post', 'feed', 'http://www.huffingtonpost.es/feeds/index.xml', 'https://upload.wikimedia.org/wikipedia/commons/thumb/9/9d/The_Huffington_Post_logo.svg/640px-The_Huffington_Post_logo.svg.png');
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webType, pblr_webUrl, pblr_logoUrl) VALUES (
	3, 'es', 'ES', 'Europa Press', 'html', 'http://www.europapress.es/contenidosrss.aspx', 'https://upload.wikimedia.org/wikipedia/commons/thumb/6/62/Logo_Europa_Press.jpeg/245px-Logo_Europa_Press.jpeg');
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webType, pblr_webUrl, pblr_logoUrl) VALUES (
	3, 'es', 'ES', 'Diario.es', 'html', 'http://www.eldiario.es/Feeds.html', 'http://images.eldiario.es/sociedad/Logo-nuevo-eldiarioes_EDIIMA20140915_0671_14.jpg');
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webType, pblr_webUrl, pblr_logoUrl) VALUES (
	1, 'es', 'ES', 'Radio 3', 'html', 'http://www.rtve.es/programas_radio3/rss.php', 'https://upload.wikimedia.org/wikipedia/commons/2/23/Radio3_RGB_POS-01.jpg');
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webType, pblr_webUrl, pblr_logoUrl) VALUES (
	1, 'es', 'ES', 'Onda Cero', 'html', 'http://www.ondacero.es/podcast/', 'https://upload.wikimedia.org/wikipedia/commons/thumb/b/bf/Onda_Cero_logo.svg/225px-Onda_Cero_logo.svg.png');
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webType, pblr_webUrl, pblr_logoUrl) VALUES (
	1, 'es', 'ES', 'Cadena Ser', 'html', 'http://cadenaser.com/ser/podcasts/', 'https://upload.wikimedia.org/wikipedia/commons/thumb/2/28/Cadena_Ser_logo.svg/600px-Cadena_Ser_logo.svg.png');
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webType, pblr_webUrl, pblr_logoUrl) VALUES (
	2, 'es', 'ES', 'El Mundo', 'html', 'http://rss.elmundo.es/rss/', 'http://www.brandemia.org/sites/default/files/sites/default/files/logo_el_mundo.jpg');
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webType, pblr_webUrl, pblr_logoUrl) VALUES (
	2, 'es', 'ES', 'El Pais', 'html', 'https://servicios.elpais.com/rss/', 'https://upload.wikimedia.org/wikipedia/commons/thumb/3/3e/El_Pais_logo_2007.svg/578px-El_Pais_logo_2007.svg.png');
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webType, pblr_webUrl, pblr_logoUrl) VALUES (
	2, 'es', 'ES', 'El Periodico', 'html', 'http://www.elperiodico.com/es/rss/portada_rss.shtml', 'https://upload.wikimedia.org/wikipedia/commons/4/4f/El_Peri%C3%B3dico_de_Catalunya_newspaper_logo.PNG');






DROP TABLE IF EXISTS feed_feed;

CREATE TABLE feed_feed(
	feed_pk INTEGER PRIMARY KEY AUTOINCREMENT
	, feed_pblr_pk INTEGER
	, feed_url VARCHAR(200)
	, feed_author VARCHAR(50)
	, feed_copyright VARCHAR(50)
	, feed_description VARCHAR(200)
	, feed_encoding VARCHAR(20)
	, feed_feedType VARCHAR(20)
	, feed_generator VARCHAR(50)
	, feed_language VARCHAR(2)
	, feed_link VARCHAR(200)
	, feed_publishedDate DATETIME
	, feed_title VARCHAR(200)
	, feed_uri VARCHAR(200)
	, feed_imUrl VARCHAR(200)
	, feed_imHeight INTEGER
	, feed_imWidth INTEGER
	, feed_podcast BOOLEAN
	, feed_subtitle VARCHAR(200)

	, CONSTRAINT uq_feed_url UNIQUE (feed_url)
);



DROP TABLE IF EXISTS follow_feed_flfd;

CREATE TABLE IF NOT EXISTS follow_feed_flfd(
	flfd_feed_pk INTEGER PRIMARY KEY
);

INSERT INTO follow_feed_flfd(flfd_feed_pk) VALUES (1);

SELECT
	feed_pk, feed_pblr_pk, feed_url, feed_author, feed_copyright, feed_description, feed_encoding, feed_feedType, feed_generator, feed_language
	, feed_link, feed_publishedDate, feed_title, feed_uri, feed_imUrl, feed_imHeight, feed_imWidth, feed_podcast, feed_subtitle
	, (
		SELECT 1 FROM follow_feed_flfd
		WHERE flfd_feed_pk = feed_pk
	) AS feed_followed
FROM feed_feed
WHERE EXISTS (
	SELECT 1 FROM follow_feed_flfd
	WHERE flfd_feed_pk = feed_pk
)
;

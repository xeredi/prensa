DROP TABLE IF EXISTS category_ctgr;

CREATE TABLE category_ctgr(
	ctgr_pk INTEGER PRIMARY KEY
	, ctgr_name VARCHAR(20)
	, ctgr_icon VARCHAR(20)
);

INSERT INTO category_ctgr(ctgr_pk, ctgr_name, ctgr_icon) VALUES (
	1, 'Radio', 'musical-notes'
);
INSERT INTO category_ctgr(ctgr_pk, ctgr_name, ctgr_icon) VALUES (
	2, 'Prensa General', 'paper'
);
INSERT INTO category_ctgr(ctgr_pk, ctgr_name, ctgr_icon) VALUES (
	3, 'Prensa Digital', 'finger-print'
);
INSERT INTO category_ctgr(ctgr_pk, ctgr_name, ctgr_icon) VALUES (
	4, 'Prensa Internacional', 'globe'
);
INSERT INTO category_ctgr(ctgr_pk, ctgr_name, ctgr_icon) VALUES (
	5, 'Deportes', 'football'
);
INSERT INTO category_ctgr(ctgr_pk, ctgr_name, ctgr_icon) VALUES (
	6, 'Economia', 'logo-usd'
);
INSERT INTO category_ctgr(ctgr_pk, ctgr_name, ctgr_icon) VALUES (
	7, 'Cultura y Ciencia', 'logo-android'
);
INSERT INTO category_ctgr(ctgr_pk, ctgr_name, ctgr_icon) VALUES (
	8, 'Viajes', 'paper-plane'
);
INSERT INTO category_ctgr(ctgr_pk, ctgr_name, ctgr_icon) VALUES (
	9, 'Motor', 'car'
);
INSERT INTO category_ctgr(ctgr_pk, ctgr_name, ctgr_icon) VALUES (
	10, 'Corazón', 'heart'
);




DROP TABLE IF EXISTS publisher_pblr;

CREATE TABLE publisher_pblr(
	pblr_pk INTEGER PRIMARY KEY AUTOINCREMENT
	, pblr_ctgr_pk INTEGER
	, pblr_lang VARCHAR(2)
	, pblr_country VARCHAR(2)
	, pblr_name VARCHAR(50)
	, pblr_webUrl VARCHAR(200)
	, pblr_logoUrl VARCHAR(200)
);

INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webUrl, pblr_logoUrl) VALUES (
	5, 'es', 'ES', 'Marca', 'http://www.marca.com/deporte/rss/', 'http://e00-marca.uecdn.es/assets/v6/img/logo-marca.png'
);
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webUrl, pblr_logoUrl) VALUES (
	5, 'es', 'ES', 'Sport', 'http://www.sport.es/es/rss/', 'http://www.sport.es/img/logo.png'
);
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webUrl, pblr_logoUrl) VALUES (
	1, 'es', 'ES', 'Onda Cero', 'http://www.ondacero.es/podcast/', 'https://upload.wikimedia.org/wikipedia/commons/thumb/b/bf/Onda_Cero_logo.svg/225px-Onda_Cero_logo.svg.png'
);
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webUrl, pblr_logoUrl) VALUES (
	1, 'es', 'ES', 'Cadena Ser', 'http://cadenaser.com/ser/podcasts/', 'https://upload.wikimedia.org/wikipedia/commons/thumb/2/28/Cadena_Ser_logo.svg/600px-Cadena_Ser_logo.svg.png'
);
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webUrl, pblr_logoUrl) VALUES (
	2, 'es', 'ES', 'El Pais', 'https://servicios.elpais.com/rss/', 'https://upload.wikimedia.org/wikipedia/commons/thumb/3/3e/El_Pais_logo_2007.svg/578px-El_Pais_logo_2007.svg.png'
);
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webUrl, pblr_logoUrl) VALUES (
	3, 'es', 'ES', 'Diario.es', 'http://www.eldiario.es/Feeds.html', 'http://images.eldiario.es/sociedad/Logo-nuevo-eldiarioes_EDIIMA20140915_0671_14.jpg'
);






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

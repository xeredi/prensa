DROP TABLE IF EXISTS publisher_pblr;

CREATE TABLE publisher_pblr(
	pblr_pk INTEGER PRIMARY KEY AUTOINCREMENT
	, pblr_lang VARCHAR(2)
	, pblr_country VARCHAR(2)
	, pblr_name VARCHAR(50)
	, pblr_webUrl VARCHAR(200)
	, pblr_logoUrl VARCHAR(200)
);

INSERT INTO publisher_pblr(pblr_lang, pblr_country, pblr_name, pblr_webUrl, pblr_logoUrl) VALUES (
	'es', 'ES', 'Marca', 'http://www.marca.com/deporte/rss/', 'http://e00-marca.uecdn.es/assets/v6/img/logo-marca.png'
);
INSERT INTO publisher_pblr(pblr_lang, pblr_country, pblr_name, pblr_webUrl, pblr_logoUrl) VALUES (
	'es', 'ES', 'Sport', 'http://www.sport.es/es/rss/', 'http://www.sport.es/img/logo.png'
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

	, CONSTRAINT uq_feed_url UNIQUE (feed_url)
);


	/** The published date. */
	private Date publishedDate;

	/** The title. */
	private String title;

	/** The uri. */
	private String uri;

	/** The im url. */
	private String imUrl;

	/** The im height. */
	private Integer imHeight;

	/** The im width. */
	private Integer imWidth;

	/** The podcast. */
	private Boolean podcast;

	/** The subtitle. */
	private String subtitle;



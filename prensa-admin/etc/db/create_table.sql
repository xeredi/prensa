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
	1, 'en', 'ES', 'Cadena Ser', 'ivoox', 'https://www.ivoox.com/escuchar-cadena-ser_nq_166_1.html', 'https://static-1.ivooxcdn.com/canales/0/8/5/6/461459066580_MD.jpg');
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webType, pblr_webUrl, pblr_logoUrl) VALUES (
	1, 'en', 'ES', 'Onda Cero', 'ivoox', 'https://www.ivoox.com/escuchar-onda-cero_nq_343_1.html', 'https://static-2.ivooxcdn.com/canales/9/5/8/3/4091471443859_MD.jpg');
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webType, pblr_webUrl, pblr_logoUrl) VALUES (
	1, 'en', 'ES', 'Radio 3', 'ivoox', 'https://www.ivoox.com/escuchar-radio-3-rne_nq_403_1.html', 'https://upload.wikimedia.org/wikipedia/commons/2/23/Radio3_RGB_POS-01.jpg');
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webType, pblr_webUrl, pblr_logoUrl) VALUES (
	1, 'en', 'ES', 'COPE', 'ivoox', 'https://www.ivoox.com/escuchar-cope_nq_158_1.html', 'https://upload.wikimedia.org/wikipedia/commons/1/1f/Logo_Cadena_COPE.png');
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webType, pblr_webUrl, pblr_logoUrl) VALUES (
	1, 'en', 'ES', 'Europa FM', 'ivoox', 'https://www.ivoox.com/escuchar-online-europa-fm_tw_67_1.html', 'https://static-2.ivooxcdn.com/radios/9/9/8/5/4251450355899_MD.jpg');
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webType, pblr_webUrl, pblr_logoUrl) VALUES (
	1, 'en', 'ES', 'Los 40 Principales', 'ivoox', 'https://www.ivoox.com/escuchar-40-principales_nq_1469_1.html', 'https://static-2.ivooxcdn.com/canales/1/5/7/8/481473348751_MD.jpg');
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webType, pblr_webUrl, pblr_logoUrl) VALUES (
	1, 'en', 'ES', 'Cadena 100', 'ivoox', 'https://www.ivoox.com/escuchar-cadena-100_nq_1494_1.html', 'https://static-2.ivooxcdn.com/canales/9/9/1/3/5541471443199_MD.jpg');
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webType, pblr_webUrl, pblr_logoUrl) VALUES (
	1, 'en', 'ES', 'Canal Sur Radio', 'ivoox', 'https://www.ivoox.com/escuchar-canal-sur-radio_nq_47618_1.html', 'https://static-2.ivooxcdn.com/canales/7/7/0/1/7861471421077_MD.jpg');
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webType, pblr_webUrl, pblr_logoUrl) VALUES (
	1, 'en', 'ES', 'esRadio', 'ivoox', 'https://www.ivoox.com/escuchar-esradio_nq_1421_1.html', 'https://static-2.ivooxcdn.com/canales/7/5/2/3/4111471443257_MD.jpg');
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webType, pblr_webUrl, pblr_logoUrl) VALUES (
	1, 'en', 'ES', 'Catalunya Radio', 'ivoox', 'https://www.ivoox.com/escuchar-catalunya-radio_nq_264_1.html', 'https://static-1.ivooxcdn.com/canales/4/7/1/5/2831473765174_MD.jpg');
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webType, pblr_webUrl, pblr_logoUrl) VALUES (
	1, 'en', 'ES', 'Radio Euskadi', 'ivoox', 'https://www.ivoox.com/escuchar-eitb-radio-euskadi_nq_23611_1.html', 'https://static-1.ivooxcdn.com/canales/2/9/8/3/4901471433892_MD.jpg');
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webType, pblr_webUrl, pblr_logoUrl) VALUES (
	1, 'en', 'ES', 'EiTB - Euskadi Irratia', 'ivoox', 'https://www.ivoox.com/escuchar-eitb-euskadi-irratia_nq_35102_1.html', 'https://static-2.ivooxcdn.com/canales/9/3/8/7/71471427839_MD.jpg');
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webType, pblr_webUrl, pblr_logoUrl) VALUES (
	1, 'en', 'ES', 'Canal Fiesta Radio', 'ivoox', 'https://www.ivoox.com/escuchar-canal-fiesta-radio_nq_4111_1.html', 'https://static-2.ivooxcdn.com/canales/5/2/1/2/6871471442125_MD.jpg');
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webType, pblr_webUrl, pblr_logoUrl) VALUES (
	1, 'en', 'ES', 'Radio Nacional de España', 'ivoox', 'https://www.ivoox.com/escuchar-radio-nacional-espana-rne_nq_191_1.html', 'https://static-2.ivooxcdn.com/canales/9/2/9/3/9671471443929_MD.jpg');
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webType, pblr_webUrl, pblr_logoUrl) VALUES (
	1, 'en', 'ES', 'Rock FM', 'ivoox', 'https://www.ivoox.com/escuchar-rock-fm-antes-rock-and-gol_nq_382_1.html', 'https://static-2.ivooxcdn.com/canales/1/8/8/3/201471443881_MD.jpg');
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webType, pblr_webUrl, pblr_logoUrl) VALUES (
	1, 'en', 'ES', 'Carne Cruda.es', 'ivoox', 'https://www.ivoox.com/escuchar-carne-cruda-es_nq_90952_1.html', 'https://static-1.ivooxcdn.com/canales/2/9/8/6/1511471416892_MD.jpg');
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webType, pblr_webUrl, pblr_logoUrl) VALUES (
	1, 'en', 'ES', 'Vaughan Radio', 'ivoox', 'https://www.ivoox.com/escuchar-vaughan_nq_255_1.html', 'https://static-2.ivooxcdn.com/canales/1/3/9/3/5711471443931_MD.jpg');
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webType, pblr_webUrl, pblr_logoUrl) VALUES (
	1, 'en', 'ES', 'Radio Marca', 'html', 'http://www.marca.com/radio/rss.html', 'http://d2jljza7x0a5yy.cloudfront.net/media/k2/items/cache/23ddb2f5acb26c69184f51b6023afcfb_XL.jpg');
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webType, pblr_webUrl, pblr_logoUrl) VALUES (
	7, 'en', 'ES', 'Muy Interesante', 'feed', 'http://feeds.feedburner.com/Muyinteresantees', 'https://upload.wikimedia.org/wikipedia/commons/thumb/a/ab/Muy_Interesante_logo.jpg/1024px-Muy_Interesante_logo.jpg');
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webType, pblr_webUrl, pblr_logoUrl) VALUES (
	7, 'en', 'ES', 'Muy Historia', 'feed', 'https://www.muyhistoria.es/rss', 'https://estaticos.muyhistoria.es/images/muy_historia_logo_.png');
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webType, pblr_webUrl, pblr_logoUrl) VALUES (
	7, 'en', 'ES', 'National Geographic', 'feed', 'http://www.nationalgeographic.com.es/feeds/rss.html', 'https://upload.wikimedia.org/wikipedia/commons/thumb/6/6a/National-Geographic-Logo.svg/800px-National-Geographic-Logo.svg.png');
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webType, pblr_webUrl, pblr_logoUrl) VALUES (
	7, 'en', 'ES', 'Wired', 'html', 'https://www.wired.com/about/rss_feeds/', 'https://www.wired.com/images_blogs/underwire/images/2007/04/17/wired_logo.gif');
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webType, pblr_webUrl, pblr_logoUrl) VALUES (
	7, 'en', 'ES', 'Xataka', 'feed', 'https://www.xataka.com/index.xml', 'https://i.blogs.es/0558dc/logo_xataka2/original.jpg');
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webType, pblr_webUrl, pblr_logoUrl) VALUES (
	7, 'en', 'ES', 'Microsiervos', 'feed', 'http://www.microsiervos.com/index.xml', 'http://img.microsiervos.com/blog/logo-microsiervos.png');
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webType, pblr_webUrl, pblr_logoUrl) VALUES (
	9, 'en', 'ES', 'Motociclismo', 'feed', 'http://api.motorpress-iberica.es/rss/motociclismo', 'http://motorpress.suscripcionesrevistas.es/img/header/logo-motorpress.svg');
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webType, pblr_webUrl, pblr_logoUrl) VALUES (
	9, 'en', 'ES', 'Autopista', 'feed', 'http://api.motorpress-iberica.es/rss/autopista/', 'http://www.motorpress-iberica.es/img/publicaciones/logo-autopista.jpg');
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webType, pblr_webUrl, pblr_logoUrl) VALUES (
	9, 'en', 'ES', 'Motor16', 'feed', 'http://www.motor16.com/rss/feed.xml', 'https://pbs.twimg.com/profile_images/468399778078789632/nzPwtASh.png');
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webType, pblr_webUrl, pblr_logoUrl) VALUES (
	10, 'en', 'ES', 'Hola!', 'feed', 'http://www.hola.com/rss.xml', 'http://www.hola.com/imagenes/noticias-de-actualidad/2012/08/13/hola-revista.jpg');
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webType, pblr_webUrl, pblr_logoUrl) VALUES (
	10, 'en', 'ES', 'Lecturas', 'feed', 'http://www.lecturas.com/feeds/rss.html', 'http://www.otorrinomadrid.com.es/site/wp-content/uploads/2017/06/logo-fb.png');
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webType, pblr_webUrl, pblr_logoUrl) VALUES (
	10, 'en', 'ES', 'Diez Minutos', 'html', 'http://www.diezminutos.es/informacion/a1145/rss-feeds/', 'https://pbs.twimg.com/profile_images/140643032/LOGO_400x400.jpg');
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webType, pblr_webUrl, pblr_logoUrl) VALUES (
	10, 'en', 'ES', 'Que Me Dices!', 'html', 'http://quemedices.diezminutos.es/listado_rss', 'https://yt3.ggpht.com/-Q25tIp7sW3o/AAAAAAAAAAI/AAAAAAAAAAA/qF4eOQyjJSw/s900-c-k-no-mo-rj-c0xffffff/photo.jpg');
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webType, pblr_webUrl, pblr_logoUrl) VALUES (
	10, 'en', 'ES', 'Mia', 'feed', 'https://www.miarevista.es/rss', 'https://pbs.twimg.com/profile_images/559745014578675712/rMOmjERX.jpeg');
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webType, pblr_webUrl, pblr_logoUrl) VALUES (
	10, 'en', 'ES', 'Love', 'feed', 'http://www.revistalove.es/feed/', 'http://www.revistalove.es/wp-content/themes/trendyblog-theme/images/revistalove.png');
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webType, pblr_webUrl, pblr_logoUrl) VALUES (
	10, 'en', 'ES', 'Vogue', 'feed', 'http://www.vogue.es/home.xml', 'http://www.vogue.com/wp-content/themes/vogue/images/logo_vogue.svg');
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webType, pblr_webUrl, pblr_logoUrl) VALUES (
	6, 'en', 'ES', 'Expansion', 'html', 'http://www.expansion.com/rss/', 'http://e01-expansion.uecdn.es/assets/desktop/master/img/logos/logo_expansion_portada.png');
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webType, pblr_webUrl, pblr_logoUrl) VALUES (
	6, 'en', 'ES', 'Emprendedores', 'feed', 'http://www.emprendedores.es/rss/feed/site', 'http://logicaecommerce.com/wp-content/uploads/2016/03/revista-emprendedores.jpg');
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webType, pblr_webUrl, pblr_logoUrl) VALUES (
	6, 'en', 'ES', 'Cinco Dias', 'html', 'https://cincodias.elpais.com/estaticos/rss/', 'http://www.gedeth.com/web/wp-content/uploads/2016/02/LOGO-Cinco-D%C3%ADas.png');
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webType, pblr_webUrl, pblr_logoUrl) VALUES (
	6, 'en', 'ES', 'El Blog Salmon', 'feed', 'https://www.elblogsalmon.com/index.xml', 'http://www.focus-economics.com/sites/default/files/wysiwyg_images/el_blog_salmon.png');
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webType, pblr_webUrl, pblr_logoUrl) VALUES (
	5, 'en', 'ES', 'Marca', 'html', 'http://www.marca.com/deporte/rss/', 'http://e00-marca.uecdn.es/assets/v6/img/logo-marca.png');
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webType, pblr_webUrl, pblr_logoUrl) VALUES (
	5, 'en', 'ES', 'Sport', 'html', 'http://www.sport.es/es/rss/', 'http://www.sport.es/img/logo.png');
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webType, pblr_webUrl, pblr_logoUrl) VALUES (
	5, 'en', 'ES', 'AS', 'html', 'https://as.com/rss/', 'https://upload.wikimedia.org/wikipedia/commons/thumb/5/51/Logo_diario_AS.svg/640px-Logo_diario_AS.svg.png');
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webType, pblr_webUrl, pblr_logoUrl) VALUES (
	4, 'en', 'ES', 'New York Times', 'html', 'http://www.nytimes.com/services/xml/rss/index.html', 'https://static1.squarespace.com/static/547fad3be4b01c408c292fe5/55524dd7e4b092ca32b26354/55524dd8e4b0c14095c83e2c/1431457287081/the-new-york-times-logo-vert.png?format=300w');
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webType, pblr_webUrl, pblr_logoUrl) VALUES (
	4, 'en', 'ES', 'Bild', 'html', 'http://www.bild.de/corporate-site/rss-infoseite/bild-service/rss-3257128.bild.html', 'http://bilder.bild.de/fotos/bild-logo-35166394/Bild/32.bild.png');
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webType, pblr_webUrl, pblr_logoUrl) VALUES (
	4, 'en', 'ES', 'Le Monde', 'html', 'http://www.lemonde.fr/rss/', 'https://upload.wikimedia.org/wikipedia/commons/thumb/4/43/Le_Monde.svg/1280px-Le_Monde.svg.png');
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webType, pblr_webUrl, pblr_logoUrl) VALUES (
	4, 'en', 'ES', 'BBC (en español)', 'html', 'http://www.bbc.com/mundo/lg/servicios/2009/03/000000_rss.shtml', 'http://m.files.bbci.co.uk/modules/bbc-morph-news-waf-page-meta/1.2.0/bbc_news_logo.png?cb=1');
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webType, pblr_webUrl, pblr_logoUrl) VALUES (
	4, 'en', 'ES', 'Reuters', 'html', 'http://www.reuters.com/tools/rss', 'http://s3.reutersmedia.net/resources/r/?m=02&d=20170131&t=2&i=1170902432&w=780&fh=&fw=&ll=&pl=&sq=&r=LYNXMPED0U165');
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webType, pblr_webUrl, pblr_logoUrl) VALUES (
	3, 'en', 'ES', 'Huffington Post', 'feed', 'http://www.huffingtonpost.es/feeds/index.xml', 'https://upload.wikimedia.org/wikipedia/commons/thumb/9/9d/The_Huffington_Post_logo.svg/640px-The_Huffington_Post_logo.svg.png');
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webType, pblr_webUrl, pblr_logoUrl) VALUES (
	3, 'en', 'ES', 'Publico', 'html', '/home/xeredi/git/prensa/prensa-admin/etc/web/RSS en publico.es _ Publico.html', 'http://blogs.publico.es/logo-publico.png');
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webType, pblr_webUrl, pblr_logoUrl) VALUES (
	3, 'en', 'ES', 'Europa Press', 'html', 'http://www.europapress.es/contenidosrss.aspx', 'https://upload.wikimedia.org/wikipedia/commons/thumb/6/62/Logo_Europa_Press.jpeg/245px-Logo_Europa_Press.jpeg');
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webType, pblr_webUrl, pblr_logoUrl) VALUES (
	3, 'en', 'ES', 'Diario.es', 'html', 'http://www.eldiario.es/Feeds.html', 'http://images.eldiario.es/sociedad/Logo-nuevo-eldiarioes_EDIIMA20140915_0671_14.jpg');
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webType, pblr_webUrl, pblr_logoUrl) VALUES (
	3, 'en', 'ES', 'La Marea', 'feed', 'http://www.lamarea.com/feed/', 'http://www.lamarea.com/wp-content/uploads/clubamigos/img/logo_popup.png');
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webType, pblr_webUrl, pblr_logoUrl) VALUES (
	3, 'en', 'ES', 'CTXT', 'feed', 'http://ctxt.es/es/?tpl=87', 'http://ctxt.es/themes/publication_1/theme_1/img/logo.png');
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webType, pblr_webUrl, pblr_logoUrl) VALUES (
	3, 'en', 'ES', 'El Confidencial', 'feed', 'http://www.elconfidencial.com/rss/', 'http://www.ecestaticos.com/file/0be042580f4f0f53813bd70f7984033c/1456834011.svg');
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webType, pblr_webUrl, pblr_logoUrl) VALUES (
	2, 'en', 'ES', 'El Mundo', 'html', 'http://rss.elmundo.es/rss/', 'http://www.brandemia.org/sites/default/files/sites/default/files/logo_el_mundo.jpg');
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webType, pblr_webUrl, pblr_logoUrl) VALUES (
	2, 'en', 'ES', 'El Pais', 'html', 'https://servicios.elpais.com/rss/', 'https://upload.wikimedia.org/wikipedia/commons/thumb/3/3e/El_Pais_logo_2007.svg/578px-El_Pais_logo_2007.svg.png');
INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webType, pblr_webUrl, pblr_logoUrl) VALUES (
	2, 'en', 'ES', 'El Periodico', 'html', 'http://www.elperiodico.com/es/rss/portada_rss.shtml', 'https://upload.wikimedia.org/wikipedia/commons/4/4f/El_Peri%C3%B3dico_de_Catalunya_newspaper_logo.PNG');






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

DROP TABLE IF EXISTS item_item;

CREATE TABLE IF NOT EXISTS item_item(
	item_pk INTEGER PRIMARY KEY
	, item_pblr_pk INTEGER
	, item_link VARCHAR(200)
	, item_pubDate DATETIME
	, item_readDate DATETIME
	, item_thumbnailUrl VARCHAR(200)
	, item_imUrl VARCHAR(200)
	, item_enclosureUrl VARCHAR(200)
	, item_enclosureLength INTEGER
	, item_author VARCHAR(50)
	, item_title VARCHAR(200)
	, item_description VARCHAR(200)

	, CONSTRAINT uq_item_link UNIQUE (item_pblr_pk, item_link)
);

DROP TABLE IF EXISTS item_feed_itfd;

CREATE TABLE IF NOT EXISTS item_feed_itfd(
	itfd_item_pk INTEGER
	, itfd_feed_pk INTEGER

	, CONSTRAINT uq_itfd UNIQUE (itfd_item_pk, itfd_feed_pk)
);


DROP TABLE IF EXISTS follow_feed_flfd;

CREATE TABLE IF NOT EXISTS follow_feed_flfd(
	flfd_feed_pk INTEGER PRIMARY KEY
	, flfd_lastRead DATETIME
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

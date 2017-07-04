package xeredi.press.model;

import java.util.Date;

import lombok.Data;

// TODO: Auto-generated Javadoc
/**
 * Instantiates a new feed.
 */
@Data
public class Feed {

	/** The id. */
	private Long id;

	/** The pblr id. */
	private Long pblrId;

	/** The url. */
	private String url;

	/** The author. */
	private String author;

	/** The copyright. */
	private String copyright;

	/** The description. */
	private String description;

	/** The encoding. */
	private String encoding;

	/** The feed type. */
	private String feedType;

	/** The generator. */
	private String generator;

	/** The language. */
	private String language;

	/** The link. */
	private String link;

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
}

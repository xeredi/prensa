package xeredi.press.model;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

import lombok.Data;

// TODO: Auto-generated Javadoc
/**
 * Instantiates a new feed.
 */
public class Feed {

	/** The id. */
	private Long id;

	/** The pblr id. */
	private Publisher pblr;

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

	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Publisher getPblr() {
		return pblr;
	}

	public void setPblr(Publisher pblr) {
		this.pblr = pblr;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCopyright() {
		return copyright;
	}

	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public String getFeedType() {
		return feedType;
	}

	public void setFeedType(String feedType) {
		this.feedType = feedType;
	}

	public String getGenerator() {
		return generator;
	}

	public void setGenerator(String generator) {
		this.generator = generator;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Date getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getImUrl() {
		return imUrl;
	}

	public void setImUrl(String imUrl) {
		this.imUrl = imUrl;
	}

	public Integer getImHeight() {
		return imHeight;
	}

	public void setImHeight(Integer imHeight) {
		this.imHeight = imHeight;
	}

	public Integer getImWidth() {
		return imWidth;
	}

	public void setImWidth(Integer imWidth) {
		this.imWidth = imWidth;
	}

	public Boolean getPodcast() {
		return podcast;
	}

	public void setPodcast(Boolean podcast) {
		this.podcast = podcast;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
}

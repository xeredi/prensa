package xeredi.press.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import lombok.Data;

// TODO: Auto-generated Javadoc
/**
 * Instantiates a new publisher.
 */
public final class Publisher {

	/** The id. */
	private Long id;

	/** The ctgr id. */
	private Category ctgr;

	/** The country. */
	private String country;

	/** The language. */
	private String language;

	/** The name. */
	private String name;

	private String webType;

	/** The web url. */
	private String webUrl;

	/** The logo url. */
	private String logoUrl;

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

	public Category getCtgr() {
		return ctgr;
	}

	public void setCtgr(Category ctgr) {
		this.ctgr = ctgr;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWebType() {
		return webType;
	}

	public void setWebType(String webType) {
		this.webType = webType;
	}

	public String getWebUrl() {
		return webUrl;
	}

	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}
}

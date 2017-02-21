package xeredi.prensa.model;

import lombok.Data;

@Data
public final class Channel {
	private String name;

	private String description;

	private String url;

	private String language;

	private String ttl;
}

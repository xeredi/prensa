package xeredi.prensa.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public final class Cathegory {
	private String name;

	private final List<String> channelsList = new ArrayList<>();

	private final Map<String, Channel> channelsMap = new HashMap<>();
}

package xeredi.prensa.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public final class Publisher {
	private String name;

	private String countryCode;

	private Channel defaultChannel;

	private final List<String> cathegoriesList = new ArrayList<>();

	private final Map<String, Cathegory> cathegoriesMap = new HashMap<>();
}

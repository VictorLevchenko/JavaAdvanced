package introduction;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * Необходимо написать класс, который преобразуют длинные URL в короткие.
 * Например, http://it-simulator.com => http://sim.it/abcdef, где "abcdef" -
 * некоторый уникальный суффикс для каждого сгенерированного адреса. Он также
 * должен уметь преобразовывать короткие URL, которые он сгенерировал, обратно в
 * исходные длинные.
 */
public class ShortUrlBuilder {

	private List<String> urls = new ArrayList<>();
	private final String BASE_URL;
	private static ShortUrlBuilder shortUrlBuilder;

	private ShortUrlBuilder(String baseUrl) {
		BASE_URL = baseUrl;
	}

	/** base url like "http://it-com */
	public static ShortUrlBuilder getInstanse(String baseUrl) throws MalformedURLException {

		// check for valid url
		new URL(baseUrl);
		if (shortUrlBuilder == null) {
			shortUrlBuilder = new ShortUrlBuilder(baseUrl);
		}
		return shortUrlBuilder;
	}

	public String encode(String longUrl) {

		int index = urls.indexOf(longUrl);
		if (index == -1) {
			urls.add(longUrl);
			index = urls.size() - 1;
		}
		String prefix = Base64.getUrlEncoder().encodeToString(("" + index).getBytes());
		String shortUrl = BASE_URL + "/" + prefix;

		return shortUrl;
	}

	public String decode(String shortUrl) {

		int pos = shortUrl.lastIndexOf('/');
		int index = Integer
				.valueOf(new String(Base64.getUrlDecoder().decode(shortUrl.substring(pos + 1))));
		return urls.get(index);
	}

	public static void main(String[] args) {

		String longUrl = "http://it-discovery/index.html";
		String shortUrl = null;
		ShortUrlBuilder shortUrlBuilder = null;

		try {
			shortUrlBuilder = ShortUrlBuilder.getInstanse("http://it-com");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		System.out.println("Long url: " + longUrl);
		shortUrl = shortUrlBuilder.encode(longUrl);
		System.out.println("Short url: " + shortUrl);
		System.out.println("Restored url: " + shortUrlBuilder.decode(shortUrl));

		shortUrl = shortUrlBuilder.encode(longUrl);
		System.out.println("Short url: " + shortUrl);
		System.out.println(shortUrlBuilder.urls.size());

	}
}

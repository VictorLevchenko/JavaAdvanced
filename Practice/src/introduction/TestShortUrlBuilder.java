package introduction;

import java.net.MalformedURLException;

import org.junit.Assert;
import org.junit.Test;

public class TestShortUrlBuilder {
	public static final String BASE_URL = "http://it-com";
	public static final String longUrl = "http://it-discovery/index.html";

	@Test
	public void getInstanceSingletonSuccess() throws MalformedURLException {
		ShortUrlBuilder shortUrlBuilder1 = ShortUrlBuilder.getInstanse(BASE_URL);
		ShortUrlBuilder shortUrlBuilder2 = ShortUrlBuilder.getInstanse(BASE_URL);
		Assert.assertEquals("Class is not singleton", shortUrlBuilder1, shortUrlBuilder2);
	}

	@Test(expected = MalformedURLException.class)
	public void getInstanceSingletonException() throws MalformedURLException {
		ShortUrlBuilder.getInstanse("//http");
	}
	
	@Test
	public void encodeAndDecodeSuccess() throws MalformedURLException {
		ShortUrlBuilder shortUrlBuilder = ShortUrlBuilder.getInstanse(BASE_URL);
		String shortUrl = shortUrlBuilder.encode(longUrl);
		String restoredUrl = shortUrlBuilder.decode(shortUrl);
		Assert.assertEquals(longUrl, restoredUrl);
		
	}
}

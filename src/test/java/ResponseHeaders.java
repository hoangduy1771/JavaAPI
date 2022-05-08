import org.apache.http.Header;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.testng.annotations.Test;
import java.io.IOException;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class ResponseHeaders extends BaseClass {
    @Test
    public void contentType() throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT);
        response = client.execute(get);

        Header contentType = response.getEntity().getContentType();
        assertEquals("application/json; charset=utf-8", contentType.getValue());

        ContentType ct = ContentType.getOrDefault(response.getEntity());
        assertEquals(ct.getMimeType(), "application/json");
        assertEquals(ct.getCharset().toString(), "UTF-8");

    }

    @Test
    public void serverIsGithub() throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT);
        response = client.execute(get);
        String headerValue = RespondUtils.getHeader(response, "Server");
        assertEquals("GitHub.com", headerValue);
    }

    @Test
    public void xRateLimitIs60() throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT);
        response = client.execute(get);
        String headerValue = RespondUtils.getHeaderNew(response, "X-RateLimit-Limit");
        assertEquals("60", headerValue);
    }

    @Test
    public void eTagIsPresent() throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT);
        response = client.execute(get);
        Boolean result = RespondUtils.headerIsPresent(response, "ETag");

        assertTrue(result);

    }

}















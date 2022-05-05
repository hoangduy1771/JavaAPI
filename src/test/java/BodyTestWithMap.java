import org.apache.http.HttpHost;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.swing.text.html.parser.Entity;
import java.io.IOException;


public class BodyTestWithMap extends BaseClass {
    CloseableHttpClient client;
    CloseableHttpResponse response;

    @BeforeMethod
    public void setUp() {
//        HttpHost proxy = new HttpHost("rb-proxy-unix-apac.bosch.com", 8080);
//        client = HttpClientBuilder.create().setProxy(proxy).build();
         client = HttpClientBuilder.create().build();

    }

    @AfterMethod
    public void closeResources() throws IOException {
        client.close();
        response.close();
    }

    @Test
    public void returnsCorrectLogin() throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT + "users/hoangduy1771");
        response = client.execute(get);
        String jsonBody = EntityUtils.toString(response.getEntity());

        System.out.println(jsonBody);

    }

}

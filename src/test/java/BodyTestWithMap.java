import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;

import static entities.User.ID;
import static entities.User.LOGIN;
import static org.testng.AssertJUnit.assertEquals;


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

    @DataProvider(name = "keys")
    private Object[][] keys() {
        return new Object[][] {
                {LOGIN},
                {ID}
        };
    }

    @Test
    public void returnsCorrectLogin() throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT + "users/hoangduy1771");
        response = client.execute(get);
        String jsonBody = EntityUtils.toString(response.getEntity());
        JSONObject jsonObject = new JSONObject(jsonBody);
        String loginValue =  RespondUtils.getValueFor(jsonObject, LOGIN).toString();
        assertEquals("hoangduy1771", loginValue);
    }

    @Test
    public void returnsCorrectId() throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT + "users/hoangduy1771");
        response = client.execute(get);
        String jsonBody = EntityUtils.toString(response.getEntity());
        JSONObject jsonObject = new JSONObject(jsonBody);
        int idValue = (Integer) RespondUtils.getValueFor(jsonObject, ID);
        assertEquals(74887621, idValue);
    }

}

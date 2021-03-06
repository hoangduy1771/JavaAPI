import org.apache.http.HttpHost;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.*;

public class Get401 extends BaseClass {

    @DataProvider(name = "endpoints")
    private Object[][] endpoints() {
        return new Object[][] {
                { "/user" },
                { "/user/followers" },
                { "/notifications" }
        };
    }

    @Test(dataProvider = "endpoints")
    public void userReturns401(String endPoint) throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT + endPoint);
        response = client.execute(get);
        int actualStatus = response.getStatusLine().getStatusCode();
        assertEquals(401, actualStatus);
    }

}

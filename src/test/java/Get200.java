import org.apache.http.client.methods.HttpGet;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.*;

public class Get200 extends BaseClass {

    @DataProvider(name = "endpoints")
    private Object[][] endpoints() {
        return new Object[][] {
                { "" },
                { "/rate_limit" },
                { "/search/repositories?q=java" }
        };
    }

    @Test(dataProvider = "endpoints")
    public void baseUrlReturns200(String endPoint) throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT + endPoint);
        response = client.execute(get);
        int actualStatus = response.getStatusLine().getStatusCode();
        assertEquals(200, actualStatus);
    }
}

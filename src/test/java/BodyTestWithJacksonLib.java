import org.apache.http.client.methods.HttpGet;
import org.testng.annotations.Test;

import java.io.IOException;

public class BodyTestWithJacksonLib extends BaseClass {
    @Test
    public void returnsCorrectLogin() throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT + "users/hoangduy1771");
        response = client.execute(get);
    }

}

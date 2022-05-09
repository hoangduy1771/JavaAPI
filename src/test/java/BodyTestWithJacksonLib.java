import entities.NotFound;
import entities.User;
import org.apache.http.client.methods.HttpGet;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.AssertJUnit.assertEquals;

public class BodyTestWithJacksonLib extends BaseClass {
    @Test
    public void returnsCorrectLogin() throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT + "users/hoangduy1771");
        response = client.execute(get);

        User user = RespondUtils.unmarshall(response, User.class);
        assertEquals(user.getLogin(), "hoangduy1771");
    }

    @Test
    public void returnsCorrectId() throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT + "users/hoangduy1771");
        response = client.execute(get);

        User user = RespondUtils.unmarshall(response, User.class);
        assertEquals(user.getId(), "74887621");
    }

    @Test
    public void returnsNotFoundValue() throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT + "users/nonexistingendpoint");
        response = client.execute(get);

        NotFound notFoundMessage = RespondUtils.unmarshallGeneric(response, NotFound.class);
        assertEquals("Not Found", notFoundMessage.getMessage());
    }

    @Test
    public void correctRateLimitAreSet() throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT + "/rate_limit");
        response = client.execute(get);

        RateLimit rateLimit = RespondUtils.unmarshallGeneric(response, RateLimit.class);
        assertEquals(60, rateLimit.getCoreLimit());
        assertEquals("10", rateLimit.getSearchLimit());
    }


}

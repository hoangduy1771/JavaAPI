import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;

import static entities.User.ID;
import static entities.User.LOGIN;
import static org.testng.AssertJUnit.assertEquals;


public class BodyTestWithMap extends BaseClass {
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

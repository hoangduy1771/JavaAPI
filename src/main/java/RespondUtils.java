import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.User;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class RespondUtils {

    public static String getHeader(CloseableHttpResponse response, String headerName) {
        // Get All headers
        Header[] headers = response.getAllHeaders();
        List<Header> httpHeaders = Arrays.asList(headers);
        String returnHeader = "";
        // Loop over the headers list
        for (Header header : httpHeaders) {
            if (headerName.equalsIgnoreCase(header.getName())) {
                returnHeader = header.getValue();
            }
        }
        // If no header found, throw an exception
        if (returnHeader.isEmpty()) {
            throw new RuntimeException("Didn't find the header: " + headerName);
        }
        // Return the header
        return returnHeader;
    }

    public static String getHeaderNew(CloseableHttpResponse response, String headerName) {
        List<Header> httpHeaders = Arrays.asList(response.getAllHeaders());
        // Using stream to get the header
        Header matchedHeader = httpHeaders.stream()
                .filter(header -> headerName.equalsIgnoreCase(header.getName()))
                .findFirst().orElseThrow(() -> new RuntimeException("Header can not be found"));
        return matchedHeader.getValue();
    }

    public static Boolean headerIsPresent(CloseableHttpResponse response, String headerName) {
        List<Header> httpHeaders = Arrays.asList(response.getAllHeaders());

        return httpHeaders.stream()
                .anyMatch(header -> header.getName().equalsIgnoreCase(headerName));
    }

    public static Object getValueFor(JSONObject jsonObject,String key) {
        try {
            return jsonObject.get(key);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }

    }

    public static User unmarshall(CloseableHttpResponse response, Class<User> userClass) throws IOException {
//        Get the Json body of the API
        String jsonBody = EntityUtils.toString(response.getEntity());

        return new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .readValue(jsonBody, userClass);
    }

    public static <T> T unmarshallGeneric(CloseableHttpResponse response, Class<T> anyClass) throws IOException {
//        Get the Json body of the API
        String jsonBody = EntityUtils.toString(response.getEntity());

        return new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .readValue(jsonBody, anyClass);
    }

}

package guru.springfamework.controllers.v1;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AbstractRestControllerTest {

    public static String asJsonString(final Object obj) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

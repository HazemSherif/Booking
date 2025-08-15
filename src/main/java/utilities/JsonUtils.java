package utilities;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JsonUtils {

    public static Object[][] getJsonData(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, String>> dataList =
                mapper.readValue(new File(filePath), List.class);

        Object[][] data = new Object[dataList.size()][dataList.get(0).size()];

        for (int i = 0; i < dataList.size(); i++) {
            int j = 0;
            for (Object value : dataList.get(i).values()) {
                data[i][j++] = value;
            }
        }
        return data;
    }
}
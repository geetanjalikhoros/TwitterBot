import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Utils
{
    private String PROPERTIES_FILE_PATH = System.getProperty("user.dir")+"/src/resources/values.properties";
    public Properties getProperties() throws IOException
    {
        FileReader reader=new FileReader(PROPERTIES_FILE_PATH);
        Properties p=new Properties();
        p.load(reader);
        return p;
    }

}

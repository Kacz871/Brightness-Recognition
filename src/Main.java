import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {

        // remember to change path in config file
        String path = new File("resources/config.properties").getAbsolutePath();
        Properties configuration = new Properties();
        try {
            configuration.load(new FileInputStream(path));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        String input = configuration.getProperty("input");
        String output = configuration.getProperty("output");
        FolderConstructor test1 = new FolderConstructor(input, output);
        ImageAnalyze test2 = new ImageAnalyze(25);
        test2.startAnalyze(test1.getImageList(), test1.getOutput());

    }


}

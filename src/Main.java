import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {

        // remember to change path in config file
        Properties configuration = new Properties();
        try {
            configuration.load(FolderConstructor.class.getResourceAsStream("config.properties"));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        String input = configuration.getProperty("input");
        String output = configuration.getProperty("output");
        FolderConstructor test1 = new FolderConstructor(input, output);
        int brightnesLvl = Integer.parseInt(configuration.getProperty("brightnesLvl"));
        ImageAnalyze test2 = new ImageAnalyze(brightnesLvl);
        test2.startAnalyze(test1.getImageList(), test1.getOutput());

    }


}

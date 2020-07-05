import java.io.File;
import java.util.ArrayList;

public class FolderConstructor {
    private String input;
    private String output;
    private File[] imageList;


    public FolderConstructor(String input, String output) {
        this.input = input;
        this.output = output;
        File folder = new File(input);
        this.imageList = folder.listFiles();
        this.folderStructure();

    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public File[] getImageList() {
        return imageList;
    }

    public void setImageList(File[] imageList) {
        this.imageList = imageList;
    }

    public void folderStructure(){
        File dark = new File(output + "\\dark");
        if(!dark.exists()) {
            dark.mkdir();
        }
        File bright = new File(output + "\\bright");
        if(!bright.exists()) {
            bright.mkdir();
        }
    }
}

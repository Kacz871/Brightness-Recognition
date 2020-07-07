import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class ImageAnalyze {
    private double brightnessThreshold;

    public ImageAnalyze(int brightnessThreshold) {
        if( brightnessThreshold < 0) {
            this.brightnessThreshold = 0;
        }else if(brightnessThreshold > 100){
            this.brightnessThreshold = 100;
        }else{
            this.brightnessThreshold = brightnessThreshold;
        }
    }

    public double getBrightnessThreshold() {
        return brightnessThreshold;
    }

    public void setBrightnessThreshold(double brightnessThreshold) {
        this.brightnessThreshold = brightnessThreshold;
    }

    // Analyze
    public void startAnalyze(File[] imageList, String outputsrc){

        BufferedImage image = null;
        for(int i = 0; i < imageList.length; i++) {

            File file = imageList[i];
            if(extentionCheck(file)) {
                try {
                    image = ImageIO.read(file);
                    String folder = "";
                    double brightnessLvl = this.brightnessThreshold / 100;
                    System.out.println(this.brightnessThreshold / 100);
                    String metaData = "";
                    double imageBrightness = countAvrBrightness(image);
                    NumberFormat nf = DecimalFormat.getInstance();
                    nf.setMaximumFractionDigits(0);
                    String resoult = nf.format(imageBrightness * 100);

                    if ( imageBrightness <= brightnessLvl) {
                        metaData = "_dark_" + resoult;
                    }else {
                        metaData = "_white_" + resoult;
                    }
                    String nameWithoutExtenstion = file.getName();
                    int pos = nameWithoutExtenstion.lastIndexOf(".");
                    if (pos > 0) {
                        nameWithoutExtenstion = nameWithoutExtenstion.substring(0, pos);
                    }
                    ImageIO.write(image, getFileExtension(file), new File(outputsrc + "\\" + nameWithoutExtenstion + metaData  +"." +getFileExtension(file)));

                    file.delete();


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("done");
            }

    // Count brightness of every pixel on image then counting average
    public double countAvrBrightness(BufferedImage image){
        float sumOfLuminance = 0;
        int width = 0;
        int height = 0;
        if( image != null){
            width =  image.getWidth();
            height = image.getHeight();
        }
        //iterating through pixel in image
        for(int i = 1; i < width; i++){
            for(int j = 1; j < height; j++){

                int pixelRGB = image.getRGB(i, j);
                // separating base color
                int red   = (pixelRGB >>> 16) & 0xFF;
                int green = (pixelRGB >>>  8) & 0xFF;
                int blue  = (pixelRGB >>>  0) & 0xFF;
                //luminence counting of pixel (https://en.wikipedia.org/wiki/Relative_luminance)
                float luminance = (red * 0.2126f + green * 0.7152f + blue * 0.0722f) / 255;
                sumOfLuminance += luminance;

            }
        }
        // img width multiplate by img height give us total amount of pixels
        float brightness = sumOfLuminance/(image.getWidth()*image.getHeight());
        System.out.println( brightness);
        return  brightness;

    }

    // return file extention in string
    private static String getFileExtension(File file) {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }

    //Check if file got jpg or png extention
    private static boolean extentionCheck(File file){
        String fileName = file.getName();
        if( fileName.endsWith(".jpg") ||
            fileName.endsWith(".jpeg") ||
            fileName.endsWith(".jpe") ||
            fileName.endsWith(".jif") ||
            fileName.endsWith(".jfif") ||
            fileName.endsWith(".jfi")||
            fileName.endsWith(".png")){
            return true;
        }

        return false;
    }
}

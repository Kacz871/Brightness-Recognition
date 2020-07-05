public class Main {

    public static void main(String[] args) {
        FolderConstructor test1 = new FolderConstructor("C:\\Users\\Kaczaf\\Desktop\\photo", "C:\\Users\\Kaczaf\\Desktop\\after");
        ImageAnalyze test2 = new ImageAnalyze(25);
        test2.startAnalyze(test1.getImageList(), test1.getOutput());
    }
}

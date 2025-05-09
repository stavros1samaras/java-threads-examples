import java.io.File;
import java.io.IOException;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class RGBtoGrayscaleParallel {

    static double redCoefficient = 0.299;
    static double greenCoefficient = 0.587;
    static double blueCoefficient = 0.114;

    public static void main(String args[]) {

        String fileNameR = null;
        String fileNameW = null;

        // Input and Output files using command line arguments
        if (args.length != 2) {
            System.out.println("Usage: java RGBtoGrayScale <file to read > <file to write>");
            System.exit(1);
        }
        fileNameR = args[0];
        fileNameW = args[1];

        // The same without command line arguments
        // fileNameR = "original.jpg";
        // fileNameW = "new.jpg";

        // Reading Input file to an image
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(fileNameR));
        } catch (IOException e) {
        }

        // Start timing
        long start = System.currentTimeMillis();

        // Coefficinets of R G B to GrayScale
        ///////////////////////////////////////
        /// 
        int numThreads = Runtime.getRuntime().availableProcessors();
        SinxThread threads[] = new SinxThread[numThreads];

        int size = img.getHeight();
        int block = size / numThreads;
        int from = 0;
        int to = 0;

        for (int i = 0; i < numThreads; i++) {
            from = i * block;
            to = i * block + block;
            if (i == (numThreads - 1))
                to = size;
            threads[i] = new SinxThread(img, from, to);
            threads[i].start();
        }

        for(int i = 0; i < numThreads; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {}
		}

        long elapsedTimeMillis = System.currentTimeMillis() - start;

        try {
            File file = new File(fileNameW);
            ImageIO.write(img, "jpg", file);
        } catch (IOException e) {
        }

        System.out.println("Done...");
        System.out.println("time in ms = " + elapsedTimeMillis);
    }

    static class SinxThread extends Thread {
        
        private int myfrom;
        private int myto;
        BufferedImage img;

        // constructor
        public SinxThread(BufferedImage img, int from, int to) {
            this.img = img;
            myfrom = from;
            myto = to;
        }

        public void run() {
            for (int y = myfrom; y < myto; y++) {
                for (int x = 0; x < img.getWidth(); x++) {
                    int pixel = img.getRGB(x, y);
                    Color color = new Color(pixel, true);
                    int red = (int) (color.getRed() * redCoefficient);
                    int green = (int) (color.getGreen() * greenCoefficient);
                    int blue = (int) (color.getBlue() * blueCoefficient);
                    color = new Color(red + green + blue,
                            red + green + blue,
                            red + green + blue);
                    img.setRGB(x, y, color.getRGB());
                }
            }

        }

    }
}

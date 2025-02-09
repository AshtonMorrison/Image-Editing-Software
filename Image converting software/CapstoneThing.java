import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.*;
import java.awt.geom.AffineTransform;

public class CapstoneThing{
    public static void main(String... args) throws InterruptedException, IOException {
        // System.out.println("Hello, and welcome to my image converting software!");
        // TimeUnit.SECONDS.sleep(3);
        // System.out.println("This program will ask you a bunch of yes/no questions.");
        // TimeUnit.SECONDS.sleep(3);
        // System.out.println("These questions will be about what you want to do to your image.");
        // TimeUnit.SECONDS.sleep(4);
        // System.out.println("For example, the program will ask if you want to greyscale your image, you respond with either a yes or no.");
        // TimeUnit.SECONDS.sleep(5);
        // System.out.println("The first thing to do is to add the image that you want to do conversions on.");
        // TimeUnit.SECONDS.sleep(4);
        // System.out.println("In order to do this, you need the EXACT FilePath for the image.");
        // TimeUnit.SECONDS.sleep(3);
        // System.out.println("An example of a file path would be C:/importantfiles/Picture.png");
        // TimeUnit.SECONDS.sleep(3);
        // System.out.println("The FilePath must be in forward slashes, not back slashes. And yes, the image type is required.");
        // TimeUnit.SECONDS.sleep(3);
        Scanner systemInScanner = new Scanner(System.in);
        System.out.println("Please Input FilePath.");
        String filePath = systemInScanner.nextLine();
        
        boolean yo = true;
        while (yo) {

            System.out.println("So the image you want to use is " + filePath + ". Correct?");
            String correctImage = systemInScanner.nextLine();

            if(correctImage.equalsIgnoreCase("yes")){
                System.out.println("Sounds good, we will now continue.");
                break;
            }else {

                System.out.println("Input the actual FilePath");
                String actualFilePath = systemInScanner.nextLine();
                filePath = actualFilePath;

            }
        }
        // TimeUnit.SECONDS.sleep(2);
        // System.out.println("Next, we need the FilePathOut.");
        // TimeUnit.SECONDS.sleep(2);
        // System.out.println("This is basically the same thing as the first FilePath, it's just what you want the image saved as.");
        // TimeUnit.SECONDS.sleep(4);
        // System.out.println("For example, C:/importantfiles/PictureButConverted.png would be an acceptable FilePathOut.");
        // TimeUnit.SECONDS.sleep(4);
        // System.out.println("Just don't make it the same name as the first FilePath. Also, IT MUST BE .png, nothing else will work.");
        // TimeUnit.SECONDS.sleep(5);
        System.out.println("Please Input FilePathOut.");
        String filePathOut = systemInScanner.nextLine();


        boolean sup = true;
        while (sup) {

            System.out.println("So the FilePathOut you want to use is " + filePathOut + ". Correct?");
            String correctImageOut = systemInScanner.nextLine();

            if(correctImageOut.equalsIgnoreCase("yes")){
                System.out.println("Sounds good, we will now continue.");
                break;
            }else {

                System.out.println("Input the actual FilePath");
                String actualFilePathOut = systemInScanner.nextLine();
                filePathOut = actualFilePathOut;

            }
        }
        TimeUnit.SECONDS.sleep(3);
        System.out.println("Commencing BufferedImage Creation.");

        //Create image
        File input = new File(filePath);
        BufferedImage image = ImageIO.read(input);

        //Copy Image to result
        BufferedImage result = new BufferedImage(
            image.getWidth(),
            image.getHeight(),
            BufferedImage.TYPE_INT_RGB);

        Graphics2D graphic = result.createGraphics();
        graphic.drawImage(image, 0, 0, Color.WHITE, null);

        TimeUnit.SECONDS.sleep(3);
        System.out.println("BufferedImage Creation Complete, The program will now commence.");

        TimeUnit.SECONDS.sleep(3);

        System.out.println("Would you like to do any color conversions?");
        String colorConversion = systemInScanner.nextLine();

        TimeUnit.SECONDS.sleep(1);

        //Color Conversions
        if(colorConversion.equalsIgnoreCase("yes")){

            System.out.println("*WARNING* Adding more than 1 color conversion (except for Invert) returns a black image.");
            TimeUnit.SECONDS.sleep(3);
            System.out.println("GreyScale + RedScale = Black image. RedScale + Invert = Not Black Image");

            TimeUnit.SECONDS.sleep(3);

            System.out.println("Would you like to GreyScale the image?");
            String greyScale = systemInScanner.nextLine();

            TimeUnit.SECONDS.sleep(1);
            if(greyScale.equalsIgnoreCase("yes")){
                //GreyScale

                BufferedImage greyImage = new BufferedImage(result.getWidth(), result.getHeight(), BufferedImage.TYPE_INT_RGB);
                Graphics2D grey2d = greyImage.createGraphics();
                grey2d.drawImage(result, 0, 0, Color.WHITE, null);

                for (int i = 0; i < greyImage.getHeight(); i++) {
                    for (int j = 0; j < greyImage.getWidth(); j++) {
                        Color c = new Color(greyImage.getRGB(j, i));
                        int red = (int) (c.getRed() * 0.299);
                        int green = (int) (c.getGreen() * 0.587);
                        int blue = (int) (c.getBlue() * 0.114);
                        Color newColor = new Color(
                                red + green + blue,
                                red + green + blue,
                                red + green + blue);
                                greyImage.setRGB(j, i, newColor.getRGB());
                    }
                }
                result = greyImage;
                System.out.println("GreyScaling Complete.");
                TimeUnit.SECONDS.sleep(1);
            }

            System.out.println("Would you like to RedScale the image?");
            String redScale = systemInScanner.nextLine();

            TimeUnit.SECONDS.sleep(1);
            if(redScale.equalsIgnoreCase("yes")){
                //RedScale

                BufferedImage redImage = new BufferedImage(result.getWidth(), result.getHeight(), BufferedImage.TYPE_INT_RGB);
                Graphics2D red2d = redImage.createGraphics();
                red2d.drawImage(result, 0, 0, Color.WHITE, null);

                for (int y = 0; y < redImage.getHeight(); y++) {
                    for (int x = 0; x < redImage.getWidth(); x++) {
                        int p = redImage.getRGB(x, y);
          
                        int a = (p >> 24) & 0xff;
                        int r = (p >> 16) & 0xff;

                        p = (a << 24) | (r << 16) | (0 << 8) | 0;
          
                        redImage.setRGB(x, y, p);
                    }
                }
                result = redImage;
                System.out.println("RedScaling Complete.");
                TimeUnit.SECONDS.sleep(1);
            }

            System.out.println("Would you like to GreenScale the image?");
            String greenScale = systemInScanner.nextLine();

            TimeUnit.SECONDS.sleep(1);
            if(greenScale.equalsIgnoreCase("yes")){
                //GreenScale

                BufferedImage greenImage = new BufferedImage(result.getWidth(), result.getHeight(), BufferedImage.TYPE_INT_RGB);
                Graphics2D green2d = greenImage.createGraphics();
                green2d.drawImage(result, 0, 0, Color.WHITE, null);

                for (int y = 0; y < greenImage.getHeight(); y++) {
                    for (int x = 0; x < greenImage.getWidth(); x++) {
                        int p = greenImage.getRGB(x, y);
          
                        int a = (p >> 24) & 0xff;
                        int g = (p >> 8) & 0xff;

                        p = (a << 24) | (0 << 16) | (g << 8) | 0;
          
                        greenImage.setRGB(x, y, p);
                    }
                }
                result = greenImage;
                System.out.println("GreenScaling Complete.");
                TimeUnit.SECONDS.sleep(1);
            }

            System.out.println("Would you like to BlueScale the image?");
            String blueScale = systemInScanner.nextLine();

            TimeUnit.SECONDS.sleep(1);
            if(blueScale.equalsIgnoreCase("yes")){
                //BlueScale

                BufferedImage blueImage = new BufferedImage(result.getWidth(), result.getHeight(), BufferedImage.TYPE_INT_RGB);
                Graphics2D blue2d = blueImage.createGraphics();
                blue2d.drawImage(result, 0, 0, Color.WHITE, null);

                for (int y = 0; y < blueImage.getHeight(); y++) {
                    for (int x = 0; x < blueImage.getWidth(); x++) {
                        int p = blueImage.getRGB(x, y);
          
                        int a = (p >> 24) & 0xff;
                        int b = p & 0xff;

                        p = (a << 24) | (0 << 16) | (0 << 8) | b;
          
                        blueImage.setRGB(x, y, p);
                    }
                }
                result = blueImage;
                System.out.println("BlueScaling Complete.");
                TimeUnit.SECONDS.sleep(1);
            }

            System.out.println("Would you like to Invert the image?");
            String invert = systemInScanner.nextLine();

            TimeUnit.SECONDS.sleep(1);
            if(invert.equalsIgnoreCase("yes")){
                //Invert

                BufferedImage invertImage = new BufferedImage(result.getWidth(), result.getHeight(), BufferedImage.TYPE_INT_RGB);
                Graphics2D invert2d = invertImage.createGraphics();
                invert2d.drawImage(result, 0, 0, Color.WHITE, null);

                for (int y = 0; y < invertImage.getHeight(); y++) {
                    for (int x = 0; x < invertImage.getWidth(); x++) {
                        int p = invertImage.getRGB(x, y);
                        int a = (p >> 24) & 0xff;
                        int r = (p >> 16) & 0xff;
                        int g = (p >> 8) & 0xff;
                        int b = p & 0xff;

                        r = 255 - r;
                        g = 255 - g;
                        b = 255 - b;
          
                        p = (a << 24) | (r << 16) | (g << 8) | b;
                        invertImage.setRGB(x, y, p);
                    }
                }
                result = invertImage;
                System.out.println("Inverting Complete.");
                TimeUnit.SECONDS.sleep(1);
            }
        }
        System.out.println("Would you like to Rotate/Flip/Change the dimensions of the image?");
        String rotateFlipDimension = systemInScanner.nextLine();
        TimeUnit.SECONDS.sleep(1);

        if(rotateFlipDimension.equalsIgnoreCase("yes")){

            System.out.println("Would you like to change the dimensions of the image? (change width/height)");
            String changeDimension = systemInScanner.nextLine();
            TimeUnit.SECONDS.sleep(1);
            if(changeDimension.equalsIgnoreCase("yes")){


                System.out.println("Insert New Width");
                int newWidth = systemInScanner.nextInt();
                systemInScanner.nextLine(); // Consume the leftover newline
                TimeUnit.SECONDS.sleep(1);

                System.out.println("Insert New Height");
                int newHeight = systemInScanner.nextInt();
                systemInScanner.nextLine(); // Consume the leftover newline
                TimeUnit.SECONDS.sleep(1);

                BufferedImage redimensionedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
                Graphics2D dimension = redimensionedImage.createGraphics();
                dimension.drawImage(result, 0, 0, newWidth, newHeight, null);

                result = redimensionedImage;
                System.out.println("Dimension Changing Complete.");
                TimeUnit.SECONDS.sleep(1);
            }
            System.out.println("Would you like to rotate the image?");
            String rotatoPotatoe = systemInScanner.nextLine();
            TimeUnit.SECONDS.sleep(1);
            if(rotatoPotatoe.equalsIgnoreCase("yes")){
                System.out.println("How much do you want the image to rotate?");
                double amountRotate = systemInScanner.nextDouble();
                systemInScanner.nextLine(); // Consume the leftover newline
                TimeUnit.SECONDS.sleep(1);

                BufferedImage rotatedImage = new BufferedImage(result.getWidth(),result.getHeight() , BufferedImage.TYPE_INT_RGB);
                Graphics2D rotate = rotatedImage.createGraphics();
                rotate.rotate(Math.toRadians(amountRotate), rotatedImage.getWidth() / 2, rotatedImage.getHeight() / 2);
                rotate.drawImage(result, 0, 0, Color.WHITE, null);

                result = rotatedImage;
                System.out.println("Image Rotation Complete.");
                TimeUnit.SECONDS.sleep(1);
            }
            System.out.println("Would you like to flip the image?");
            String flippoBippo = systemInScanner.nextLine();
            TimeUnit.SECONDS.sleep(1);
            if(flippoBippo.equalsIgnoreCase("yes")){

                System.out.println("Would you like to flip the image horizontal?");
                String flipHorizontal = systemInScanner.nextLine();
                TimeUnit.SECONDS.sleep(1);

                if(flipHorizontal.equalsIgnoreCase("yes")){

                    BufferedImage horizontalFlippedImage = new BufferedImage(result.getWidth(),result.getHeight() , BufferedImage.TYPE_INT_RGB);
                    Graphics2D horizontalFlip2d = horizontalFlippedImage.createGraphics();
                    horizontalFlip2d.drawImage(result, 0, 0, Color.WHITE, null);

                    AffineTransform horizontal = AffineTransform.getScaleInstance(-1, 1);
                    horizontal.translate(-horizontalFlippedImage.getWidth(), 0);
                    AffineTransformOp op = new AffineTransformOp(horizontal, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
                    horizontalFlippedImage = op.filter(horizontalFlippedImage, null);

                    result = horizontalFlippedImage;
                    System.out.println("Horizontal Flip Complete.");
                    TimeUnit.SECONDS.sleep(1);
                }

                System.out.println("Would you like to flip the image vertical?");
                String flipVertical = systemInScanner.nextLine();
                TimeUnit.SECONDS.sleep(1);

                if(flipVertical.equalsIgnoreCase("yes")){

                    BufferedImage verticalFlippedImage = new BufferedImage(result.getWidth(),result.getHeight() , BufferedImage.TYPE_INT_RGB);
                    Graphics2D verticalFlip2d = verticalFlippedImage.createGraphics();
                    verticalFlip2d.drawImage(result, 0, 0, Color.WHITE, null);

                    AffineTransform vertical = AffineTransform.getScaleInstance(1, -1);
                    vertical.translate(0, -verticalFlippedImage.getHeight());
                    AffineTransformOp op = new AffineTransformOp(vertical, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
                    verticalFlippedImage = op.filter(verticalFlippedImage, null);

                    result = verticalFlippedImage;
                    System.out.println("Vertical Flip Complete.");
                    TimeUnit.SECONDS.sleep(1);
                }
            }
        }

        System.out.println("Would you like to add any image filters?");
        String imageEffect = systemInScanner.nextLine();
        TimeUnit.SECONDS.sleep(1);
        if(imageEffect.equalsIgnoreCase("yes")){

            System.out.println("Would you like to sharpen the image?");
            String sharpen = systemInScanner.nextLine();
            TimeUnit.SECONDS.sleep(1);

            if(sharpen.equalsIgnoreCase("yes")){

                BufferedImage sharpenedImage = new BufferedImage(result.getWidth(), result.getHeight(), BufferedImage.TYPE_INT_RGB);
                Graphics2D sharpen2d = sharpenedImage.createGraphics();
                sharpen2d.drawImage(result, 0, 0, Color.WHITE, null);

                Kernel kernel = new Kernel(3, 3,
                new float[]{
                    0f, -1f, 0f,
                    -1f, 5f, -1f,
                    0f, -1f, 0f});
 
                BufferedImageOp op = new ConvolveOp(kernel);
 
                sharpenedImage = op.filter(sharpenedImage, null);
                result = sharpenedImage;
                System.out.println("Sharpening Complete.");
                TimeUnit.SECONDS.sleep(1);
            }
            System.out.println("Would you like to blur the image?");
            String blur = systemInScanner.nextLine();
            TimeUnit.SECONDS.sleep(1);
            if(blur.equalsIgnoreCase("yes")){

                BufferedImage blurredImage = new BufferedImage(result.getWidth(), result.getHeight(), BufferedImage.TYPE_INT_RGB);
                Graphics2D blur2d = blurredImage.createGraphics();
                blur2d.drawImage(result, 0, 0, Color.WHITE, null);

                Kernel kernel = new Kernel(3, 3, 
                new float[] {
                    0.0625f, 0.125f, 0.0625f,
                    0.125f, 0.25f, 0.125f, 
                    0.0625f, 0.125f, 0.0625f});

                BufferedImageOp op = new ConvolveOp(kernel);
 
                blurredImage = op.filter(blurredImage, null);
                result = blurredImage;
                System.out.println("Blurring Complete.");
                TimeUnit.SECONDS.sleep(1);
            }
            System.out.println("Would you like to pixelate the image?");
            String pixelate = systemInScanner.nextLine();
            TimeUnit.SECONDS.sleep(1);
            if(pixelate.equalsIgnoreCase("yes")){

                BufferedImage pixelatedImage = new BufferedImage(result.getWidth(), result.getHeight(), BufferedImage.TYPE_INT_RGB);
                Graphics2D pixelate2d = pixelatedImage.createGraphics();
                pixelate2d.drawImage(result, 0, 0, Color.WHITE, null);

                System.out.println("How big do you want the pixelation to be? (Whole Numbers Only)");
                int sizeOfPixelate = systemInScanner.nextInt();

                Raster src = pixelatedImage.getData();
                WritableRaster dest = src.createCompatibleWritableRaster();

                for(int y = 0; y < src.getHeight(); y += sizeOfPixelate) {
                    for(int x = 0; x < src.getWidth(); x += sizeOfPixelate) {

                    double[] pixel = new double[3];
                    pixel = src.getPixel(x, y, pixel);

                        for(int yd = y; (yd < y + sizeOfPixelate) && (yd < dest.getHeight()); yd++) {
                            for(int xd = x; (xd < x + sizeOfPixelate) && (xd < dest.getWidth()); xd++) {
                            dest.setPixel(xd, yd, pixel);
                            }
                        }
                    }
                }
                pixelatedImage.setData(dest);
                result = pixelatedImage;
                System.out.println("Pixelation Complete.");
                TimeUnit.SECONDS.sleep(1);
            }
            System.out.println("Would you like to emboss the image?");
            String emboss = systemInScanner.nextLine();
            systemInScanner.close();
            TimeUnit.SECONDS.sleep(1);
            if(emboss.equalsIgnoreCase("yes")){

                BufferedImage embossedImage = new BufferedImage(result.getWidth(), result.getHeight(), BufferedImage.TYPE_INT_RGB);
                Graphics2D emboss2d = embossedImage.createGraphics();
                emboss2d.drawImage(result, 0, 0, Color.WHITE, null);

                Kernel kernel = new Kernel(3, 3, 
                new float[] {
                    -2f, -1f, 0f,
                    -1f, 1f, 1f,
                    0f, 1f, 2f});

                BufferedImageOp op = new ConvolveOp(kernel);
 
                embossedImage = op.filter(embossedImage, null);
                result = embossedImage;
                TimeUnit.SECONDS.sleep(1);
                System.out.println("Emboss Complete.");
                TimeUnit.SECONDS.sleep(1);
            }
        }
        System.out.println("All possible Image Conversions Complete.");
        TimeUnit.SECONDS.sleep(2);
        System.out.println("Exporting Your New Image.");
        TimeUnit.SECONDS.sleep(2);

        File output = new File(filePathOut);
        ImageIO.write(result, "png", output);

        System.out.println("Exporting Complete.");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Have A Nice Day.");
        TimeUnit.SECONDS.sleep(2);

        systemInScanner.close();
    }
}
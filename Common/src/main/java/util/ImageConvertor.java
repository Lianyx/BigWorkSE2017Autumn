package util;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImageConvertor {
    public static byte[] getByte(Image image){
        BufferedImage bimg = SwingFXUtils.fromFXImage(image,null);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try{
            ImageIO.write(bimg, "png", baos);
        }catch(Exception e){
            e.printStackTrace();
        }
        return baos.toByteArray();
    }
    public static BufferedImage getImage(byte[] bytes){
        InputStream in = new ByteArrayInputStream(bytes);
        BufferedImage image = null;
        try{
            image = ImageIO.read(in);
        }catch(Exception e){
            e.printStackTrace();
        }
        return image;
    }

    public static Image getFXImage(BufferedImage bufferedImage){
        Image image = SwingFXUtils.toFXImage(bufferedImage,null);
        return image;
    }

}

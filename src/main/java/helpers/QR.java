package helpers;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.ImageView;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

public class QR {


    public static BufferedImage createQR(String c) {

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        String code = String.valueOf(c);
        int width = 150;
        int height = 150;
        String fileType = "png";

        BufferedImage bufferedImage;
        try {
            BitMatrix byteMatrix;
            byteMatrix = qrCodeWriter.encode(code, BarcodeFormat.QR_CODE, width, height);

            bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bufferedImage.createGraphics();

            Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(Color.BLACK);

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }

            System.out.println("QR Generado...");

            return bufferedImage;
        } catch (WriterException e) {
            e.printStackTrace();
        }

        return null;
    }


    public static void displayImage(ImageView qr, String id){
        qr.setImage(SwingFXUtils.toFXImage(createQR(id), null));


    }

    public static void saveImage(RenderedImage image, File path){
        try {
            ImageIO.write(image,"png",path);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }






}

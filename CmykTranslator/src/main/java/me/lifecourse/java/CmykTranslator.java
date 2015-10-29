package me.lifecourse.java;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.util.Iterator;

/**
 * Created by scdhm on 2015/10/29.
 */
public class CmykTranslator {
    static String file = "E:/temp/example-cmyk.jpg";

    public static void main(String[] args) throws Throwable {
        // ��һ��reader
        Iterator readers = ImageIO.getImageReadersByFormatName("JPEG");
        ImageReader reader = null;
        while (readers.hasNext()) {
            reader = (ImageReader)readers.next();
            if (reader.canReadRaster()){
                break;
            }
        }
        ImageInputStream input = ImageIO.createImageInputStream(new File(file));
        reader.setInput(input);

        // ����ͼƬ.
        BufferedImage image;
        try {
            image = reader.read(0);
        } catch (IIOException e){
            // ��ȡRaster (û����ɫ��ת��).
            Raster raster = reader.readRaster(0, null);

            // ����ѡ��һ��BufferedImage����.
            int imageType;
            switch (raster.getNumBands()) {
                case 1:
                    imageType = BufferedImage.TYPE_BYTE_GRAY;
                    break;
                case 3:
                    imageType = BufferedImage.TYPE_3BYTE_BGR;
                    break;
                case 4:
                    imageType = BufferedImage.TYPE_4BYTE_ABGR;
                    break;
                default:
                    throw new UnsupportedOperationException();
            }

            // ����һ��BufferedImage.
            image = new BufferedImage(raster.getWidth(), raster.getHeight(), imageType);

            // ����ͼƬ����.
            image.getRaster().setRect(raster);
        }

    }


}

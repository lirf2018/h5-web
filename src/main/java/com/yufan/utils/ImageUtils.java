package com.yufan.utils;

import com.luciad.imageio.webp.WebPReadParam;
import com.luciad.imageio.webp.WebPWriteParam;
import com.mortennobel.imagescaling.ResampleOp;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.log4j.Logger;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author lirf
 * @version 1.0
 * @date 2019/12/13 13:38
 * @describe
 */
public class ImageUtils {

    private static Logger LOG = Logger.getLogger(ImageUtils.class);

    public static void main(String[] args) {
        String root = "C:\\Users\\Administrator\\Desktop\\test";
//        webpToImg(root);
        imgToWebp(root);
//        compressImg(root);
    }

    /**
     * webp 转  图片类型
     */
    public static void webpToImg(String root) {
        try {
            String inputWebpPath = root + "/a.webp";
            String outputJpgPath = root + "/b.jpg";
//            String outputJpegPath = "test_pic/test_.jpeg";
//            String outputPngPath = "test_pic/test_.png";

            // Obtain a WebP ImageReader instance
            ImageReader reader = ImageIO.getImageReadersByMIMEType("image/webp").next();

            // Configure decoding parameters
            WebPReadParam readParam = new WebPReadParam();
            readParam.setBypassFiltering(true);

            // Configure the input on the ImageReader
            reader.setInput(new FileImageInputStream(new File(inputWebpPath)));

            // Decode the image
            BufferedImage image = reader.read(0, readParam);

            //ImageIO.write(image, "png", new File(outputPngPath));
            ImageIO.write(image, "jpg", new File(outputJpgPath));
            //ImageIO.write(image, "jpeg", new File(outputJpegPath));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 图片类型 转  webp
     */
    public static void imgToWebp(String root) {
        try {
            //String inputPngPath = "test_pic/test.png";
            String inputJpgPath = root + "/d.jpg";
            String outputWebpPath = root + "/d.webp";

            // Obtain an image to encode from somewhere
            BufferedImage image = ImageIO.read(new File(inputJpgPath));

            // Obtain a WebP ImageWriter instance
            ImageWriter writer = ImageIO.getImageWritersByMIMEType("image/webp").next();

            // Configure encoding parameters
            WebPWriteParam writeParam = new WebPWriteParam(writer.getLocale());
            writeParam.setCompressionMode(WebPWriteParam.MODE_DEFAULT);

            // Configure the output on the ImageWriter
            writer.setOutput(new FileImageOutputStream(new File(outputWebpPath)));

            // Encode
            long st = System.currentTimeMillis();
            writer.write(null, new IIOImage(image, null, null), writeParam);
            System.out.println("cost: " + (System.currentTimeMillis() - st));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Thumbnails进行压缩
     * 1.缩放 scale(0.9);   //参数是浮点数,大于1表示放大，小于1表示缩小
     * 2.质量压缩  outputQuality(0.9); //参数是浮点数，0-1之间
     * 3.剪裁 .sourceRegion(100, 100, 300, 300);    .sourceRegion(Positions.CENTER, 200, 200);
     * 4.根据宽度来缩 .width(500);
     * 5.根据高度来缩放 .height(500);
     * 6.在调整尺寸时保持比例 .keepAspectRatio(true);  //默认为true，如果要剪裁到特定的比例，设为false即可
     * 7.根据宽度和高度进行缩放 .size(600, 700);//如果设置了keepAspectRatio(true)，将按比例进行缩放，否则将强制按尺寸输出
     * 8.将图片放入内存 Thumbnails.of(file2).scale(1.0).outputQuality(1.0).asBufferedImage(); //必须要指定scale
     * 9.加水印 .watermark(Positions.BOTTOM_RIGHT, bufferedImage, 1.0f) 第一个参数是加水印的位置,第二个参数是要加水印的图片,第三个参数是水印图片的透明度
     * *注意：scale、width|height、size三者不能同时共存，但必须要有一个
     */
    public static void compressImg(String root) {
        try {
            //Thumbnails.of(new File(root + "/a.jpg")).size(160, 160).toFile(new File(root + "/aa.jpg"));
            //单个图片等比例缩放
//            Thumbnails.of(new File(root + "/a.jpg")).scale(0.2).toFile(new File(root + "/aa.jpg"));
            Thumbnails.of(new File(root + "/x.png")).scale(0.5).toFile(new File(root + "/xx.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 接收输入流输生成图片
     *
     * @param input
     * @param writePath
     * @param width
     * @param height
     * @param format
     * @return
     */
    public boolean resizeImage(InputStream input, String writePath, Integer width, Integer height, String format) {
        try {
            BufferedImage inputBufImage = ImageIO.read(input);
            LOG.info("转前图片高度和宽度：" + inputBufImage.getHeight() + ":" + inputBufImage.getWidth());
            ResampleOp resampleOp = new ResampleOp(width, height);// 转换
            BufferedImage rescaledTomato = resampleOp.filter(inputBufImage, null);
            ImageIO.write(rescaledTomato, format, new File(writePath));
            LOG.info("转后图片高度和宽度：" + rescaledTomato.getHeight() + ":" + rescaledTomato.getWidth());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 接收File输出图片
     *
     * @param file
     * @param writePath
     * @param width
     * @param height
     * @param format
     * @return
     */
    public boolean resizeImage(File file, String writePath, Integer width, Integer height, String format) {
        try {
            BufferedImage inputBufImage = ImageIO.read(file);
            inputBufImage.getType();
            System.out.println("转前图片高度和宽度：" + inputBufImage.getHeight() + ":" + inputBufImage.getWidth());
            ResampleOp resampleOp = new ResampleOp(width, height);// 转换
            BufferedImage rescaledTomato = resampleOp.filter(inputBufImage,
                    null);
            ImageIO.write(rescaledTomato, format, new File(writePath));
            System.out.println("转后图片高度和宽度：" + rescaledTomato.getHeight() + ":" + rescaledTomato.getWidth());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 接收字节数组生成图片
     *
     * @param RGBS
     * @param writePath
     * @param width
     * @param height
     * @param format
     * @return
     */
    public boolean resizeImage(byte[] RGBS, String writePath, Integer width, Integer height, String format) {
        InputStream input = new ByteArrayInputStream(RGBS);
        return this.resizeImage(input, writePath, width, height, format);
    }

    public byte[] readBytesFromIS(InputStream is) throws IOException {
        int total = is.available();
        byte[] bs = new byte[total];
        is.read(bs);
        return bs;
    }

    //测试：只测试了字节流的方式，其它的相对简单，没有一一测试
    /*public static void main(String[] args) throws IOException {
        int width = 150;
        int height = 150;
        File inputFile = new File("F://from.jpg");
        File outFile = new File("F://to.jpg");
        String outPath = outFile.getAbsolutePath();
        MyImage myImage = new MyImage();
        InputStream input = new FileInputStream(inputFile);
        byte[] byteArrayImage = myImage.readBytesFromIS(input);
        input.read(byteArrayImage);
        myImage.resizeImage(byteArrayImage, outPath, width, height, "jpg");
    }*/

}

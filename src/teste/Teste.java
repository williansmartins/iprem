package teste;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Teste {
	static String localThumb = "//Users//williansmartins//Documents//workspace//iprem//WebContent//images//uploads//thumbs//";
	

	public static BufferedImage Redimension(String caminho, int w, int h)
			throws IOException {

		BufferedImage fundo = null, image = null;

		try {

			fundo = ImageIO.read(new File(caminho));
			image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
			Graphics g = image.getGraphics();
			g.drawImage(fundo.getScaledInstance(w, h, 50), 0, 0, null);
			return image;

		}

		catch (IOException e1) {

			fundo = new BufferedImage(1, 1, BufferedImage.BITMASK);
			return fundo;

		}

	}
	 public static BufferedImage scaleImage(BufferedImage image, int imageType,
	            int newWidth, int newHeight) {
	        // Make sure the aspect ratio is maintained, so the image is not distorted
	        double thumbRatio = (double) newWidth / (double) newHeight;
	        int imageWidth = image.getWidth(null);
	        int imageHeight = image.getHeight(null);
	        double aspectRatio = (double) imageWidth / (double) imageHeight;

	        if (thumbRatio < aspectRatio) {
	            newHeight = (int) (newWidth / aspectRatio);
	        } else {
	            newWidth = (int) (newHeight * aspectRatio);
	        }

	        // Draw the scaled image
	        BufferedImage newImage = new BufferedImage(newWidth, newHeight,
	                imageType);
	        Graphics2D graphics2D = newImage.createGraphics();
	        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
	                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	        graphics2D.drawImage(image, 0, 0, newWidth, newHeight, null);

	        return newImage;
	    }
	public static void main(String[] args) throws IOException {
		String local = localThumb + "escolha.jpg";
//		BufferedImage image = Redimension(local , 20, 700);
		BufferedImage in = javax.imageio.ImageIO.read(new java.io.File(local));
        BufferedImage out = Teste.scaleImage(in, BufferedImage.TYPE_INT_RGB, 100, 800);
        
		ImageIO.write(out, "JPG", new File(localThumb + "final.jpg"));

	}

}

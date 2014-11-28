package com.itg.weixin.web;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.imageio.ImageIO;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class Test2 extends JFrame {

	public Test2(String url, File file) throws Exception {
		JEditorPane editorPane = new JEditorPane();
		editorPane.setEditable(false);
		editorPane.setPage(url);
		JScrollPane jsp = new JScrollPane(editorPane);
		getContentPane().add(jsp);
		this.setLocation(0, 0);
		this.setVisible(true); // 如果这里不设置可见，则里面的图片等无法截取

		// 如果不延时，则图片等可能没有时间下载显示
		// 具体的秒数需要根据网速等调整
		Thread.sleep(5 * 1000);

		setSize(1600, 900);

		pack();
		// BufferedImage image = new BufferedImage(editorPane.getWidth(),
		// editorPane.getHeight(), BufferedImage.TYPE_INT_RGB);
		BufferedImage image = new BufferedImage(editorPane.getWidth(),
				editorPane.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics2D = image.createGraphics();
		editorPane.paint(graphics2D);

		BufferedImage image1 = resize(image, 600, 400);

		ImageIO.write(image, "jpg", file);
		dispose();
	}

	public static void main(String[] args) throws Exception {
		URL accessUrl;
		String responseContent = null;
		String appid = "wxf0364f589da8ea45";
		String secret = "c9d8f4f5898d4e02fac60a081a9db121";
		try {
			accessUrl = new URL(
					"https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
							+ appid + "&secret=" + secret);

			TrustManager[] tm = { new X509TrustManager() {
				@Override
				public X509Certificate[] getAcceptedIssuers() {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public void checkServerTrusted(X509Certificate[] arg0,
						String arg1) throws CertificateException {
					// TODO Auto-generated method stub
				}

				@Override
				public void checkClientTrusted(X509Certificate[] arg0,
						String arg1) throws CertificateException {
					// TODO Auto-generated method stub
				}
			} };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			// 创建URL对象
			URL myURL = new URL(
					"https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
							+ appid + "&secret=" + secret);
			// 创建HttpsURLConnection对象，并设置其SSLSocketFactory对象
			HttpsURLConnection httpsConn = (HttpsURLConnection) myURL
					.openConnection();
			httpsConn.setSSLSocketFactory(ssf);

			// 取得该连接的输入流，以读取响应内容
			InputStreamReader insr = new InputStreamReader(
					httpsConn.getInputStream());
			BufferedReader in = new BufferedReader(insr);
			System.out.print(in.readLine());
//			// 读取服务器的响应内容并显示
//			int respInt = insr.read();
//			while (respInt != -1) {
//				System.out.print((char) respInt);
//				respInt = insr.read();
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static BufferedImage resize(BufferedImage source, int targetW,
			int targetH) {
		// targetW，targetH分别表示目标长和宽
		int type = source.getType();
		BufferedImage target = null;
		double sx = (double) targetW / source.getWidth();
		double sy = (double) targetH / source.getHeight();
		// 这里想实现在targetW，targetH范围内实现等比缩放。如果不需要等比缩放
		// 则将下面的if else语句注释即可
		if (sx > sy) {
			sx = sy;
			targetW = (int) (sx * source.getWidth());
			// } else {
			// sy = sx;
			// targetH = (int) (sy * source.getHeight());
		}
		if (type == BufferedImage.TYPE_CUSTOM) { // handmade
			ColorModel cm = source.getColorModel();
			WritableRaster raster = cm.createCompatibleWritableRaster(targetW,
					targetH);
			boolean alphaPremultiplied = cm.isAlphaPremultiplied();
			target = new BufferedImage(cm, raster, alphaPremultiplied, null);
		} else
			target = new BufferedImage(targetW, targetH, type);
		Graphics2D g = target.createGraphics();
		// smoother than exlax:
		g.setRenderingHint(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);
		g.drawRenderedImage(source, AffineTransform.getScaleInstance(sx, sy));
		g.dispose();
		return target;
	}
}

package com.example.demo;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PdfController {

	@RequestMapping(value = "/getPdfFile", method = RequestMethod.GET)
	public void getPdfFile(@RequestParam Map<String, String> param, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		BufferedInputStream bis = null;
		OutputStream os = null;
		String input = request.getParameter("input");
		System.out.println(input);
		// 上篇博客默认文件的hashcode值为文件名，此处暂且可以把fileName即hashCode当成文件的id操作
		try {
			// 文件名称,此处最好加一个fileName合法性判断
			String newfileName = input + ".pdf";
			byte[] buf = new byte[1024];
			int len = 0;
			response.reset();
			response.setContentType("application/pdf;charset=utf-8");
			// 切记此处不可写成response.setHeader("Content-disposition", "attachment;filename=" +
			// fileName);
			// 添加attachment浏览器会响应下载，预览会失败
			response.setHeader("Content-Disposition:inline", "filename=" + "aaa你好");
			String pdfPath = request.getServletContext().getRealPath("/convert") + File.separator + newfileName;
			pdfPath = "D://convert//a.pdf";
			File file = new File(pdfPath);
			if (file.exists()) {
				FileInputStream fis = new FileInputStream(pdfPath);
				if (fis != null) {
					bis = new BufferedInputStream(fis);
					os = response.getOutputStream();
					while ((len = bis.read(buf)) != -1) {
						os.write(buf, 0, len);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bis != null) {
				bis.close();
			}
			if (os != null) {
				os.close();
			}

		}
	}

	/**
	 * 转换全部的pdf
	 * 
	 * @param fileAddress
	 *            文件地址
	 * @param filename
	 *            PDF文件名
	 * @param type
	 *            图片类型
	 */
	public static void pdf2png(String fileAddress, String filename, String type) {
		// 将pdf装图片 并且自定义图片得格式大小
		File file = new File(fileAddress + "\\" + filename + ".pdf");
		try {
			PDDocument doc = PDDocument.load(file);
			PDFRenderer renderer = new PDFRenderer(doc);
			int pageCount = doc.getNumberOfPages();
			for (int i = 0; i < pageCount; i++) {
				BufferedImage image = renderer.renderImageWithDPI(i, 144); // Windows native DPI
				// BufferedImage srcImage = resize(image, 240, 240);//产生缩略图
				ImageIO.write(image, type, new File(fileAddress + "\\" + filename + "_" + (i + 1) + "." + type));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 自由确定起始页和终止页
	 * 
	 * @param fileAddress
	 *            文件地址
	 * @param filename
	 *            pdf文件名
	 * @param indexOfStart
	 *            开始页 开始转换的页码，从0开始
	 * @param indexOfEnd
	 *            结束页 停止转换的页码，-1为全部
	 * @param type
	 *            图片类型
	 */
	public static void pdf2pngPage(String fileAddress, String filename, int indexOfStart, int indexOfEnd, String type) {
		// 将pdf装图片 并且自定义图片得格式大小
		File file = new File(fileAddress + "\\" + filename + ".pdf");
		PDDocument doc = null;
		try {
			doc = PDDocument.load(file);
			PDFRenderer renderer = new PDFRenderer(doc);
			int pageCount = doc.getNumberOfPages();
			if (indexOfEnd > pageCount) {
				indexOfEnd = pageCount;
			}
			for (int i = indexOfStart; i < indexOfEnd; i++) {
				long start = System.currentTimeMillis();
				BufferedImage image = renderer.renderImageWithDPI(i, 80); // Windows native DPI
				long end = System.currentTimeMillis();
				System.out.println("renderer speed:" + (end - start));
				// BufferedImage srcImage = resize(image, 240, 240);//产生缩略图

				long start1 = System.currentTimeMillis();
				ImageIO.write(image, type, new File(fileAddress + "\\" + filename + "_" + (i + 1) + "." + type));
				long end1 = System.currentTimeMillis();
				System.out.println("write--- speed:" + (end1 - start1));

			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (doc != null)
					doc.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		pdf2pngPage("D://convert//", "a", 0, 100, "png");
		long end = System.currentTimeMillis();
		System.out.println("消耗时间" + (end - start) / 1000);
		System.out.println("aaaa");
	}
}

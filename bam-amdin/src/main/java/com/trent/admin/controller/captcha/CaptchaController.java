package com.trent.admin.controller.captcha;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.ChineseCaptcha;
import com.wf.captcha.GifCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import io.jsonwebtoken.io.IOException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;

/**
 * @Author: Trent
 * @Date: 2021/11/15 13:35
 * @program: BAM
 * @Description:
 */
@Api(tags = "CaptchaController")
@RestController
public class CaptchaController{
	@Autowired
	private DefaultKaptcha defaultKaptcha;
	@ApiOperation(value = "验证码")
	@GetMapping(value = "/captcha", produces = "image/jpeg")
	public void captcha(HttpServletRequest request, HttpServletResponse
			                                                response) {
		// 定义response输出类型为image/jpeg类型
		response.setDateHeader("Expires", 0);
		// Set standard HTTP/1.1 no-cache headers.
		response.setHeader("Cache-Control", "no-store, no-cache, mustrevalidate");
		// Set IE extended HTTP/1.1 no-cache headers (use addHeader).
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		// Set standard HTTP/1.0 no-cache header.
		response.setHeader("Pragma", "no-cache");
		// return a jpeg
		response.setContentType("image/jpeg");
		//-------------------生成验证码 begin --------------------------
		//获取验证码文本内容
		String text = defaultKaptcha.createText();
		System.out.println("验证码内容：" + text);
		//将验证码放入session中
		request.getSession().setAttribute("captcha", text);
		//根据文本内容创建图形验证码
		BufferedImage image = defaultKaptcha.createImage(text);
		ServletOutputStream outputStream = null;
		try {
			outputStream = response.getOutputStream();
			//输出流输出图片，格式jpg
			ImageIO.write(image, "jpg", outputStream);
			outputStream.flush();
		} catch ( IOException e) {
			e.printStackTrace();
		}catch( java.io.IOException e ){
			e.printStackTrace();
		}finally {
			if (null != outputStream) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}catch( java.io.IOException e ){
					e.printStackTrace();
				}
			}
		}
		//-------------------生成验证码 end ----------------------------
	}
	@ApiOperation(value = "png类型验证码")
	@GetMapping("/images/captchaPng")
	public void captchaPng(String key, HttpServletRequest request, HttpServletResponse response) throws java.io.IOException{
		// png类型
		SpecCaptcha captcha = new SpecCaptcha(130, 48);
		String text = captcha.text();// 获取验证码的字符
		char[] chars = captcha.textChar();// 获取验证码的字符数组
		
		System.out.println("验证码："+text);
		System.out.println(chars);
		// 输出验证码
		captcha.out(response.getOutputStream());
	}
	@ApiOperation(value = "gif类型验证码")
	@RequestMapping("/images/captchaGif")
	public void captchaGif(HttpServletResponse response) throws java.io.IOException{
		
		// 三个参数分别为宽、高、位数
		GifCaptcha gifCaptcha = new GifCaptcha(100, 48, 4);
		// 设置类型：字母数字混合
		gifCaptcha.setCharType(Captcha.TYPE_DEFAULT);
		//获取验证码
		String text = gifCaptcha.text();
		System.out.println("验证码为："+text);
		// 输出验证码
		gifCaptcha.out(response.getOutputStream());
	}
	@ApiOperation(value = "ZHCN类型验证码")
	@RequestMapping("/images/captchaZHCN")
	public void captchaZHCN(HttpServletResponse response) throws java.io.IOException{
		
		// 中文类型
		ChineseCaptcha captcha = new ChineseCaptcha(130, 48);
		//获取验证码
		String text = captcha.text();
		System.out.println("验证码为："+text);
		// 输出验证码
		captcha.out(response.getOutputStream());
	}
	@ApiOperation(value = "compute类型验证码")
	@RequestMapping("/images/captchaCompute")
	public void captchaCompute(HttpServletResponse response) throws java.io.IOException{
		
		// 算术类型
		ArithmeticCaptcha captcha = new ArithmeticCaptcha(130, 48);
		
		captcha.setLen(3);  // 几位数运算，默认是两位
		captcha.getArithmeticString();  // 获取运算的公式：4-9+1=?
		String text = captcha.text();// 获取运算的结果：-4
		
		System.out.println("计算结果为："+text);
		// 输出验证码
		captcha.out(response.getOutputStream());
	}
	public static void main(String[] args){
		// 三个参数分别为宽、高、位数
		GifCaptcha gifCaptcha = new GifCaptcha(100, 48, 4);
		// 设置类型：字母数字混合
		gifCaptcha.setCharType(Captcha.TYPE_DEFAULT);
		//获取验证码
		String text = gifCaptcha.text();
		System.out.println("验证码为："+text);
	}
}

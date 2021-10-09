package com.qst.onlineVote.servlet;


 
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class CodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		  //�����հ�ͼƬ
		BufferedImage image=new BufferedImage(70, 30, BufferedImage.TYPE_INT_RGB);
		//��ȡͼƬ����
		Graphics g=image.getGraphics();
		  Random r=new Random();
		 //���û�����ɫ
		 g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
		 //���ƾ��α���
		 g.fillRect(0, 0, 100, 30);
		 //����8��������
		 for(int i=0;i<8;i++){
			 g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
			 g.drawLine(r.nextInt(90), r.nextInt(30), r.nextInt(100), r.nextInt(30));
		 }
		 //���û�����ɫ
		 g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
		 //���÷�������ȡ����Ϊ5������ַ��� 
		 String number=getNumbers(4);
		    HttpSession se=request.getSession();
		      se.setAttribute("vcode", number);
		  g.setFont(new Font(null, Font.ITALIC+Font.BOLD, 24));
		  g.drawString(number,5, 25);
       response.setContentType("image/jpeg");
       OutputStream out= response.getOutputStream();
        ImageIO.write(image, "jpeg", out);
        out.close();
	}
	 
	public String getNumbers(int size){
		String str="QWERTYUIOPLKJHGFDSAZXCVBNM0123456789";
		  String number="";
		   Random r=new Random();
		   for(int i=0;i<size;i++){
			  char c=str.charAt(r.nextInt(str.length()));
			  number=number+c;
		   }
		return number;
		
	}

}

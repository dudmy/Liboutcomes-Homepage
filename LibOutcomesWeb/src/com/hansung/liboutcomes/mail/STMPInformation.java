package com.hansung.liboutcomes.mail;

import java.util.Properties;

public class STMPInformation {
	
	private Properties p = new Properties(); // ������ ���� ��ü
	
	public STMPInformation() {
		p.put("mail.smtp.host","smtp.naver.com"); // ���̹� SMTP
		 
		p.put("mail.smtp.port", "465");
		p.put("mail.smtp.starttls.enable", "true");
		p.put("mail.smtp.auth", "true");
		p.put("mail.smtp.debug", "true");
		p.put("mail.smtp.socketFactory.port", "465");
		p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		p.put("mail.smtp.socketFactory.fallback", "false");
		// SMTP ������ �����ϱ� ���� ������
	}

	public Properties getP() {
		return p;
	}
	
}

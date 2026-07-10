package com.isrdc.utils;

import org.springframework.stereotype.Component;

@Component
public class EmailPage {

	public String wlcPage (String username,String link) {
		
		
		return "<!DOCTYPE html> \r\n"
				+ "             <html lang=\"en\"> \r\n"
				+ "            <head> \r\n"
				+ "     <meta charset=\"UTF-8\">\r\n"
				+ "     <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"> \r\n"
				+ "     <title>Email Verification</title> \r\n"
				+ "      <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css\">\r\n"
				+ "            </link>\r\n"
				+ "    </head> \r\n"
				+ "     <body> \r\n"
				+ "        <div style=\"\r\n"
				+ "max-width:600px;\r\n"
				+ "margin:auto;\r\n"
				+ "background:white;\r\n"
				+ "padding:30px;\r\n"
				+ "border-radius:10px;\r\n"
				+ "box-shadow:0 0 10px rgba(0,0,0,.1);>\r\n"
				+ "\r\n"
				+ "<h4 style=\"color:#28a745;\">Hello "+ username+"</h4> \r\n"
				+ "     <p >  </p> \r\n"
				+ "    <p >Thank you for  creating account. <br>Please verify your account To login . <br>To verify account click the button below . <br>This Verification Link will expire in 24 hours </p> \r\n"
				+ "     \r\n"
				+ "    <a href=\"" + link + "\" style=\""
				+ "background:#0d6efd;"
				+ "color:#ffffff;"
				+ "padding:14px 30px;"
				+ "text-decoration:none;"
				+ "border-radius:5px;"
				+ "display:inline-block;"
				+ "font-weight:bold;"
				+ "\">Verify Email</a>\r\n"

				+ "    <p style=\"margin-top:20px;color:#555;font-size:14px;\">"
				+ "If the verification button doesn't work, copy and paste this URL into your browser:<br>"
				+ "<a href=\"" + link + "\" style=\"color:#0d6efd;word-break:break-all;\">"
				+ link
				+ "</a></p>\r\n"

				+ "    <p style=\"color:#444;\">Regards,<br><strong>Team CodeCrafters</strong></p>\r\n"

				+ "    <p style=\"font-size:12px;color:#888;\">"
				+ "This email is automated. Please do not reply."
				+ "</p>\r\n"

				+ "    <div style=\"margin-top:20px;text-align:center;font-size:12px;color:#999;\">"
				+ "© 2026 User Management System"
				+ "</div>\r\n"

				+ "</div>\r\n"
				+ "</body>\r\n"
				+ "</html>";
	}

}

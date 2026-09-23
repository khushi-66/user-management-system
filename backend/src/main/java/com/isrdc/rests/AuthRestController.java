package com.isrdc.rests;


import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.isrdc.dtos.LoginResponseDto;
import com.isrdc.dtos.NotificationDto;
import com.isrdc.dtos.ResetPasswordDto;
import com.isrdc.dtos.RoleDto;
import com.isrdc.dtos.UserDto;
import com.isrdc.dtos.UserProfileDto;
import com.isrdc.exceptions.SignupFailedException;
import com.isrdc.jwts.JwtService;
import com.isrdc.services.NotificationService;
import com.isrdc.services.OTPService;
import com.isrdc.services.RoleService;
import com.isrdc.services.UserProfileService;
import com.isrdc.services.UserService;
import com.isrdc.services.AuthService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class AuthRestController {
	@Autowired
	private OTPService otpserv;
	@Autowired
	private AuthService authServ;
	@Autowired
	private UserService userServ;
	@Autowired
	private RoleService roleServ;
	@Autowired
	private UserProfileService profileServ;
	@Autowired
	private NotificationService notificationServ;
	@Autowired
	private PasswordEncoder passenc;
	@Autowired
	private AuthenticationManager authmanager;
	@Autowired
	private JwtService jwtServ;
	
	
  @PostMapping("/signup")
  public ResponseEntity<?> signUp( @Valid @RequestBody UserDto user,BindingResult res) {
	  if(res.hasErrors()) {
		  throw new SignupFailedException(" Signup Failed Please Try Again");
	  }
	user.setPassword( passenc.encode(user.getPassword()));
	authServ.signupUser(user);
	 return ResponseEntity.ok(Map.of(
			 "Status","success",
			 "Message","Congratualations Your is account created you are signup successfully"
			 ));
  }
  
  
  @PostMapping("/signin")
  public ResponseEntity<?> signIn(@RequestBody UserDto dto,HttpServletRequest req) {
      System.out.println("========== SIGNIN API CALLED ==========");
      System.out.println("Email: " + dto.getEmail());
      UserDto userdto = userServ.findUser(dto.getEmail());
        if (userdto == null) {
          System.out.println("USER NOT FOUND");
          return ResponseEntity.ok(Map.of(
                  "status", "unsuccessful",
                  "responseMsg", "Invalid email or password"
          ));
      }
System.out.println("DATABASE STATUS: [" + userdto.getStatus() + "]");
// INACTIVE CHECK
      if ("InActive".equalsIgnoreCase(userdto.getStatus().trim())) {
System.out.println("========== INACTIVE USER - LOGIN BLOCKED ==========");
return ResponseEntity.ok(Map.of(
                  "status", "unsuccessful",
                  "responseMsg", "Please verify your email before logging in.",
                  "accountStatus", "InActive"
          ));
      }

      // BLOCKED CHECK
      if ("Blocked".equalsIgnoreCase(userdto.getStatus().trim())) {
System.out.println("========== BLOCKED USER - LOGIN BLOCKED ==========");
return ResponseEntity.ok(Map.of(
                  "status", "unsuccessful",
                  "responseMsg", "Your account has been blocked. Please contact administrator.",
                  "accountStatus", "Blocked"
          ));
      }

      // ONLY ACTIVE USER WILL REACH HERE
      System.out.println("========== STATUS OK - AUTHENTICATING ==========");
UsernamePasswordAuthenticationToken token =
              new UsernamePasswordAuthenticationToken(
                      dto.getEmail(),
                      dto.getPassword()
              );
Authentication auth = authmanager.authenticate(token);
if (auth.isAuthenticated()) {
 String jwtToken = jwtServ.generateToken(dto.getEmail());
userServ.updateLastloginUser(dto.getEmail());
          authServ.createLoginHistoryAndActivityLogs(dto.getEmail(),req);
          System.out.println("loginhistory added and activity added");
          RoleDto roledto = roleServ.getRoleByUserId(userdto.getUserId());
          System.out.println("roledto fetching end");
         //      response preparing
        List<NotificationDto>AllNotifications= notificationServ.fetchAllNotifications(userdto.getUserId());
          UserProfileDto userProfiledto=profileServ.findUserProfile(userdto.getUserId());
 System.out.println("user profileId :  "+userProfiledto.getProfileId()+userProfiledto.getUserId());
  LoginResponseDto response=new LoginResponseDto();
         response.setAccountStatus(userdto.getStatus());
         response.setEmail(userdto.getEmail());
         response.setNotifications(AllNotifications);
         response.setPhone(userdto.getPhone());
         response.setResponseMsg("login successfull");
         response.setRole(roledto.getName());
         response.setStatus("success");
         response.setToken(jwtToken);
         response.setUserId(userdto.getUserId());
         response.setUsername(userdto.getName());
         response.setUserProfile(userProfiledto);
         response.setLastLogin(LocalDateTime.now());
         System.out.println(response);
         return ResponseEntity.ok(response);
      }
return ResponseEntity.ok(Map.of(
              "status", "unsuccessful",
              "responseMsg", "Invalid email or password"
      ));
  }
  
  @GetMapping("/send-otp")
	public String sendOTP(@RequestParam("phone")String phone) {
		otpserv.sendOTP(phone);
		 return "OTP sent Successfully.........";
	}
	
	@GetMapping("/verify-otp")
	public ResponseEntity<?> verifyOTP(@RequestParam String phone, @RequestParam String otp) {
	boolean isVerifiedOTP=	otpserv.verify(phone,otp);
	System.out.println(isVerifiedOTP);
	return  ResponseEntity.ok(
				((Map.of(
						 "status","success","msg","OTP verified Successfully"))
				)	);}
	
  @GetMapping("/verify-email")
  public void verifyToken(@RequestParam String token,HttpServletResponse res)throws IOException {
	String isEmail= authServ.verifyEmail(token);
	String location="http://localhost:5173/"+isEmail;
  res.sendRedirect(location);
  }
  
  @GetMapping("/verify-passwordtoken")
  public void verifyPasswordToken(@RequestParam String token,HttpServletResponse res)throws IOException {
	  String status = authServ.verifyToken(token);
System.out.println(status);
	    if ("verified-token".equals(status)) {
	        res.sendRedirect("http://localhost:5173/change-password?token=" + token);
	    } else if ("expired-token".equals(status)) {
	        res.sendRedirect("http://localhost:5173/expired-token");
	    } else {
	        res.sendRedirect("http://localhost:5173/invalid-token");
	    }
  }
	
  @PostMapping("/resend-verificationmail")
  public ResponseEntity<?> resendVerificationMail(@RequestBody UserDto dto) {
	String email=dto.getEmail();
	System.out.println(email);
	authServ.reSendVerificationMail(email);
	return ResponseEntity.ok(Map.of(
			 "Status","success",
			 "Message","Email sent successfully"
			 ));
  }
  
  @PostMapping("/reset-password")
  public ResponseEntity<?> resetPassword(@RequestBody UserDto dto) {
	String email=dto.getEmail();
	System.out.println(email);
	authServ.SendPasswordResetMail(email);
	return ResponseEntity.ok(Map.of(
			 "Status","success",
			 "Message","password resetmail sent successfully"
			 ));
  }
  
  
  @PostMapping("/update-password")
  public ResponseEntity<?> UpdatePassword(@RequestBody ResetPasswordDto dto,HttpServletResponse res) throws IOException {
	String token=dto.getToken();
	String querypassword= dto.getPassword();
	System.out.println(querypassword);
	System.out.println(token);
	String status=authServ.verifyToken(token);
	if ("verified-token".equals(status)) {
		UserDto userdto=userServ.findUserByToken(token);
		System.out.println(userdto);
		userServ.updateUser(passenc.encode(querypassword),userdto.getUserId());
	notificationServ.createNotification(userdto.getUserId(), "password changed", "Your password is updated now remember next time");
		return ResponseEntity.ok(Map.of(
				 "Status","success",
				 "Message","password updated successfully"
				 ));}
    else if ("expired-token".equals(status)) {
    	return ResponseEntity.ok(Map.of(
   			 "Status","expired",
   			 "Message","password expired "
   			 ));
    } else {
    	return ResponseEntity.ok(Map.of(
   			 "Status","invalid",
   			 "Message","password is invalid"
   			 ));
    }
	}
  }
	
  

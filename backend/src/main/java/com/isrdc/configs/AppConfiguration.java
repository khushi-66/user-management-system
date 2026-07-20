package com.isrdc.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.isrdc.filters.AppFilter;
import com.isrdc.repos.ActivityLogRepo;
import com.isrdc.services.UserService;

@Configuration
public class AppConfiguration {

    
@Autowired
private UserService serv;
@Autowired
private AppFilter filter;

   
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
                        .allowedHeaders("*");
            }
        };
    }
	
	@Bean
	public PasswordEncoder passEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public  AuthenticationProvider authProvider() {
		DaoAuthenticationProvider authprov=new DaoAuthenticationProvider(serv);
		authprov.setPasswordEncoder(passEncoder());
		 return authprov;
	}
	
	@Bean
	public AuthenticationManager authManager(AuthenticationConfiguration conf) {
		return conf.getAuthenticationManager();
	}
	
	@Bean
	public SecurityFilterChain appSecurity(HttpSecurity sec) throws Exception {
		return sec.csrf(csrf->csrf.disable()).authorizeHttpRequests(
				req->req.requestMatchers("/contact","/resend-verificationmail", "/verify-passwordtoken","/update-password","/reset-password","/send-otp","/verify-otp","/signup","/verify-email","/signin")
				.permitAll()
				.anyRequest()
				.authenticated()
				).sessionManagement(
						sess->sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authenticationProvider(authProvider())
				.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}
 
}

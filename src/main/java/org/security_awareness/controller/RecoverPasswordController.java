package org.security_awareness.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.security_awareness.model.Password;
import org.security_awareness.model.User;
import org.security_awareness.service.PasswordService;
import org.security_awareness.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@PropertySource("classpath:application.properties")
public class RecoverPasswordController {
		
	@Autowired
	private Environment env;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordService passwordService;

	@RequestMapping(value = "/recoverPassword", method = RequestMethod.GET)
	public ResponseEntity<Void> resetPassword(
			HttpServletRequest request,
			@RequestParam("email") String email){

		User user = userService.findByEmail(email);
		if(user == null){
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		
		String randomPassword = UUID.randomUUID().toString();
		
		System.out.println("random Password: " + randomPassword);
		
		//1. add new password
		Password password = user.getPassword();
		password.setPassword(randomPassword);
		passwordService.save(password);
		
		//2. send email with new password and instructions
		SimpleMailMessage simpleMailMessage = createMailMessage(request, user, randomPassword);
		mailSender.send(simpleMailMessage);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
	
	// ------------------- NON API -------------------
	
	private SimpleMailMessage createMailMessage(HttpServletRequest request, 
			User user, String password) {
		
		SimpleMailMessage mailMessage = new SimpleMailMessage();

		String emailText = "Hello " + user.getFirstName() + ",\n\n" +
		    "Your new password is: " + password + "\n\n" +
			"Please remember to change your password again in your Settings Profile Page.";	
		mailMessage.setFrom(env.getRequiredProperty("smtp.username"));
		mailMessage.setTo(user.getEmail());
		mailMessage.setSubject("Security Awareness password recovery request");
		mailMessage.setText(emailText);
		return mailMessage;
	}
}

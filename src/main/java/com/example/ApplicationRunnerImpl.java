package com.example;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.model.Activity;
import com.example.model.Authority;
import com.example.model.Notification;
import com.example.model.Resource;
import com.example.model.Role;
import com.example.model.User;
import com.example.model.UserConfiguration;
import com.example.model.Zone;
import com.example.repository.ActivityRepository;
import com.example.repository.AuthorityRepository;
import com.example.repository.NotificationRepository;
import com.example.repository.ResourceRepository;
import com.example.repository.UserConfigurationRepositry;
import com.example.repository.UserRepository;
import com.example.repository.ZoneRepository;

@Component
public class ApplicationRunnerImpl implements ApplicationRunner{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuthorityRepository authorityRepository;
	
	@Autowired
	private ZoneRepository zoneRepository;
	
	@Autowired
	private UserConfigurationRepositry userConfigurationRepository;
	
	@Autowired
	private ActivityRepository activityRepository;
	
	@Autowired
	private NotificationRepository notificationRepository;
	
	@Autowired
	private ResourceRepository resourceRepository;
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	private static final String ALONSO = "alonso_50@mail.com";
	private static final String GONZALO = "gonzalo_50@mail.com";
	private static final String MARIO = "mario_50@mail.com";
	private static final String SERGI = "sergi_50@mail.com";
	private static final String JUAN = "juan_50@mail.com";
	private static final String VICTOR = "victor_50@mail.com";
	private static final String MARCOS = "marcos_50@mail.com";
	
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy hh:mm");
	
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		loadSecurityContext();
		loadUsersData();
		loadZonesData();
		loadUserZonesRelations();
		loadActivities();
		loadNotifications();
		loadResources();
	}
	
	private void loadResources(){
		// Resource
		try{
			Resource resource1 = new Resource();
			resource1.setCreator(userRepository.findByEmail(ALONSO));
			resource1.setName("LOPD");
			resource1.setType(1);
			resource1.setUrl("http://www.lopd.es/download.pdf");
			resource1.setCreationDate(DATE_FORMAT.parse("01/01/2017 07:00"));
			resourceRepository.save(resource1);
			
			Resource resource2 = new Resource();
			resource2.setCreator(userRepository.findByEmail(ALONSO));
			resource2.setName("SECURITY TPM");
			resource2.setType(1);
			resource2.setUrl("http://www.securitytpm.es/tpm.pdf");
			resource2.setCreationDate(DATE_FORMAT.parse("01/02/2017 10:00"));
			resourceRepository.save(resource2);
			
			Resource resource3 = new Resource();
			resource3.setCreator(userRepository.findByEmail(GONZALO));
			resource3.setName("SECURITY OF THINGS WORLD!");
			resource3.setType(0);
			resource3.setUrl("http://www.securityofthing.com");
			resource3.setCreationDate(DATE_FORMAT.parse("02/01/2017 10:00"));
			resourceRepository.save(resource3);
		
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	
	private void loadNotifications(){
		// Notification
		try {
			Notification notification1 = new Notification();
			notification1.setTitle("Breakfast at 10th Floor");
			notification1.setDescription("free croassants");
			notification1.setDate(DATE_FORMAT.parse("25/01/2017 08:00"));
			notification1.setSender(userRepository.findByEmail(GONZALO));
			notificationRepository.save(notification1);
			
			Notification notification2 = new Notification();
			notification2.setTitle("Security Alarm");
			notification2.setDescription("This is a security alarm!");
			notification2.setDate(DATE_FORMAT.parse("25/01/2017 08:00"));
			notification2.setSender(userRepository.findByEmail(MARIO)); 
			notificationRepository.save(notification2);
			
			Notification notification3 = new Notification();
			notification3.setTitle("Welcome to Security Awareness Application!");
			notification3.setDescription("All info of your sec awar, in one app!");
			notification3.setDate(DATE_FORMAT.parse("20/01/2017 11:00"));
			notification3.setSender(userRepository.findByEmail(ALONSO)); 
			notificationRepository.save(notification3);
			
			Notification notification4 = new Notification();
			notification4.setTitle("NEWS");
			notification4.setDescription("Some changes in our political agreements");
			notification4.setDate(DATE_FORMAT.parse("26/01/2017 19:00"));
			notification4.setSender(userRepository.findByEmail(MARIO)); 
			notificationRepository.save(notification4);
			
			Notification notification5 = new Notification();
			notification5.setTitle("You have a next event in 1 day!");
			notification5.setDescription("Your next security event will be in one day");
			notification5.setDate(DATE_FORMAT.parse("29/01/2017 10:00"));
			notification5.setSender(userRepository.findByEmail(JUAN)); 
			notificationRepository.save(notification5);
			
		
		
		} catch (ParseException e) {
			e.printStackTrace();
			
		}
		
	}
	
	private void loadActivities(){
		 
		try{
			// Activity 1
			Activity activity1 = new Activity();
			activity1.setName("New years Eve Security!");
			activity1.setDescription("Tech meeting");
			activity1.setPlace("Barcelona Forum");
			activity1.setManager(userRepository.findByEmail(ALONSO));
			activity1.setType(2);
			activity1.setNumRepeats(4);
			activity1.setDateTimeStart(DATE_FORMAT.parse("29/12/2016 18:00"));
			activity1.setDateTimeEnd(DATE_FORMAT.parse("29/12/2016 21:00"));
			Set<User> usersActivity1 = new HashSet<>();
			usersActivity1.add(userRepository.findByEmail(GONZALO));
			usersActivity1.add(userRepository.findByEmail(JUAN));
			activity1.setUsers(usersActivity1);
			activityRepository.save(activity1);
			
			// Activity 2		
			Activity activity2 = new Activity();
			activity2.setName("Hack a Car!");
			activity2.setDescription("Tech congress");		
			activity2.setDateTimeStart(DATE_FORMAT.parse("21/01/2017 18:00"));
			activity2.setDateTimeEnd(DATE_FORMAT.parse("21/01/2017 21:00"));
			activity2.setPlace("Barcelona Imagina Building");
			activity2.setManager(userRepository.findByEmail(GONZALO));
			activity2.setType(2);
			Set<User> usersActivity2 = new HashSet<>();
			usersActivity2.add(userRepository.findByEmail(VICTOR));
			usersActivity2.add(userRepository.findByEmail(MARCOS));
			activity2.setUsers(usersActivity2);
			activityRepository.save(activity2);	
			
			// Activity 3	
			Activity activity3 = new Activity();
			activity3.setName("Security Of Things");
			activity3.setDescription("World conference");	
			activity3.setDateTimeStart(DATE_FORMAT.parse("23/01/2017 18:00"));
			activity3.setDateTimeEnd(DATE_FORMAT.parse("26/01/2017 21:00"));
			activity3.setPlace("Barcelona Hall Congress");
			activity3.setManager(userRepository.findByEmail(GONZALO));
			activity3.setType(2);
			activity3.setNumRepeats(3);
			Set<User> usersActivity3 = new HashSet<>();
			usersActivity3.add(userRepository.findByEmail(ALONSO));
			activity3.setUsers(usersActivity3);
			activityRepository.save(activity3);
		
		} catch (ParseException e) {
			e.printStackTrace();
			
		}
	}
	
	private void loadUserZonesRelations() {
		User user = userRepository.findByEmail(GONZALO);
		Set<Zone> zones = new HashSet<>();
		zones.add(zoneRepository.findByName("Barcelona"));
		zones.add(zoneRepository.findByName("Madrid"));
		user.setZones(zones);
		userRepository.save(user);
				
	}

	private void loadZonesData() {
		zoneRepository.save( new Zone("Barcelona"
				, userRepository.findByEmail(GONZALO)
				, null));
		zoneRepository.save( new Zone("Girona"
				, userRepository.findByEmail(GONZALO)
				, null));
		zoneRepository.save( new Zone("Madrid"
				, userRepository.findByEmail(GONZALO)
				, null));
		zoneRepository.save( new Zone("Tarragona"
				, userRepository.findByEmail(MARIO)
				, null));
		zoneRepository.save( new Zone("Lleida"
				, userRepository.findByEmail(MARIO)
				, null));
		
	}

	private void loadUsersData(){
						
		Authority user = new Authority(Role.USER);
		Authority anonymous = new Authority(Role.ANONYMOUS);
		Authority admin = new Authority(Role.ADMIN);
		Authority superadmin = new Authority(Role.SUPERADMIN);
		
		authorityRepository.save(user);
		authorityRepository.save(anonymous);
		authorityRepository.save(admin);
		authorityRepository.save(superadmin);
		
			
		User user1 = new User(
				"David", // first name
				"Alonso", // last name
				passwordEncoder.encode("123456"), // password
				ALONSO, // email
				superadmin,
				userConfigurationRepository.save(new UserConfiguration(0,0)));
		user1.setCity("Barcelona");
		userRepository.save(user1);
		
		User user2 = new User(
				"Gonzalo", 
				"Asensio", 
				passwordEncoder.encode("123456"), 
				GONZALO,
				admin,
				userConfigurationRepository.save(new UserConfiguration(0,0)));
		user2.setCity("Madrid");
		userRepository.save(user2);
		
		User user3 = new User(
				"Mario", 
				"Reyes", 
				passwordEncoder.encode("123456"), 
				MARIO,
				admin,
				userConfigurationRepository.save(new UserConfiguration(0,0)));
		user3.setCity("Fraga");
		userRepository.save(user3);
		
		
		User user4 = new User(
				"Sergi", 
				"Alonso", 
				passwordEncoder.encode("123456"), 
				SERGI,
				user,
				userConfigurationRepository.save(new UserConfiguration(0,0)));
		user4.setCity("Terrassa");
		userRepository.save(user4);
		
		User user5 = new User(
				"Samuel", 
				"Exposito", 
				passwordEncoder.encode("123456"), 
				"samuel_50@mail.com",
				user,
				userConfigurationRepository.save(new UserConfiguration(0,0)));
		user5.setCity("Barcelona");
		userRepository.save(user5);

		
		User user6 = new User(
				"Diego", 
				"Delgado", 
				passwordEncoder.encode("123456"), 
				"diego_50@mail.com",
				user,
				userConfigurationRepository.save(new UserConfiguration(0,0)));
		user6.setCity("Barcelona");
		userRepository.save(user6);
		
		
		User user7 = new User(
				"Marcos", 
				"Repolles", 
				passwordEncoder.encode("123456"), 
				MARCOS,
				user,
				userConfigurationRepository.save(new UserConfiguration(0,0)));
		user7.setCity("Barcelona");
		userRepository.save(user7);

		
		User user8 = new User(
				"Txema", 
				"Romeria", 
				passwordEncoder.encode("123456"), 
				"txema_50@mail.com",
				user,
				userConfigurationRepository.save(new UserConfiguration(0,0)));
		user8.setCity("Guipuzcoa");
		userRepository.save(user8);

		
		User user9 = new User(
				"Juan", 
				"Caubet", 
				passwordEncoder.encode("123456"), 
				JUAN,
				anonymous,
				userConfigurationRepository.save(new UserConfiguration(0,0)));
		user9.setCity("Montanya");
		userRepository.save(user9);


		User user10 = new User(
				"Victor",
				"Pomareda",
				passwordEncoder.encode("123456"),
				VICTOR,
				anonymous,
				userConfigurationRepository.save(new UserConfiguration(0,0)));
		user10.setCity("Barcelona");
		userRepository.save(user10);
	}
	
	void loadSecurityContext(){
		
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(Role.SUPERADMIN.getRoleName()));
		
		org.springframework.security.core.userdetails.User springUser = new org.springframework.security.core.userdetails.User(
				"test", "test", authorities);
		Authentication authentication = new UsernamePasswordAuthenticationToken(springUser, null,
			      springUser.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
}
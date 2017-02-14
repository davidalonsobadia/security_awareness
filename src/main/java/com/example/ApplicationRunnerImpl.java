package com.example;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
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
import com.example.model.ActivityStatus;
import com.example.model.Authority;
import com.example.model.Notification;
import com.example.model.NotificationStatus;
import com.example.model.Password;
import com.example.model.Resource;
import com.example.model.Role;
import com.example.model.User;
import com.example.model.UserConfiguration;
import com.example.model.Zone;
import com.example.repository.ActivityStatusRepository;
import com.example.repository.AuthorityRepository;
import com.example.repository.NotificationRepository;
import com.example.repository.NotificationStatusRepository;
import com.example.repository.PasswordRepository;
import com.example.repository.ResourceRepository;
import com.example.repository.UserRepository;
import com.example.repository.ZoneRepository;
import com.example.service.ActivityService;

@Component
public class ApplicationRunnerImpl implements ApplicationRunner{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuthorityRepository authorityRepository;
	
	@Autowired
	private ZoneRepository zoneRepository;
			
	@Autowired
	private NotificationRepository notificationRepository;
	
	@Autowired
	private ResourceRepository resourceRepository;
	
	@Autowired
	private NotificationStatusRepository notificationStatusRepository;
		
	@Autowired
	private ActivityStatusRepository activityStatusRepository;
	
	
	@Autowired
	private ActivityService activityService;	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	private static final String ALONSO = "alonso_50@mail.com";
	private static final String GONZALO = "gonzalo_50@mail.com";
	private static final String MARIO = "mario_50@mail.com";
	private static final String SERGI = "sergi_50@mail.com";
	private static final String JUAN = "juan_50@mail.com";
	private static final String VICTOR = "victor_50@mail.com";
	private static final String MARCOS = "marcos_50@mail.com";
	private static final String ANNA = "anna@weappyou.com";
	private static final String TXEMA = "txema_50@mail.com";
	
	
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy hh:mm");
	
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		loadSecurityContext();
		loadUsersData();
		loadZonesData();
		loadUserZonesRelations();
		loadActivities();
		loadActivityStatus();
		loadNotifications();
		loadResources();
	}
	
	private void loadActivityStatus(){
		
		ActivityStatus activityStatus1 = new ActivityStatus();
		activityStatus1.setActivity(activityService.findOne(1));
		activityStatus1.setUser(userRepository.findByEmail(SERGI));
		activityStatus1.setInterested(true);
		activityStatus1.setAssistant(false);
		activityStatusRepository.save(activityStatus1);
		
		ActivityStatus activityStatus2 = new ActivityStatus();
		activityStatus2.setActivity(activityService.findOne(2));
		activityStatus2.setUser(userRepository.findByEmail(SERGI));
		activityStatus2.setInterested(true);
		activityStatus2.setAssistant(false);
		activityStatusRepository.save(activityStatus2);
		
		ActivityStatus activityStatus3 = new ActivityStatus();
		activityStatus3.setActivity(activityService.findOne(3));
		activityStatus3.setUser(userRepository.findByEmail(SERGI));
		activityStatus3.setInterested(true);
		activityStatus3.setAssistant(false);
		activityStatusRepository.save(activityStatus3);
		
		ActivityStatus activityStatus4 = new ActivityStatus();
		activityStatus4.setActivity(activityService.findOne(4));
		activityStatus4.setUser(userRepository.findByEmail(SERGI));
		activityStatus4.setInterested(true);
		activityStatus4.setAssistant(true);
		activityStatusRepository.save(activityStatus4);
		
		ActivityStatus activityStatus5 = new ActivityStatus();
		activityStatus5.setActivity(activityService.findOne(1));
		activityStatus5.setUser(userRepository.findByEmail(TXEMA));
		activityStatus5.setInterested(true);
		activityStatus5.setAssistant(false);
		activityStatusRepository.save(activityStatus5);
		
		ActivityStatus activityStatus6 = new ActivityStatus();
		activityStatus6.setActivity(activityService.findOne(2));
		activityStatus6.setUser(userRepository.findByEmail(TXEMA));
		activityStatus6.setInterested(true);
		activityStatus6.setAssistant(false);
		activityStatusRepository.save(activityStatus6);
		
		ActivityStatus activityStatus7 = new ActivityStatus();
		activityStatus7.setActivity(activityService.findOne(3));
		activityStatus7.setUser(userRepository.findByEmail(MARIO));
		activityStatus7.setInterested(true);
		activityStatus7.setAssistant(false);
		activityStatusRepository.save(activityStatus7);
		
		ActivityStatus activityStatus8 = new ActivityStatus();
		activityStatus8.setActivity(activityService.findOne(3));
		activityStatus8.setUser(userRepository.findByEmail(ANNA));
		activityStatus8.setInterested(true);
		activityStatus8.setAssistant(false);
		activityStatusRepository.save(activityStatus8);
		
		ActivityStatus activityStatus9 = new ActivityStatus();
		activityStatus9.setActivity(activityService.findOne(3));
		activityStatus9.setUser(userRepository.findByEmail(GONZALO));
		activityStatus9.setInterested(true);
		activityStatus9.setAssistant(false);
		activityStatusRepository.save(activityStatus9);
		
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
			
			Resource resource4 = new Resource();
			resource4.setCreator(userRepository.findByEmail(ANNA));
			resource4.setName("BUILD AN AWESOME ANDROID APP FROM SCRATCH");
			resource4.setType(1);
			resource4.setUrl("http://www.weappyou.com");
			resource4.setCreationDate(DATE_FORMAT.parse("02/02/2017 10:00"));
			resourceRepository.save(resource4);
		
			
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
			
			for(User user : userRepository.findAll()){
				for(Notification notification: notificationRepository.findAll()){
					NotificationStatus notificationStatus = new NotificationStatus();
					notificationStatus.setNotification(notification);
					notificationStatus.setUser(user);
					Random random = new Random();
					notificationStatus.setStatus(random.nextInt(3));
					notificationStatusRepository.save(notificationStatus);
				}
			}
		
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
			activity1.setZone(zoneRepository.findByName("Barcelona"));
			activity1.setManager(userRepository.findByEmail(ALONSO));
			activity1.setType(2);
			activity1.setNumRepeats(4);
			activity1.setDateTimeStart(DATE_FORMAT.parse("29/12/2016 18:00"));
			activity1.setDateTimeEnd(DATE_FORMAT.parse("29/12/2016 21:00"));
			activityService.save(activity1);
			
			// Activity 2		
			Activity activity2 = new Activity();
			activity2.setName("Hack a Car!");
			activity2.setDescription("Tech congress");
			activity2.setPlace("Barcelona Imagina Building");
			activity2.setZone(zoneRepository.findByName("Barcelona"));
			activity2.setDateTimeStart(DATE_FORMAT.parse("21/01/2017 11:00"));
			activity2.setDateTimeEnd(DATE_FORMAT.parse("21/01/2017 12:00"));
			activity2.setManager(userRepository.findByEmail(GONZALO));
			activity2.setType(2);
			activityService.save(activity2);	
			
			// Activity 3	
			Activity activity3 = new Activity();
			activity3.setName("Security Of Things");
			activity3.setDescription("World conference");
			activity3.setPlace("Barcelona Hall Congress");
			activity3.setZone(zoneRepository.findByName("Barcelona"));
			activity3.setDateTimeStart(DATE_FORMAT.parse("23/01/2017 18:00"));
			activity3.setDateTimeEnd(DATE_FORMAT.parse("23/01/2017 21:00"));
			activity3.setManager(userRepository.findByEmail(GONZALO));
			activity3.setType(2);
			activity3.setNumRepeats(3);
			activityService.save(activity3);
			
			// Activity 4	
			Activity activity4 = new Activity();
			activity4.setName("Tech Capital Congress");
			activity4.setDescription("Madrid capital");
			activity4.setPlace("Madrid Palacio de Deportes");
			activity4.setZone(zoneRepository.findByName("Madrid"));
			activity4.setDateTimeStart(DATE_FORMAT.parse("31/01/2017 09:00"));
			activity4.setDateTimeEnd(DATE_FORMAT.parse("31/01/2017 14:00"));
			activity4.setManager(userRepository.findByEmail(GONZALO));
			activity4.setType(2);
			activity4.setNumRepeats(3);
			activityService.save(activity4);
		
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
		
		Set<Zone> zones2 = new HashSet<>();
		zones2.add(zoneRepository.findByName("Barcelona"));
		User user2 = userRepository.findByEmail(JUAN);
		user2.setZones(zones2);
		userRepository.save(user2);
		
		User user3 = userRepository.findByEmail(MARIO);
		user3.setZones(zones2);
		userRepository.save(user3);
		
		User user4 = userRepository.findByEmail(ANNA);
		Set<Zone> zones3 = new HashSet<>();
		zones3.add(zoneRepository.findByName("Barcelona"));
		zones3.add(zoneRepository.findByName("Girona"));
		zones3.add(zoneRepository.findByName("Lleida"));
		user4.setZones(zones3);
		userRepository.save(user4);
		
		
		User user5 = userRepository.findByEmail(SERGI);
		Set<Zone> zones5 = new HashSet<>();
		zones5.add(zoneRepository.findByName("Barcelona"));
		user5.setZones(zones5);
		userRepository.save(user5);
		
		User user6 = userRepository.findByEmail(TXEMA);
		Set<Zone> zones6 = new HashSet<>();
		zones6.add(zoneRepository.findByName("Barcelona"));
		user6.setZones(zones6);
		userRepository.save(user6);
		
		User user7 = userRepository.findByEmail(VICTOR);
		Set<Zone> zones7 = new HashSet<>();
		zones7.add(zoneRepository.findByName("Girona"));
		user7.setZones(zones7);
		userRepository.save(user7);
		
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
				new Password(passwordEncoder.encode("123456")), // password
				ALONSO, // email
				superadmin,
				new UserConfiguration(0,0));
		user1.setCity("Barcelona");
		user1.setRegistered(true);
		userRepository.save(user1);
		
		User user2 = new User(
				"Gonzalo", 
				"Asensio", 
				new Password(passwordEncoder.encode("123456")), 
				GONZALO,
				admin,
				new UserConfiguration(0,0));
		user2.setCity("Madrid");
		user2.setRegistered(true);
		userRepository.save(user2);
		
		User user3 = new User(
				"Mario", 
				"Reyes", 
				new Password(passwordEncoder.encode("123456")), 
				MARIO,
				admin,
				new UserConfiguration(0,0));
		user3.setCity("Fraga");
		user3.setRegistered(true);
		userRepository.save(user3);
		
		
		User user4 = new User(
				"Sergi", 
				"Alonso", 
				new Password(passwordEncoder.encode("123456")), 
				SERGI,
				user,
				new UserConfiguration(0,0));
		user4.setCity("Terrassa");
		user4.setRegistered(true);
		userRepository.save(user4);
		
		User user5 = new User(
				"Samuel", 
				"Exposito", 
				new Password(passwordEncoder.encode("123456")), 
				"samuel_50@mail.com",
				user,
				new UserConfiguration(0,0));
		user5.setCity("Barcelona");
		user5.setRegistered(true);
		userRepository.save(user5);

		
		User user6 = new User(
				"Diego", 
				"Delgado", 
				new Password(passwordEncoder.encode("123456")), 
				"diego_50@mail.com",
				user,
				new UserConfiguration(0,0));
		user6.setCity("Barcelona");
		user6.setRegistered(true);
		userRepository.save(user6);
		
		
		User user7 = new User(
				"Marcos", 
				"Repolles", 
				new Password(passwordEncoder.encode("123456")), 
				MARCOS,
				user,
				new UserConfiguration(0,0));
		user7.setCity("Barcelona");
		user7.setRegistered(true);
		userRepository.save(user7);

		
		User user8 = new User(
				"Txema", 
				"Romeria", 
				new Password(passwordEncoder.encode("123456")), 
				TXEMA,
				user,
				new UserConfiguration(0,0));
		user8.setCity("Guipuzcoa");
		user8.setRegistered(true);
		userRepository.save(user8);

		
		User user9 = new User(
				"Juan", 
				"Caubet", 
				new Password(passwordEncoder.encode("123456")), 
				JUAN,
				anonymous,
				new UserConfiguration(0,0));
		user9.setCity("Montanya");
		user9.setRegistered(false);
		userRepository.save(user9);


		User user10 = new User(
				"Victor",
				"Pomareda",
				new Password(passwordEncoder.encode("123456")),
				VICTOR,
				anonymous,
				new UserConfiguration(0,0));
		user10.setCity("Barcelona");
		user10.setRegistered(false);
		userRepository.save(user10);
		
		
		User user11 = new User(
				"Anna",
				"Noell",
				new Password(passwordEncoder.encode("123456")),
				ANNA,
				anonymous,
				new UserConfiguration(0,0));
		user11.setCity("Barcelona");
		user11.setRegistered(false);
		userRepository.save(user11);			
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

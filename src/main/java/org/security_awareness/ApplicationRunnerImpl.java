package org.security_awareness;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import org.security_awareness.model.Activity;
import org.security_awareness.model.ActivityStatus;
import org.security_awareness.model.Authority;
import org.security_awareness.model.Notification;
import org.security_awareness.model.NotificationStatus;
import org.security_awareness.model.Password;
import org.security_awareness.model.Resource;
import org.security_awareness.model.Role;
import org.security_awareness.model.User;
import org.security_awareness.model.UserConfiguration;
import org.security_awareness.model.Zone;
import org.security_awareness.model.ZoneStatus;
import org.security_awareness.repository.ActivityStatusRepository;
import org.security_awareness.repository.AuthorityRepository;
import org.security_awareness.repository.NotificationRepository;
import org.security_awareness.repository.NotificationStatusRepository;
import org.security_awareness.repository.ResourceRepository;
import org.security_awareness.repository.UserRepository;
import org.security_awareness.repository.ZoneRepository;
import org.security_awareness.repository.ZoneStatusRepository;
import org.security_awareness.service.ActivityService;
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
	private ZoneStatusRepository zoneStatusRepository;
	
	
	@Autowired
	private ActivityService activityService;	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	private static final String ALONSO = "david.alonso@eurecat.org";
	private static final String GONZALO = "gonzalo_50@mail.com";
	private static final String MARIO = "mario_50@mail.com";
	private static final String SERGI = "sergi_50@mail.com";
	private static final String JUAN = "juan_50@mail.com";
	private static final String VICTOR = "victor_50@mail.com";
	private static final String MARCOS = "marcos_50@mail.com";
	private static final String ANNA = "anna@weappyou.com";
	private static final String TXEMA = "txema_50@mail.com";
	private static final String SAMUEL = "samuel_50@mail.com";
	private static final String DIEGO = "diego_50@mail.com";
	
	private static final String BARCELONA = "Barcelona";
	private static final String MADRID = "Madrid";
	private static final String LLEIDA = "Lleida";
	private static final String TARRAGONA = "Tarragona";
	private static final String GIRONA = "Girona";
	
	
	
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy hh:mm");
	
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		loadSecurityContext();
		loadUsersData();
		loadZonesData();
		loadZoneStatus();
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
		activityStatus9.setAssistant(true);
		activityStatusRepository.save(activityStatus9);
		
		ActivityStatus activityStatus10 = new ActivityStatus();
		activityStatus10.setActivity(activityService.findOne(5));
		activityStatus10.setUser(userRepository.findByEmail(SAMUEL));
		activityStatus10.setInterested(true);
		activityStatus10.setAssistant(true);
		activityStatusRepository.save(activityStatus10);
		
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
			activity1.setZone(zoneRepository.findByName(BARCELONA));
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
			activity2.setZone(zoneRepository.findByName(BARCELONA));
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
			activity3.setZone(zoneRepository.findByName(BARCELONA));
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
			activity4.setZone(zoneRepository.findByName(MADRID));
			activity4.setDateTimeStart(DATE_FORMAT.parse("31/01/2017 09:00"));
			activity4.setDateTimeEnd(DATE_FORMAT.parse("31/01/2017 14:00"));
			activity4.setManager(userRepository.findByEmail(GONZALO));
			activity4.setType(2);
			activity4.setNumRepeats(3);
			activityService.save(activity4);
			
			// Activity 5
			Activity activity5 = new Activity();
			activity5.setName("Rural Tech Congress");
			activity5.setDescription("Cow Rural Tech Congress");
			activity5.setPlace("Hort de Lleida");
			activity5.setZone(zoneRepository.findByName(LLEIDA));
			activity5.setDateTimeStart(DATE_FORMAT.parse("30/01/2017 09:00"));
			activity5.setDateTimeEnd(DATE_FORMAT.parse("30/01/2017 14:00"));
			activity5.setManager(userRepository.findByEmail(MARIO));
			activity5.setType(2);
			activity5.setNumRepeats(2);
			activityService.save(activity5);
		
			// Activity 6
			Activity activity6 = new Activity();
			activity6.setName("Amazing Internet World");
			activity6.setDescription("Tech Congress");
			activity6.setPlace("Barcelona Fira");
			activity6.setZone(zoneRepository.findByName(BARCELONA));
			activity6.setDateTimeStart(DATE_FORMAT.parse("10/03/2017 09:00"));
			activity6.setDateTimeEnd(DATE_FORMAT.parse("10/03/2017 20:00"));
			activity6.setManager(userRepository.findByEmail(ALONSO));
			activity6.setType(2);
			activity6.setNumRepeats(3);
			activityService.save(activity6);
			
			// Activity 7
			Activity activity7 = new Activity();
			activity7.setName("Capital Hacking Conference");
			activity7.setDescription("Tech Congress");
			activity7.setPlace("Madrid Palacio de Deportes");
			activity7.setZone(zoneRepository.findByName(MADRID));
			activity7.setDateTimeStart(DATE_FORMAT.parse("20/03/2017 13:00"));
			activity7.setDateTimeEnd(DATE_FORMAT.parse("20/03/2017 21:00"));
			activity7.setManager(userRepository.findByEmail(GONZALO));
			activity7.setType(2);
			activity7.setNumRepeats(2);
			activityService.save(activity7);
			
			
			// Activity 8
			Activity activity8 = new Activity();
			activity8.setName("Girona Tour");
			activity8.setDescription("Congress");
			activity8.setPlace("Girona Centre");
			activity8.setZone(zoneRepository.findByName(GIRONA));
			activity8.setDateTimeStart(DATE_FORMAT.parse("24/03/2017 08:00"));
			activity8.setDateTimeEnd(DATE_FORMAT.parse("24/03/2017 10:00"));
			activity8.setManager(userRepository.findByEmail(ALONSO));
			activity8.setType(3);
			activity8.setNumRepeats(1);
			activityService.save(activity8);
			
			// Activity 9
			Activity activity9 = new Activity();
			activity9.setName("Blockchain Global");
			activity9.setDescription("Tech Congress");
			activity9.setPlace("Barcelona Centre");
			activity9.setZone(zoneRepository.findByName(BARCELONA));
			activity9.setDateTimeStart(DATE_FORMAT.parse("01/04/2017 08:00"));
			activity9.setDateTimeEnd(DATE_FORMAT.parse("01/04/2017 19:00"));
			activity9.setManager(userRepository.findByEmail(MARIO));
			activity9.setType(3);
			activity9.setNumRepeats(4);
			activityService.save(activity9);
			
			// Activity 10
			Activity activity10 = new Activity();
			activity10.setName("Identity Journey");
			activity10.setDescription("Identity Journey Congress");
			activity10.setPlace("Barcelona Fira");
			activity10.setZone(zoneRepository.findByName(BARCELONA));
			activity10.setDateTimeStart(DATE_FORMAT.parse("05/04/2017 08:30"));
			activity10.setDateTimeEnd(DATE_FORMAT.parse("05/04/2017 20:00"));
			activity10.setManager(userRepository.findByEmail(ALONSO));
			activity10.setType(1);
			activity10.setNumRepeats(1);
			activityService.save(activity10);
			
		} catch (ParseException e) {
			e.printStackTrace();
			
		}
	}
	
	private void loadZoneStatus() {
		
		User userGonzalo = userRepository.findByEmail(GONZALO);
		User userMario = userRepository.findByEmail(MARIO);
		User userDavid = userRepository.findByEmail(ALONSO);
		User userDiego = userRepository.findByEmail(DIEGO);
		User userJuan = userRepository.findByEmail(JUAN);
		User userVictor = userRepository.findByEmail(VICTOR);
		User userSamuel = userRepository.findByEmail(SAMUEL);
		User userTxema = userRepository.findByEmail(TXEMA);
		User userAnna = userRepository.findByEmail(ANNA);
		User userSergi = userRepository.findByEmail(SERGI);
		User userMarcos = userRepository.findByEmail(MARCOS);
		
		Zone zoneBarcelona = zoneRepository.findByName(BARCELONA);
		Zone zoneMadrid = zoneRepository.findByName(MADRID);
		Zone zoneGirona = zoneRepository.findByName(GIRONA);
		Zone zoneLleida = zoneRepository.findByName(LLEIDA);
		Zone zoneTarragona = zoneRepository.findByName(TARRAGONA);
		
		ZoneStatus zoneStatus = new ZoneStatus();
		zoneStatus.setStatus(1); // accepted
		zoneStatus.setUser(userGonzalo);
		zoneStatus.setZone(zoneBarcelona);
		zoneStatusRepository.save(zoneStatus);

		ZoneStatus zoneStatus1 = new ZoneStatus();
		zoneStatus1.setStatus(1); // accepted
		zoneStatus1.setUser(userGonzalo);
		zoneStatus1.setZone(zoneMadrid);
		zoneStatusRepository.save(zoneStatus1);
		
		ZoneStatus zoneStatus2 = new ZoneStatus();
		zoneStatus2.setStatus(1); // accepted
		zoneStatus2.setUser(userJuan);
		zoneStatus2.setZone(zoneBarcelona);
		zoneStatusRepository.save(zoneStatus2);

		ZoneStatus zoneStatus3 = new ZoneStatus();
		zoneStatus3.setStatus(1); // accepted
		zoneStatus3.setUser(userMario);
		zoneStatus3.setZone(zoneBarcelona);
		zoneStatusRepository.save(zoneStatus3);

		ZoneStatus zoneStatus4 = new ZoneStatus();
		zoneStatus4.setStatus(1); // accepted
		zoneStatus4.setUser(userAnna);
		zoneStatus4.setZone(zoneBarcelona);
		zoneStatusRepository.save(zoneStatus4);


		ZoneStatus zoneStatus5 = new ZoneStatus();
		zoneStatus5.setStatus(1); // accepted
		zoneStatus5.setUser(userAnna);
		zoneStatus5.setZone(zoneGirona);
		zoneStatusRepository.save(zoneStatus5);
		
		ZoneStatus zoneStatus6 = new ZoneStatus();
		zoneStatus6.setStatus(1); // accepted
		zoneStatus6.setUser(userAnna);
		zoneStatus6.setZone(zoneLleida);
		zoneStatusRepository.save(zoneStatus6);
		
		ZoneStatus zoneStatus7 = new ZoneStatus();
		zoneStatus7.setStatus(1); // accepted
		zoneStatus7.setUser(userAnna);
		zoneStatus7.setZone(zoneTarragona);
		zoneStatusRepository.save(zoneStatus7);
		
		ZoneStatus zoneStatus8 = new ZoneStatus();
		zoneStatus8.setStatus(1); // accepted
		zoneStatus8.setUser(userSergi);
		zoneStatus8.setZone(zoneBarcelona);
		zoneStatusRepository.save(zoneStatus8);

		ZoneStatus zoneStatus9 = new ZoneStatus();
		zoneStatus9.setStatus(1); // accepted
		zoneStatus9.setUser(userTxema);
		zoneStatus9.setZone(zoneBarcelona);
		zoneStatusRepository.save(zoneStatus9);
		
		ZoneStatus zoneStatus10 = new ZoneStatus();
		zoneStatus10.setStatus(1); // accepted
		zoneStatus10.setUser(userVictor);
		zoneStatus10.setZone(zoneGirona);
		zoneStatusRepository.save(zoneStatus10);	
		
		ZoneStatus zoneStatus11 = new ZoneStatus();
		zoneStatus11.setStatus(1); // accepted
		zoneStatus11.setUser(userDavid);
		zoneStatus11.setZone(zoneBarcelona);
		zoneStatusRepository.save(zoneStatus11);
		
		ZoneStatus zoneStatus12 = new ZoneStatus();
		zoneStatus12.setStatus(1); // accepted
		zoneStatus12.setUser(userDiego);
		zoneStatus12.setZone(zoneTarragona);
		zoneStatusRepository.save(zoneStatus12);
		
		ZoneStatus zoneStatus13 = new ZoneStatus();
		zoneStatus13.setStatus(1); // accepted
		zoneStatus13.setUser(userDiego);
		zoneStatus13.setZone(zoneBarcelona);
		zoneStatusRepository.save(zoneStatus13);
		
		ZoneStatus zoneStatus14 = new ZoneStatus();
		zoneStatus14.setStatus(1); // accepted
		zoneStatus14.setUser(userSamuel);
		zoneStatus14.setZone(zoneBarcelona);
		zoneStatusRepository.save(zoneStatus14);
		
		ZoneStatus zoneStatus15 = new ZoneStatus();
		zoneStatus15.setStatus(1); // accepted
		zoneStatus15.setUser(userMarcos);
		zoneStatus15.setZone(zoneBarcelona);
		zoneStatusRepository.save(zoneStatus15);
	}

	private void loadZonesData() {
		
		Zone bar = new Zone(BARCELONA);
		//bar.setManager(newArrayList(userRepository.findByEmail(GONZALO)));
				
		Zone gir = new Zone(GIRONA);
		//gir.setManager(newArrayList(userRepository.findByEmail(GONZALO)));
		
		Zone tar = new Zone(TARRAGONA);
		//tar.setManager(newArrayList(userRepository.findByEmail(MARIO)));
		
		Zone lle = new Zone(LLEIDA);
		//lle.setManager(newArrayList(userRepository.findByEmail(MARIO)));
		
		Zone mad = new Zone(MADRID);
		//mad.setManager(newArrayList(userRepository.findByEmail(MARIO)));

		zoneRepository.save(bar);
		zoneRepository.save(gir);
		zoneRepository.save(tar);
		zoneRepository.save(lle);
		zoneRepository.save(mad);
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
		user1.setCity(BARCELONA);
		user1.setRegistered(true);
		userRepository.save(user1);
		
		User user2 = new User(
				"Gonzalo", 
				"Asensio", 
				new Password(passwordEncoder.encode("123456")), 
				GONZALO,
				admin,
				new UserConfiguration(0,0));
		user2.setCity(MADRID);
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
				SAMUEL,
				user,
				new UserConfiguration(0,0));
		user5.setCity(BARCELONA);
		user5.setRegistered(true);
		userRepository.save(user5);

		
		User user6 = new User(
				"Diego", 
				"Delgado", 
				new Password(passwordEncoder.encode("123456")), 
				DIEGO,
				user,
				new UserConfiguration(0,0));
		user6.setCity(BARCELONA);
		user6.setRegistered(true);
		userRepository.save(user6);
		
		
		User user7 = new User(
				"Marcos", 
				"Repolles", 
				new Password(passwordEncoder.encode("123456")), 
				MARCOS,
				user,
				new UserConfiguration(0,0));
		user7.setCity(BARCELONA);
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
		user10.setCity(BARCELONA);
		user10.setRegistered(false);
		userRepository.save(user10);
		
		
		User user11 = new User(
				"Anna",
				"Noell",
				new Password(passwordEncoder.encode("123456")),
				ANNA,
				anonymous,
				new UserConfiguration(0,0));
		user11.setCity(BARCELONA);
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

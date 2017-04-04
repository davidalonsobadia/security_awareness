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
import org.security_awareness.utils.DateFormatSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
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
	private static final String JUAN = "juan.caubet@eurecat.org";
	private static final String VICTOR = "victor_50@mail.com";
	private static final String MARCOS = "marcos_50@mail.com";
	private static final String ANNA = "anna@weappyou.com";
	private static final String TXEMA = "txema_50@mail.com";
	private static final String SAMUEL = "samuel_50@mail.com";
	private static final String DIEGO = "diego_50@mail.com";
	
	private static final String PAU = "pau.li@eurecat.org";
	private static final String RAMSES = "ramses_50@mail.com";
	private static final String SERGI_ROVIRA = "sergi.rovira@eurecat.org";
	private static final String AITOR = "aitor_50@mail.com";
	private static final String CARME = "carme.zambrana@eurecat.org";
	private static final String MIREIA = "mireia.claramun@eurecat.org";
	private static final String MARIONA = "mariona_50@mail.com";
	private static final String ROSA = "rosa_50@mail.com";
	private static final String JORDI = "jordi_50@mail.com";
	private static final String CLARA = "clara_50@mail.com";
	private static final String MONTSE = "montse_50@mail.com";
	private static final String PABLO = "pablo_50@mail.com";
	private static final String NURIA = "nuria_50@mail.com";
	private static final String NEUS = "neus_50@mail.com";
	private static final String ADRIAN = "adrian_50@mail.com";
	private static final String CRISTINA = "cristina_50@mail.com";
		
	private static final String DEFAULT_PASSWORD = "Awareness2017";
	
	private static final String BARCELONA = "Barcelona";
	private static final String MADRID = "Madrid";
	private static final String LLEIDA = "Lleida";
	private static final String TARRAGONA = "Tarragona";
	private static final String GIRONA = "Girona";
	
	private static final String ZARAGOZA = "Zaragoza";
	private static final String TERUEL = "Teruel";
	private static final String VALENCIA = "Valencia";

	
	private static final SimpleDateFormat DATE_FORMAT = DateFormatSingleton.getDateFormat();
	
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
//		loadSecurityContext();
//		loadUsersData();
//		loadZonesData();
//		loadZoneStatus();
//		loadActivities();
//		loadActivityStatus();
//		loadNotifications();
//		loadResources();
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
		
		ActivityStatus activityStatus11 = new ActivityStatus();
		activityStatus11.setActivity(activityService.findOne(6));
		activityStatus11.setUser(userRepository.findByEmail(DIEGO));
		activityStatus11.setInterested(false);
		activityStatus11.setAssistant(true);
		activityStatusRepository.save(activityStatus11);
		
		ActivityStatus activityStatus12 = new ActivityStatus();
		activityStatus12.setActivity(activityService.findOne(6));
		activityStatus12.setUser(userRepository.findByEmail(SERGI));
		activityStatus12.setInterested(false);
		activityStatus12.setAssistant(true);
		activityStatusRepository.save(activityStatus12);
		
		ActivityStatus activityStatus13 = new ActivityStatus();
		activityStatus13.setActivity(activityService.findOne(4));
		activityStatus13.setUser(userRepository.findByEmail(MARCOS));
		activityStatus13.setInterested(false);
		activityStatus13.setAssistant(true);
		activityStatusRepository.save(activityStatus13);
		
		ActivityStatus activityStatus14 = new ActivityStatus();
		activityStatus14.setActivity(activityService.findOne(4));
		activityStatus14.setUser(userRepository.findByEmail(TXEMA));
		activityStatus14.setInterested(false);
		activityStatus14.setAssistant(true);
		activityStatusRepository.save(activityStatus14);
		
		ActivityStatus activityStatus15 = new ActivityStatus();
		activityStatus15.setActivity(activityService.findOne(3));
		activityStatus15.setUser(userRepository.findByEmail(TXEMA));
		activityStatus15.setInterested(true);
		activityStatus15.setAssistant(false);
		activityStatusRepository.save(activityStatus15);
		
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
			
			// Activity 11
			Activity activity11 = new Activity();
			activity11.setName("How to grow your business with an API");
			activity11.setDescription("Hey all! For the 2nd product manager meetup of the year, we’ll be focusing on API’s, and how to use them effectively to grow your business. API’s offers endless possibilities for your product, but which to use, what is right for your product and how to find them. With us we have the CTO of Hitch, Bruno Pedro, the startup that connects API’s and companies. He will share insights and experience as he’s working closely with both parties.");
			activity11.setPlace("Eurecat Headquarters");
			activity11.setZone(zoneRepository.findByName(BARCELONA));
			activity11.setDateTimeStart(DATE_FORMAT.parse("01/04/2017 18:00"));
			activity11.setDateTimeEnd(DATE_FORMAT.parse("01/04/2017 21:00"));
			activity11.setManager(userRepository.findByEmail(ALONSO));
			activity11.setType(2);
			activity11.setNumRepeats(0);
			activityService.save(activity11);
			
			// Activity 12
			Activity activity12 = new Activity();
			activity12.setName("Stack Tuesday");
			activity12.setDescription("Description Here");
			activity12.setPlace("Eurecat Headquarters");
			activity12.setZone(zoneRepository.findByName(BARCELONA));
			activity12.setDateTimeStart(DATE_FORMAT.parse("04/04/2017 15:30"));
			activity12.setDateTimeEnd(DATE_FORMAT.parse("04/04/2017 20:00"));
			activity12.setManager(userRepository.findByEmail(GONZALO));
			activity12.setType(2);
			activity12.setNumRepeats(0);
			activityService.save(activity12);
			
			// Activity 13
			Activity activity13 = new Activity();
			activity13.setName("Tech Experience Conference Madrid");
			activity13.setDescription("test of new Congress");
			activity13.setPlace("Madrid");
			activity13.setZone(zoneRepository.findByName(MADRID));
			activity13.setDateTimeStart(DATE_FORMAT.parse("12/05/2017 09:00"));
			activity13.setDateTimeEnd(DATE_FORMAT.parse("12/05/2017 19:00"));
			activity13.setManager(userRepository.findByEmail(GONZALO));
			activity13.setType(1);
			activity13.setNumRepeats(5);
			activityService.save(activity13);
			
			// Activity 14
			Activity activity14 = new Activity();
			activity14.setName("Maño Congress");
			activity14.setDescription("Zaragoza Congress");
			activity14.setPlace("Los pilares Restaurant");
			activity14.setZone(zoneRepository.findByName(ZARAGOZA));
			activity14.setDateTimeStart(DATE_FORMAT.parse("30/04/2017 06:45"));
			activity14.setDateTimeEnd(DATE_FORMAT.parse("30/04/2017 19:00"));
			activity14.setManager(userRepository.findByEmail(ALONSO));
			activity14.setType(1);
			activity14.setNumRepeats(3);
			activityService.save(activity14);
			
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
		User userPau = userRepository.findByEmail(PAU);
		User userRamses = userRepository.findByEmail(RAMSES);
		User userSergiRovira = userRepository.findByEmail(SERGI_ROVIRA);
		
		Zone zoneBarcelona = zoneRepository.findByName(BARCELONA);
		Zone zoneMadrid = zoneRepository.findByName(MADRID);
		Zone zoneGirona = zoneRepository.findByName(GIRONA);
		Zone zoneLleida = zoneRepository.findByName(LLEIDA);
		Zone zoneTarragona = zoneRepository.findByName(TARRAGONA);
		Zone zoneZaragoza= zoneRepository.findByName(ZARAGOZA);
		Zone zoneTeruel = zoneRepository.findByName(TERUEL);
		Zone zoneValencia= zoneRepository.findByName(VALENCIA);
		
		ZoneStatus zoneStatusAdmins = new ZoneStatus();
		zoneStatusAdmins.setStatus(2); // admin
		zoneStatusAdmins.setUser(userDavid);
		zoneStatusAdmins.setZone(zoneBarcelona);
		zoneStatusRepository.save(zoneStatusAdmins);
		
		ZoneStatus zoneStatusAdmins2 = new ZoneStatus();
		zoneStatusAdmins2.setStatus(2); // admin
		zoneStatusAdmins2.setUser(userGonzalo);
		zoneStatusAdmins2.setZone(zoneMadrid);
		zoneStatusRepository.save(zoneStatusAdmins2);
		
		ZoneStatus zoneStatusAdmins3 = new ZoneStatus();
		zoneStatusAdmins3.setStatus(2); // admin
		zoneStatusAdmins3.setUser(userMario);
		zoneStatusAdmins3.setZone(zoneGirona);
		zoneStatusRepository.save(zoneStatusAdmins3);
		
		ZoneStatus zoneStatusAdmins4 = new ZoneStatus();
		zoneStatusAdmins4.setStatus(2); // admin
		zoneStatusAdmins4.setUser(userDiego);
		zoneStatusAdmins4.setZone(zoneLleida);
		zoneStatusRepository.save(zoneStatusAdmins4);
		
		ZoneStatus zoneStatusAdmins5 = new ZoneStatus();
		zoneStatusAdmins5.setStatus(2); // admin
		zoneStatusAdmins5.setUser(userSamuel);
		zoneStatusAdmins5.setZone(zoneTarragona);
		zoneStatusRepository.save(zoneStatusAdmins5);
		
		ZoneStatus zoneStatusAdmins6 = new ZoneStatus();
		zoneStatusAdmins6.setStatus(2); // admin
		zoneStatusAdmins6.setUser(userPau);
		zoneStatusAdmins6.setZone(zoneZaragoza);
		zoneStatusRepository.save(zoneStatusAdmins6);
		
		ZoneStatus zoneStatusAdmins7 = new ZoneStatus();
		zoneStatusAdmins7.setStatus(2); // admin
		zoneStatusAdmins7.setUser(userRamses);
		zoneStatusAdmins7.setZone(zoneTeruel);
		zoneStatusRepository.save(zoneStatusAdmins7);
		
		ZoneStatus zoneStatusAdmins8 = new ZoneStatus();
		zoneStatusAdmins8.setStatus(2); // admin
		zoneStatusAdmins8.setUser(userSergiRovira);
		zoneStatusAdmins8.setZone(zoneValencia);
		zoneStatusRepository.save(zoneStatusAdmins8);
		
		ZoneStatus zoneStatus = new ZoneStatus();
		zoneStatus.setStatus(1); // accepted
		zoneStatus.setUser(userGonzalo);
		zoneStatus.setZone(zoneBarcelona);
		zoneStatusRepository.save(zoneStatus);

//		ZoneStatus zoneStatus1 = new ZoneStatus();
//		zoneStatus1.setStatus(1); // accepted
//		zoneStatus1.setUser(userGonzalo);
//		zoneStatus1.setZone(zoneMadrid);
//		zoneStatusRepository.save(zoneStatus1);
		
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
		
//		ZoneStatus zoneStatus11 = new ZoneStatus();
//		zoneStatus11.setStatus(1); // accepted
//		zoneStatus11.setUser(userDavid);
//		zoneStatus11.setZone(zoneBarcelona);
//		zoneStatusRepository.save(zoneStatus11);
		
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
		Zone gir = new Zone(GIRONA);
		Zone tar = new Zone(TARRAGONA);
		Zone lle = new Zone(LLEIDA);
		Zone mad = new Zone(MADRID);
		Zone zar = new Zone(ZARAGOZA);
		Zone ter = new Zone(TERUEL);
		Zone val = new Zone(VALENCIA);

		zoneRepository.save(bar);
		zoneRepository.save(gir);
		zoneRepository.save(tar);
		zoneRepository.save(lle);
		zoneRepository.save(mad);
		zoneRepository.save(zar);
		zoneRepository.save(ter);
		zoneRepository.save(val);
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
				new Password(passwordEncoder.encode(DEFAULT_PASSWORD)), // password
				ALONSO, // email
				superadmin,
				new UserConfiguration(4,4));
		user1.setCity(BARCELONA);
		userRepository.save(user1);
		
		User user2 = new User(
				"Gonzalo", 
				"Asensio", 
				new Password(passwordEncoder.encode(DEFAULT_PASSWORD)), 
				GONZALO,
				admin,
				new UserConfiguration(4,4));
		user2.setCity(MADRID);
		userRepository.save(user2);
		
		User user3 = new User(
				"Mario", 
				"Reyes", 
				new Password(passwordEncoder.encode(DEFAULT_PASSWORD)), 
				MARIO,
				admin,
				new UserConfiguration(3,4));
		user3.setCity("Fraga");
		userRepository.save(user3);
		
		
		User user4 = new User(
				"Sergi", 
				"Alonso", 
				new Password(passwordEncoder.encode(DEFAULT_PASSWORD)), 
				SERGI,
				admin,
				new UserConfiguration(3,4));
		user4.setCity("Terrassa");
		userRepository.save(user4);
		
		User user5 = new User(
				"Samuel", 
				"Exposito", 
				new Password(passwordEncoder.encode(DEFAULT_PASSWORD)), 
				SAMUEL,
				admin,
				new UserConfiguration(3,4));
		user5.setCity(BARCELONA);
		userRepository.save(user5);

		
		User user6 = new User(
				"Diego", 
				"Delgado", 
				new Password(passwordEncoder.encode(DEFAULT_PASSWORD)), 
				DIEGO,
				admin,
				new UserConfiguration(2,0));
		user6.setCity(BARCELONA);
		userRepository.save(user6);
		
		
		User user7 = new User(
				"Marcos", 
				"Repolles", 
				new Password(passwordEncoder.encode(DEFAULT_PASSWORD)), 
				MARCOS,
				anonymous,
				new UserConfiguration(1,0));
		user7.setCity(BARCELONA);
		userRepository.save(user7);

		
		User user8 = new User(
				"Txema", 
				"Romeria", 
				new Password(passwordEncoder.encode(DEFAULT_PASSWORD)), 
				TXEMA,
				user,
				new UserConfiguration(1,0));
		user8.setCity("Guipuzcoa");
		userRepository.save(user8);

		
		User user9 = new User(
				"Juan", 
				"Caubet", 
				new Password(passwordEncoder.encode(DEFAULT_PASSWORD)), 
				JUAN,
				anonymous,
				new UserConfiguration(0,0));
		user9.setCity("Montanya");
		userRepository.save(user9);


		User user10 = new User(
				"Victor",
				"Pomareda",
				new Password(passwordEncoder.encode(DEFAULT_PASSWORD)),
				VICTOR,
				anonymous,
				new UserConfiguration(4,4));
		user10.setCity(GIRONA);
		userRepository.save(user10);
		
		
		User user11 = new User(
				"Anna",
				"Noell",
				new Password(passwordEncoder.encode(DEFAULT_PASSWORD)),
				ANNA,
				user,
				new UserConfiguration(0,0));
		user11.setCity(BARCELONA);
		userRepository.save(user11);
		
		User user12 = new User(
				"Pau",
				"Li",
				new Password(passwordEncoder.encode(DEFAULT_PASSWORD)),
				PAU,
				admin,
				new UserConfiguration(4,1));
		user12.setCity("Sant Cugat del Vallès");
		userRepository.save(user12);	
		
		User user13 = new User(
				"Ramses",
				"Unknown",
				new Password(passwordEncoder.encode(DEFAULT_PASSWORD)),
				RAMSES,
				admin,
				new UserConfiguration(3,0));
		user13.setCity(TARRAGONA);
		userRepository.save(user13);	
		
		User user14 = new User(
				"Sergi",
				"Rovira",
				new Password(passwordEncoder.encode(DEFAULT_PASSWORD)),
				SERGI_ROVIRA,
				admin,
				new UserConfiguration(2,2));
		user14.setCity(GIRONA);
		userRepository.save(user14);	
		
		User user15 = new User(
				"Aitor",
				"Menta",
				new Password(passwordEncoder.encode(DEFAULT_PASSWORD)),
				AITOR,
				user,
				new UserConfiguration(4,4));
		user15.setCity(TARRAGONA);
		userRepository.save(user15);	
		
		User user16 = new User(
				"Carme",
				"Zambrana",
				new Password(passwordEncoder.encode(DEFAULT_PASSWORD)),
				CARME,
				user,
				new UserConfiguration(4,3));
		user16.setCity("Palma de Mallorca");
		userRepository.save(user16);	
		
		User user17 = new User(
				"Mireia",
				"Claramunt",
				new Password(passwordEncoder.encode(DEFAULT_PASSWORD)),
				MIREIA,
				user,
				new UserConfiguration(4,4));
		user17.setCity(LLEIDA);
		userRepository.save(user17);	
		
		User user18 = new User(
				"Mariona",
				"Garcia",
				new Password(passwordEncoder.encode(DEFAULT_PASSWORD)),
				MARIONA,
				user,
				new UserConfiguration(4,4));
		user18.setCity("Badia del Valles");
		userRepository.save(user18);	
		
		User user19 = new User(
				"Rosa",
				"Garcia",
				new Password(passwordEncoder.encode(DEFAULT_PASSWORD)),
				ROSA,
				user,
				new UserConfiguration(4,3));
		user19.setCity("La Garriga");
		userRepository.save(user19);	
		
		User user20 = new User(
				"Jordi",
				"Garcia",
				new Password(passwordEncoder.encode(DEFAULT_PASSWORD)),
				JORDI,
				user,
				new UserConfiguration(4,4));
		user20.setCity(BARCELONA);
		userRepository.save(user20);	
		
		User user21 = new User(
				"Clara",
				"Garcia",
				new Password(passwordEncoder.encode(DEFAULT_PASSWORD)),
				CLARA,
				user,
				new UserConfiguration(4,4));
		user21.setCity("Reus");
		userRepository.save(user21);	
		
		User user22 = new User(
				"Montse",
				"Martinez",
				new Password(passwordEncoder.encode(DEFAULT_PASSWORD)),
				MONTSE,
				user,
				new UserConfiguration(4,1));
		user22.setCity("Blanes");
		userRepository.save(user22);	
		
		User user23 = new User(
				"Pablo",
				"Badia",
				new Password(passwordEncoder.encode(DEFAULT_PASSWORD)),
				PABLO,
				user,
				new UserConfiguration(4,4));
		user23.setCity("Castellar");
		userRepository.save(user23);
		
		User user24 = new User(
				"Nuria",
				"Marcet",
				new Password(passwordEncoder.encode(DEFAULT_PASSWORD)),
				NURIA,
				user,
				new UserConfiguration(4,4));
		user24.setCity("Rubi");
		userRepository.save(user24);
		
		User user25 = new User(
				"Neus",
				"Castillo",
				new Password(passwordEncoder.encode(DEFAULT_PASSWORD)),
				NEUS,
				anonymous,
				new UserConfiguration(4,4));
		user25.setCity("Sabadell");
		userRepository.save(user25);
		
		User user26 = new User(
				"Adrian",
				"Caubet",
				new Password(passwordEncoder.encode(DEFAULT_PASSWORD)),
				ADRIAN,
				anonymous,
				new UserConfiguration(4,4));
		user26.setCity("La Palma de Cervelló");
		userRepository.save(user26);
		
		User user27 = new User(
				"Cristina",
				"Caubet",
				new Password(passwordEncoder.encode(DEFAULT_PASSWORD)),
				CRISTINA,
				anonymous,
				new UserConfiguration(4,4));
		user27.setCity("La Palma de Cervelló");
		userRepository.save(user27);
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

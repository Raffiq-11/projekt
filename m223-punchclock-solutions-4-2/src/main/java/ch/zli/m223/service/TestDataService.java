package ch.zli.m223.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.model.Booking;
import ch.zli.m223.model.Category;
import ch.zli.m223.model.Entry;
import ch.zli.m223.model.Status;
import ch.zli.m223.model.Tag;
import ch.zli.m223.model.User;
import io.quarkus.arc.profile.IfBuildProfile;
import io.quarkus.runtime.StartupEvent;

@IfBuildProfile("dev")
@ApplicationScoped
public class TestDataService {

  @Inject
  EntityManager entityManager;

  @Transactional
  void generateTestData(@Observes StartupEvent event) {
    // Booking
    var firstBooking = new Booking();
    var secondBooking = new Booking();
    var thirdBooking = new Booking();

    
    // Users
    var firstUser = new User();
    firstUser.setFirstname("Peter");
    firstUser.setLastname("Bauer");
    firstUser.setEmail("peter@gmail.com");
    firstUser.setPassword("password123");
    firstUser.setActive(true);
    firstUser.setIsAdmin(false);
    firstUser.setBooking(new HashSet<>(Arrays.asList(firstBooking)));
    entityManager.persist(firstUser);


    var secondUser = new User();
    secondUser.setFirstname("Hans");
    secondUser.setLastname("kuh");
    secondUser.setEmail("hans@gmail.com");
    secondUser.setPassword("password888");
    secondUser.setActive(false);
    secondUser.setIsAdmin(false);
    secondUser.setBooking(new HashSet<>(Arrays.asList(secondBooking)));
    entityManager.persist(secondUser);



    var thirdUser = new User();
    thirdUser.setFirstname("Admin");
    thirdUser.setLastname("Admin123");
    thirdUser.setEmail("Admin.service@gmail.com");
    thirdUser.setPassword("password111");
    thirdUser.setActive(true);
    thirdUser.setIsAdmin(true);
    thirdUser.setBooking(new HashSet<>(Arrays.asList(thirdBooking)));
    entityManager.persist(thirdUser);


    
   // Status
   var firstStatus = new Status();
   firstStatus.setIsConfirmed(true);
   firstStatus.setBooking(firstBooking);
   entityManager.persist(firstStatus);


   var secondStatus = new Status();
   secondStatus.setIsConfirmed(true);
   secondStatus.setBooking(secondBooking);
   entityManager.persist(secondStatus);


   var thirdStatus = new Status();
   thirdStatus.setIsConfirmed(true);
   thirdStatus.setBooking(thirdBooking);
   entityManager.persist(thirdStatus);


   // Booking
   firstBooking.setDate(LocalDate.now());
   firstBooking.setActive(true);
   firstBooking.setIsFullDay(true);
   firstBooking.setIsAccepted(true);
   firstBooking.setUser(firstUser);
   firstBooking.setStatus(firstStatus);
   entityManager.persist(firstBooking);

   
   secondBooking.setDate(LocalDate.now());
   secondBooking.setActive(true);
   secondBooking.setIsFullDay(true);
   secondBooking.setIsAccepted(true);
   secondBooking.setUser(secondUser);
   secondBooking.setStatus(secondStatus);
   entityManager.persist(secondBooking);


   thirdBooking.setDate(LocalDate.now());
   thirdBooking.setActive(true);
   thirdBooking.setIsFullDay(true);
   thirdBooking.setIsAccepted(true);
   thirdBooking.setUser(thirdUser);
   thirdBooking.setStatus(thirdStatus);
   entityManager.persist(thirdBooking);





    // Categories
    var projectACategory = new Category();
    projectACategory.setTitle("Project A");
    entityManager.persist(projectACategory);

    var projectBCategory = new Category();
    projectBCategory.setTitle("Project B");
    entityManager.persist(projectBCategory);

    var projectCCategory = new Category();
    projectCCategory.setTitle("Project C");
    entityManager.persist(projectCCategory);

    // Tags
    var programmingTag = new Tag();
    programmingTag.setTitle("Programming");
    entityManager.persist(programmingTag);

    var debuggingTag = new Tag();
    debuggingTag.setTitle("Debugging");
    entityManager.persist(debuggingTag);

    var meetingTag = new Tag();
    meetingTag.setTitle("Meeting");
    entityManager.persist(meetingTag);

    // Entries
    var firstEntry = new Entry();
    firstEntry.setCategory(projectACategory);
    firstEntry.setTags(new HashSet<>(Arrays.asList(programmingTag, debuggingTag)));
    firstEntry.setCheckIn(LocalDateTime.now().minusHours(3));
    firstEntry.setCheckOut(LocalDateTime.now().minusHours(2));
    entityManager.persist(firstEntry);

    var secondEntry = new Entry();
    secondEntry.setCategory(projectACategory);
    secondEntry.setTags(new HashSet<>(Arrays.asList(meetingTag)));
    secondEntry.setCheckIn(LocalDateTime.now().minusHours(2));
    secondEntry.setCheckOut(LocalDateTime.now().minusHours(1));
    entityManager.persist(secondEntry);

    var thirdEntry = new Entry();
    thirdEntry.setCategory(projectBCategory);
    thirdEntry.setTags(new HashSet<>(Arrays.asList(programmingTag)));
    thirdEntry.setCheckIn(LocalDateTime.now().minusHours(1));
    thirdEntry.setCheckOut(LocalDateTime.now());
    entityManager.persist(thirdEntry);
  }
}

import java.util.*;

class Applicant {
    private String name;
    private String email;
    private String phone;
    private Date registrationDate;

    public Applicant(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.registrationDate = new Date();  // Store registration time
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public static boolean isValidEmail(String email) {
        return email != null && email.contains("@");
    }

    public static boolean isValidPhone(String phone) {
        return phone != null && phone.length() == 10 && phone.matches("[0-9]+");
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Email: " + email + ", Phone: " + phone + ", Registration Date: " + registrationDate;
    }
}

class Event {
    private String eventName;
    private String date;
    private String eventType;
    private final List<Applicant> registeredApplicants;
    private String location;
    private String description;
    private String organizer;

    public void updateEventDetails(String newDate, String newType, String newLocation, String newDescription) {
        this.date = newDate;
        this.eventType = newType;
        this.location = newLocation;
        this.description = newDescription;
        System.out.println("Event details updated successfully.");
}
public boolean removeApplicant(Applicant applicant) {
    if (registeredApplicants.contains(applicant)) {
        registeredApplicants.remove(applicant);
        System.out.println("Applicant " + applicant.getName() + " removed successfully.");
        return true;
    } else {
        System.out.println("Applicant not found.");
        return false;
    }
}
    public Event(String eventName, String date, String eventType) {
        this.eventName = eventName;
        this.date = date;
        this.eventType = eventType;
        this.registeredApplicants = new ArrayList<>();
    }

    public String getEventName() {
        return eventName;
    }

    public String getDate() {
        return date;
    }

    public String getEventType() {
        return eventType;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public void registerApplicant(Applicant applicant) {
        this.registeredApplicants.add(applicant);
    }

    public List<Applicant> getRegisteredApplicants() {
        return registeredApplicants;
    }

    public void displayRegisteredApplicants() {
        if (registeredApplicants.isEmpty()) {
            System.out.println("No applicants registered for " + eventName);
        } else {
            Collections.sort(registeredApplicants, Comparator.comparing(Applicant::getRegistrationDate));
            System.out.println("Registered applicants for " + eventName + ":");
            for (Applicant applicant : registeredApplicants) {
                System.out.println(applicant);
            }
        }
    }

    public void generateReport() {
        System.out.println("\nReport for Event: " + eventName);
        System.out.println("Event Type: " + eventType);
        System.out.println("Location: " + location);
        System.out.println("Description: " + description);
        System.out.println("Organizer: " + organizer);
        System.out.println("Total Registrations: " + registeredApplicants.size());
        System.out.println("List of Registered Applicants:");
        for (Applicant applicant : registeredApplicants) {
            System.out.println(applicant);
        }
    }

    // Sorting applicants by name (Alphabetically)
    public void sortApplicantsByName() {
        Collections.sort(registeredApplicants, Comparator.comparing(Applicant::getName));
    }
}

class College {
    private String name;
    private List<Event> events;

    public College(String name) {
        this.name = name;
        this.events = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addEvent(Event event) {
        this.events.add(event);
    }

    public Event findEvent(String eventName) {
        for (Event event : events) {
            if (event.getEventName().equalsIgnoreCase(eventName)) {
                return event;
            }
        }
        return null;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void displayAllEvents() {
        if (events.isEmpty()) {
            System.out.println("No events registered for " + name);
        } else {
            System.out.println("Events for College: " + name);
            for (Event event : events) {
                System.out.println("Event Name: " + event.getEventName() + ", Type: " + event.getEventType() + ", Date: " + event.getDate());
            }
        }
    }
}
class User {
    private String username;
    private String password;
    private String role; // Admin or Regular User

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}

public class RegistrationSystem {
    private static Map<String, College> colleges = new HashMap<>();
    private static Map<String, User> users = new HashMap<>();
    private static User currentUser;

    // Admin login
    public static void updateEventDetails(College college) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Event Name to update: ");
        String eventName = scanner.nextLine();
        Event event = college.findEvent(eventName);
        if (event != null) {
            System.out.print("Enter new Event Date: ");
            String newDate = scanner.nextLine();
            System.out.print("Enter new Event Type: ");
            String newType = scanner.nextLine();
            System.out.print("Enter new Location: ");
            String newLocation = scanner.nextLine();
            System.out.print("Enter new Description: ");
            String newDescription = scanner.nextLine();
            event.updateEventDetails(newDate, newType, newLocation, newDescription);
        } else {
            System.out.println("Event not found.");
        }
    }
    public static void removeApplicantFromEvent(College college) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Event Name to remove applicant from: ");
        String eventName = scanner.nextLine();
        Event event = college.findEvent(eventName);
        if (event != null) {
            System.out.print("Enter Applicant Name to remove: ");
            String applicantName = scanner.nextLine();
            Applicant applicantToRemove = null;
            for (Applicant applicant : event.getRegisteredApplicants()) {
                if (applicant.getName().equalsIgnoreCase(applicantName)) {
                    applicantToRemove = applicant;
                    break;
                }
            }
            if (applicantToRemove != null) {
                event.removeApplicant(applicantToRemove);
            } else {
                System.out.println("Applicant not found.");
            }
        } else {
            System.out.println("Event not found.");
        }
    }
    public static boolean loginAdmin(String username, String password) {
        User user = users.get(username);
        return user != null && user.getRole().equals("Admin") && user.getPassword().equals(password);
    }

    // Regular User login
    public static boolean loginUser(String username, String password) {
        User user = users.get(username);
        return user != null && user.getRole().equals("User") && user.getPassword().equals(password);
    }

    public static College createCollege(String collegeName) {
        College college = new College(collegeName);
        colleges.put(collegeName, college);
        return college;
    }

    public static College findCollege(String collegeName) {
        return colleges.get(collegeName);
    }

    public static Applicant getApplicantFromInput() {
        Scanner scanner = new Scanner(System.in);
        String name, email, phone;

        // Name input validation
        while (true) {
            System.out.print("Enter Applicant Name: ");
            name = scanner.nextLine();
            if (!name.isEmpty()) break;
            System.out.println("Name cannot be empty!");
        }

        // Email validation
        while (true) {
            System.out.print("Enter Email: ");
            email = scanner.nextLine();
            if (Applicant.isValidEmail(email)) break;
            System.out.println("Invalid email format. Please try again.");
        }

        // Phone validation
        while (true) {
            System.out.print("Enter Phone (10 digits): ");
            phone = scanner.nextLine();
            if (Applicant.isValidPhone(phone)) break;
            System.out.println("Invalid phone format. Please try again.");
        }

        return new Applicant(name, email, phone);
    }

    public static void registerApplicant(Event event, Applicant applicant) {
        event.registerApplicant(applicant);
        System.out.println("Applicant " + applicant.getName() + " registered successfully.");
    }

    public static void collegeMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nCollege Menu:");
            System.out.println("1. Create a New College");
            System.out.println("2. Select an Existing College");
            System.out.println("3. Exit");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter the College Name: ");
                    String collegeName = scanner.nextLine();
                    College college = createCollege(collegeName);
                    eventMenu(college);
                    break;
                case 2:
                    System.out.print("Enter the College Name to select: ");
                    String existingCollegeName = scanner.nextLine();
                    College existingCollege = findCollege(existingCollegeName);
                    if (existingCollege != null) {
                        eventMenu(existingCollege);
                    } else {
                        System.out.println("College not found.");
                    }
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void eventMenu(College college) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nEvent Menu for " + college.getName() + ":");
            System.out.println("1. Register Applicant");
            System.out.println("2. Display Registered Applicants");
            System.out.println("3. Generate Event Report");
            System.out.println("4. Sort Applicants by Name");
            if (currentUser != null && currentUser.getRole().equals("Admin")) {
                System.out.println("5. Add New Event");
                System.out.println("6. Delete Event");
            }
            System.out.println("7. Back to College Menu");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Event Name to register for: ");
                    String eventName = scanner.nextLine();
                    Event event = college.findEvent(eventName);
                    if (event != null) {
                        Applicant applicant = getApplicantFromInput();
                        registerApplicant(event, applicant);
                    } else {
                        System.out.println("Event not found.");
                    }
                    break;
                case 2:
                    System.out.print("Enter Event Name to view applicants: ");
                    eventName = scanner.nextLine();
                    event = college.findEvent(eventName);
                    if (event != null) {
                        event.displayRegisteredApplicants();
                    } else {
                        System.out.println("Event not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter Event Name to generate report: ");
                    eventName = scanner.nextLine();
                    event = college.findEvent(eventName);
                    if (event != null) {
                        event.generateReport();
                    } else {
                        System.out.println("Event not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter Event Name to sort applicants: ");
                    eventName = scanner.nextLine();
                    event = college.findEvent(eventName);
                    if (event != null) {
                        event.sortApplicantsByName();
                        System.out.println("Applicants sorted by name.");
                    } else {
                        System.out.println("Event not found.");
                    }
                    break;
                case 5:
                    if (currentUser != null && currentUser.getRole().equals("Admin")) {
                        System.out.print("Enter Event Name to add: ");
                        eventName = scanner.nextLine();
                        System.out.print("Enter Event Date: ");
                        String date = scanner.nextLine();
                        System.out.print("Enter Event Type: ");
                        String eventType = scanner.nextLine();
                        Event newEvent = new Event(eventName, date, eventType);
                        college.addEvent(newEvent);
                        System.out.println("Event added successfully.");
                    }
                    break;
                case 6:
                    if (currentUser != null && currentUser.getRole().equals("Admin")) {
                        System.out.print("Enter Event Name to delete: ");
                        eventName = scanner.nextLine();
                        Event eventToDelete = college.findEvent(eventName);
                        if (eventToDelete != null) {
                            college.getEvents().remove(eventToDelete);
                            System.out.println("Event deleted successfully.");
                        } else {
                            System.out.println("Event not found.");
                        }
                    }
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        // Sample users
        users.put("admin", new User("admin", "admin123", "Admin"));
        users.put("user1", new User("user1", "user123", "User"));
        
        // Admin login for demo
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter username: ");
        String username = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();

        if (loginAdmin(username, password)) {
            currentUser = users.get(username);
            System.out.println("Admin logged in successfully.");
            collegeMenu();
        } else if (loginUser(username, password)) {
            currentUser = users.get(username);
            System.out.println("User logged in successfully.");
            collegeMenu();
        } else {
            System.out.println("Invalid credentials.");
        }
    }
    public static void adminEventManagementMenu(College college) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nAdmin Event Management Menu for " + college.getName() + ":");
            System.out.println("1. Update Event Details");
            System.out.println("2. Remove Applicant from Event");
            System.out.println("3. Back to Event Menu");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    updateEventDetails(college);
                    break;
                case 2:
                    removeApplicantFromEvent(college);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Modified event menu to include admin-specific functionalities
     public static void eventMenu(College college) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nEvent Menu for " + college.getName() + ":");
            System.out.println("1. Register Applicant");
            System.out.println("2. Display Registered Applicants");
            System.out.println("3. Generate Event Report");
            System.out.println("4. Sort Applicants by Name");
            if (currentUser != null && currentUser.getRole().equals("Admin")) {
                System.out.println("5. Add New Event");
                System.out.println("6. Delete Event");
                System.out.println("7. Admin Event Management");
            }
            System.out.println("8. Back to College Menu");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Event Name to register for: ");
                    String eventName = scanner.nextLine();
                    Event event = college.findEvent(eventName);
                    if (event != null) {
                        Applicant applicant = getApplicantFromInput();
                        registerApplicant(event, applicant);
                    } else {
                        System.out.println("Event not found.");
                    }
                    break;
                case 2:
                    System.out.print("Enter Event Name to view applicants: ");
                    eventName = scanner.nextLine();
                    event = college.findEvent(eventName);
                    if (event != null) {
                        event.displayRegisteredApplicants();
                    } else {
                        System.out.println("Event not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter Event Name to generate report: ");
                    eventName = scanner.nextLine();
                    event = college.findEvent(eventName);
                    if (event != null) {
                        event.generateReport();
                    } else {
                        System.out.println("Event not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter Event Name to sort applicants: ");
                    eventName = scanner.nextLine();
                    event = college.findEvent(eventName);
                    if (event != null) {
                        event.sortApplicantsByName();
                        System.out.println("Applicants sorted by name.");
                    } else {
                        System.out.println("Event not found.");
                    }
                    break;
                case 5:
                    if (currentUser != null && currentUser.getRole().equals("Admin")) {
                        System.out.print("Enter Event Name to add: ");
                        eventName = scanner.nextLine();
                        System.out.print("Enter Event Date: ");
                        String date = scanner.nextLine();
                        System.out.print("Enter Event Type: ");
                        String eventType = scanner.nextLine();
                        Event newEvent = new Event(eventName, date, eventType);
                        college.addEvent(newEvent);
                        System.out.println("Event added successfully.");
                    }
                    break;
                case 6:
                    if (currentUser != null && currentUser.getRole().equals("Admin")) {
                        System.out.print("Enter Event Name to delete: ");
                        eventName = scanner.nextLine();
                        Event eventToDelete = college.findEvent(eventName);
                        if (eventToDelete != null) {
                            college.getEvents().remove(eventToDelete);
                            System.out.println("Event deleted successfully.");
                        } else {
                            System.out.println("Event not found.");
                        }
                    }
                    break;
                case 7:
                    if (currentUser != null && currentUser.getRole().equals("Admin")) {
                        adminEventManagementMenu(college);
                    }
                    break;
                case 8:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    // Main method for testing
    public static void main(String[] args) {
        // Existing logic for user and admin login...
        users.put("admin", new User("admin", "admin123", "Admin"));
        users.put("user1", new User("user1", "user123", "User"));
        
        // Admin login for demo
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter username: ");
        String username = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();

        if (loginAdmin(username, password)) {
            currentUser = users.get(username);
            System.out.println("Admin logged in successfully.");
            collegeMenu();
        } else if (loginUser(username, password)) {
            currentUser = users.get(username);
            System.out.println("User logged in successfully.");
            collegeMenu();
        } else {
            System.out.println("Invalid credentials.");
        }
    }
}



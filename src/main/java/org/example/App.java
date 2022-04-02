package org.example;

import org.example.DAO.MySqlStaffDAO;
import org.example.DAO.StaffDAO_Interface;
import org.example.DTO.Staff;
import org.example.Exceptions.DaoException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {
        App main = new App();
        main.start();
    }

    PriorityQueue<Staff> queue;
    static ArrayList<Staff> staff_list = new ArrayList<>();

    public void start(){

        this.queue = new PriorityQueue<>();


        staff_list.add(new Staff(1, "Manager", "Tanish", "Afre", 15.50, 75, "ta@gmail.com"));   //1162.5
        staff_list.add(new Staff(2, "Supervisor", "John", "Wane", 14.50, 50, "jw@gmail.com"));  //725
        staff_list.add(new Staff(3, "Assistant", "Bruce", "Banner", 11.75, 20, "bb@gmail.com"));    //235
        staff_list.add(new Staff(4, "Bar Staff", "Adolf", "Hitler", 10.50, 15, "ah@gmail.com"));    //157.5
        staff_list.add(new Staff(5, "Bar Staff", "Vladimir", "Putin", 10.25, 20, "vp@gmail.com"));  //205
        staff_list.add(new Staff(6, "Bar Staff", "Yug", "Vamos", 10.25, 25, "yv@gmail.com"));   //256.25
        staff_list.add(new Staff(7, "Bar Staff", "Alex", "Costa", 10.80, 30, "ac@gmail.com"));  //324
        staff_list.add(new Staff(8, "Floor Staff", "Alexa", "Nosiri", 11.50, 15, "an@gmail.com"));  //172.5
        staff_list.add(new Staff(9, "Floor Staff", "Tom", "Holland", 10.50, 20, "th@gmail.com"));   //230
        staff_list.add(new Staff(10, "Floor Staff", "Stephen", "Strange", 12.00, 25, "ss@gmail.com"));  //300
        staff_list.add(new Staff(11, "Floor Staff", "Seb", "Dovel", 12.00, 30, "sd@gmail.com"));    //360


        //Main Menu
        final String MENU_ITEMS = "\n*** MAIN MENU ***\n"
                + "1. Display All Staff\n"
                + "2. Retrieve a Staff object by key from HashMap\n"
                + "3. Display Staff-Station from TreeMap in order of Staff_First_Name\n"
                + "4. Priority Sequence Simulation\n"
                + "5. PriorityQueue Two-Field Comparison Demo\n"
                + "6. Staff DB Collections\n"
                + "7. Exit\n"
                + "Enter Option [1,7]";

        final int DISPLAY_STAFF = 1;
        final int HASH_RETRIEVE = 2;
        final int TREE_RETRIEVE = 3;
        final int PRIORITY_QUEUE_DISPLAY = 4;
        final int TWO_FIELD_COMPARISON = 5;
        final int Staff_Collections = 6;
        final int EXIT = 7;

        Scanner keyboard = new Scanner(System.in);
        int option = 0;
        do {
            System.out.println("\n" + MENU_ITEMS);
            try {
                String usersInput = keyboard.nextLine();
                option = Integer.parseInt(usersInput);
                switch (option) {
                    case DISPLAY_STAFF:
                        System.out.println("All Staff");
                        System.out.println();
                        displayStaff(staff_list);
                        break;
                    case HASH_RETRIEVE:
                        System.out.println("Hash Map Retrieve option chosen");
                        hashRetrieve(staff_list);
                        break;
                    case TREE_RETRIEVE:
                        System.out.println("Display using TreeMap option chosen");
                        treeRetrieve(staff_list);
                        break;
                    case PRIORITY_QUEUE_DISPLAY:
                        System.out.println("Priority Sequence Simulation - Work Hours");
                        demoWorkHoursComparator();
                        break;
                    case TWO_FIELD_COMPARISON:
                        PriorityQueueTwoFieldComparisonDemo(staff_list);
                    case Staff_Collections:
                        DBCollection();
                        break;
                    case EXIT:
                        System.out.println("Exit Menu option chosen");
                        break;
                    default:
                        System.out.print("Invalid option - please enter number in range");
                        break;
                }
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.print("Invalid option - please enter number in range");
            }
        } while (option != EXIT);
        System.out.println("\nExiting Main Menu, Thank you!.");

    }

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    // Feature 1
    // Display in Table form
    public static void displayStaff(List<Staff> staffArrayList) {

        System.out.printf("%5s\t%-15s\t%-15s\t%-15s\t%-2s\t%5s %15s\n", "ID", "First_Name", "Last_Name", "Staff Position", "Rate_Per_Hour", "Work_Hours", "Email");
        for (Staff s : staffArrayList) {
            System.out.printf("%5d\t%-15s\t%-15s\t%-15s\t%-2.2f\t%10d\t\t\t\t\t%-20s\n", s.getStaff_id(), s.getFirst_name(), s.getLast_name(), s.getStaff_position(), s.getRate_per_hour(), s.getWork_hours(), s.getEmail());
        }

    }

    // Feature 2
    // HashMaping
    public static void hashRetrieve(ArrayList<Staff> staffArrayList) {
        Scanner sc = new Scanner(System.in);
        Map<Integer, Staff> map = new HashMap<>();
        for (Staff s : staffArrayList) {
            map.put(s.getStaff_id(), s);
        }

        // Display all possible options to staff members
        Set<Integer> keySet = map.keySet();  // get all keys
        System.out.println("Choose from following Staff IDs: ");
        for (Integer id : keySet) {
            System.out.print(id + ", ");
        }

        try {
            System.out.println("\nPlease Enter Staff ID: ");
            int key = sc.nextInt();
            if (map.containsKey(key)) {
                System.out.println("Object with key " + key + " - " + map.get(key));
            } else {
                System.out.println("Staff object with key " + key + " is NOT found.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Id is not a Number");
        }
    }

    // Feature 3
    // tree
    public static void treeRetrieve(ArrayList<Staff> staffArrayList) {
        //Station objects
        Counter c1 = new Counter(1, "HardDrinks");
        Counter c2 = new Counter(2, "Cocktail");
        Counter c3 = new Counter(3, "Mocktail");
        Counter c4 = new Counter(4, "Beer");

        Map<Staff, Counter> stationMap = new TreeMap<>(new StaffCounterComparator());

        // filling values in treeMap as per work hours just to avoid tedious hard coding

        for (Staff s : staffArrayList) {
            if (s.getWork_hours() == 15) {
                stationMap.put(s, c1);
            } else if (s.getWork_hours() == 20) {
                stationMap.put(s, c2);
            } else if (s.getWork_hours() == 25){
                stationMap.put(s, c3);
            } else if (s.getWork_hours() == 30){
                stationMap.put(s, c4);
            }
        }

        System.out.println("\nTreeMap: [ Staff -> Counter ] in Order of Staff working hours\n");

        // for each Entry in the set of all entries
        for (Map.Entry<Staff, Counter> entry : stationMap.entrySet()) {
            Staff staff = entry.getKey();
            Counter counter = entry.getValue();
            System.out.println("{" + staff + "} -> " + counter);
        }

    }

    // Feature 4
    // Priority queue
    private static void demoWorkHoursComparator() {
        PriorityQueue<Staff> queue = new PriorityQueue<Staff>(
                new StaffWorkHourComparator(SortType.Ascending));

        // add two third-priority staff members.

        queue.add(new Staff(3, "Assistant", "Bruce", "Banner", 11.75, 20, "bb@gmail.com"));    //235
        queue.add(new Staff(7, "Bar Staff", "Alex", "Costa", 10.80, 30, "ac@gmail.com"));  //324


        // add two second-priority level items
        queue.add(new Staff(6, "Bar Staff", "Yug", "Vamos", 10.25, 25, "yv@gmail.com"));   //256.25
        queue.add(new Staff(9, "Floor Staff", "Tom", "Holland", 10.50, 20, "th@gmail.com"));   //230


        // remove and display one element
        System.out.println("Remove and display a single element");
        Staff s = queue.remove();
        System.out.println(s.toString() + "  -  Pay per week: €" + (s.getRate_per_hour() * s.getWork_hours()));

        // add one top-priority element
        queue.add(staff_list.get(0));

        // remove and display all elements in priority order
        System.out.println("\nRemove and display all elements");
        while ( !queue.isEmpty() ) {
            Staff r = queue.remove();
            System.out.println(r.toString() + "\t-\tPrice per week: €" + (Double.valueOf(Math.round((r.getRate_per_hour()*r.getWork_hours()) * 100)) / 100) );
        }
    }

    public static void PriorityQueueTwoFieldComparisonDemo(ArrayList<Staff> stafflist) {
        PriorityQueue<Staff> queue = new PriorityQueue<Staff>();
        for (Staff s : stafflist) {
            queue.add(s);
        }
        System.out.println("Staff in priority order of Work Hours Within FistName and LastName");
        Iterator<Staff> iterator = queue.iterator();
        while (iterator.hasNext()) {
            System.out.println(queue.remove());
        }
    }
//________________________________________________________________________________________________________________________
// menu 2
//________________________________________________________________________________________________________________________

    public static void DBCollection(){

        StaffDAO_Interface IStaffDao = new MySqlStaffDAO(); //Staff DAO Interface

        //DB Collections Sub Menu
        final String MENU_ITEM = "\n*** COLLECTIONS MENU ***\n"
                + "1. Find All Staff\n"
                + "2. Find Staff by ID\n"
                + "3. Delete Staff by ID\n"
                + "4. Add New Staff\n"
                + "5. Filter Staff\n"
                + "6. JSON All Staff\n"
                + "7. JSON Staff by ID\n"
                + "8. Exit\n"
                + "Enter Option [1,8]";

        final int findAll = 1;
        final int findByID = 2;
        final int deleteByID = 3;
        final int insertStaff =4;
        final int findAllUsingFilter = 5;
        final int findAllStaffJSON = 6;
        final int findStaffbyIDJSON = 7;
        final int EXIT = 8;


        Scanner kb = new Scanner(System.in);
        int option = 0;
        do {
            System.out.println("\n" + MENU_ITEM);
            try {
                String usersInput = kb.nextLine();
                option = Integer.parseInt(usersInput);
                switch (option) {
                    case findAll:
                        System.out.println("All Staff");
                        DBFindAllStaff(IStaffDao);
                        break;
                    case findByID:
                        System.out.println("Find Staff by ID option chosen");
                        DBFindStaffByID(IStaffDao);
                        break;
                    case deleteByID:
                        System.out.println("Delete staff by ID option chosen");
                        DBDeleteStaffByID(IStaffDao);
                        break;
                    case insertStaff:
                        System.out.println("Insert Staff option chosen");
                        DBInsertStaff(IStaffDao);
                        break;
                    case findAllUsingFilter:
                        System.out.println("Find Staff using Filter option chosen");
                        DBFilterSubMenu();
                        break;
                    case findAllStaffJSON:
                        System.out.println("JSON All Staff option chosen");
                  //      findAllStaffJSON(IStaffDao);
                        break;
                    case findStaffbyIDJSON:
                        System.out.println("JSON Staff by ID option chosen");
                //        findStaffbyIDJSON(IStaffDao);
                        break;
                    case EXIT:
                        System.out.println("Exit Menu option chosen");
                        break;
                    default:
                        System.out.print("Invalid option - please enter number in range");
                        break;
                }

            } catch (InputMismatchException | NumberFormatException e) {
                System.out.print("Invalid option - please enter number in range");
            }
        } while (option != EXIT);

        System.out.println("\nExiting Collections Sub Menu.");
    }

    public static void DBFindAllStaff(StaffDAO_Interface IStaffDao){
        try
        {
            System.out.println("\nfindAllStaff()");
            List<Staff> staffList = IStaffDao.findAllStaff();

            if( staffList.isEmpty() )
                System.out.println("No Staff found");
            else {
                displayStaff(staffList);
            }
        }
        catch( DaoException e )
        {
            e.printStackTrace();
        }
    }

    public static void DBFindStaffByID(StaffDAO_Interface IStaffDao){

        Scanner sc = new Scanner(System.in);
        try
        {
            System.out.println("\nfindStaffById()");

            try {
                System.out.println("Enter Staff ID");

                int id = sc.nextInt();


                Staff s = IStaffDao.findStaffbyID(id);

                if (s != null) // null returned if userid and password not valid
                    System.out.println("Staff found: ID=" + s.getStaff_id() + " " + s.getFirst_name() + " " + s.getLast_name() + "\tEmail=" + s.getEmail() + "\tPosition=" + s.getStaff_position() + "\tWorkHours=" + s.getWork_hours() + "\tRatePerHour=" + s.getRate_per_hour());
                else
                    System.out.println("Staff with ID '" + id + "' doesnt exist.");
            }
            catch (NumberFormatException | InputMismatchException e){
                System.out.println("Invalid ID");
            }

        }
        catch( DaoException e )
        {
            e.printStackTrace();
        }
    }

    public static void DBDeleteStaffByID(StaffDAO_Interface IStaffDao){
        Scanner sc = new Scanner(System.in);
        try
        {
            System.out.println("\ndeleteStaffByID()");

            try {
                System.out.println("Enter Staff ID");

                int id = sc.nextInt();


                Staff s = IStaffDao.findStaffbyID(id);

                if(s!=null){
                    IStaffDao.deleteById(id);
                    System.out.println("Staff with ID "+id+" deleted successfully.");
                }
                else{
                    System.out.println("Staff with ID "+id+" doesn't exist.");
                }

            }
            catch (NumberFormatException | InputMismatchException e){
                System.out.println("Invalid ID");
            }

        }
        catch( DaoException e )
        {
            e.printStackTrace();
        }
    }

    public static void DBInsertStaff(StaffDAO_Interface IStaffDao){

        Scanner sc = new Scanner(System.in);
        try
        {
            System.out.println("\ninsertStaff()");

            try {

                System.out.println("Enter Staff ID");
                int id = sc.nextInt();
                Staff s = IStaffDao.findStaffbyID(id);
                if(s==null) {
                    System.out.println("\nCall addStaff()\n");
                    try {
                        System.out.println("Enter Staff Position");
                        String staff_position = sc.next();
                        System.out.println("Enter Staff First Name");
                        String first_name = sc.next();
                        System.out.println("Enter Staff Last Name");
                        String last_name = sc.next();
                        System.out.println("Enter Staff Rate Per Hour");
                        double rate_per_hour = sc.nextDouble();
                        System.out.println("Enter Staff Work Hours");
                        int work_hours = sc.nextInt();
                        System.out.println("Enter Staff Email");
                        String email = sc.next();
                        /*regex*/
                        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
                        if(matcher.matches()){
                            Staff staff = new Staff(id,staff_position,first_name,last_name,rate_per_hour,work_hours,email);
                            IStaffDao.addStaff(staff);
                            System.out.println("Added Successfully.");
                        }
                        else{
                            System.out.println("Email Format Incorrect - Failed to add Staff!");
                        }
                    }catch(NumberFormatException | InputMismatchException e){
                        System.out.println("Invalid Format - Insert Failed!");
                    }
                }
                else{
                    System.out.println("Duplicate ID - Insert Failed!");
                }

            }
            catch (NumberFormatException | InputMismatchException e){
                System.out.println("Invalid Format");
            }

        }
        catch( DaoException e )
        {
            e.printStackTrace();
        }
    }

    public static void DBFilterSubMenu(){

        StaffDAO_Interface IStaffDao = new MySqlStaffDAO(); //Staff DAO Interface

        //DB Collections Sub Menu
        final String MENU_ITEM = "\n*** COLLECTIONS FILTER BY ***\n"
                + "1. WORK HOURS\n"
                + "2. FIRST NAME\n"
                + "3. Exit\n"
                + "Enter Option [1,3]";

        final int filterWorkHour =1;
        final int filterFirstName =2;
        final int EXIT = 3;


        Scanner kb = new Scanner(System.in);
        int option = 0;
        do {
            System.out.println("\n" + MENU_ITEM);
            try {
                String usersInput = kb.nextLine();
                option = Integer.parseInt(usersInput);
                switch (option) {
                    case filterWorkHour:
                        System.out.println("Filter by Work Hour");
                        DBFindAllStaffUsingFilterWorkHours(IStaffDao);
                        break;
                    case filterFirstName:
                        System.out.println("Filter by First Name");
                        DBFindAllStaffUsingFilterFirstName(IStaffDao);
                        break;
                    case EXIT:
                        System.out.println("Exit Menu option chosen");
                        break;
                    default:
                        System.out.print("Invalid option - please enter number in range");
                        break;
                }

            } catch (InputMismatchException | NumberFormatException e) {
                System.out.print("Invalid option - please enter number in range");
            }
        } while (option != EXIT);

        System.out.println("\nExiting Filter Sub Menu.");
    }

    public static void DBFindAllStaffUsingFilterWorkHours(StaffDAO_Interface IStaffDao){
        try
        {
            System.out.println("\nfindAllStaffUsingFilter()\nIn Order of Work_Hours\n");
            /**Using DB Query*/
            //List<Staff> staffList = IStaffDao.findStaffUsingFilterWorkHour();

            /***Using Comparator*/
            List<Staff> staffList = IStaffDao.findStaffUsingFilterWorkHourComparator();

            if( staffList.isEmpty() )
                System.out.println("No Staff found");
            else {
                displayStaff(staffList);
            }

        }
        catch( DaoException e )
        {
            e.printStackTrace();
        }
    }
    public static void DBFindAllStaffUsingFilterFirstName(StaffDAO_Interface IStaffDao) {
        try {
            System.out.println("\nfindAllStaffUsingFilter()\nIn Order of First_Name\n");
            /**Using DB Query*/
            //List<Staff> staffList = IStaffDao.findStaffUsingFilterFirstName();

            /***Using Comparator*/
            List<Staff> staffList = IStaffDao.findStaffUsingFilterFirstNameComparator();

            if (staffList.isEmpty())
                System.out.println("No Staff found");
            else {
                displayStaff(staffList);
            }

        } catch (DaoException e) {
            e.printStackTrace();
        }
    }


}

package task2;


import java.util.*;

public class HospitalManagementSystem {

    private static List<Patient> patients = new ArrayList<>();
    private static List<Appointment> appointments = new ArrayList<>();
    private static List<Staff> staffList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Hospital Management System ---");
            System.out.println("1. Register Patient");
            System.out.println("2. Schedule Appointment");
            System.out.println("3. View Patients");
            System.out.println("4. View Appointments");
            System.out.println("5. Manage Staff (Admin Only)");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> registerPatient();
                case 2 -> scheduleAppointment();
                case 3 -> viewPatients();
                case 4 -> viewAppointments();
                case 5 -> manageStaff();
                case 6 -> System.out.println("Exiting the system. Goodbye!");
                default -> System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 6);
    }

    // Module 1: Patient Registration
    private static void registerPatient() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n--- Patient Registration ---");
        System.out.print("Enter Patient Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();
        System.out.print("Enter Contact Number: ");
        String contact = scanner.nextLine();

        Patient patient = new Patient(name, age, address, contact);
        patients.add(patient);
        System.out.println("Patient registered successfully!");
    }

    // Module 2: Appointment Scheduling
    private static void scheduleAppointment() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n--- Appointment Scheduling ---");
        System.out.print("Enter Patient Name: ");
        String patientName = scanner.nextLine();
        System.out.print("Enter Doctor's Name: ");
        String doctorName = scanner.nextLine();
        System.out.print("Enter Appointment Date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        System.out.print("Enter Appointment Time (HH:MM): ");
        String time = scanner.nextLine();

        Appointment appointment = new Appointment(patientName, doctorName, date, time);
        appointments.add(appointment);
        System.out.println("Appointment scheduled successfully!");
    }

    // Module 3: View Patients
    private static void viewPatients() {
        System.out.println("\n--- Patient List ---");
        if (patients.isEmpty()) {
            System.out.println("No patients registered.");
        } else {
            for (Patient patient : patients) {
                System.out.println(patient);
            }
        }
    }

    // Module 4: View Appointments
    private static void viewAppointments() {
        System.out.println("\n--- Appointment List ---");
        if (appointments.isEmpty()) {
            System.out.println("No appointments scheduled.");
        } else {
            for (Appointment appointment : appointments) {
                System.out.println(appointment);
            }
        }
    }

    // Module 5: Staff Management (Admin Only)
    private static void manageStaff() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n--- Staff Management (Admin Access Only) ---");
        System.out.print("\nEnter Admin Password: ");
        String password = scanner.nextLine();

        if (!"admin123".equals(password)) {  // Simple password check for demo
            System.out.println("Access Denied! Incorrect password.");
            return;
        }

        System.out.println("Access Granted.");
        System.out.println("1. Add Staff");
        System.out.println("2. View All Staff");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1 -> addStaff();
            case 2 -> viewStaff();
            default -> System.out.println("Invalid choice.");
        }
    }

    private static void addStaff() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter Staff Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Role (e.g., Doctor, Nurse, Receptionist): ");
        String role = scanner.nextLine();

        Staff staff = new Staff(name, role);
        staffList.add(staff);
        System.out.println("Staff added successfully!");
    }

    private static void viewStaff() {
        System.out.println("\n--- Staff List ---");
        if (staffList.isEmpty()) {
            System.out.println("No staff members found.");
        } else {
            for (Staff staff : staffList) {
                System.out.println(staff);
            }
        }
    }
}

// --- Supporting Classes ---

// Class for Patient
class Patient {
    private String name;
    private int age;
    private String address;
    private String contact;

    public Patient(String name, int age, String address, String contact) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Patient{name='" + name + "', age=" + age + ", address='" + address + "', contact='" + contact + "'}";
    }
}

// Class for Appointment
class Appointment {
    private String patientName;
    private String doctorName;
    private String date;
    private String time;

    public Appointment(String patientName, String doctorName, String date, String time) {
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.date = date;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Appointment{patientName='" + patientName + "', doctorName='" + doctorName + "', date='" + date + "', time='" + time + "'}";
    }
}

// Class for Staff
class Staff {
    private String name;
    private String role;

    public Staff(String name, String role) {
        this.name = name;
        this.role = role;
    }

    @Override
    public String toString() {
        return "Staff{name='" + name + "', role='" + role + "'}";
    }
}

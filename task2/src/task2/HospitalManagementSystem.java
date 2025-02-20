package brainwave_intern;

import java.util.*;

public class HospitalManagementSystem {

    private static final List<Patient> patients = new ArrayList<>();
    private static final List<Appointment> appointments = new ArrayList<>();
    private static final List<Invoice> invoices = new ArrayList<>();
    private static final List<MedicalSupply> inventory = new ArrayList<>();
    private static final List<Staff> staffList = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n--- Hospital Management System ---");
            System.out.println("1. Register Patient");
            System.out.println("2. Schedule Appointment");
            System.out.println("3. Manage Billing and Invoicing");
            System.out.println("4. Manage Inventory for Medical Supplies");
            System.out.println("5. Manage Staff");
            System.out.println("6. View All Patients");
            System.out.println("7. Get Patient Details by Name");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            choice = getIntInput();

            switch (choice) {
                case 1 -> registerPatient();
                case 2 -> scheduleAppointment();
                case 3 -> manageBilling();
                case 4 -> manageInventory();
                case 5 -> manageStaff();
                case 6 -> viewAllPatients();
                case 7 -> getPatientDetailsByName();
                case 8 -> System.out.println("Exiting the system. Goodbye!");
                default -> System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 8);
    }

    // --- Patient Registration Module ---
    private static void registerPatient() {
        System.out.print("Enter Patient Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Age: ");
        int age = getIntInput();
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();
        System.out.print("Enter Contact Number: ");
        String contact = scanner.nextLine();
        System.out.print("Enter Disease: ");
        String disease = scanner.nextLine();

        if (contact.isBlank() || contact.length() != 10) {
            System.out.println("Invalid contact number. Please enter a valid 10-digit number.");
            return;
        }

        String patientID = UUID.randomUUID().toString(); // Unique Patient ID
        Patient patient = new Patient(patientID, name, age, address, contact, disease);
        patients.add(patient);
        System.out.println("Patient registered successfully. Patient ID: " + patientID);
    }

    // --- Get Patient Details by Name ---
    private static void getPatientDetailsByName() {
        System.out.print("Enter Patient Name: ");
        String name = scanner.nextLine();

        List<Patient> matchingPatients = new ArrayList<>();
        for (Patient patient : patients) {
            if (patient.getName().equalsIgnoreCase(name)) {
                matchingPatients.add(patient);
            }
        }

        if (matchingPatients.isEmpty()) {
            System.out.println("No patient found with the name: " + name);
        } else {
            for (Patient patient : matchingPatients) {
                System.out.println("Patient Details:");
                System.out.println(patient);
                double billAmount = getBillingAmount(patient.getPatientID());
                System.out.println("Total Billing Amount: " + (billAmount > 0 ? billAmount : "No bills found."));
            }
        }
    }

    private static double getBillingAmount(String patientID) {
        return invoices.stream()
                .filter(invoice -> invoice.getPatientID().equals(patientID))
                .mapToDouble(Invoice::getAmount)
                .sum();
    }

    // --- Appointment Scheduling Module ---
    private static void scheduleAppointment() {
        System.out.print("Enter Patient ID: ");
        String patientID = scanner.nextLine();
        if (findPatientByID(patientID) == null) {
            System.out.println("Patient not found. Please register the patient first.");
            return;
        }

        System.out.print("Enter Doctor's Name: ");
        String doctorName = scanner.nextLine();
        System.out.print("Enter Appointment Date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        System.out.print("Enter Appointment Time (HH:MM): ");
        String time = scanner.nextLine();

        Appointment appointment = new Appointment(patientID, doctorName, date, time);
        appointments.add(appointment);
        System.out.println("Appointment scheduled successfully for Patient ID: " + patientID);
    }

    // --- Billing Management ---
    private static void manageBilling() {
        System.out.print("Enter Patient ID: ");
        String patientID = scanner.nextLine();
        if (findPatientByID(patientID) == null) {
            System.out.println("Patient not found.");
            return;
        }

        System.out.print("Enter Bill Amount: ");
        double amount = getDoubleInput();
        invoices.add(new Invoice(patientID, amount));
        System.out.println("Invoice added successfully.");
    }

    // --- Inventory Management ---
    private static void manageInventory() {
        System.out.print("Enter Medical Supply Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Quantity: ");
        int quantity = getIntInput();
        inventory.add(new MedicalSupply(name, quantity));
        System.out.println("Medical supply added successfully.");
    }

    // --- Staff Management ---
    private static void manageStaff() {
        System.out.print("Enter Staff Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Role: ");
        String role = scanner.nextLine();
        staffList.add(new Staff(name, role));
        System.out.println("Staff added successfully.");
    }

    // --- View All Patients ---
    private static void viewAllPatients() {
        if (patients.isEmpty()) {
            System.out.println("No patients registered.");
        } else {
            patients.forEach(System.out::println);
        }
    }

    private static Patient findPatientByID(String patientID) {
        for (Patient patient : patients) {
            if (patient.getPatientID().equals(patientID)) {
                return patient;
            }
        }
        return null;
    }

    private static int getIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Enter a valid number: ");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        return value;
    }

    private static double getDoubleInput() {
        while (!scanner.hasNextDouble()) {
            System.out.print("Invalid input. Enter a valid number: ");
            scanner.next();
        }
        double value = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        return value;
    }

    // --- Supporting Classes ---
    static class Patient {
        private final String patientID;
        private final String name;
        private final int age;
        private final String address;
        private final String contact;
        private final String disease;

        public Patient(String patientID, String name, int age, String address, String contact, String disease) {
            this.patientID = patientID;
            this.name = name;
            this.age = age;
            this.address = address;
            this.contact = contact;
            this.disease = disease;
        }

        public String getPatientID() {
            return patientID;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "Patient{ID='" + patientID + "', Name='" + name + "', Age=" + age +
                    ", Address='" + address + "', Contact='" + contact + "', Disease='" + disease + "'}";
        }
    }

    static class Appointment {
        private final String patientID;
        private final String doctorName;
        private final String date;
        private final String time;

        public Appointment(String patientID, String doctorName, String date, String time) {
            this.patientID = patientID;
            this.doctorName = doctorName;
            this.date = date;
            this.time = time;
        }
    }

    static class Invoice {
        private final String patientID;
        private final double amount;

        public Invoice(String patientID, double amount) {
            this.patientID = patientID;
            this.amount = amount;
        }

        public String getPatientID() {
            return patientID;
        }

        public double getAmount() {
            return amount;
        }
    }

    static class MedicalSupply {
        private final String name;
        private final int quantity;

        public MedicalSupply(String name, int quantity) {
            this.name = name;
            this.quantity = quantity;
        }
    }

    static class Staff {
        private final String name;
        private final String role;

        public Staff(String name, String role) {
            this.name = name;
            this.role = role;
        }
    }
}

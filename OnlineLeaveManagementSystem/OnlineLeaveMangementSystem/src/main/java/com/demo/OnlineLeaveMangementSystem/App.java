
package com.demo.OnlineLeaveMangementSystem;

import java.io.BufferedWriter;
import java.io.Console;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.Dao.*;
import com.DaoImplementation.*;
import com.Entity.*;
import com.Service.*;
import com.ServiceImplementation.*;

public class App {

    private static final Scanner scanner = new Scanner(System.in);
    private static final AdminService adminService = new AdminServiceImpl(new AdminDaoImpl());
    private static final EmployeeService employeeService = new EmployeeServiceImpl();
    private static final LeaveService leaveService = new LeaveServiceImpl();
    private static final ReportService reportService = new ReportServiceImpl();
    private static final String COMPANY_NAME = "TechCorp";  // Default company name

    private static final String adminId = "admin1";
    private static final String adminPassword = "admin123";

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n🔑 LOGIN PAGE");
            System.out.println("1️. Login as Admin");
            System.out.println("2️. Login as Employee");
            System.out.println("3️. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    loginAdmin();
                    break;
                case 2:
                    loginEmployee();
                    break;
                case 3:
                    System.out.println("👋 Exiting... Bye!");
                    System.exit(0);
                default:
                    System.out.println("❌ Invalid Choice. Try Again.");
            }
        }
    }

    private static void loginAdmin() {
        System.out.print("Enter Admin ID: ");
        String enteredId = scanner.nextLine();

        System.out.print("Enter Admin Password: ");
        String enteredPass = hidePasswordInput();

        if (adminId.equals(enteredId) && adminPassword.equals(enteredPass)) {
            System.out.println("✅ Admin Logged in Successfully!");
            adminMenu();
        } else {
            System.out.println("❌ Invalid Admin Credentials!");
        }
    }

    private static void loginEmployee() {
        System.out.print("Enter Employee ID: ");
        String empId = scanner.nextLine();

        Employee employee = employeeService.getEmployeeById(empId);
        if (employee == null) {
            System.out.println("⚠️ Employee not found. Please Register First!");

            registerEmployee(empId);
        } else {
            System.out.print("Enter Password: ");
            String empPass = hidePasswordInput();

            if (employee.getEmpPass().equals(empPass)) {
                System.out.println("✅ Employee Logged in Successfully!");
                employeeMenu(employee);
            } else {
                System.out.println("❌ Incorrect Password!");
            }
        }
    }

    private static void registerEmployee(String empId) {
        System.out.println("\n📝 REGISTER EMPLOYEE");

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = hidePasswordInput();

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Department: ");
        String department = scanner.nextLine();

        Employee newEmp = new Employee();
        newEmp.setEmpId(empId);
        newEmp.setEmpName(name);
        newEmp.setEmpPass(password);
        newEmp.setEmpEmail(email);
        newEmp.setDepartmentName(department);
        newEmp.setCompanyName(COMPANY_NAME);

        try {
            employeeService.addEmployee(newEmp);
            System.out.println("✅ Employee Registered Successfully. Now Please Login Again.");
        } catch (Exception e) {
            System.out.println("❌ Error while Registering Employee: " + e.getMessage());
        }
    }

    private static void adminMenu() {
        while (true) {
            System.out.println("\n⚙️ ADMIN MENU");
            System.out.println("1️. Approve or Reject Leave");
            System.out.println("2️. View All Reports");
            System.out.println("3️. View All Employees");
            System.out.println("4️. Delete Employee");
            System.out.println("5️. Export Reports (CSV)");
            System.out.println("6. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                	approveLeave();
                    break;
                case 2:
                    viewAllReports();
                    break;
                case 3:
                    viewAllEmployees();
                    break;
                case 4:
                    deleteEmployee();
                    break;
                case 5:
                	exportReport();
                	break;
                case 6:
                    System.out.println("🔒 Admin Logged out.");
                    return;
                default:
                    System.out.println("❌ Invalid Option.");
            }
        }
    }

    private static void employeeMenu(Employee employee) {
        while (true) {
            System.out.println("\n👨‍💼 EMPLOYEE MENU");
            System.out.println("1️. Apply for Leave");
            System.out.println("2️. View My Leave Report");
            System.out.println("3️. Update My Profile");
            System.out.println("4️. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    applyLeave(employee);
                    break;
                case 2:
                    viewMyLeaveReports(employee.getEmpId());
                    break;
                case 3:
                    updateProfile(employee);
                    break;
                case 4:
                    System.out.println("👋 Employee Logged out.");
                    return;
                default:
                    System.out.println("❌ Invalid Option.");
            }
        }
    }

    public static void approveLeave() {
        System.out.println("🔍 Pending Leave Applications:");

        List<LeaveTable> pendingLeaves = leaveService.getPendingLeaves();
        if (pendingLeaves.isEmpty()) {
            System.out.println("✅ No pending leave applications found.");
            return;
        }

        // Display all pending leaves
        System.out.printf("%-15s %-10s %-10s %-12s %-12s %-10s\n", "Leave ID", "Emp ID", "Type", "Start Date", "End Date", "Days");
        for (LeaveTable leave : pendingLeaves) {
            System.out.printf("%-15s %-10s %-10s %-12s %-12s %-10d\n",
                    leave.getLeaveId(),
                    leave.getEmployee().getEmpId(),
                    leave.getLeaveType(),
                    leave.getStartDate(),
                    leave.getEndDate(),
                    leave.getTotalDays());
        }

        System.out.print("Enter Leave ID to Approve/Reject: ");
        String leaveId = scanner.next();

        LeaveTable leave = leaveService.getLeaveById(leaveId);
        if (leave == null) {
            System.out.println("❌ Leave ID not found.");
            return;
        }

        System.out.print("Do you want to Approve (A) or Reject (R) this leave? ");
        String decision = scanner.next().toUpperCase();

        if (decision.equals("A")) {
            // Step 1: Approve the leave
            leave.setLeaveStatus("Approved");
            leaveService.updateLeave(leave);
            System.out.println("✅ Leave Approved Successfully.");

        } else if (decision.equals("R")) {
            // Step 1: Reject the leave
            leave.setLeaveStatus("Rejected");
            leaveService.updateLeave(leave);
            System.out.println("❌ Leave Rejected Successfully.");

        } else {
            System.out.println("❌ Invalid choice. Please enter 'A' or 'R'.");
            return;
        }

        // Step 2: Generate or Update Report
        Employee emp = employeeService.getEmployeeById(leave.getEmployee().getEmpId());
        Admin admin = adminService.getAdminById("admin1");

        Report report = reportService.getReportByLeaveId(leave.getLeaveId());

        if (report != null) {
            // Update existing report
            report.setStatus(leave.getLeaveStatus());
            report.setComment(leave.getLeaveStatus().equals("Rejected") ? "Leave rejected by Admin" : "-");
            report.setGeneratedDate(new Date());
            report.setAdmin(admin);
            reportService.updateReport(report);
            System.out.println("📄 Report Updated Successfully.");
        } else {
            // Create new report (fallback case)
            Report newReport = new Report();
            newReport.setReportId("R" + System.currentTimeMillis());
            newReport.setEmpName(emp != null ? emp.getEmpName() : "Unknown");
            newReport.setLeaveType(leave.getLeaveType());
            newReport.setStartDate(leave.getStartDate());
            newReport.setEndDate(leave.getEndDate());
            newReport.setTotalDays(leave.getTotalDays());
            newReport.setStatus(leave.getLeaveStatus());
            newReport.setComment(leave.getLeaveStatus().equals("Rejected") ? "Leave rejected by Admin" : "-");
            newReport.setLeave(leave);
            newReport.setAdmin(admin);
            newReport.setEmployee(emp);
            newReport.setGeneratedDate(new Date());
            reportService.saveReport(newReport);
            System.out.println("📄 New Report Generated (Fallback).");
        }
    }


    private static void viewAllReports() {
        List<Report> reports = reportService.getAllReports();

        if (reports == null || reports.isEmpty()) {
            System.out.println("📭 No leave reports found.");
            return;
        }

        System.out.println("\n📋 All Leave Reports");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-15s %-10s %-15s %-12s %-12s %-12s %-10s %-10s %-25s\n",
                "REPORT ID", "EMP ID", "EMP NAME", "TYPE", "START DATE", "END DATE", "DAYS", "STATUS", "REMARK");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------");

        for (Report report : reports) {
            System.out.printf("%-15s %-10s %-15s %-12s %-12s %-12s %-10d %-10s %-25s\n",
                    report.getReportId(),
                    report.getEmployee() != null ? report.getEmployee().getEmpId() : "N/A",
                    report.getEmpName(),
                    report.getLeaveType(),
                    report.getStartDate(),
                    report.getEndDate(),
                    report.getTotalDays(),
                    report.getStatus(),
                    report.getComment() != null ? report.getComment() : "None");
        }

        System.out.println("----------------------------------------------------------------------------------------------------------------------------\n");
    }


    private static void viewAllEmployees() {
        List<Employee> list = employeeService.getAllEmployees();
        System.out.println("\n📋 Employee List");
        System.out.println("+--------+--------------+--------------+----------------------+----------+");
        System.out.println("| Emp ID | Company Name | Dept. Name   | Email                | Name     |");
        System.out.println("+--------+--------------+--------------+----------------------+----------+");
        for (Employee e : list) {
            System.out.printf("| %-6s | %-12s | %-12s | %-20s | %-8s |\n",
                    e.getEmpId(), e.getCompanyName(), e.getDepartmentName(), e.getEmpEmail(), e.getEmpName());
        }
        System.out.println("+--------+--------------+--------------+----------------------+----------+");
    }



    private static void deleteEmployee() {
        List<Employee> employees = employeeService.getAllEmployees();
        
        if (employees.isEmpty()) {
            System.out.println("⚠️ No employees found.");
            return;
        }

        // Display all employees
        System.out.println("\n📋 List of Employees:");
        System.out.printf("%-10s %-20s %-25s %-20s %-20s\n", "Emp ID", "Name", "Email", "Department", "Company");
        System.out.println("---------------------------------------------------------------------------------------------");

        for (Employee emp : employees) {
            System.out.printf("%-10s %-20s %-25s %-20s %-20s\n",
                    emp.getEmpId(), emp.getEmpName(), emp.getEmpEmail(),
                    emp.getDepartmentName(), emp.getCompanyName());
        }

        // Ask which employee to delete
        System.out.print("\nEnter the Employee ID to delete: ");
        String empId = scanner.next();
        scanner.nextLine();  // Consume newline

        Employee employee = employeeService.getEmployeeById(empId);
        if (employee == null) {
            System.out.println("❌ Employee not found.");
            return;
        }

        employeeService.deleteEmployee(empId);
        System.out.println("🗑️ Employee deleted successfully.");
    }


    private static void applyLeave(Employee employee) {
        System.out.print("Enter Start Date (YYYY-MM-DD): ");
        String startDateStr = scanner.next();
        System.out.print("Enter End Date (YYYY-MM-DD): ");
        String endDateStr = scanner.next();
        scanner.nextLine(); // clear buffer

        try {
            LocalDate startDate = LocalDate.parse(startDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate endDate = LocalDate.parse(endDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            if (endDate.isBefore(startDate)) {
                System.out.println("❌ Error: End date cannot be before start date.");
                return;
            }

            int totalDays = (int) ChronoUnit.DAYS.between(startDate, endDate) + 1;

            System.out.print("Enter Leave Type: ");
            String leaveType = scanner.nextLine();

            LeaveTable leave = new LeaveTable(generateLeaveId(), employee, startDateStr, endDateStr, leaveType, totalDays, "Pending");
            leaveService.applyLeave(leave);

            Report report = new Report();
            report.setReportId("R" + System.currentTimeMillis());
            report.setEmpName(employee.getEmpName());
            report.setLeaveType(leaveType);
            report.setStartDate(startDateStr);
            report.setEndDate(endDateStr);
            report.setTotalDays(totalDays);
            report.setStatus("Pending");
            report.setComment("Awaiting Approval");
            report.setLeave(leave);
            report.setAdmin(null); // Not approved yet
            report.setEmployee(employee);
            report.setGeneratedDate(new Date());

            reportService.saveReport(report);

            System.out.println("✅ Leave request submitted! Total Days: " + totalDays);
            System.out.println("📄 Leave report generated as pending.");

        } catch (Exception e) {
            System.out.println("❌ Invalid date format.");
        }
    }


    public static void viewMyLeaveReports(String empId) {
        List<Report> reports = reportService.getReportsByEmpId(empId);

        if (reports == null || reports.isEmpty()) {
            System.out.println("❌ No leave reports found.");
        } else {
            System.out.println("\n📋 Leave Report Details of employee");
            System.out.println("----------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%-15s %-10s %-15s %-12s %-12s %-12s %-10s %-10s %-25s\n",
                "REPORT ID", "EMP ID", "EMP NAME", "TYPE", "START DATE", "END DATE", "DAYS", "STATUS", "REMARK");
            System.out.println("----------------------------------------------------------------------------------------------------------------------------");

            for (Report report : reports) {
                System.out.printf("%-15s %-10s %-15s %-12s %-12s %-12s %-10d %-10s %-25s\n",
                    report.getReportId(),
                    report.getEmployee() != null ? report.getEmployee().getEmpId() : "N/A",
                    report.getEmpName(),
                    report.getLeaveType(),
                    report.getStartDate(),
                    report.getEndDate(),
                    report.getTotalDays(),
                    report.getStatus(),
                    report.getComment() != null ? report.getComment() : "None");
            }

            System.out.println("----------------------------------------------------------------------------------------------------------------------------\n");
        }
    }


    private static void updateProfile(Employee employee) {
        System.out.println("\n✏️ UPDATE YOUR PROFILE");
        
        System.out.print("Enter New Name (leave blank to keep same): ");
        String newName = scanner.nextLine().trim();
        
        System.out.print("Enter New Email (leave blank to keep same): ");
        String newEmail = scanner.nextLine().trim();
        
        System.out.print("Enter New Password (leave blank to keep same): ");
        String newPassword = scanner.nextLine().trim();
        
        System.out.print("Enter New Department (leave blank to keep same): ");
        String newDepartment = scanner.nextLine().trim();

        if (!newName.isEmpty()) employee.setEmpName(newName);
        if (!newEmail.isEmpty() && isValidEmail(newEmail)) employee.setEmpEmail(newEmail);
        if (!newPassword.isEmpty() && newPassword.length() >= 6) employee.setEmpPass(newPassword);
        if (!newDepartment.isEmpty()) employee.setDepartmentName(newDepartment);

        employeeService.updateEmployee(employee);
        System.out.println("✅ Profile Updated Successfully.");
    }

    private static boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    private static String hidePasswordInput() {
        String password = "";
        try {
            Console console = System.console();
            if (console != null) {
                char[] pwdArray = console.readPassword();
                password = new String(pwdArray);
            } else {
                // fallback if console not available (e.g. IDE)
                password = scanner.nextLine();
            }
        } catch (Exception e) {
            password = scanner.nextLine();
        }
        return password;
    }
    private static void exportReport() {
        List<Report> reports = reportService.getAllReports();
        
        if (reports == null || reports.isEmpty()) {
            System.out.println("📭 No leave reports found to export.");
            return;
        }

        String fileName = "leave_reports.csv";
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("leave_reports.csv")))
        
        {
            // Write CSV Header
            writer.write("REPORT ID,EMP ID,EMP NAME,TYPE,START DATE,END DATE,DAYS,STATUS,REMARK");
            writer.newLine();

            // Write Report Data
            for (Report report : reports) {
                writer.write(String.format("%s,%s,%s,%s,%s,%s,%d,%s,%s\n",
                    report.getReportId(),
                    report.getEmployee() != null ? report.getEmployee().getEmpId() : "N/A",
                    report.getEmpName(),
                    report.getLeaveType(),
                    report.getStartDate(),
                    report.getEndDate(),
                    report.getTotalDays(),
                    report.getStatus(),
                    report.getComment() != null ? report.getComment() : "None"));
            }

            System.out.println("✅ Reports have been successfully exported to " + fileName);
        } catch (IOException e) {
            System.out.println("❌ Error exporting reports: " + e.getMessage());
        }
    }

    private static String generateLeaveId() {
        return "L" + System.currentTimeMillis();
    }

    private static String generateReportId() {
        return "R" + System.currentTimeMillis();
    }

    public Admin getDefaultAdmin() {
        return new Admin("admin1", "admin123"); 
    }

}


//package com.demo.OnlineLeaveMangementSystem;
//
//import com.DaoImplementation.AdminDaoImpl;
//import com.Entity.*;
//import com.Service.*;
//import com.ServiceImplementation.*;
//
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.time.temporal.ChronoUnit;
//import java.util.Date;
//import java.util.List;
//import java.util.Scanner;
//import java.util.stream.Collectors;
//
//import com.ServiceImplementation.AdminServiceImpl;
//import com.Service.AdminService;
//
//
//
//public class App {
//
//    private static final Scanner scanner = new Scanner(System.in);
//    private static final AdminService adminService = new AdminServiceImpl(new AdminDaoImpl());
//
//    private static final EmployeeService employeeService = new EmployeeServiceImpl();
//    private static final LeaveService leaveService = new LeaveServiceImpl();
//    private static final ReportService reportService = new ReportServiceImpl();
//   
//    private static Admin currentAdmin;
//    private static final String adminId = "admin1"; // globally declared at top of class
//
//    public static void main(String[] args) {
//        while (true) {
//            System.out.println("\n🔑 LOGIN");
//            System.out.print("Enter User ID: ");
//            String userId = scanner.next();
//            System.out.print("Enter Password: ");
//            String password = scanner.next();
//
//            if (userId.equals("admin1") && password.equals("admin123")) {
//                currentAdmin = new Admin(userId, password); // Admin login
//                adminMenu();
//            } else {
//                Employee employee = employeeService.getEmployeeById(userId);
//                if (employee != null && employee.getEmpPass().equals(password)) {
//                    employeeMenu(employee);
//                } else {
//                    System.out.println("❌ Invalid Credentials. Try again.");
//                }
//            }
//        }
//    }
//
//    private static void adminMenu() {
//        while (true) {
//            System.out.println("\n⚙️ ADMIN MENU");
//            System.out.println("1️⃣ Add Employee");
//            System.out.println("2️⃣ Approve Leave");
//            System.out.println("3️⃣ View Report List");
//            System.out.println("4️⃣ Update Employee");
//            System.out.println("5️⃣ Delete Employee");
//            System.out.println("6️⃣ View All Employees");
//            System.out.println("7️⃣ Logout");
//            System.out.print("Choose an option: ");
//
//            int choice;
//            try {
//                choice = scanner.nextInt();
//            } catch (Exception e) {
//                System.out.println("❌ Invalid input. Please enter a number.");
//                scanner.nextLine();
//                continue;
//            }
//
//            switch (choice) {
//                case 1 : addEmployee(); break;
//                case 2 : approveLeave(); break;
//                case 3 : viewReportList();break;
//                case 4 : updateEmployee();break;
//                case 5 : deleteEmployee();break;
//                case 6 : viewAllEmployees();break;
//                case 7 : {
//                    System.out.println("🔒 Logging out...");
//                    return;
//                }
//                default : System.out.println("❌ Invalid option. Try again.");
//            }
//
//            System.out.println("\n🔁 Returning to Admin Menu...");
//        }
//    }
//
//    private static void employeeMenu(Employee employee) {
//        while (true) {
//            System.out.println("\n👨‍💼 EMPLOYEE MENU");
//            System.out.println("1️⃣ Apply for Leave");
//            System.out.println("2️⃣ View Leave Report");
//            System.out.println("3️⃣ Logout");
//            System.out.print("Choose an option: ");
//
//            int choice;
//            try {
//                choice = scanner.nextInt();
//            } catch (Exception e) {
//                System.out.println("❌ Invalid input.");
//                scanner.nextLine();
//                continue;
//            }
//
//            switch (choice) {
//                case 1 : applyForLeave(employee); break;
//                case 2 : viewLeaveReport(employee.getEmpId()); break;
//                case 3 : {
//                    System.out.println("👋 Logging out...");
//                    return;
//                }
//                default : System.out.println("❌ Invalid option. Try again.");
//            }
//
//            System.out.println("\n🔁 Returning to Employee Menu...");
//        }
//    }
//
//    private static void addEmployee() {
//        System.out.println("\n🆕 Add Employee");
//        System.out.print("Enter Employee ID: ");
//        String empId = scanner.next();
//        scanner.nextLine();
//
//        System.out.print("Enter Employee Name: ");
//        String empName = scanner.nextLine();
//
//        System.out.print("Enter Employee Email: ");
//        String empEmail = scanner.nextLine();
//
//        System.out.print("Enter Employee Password: ");
//        String empPassword = scanner.nextLine();
//
//        System.out.print("Enter Department Name: ");
//        String departmentName = scanner.nextLine();
//
//        String companyName = "Techno"; // Default company name
//
//        Employee employee = new Employee(empId, empName, empEmail, empPassword, departmentName, companyName);
//        employeeService.addEmployee(employee);
//        System.out.println("✅ Employee added successfully!");
//    }
//
//    private static void updateEmployee() {
//        System.out.print("Enter Employee ID to update: ");
//        String empId = scanner.next();
//        scanner.nextLine();
//
//        Employee existingEmployee = employeeService.getEmployeeById(empId);
//        if (existingEmployee == null) {
//            System.out.println("❌ Employee not found.");
//            return;
//        }
//
//        System.out.print("Enter New Name (" + existingEmployee.getEmpName() + "): ");
//        String newName = scanner.nextLine();
//
//        System.out.print("Enter New Email (" + existingEmployee.getEmpEmail() + "): ");
//        String newEmail = scanner.nextLine();
//
//        System.out.print("Enter New Password: ");
//        String newPass = scanner.nextLine();
//
//        existingEmployee.setEmpName(newName);
//        existingEmployee.setEmpEmail(newEmail);
//        existingEmployee.setEmpPass(newPass);
//
//        employeeService.updateEmployee(existingEmployee);
//        System.out.println("✅ Employee updated successfully!");
//    }
//
//    private static void deleteEmployee() {
//        System.out.print("Enter Employee ID to delete: ");
//        String empId = scanner.next();
//        scanner.nextLine();
//
//        Employee employee = employeeService.getEmployeeById(empId);
//        if (employee == null) {
//            System.out.println("❌ Employee not found.");
//            return;
//        }
//
//        employeeService.deleteEmployee(empId);
//        System.out.println("🗑️ Employee deleted successfully.");
//    }
//
//    private static void viewAllEmployees() {
//        List<Employee> list = employeeService.getAllEmployees();
//        System.out.println("\n📋 Employee List");
//        System.out.println("+--------+--------------+--------------+----------------------+----------+---------+");
//        System.out.println("| Emp ID | Company Name | Dept. Name   | Email                | Name     | Pass    |");
//        System.out.println("+--------+--------------+--------------+----------------------+----------+---------+");
//        for (Employee e : list) {
//            System.out.printf("| %-6s | %-12s | %-12s | %-20s | %-8s | %-7s |\n",
//                    e.getEmpId(), e.getCompanyName(), e.getDepartmentName(), e.getEmpEmail(), e.getEmpName(), e.getEmpPass());
//        }
//        System.out.println("+--------+--------------+--------------+----------------------+----------+---------+");
//    }
//    
//    private static void applyForLeave(Employee employee) {
//        System.out.println("\n📅 Apply for Leave");
//
//        System.out.print("Enter Start Date (YYYY-MM-DD): ");
//        String startDateStr = scanner.next();
//        System.out.print("Enter End Date (YYYY-MM-DD): ");
//        String endDateStr = scanner.next();
//        scanner.nextLine(); // clear buffer
//
//        try {
//            LocalDate startDate = LocalDate.parse(startDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//            LocalDate endDate = LocalDate.parse(endDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//
//            if (endDate.isBefore(startDate)) {
//                System.out.println("❌ Error: End date cannot be before start date.");
//                return;
//            }
//
//            int totalDays = (int) ChronoUnit.DAYS.between(startDate, endDate) + 1;
//
//            System.out.print("Enter Leave Type: ");
//            String leaveType = scanner.nextLine();
//
//            // Step 1: Create and save leave
//            LeaveTable leave = new LeaveTable(generateLeaveId(), employee, startDateStr, endDateStr, leaveType, totalDays, "Pending");
//            leaveService.applyLeave(leave);
//
//            // ✅ Step 2: Generate report immediately
//            Report report = new Report();
//            report.setReportId("R" + System.currentTimeMillis());
//            report.setEmpName(employee.getEmpName());
//            report.setLeaveType(leaveType);
//            report.setStartDate(startDateStr);
//            report.setEndDate(endDateStr);
//            report.setTotalDays(totalDays);
//            report.setStatus("Pending");
//            report.setComment("Awaiting Approval");
//            report.setLeave(leave);
//            report.setAdmin(null); // Not approved yet
//            report.setEmployee(employee);
//            report.setGeneratedDate(new Date());
//
//            reportService.saveReport(report);
//
//            System.out.println("✅ Leave request submitted! Total Days: " + totalDays);
//            System.out.println("📄 Leave report generated as pending.");
//
//        } catch (Exception e) {
//            System.out.println("❌ Invalid date format.");
//        }
//    }
//    
//    public static void approveLeave() {
//        System.out.println("🔍 Pending Leave Applications:");
//
//        List<LeaveTable> pendingLeaves = leaveService.getPendingLeaves();
//        if (pendingLeaves.isEmpty()) {
//            System.out.println("✅ No pending leave applications found.");
//            return;
//        }
//
//        // Display all pending leaves
//        System.out.printf("%-15s %-10s %-10s %-12s %-12s %-10s\n", "Leave ID", "Emp ID", "Type", "Start Date", "End Date", "Days");
//        for (LeaveTable leave : pendingLeaves) {
//            System.out.printf("%-15s %-10s %-10s %-12s %-12s %-10d\n",
//                    leave.getLeaveId(),
//                    leave.getEmployee().getEmpId(),
//                    leave.getLeaveType(),
//                    leave.getStartDate(),
//                    leave.getEndDate(),
//                    leave.getTotalDays());
//        }
//
//        System.out.print("Enter Leave ID to approve: ");
//        String leaveId = scanner.next();
//
//        LeaveTable leave = leaveService.getLeaveById(leaveId);
//        if (leave == null) {
//            System.out.println("❌ Leave ID not found.");
//            return;
//        }
//
//        // Step 1: Approve the leave
//        leave.setLeaveStatus("Approved");
//        leaveService.updateLeave(leave);
//        System.out.println("✅ Leave approved successfully.");
//
//        // Step 2: Get employee and admin info
//        Employee emp = employeeService.getEmployeeById(leave.getEmployee().getEmpId());
//        Admin admin = adminService.getAdminById("admin1");
//
//        // Step 3: Check if a report already exists for this leave
//        Report report = reportService.getReportByLeaveId(leave.getLeaveId());
//
//        if (report != null) {
//            // ✅ Update existing report
//            report.setStatus("Approved");
//            report.setComment("-");
//            report.setGeneratedDate(new Date());
//            report.setAdmin(admin);
//            reportService.updateReport(report);
//            System.out.println("📄 Report updated successfully.");
//        } else {
//            // 🆕 Fallback: Create new report (only if somehow not generated during apply)
//            Report newReport = new Report();
//            newReport.setReportId("R" + System.currentTimeMillis());
//            newReport.setEmpName(emp != null ? emp.getEmpName() : "Unknown");
//            newReport.setLeaveType(leave.getLeaveType());
//            newReport.setStartDate(leave.getStartDate());
//            newReport.setEndDate(leave.getEndDate());
//            newReport.setTotalDays(leave.getTotalDays());
//            newReport.setStatus("Approved");
//            newReport.setComment("-");
//            newReport.setLeave(leave);
//            newReport.setAdmin(admin);
//            newReport.setEmployee(emp);
//            newReport.setGeneratedDate(new Date());
//            reportService.saveReport(newReport);
//            System.out.println("📄 New report generated (fallback).");
//        }
//    }
//
//    
//
//    public static void viewLeaveReport(String empId) {
//        List<Report> reports = reportService.getReportsByEmpId(empId);
//
//        if (reports == null || reports.isEmpty()) {
//            System.out.println("❌ No leave reports found.");
//        } else {
//            System.out.println("\n📋 Leave Report Details of employee");
//            System.out.println("----------------------------------------------------------------------------------------------------------------------------");
//            System.out.printf("%-15s %-10s %-15s %-12s %-12s %-12s %-10s %-10s %-25s\n",
//                "REPORT ID", "EMP ID", "EMP NAME", "TYPE", "START DATE", "END DATE", "DAYS", "STATUS", "REMARK");
//            System.out.println("----------------------------------------------------------------------------------------------------------------------------");
//
//            for (Report report : reports) {
//                System.out.printf("%-15s %-10s %-15s %-12s %-12s %-12s %-10d %-10s %-25s\n",
//                    report.getReportId(),
//                    report.getEmployee() != null ? report.getEmployee().getEmpId() : "N/A",
//                    report.getEmpName(),
//                    report.getLeaveType(),
//                    report.getStartDate(),
//                    report.getEndDate(),
//                    report.getTotalDays(),
//                    report.getStatus(),
//                    report.getComment() != null ? report.getComment() : "None");
//            }
//
//            System.out.println("----------------------------------------------------------------------------------------------------------------------------\n");
//        }
//    }
//    private static void viewReportList() {
//        List<Report> reports = reportService.getAllReports();
//
//        if (reports == null || reports.isEmpty()) {
//            System.out.println("📭 No leave reports found.");
//            return;
//        }
//
//        System.out.println("\n📋 All Leave Reports");
//        System.out.println("----------------------------------------------------------------------------------------------------------------------------");
//        System.out.printf("%-15s %-10s %-15s %-12s %-12s %-12s %-10s %-10s %-25s\n",
//                "REPORT ID", "EMP ID", "EMP NAME", "TYPE", "START DATE", "END DATE", "DAYS", "STATUS", "REMARK");
//        System.out.println("----------------------------------------------------------------------------------------------------------------------------");
//
//        for (Report report : reports) {
//            System.out.printf("%-15s %-10s %-15s %-12s %-12s %-12s %-10d %-10s %-25s\n",
//                    report.getReportId(),
//                    report.getEmployee() != null ? report.getEmployee().getEmpId() : "N/A",
//                    report.getEmpName(),
//                    report.getLeaveType(),
//                    report.getStartDate(),
//                    report.getEndDate(),
//                    report.getTotalDays(),
//                    report.getStatus(),
//                    report.getComment() != null ? report.getComment() : "None");
//        }
//
//        System.out.println("----------------------------------------------------------------------------------------------------------------------------\n");
//    }
//    
//    private static String generateLeaveId() {
//        return "L" + System.currentTimeMillis();
//    }
//
//    //This method generates a unique Report ID.
//    private static String generateReportId() {
//        return "R" + System.currentTimeMillis();
//    }
//    
//    
//    public Admin getDefaultAdmin() {
//        return new Admin("admin1", "admin123"); 
//        
//    }
//}
//
//

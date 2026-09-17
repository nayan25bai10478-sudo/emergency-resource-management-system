package emergency;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        IncidentService incidentService = new IncidentService();
        ResourceService resourceService = new ResourceService();
        ReportService reportService = new ReportService(incidentService, resourceService);

        while (true) {
            System.out.println("\n===== EMERGENCY RESOURCE SYSTEM =====");
            System.out.println("1. Report Incident");
            System.out.println("2. View Incidents");
            System.out.println("3. Add Resource");
            System.out.println("4. View Resources");
            System.out.println("5. Assign Resource");
            System.out.println("6. Update Incident");
            System.out.println("7. View Report");
            System.out.println("8. Exit");
            System.out.print("Enter choice: ");

            String choice = sc.nextLine();

            try {
                switch (choice) {
                    case "1":
                        incidentService.addIncident(sc);
                        break;
                    case "2":
                        incidentService.showIncidents();
                        break;
                    case "3":
                        resourceService.addResource(sc);
                        break;
                    case "4":
                        resourceService.showResources();
                        break;
                    case "5":
                        incidentService.assignResource(sc, resourceService);
                        break;
                    case "6":
                        incidentService.updateStatus(sc);
                        break;
                    case "7":
                        reportService.showReport();
                        break;
                    case "8":
                        System.out.println("System closed.");
                        sc.close();
                        return;
                    default:
                        System.out.println("Please enter a valid choice.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}

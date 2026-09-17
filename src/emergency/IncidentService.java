package emergency;

import java.util.*;

public class IncidentService {
    private final String file = "data/incidents.txt";
    private List<Incident> incidents = new ArrayList<>();

    public IncidentService() {
        load();
    }

    private void load() {
        for (String line : FileStore.read(file)) {
            Incident incident = Incident.fromFileString(line);
            if (incident != null) incidents.add(incident);
        }
    }

    private void save() {
        List<String> lines = new ArrayList<>();
        for (Incident i : incidents) lines.add(i.toFileString());
        FileStore.write(file, lines);
    }

    public void addIncident(Scanner sc) {
        String type = Validator.required(read(sc, "Incident type: "), "Incident type");
        String location = Validator.required(read(sc, "Location: "), "Location");
        String priority = Validator.priority(read(sc, "Priority (HIGH/MEDIUM/LOW): "));
        String description = Validator.required(read(sc, "Short description: "), "Description");

        String id = IdGenerator.incidentId(incidents.size() + 1);
        Incident incident = new Incident(id, type, location, priority, "OPEN",
                description, "NONE");
        incidents.add(incident);
        PriorityManager.sort(incidents);
        save();

        System.out.println("Incident created: " + id);
    }

    public void showIncidents() {
        if (incidents.isEmpty()) {
            System.out.println("No incidents found.");
            return;
        }

        PriorityManager.sort(incidents);
        System.out.println("\nID      TYPE        LOCATION        PRIORITY   STATUS      RESOURCE");
        System.out.println("---------------------------------------------------------------------");
        for (Incident i : incidents) {
            System.out.printf("%-7s %-11s %-15s %-10s %-11s %s%n",
                    i.getId(), i.getType(), i.getLocation(), i.getPriority(),
                    i.getStatus(), i.getResourceId());
        }
    }

    public void assignResource(Scanner sc, ResourceService resourceService) {
        String incidentId = Validator.required(read(sc, "Incident ID: "), "Incident ID");
        Incident incident = find(incidentId);
        if (incident == null) throw new IllegalArgumentException("Incident not found.");

        List<Resource> available = resourceService.availableResources();
        if (available.isEmpty()) {
            System.out.println("No free resource is available.");
            return;
        }

        System.out.println("\nAvailable resources:");
        for (Resource r : available) {
            System.out.println(r.getId() + " - " + r.getName() + " - " +
                    r.getType() + " - " + r.getLocation());
        }

        String resourceId = Validator.required(read(sc, "Resource ID: "), "Resource ID");
        Resource resource = resourceService.find(resourceId);
        if (resource == null) throw new IllegalArgumentException("Resource not found.");
        if (!resource.getStatus().equals("AVAILABLE")) {
            throw new IllegalArgumentException("Resource is not available.");
        }

        incident.setResourceId(resourceId);
        incident.setStatus("ASSIGNED");
        resource.setStatus("BUSY");
        resourceService.saveChanges();
        save();

        System.out.println(resourceId + " assigned to " + incidentId + ".");
    }

    public void updateStatus(Scanner sc) {
        String id = Validator.required(read(sc, "Incident ID: "), "Incident ID");
        Incident incident = find(id);
        if (incident == null) throw new IllegalArgumentException("Incident not found.");

        String status = Validator.incidentStatus(read(sc, "New status (OPEN/ASSIGNED/RESOLVED): "));
        incident.setStatus(status);
        save();
        System.out.println("Status updated.");
    }

    public Incident find(String id) {
        for (Incident i : incidents) {
            if (i.getId().equalsIgnoreCase(id)) return i;
        }
        return null;
    }

    public List<Incident> getIncidents() {
        return incidents;
    }

    private String read(Scanner sc, String text) {
        System.out.print(text);
        return sc.nextLine();
    }
}

package emergency;

import java.util.*;

public class ReportService {
    private IncidentService incidentService;
    private ResourceService resourceService;

    public ReportService(IncidentService incidentService, ResourceService resourceService) {
        this.incidentService = incidentService;
        this.resourceService = resourceService;
    }

    public void showReport() {
        List<Incident> incidents = incidentService.getIncidents();
        List<Resource> resources = resourceService.getResources();

        int open = 0;
        int assigned = 0;
        int resolved = 0;
        int high = 0;

        for (Incident i : incidents) {
            if (i.getStatus().equals("OPEN")) open++;
            if (i.getStatus().equals("ASSIGNED")) assigned++;
            if (i.getStatus().equals("RESOLVED")) resolved++;
            if (i.getPriority().equals("HIGH")) high++;
        }

        int available = 0;
        int busy = 0;
        for (Resource r : resources) {
            if (r.getStatus().equals("AVAILABLE")) available++;
            if (r.getStatus().equals("BUSY")) busy++;
        }

        System.out.println("\n========== SYSTEM REPORT ==========");
        System.out.println("Total incidents     : " + incidents.size());
        System.out.println("Open incidents      : " + open);
        System.out.println("Assigned incidents  : " + assigned);
        System.out.println("Resolved incidents  : " + resolved);
        System.out.println("High priority       : " + high);
        System.out.println("Total resources     : " + resources.size());
        System.out.println("Available resources : " + available);
        System.out.println("Busy resources      : " + busy);
        System.out.println("===================================");
    }
}

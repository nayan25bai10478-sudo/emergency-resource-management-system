package emergency;

import java.util.*;

public class ResourceService {
    private final String file = "data/resources.txt";
    private List<Resource> resources = new ArrayList<>();

    public ResourceService() {
        load();
    }

    private void load() {
        for (String line : FileStore.read(file)) {
            Resource resource = Resource.fromFileString(line);
            if (resource != null) resources.add(resource);
        }
    }

    public void saveChanges() {
        List<String> lines = new ArrayList<>();
        for (Resource r : resources) lines.add(r.toFileString());
        FileStore.write(file, lines);
    }

    public void addResource(Scanner sc) {
        String name = Validator.required(read(sc, "Resource name: "), "Resource name");
        String type = Validator.required(read(sc, "Resource type: "), "Resource type");
        String location = Validator.required(read(sc, "Current location: "), "Location");

        String id = IdGenerator.resourceId(resources.size() + 1);
        resources.add(new Resource(id, name, type, "AVAILABLE", location));
        saveChanges();

        System.out.println("Resource added: " + id);
    }

    public void showResources() {
        if (resources.isEmpty()) {
            System.out.println("No resources found.");
            return;
        }

        System.out.println("\nID      NAME                 TYPE          STATUS       LOCATION");
        System.out.println("-----------------------------------------------------------------");
        for (Resource r : resources) {
            System.out.printf("%-7s %-20s %-13s %-12s %s%n",
                    r.getId(), r.getName(), r.getType(), r.getStatus(), r.getLocation());
        }
    }

    public List<Resource> availableResources() {
        List<Resource> result = new ArrayList<>();
        for (Resource r : resources) {
            if (r.getStatus().equals("AVAILABLE")) result.add(r);
        }
        return result;
    }

    public Resource find(String id) {
        for (Resource r : resources) {
            if (r.getId().equalsIgnoreCase(id)) return r;
        }
        return null;
    }

    public List<Resource> getResources() {
        return resources;
    }

    private String read(Scanner sc, String text) {
        System.out.print(text);
        return sc.nextLine();
    }
}

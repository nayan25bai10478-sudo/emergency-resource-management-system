package emergency;

public class Incident {
    private String id;
    private String type;
    private String location;
    private String priority;
    private String status;
    private String description;
    private String resourceId;

    public Incident(String id, String type, String location, String priority,
                    String status, String description, String resourceId) {
        this.id = id;
        this.type = type;
        this.location = location;
        this.priority = priority;
        this.status = status;
        this.description = description;
        this.resourceId = resourceId;
    }

    public String getId() { return id; }
    public String getType() { return type; }
    public String getLocation() { return location; }
    public String getPriority() { return priority; }
    public String getStatus() { return status; }
    public String getDescription() { return description; }
    public String getResourceId() { return resourceId; }

    public void setStatus(String status) { this.status = status; }
    public void setResourceId(String resourceId) { this.resourceId = resourceId; }

    public String toFileString() {
        return id + "|" + clean(type) + "|" + clean(location) + "|" + priority + "|" +
               status + "|" + clean(description) + "|" + resourceId;
    }

    private String clean(String value) {
        return value.replace("|", "/");
    }

    public static Incident fromFileString(String line) {
        String[] p = line.split("\\|", -1);
        if (p.length != 7) return null;
        return new Incident(p[0], p[1], p[2], p[3], p[4], p[5], p[6]);
    }
}

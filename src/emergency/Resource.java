package emergency;

public class Resource {
    private String id;
    private String name;
    private String type;
    private String status;
    private String location;

    public Resource(String id, String name, String type, String status, String location) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.status = status;
        this.location = location;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getType() { return type; }
    public String getStatus() { return status; }
    public String getLocation() { return location; }

    public void setStatus(String status) { this.status = status; }

    public String toFileString() {
        return id + "|" + clean(name) + "|" + clean(type) + "|" + status + "|" + clean(location);
    }

    private String clean(String value) {
        return value.replace("|", "/");
    }

    public static Resource fromFileString(String line) {
        String[] p = line.split("\\|", -1);
        if (p.length != 5) return null;
        return new Resource(p[0], p[1], p[2], p[3], p[4]);
    }
}

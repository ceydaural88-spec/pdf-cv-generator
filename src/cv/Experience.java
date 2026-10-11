package cv;

/** Model class: one job experience. Fields are private (encapsulation). */
public class Experience {
    private final String position;
    private final String company;
    private final String dateRange;
    private final String[] tasks;

    public Experience(String position, String company, String dateRange, String[] tasks) {
        this.position = position;
        this.company = company;
        this.dateRange = dateRange;
        this.tasks = tasks;
    }

    public String getPosition() { return position; }
    public String getCompany() { return company; }
    public String getDateRange() { return dateRange; }
    public String[] getTasks() { return tasks; }
}

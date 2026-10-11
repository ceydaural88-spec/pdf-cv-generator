package cv;

/** Model class: one education record. */
public class Education {
    private final String school;
    private final String degree;
    private final String dateRange;

    public Education(String school, String degree, String dateRange) {
        this.school = school;
        this.degree = degree;
        this.dateRange = dateRange;
    }

    public String getSchool() { return school; }
    public String getDegree() { return degree; }
    public String getDateRange() { return dateRange; }
}

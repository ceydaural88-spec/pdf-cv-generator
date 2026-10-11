package cv;

import java.util.ArrayList;
import java.util.List;

/** Model class: all personal data used in the CV. */
public class Person {
    private final String fullName;
    private final String title;
    private final String email;
    private final String phone;
    private final String city;
    private final String summary;
    private final List<Experience> experiences = new ArrayList<>();
    private final List<Education> educations = new ArrayList<>();
    private final List<String> skills = new ArrayList<>();
    private final List<String> languages = new ArrayList<>();

    public Person(String fullName, String title, String email, String phone,
                  String city, String summary) {
        this.fullName = fullName;
        this.title = title;
        this.email = email;
        this.phone = phone;
        this.city = city;
        this.summary = summary;
    }

    public void addExperience(Experience e) { experiences.add(e); }
    public void addEducation(Education e) { educations.add(e); }
    public void addSkill(String s) { skills.add(s); }
    public void addLanguage(String l) { languages.add(l); }

    public String getFullName() { return fullName; }
    public String getTitle() { return title; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getCity() { return city; }
    public String getSummary() { return summary; }
    public List<Experience> getExperiences() { return experiences; }
    public List<Education> getEducations() { return educations; }
    public List<String> getSkills() { return skills; }
    public List<String> getLanguages() { return languages; }
}

package com.odev.cv;

public class Main {
    public static void main(String[] args) {
        // CV Verisi
        Resume myResume = new Resume(
                "Ceyda Ural",
                "Software Engineer",
                "ceydaural88@gmail.com",
                "+90 531 700 8760",
                "Istanbul, Turkey",
                "cvresim.jpeg"
        );

        // 3 Adet Hayali Is Deneyimi
        myResume.addExperience(new Experience(
                "Tech Solutions Inc.",
                "Senior Java Developer",
                "2024",
                "Present",
                "Developing microservices architecture and Spring Boot projects."
        ));

        myResume.addExperience(new Experience(
                "Data Systems Ltd.",
                "Software Developer",
                "2022",
                "2024",
                "Database optimization and REST API integrations."
        ));

        myResume.addExperience(new Experience(
                "Inovasyon IT",
                "Intern Developer",
                "2021",
                "2022",
                "Assisting unit tests and code refactoring with C and Java."
        ));

        // PDF Uretimi
        ResumePdfGenerator pdfGenerator = new ResumePdfGenerator();
        pdfGenerator.generate(myResume, "Ozgecmis_Ceyda_Ural.pdf");
    }
}
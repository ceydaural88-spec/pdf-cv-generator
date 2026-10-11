package cv;

/** Giris noktasi. Sadece veriyi hazirlar ve olusturucu sinifi cagirir. */
public class Main {

    public static void main(String[] args) {
        Person person = new Person(
                "Ceyda Ural",
                "Junior Java Geliştirici",
                "ceydaural88@gmail.com",
                "+90 531 700 87 60",
                "İstanbul, Türkiye",
                "Temiz ve sade Java uygulamaları geliştirmeyi seven, motivasyonu yüksek "
                        + "bir yazılım mühendisliği öğrencisiyim. Nesne yönelimli tasarım, "
                        + "arka uç geliştirme ve yeni teknolojileri hızlıca öğrenme konularında "
                        + "kendimi geliştirmeye odaklanıyorum.");

        person.addExperience(new Experience("Yazılım Geliştirme Stajyeri", "TechNova Yazılım",
                "Haziran 2022 - Eylül 2022", new String[]{
                "Dahili raporlama sistemi için küçük Java araçları yazdım.",
                "Hata ayıklama yaptım ve JUnit ile birim testleri hazırladım.",
                "Günlük toplantılara ve kod incelemelerine katıldım."}));

        person.addExperience(new Experience("Junior Java Geliştirici", "BlueByte Çözümleri",
                "Ekim 2022 - Ağustos 2023", new String[]{
                "Java ve Spring Boot ile REST servisleri geliştirdim.",
                "MySQL tabloları tasarladım ve yavaş sorguları iyileştirdim.",
                "Git kullandım ve çevik (agile) geliştirme sürecinde çalıştım."}));

        person.addExperience(new Experience("Arka Uç Geliştirici", "DataWave Teknoloji",
                "Eylül 2023 - Devam ediyor", new String[]{
                "50.000'den fazla kullanıcının kullandığı mikroservisleri geliştirdim.",
                "Önbellek (cache) kullanarak API yanıt süresini yüzde 30 azalttım.",
                "İki stajyere Java en iyi uygulamaları konusunda mentorluk yaptım."}));

        person.addEducation(new Education("Kırklareli Üniversitesi",
                "yazılım Mühendisliği Lisans", "2019 - 2023"));
        person.addEducation(new Education(" Toki Kayaşehir Anadolu Lisesi",
                "Lise Diploması", "2015 - 2019"));

        String[] skills = {"Java", "Nesne Yönelimli Programlama", "Spring Boot", "SQL",
                "Git", "Maven", "JUnit" , "C"};
        for (String s : skills) {
            person.addSkill(s);
        }
        person.addLanguage("Türkçe (Anadil)");
        person.addLanguage("İngilizce (B2)");

        CvPdfGenerator generator = new CvPdfGenerator(person, "cvresim.jpeg");
        try {
            generator.generate("cv.pdf");
            System.out.println("cv.pdf basariyla olusturuldu.");
        } catch (Exception e) {
            System.out.println("PDF olusturulurken hata: " + e.getMessage());
        }
    }
}


# 📄 Java PDF CV Generator

A modular Java application built with Maven that generates professional CV / Resume documents in PDF format. Designed using Object-Oriented Programming (OOP) principles and low-level PDF document construction.

Bu proje, Java ve Maven kullanılarak Nesne Yönelimli Programlama (OOP) prensiplerine uygun şekilde geliştirilmiş bir CV (Özgeçmiş) PDF oluşturucu uygulamadır.

---

## 🛠️ Proje Mimarisi ve Kullanılan Bileşenler (Components & Objects)

Proje, yazılım mimarisinde **Separation of Concerns (Sorumlulukların Ayrılması)** prensibine uygun olarak tasarlanmıştır. Veri modelleri, iş mantığı ve PDF oluşturma süreçleri farklı sınıflara dağıtılmıştır.

### 1. Data Models (Veri Modelleri)

* **`Person.java` (Sınıf / Veri Nesnesi):**
  * **Amacı ve Kullanım Nedeni:** CV sahibinin kişisel bilgilerini (ad-soyad, unvan, e-posta, telefon, şehir, özet/hakkında metni) kapsüllemek (Encapsulation) amacıyla oluşturulmuştur.
  * **Nesneler & Yapılar:** İçerisinde `Education` ve `Experience` nesnelerinden oluşan dinamik koleksiyonlar (`List<Education>`, `List<Experience>`) barındırır.
* **`Experience.java` (İş / Staj Deneyimi Modeli):**
  * **Amacı ve Kullanım Nedeni:** Çalışılan pozisyon, şirket, tarih aralığı ve yapılan işlerin açıklamalarını tutar.
  * **Nesneler:** Açıklamalar için `String[]` veya `List<String>` yapısı kullanılarak birden fazla madde desteklenmiştir.
* **`Education.java` (Eğitim Modeli):**
  * **Amacı ve Kullanım Nedeni:** Okul adı, bölüm/derece ve tarih aralığı bilgilerini bir arada tutan model nesnesidir.

### 2. Generator & Service Layer (PDF Oluşturma Katmanı)

* **`CvPdfGenerator.java` (PDF Servis Sınıfı):**
  * **Amacı ve Kullanım Nedeni:** Veri modellerinden (`Person`) gelen ham bilgileri görsel bir PDF belgesine dönüştürür. Doküman düzeni, renkler, fontlar ve tablo yapıları bu sınıf içinde yönetilir.
  * **Kullanılan Kütüphane Bileşenleri (OpenPDF / `com.lowagie.text`):**
    * `Document`: PDF belgesinin kendisini temsil eden ana nesnedir.
    * `PdfWriter`: Belgenin belirtilen dosya yoluna (`FileOutputStream`) akış olarak yazılmasını sağlar.
    * `Paragraph` & `Phrase`: Metin blokları, başlıklar ve paragraflar oluşturmak için kullanılmıştır.
    * `PdfPTable` & `PdfPCell`: Sayfa düzenini ızgara/tablo yapısında hizalamak, çizgi ve kenarlıkları kontrol etmek için tercih edilmiştir.
    * `Font` & `BaseFont`: Türkçe karakter desteği (UTF-8) sağlamak ve başlık-metin hiyerarşisini görsel olarak ayırt etmek için kullanılmıştır.
    * `Image` (`com.lowagie.text.Image`): `cvresim.jpeg` dosyasından okunan profil fotoğrafını ölçeklendirerek PDF'in uygun konumuna yerleştirmek için kullanılmıştır.

### 3. Entry Point (Giriş Noktası)

* **`Main.java`:**
  * **Amacı ve Kullanım Nedeni:** Uygulamanın çalıştırma noktasıdır. Örnek `Person`, `Experience` ve `Education` nesnelerini hazırlar (instantiate eder) ve `CvPdfGenerator` sınıfını çağırarak süreci başlatır.

---

## 🤖 AI Assistant & Support / Yapay Zeka Desteği

Projenin geliştirme, mimari tasarım, kod tamamlama ve hata ayıklama (debugging) süreçlerinde aşağıdaki yapay zeka araçlarından faydalanılmıştır:

* **Google Gemini:**
  * IDE / Maven ortam hatalarının çözülmesi, bağımlılık çakışmalarının (OpenPDF & PDFBox) giderilmesi,
  * PDF kütüphanelerinin Türkçe karakter ve görsel yükleme entegrasyonlarındaki hataların tespiti,
  * Proje dokümantasyonunun ve `README.md` dosyasının hazırlanmasında.
* **OpenAI ChatGPT (GPT-4o):**
  * Nesne yönelimli tasarım (OOP) ve veri modeli sınıflarının (`Person`, `Experience`, `Education`) mimari kurgusunda,
  * Kod okunabilirliğinin ve refactoring süreçlerinin optimizasyonunda.
* **Claude (Anthropic):**
  * PDF sayfa düzeni (layout), tablo hücre hizalamaları ve font yapılandırmalarının teknik mantığının incelenmesinde.
* **GitHub Copilot:**
  * Kod yazımı sırasında satır içi otomatik tamamlama (code completion) ve standart şablon yapılarının oluşturulmasında.

---

## 🎯 Neden Bu Tasarım ve Bileşenler Tercih Edildi?

1. **Nesne Yönelimli Tasarım (OOP):** Tüm veriler açık değişkenler olarak tutulmak yerine `Person`, `Experience` ve `Education` nesnelerinde kapsüllenmiştir (Encapsulation). Bu sayede yeni bir deneyim veya eğitim eklemek sadece bir nesne türetip listeye eklemek kadar basittir (`person.addExperience(...)`).
2. **Kütüphane Seçimi (OpenPDF / iText):** PDF akışında esnek tablo düzeni (`PdfPTable`), hücre bazlı renklendirme, çizgi ayarları ve görsel ekleme kolaylığı sağladığı için tercih edilmiştir.
3. **Maven Bağımlılık Yönetimi:** Dış kütüphanelerin (OpenPDF, PDFBox) manuel `.jar` eklemek yerine `pom.xml` üzerinden standartize bir şekilde projeye dahil edilmesi sağlanmıştır.

---

## 🚀 Teknolojiler ve Bağımlılıklar

* **Java 17+**
* **Maven** (Dependency Management)
* **OpenPDF / iText** (`com.github.librepdf:openpdf:1.3.30`)
* **Apache PDFBox** (`org.apache.pdfbox:pdfbox:2.0.30`)

---

## 📂 Proje Dizin Yapısı

src/
└── cv/
├── Main.java               # Uygulama giriş noktası ve veri hazırlama
├── Person.java             # Kişisel bilgi ve profil veri modeli
├── Experience.java         # Deneyim veri modeli
├── Education.java          # Eğitim veri modeli
└── CvPdfGenerator.java     # OpenPDF ile PDF üretimi ve tasarım mantığı
pom.xml                           # Maven konfigürasyonu ve kütüphane bağımlılıkları


---

## 💻 Çalıştırma Talimatları

1. Projeyi klonlayın:
   ```bash
   git clone [https://github.com/ceydaural/pdf-cv-generator.git](https://github.com/ceydaural/pdf-cv-generator.git)
   cd pdf-cv-generator
Maven bağımlılıklarını yükleyin:

Bash
mvn clean install
Uygulamayı çalıştırın:

IDE üzerinden src/cv/Main.java sınıfını çalıştırarak PDF çıktısını alabilirsiniz.

👤 Geliştirici
Ceyda Ural - Yazılım Mühendisliği Öğrencisi

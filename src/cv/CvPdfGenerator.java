package cv;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.draw.LineSeparator;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;

/**
 * PDF olusturan ayri sinif. Main icinde PDF kodu yoktur.
 */
public class CvPdfGenerator {

    private static final Color DARK = new Color(33, 47, 61);
    private static final Color ACCENT = new Color(41, 128, 185);
    private static final Color GRAY = new Color(100, 100, 100);

    private static final String[] FONT_DIRS = {
            "C:/Windows/Fonts/", "/Library/Fonts/", "/System/Library/Fonts/Supplemental/",
            "/usr/share/fonts/truetype/msttcorefonts/"
    };

    private final Person person;
    private final String photoPath;

    private BaseFont regularBase;
    private BaseFont boldBase;
    private BaseFont italicBase;

    public CvPdfGenerator(Person person, String photoPath) {
        this.person = person;
        this.photoPath = photoPath;
        loadFonts();
    }

    /** Turkce karakterler icin Arial fontunu yukler (PDF'e gomulur). */
    private void loadFonts() {
        try {
            regularBase = findFont("arial.ttf", "Arial.ttf");
            boldBase = findFont("arialbd.ttf", "Arial Bold.ttf");
            italicBase = findFont("ariali.ttf", "Arial Italic.ttf");
        } catch (Exception e) {
            System.out.println("Font yuklenemedi: " + e.getMessage());
        }
        if (regularBase == null) {
            System.out.println("UYARI: Arial bulunamadi, Turkce karakterler bozuk cikabilir.");
            try {
                regularBase = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, false);
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }
        if (boldBase == null) boldBase = regularBase;
        if (italicBase == null) italicBase = regularBase;
    }

    private BaseFont findFont(String... names) throws DocumentException, IOException {
        for (String dir : FONT_DIRS) {
            for (String name : names) {
                File f = new File(dir + name);
                if (f.exists()) {
                    return BaseFont.createFont(f.getAbsolutePath(), BaseFont.IDENTITY_H,
                            BaseFont.EMBEDDED);
                }
            }
        }
        return null;
    }

    /** Public: PDF dosyasini olusturur. */
    public void generate(String outputPath) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4, 36, 36, 36, 36);
        try (FileOutputStream out = new FileOutputStream(outputPath)) {
            PdfWriter.getInstance(document, out);
            document.open();
            addHeader(document);
            addSummary(document);
            addExperiences(document);
            addEducations(document);
            addSkillsAndLanguages(document);
            document.close();
        }
    }

    // ---------- private yardimci metotlar ----------

    private Font font(float size, int style, Color color) {
        BaseFont base = regularBase;
        if (style == Font.BOLD) base = boldBase;
        else if (style == Font.ITALIC) base = italicBase;
        return new Font(base, size, Font.NORMAL, color);
    }

    private void addHeader(Document document) throws DocumentException {
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{1f, 3f});

        PdfPCell photoCell = new PdfPCell();
        photoCell.setBackgroundColor(DARK);
        photoCell.setBorder(Rectangle.NO_BORDER);
        photoCell.setPadding(14);
        photoCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        photoCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        try {
            Image photo = Image.getInstance(photoPath);
            photo.scaleToFit(110, 130);
            photoCell.addElement(photo);
        } catch (Exception e) {
            System.out.println("Fotograf yuklenemedi: " + e.getMessage());
            photoCell.addElement(new Phrase(" "));
        }
        table.addCell(photoCell);

        PdfPCell infoCell = new PdfPCell();
        infoCell.setBackgroundColor(DARK);
        infoCell.setBorder(Rectangle.NO_BORDER);
        infoCell.setPadding(14);
        infoCell.setVerticalAlignment(Element.ALIGN_MIDDLE);

        infoCell.addElement(new Paragraph(person.getFullName(), font(26, Font.BOLD, Color.WHITE)));
        infoCell.addElement(new Paragraph(person.getTitle(),
                font(13, Font.NORMAL, new Color(174, 214, 241))));
        Paragraph contact = new Paragraph(
                "E-posta: " + person.getEmail() + "\nTelefon: " + person.getPhone()
                        + "\nŞehir: " + person.getCity(),
                font(10, Font.NORMAL, Color.WHITE));
        contact.setSpacingBefore(8);
        infoCell.addElement(contact);
        table.addCell(infoCell);

        document.add(table);
    }

    private void addSectionTitle(Document document, String text) throws DocumentException {
        Paragraph title = new Paragraph(text.toUpperCase(new Locale("tr", "TR")),
                font(13, Font.BOLD, ACCENT));
        title.setSpacingBefore(16);
        document.add(title);
        document.add(new LineSeparator(1f, 100f, ACCENT, Element.ALIGN_CENTER, -2f));
    }

    private void addSummary(Document document) throws DocumentException {
        addSectionTitle(document, "Profil");
        Paragraph p = new Paragraph(person.getSummary(), font(10.5f, Font.NORMAL, Color.BLACK));
        p.setSpacingBefore(6);
        p.setLeading(14);
        document.add(p);
    }

    private void addExperiences(Document document) throws DocumentException {
        addSectionTitle(document, "İş Deneyimi");
        for (Experience e : person.getExperiences()) {
            Paragraph head = new Paragraph(e.getPosition() + " - " + e.getCompany(),
                    font(11.5f, Font.BOLD, DARK));
            head.setSpacingBefore(8);
            document.add(head);
            document.add(new Paragraph(e.getDateRange(), font(9.5f, Font.ITALIC, GRAY)));
            for (String task : e.getTasks()) {
                Paragraph t = new Paragraph("- " + task, font(10.5f, Font.NORMAL, Color.BLACK));
                t.setIndentationLeft(10);
                t.setLeading(14);
                document.add(t);
            }
        }
    }

    private void addEducations(Document document) throws DocumentException {
        addSectionTitle(document, "Eğitim");
        for (Education e : person.getEducations()) {
            Paragraph head = new Paragraph(e.getSchool(), font(11.5f, Font.BOLD, DARK));
            head.setSpacingBefore(6);
            document.add(head);
            document.add(new Paragraph(e.getDegree() + "  |  " + e.getDateRange(),
                    font(10, Font.NORMAL, GRAY)));
        }
    }

    private void addSkillsAndLanguages(Document document) throws DocumentException {
        addSectionTitle(document, "Yetenekler");
        Paragraph skills = new Paragraph(String.join("  |  ", person.getSkills()),
                font(10.5f, Font.NORMAL, Color.BLACK));
        skills.setSpacingBefore(6);
        document.add(skills);

        addSectionTitle(document, "Yabancı Diller");
        Paragraph langs = new Paragraph(String.join("  |  ", person.getLanguages()),
                font(10.5f, Font.NORMAL, Color.BLACK));
        langs.setSpacingBefore(6);
        document.add(langs);
    }
}

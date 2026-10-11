package com.odev.cv;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

public class ResumePdfGenerator {

    public void generate(Resume resume, String outputPath) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream cs = new PDPageContentStream(document, page)) {
                // Renkler
                Color primaryColor = new Color(28, 54, 87);    // Koyu Lacivert
                Color secondaryColor = new Color(52, 101, 164); // Mavi
                Color darkTextColor = new Color(40, 40, 40);   // Metin Rengi
                Color lightBgColor = new Color(245, 247, 250);  // Sol Panel Arka Planı

                // Sol Panel Arka Planı
                cs.setNonStrokingColor(lightBgColor);
                cs.addRect(0, 0, 200, 792);
                cs.fill();

                // Sol/Sag Ayirici Cizgi
                cs.setStrokingColor(new Color(220, 224, 230));
                cs.setLineWidth(1.5f);
                cs.moveTo(200, 0);
                cs.lineTo(200, 792);
                cs.stroke();

                // Fotograf Yukleme
                File imgFile = new File(resume.getImagePath());
                if (imgFile.exists()) {
                    PDImageXObject image = PDImageXObject.createFromFileByExtension(imgFile, document);
                    cs.drawImage(image, 35, 640, 130, 130);
                }

                // Iletisim Bilgileri (Sol Panel)
                int sideY = 600;
                cs.beginText();
                cs.setFont(PDType1Font.HELVETICA_BOLD, 12);
                cs.setNonStrokingColor(primaryColor);
                cs.newLineAtOffset(25, sideY);
                cs.showText("CONTACT");
                cs.endText();

                sideY -= 20;
                cs.beginText();
                cs.setFont(PDType1Font.HELVETICA, 9);
                cs.setNonStrokingColor(darkTextColor);
                cs.newLineAtOffset(25, sideY);
                cs.showText("Email:");
                cs.newLineAtOffset(0, -12);
                cs.showText(resume.getEmail());
                cs.newLineAtOffset(0, -20);
                cs.showText("Phone:");
                cs.newLineAtOffset(0, -12);
                cs.showText(resume.getPhone());
                cs.newLineAtOffset(0, -20);
                cs.showText("Location:");
                cs.newLineAtOffset(0, -12);
                cs.showText(resume.getLocation());
                cs.endText();

                // Sag Panel (Isim & Unvan)
                cs.beginText();
                cs.setFont(PDType1Font.HELVETICA_BOLD, 22);
                cs.setNonStrokingColor(primaryColor);
                cs.newLineAtOffset(220, 730);
                cs.showText(resume.getFullName().toUpperCase());
                cs.endText();

                cs.beginText();
                cs.setFont(PDType1Font.HELVETICA_BOLD, 12);
                cs.setNonStrokingColor(secondaryColor);
                cs.newLineAtOffset(220, 710);
                cs.showText(resume.getTitle().toUpperCase());
                cs.endText();

                // Dekoratif Cizgi
                cs.setStrokingColor(secondaryColor);
                cs.setLineWidth(2f);
                cs.moveTo(220, 695);
                cs.lineTo(570, 695);
                cs.stroke();

                // Is Deneyimleri Basligi
                int mainY = 665;
                cs.beginText();
                cs.setFont(PDType1Font.HELVETICA_BOLD, 14);
                cs.setNonStrokingColor(primaryColor);
                cs.newLineAtOffset(220, mainY);
                cs.showText("WORK EXPERIENCE");
                cs.endText();

                mainY -= 25;

                // Deneyim Listesi
                for (Experience exp : resume.getExperiences()) {
                    cs.beginText();
                    cs.setFont(PDType1Font.HELVETICA_BOLD, 11);
                    cs.setNonStrokingColor(darkTextColor);
                    cs.newLineAtOffset(220, mainY);
                    cs.showText(exp.getTitle());
                    cs.endText();

                    cs.beginText();
                    cs.setFont(PDType1Font.HELVETICA_OBLIQUE, 9);
                    cs.setNonStrokingColor(secondaryColor);
                    cs.newLineAtOffset(450, mainY);
                    cs.showText(exp.getStartDate() + " - " + exp.getEndDate());
                    cs.endText();

                    mainY -= 15;
                    cs.beginText();
                    cs.setFont(PDType1Font.HELVETICA_BOLD, 10);
                    cs.setNonStrokingColor(secondaryColor);
                    cs.newLineAtOffset(220, mainY);
                    cs.showText(exp.getCompanyName());
                    cs.endText();

                    mainY -= 15;
                    cs.beginText();
                    cs.setFont(PDType1Font.HELVETICA, 9.5f);
                    cs.setNonStrokingColor(darkTextColor);
                    cs.newLineAtOffset(220, mainY);
                    cs.showText(exp.getDescription());
                    cs.endText();

                    mainY -= 35;
                }
            }

            document.save(outputPath);
            System.out.println("PDF basariyla olusturuldu: " + outputPath);
        } catch (IOException e) {
            System.err.println("PDF olusturulurken hata olustu: " + e.getMessage());
        }
    }
}
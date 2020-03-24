<%-- 
    Document   : IText
    Created on : 28 Jan, 2020, 11:57:36 AM
    Author     : karan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.io.FileOutputStream" %>
<%@page import="java.io.IOException" %>
<%@page import="java.net.MalformedURLException" %>
<%@page import="com.itextpdf.text.Chapter" %>
<%@page import="com.itextpdf.text.Document" %>
<%@page import="com.itextpdf.text.DocumentException" %>
<%@page import="com.itextpdf.text.Font" %>
<%@page import="com.itextpdf.text.FontFactory" %>
<%@page import="com.itextpdf.text.PageSize" %>
<%@page import="com.itextpdf.text.Paragraph" %>
<%@page import="com.itextpdf.text.Paragraph" %>
<%@page import="com.itextpdf.text.Phrase" %>
<%@page import="com.itextpdf.text.Section" %>
<%@page import="com.itextpdf.text.pdf.CMYKColor" %>
<%@page import="com.itextpdf.text.pdf.PdfPCell" %>
<%@page import="com.itextpdf.text.pdf.PdfPTable" %>
<%@page import="com.itextpdf.text.pdf.PdfWriter" %>
<!DOCTYPE html>






<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Document document = new Document(PageSize.A4, 50, 50, 50, 50);

            // Listing 2. Creation of PdfWriter object
            PdfWriter writer = PdfWriter.getInstance(document,
                    new FileOutputStream("/home/rumit/AMS/AMS_1/web/pdf/9.pdf"));

            document.open();

            // Listing 3. Creation of chapter object
            Paragraph title11 = new Paragraph("reports",
                    FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD,
                            new CMYKColor(0, 255, 255, 17)));
            Chapter chapter1 = new Chapter(title11, 1);
            chapter1.setNumberDepth(0);

            // Listing 4. Creation of section object
            Section section1 = chapter1.addSection(title11);

            // Listing 5. Creation of table object
            PdfPTable t = new PdfPTable(4);

            t.setSpacingBefore(25);
            t.setSpacingAfter(25);
            PdfPCell c1 = new PdfPCell(new Phrase("Name"));
            t.addCell(c1);
            PdfPCell c2 = new PdfPCell(new Phrase("Email"));
            t.addCell(c2);
            PdfPCell c3 = new PdfPCell(new Phrase("Punchin"));
            t.addCell(c3);
            PdfPCell c4 = new PdfPCell(new Phrase("Punchout"));
            t.addCell(c4);

            for (int i = 0; i < 7; i++) {
                t.addCell("1.1");
                t.addCell("shah.rumit57@gmail.com");
                t.addCell("1.3");
                t.addCell("1.4");
            }
            section1.add(t);

            document.add(chapter1);
            document.close();
        %>
    </body>
</html>

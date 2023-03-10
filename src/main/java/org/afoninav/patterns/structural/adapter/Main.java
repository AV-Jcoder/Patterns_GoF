package com.afoninav.patterns.structural.adapter;

/**
 * Пример Шаблона Адаптер, который использует
 * "множественное наследование"(extends + implements).
 *
 * Возможен вариант с композицией.
 */
public class Main {
    public static void main(String[] args) {

        Djvu djvuFile = new PdfToDjvu("Это документ в формате .pdf");
        DjvuReader djvuReader = new DjvuReader(djvuFile);
        djvuReader.displayContent();

    }
}

/**
 * Класс который читает только Djvu файлы.
 *
 */
class DjvuReader {
    Djvu djvuFile;

    DjvuReader(Djvu file) {
        this.djvuFile = file;
    }

    public void displayContent() {

        System.out.println(this.djvuFile.readDjvu());
    }
}

/**
 * Интерфейс к которому нужно адаптировать.
 */
interface Djvu {
    String readDjvu();
}

/**
 * Интерфейс который нужно адаптировать.
 */
interface Pdf {
    byte[] readPdf();
}

/**
 * Реализация PDF документа.
 */
class PdfDocument implements Pdf {
    private String pdfDocument;

    public PdfDocument(String pdfDocument) {
        this.pdfDocument = pdfDocument;
    }

    @Override
    public byte[] readPdf() {
        return pdfDocument.getBytes();
    }
}

/**
 * Реализация Адаптера
 */
class PdfToDjvu extends PdfDocument implements Djvu {

    public PdfToDjvu(String pdfDocument) {
        super(pdfDocument);
    }

    @Override
    public String readDjvu() {
        String djvuFormat;
        djvuFormat = new String(this.readPdf());
        return djvuFormat;
    }
}

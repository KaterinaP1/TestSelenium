import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;

public class TestDoc {

    public static boolean findTextInDoc(File file, String text) {
        try {
            InputStream stream = Files.newInputStream(file.toPath());

            XWPFDocument document = new XWPFDocument(stream);
            List<XWPFParagraph> paragraphs = document.getParagraphs();
            document.close();

            for (XWPFParagraph paragraph : paragraphs) {
                List<XWPFRun> runs = paragraph.getRuns();
                for (XWPFRun run : runs) {
                    if (run.text().equals(text)) {
                        return true;
                    }
                }
            }

            return false;
        } catch (IOException e) {
            return false;
        }
    }
}

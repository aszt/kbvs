package kbvs.converter;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by c-kx on 2016/5/9.
 */
public class TextExtraction {
        public String[] getMetadata(File file) {

            Parser parser = new AutoDetectParser();
            BodyContentHandler handler = new BodyContentHandler(100000000);
            Metadata metadata = new Metadata();
            InputStream inputstream = null;
            try {
                inputstream = new FileInputStream(file);
                ParseContext context = new ParseContext();
                parser.parse(inputstream, handler, metadata, context);
                inputstream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

//            String[] metadataNames = metadata.names();
//
//            for (String name : metadataNames) {
//                System.out.println(name + ": " + metadata.get(name));
//            }

            return  null;
        }
}

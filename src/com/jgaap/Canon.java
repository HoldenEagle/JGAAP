package com.jgaap;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.tika.Tika;

public class Canon{
	static private Tika tika = new Tika();

	static private String replaceCRLF(String text) {
		// change CRLF sequences (\r, \n, and \r\n) to LF (\n)
		text = text.replaceAll("(\r\n)|\r|\n", "\n");
		return text;
	}


	static String loadDocument(String filepath, String charset) throws Exception {
		String text = "";
		if(1==1){
			InputStream is = getInputStream(filepath);
			text= tika.parseToString(is);
			is.close();
		}
		if(text.isEmpty()){
			InputStream is = getInputStream(filepath);
			text = readText(is, charset);
			is.close();
		}
		text = replaceCRLF(text);
		return text;
	}
    static private String readText(InputStream is, String charset) throws IOException {
		int c;
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader reader;
		if (charset==null||charset.isEmpty()||charset.equalsIgnoreCase("tika")) {
			reader = new BufferedReader(new InputStreamReader(is));
		} else {
			reader = new BufferedReader(new InputStreamReader(is,charset));
		}
		while ((c = reader.read()) != -1) {
			stringBuilder.append((char)c);
		}
		reader.close();
		return stringBuilder.toString();
	}


    static private InputStream getInputStream(String filepath) throws Exception{
		InputStream is;
		is = new FileInputStream(filepath);
		return is;
	}

    public void run_canon() {
        try{
            String txt = loadDocument("C:\\cygwin64\\jGAAP_work\\JGAAP\\src\\com\\jgaap\\pdf_doc.pdf" , "tika");
            for (char c : txt.toCharArray()) {
				System.out.printf("Character: %c, Unicode: \\u%04x%n", c, (int) c);
			}
			
			char curly_double1 = '\u201d';
			char curly_double2 = '\u201c';
			char curly_single1 = '\u2018';
			char curly_single2 = '\u2019';
			char target_single = '\'';
			txt = txt.replace(curly_double1, '\u0022');
			txt = txt.replace(curly_single1, target_single);
			txt = txt.replace(curly_double2, '\u0022');
			txt = txt.replace(curly_single2, target_single);
			
			System.out.println(txt);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    }
}
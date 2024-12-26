package com.jgaap.canonicizers;

import com.jgaap.generics.Canonicizer;

/**
 * @author Holden Eagle-> Standardizes quotation marks
 */
public class ConvertCurlyQuotes extends Canonicizer{
    @Override
	public String displayName() {
		return "Convert Curly Quotes";
	}

    @Override
	public String tooltipText() {
		return "Converts Curly Quotes (Smart Quotes) into nonslanted marks to standardize them.";
	}

	@Override
	public String longDescription() {
		return "Converts Curly Quotes (Smart Quotes) into nonslanted marks to standardize them. Curly Quotes typically in Word and PDF Documents.";
	}

	@Override
	public boolean showInGUI() {
		return true;
	}

    /**
	 * Strip punctuation from input characters
	 * 
	 * @param procText
	 *            array of characters to be processed.
	 * @return array of processed characters.
	 */
	@Override
	public char[] process(char[] procText) {
		String txt = new String(procText);
        char curly_double1 = '\u201d';
		char curly_double2 = '\u201c';
		char curly_single1 = '\u2018';
		char curly_single2 = '\u2019';
		char target_single = '\'';
		txt = txt.replace(curly_double1, '\u0022');
		txt = txt.replace(curly_single1, target_single);
		txt = txt.replace(curly_double2, '\u0022');
		txt = txt.replace(curly_single2, target_single);
        return txt.toCharArray();
	}
}

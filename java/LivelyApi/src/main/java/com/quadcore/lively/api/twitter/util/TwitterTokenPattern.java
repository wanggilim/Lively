package com.quadcore.lively.api.twitter.util;

import java.util.regex.Pattern;

public class TwitterTokenPattern {
	
	public static Pattern pattern() {
		final Pattern pattern = Pattern.compile(
			"[\\uD83C-\\uDBFF\\uDC00-\\uDFFF]+"
            + "|[\ud83c\udc00-\ud83c\udfff]"
            + "|[\ud83d\udc00-\ud83d\udfff]"
            + "|[\u2600-\u27ff]"
            + "|\\uD83D[\\uDC00-\\uDFFF]"
            + "|\\uD83C[\\uDC00-\\uDFFF]"
            + "|\\uFFFD"
            + "|http*\\w://.*\\w+",
            Pattern.UNICODE_CASE |
            Pattern.CANON_EQ |
            Pattern.CASE_INSENSITIVE
		);
		return pattern;
	}
	
	public static String patternString() {
		final Pattern pattern = Pattern.compile(
			"[\\uD83C-\\uDBFF\\uDC00-\\uDFFF]+"
            + "|[\ud83c\udc00-\ud83c\udfff]"
            + "|[\ud83d\udc00-\ud83d\udfff]"
            + "|[\u2600-\u27ff]"
            + "|\\uD83D[\\uDC00-\\uDFFF]"
            + "|\\uD83C[\\uDC00-\\uDFFF]"
            + "|\\uFFFD"
            + "|http*\\w://.*\\w+",
            Pattern.UNICODE_CASE |
            Pattern.CANON_EQ |
            Pattern.CASE_INSENSITIVE
		);
		return pattern.toString();
	}
	
	

}

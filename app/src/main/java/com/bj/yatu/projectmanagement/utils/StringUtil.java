package com.bj.yatu.projectmanagement.utils;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	public static boolean isEmpty(CharSequence str) {
		if (TextUtils.isEmpty(str) || str == null) {
			return true;
		}
		return false;
	}

	public static String cutStringIfNeed(String soure, int cutlenth) {
		if (StringUtil.isEmpty(soure) || cutlenth <= 0) {
			return "";
		}

		// replace blank
		if (soure != null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(soure);
			soure = m.replaceAll("");
		}

		if (soure.length() <= cutlenth) {
			return soure;
		}
		return soure.substring(0, cutlenth);
	}
}

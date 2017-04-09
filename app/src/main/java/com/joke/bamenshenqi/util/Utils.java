package com.joke.bamenshenqi.util;

import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.regex.Pattern;

import android.util.Base64;

public final class Utils {
	static DecimalFormat df;

	static {
		Utils.df = null;
	}

	public Utils() {
		super();
	}

	public static String FloatToString(float value) {
		return new DecimalFormat("###,###,##0.00").format(((double) value));
	}

	public static int ceil(float f) {
		float v5 = 4f;
		float v4 = 3f;
		float v3 = 2f;
		float v2 = 1f;
		int v0 = 0;
		if (f <= 0f || f > v2) {
			if (f > v2 && f <= v3) {
				return 2;
			}

			if (f > v3 && f <= v4) {
				return 3;
			}

			if (f > v4 && f <= v5) {
				return 4;
			}

			if (f <= v5) {
				return v0;
			}

			if (f > 5f) {
				return v0;
			}

			v0 = 5;
		} else {
			v0 = 1;
		}

		return v0;
	}

	public static String doubleFormat(double value, String doubleoFrmat) {
		if (Utils.df == null) {
			Utils.df = new DecimalFormat(doubleoFrmat);
		}

		return Utils.df.format(value);
	}

	public static String encodeBase64File(File file) throws Exception {
		FileInputStream v1 = new FileInputStream(file);
		byte[] v0 = new byte[((int) file.length())];
		v1.read(v0);
		v1.close();
		return Base64.encodeToString(v0, 0);
	}

	public static String formatBankNo(String bankNo) {
		return bankNo.substring(0, 4) + " " + "**** **** ****" + " " + bankNo.substring(bankNo.length() - 3);
	}

	public static String formatNumber(double number, int fractionDigits) {
		NumberFormat v0 = NumberFormat.getInstance();
		v0.setMaximumFractionDigits(fractionDigits);
		v0.setMinimumFractionDigits(fractionDigits);
		return v0.format(number);
	}

	public static String formatNumber(String number, int fractionDigits) {
		return Utils.formatNumber(Double.parseDouble(number), fractionDigits);
	}

	public static ArrayList getParamFromUrl(String url) {
		ArrayList v4;
		ArrayList v3 = null;
		if (Utils.isNull(url)) {
			v4 = v3;
		} else {
			String[] v2 = url.split("\\?");
			if (Utils.isNull(v2)) {
				v4 = v3;
			} else {
				String[] v5 = v2[1].split("&");
				if (Utils.isNull(v5)) {
					v4 = v3;
				} else {
					v4 = new ArrayList();
					int v1;
					for (v1 = 0; v1 < v5.length; ++v1) {
						v4.add(v5[v1].split("=")[1]);
					}

					v4 = v4;
				}
			}
		}

		return v4;
	}

	public static boolean isEmail(String str) {
		return Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*").matcher(((CharSequence) str)).matches();
	}

	public static boolean isEmpty(String str) {
		boolean v0 = str == null || ("".equals(str)) ? true : false;
		return v0;
	}

	public static boolean isInviteCode(String str) {
		boolean v2 = str != null ? Pattern.compile("[0-9a-zA-Z�?龥]+", 2).matcher(str.trim()).matches() : false;
		return v2;
	}

	public static boolean isMobileNO(String mobiles) {
		return Pattern.compile("^1\\d{10}.").matcher(((CharSequence) mobiles)).matches();
	}

	public static boolean isNull(Object obj) {
		boolean v0 = obj == null || obj == "" || (obj.equals("")) ? true : false;
		return v0;
	}

	public static boolean isNumber(String str) {
		boolean v3 = !Pattern.compile("\\d+(\\.\\d+)?").matcher(((CharSequence) str)).matches() ? false : true;
		return v3;
	}

	public static boolean isStrAndLetter(String str) {
		boolean v0 = true;
		int v1;
		for (v1 = 0; v1 < str.length(); ++v1) {
			if (!str.substring(v1, v1 + 1).matches("[\\u4e00-\\u9fbf]+") && !str.substring(v1, v1 + 1).matches("[a-zA-Z]")) {
				return false;
			}
		}

		return v0;
	}

	public static boolean isUrl(String url) {
		boolean v0 = true;
		int v2 = -1;
		if (url.indexOf("http") <= v2 && url.indexOf("www") <= v2 && url.indexOf("com") <= v2 && url.indexOf("cn") <= v2) {
			v0 = false;
		}

		return v0;
	}

	public static String shuZhiToZhiMu(int a) {
		String v0;
		switch (a) {
		case 10: {
			v0 = "A";
			break;
		}
		case 11: {
			v0 = "B";
			break;
		}
		case 12: {
			v0 = "C";
			break;
		}
		case 13: {
			v0 = "D";
			break;
		}
		case 14: {
			v0 = "E";
			break;
		}
		case 15: {
			v0 = "F";
			break;
		}
		default: {
			v0 = "" + a;
			break;
		}
		}

		return v0;
	}

	public static String stringIntercept(String intercept) {
		return intercept.substring(0, intercept.length() - 1);
	}

	public static String t1(int a) {
		int v1 = a / 16;
		String v0;
		for (v0 = "" + Utils.shuZhiToZhiMu(a % 16); v1 > 0;) {
			int v2 = v1 % 16;
			v1 /= 16;
			v0 = Utils.shuZhiToZhiMu(v2) + v0;
		}

		System.out.println("16进制===" + v0);
		return v0;
	}

	public static String toKM(String distance) {
		String v1;
		double v4 = 1000;
		if (distance == null || ("".equals(distance))) {
			v1 = "未设";
		} else if (Double.valueOf(distance).doubleValue() >= v4) {
			v1 = Double.parseDouble(new DecimalFormat("0.0").format(Double.valueOf(distance).doubleValue() / v4)) + "公里";
		} else {
			v1 = (((int) Math.ceil(Double.valueOf(distance).doubleValue()))) + "?";
		}

		return v1;
	}
}

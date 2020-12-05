package com.example.demo;

import java.io.UnsupportedEncodingException;

public class TTT {

	public static void main(String[] args) {
		// String x = "445444j";
		// x = x.substring(2, x.length());
		// System.out.println(x);

		byte[] srcBytes = new byte[] { 2, 4, 8, 1, 5, 6, 7, 10, 15, 50 };
		byte[] destBytes = new byte[10];
		System.out.println(destBytes);
		System.arraycopy(srcBytes, 5, destBytes, 0, 3);
		String x = null;
		try {
			x = new String(destBytes, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(x);
	}

}

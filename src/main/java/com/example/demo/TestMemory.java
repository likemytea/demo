package com.example.demo;

import java.text.DecimalFormat;

public class TestMemory {
	public static void main(String[] args) {
		displayAvailableMemory();
	}

	/**
	 * Displays the total amount of memory, the maximal amount of memory and the
	 * total amount of free memory in the Java Virtual Machine.
	 */
	public static void displayAvailableMemory() {

		DecimalFormat df = new DecimalFormat("0.00");

		// Display the total amount of memory in the Java virtual machine.
		long totalMem = Runtime.getRuntime().totalMemory();

		System.out.println("totalMemory:" + df.format(totalMem / 1000000F) + " MB");

		// Display the maximum amount of memory that the Java virtual machine will
		// attempt to use.
		long maxMem = Runtime.getRuntime().maxMemory();
		System.out.println("maxMemory:" + df.format(maxMem / 1000000F) + " MB");

		// Display the amount of free memory in the Java Virtual Machine.
		long freeMem = Runtime.getRuntime().freeMemory();
		System.out.println("freeMemory:" + df.format(freeMem / 1000000F) + " MB");
		System.out.println("供应链里边的算法" + (freeMem * 100 / totalMem));
	}
}

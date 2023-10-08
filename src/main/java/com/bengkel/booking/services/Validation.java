package com.bengkel.booking.services;

import com.bengkel.booking.models.Customer;

import java.util.Scanner;

public class Validation {
	public static String regexNumber = "^[0-9]+$";
	public static String regexHuruf = "^[a-zA-Z ]+$";
	public static String regexID = "^[A-Za-z0-9\\-]+$";

	public static String validasiInput(String question, String errorMessage, String regex) {
		Scanner input = new Scanner(System.in);
		String result;
		boolean isLooping = true;
		do {
			System.out.print(question);
			result = input.nextLine();

			//validasi menggunakan matches
			if (result.matches(regex)) {
				isLooping = false;
			}else {
				System.out.println(errorMessage);
			}

		} while (isLooping);

		return result;
	}

	public static int validasiNumberWithRange(String question, String errorMessage, String regex, int max, int min) {
		int result;
		boolean isLooping = true;
		do {
			result = Integer.valueOf(validasiInput(question, errorMessage, regex));
			if (result >= min && result <= max) {
				isLooping = false;
			}else {
				System.out.println("Pilihan angka " + min + " s.d " + max);
			}
		} while (isLooping);

		return result;
	}

	public static boolean validateMenu(String question) {
		boolean isLooping = false;

		int pilihan = validasiNumberWithRange(question, "Hanya menerima inputan angka 0!", regexNumber, 0, 0);
		if (pilihan == 0) {
			isLooping = true;
		}

		return isLooping;
	}
}

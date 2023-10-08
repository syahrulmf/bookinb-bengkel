package com.bengkel.booking.services;

import com.bengkel.booking.models.Customer;

import java.util.Scanner;

public class Validation {

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

//  public static String validasiCustomerId(String question, String errorMessage, String regex) {
//    String input;
//    boolean isLooping = true;
//
//    do {
//      input = validasiInput(question, errorMessage, regex);
//      Customer dataCustomer = BengkelService.getCustomerById(input);
//      if (dataCustomer != null) {
//        isLooping = false;
//      } else {
//        System.out.println(errorMessage);
//				BengkelService.numberOfError++;
//      }
//    } while (isLooping);
//
//    return input;
//  }

//	public static void validasiLogin(String customerID, String password) {
//
//	}
}

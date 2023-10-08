package com.bengkel.booking.services;

import com.bengkel.booking.models.ItemService;
import com.bengkel.booking.models.Vehicle;

import java.util.List;
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

	public static boolean validasiMenu(String question) {
		boolean isLooping = false;

		int pilihan = validasiNumberWithRange(question, "Hanya menerima inputan angka 0!", regexNumber, 0, 0);
		if (pilihan == 0) {
			isLooping = true;
		}

		return isLooping;
	}

	public static String validasiVehicleId(String question, String errorMessage, String regex) {
		String input;
		boolean isLooping = true;

		do {
			input = validasiInput(question, errorMessage, regex);
			Vehicle dataVehicle = BengkelService.getVehicleById(input);
			if (dataVehicle != null) {
				isLooping = false;
			} else {
				System.out.println(errorMessage);
			}
		} while (isLooping);

		return input;
	}


	public static String validasiServiceId(String question, String errorMessage, String regex, List<ItemService> listServices) {
		String input;
		boolean isLooping = true;

		do {
			input = validasiInput(question, errorMessage, regex);
			ItemService dataService = BengkelService.getServiceById(input);
			ItemService cutomerChoiceService = getDataByCustomerChoice(dataService, listServices);

			if (dataService != null && cutomerChoiceService == null) {
				isLooping = false;

			} else if (cutomerChoiceService != null) {
				System.out.println("Service Sudah dipilih");

			} else {
				System.out.println(errorMessage);
			}
		} while (isLooping);

		return input;
	}

	private static int numberServiceOrder = 1;
	public static boolean validasiService(String question, String member) {
		boolean isLooping = false;

		if (member.equalsIgnoreCase("Member") && numberServiceOrder != MenuService.customerLoggedIn.getMaxNumberOfService()) {
			String pilihan = validasiInput(question, "Hanya menerima input Y/T!", "^(?i)(Y|T)$");
			if (pilihan.equalsIgnoreCase("Y")) {
				isLooping = true;
				numberServiceOrder++;
			}
		}

		return isLooping;
	}

	public static ItemService getDataByCustomerChoice(ItemService dataService, List<ItemService> listServices) {
		return listServices.stream().filter(service -> service.equals(dataService)).findFirst().orElse(null);
	}
}

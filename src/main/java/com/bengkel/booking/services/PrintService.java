package com.bengkel.booking.services;

import java.util.List;

import com.bengkel.booking.models.Car;
import com.bengkel.booking.models.Vehicle;

public class PrintService {

	public static void printMenu(String title, String[] listMenu) {
		int number = 1;
		String formatTabel = "| %-3s | %-48s |%n";
		System.out.println(title);
		System.out.println("==========================================================");
		System.out.printf(formatTabel, "No", "Menu");
		System.out.println("==========================================================");

		for (String menu : listMenu) {
			if (number == listMenu.length) {
				System.out.printf(formatTabel, 0, menu);
			}else {
				System.out.printf(formatTabel, number, menu);
			}
			number++;
		}

		System.out.println("==========================================================");
	}
	
	public static void printVechicle(List<Vehicle> listVehicle) {
		String formatTable = "| %-2s | %-15s | %-10s | %-15s | %-15s | %-5s | %-15s |%n";
		String line = "+----+-----------------+------------+-----------------+-----------------+-------+-----------------+%n";
		System.out.format(line);
	    System.out.format(formatTable, "No", "Vechicle Id", "Warna", "Brand", "Transmisi", "Tahun", "Tipe Kendaraan");
	    System.out.format(line);
	    int number = 1;
	    String vehicleType = "";
	    for (Vehicle vehicle : listVehicle) {
	    	if (vehicle instanceof Car) {
				vehicleType = "Mobil";
			}else {
				vehicleType = "Motor";
			}
	    	System.out.format(formatTable, number, vehicle.getVehiclesId(), vehicle.getColor(), vehicle.getBrand(), vehicle.getTransmisionType(), vehicle.getYearRelease(), vehicleType);
	    	number++;
	    }
	    System.out.printf(line);
	}
	
	//Silahkan Tambahkan function print sesuai dengan kebutuhan.
	
}

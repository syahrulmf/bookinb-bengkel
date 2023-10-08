package com.bengkel.booking.services;

import com.bengkel.booking.models.Customer;
import com.bengkel.booking.models.MemberCustomer;

import java.util.Scanner;

public class BengkelService {
  private static Scanner input = new Scanner(System.in);
  public static int numberOfError = 0;
  public static String regexNumber = "^[0-9]+$";
  public static String regexHuruf = "^[a-zA-Z ]+$";
  public static String regexID = "^[A-Za-z0-9\\-]+$";
	
	//Silahkan tambahkan fitur-fitur utama aplikasi disini
	
	//Login
  public static boolean loginService() {
    boolean isLogin = false;
    boolean isLooping = true;

    do {
      String customerID = Validation.validasiInput("Masukan Customer ID: ", "Customer ID tidak ditemukan atau salah!", regexID);
      String password = Validation.validasiInput("Masukan Password: ", "Password yang anda masukan salah!", regexID);
      Customer dataCustomer = getCustomerById(customerID);

      if (dataCustomer != null) {
        if (!dataCustomer.getCustomerId().equalsIgnoreCase(customerID)) {
          System.out.println("\nCustomer ID Tidak ditemukan atau salah\n");
          numberOfError++;
        } else if (!dataCustomer.getPassword().equals(password)) {
          System.out.println("\nPassword yang anda masukan salah\n");
          numberOfError++;
        } else {
          MenuService.customerLoggedIn.add(dataCustomer);
          isLogin = true;
          isLooping = false;
        }
      } else {
        System.out.println("\nCustomer ID dan Password salah!\n");
        numberOfError++;
      }

      if (numberOfError < 3) {
        isLogin = false;
        isLooping = false;
        System.out.println("\nAnda telah gagal Login sebanyak 3x, Sistem dihentikan!\n");
        System.exit(0);
      }
    }while (isLooping);

    return isLogin;
  }

  public static Customer getCustomerById(String customerID){
    return MenuService.listAllCustomers.stream()
            .filter(customer -> customer.getCustomerId().equalsIgnoreCase(customerID))
            .findFirst()
            .orElse(null);
  }
	
	//Info Customer
	
	//Booking atau Reservation
	
	//Top Up Saldo Coin Untuk Member Customer
	
	//Logout
	
}

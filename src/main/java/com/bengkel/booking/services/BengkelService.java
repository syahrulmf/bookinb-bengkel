package com.bengkel.booking.services;

import com.bengkel.booking.models.Customer;
import com.bengkel.booking.models.MemberCustomer;
import com.bengkel.booking.util.Util;

import java.util.Scanner;

public class BengkelService {
  private static Scanner input = new Scanner(System.in);
  public static int numberOfError = 0;
	//Silahkan tambahkan fitur-fitur utama aplikasi disini
	
	//Login
  public static boolean loginService() {
    boolean isLogin = false;
    boolean isLooping = true;

    do {
      String customerID = Validation.validasiInput("Masukan Customer ID: ", "Customer ID tidak ditemukan atau salah!", Validation.regexID);
      String password = Validation.validasiInput("Masukan Password: ", "Password yang anda masukan salah!", Validation.regexID);
      Customer dataCustomer = getCustomerById(customerID);

      if (dataCustomer != null) {
        if (!dataCustomer.getCustomerId().equalsIgnoreCase(customerID)) {
          System.out.println("\nCustomer ID Tidak ditemukan atau salah\n");
          numberOfError++;
        } else if (!dataCustomer.getPassword().equals(password)) {
          System.out.println("\nPassword yang anda masukan salah\n");
          numberOfError++;
        } else {
          MenuService.customerLoggedIn = dataCustomer;
          isLogin = true;
          isLooping = false;
        }
      } else {
        System.out.println("\nCustomer ID dan Password salah!\n");
        numberOfError++;
      }

      if (numberOfError == 3) {
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
  public static void getInfoCustomer() {
    Customer customer = MenuService.customerLoggedIn;
    String member = customer instanceof MemberCustomer ? "Member" : "Non Member";
    System.out.println(member);
    System.out.println("Customer Profile");
    System.out.println("Customer ID     : " + customer.getCustomerId());
    System.out.println("Nama            : " + customer.getName());
    System.out.println("Customer Status : " + member);
    System.out.println("Alamat          : " + customer.getAddress());
    if (customer instanceof MemberCustomer) {
      System.out.println("Saldo Coin      : " + Util.formatCurrency(((MemberCustomer)customer).getSaldoCoin()));
    }
    System.out.println();
    PrintService.printVechicle("List Kendaraan", customer.getVehicles());
  }
	
	//Booking atau Reservation
	
	//Top Up Saldo Coin Untuk Member Customer
	
	//Logout
	
}

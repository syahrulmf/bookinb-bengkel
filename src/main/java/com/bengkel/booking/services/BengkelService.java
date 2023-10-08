package com.bengkel.booking.services;

import com.bengkel.booking.models.*;
import com.bengkel.booking.util.Util;

import java.util.ArrayList;
import java.util.List;


public class BengkelService {
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
  public static void bookingBengkel(String vehicleID, Customer customerLoggedIn) {
    Vehicle dataVehicle = getVehicleById(vehicleID);
    List<ItemService> services = new ArrayList<>();
    List<ItemService> servicesByVehicleType = getServicesByVehicleType(dataVehicle.getVehicleType());
    boolean isService = true;
    String member = customerLoggedIn instanceof MemberCustomer ? "Member" : "Non Member";
    String payment = "Cash";

    PrintService.showAllServices("List Data Service", servicesByVehicleType);
    do {
      String serviceID = Validation.validasiServiceId("Silahkan Masukan Service ID: ", "Service yang dicari tidak tersedia", Validation.regexID, services);
      services.add(getServiceById(serviceID));
      isService = Validation.validasiService("Ingin Pilih Service Yang Lain (Y/T)? ", member);
    } while (isService);

    if (member.equalsIgnoreCase("Member")) {
      payment = Validation.validasiInput("Silahkan Pilih Metode Pembayaran (Saldo Coin atau Cash): ", "Input tidak dimengerti!", "^(?i)(Saldo Coin|Cash)$");
    }

    double totalPaymentService = 0;
    for (ItemService item : services) {
      totalPaymentService += item.getPrice();
    }

    // menambahkan data order ke list data order
    BookingOrder order = new BookingOrder(Util.generateBookingId(), customerLoggedIn, services, payment, totalPaymentService);
    MenuService.listBookingOrder.add(order);
    // jika metode pembayaran menggunakan saldo coin, saldo akan dikurangi dengan total pembayaran
    if (payment.equalsIgnoreCase("Saldo Coin")) {
      ((MemberCustomer) customerLoggedIn).setSaldoCoin(((MemberCustomer) customerLoggedIn).getSaldoCoin() - order.getTotalPayment());
    }

    System.out.println("\nBooking Berhasil");
    System.out.println("Total Harga Service: " + Util.formatCurrency(order.getTotalServicePrice()));
    System.out.println("Total Pembayaran: " + Util.formatCurrency(order.getTotalPayment()));
    System.out.println();
  }

  public static List<ItemService> getServicesByVehicleType(String vehicleType) {
    List<ItemService> services = new ArrayList<>();

    for (ItemService item : MenuService.listAllItemService) {
      if (item.getVehicleType().equalsIgnoreCase(vehicleType)) {
        services.add(item);
      }
    }
    return services;
  }

  public static ItemService getServiceById(String serviceID){
    return MenuService.listAllItemService.stream().filter(service -> service.getServiceId().equalsIgnoreCase(serviceID)).findFirst().orElse(null);
  }

  public static Vehicle getVehicleById(String vehicleID) {
    return MenuService.customerLoggedIn.getVehicles().stream()
            .filter(vehicle -> vehicle.getVehiclesId().equalsIgnoreCase(vehicleID))
            .findFirst()
            .orElse(null);
  }
	
	//Top Up Saldo Coin Untuk Member Customer
	
	//Logout
	
}

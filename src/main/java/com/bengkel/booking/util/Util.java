package com.bengkel.booking.util;

import com.bengkel.booking.services.MenuService;

import java.text.DecimalFormat;

public class Util {
  public static String formatCurrency(double saldo) {
    DecimalFormat df = new DecimalFormat("#,###.00");

    return df.format(saldo);
  }

  private static int bookingNumber = 1;

  public static String generateBookingId() {
    return "Book-Cust-" + String.format("%03d", bookingNumber++) + MenuService.customerLoggedIn.getCustomerId().substring(4, 8);
  }

}

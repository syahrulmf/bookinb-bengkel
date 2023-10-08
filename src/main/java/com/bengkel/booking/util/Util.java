package com.bengkel.booking.util;

import java.text.DecimalFormat;

public class Util {
  public static String formatCurrency(double saldo) {
    DecimalFormat df = new DecimalFormat("#,###.00");

    return df.format(saldo);
  }

}

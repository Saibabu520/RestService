package com.cvl.regression.commons;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Controller;

@Controller
public class UtilFunctions {
  
  /** 
   * The Method will return current date in specified format.
  */
  public static String getCurrentDate() {

    final SimpleDateFormat sdf = new SimpleDateFormat(ConstantsUtil.ISO_DATE,Locale.ENGLISH);
    return sdf.format(new Date());
  } // End of getCurrentDate.
} // End of UtilFunctions.
package io.github.hkust1516csefyp43.easymed.utility;

import android.graphics.Color;
import android.util.Log;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Random;

import io.github.hkust1516csefyp43.easymed.utility.Const.BMI;
import io.github.hkust1516csefyp43.easymed.utility.Const.BMI.WeightForAgeStatus;
import io.github.hkust1516csefyp43.easymed.pojo.Patient;


/**
 * Created by Louis on 5/11/15.
 */
public class Util {
  public final static int HOW_MANY_MS_IN_S = 1000;
  public final static int HOW_MANY_S_IN_MIN = 60;
  public final static int HOW_MANY_MIN_IN_HOUR = 60;
  public final static int HOW_MANY_HOUR_IN_DAY = 24;
  public final static int HOW_MANY_DAY_IN_WEEK = 7;
  public final static long MILLISECOND = 1;
  public final static long SECOND = HOW_MANY_MS_IN_S * MILLISECOND;
  public final static long MINUTE = HOW_MANY_S_IN_MIN * SECOND;
  public final static long HOUR = HOW_MANY_MIN_IN_HOUR * MINUTE;
  public final static long DAY = HOW_MANY_HOUR_IN_DAY * HOUR;
  public final static long WEEK = HOW_MANY_DAY_IN_WEEK * DAY;
  public final static long MONTH = (long) (30.4375 * DAY);
  public final static long YEAR = (long) (365.2422 * DAY);

  private String packageName;

  public static Locale getDefaultLocale() {
    return Locale.ENGLISH;
  }

  public static String lastSeenToString(Date d) {
    Calendar now = Calendar.getInstance();
    long diff = now.getTimeInMillis() - d.getTime();
    return toTimeAgo(diff);
  }

  public static double celsiusToFahrenheit(double c) {
    return (c * 1.8 + 32);
  }

  public static double fahrenheitToCelsius(double f) {
    return ((f - 32) / 1.8);
  }

  public static String toTimeAgo(long dateMsDiff) {
    if (dateMsDiff < MINUTE)
      return "Just now";
    else if (dateMsDiff < 2 * MINUTE)
      return "A minute ago";
    else if (dateMsDiff < 50 * MINUTE)
      return (dateMsDiff / MINUTE + " minutes ago");
    else if (dateMsDiff < 80 * MINUTE)
      return "An hour ago";
    else if (dateMsDiff < 24 * HOUR)
      return (dateMsDiff / HOUR + " hours ago");
    else if (dateMsDiff < 36 * HOUR)
      return ("A day ago");
    else if (dateMsDiff < WEEK)
      return (dateMsDiff / DAY + " days ago");
    else if (dateMsDiff < 2 * WEEK)
      return "A week ago";
    else if (dateMsDiff < MONTH)
      return (dateMsDiff / WEEK + " weeks ago");
    else if (dateMsDiff < 2 * MONTH)
      return "A month ago";
    else if (dateMsDiff < YEAR)
      return (dateMsDiff / MONTH + " months ago");
    else if (dateMsDiff < 2 * YEAR)
      return "A year ago";
    else return (dateMsDiff / YEAR + " years ago");
  }

  /**
   * @param year  of birthday (the normal 4 digit format that human uses)
   * @param month of birthday (1-12, not 0-11)
   * @param day   of birthday (1-31)
   * @return a string of age description:
   */
  public static String birthdayToAgeString(int year, int month, int day) {
    GregorianCalendar n = new GregorianCalendar();
    if (year < 1800 || month < 1 || month > 12 || day < 1 || day > 31 || new GregorianCalendar(year, month - 1, day).compareTo(n) != -1) {
      return "Invalid birthday";
    } else {
      long now = new GregorianCalendar().getTimeInMillis();
      long bd = new GregorianCalendar(year, month - 1, day).getTimeInMillis();
      long millDiff = now - bd;
      if (millDiff < (48 * HOUR))
        return "1 day old";
      else if (millDiff < (MONTH))
        return (millDiff / DAY + " days old");
      else if (millDiff < (2 * MONTH))
        return "1 month old";
      else if (millDiff < (2 * YEAR))
        return (millDiff / MONTH + " months old");
      else
        return (millDiff / YEAR + " years old");
    }
  }

  /**
   * Extract text from drawable
   *
   * @param p
   * @return
   */
  public static String getTextDrawableText(Patient p) {
    String op;
    if (p != null) {
      if (p.getFirstName() != null) {
        if (p.getLastName() != null) {
          op = p.getFirstName().substring(0, 1) + p.getLastName().substring(0, 1);
        } else {
          op = p.getFirstName().substring(0, 1);
        }
      } else {
        if (p.getLastName() != null) {
          op = p.getLastName().substring(0, 1);
        } else {
          op = "?";
        }
      }
    } else {
      op = "?";
    }
    return op.toUpperCase(Locale.ENGLISH);
  }

  /**
   * @param s >> the name
   * @return true if the whole name is english + number + space
   */
  public static boolean isValidEnglishChar(String s) {
    for (char c : s.toCharArray()) {
      if ((c != ' ') && (c < '0' || c > 'z' || (c > '9' && c < 'A') || (c > 'Z' && c < 'a')))
        return false;
    }
    return true;
  }

  /**
   * Generate a random color
   * @return a color-int
   */
  public static int getRandomColor() {
    Random rand = new Random();
    int r, g, b;
    do {
      r = rand.nextInt(255);
    } while (r < 50);
    do {
      g = rand.nextInt(255);
    } while (g < 50);
    do {
      b = rand.nextInt(255);
    } while (b < 50);
    return Color.rgb(r, g, b);
  }

  /**
   * r >> first letter >> 1-37 >> 0-255 >> round(256x/37)
   * g >> second letter >> 1-38 >> 0-255 >> round(256y/38)
   * b >> first letter * second letter >> 1-1406 >> 0-255 >> round(256z/1406)
   *
   * return a color based on the text
   * logic:
   * 1. map the first letter to 1-37 (0-9, a-z, space) >> preR
   * 2. map the second letter to 1-38 (null, 0-9, a-z, space) >> preG
   * 3. third number = multiply of the first 2 >> preB
   * 4. map preR to 0-255 >> R
   * 5. map preG to 0-255 >> G
   * 6. map preB to 0-255 >> B
   * 7. return color
   *
   * @param text the 1/2 letter(s) on the TextDrawable
   * @return a color-int
   */
  public static int getTextDrawableColor(String text) {
    if (text.length() > 2) {
      return getRandomColor();
    } else {
      String textLC = text.toLowerCase(Locale.ENGLISH);
      Character first = textLC.charAt(0);         //extract first char >> r
      Character second;
      if (textLC.length() < 2)
        second = null;
      else
        second = textLC.charAt(1);              //extract second char >> g
      int preR = charToInt(first);                //map first char to 1-37
      int preG = charToInt(second);               //map second char to 1-38
      int preB = preR * preG;                     //r*g >> b (should match 1-1406)
      int r = 256 * preR / 37;
      int g = 256 * preG / 38;
      int b = 256 * preB / 1406;
//            Log.d("qqq63", text + " >> " + preR + "," + preG + "," + preB);
//            Log.d("qqq64", text + " >> " + r + "," + g + "," + b);
      return Color.rgb(r, g, b);
    }
  }

  private static int charToInt(Character c) {
    if (c == null)                      //null >> 1
      return 1;
    int ascii = (int) c;
    if (c == 32)                        //space >> 37
      return 37;
    if (c >= 'a' && c <= 'z')           //
      return ascii - 86;
    if (c >= '0' && c <= '9')
      return ascii - 47;
    else
      return 0;
  }

  /**
   * Round number
   *
   * @param num    is the number you want to round
   * @param length of decimal places you want (e.g. 2 >> #.##)
   */
  public static String roundNumber(double num, int length) {
    String format = "#";
    if (length > 0)
      format += ".";
    for (int i = 0; i < length; i++) {
      format += "#";
    }
    DecimalFormat df = new DecimalFormat(format);
    df.setRoundingMode(RoundingMode.CEILING);
    return df.format(num);
  }

  /**
   * Check if weight vs age is normal
   *
   * @param ageMonths 0-60 (inclusive) i.e. newborn to 5 years old
   * @param weight    of child in kh
   * @param isMale    is true if is male
   * @return Overweight, Normal, Underweight or Super underweight
   */
  public static WeightForAgeStatus getWeightForAgeStatus(int ageMonths, double weight, boolean isMale) {
    if (ageMonths > 60)
      return null;
    double[] list = new double[5];
    if (isMale) { //male
      for (int i = 0; i < 5; i++) {
        list[i] = BMI.weightForAgeBoyUnder5[i][ageMonths];
        Log.d("qqq290", "" + list[i]);
      }
    } else {    //female
      for (int i = 0; i < 5; i++) {
        list[i] = BMI.weightForAgeGirlUnder5[i][ageMonths];
        Log.d("qqq290", "" + list[i]);
      }
    }
    int j = 4;
    int position = -1;
    while (j >= 0 && position == -1) {
      Log.d("qqq292", "" + list[j] + " vs " + weight);
      if (weight >= list[j])
        position = j;
      else
        j--;
    }
    switch (position) {
      case 0:             //super underweight
        return WeightForAgeStatus.TOO_UNDERWEIGHT;
      case 1:             //underweight
        return WeightForAgeStatus.UNDERWEIGHT;
      case 2:             //normal
      case 3:             //normal
        return WeightForAgeStatus.NORMAL;
      case 4:             //overweight
        return WeightForAgeStatus.OVERWEIGHT;
      default:
        return WeightForAgeStatus.NORMAL;
    }
  }

  public static String dateInStringOrToday(Date date) {
    GregorianCalendar gc = new GregorianCalendar();
    gc.setTime(date);
    GregorianCalendar today = new GregorianCalendar();
    if (gc.get(Calendar.YEAR) == today.get(Calendar.YEAR) && gc.get(Calendar.MONTH) == today.get(Calendar.MONTH) && gc.get(Calendar.DAY_OF_MONTH) == today.get(Calendar.DAY_OF_MONTH)) {
      return "Today";
    } else {
      return dateInString(gc);
    }
  }

  private static String dateInString(GregorianCalendar gc) {
    return "" + gc.get(Calendar.YEAR) + "-" + (gc.get(Calendar.MONTH) + 1) + "-" + gc.get(Calendar.DAY_OF_MONTH);
  }

  public static String dateInString(Date date) {
    GregorianCalendar gc = new GregorianCalendar();
    gc.setTime(date);
    return dateInString(gc);
  }

  public static String todayString() {
    GregorianCalendar gc = new GregorianCalendar();
    return dateInString(gc);
  }

}
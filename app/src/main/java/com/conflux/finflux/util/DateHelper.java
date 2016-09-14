package com.conflux.finflux.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by jagadeeshakn on 7/9/2016.
 */
public class DateHelper {

    public static final String DATE_FORMAT_VALUE = "dd MMM yyyy";

    public static String getCurrentDateAsString(){

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        String date = day + " - " + (month + 1) + " - " + year;

        return date;
    }

    public static List<Integer> getCurrentDateAsListOfIntegers() {

        List<Integer> date = new ArrayList<Integer>();
        Calendar calendar = Calendar.getInstance();
        date.add(calendar.get(Calendar.YEAR));
        date.add(calendar.get(Calendar.MONTH)+1);
        date.add(calendar.get(Calendar.DAY_OF_MONTH));

        return date;
    }

    public static String getDateAsStringUsedForCollectionSheetPayload(String date) {
        final StringBuilder builder = new StringBuilder();
        if (date != null) {
            String[] splittedDate = date.split("-");
            int month = Integer.parseInt(splittedDate[1]);
            builder.append(splittedDate[0]);
            builder.append("-");
            builder.append(getMonthName(month));
            builder.append("-");
            builder.append(splittedDate[2]);
        }
        return builder.toString();
        //Return as dd-mmm-yyyy

    }
    public static String getDateAsStringUsedForCollectionSheet(String date) {
        final StringBuilder builder = new StringBuilder();
        if (date != null) {
            String[] splittedDate = date.split("-");
            int month = Integer.parseInt(splittedDate[1]);
            builder.append(splittedDate[0]);
            builder.append(" ");
            builder.append(getMonthName(month));
            builder.append(" ");
            builder.append(splittedDate[2]);
            System.out.println("datebuilder" + builder);
        }
        return builder.toString();
        //Return as dd-mmm-yyyy

    }

    //Currently supports on "dd MM yyyy"
    public static String getCurrentDateAsDateFormat() {

        Calendar calendar = Calendar.getInstance();

        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        String date = day + " " + (month + 1) + " " + year;


        return date;

    }

    /**
     *
     * @param integersOfDate
     * @return date in format dd MMM yyyy
     */

    public static String getDateAsString(List<Integer> integersOfDate) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(integersOfDate.get(2))
                .append(" ")
                .append(getMonthName(integersOfDate.get(1)))
                .append(" ")
                .append(integersOfDate.get(0));

        return stringBuilder.toString();

    }

    /**
     *
     * @return zero if both date1 and date2 are equal, positive int if date1 > date2
     * and negative int if date1 < date2
     */

    public static int dateComparator(List<Integer> date1, List<Integer> date2) {

        /*
         *  Each List contains 3 items
         *  index 0 = Year
         *  index 1 = Month
         *  index 2 = Day
         *
         *  Format is YYYY - MM - DD
        */

        //comparing years
        if(date1.get(0).equals(date2.get(0))) {

            //now that years are equal lets compare months

            if(date1.get(1).equals(date2.get(1))) {

                //now that months are also equal lets compare days

                if(date1.get(2).equals(date2.get(2))){
                    return 0;
                } else if(date1.get(2) > date2.get(2)) {
                    return 1;
                } else {
                    return -1;
                }

            } else if(date1.get(1) > date2.get(1)) {
                return 1;
            } else {
                return -1;
            }

        }else if(date1.get(0) > date2.get(0)) {
            return 1;
        } else {
            return -1;
        }
    }

    public static String getMonthName(int month) {
        String monthName = "";
        switch (month) {
            case 1:
                monthName = "Jan";
                break;
            case 2:
                monthName = "Feb";
                break;
            case 3:
                monthName = "Mar";
                break;
            case 4:
                monthName = "Apr";
                break;
            case 5:
                monthName = "May";
                break;
            case 6:
                monthName = "Jun";
                break;
            case 7:
                monthName = "Jul";
                break;
            case 8:
                monthName = "Aug";
                break;
            case 9:
                monthName = "Sep";
                break;
            case 10:
                monthName = "Oct";
                break;
            case 11:
                monthName = "Nov";
                break;
            case 12:
                monthName = "Dec";
                break;
        }
        return monthName;
    }

    public static String getMonthFullName(int month) {
        String monthName = "";
        switch (month) {
            case 1:
                monthName = "January";
                break;
            case 2:
                monthName = "February";
                break;
            case 3:
                monthName = "March";
                break;
            case 4:
                monthName = "April";
                break;
            case 5:
                monthName = "May";
                break;
            case 6:
                monthName = "June";
                break;
            case 7:
                monthName = "July";
                break;
            case 8:
                monthName = "August";
                break;
            case 9:
                monthName = "September";
                break;
            case 10:
                monthName = "October";
                break;
            case 11:
                monthName = "November";
                break;
            case 12:
                monthName = "December";
                break;
        }
        return monthName;
    }

    /*public static String getPayloadDate(Context context) {
        if (Constants.applicationContext == null)
            Log.i("DateHelper", "Application Context is null!");
        SharedPreferences preferences = context.getSharedPreferences(OfflineCenterInputActivity.PREF_CENTER_DETAILS, Context.MODE_PRIVATE);
        String date = preferences.getString(OfflineCenterInputActivity.TRANSACTION_DATE_KEY, null);
        final StringBuilder builder = new StringBuilder();
        if (date != null) {
            String[] splittedDate = date.split("-");
            int month = MeetingFallCenterInteger.parseInt(splittedDate[1]);
            builder.append(splittedDate[0]);
            builder.append(" ");
            builder.append(DateHelper.getMonthName(month));
            builder.append(" ");
            builder.append(splittedDate[2]);
        }
        return builder.toString();
    }
    public static String getPayloadDate() {
        SharedPreferences preferences = Constants.applicationContext.getSharedPreferences(OfflineCenterInputActivity.PREF_CENTER_DETAILS, Context.MODE_PRIVATE);
        String date = preferences.getString(OfflineCenterInputActivity.TRANSACTION_DATE_KEY, null);
        final StringBuilder builder = new StringBuilder();
        if (date != null) {
            String[] splittedDate = date.split("-");
            int month = MeetingFallCenterInteger.parseInt(splittedDate[1]);
            builder.append(splittedDate[0]);
            builder.append(" ");
            builder.append(DateHelper.getMonthName(month));
            builder.append(" ");
            builder.append(splittedDate[2]);
        }
        return builder.toString();
    }
*/
    public static List<Integer> getDateList(String date,String separator){
        String[] splittedDate = date.split(separator);
        List<Integer> dateList = new ArrayList<>();
        for(int i=0;i<3;i++){
            dateList.add(Integer.parseInt(splittedDate[i]));
        }

        return dateList;
    }


    //input dd/mmmm/yyyy
    public static Spanned getDateStringSpannedFormat(String date){
        String[] dateString = date.split(" ");
        String day = removeLeadingZeroes(dateString[0]);
        String superScript = getDayNumberSuffix(Integer.parseInt(day));
        String month = dateString[1];
        String year = dateString[2];
        Spanned spanned = Html.fromHtml(day + superScript + " " + month + " " + year);
        return spanned;
    }

    public static String getDayNumberSuffix(int day) {
        if (day >= 11 && day <= 13) {
            return "<sup>th </sup>";
        }
        switch (day % 10) {
            case 1:
                return "<sup>st </sup>\t";
            case 2:
                return "<sup>nd </sup>\t";
            case 3:
                return "<sup>rd </sup>\t";
            default:
                return "<sup>th </sup>\t";
        }
    }
    public static String removeLeadingZeroes(String value) {
        if (Pattern.matches("[0]+", value)) {
            return "0";
        } else {
            while (value.indexOf("0") == 0) {
                value = value.substring(1);
            }
            return value;
        }
    }
    // dd/mmmm/yyyy
    public static String getDateFormatFullString(String meetingDate){
        String[] date = meetingDate.split(" ");
        String day = date[0];
        String month = getMonthFullName(Integer.parseInt(date[1]));
        String year = date[2];
        return day+" "+month+" "+year;
    }

    public static String getDateFormatFullName(String meetingDate){
        String[] date = meetingDate.split(" ");
        String day = removeLeadingZeroes(date[0]);
        String superScript = getDayNumberSuffix(Integer.parseInt(day));
        String month = getMonthFullName(Integer.parseInt(date[1]));
        String year = date[2];
        String date1 = day+" "+month+" "+year;
        return date1;
    }


    //returns Day of the week
    public static String getDayOfTheDate(String dateFullString,String dateFormat){
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(dateFormat);
        Date date1 = null;
        try {
            date1 = (Date)simpleDateFormat1.parse(dateFullString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(date1!=null){
            DateFormat format2=new SimpleDateFormat("EEEE");
            String day=format2.format(date1);
            return day;
        }
        return null;
    }

    // dd/mmmm/yyyy
    public static Spanned getDateFormatTodisplay(String meetingDate){
        String[] date = meetingDate.split(" ");
        String day = removeLeadingZeroes(date[0]);
        String superScript = getDayNumberSuffix(Integer.parseInt(day));
        String month = getMonthFullName(Integer.parseInt(date[1]));
        String year = date[2];
        Spanned spanned = Html.fromHtml(day + superScript + " " + month + " " + year);
        return spanned;
    }
}

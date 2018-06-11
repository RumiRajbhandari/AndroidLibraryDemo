package com.example.demomodule.outletDetail

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.net.ConnectivityManager
import android.text.SpannableString
import android.text.Spanned
import android.text.TextUtils
import android.text.style.AbsoluteSizeSpan
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


@SuppressLint("MissingPermission")
fun isNetworkAvailable(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connectivityManager.activeNetworkInfo
    return networkInfo != null && networkInfo.isConnected
}


//Adds comma in given value
fun formatPrice(orderPrice: String): String {
    val longVal = java.lang.Double.parseDouble(orderPrice)
    val formatter = NumberFormat.getInstance(Locale.US) as DecimalFormat
    formatter.applyPattern("###,###,###,###.###")
    return formatter.format(longVal)
}

//Returns the modulus of two values
fun getMod(first: Double, second: Int): String {
    var mod = first % second
    var decimalFormat = DecimalFormat("##")
    return "." + decimalFormat.format(mod * 100)
}

fun getFormattedText(value: Double): CharSequence {
    var trimmedAmount = value.toInt()
    var textDecimal = ""
    var text = formatPrice(DecimalFormat("##.##").format(trimmedAmount))
    if (trimmedAmount > 0) {
        textDecimal = getMod(value, trimmedAmount)
    }

    var spannableString = SpannableString(text)
    spannableString.setSpan(AbsoluteSizeSpan(18, true), 0, text.length, Spanned.SPAN_INCLUSIVE_INCLUSIVE)

    var spanDecimal = SpannableString(textDecimal)
    spanDecimal.setSpan(AbsoluteSizeSpan(12, true), 0, textDecimal.length, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
    return TextUtils.concat(spannableString, spanDecimal)
}

fun formatDateWithTimeStamp(inputDate: String): String {
    val mdf = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    val sdf = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault())
    val date = sdf.parse(inputDate)
    return mdf.format(date)
}

fun getTotalNoOfDays(latestDate: String?): Long {
    val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    var d: Date? = null
    d = try {
        formatter.parse(latestDate)
    } catch (e: ParseException) {
        val formatter2 = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        try {
            formatter2.parse(latestDate)
        } catch (e1: ParseException) {
            return 0L
        }

    }

    val thatDay = Calendar.getInstance()
    thatDay.time = d
    val today = Calendar.getInstance()
    val diff = today.timeInMillis - thatDay.timeInMillis
    return diff / (24 * 60 * 60 * 1000)
}

fun formatMonths(inputDate: String): String {
    val monthDate = SimpleDateFormat("MMM", Locale.getDefault())
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val date = sdf.parse(inputDate)
    return monthDate.format(date)
}

fun formatDate(inputDate: String): String {
    val mdf = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val date = sdf.parse(inputDate)
    return mdf.format(date)
}

fun formatMilliSecondToHour(millisecondDate: Long):String{
    if (millisecondDate.equals(0L)){
        return "00:00"
    }
    val hourFormatter =SimpleDateFormat("HH:mm",Locale.getDefault())
    val calender=Calendar.getInstance()
    calender.timeInMillis=millisecondDate
    return hourFormatter.format(calender.time)
}

fun formatMilliSecondToMonthAndDay(millisecondDate: Long):String{
    val dateFormatter =SimpleDateFormat("MMM-dd",Locale.getDefault())
    val calender=Calendar.getInstance()
    calender.timeInMillis=millisecondDate
    return dateFormatter.format(calender.time)

}

fun formatMilliSecondToMonth(millisecondDate: Long):String{
    val dateFormatter =SimpleDateFormat("yyyy-MM-dd",Locale.getDefault())
    val calender=Calendar.getInstance()
    calender.timeInMillis=millisecondDate
    return dateFormatter.format(calender.time)

}

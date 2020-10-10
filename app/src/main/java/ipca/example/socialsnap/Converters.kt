package ipca.example.socialsnap


import java.text.SimpleDateFormat
import java.util.*

//
// Created by lourencogomes on 07/05/2020.
//

fun dateToString(date : Date) : String{
    val formatter = SimpleDateFormat("dd MM yyyy hh:mm", Locale.getDefault())
    return formatter.format(date)
}

fun stringToDate(dateStr: String) : Date {
    val formatter = SimpleDateFormat("dd MM yyyy hh:mm", Locale.getDefault())
    val date = formatter.parse(dateStr)

    return date ?: Date()

}
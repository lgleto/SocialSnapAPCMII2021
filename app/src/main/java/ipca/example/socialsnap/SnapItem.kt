package ipca.example.socialsnap

import java.util.*
import kotlin.collections.HashMap

//
// Created by lourencogomes on 09/10/2020.
//
class SnapItem {

    var filePath    : String? = null
    var description : String? = null
    var date        : Date?   = null
    var userId      : String? = null

    constructor(
        filePath: String?,
        description: String?,
        date: Date?,
        userId: String?
    ) {
        this.filePath = filePath
        this.description = description
        this.date = date
        this.userId = userId
    }

    fun toHashMap() : HashMap<String, Any?>{
        val hashMap = HashMap<String, Any?>()
        hashMap["filePath"] = filePath
        hashMap["description"] = description
        hashMap["date"] = date?.let { dateToString(it) }
        hashMap["userId"] = userId
        return hashMap
    }

    companion object {
        fun formHash(hashMap:  HashMap<String, Any?>) : SnapItem {
            val item = SnapItem(
                hashMap["filePath"].toString(),
                hashMap["description"].toString(),
                stringToDate(hashMap["date"].toString()),
                hashMap["userId"].toString()
            )
            return item
        }
    }

}
package com.qi.student.student

import android.os.Parcel
import android.os.Parcelable

//class StudentModel {
//    var username : String = ""
//    var address : String = ""
//}

class StudentModel() : Parcelable{
    var studentId : Int? = 0
    var username : String? = ""
    var address : String? = ""
    var email : String? = ""
    var phone : String? = ""

    constructor(parcel: Parcel) : this() {
        studentId = parcel.readValue(Int::class.java.classLoader) as? Int
        username = parcel.readString()
        address = parcel.readString()
        email = parcel.readString()
        phone = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(studentId)
        parcel.writeString(username)
        parcel.writeString(address)
        parcel.writeString(email)
        parcel.writeString(phone)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<StudentModel> {
        override fun createFromParcel(parcel: Parcel): StudentModel {
            return StudentModel(parcel)
        }

        override fun newArray(size: Int): Array<StudentModel?> {
            return arrayOfNulls(size)
        }
    }
}
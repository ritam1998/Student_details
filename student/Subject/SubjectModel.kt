package com.qi.student.Subject

import android.os.Parcel
import android.os.Parcelable

class SubjectModel() : Parcelable {

    var subjectid : Int ?= 0
    var subjectname : String ?= ""

    constructor(parcel: Parcel) : this() {
        subjectid = parcel.readValue(Int::class.java.classLoader) as? Int
        subjectname = parcel.readString()
    }

    //    override fun toString(): String {
//        return subjectname
//    }
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(subjectid)
        parcel.writeString(subjectname)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SubjectModel> {
        override fun createFromParcel(parcel: Parcel): SubjectModel {
            return SubjectModel(parcel)
        }

        override fun newArray(size: Int): Array<SubjectModel?> {
            return arrayOfNulls(size)
        }
    }

}
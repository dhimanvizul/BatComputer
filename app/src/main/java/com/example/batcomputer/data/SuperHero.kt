package com.example.batcomputer.data

import android.os.Parcel
import android.os.Parcelable

data class SuperHero(val name: String?, val mail: String?, val type: String?, val image: Int,
                     val essay: String?) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(mail)
        parcel.writeString(type)
        parcel.writeInt(image)
        parcel.writeString(essay)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SuperHero> {
        override fun createFromParcel(parcel: Parcel): SuperHero {
            return SuperHero(parcel)
        }

        override fun newArray(size: Int): Array<SuperHero?> {
            return arrayOfNulls(size)
        }
    }
}
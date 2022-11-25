package com.finalatixassignment.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class ResponseNameData(
    var name: String,
    var realname: String,
    var team: String,
    var firstappearance: String,
    var createdby: String,
    var publisher: String,
    var imageurl: String,
    var bio: String
):Parcelable

package edu.uca.ni.clasificacion_pelicula.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "tbl_clasificacion")
data class Clasificacion(

    @PrimaryKey(autoGenerate = true)val id: Int,
    val n_clasificacion: String,
    val abreviacion: String

):Parcelable

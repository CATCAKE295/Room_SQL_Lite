package edu.uca.ni.clasificacion_pelicula.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import edu.uca.ni.clasificacion_pelicula.model.Clasificacion

@Database(entities = [Clasificacion::class],version = 1,exportSchema = false)
abstract class ClasificacionDatabase: RoomDatabase() {

    abstract fun clasificacionDao(): ClasificacionDao

    companion object{

        @Volatile
        private var INSTANCE: ClasificacionDatabase? = null

                fun getDataBase(context:Context): ClasificacionDatabase{

                    val tempInstance = INSTANCE
                    if (tempInstance != null){

                        return tempInstance

                    }
                    synchronized(this){

                        val instance = Room.databaseBuilder(
                            context.applicationContext,ClasificacionDatabase::class.java,"clasificacion_database"
                        ).build()
                        INSTANCE = instance
                        return instance
                    }

                }
    }
}
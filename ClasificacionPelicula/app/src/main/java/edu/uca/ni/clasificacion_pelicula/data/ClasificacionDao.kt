package edu.uca.ni.clasificacion_pelicula.data

import androidx.lifecycle.LiveData
import androidx.room.*
import edu.uca.ni.clasificacion_pelicula.model.Clasificacion

@Dao
interface ClasificacionDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addClasificacion(clasificacion: Clasificacion)

    @Query("SELECT * FROM tbl_clasificacion")
    fun  readAllData(): LiveData<List<Clasificacion>>

    @Update
    fun updateClasificacion(clasificacion: Clasificacion)

    @Delete
    fun deleteClasificacion(clasificacion: Clasificacion)

    @Query("DELETE FROM tbl_clasificacion")
    fun deleteAllClasificacion()
}
package com.example.hanyarunrun.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DataDao {

    @Insert
    suspend fun insert(data: DataEntity)

    @Update
    suspend fun update(data: DataEntity)

    @Delete
    suspend fun delete(data: DataEntity)

    @Query("SELECT * FROM data_table ORDER BY id DESC")
    fun getAll(): LiveData<List<DataEntity>>

    @Query("SELECT * FROM data_table WHERE id = :dataId")
    suspend fun getById(dataId: Int): DataEntity?
}

@Dao
interface ProfileDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(profile: ProfileEntity)

    @Query("SELECT * FROM profile_table WHERE id = 1")
    fun getProfile(): LiveData<ProfileEntity?>

}

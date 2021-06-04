package com.example.moviedatabase.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.moviedatabase.Model.Result

@Dao
interface MovieDao {

    @Query("select * from Result")
    fun getAllMovies(): LiveData<List<Result>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllMovie(result: List<Result>)

    // Bookmark

    @Query("select * from Result where isSelected = 1")
    fun getFavourite(): LiveData<List<Result>>

    @Query("select isSelected from Result where id = :id")
    suspend fun updateBookmark(id: Int): Boolean

    @Query("update Result set isSelected = 1 where id = :id  ")
    suspend fun isSelected(id: Int)

    @Query("update Result set isSelected = 0 where id = :id")
    suspend fun isNotSelected(id: Int)

}
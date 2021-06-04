package com.example.moviedatabase.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.moviedatabase.Dao.MovieDao
import com.example.moviedatabase.Model.Result

@Database(entities = [Result::class], version = 1)
abstract class MovieDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object{
        private var instance: MovieDatabase?= null

        @Synchronized
        fun getDatabase(context: Context): MovieDatabase{
            if (instance == null) {
                instance = Room.databaseBuilder(context.applicationContext,
                    MovieDatabase::class.java,"movie_database")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance!!
        }


    }

}
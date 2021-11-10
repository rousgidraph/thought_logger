package com.example.log_thoughts.data

import android.os.Handler
import android.os.Looper
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Inject
import javax.inject.Singleton


//@Singleton
class ThoughtLocalDataSource @Inject constructor(private val thoughtDao: ThoughtDao){

    private val executorService: ExecutorService = Executors.newFixedThreadPool(4)
    private val mainThreadHandler by lazy {
        Handler(Looper.getMainLooper())
    }

    fun addThought(msg: String) {
        executorService.execute {
            thoughtDao.insertAll(
                Thought(
                    msg,
                    System.currentTimeMillis()
                )
            )
        }
    }

    fun getAllLogs(callback: (List<Thought>) -> Unit) {
        executorService.execute {
            val logs = thoughtDao.getAll()
            mainThreadHandler.post { callback(logs) }
        }
    }

    fun removeLogs() {
        executorService.execute {
            thoughtDao.nukeTable()
        }
    }

}
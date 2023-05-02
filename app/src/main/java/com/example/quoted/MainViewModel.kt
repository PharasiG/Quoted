package com.example.quoted

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.google.gson.Gson

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private var quoteList: Array<Quote> = emptyArray()
    private var index: Int = 0
    private var size = 0

    init {
        quoteList = loadQuoteFromAssets()
        size = quoteList.size
    }

    private fun loadQuoteFromAssets(): Array<Quote> {
        val inputStream = getApplication<Application>().assets.open("quotes.json")
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()

        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        return gson.fromJson(json, Array<Quote>::class.java)
    }

    fun getQuote() = quoteList[index]

    fun nextQuote(): Quote {
        if (++index == size) index = 0
        return quoteList[index]
    }

    fun prevQuote(): Quote {
        if (--index < 0) index = size - 1
        return quoteList[index]
    }
}






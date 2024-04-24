package com.example.quote

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson

class MainViewModel(val context: Context): ViewModel() {
    private var quoteList :Array<Quote> = emptyArray()
    private var index = 0

    init {
        quoteList = getQuoteFromAssets()
    }

    private fun getQuoteFromAssets(): Array<Quote> {
       val inputStream = context.assets.open("quotes.json")
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer,Charsets.UTF_8)
        val gson = Gson()
        return gson.fromJson(json,Array<Quote>::class.java)
    }

    fun getQuote() = quoteList[index]

    fun getNextQuote(): Quote {
        if(index+1>=quoteList.size){
            return quoteList[index]
        }
        return  quoteList[++index]
    }

    fun getPreviousQuote() :Quote{
        if(index-1<=-1){
            return quoteList[index]
        }
        return  quoteList[--index]
    }
}
package com.example.quote

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel : MainViewModel

    private val quoteTxt: TextView
        get() = findViewById(R.id.quoteText)
    private val author: TextView
        get() = findViewById(R.id.author)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel = ViewModelProvider(this,MainViewModelFactory(application)).get(MainViewModel::class.java)
        setQuote(mainViewModel.getQuote())
    }

    fun setQuote(quote: Quote){
        quoteTxt.text = quote.text
        author.text = quote.author
    }

    fun onPrevious(view: View) {
        setQuote(mainViewModel.getPreviousQuote())
    }
    fun onNext(view: View) {
        setQuote(mainViewModel.getNextQuote())
    }
    fun onShare(view: View) {
        var intent = Intent(Intent.ACTION_SEND)
        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_TEXT,mainViewModel.getQuote().text)
        startActivity(intent)
    }
}
package com.example.quoted

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.quoted.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var data: MainViewModel
    private lateinit var quoteText: TextView
    private lateinit var quoteAuthor: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        data = ViewModelProvider(this).get(MainViewModel::class.java)
        quoteText = binding.quoteText
        quoteAuthor = binding.quoteAuthor

        setQuote(data.getQuote())
    }

    private fun setQuote(quote: Quote) {
        quoteText.text = quote.text
        quoteAuthor.text = quote.author
    }

    fun onNext(view: View) = setQuote(data.nextQuote())

    fun onPrev(view: View) = setQuote(data.prevQuote())

    fun onShare(view: View) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        val text = getString(R.string.shareable_text, data.getQuote().text, data.getQuote().author)
        intent.putExtra(Intent.EXTRA_TEXT, text)
        startActivity(intent)
    }
}
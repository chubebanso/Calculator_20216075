package com.example.mycomputer

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener
import kotlin.time.times

class CurrencyActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        enableEdgeToEdge();
        setContentView(R.layout.currency_layout);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.currency)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val source = findViewById<EditText>(R.id.source)
        val target = findViewById<EditText>(R.id.target)
        val spinner1 = findViewById<Spinner>(R.id.spinner1)
        val spinner2 = findViewById<Spinner>(R.id.spinner2)


        val items : Array<String> = arrayOf("USD", "VND", "JPY","EUR","CNY");
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner1.adapter = adapter
        spinner2.adapter = adapter

        source.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                convertCurrency(source, target, spinner1, spinner2)
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                convertCurrency(source, target, spinner1, spinner2)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Xử lý khi không có mục nào được chọn nếu cần
            }
        }

        spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                convertCurrency(source, target, spinner1, spinner2)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Xử lý khi không có mục nào được chọn nếu cần
            }
        }
    }
    fun convertCurrency(source :EditText, target : EditText, spinner1: Spinner, spinner2: Spinner){
        val money = source.text.toString().toDoubleOrNull() ?: return
        val rateSource = getRate(spinner1.selectedItem.toString())
        val rateTarget = getRate(spinner2.selectedItem.toString())
        target.setText(String.format("%.2f", money * rateTarget / rateSource))
    }

    fun getRate(currency : String) : Double {
        if(currency === "USD") return 1.0;
        if(currency === "VND") return 25310.00;
        if(currency === "EUR") return 0.93;
        if(currency === "JPY") return 153.30;
        if(currency === "CNY") return 7.14;
        return TODO("Provide the return value")
    }

}
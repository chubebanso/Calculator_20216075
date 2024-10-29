package com.example.mycomputer

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class ComputerActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var textResult: TextView

    var state: Int = 1
    var op: Int = 0
    var op1: Int = 0
    var op2: Int = 0

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.linear_layout)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        textResult = findViewById(R.id.result)
        findViewById<Button>(R.id.btn0).setOnClickListener(this)
        findViewById<Button>(R.id.btn1).setOnClickListener(this)
        findViewById<Button>(R.id.btn2).setOnClickListener(this)
        findViewById<Button>(R.id.btn3).setOnClickListener(this)
        findViewById<Button>(R.id.btn4).setOnClickListener(this)
        findViewById<Button>(R.id.btn5).setOnClickListener(this)
        findViewById<Button>(R.id.btn6).setOnClickListener(this)
        findViewById<Button>(R.id.btn7).setOnClickListener(this)
        findViewById<Button>(R.id.btn8).setOnClickListener(this)
        findViewById<Button>(R.id.btn9).setOnClickListener(this)
        findViewById<Button>(R.id.btnAdd).setOnClickListener(this)
        findViewById<Button>(R.id.btnSub).setOnClickListener(this)
        findViewById<Button>(R.id.btnMul).setOnClickListener(this)
        findViewById<Button>(R.id.btnDiv).setOnClickListener(this)
        findViewById<Button>(R.id.btnEqual).setOnClickListener(this)
        findViewById<Button>(R.id.btnBS).setOnClickListener(this)
        findViewById<Button>(R.id.btnC).setOnClickListener(this)
        findViewById<Button>(R.id.btnCE).setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        val id = p0?.id
        if (id == R.id.btn0) addDigit(0)
        if (id == R.id.btn1) addDigit(1)
        if (id == R.id.btn2) addDigit(2)
        if (id == R.id.btn3) addDigit(3)
        if (id == R.id.btn4) addDigit(4)
        if (id == R.id.btn5) addDigit(5)
        if (id == R.id.btn6) addDigit(6)
        if (id == R.id.btn7) addDigit(7)
        if (id == R.id.btn8) addDigit(8)
        if (id == R.id.btn9) addDigit(9)
        if (id == R.id.btnBS) removeDigit()
        if (id == R.id.btnC) removeC()
        if (id == R.id.btnCE) removeCE()

        if(id == R.id.btnAdd) {
            op = 1
            state = 2
        }
        if(id == R.id.btnSub) {
            op = 2
            state = 2
        }
        if(id == R.id.btnMul) {
            op = 3
            state = 2
        }
        if(id == R.id.btnDiv) {
            op = 4
            state = 2
        }

        if (id == R.id.btnEqual) {
            var result = 0
            if (op == 1) {
                result = op1 + op2
            }
            if (op == 2) {
                result = op1 - op2
            }
            if (op == 3) {
                result = op1 * op2
            }
            if (op == 4 && op2 != 0) {
                result = op1 / op2
            } else if (op == 4 && op2 == 0) {
                textResult.text = "Error"
                return
            }
            textResult.text = "$result"
            state = 1
            op1 = 0
            op2 = 0
            op = 0
        }
    }
    fun addDigit(c: Int) {
        if (state == 1) {
            op1 = op1 * 10 + c
            textResult.text = "$op1"
        }else {
            op2 = op2 * 10 + c
            textResult.text = "$op2"
        }
    }

    fun removeDigit(){
        if(state == 1){
            op1 = op1 / 10
            textResult.text = "$op1"
        }else{
            op2 = op2 / 10
            textResult.text = "$op2"
        }
    }

    fun removeCE(){
        if(state == 1){
            op1 = 0
            textResult.text = "$op1"
        }else{
            op2 = 0
            textResult.text = "$op2"
        }
    }

    fun removeC(){
        textResult.text = "0"
        state = 1
        op1 = 0
        op2 = 0
        op = 0
    }



}

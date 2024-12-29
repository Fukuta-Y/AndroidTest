package com.example.myfirstapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // EditText, Button, TextViewの参照を取得
        val inputText: EditText = findViewById(R.id.inputText)
        val submitButton: Button = findViewById(R.id.submitButton)
        val outputText: TextView = findViewById(R.id.outputText)

        // ボタンのクリックリスナーを設定
        // TODO確認
        submitButton.setOnClickListener {
            // 入力されたテキストを取得
            val userInput = inputText.text.toString()

            // 入力されたテキストをTextViewに表示
            outputText.text = "あなたの入力: $userInput"
        }
    }
}

package com.example.myfirstapp

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.myfirstapp.api.RetrofitInstance
import com.example.myfirstapp.api.AllergyResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var resultTextView: TextView
    private lateinit var idInput: EditText
    private lateinit var searchButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultTextView = findViewById(R.id.resultTextView)
        idInput = findViewById(R.id.idInput)
        searchButton = findViewById(R.id.searchButton)

        searchButton.setOnClickListener {
            val inputText = idInput.text.toString()
            if (inputText.isNotEmpty()) {
                val id = inputText.toInt()
                fetchAllergyInfo(id)
            } else {
                Toast.makeText(this, "アレルギーIDを入力してください", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun fetchAllergyInfo(id: Int) {
        resultTextView.text = "読み込み中..."

        RetrofitInstance.api.getAllergyInfo(id).enqueue(object : Callback<AllergyResponse> {
            override fun onResponse(call: Call<AllergyResponse>, response: Response<AllergyResponse>) {
                if (response.isSuccessful) {
                    val allergyIds = response.body()?.result?.info?.joinToString(", ") { it.allergyId.toString() }
                    resultTextView.text = "Allergy IDs: $allergyIds"
                } else {
                    resultTextView.text = "エラー: ${response.code()}"
                }
            }

            override fun onFailure(call: Call<AllergyResponse>, t: Throwable) {
                resultTextView.text = "通信エラー: ${t.message}"
            }
        })
    }
}

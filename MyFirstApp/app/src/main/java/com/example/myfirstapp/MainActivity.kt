package com.example.myfirstapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myfirstapp.api.RetrofitInstance
import com.example.myfirstapp.api.AllergyResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var resultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // TextViewのIDを取得
        resultTextView = findViewById(R.id.resultTextView)

        // APIリクエストの実行
        RetrofitInstance.api.getAllergyInfo(1).enqueue(object : Callback<AllergyResponse> {
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

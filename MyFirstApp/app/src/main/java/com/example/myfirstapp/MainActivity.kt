package com.example.myfirstapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myfirstapp.api.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val allergyNameMap = mapOf(
        1 to "卵",
        2 to "小麦",
        3 to "そば",
        4 to "牛乳",
        5 to "落花生"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                AllergyScreen()
            }
        }
    }

    @Composable
    fun AllergyScreen() {
        var text by remember { mutableStateOf("読み込み中...") }

        LaunchedEffect(Unit) {
            try {
                val response = RetrofitInstance.api.getRecipeAllergyInfo(recipeId = 1)
                if (response.result.success) {
                    val names = response.result.info.mapNotNull {
                        allergyNameMap[it.allergyId]
                    }
                    text = "アレルギー: ${names.joinToString(", ")}"
                } else {
                    text = "取得失敗"
                }
            } catch (e: Exception) {
                text = "エラー: ${e.message}"
            }
        }

        // UI部分
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(text = text, style = MaterialTheme.typography.bodyLarge)
        }
    }
}

package com.example.myapplication2



import okhttp3.*
import java.io.IOException

class TelegramSenderMessage() {
    private  val botToken = "xxxxxxxxxxxxxxxxxxxxxxxxxxx bot id"
    private val chatId = "4xxxxxxx"
    fun sendMessage(sender: String, text: String) {
        val client = OkHttpClient()

        val all_text ="от: ".plus(sender).plus(" текст: ").plus(text)
        val requestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("chat_id", chatId)
            .addFormDataPart("text", all_text)
            .build()

        val request = Request.Builder()
            .url("https://api.telegram.org/bot$botToken/sendMessage")
            .post(requestBody)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // Обработка ошибок при выполнении запроса
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                // Обработка успешного ответа от сервера Telegram
                response.use {
                    if (!response.isSuccessful) {
                        // Обработка неуспешного ответа
                        println("Error: ${response.code} ${response.message}")
                    }
                }
            }
        })
    }
}
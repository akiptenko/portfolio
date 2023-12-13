package com.example.myapplication2

import android.Manifest
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.Telephony
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.myapplication2.ui.theme.MyApplication2Theme
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.exceptions.TelegramApiException
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession

class MainActivity2 : ComponentActivity() {
    private val SMS_PERMISSION_CODE = 123

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val textView: TextView = findViewById(R.id.editTextName)

        val button: Button = findViewById(R.id.buttonGreet)
        button.setOnClickListener {
            showToast(textView.text)
            textView.text = "Hello Kitty!"
        }

        // Проверить разрешение на чтение SMS
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS)
            != PackageManager.PERMISSION_GRANTED
        ) {
            // Запросить разрешение, если оно не предоставлено
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.RECEIVE_SMS),
                SMS_PERMISSION_CODE
            )
        } else {
            // Разрешение уже предоставлено, регистрировать SmsReceiver
            registerSmsReceiver()
        }
        showToast("SMS to Telegram App started!")
        // Создать экземпляр Telegram-бота
        // createTelegramBot()







        val telegramSender = TelegramSenderMessage()
        telegramSender.sendMessage("AndroidApp", "Hello, Telegram2!")  // Отправка сообщения


        // Приветствие
        showToast("SMS to Telegram App started!")
    }

    private fun registerSmsReceiver() {
        val intentFilter = IntentFilter(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)
        registerReceiver(SmsReceiver(), intentFilter)
    }

    private fun createTelegramBot() {
        try {
            val botsApi = TelegramBotsApi(DefaultBotSession::class.java)
            botsApi.registerBot(YourTelegramBot2())
        } catch (e: TelegramApiException) {
            showToast("Failed to create Telegram Bot: ${e.message}")
            e.printStackTrace()
        }
    }



    private fun showToast(message: CharSequence) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == SMS_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showToast("SMS permission granted. Starting SMS to Telegram App...")
                registerSmsReceiver()
            } else {
                showToast("SMS permission denied. App cannot function without SMS permission.")
            }
        }
    }
}

class YourTelegramBot2 : TelegramLongPollingBot() {
    override fun getBotToken(): String {
        return "xxxxxxxxxxxxxxxxxxxxxxxxxxx bot id"
    }

    override fun getBotUsername(): String {
        return "SMS_Reception_Kip_bot"
    }

    override fun onUpdateReceived(update: Update) {
        // Обработать обновление
    }
}


class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplication2Theme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplication2Theme {
        Greeting("Android")
    }
}



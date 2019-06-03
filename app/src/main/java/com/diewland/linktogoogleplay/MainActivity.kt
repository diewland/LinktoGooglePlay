package com.diewland.linktogoogleplay

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var girlsos_package = "com.diewland.girlpublicbussos"

        var btn_intent = findViewById<Button>(R.id.intent_link)
        var btn_instant = findViewById<Button>(R.id.instant_link)

        // link by intent
        btn_intent.setOnClickListener {
            try {
                val intent = packageManager.getLaunchIntentForPackage(girlsos_package)
                startActivity(intent)
            }
            catch (e: Exception){
                Toast.makeText(applicationContext, "App not found, redirect to Play Store", Toast.LENGTH_LONG).show()

                // jump to play store
                val intent = Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse("https://play.google.com/store/apps/details?id=${girlsos_package}")
                    setPackage("com.android.vending")
                }
                startActivity(intent)
            }
        }

        // link by instant
        btn_instant.setOnClickListener {
            val uriBuilder = Uri.parse("https://play.google.com/store/apps/details")
                .buildUpon()
                .appendQueryParameter("id", girlsos_package)
                .appendQueryParameter("launch", "true")

            // Optional parameters, such as referrer, are passed onto the launched
            // instant app. You can retrieve these parameters using Activity.intent.data.
            uriBuilder.appendQueryParameter("referrer", "exampleCampaignId")

            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = uriBuilder.build()
                setPackage("com.android.vending")
            }
            startActivity(intent)
        }
    }
}

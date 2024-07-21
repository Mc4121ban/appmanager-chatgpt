package com.example.appmanager

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class AppSelectionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activityapp_selection)

        val listView: ListView = findViewById(R.id.listViewApps)
        val appsList = intent.getStringArrayListExtra("APPS_LIST") ?: arrayListOf()
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, appsList)
        listView.adapter = adapter

        listView.setOnItemClickListener { , , position,  ->
            val selectedApp = appsList[position]
            val resultIntent = Intent()
            resultIntent.putExtra("SELECTED_APP", selectedApp)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}
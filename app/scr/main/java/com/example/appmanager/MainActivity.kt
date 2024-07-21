package com.example.appmanager

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val appsList = mutableListOf<String>()
    private lateinit var listView: ListView
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.listViewApps)
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, appsList)
        listView.adapter = adapter

        val btnAddApp: Button = findViewById(R.id.btnAddApp)
        btnAddApp.setOnClickListener {
            showInstalledApps()
        }
    }

    private fun showInstalledApps() {
        val pm: PackageManager = packageManager
        val packages = pm.getInstalledApplications(PackageManager.GET_META_DATA)
        val appNames = packages.map { it.loadLabel(pm).toString() }

        val intent = Intent(this, AppSelectionActivity::class.java)
        intent.putStringArrayListExtra("APPS_LIST", ArrayList(appNames))
        startActivityForResult(intent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK) {
            val selectedApp = data?.getStringExtra("SELECTED_APP")
            if (selectedApp != null) {
                addApp(selectedApp)
            }
        }
    }

    private fun addApp(appName: String) {
        if (!appsList.contains(appName)) {
            appsList.add(appName)
            adapter.notifyDataSetChanged()
        }
    }
}
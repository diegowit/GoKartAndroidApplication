package com.example.gokartandroidapplication.activities

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.gokartandroidapplication.R
import com.example.gokartandroidapplication.models.GoKartModel

/*code reference: Android Studio Developers
* Set up the search UI
*
* link: https://developer.android.com/develop/ui/views/search/training/setup
*
* */
class SearchResultsActivity : AppCompatActivity() {
    var gokarts = mutableListOf<GoKartModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_results)
        handleIntent(intent)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent) {
        if (Intent.ACTION_SEARCH == intent.action) {
            val name = intent.getStringExtra(SearchManager.QUERY)
            Log.d("SEARCH", "Search query was: $name")
            if (name != null) {
                findByName(name)
            }
        }

    }

    private fun findByName(name: String) : GoKartModel? {
        val foundGoKart: GoKartModel? = gokarts.find { it.name == name }
        return foundGoKart
    }
}

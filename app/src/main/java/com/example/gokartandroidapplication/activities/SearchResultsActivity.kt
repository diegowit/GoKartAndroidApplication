package com.example.gokartandroidapplication.activities


import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gokartandroidapplication.adapters.GoKartAdapter
import com.example.gokartandroidapplication.adapters.GoKartListener
import com.example.gokartandroidapplication.adapters.SearchAdapter
import com.example.gokartandroidapplication.adapters.SearchListener
import com.example.gokartandroidapplication.databinding.ActivitySearchResultsBinding
import com.example.gokartandroidapplication.main.MainApp
import com.example.gokartandroidapplication.models.GoKartModel


/*code reference: Android Studio Developers
* Set up the search UI
*
* link: https://developer.android.com/develop/ui/views/search/training/setup
*
* */
class SearchResultsActivity : AppCompatActivity(), SearchListener {
    lateinit var app: MainApp
    private var position: Int = 0
    // View binding variable
    private lateinit var binding: ActivitySearchResultsBinding
    var gokarts = mutableListOf<GoKartModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout using view binding
        binding = ActivitySearchResultsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        app = application as MainApp

        // Set up the RecyclerView with adapter and layout manager
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerViewSearchResults.layoutManager = layoutManager
        binding.recyclerViewSearchResults.adapter = SearchAdapter(app.gokarts.findAll(), this)

        // Set the title of the top app bar
        setSupportActionBar(binding.topAppBar)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleIntent(intent)
    }

    override fun onSearchClick(gokart: GoKartModel, position: Int) {
        // Handle the click event
        Toast.makeText(this, "GoKart clicked: ${gokart.name}", Toast.LENGTH_SHORT).show()
    }

    private fun handleIntent(intent: Intent) {
        if (Intent.ACTION_SEARCH == intent.action) {
            val query = intent.getStringExtra(SearchManager.QUERY)
            // Do something with the query
            Log.d("SearchResultsActivity", "Search query: $query")
        }
    }
}





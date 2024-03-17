package com.example.gokartandroidapplication.activities

import android.app.Activity
import android.content.Intent
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.MenuItem
import com.example.gokartandroidapplication.databinding.ActivityGokartlistBinding
import com.example.gokartandroidapplication.databinding.CardGokartBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.activity.result.contract.ActivityResultContracts
import com.example.gokartandroidapplication.R
import com.example.gokartandroidapplication.main.MainApp
import com.example.gokartandroidapplication.models.GoKartModel
import com.google.android.material.snackbar.Snackbar
import com.example.gokartandroidapplication.adapters.GoKartAdapter
import com.example.gokartandroidapplication.adapters.GoKartListener



class GoKartListActivity : AppCompatActivity(), GoKartListener {

    // Late-initialized variable to hold the MainApp instance
    lateinit var app: MainApp

    // View binding variable
    private lateinit var binding: ActivityGokartlistBinding

    // Called when the activity is created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout using view binding
        binding = ActivityGokartlistBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize the MainApp instance
        app = application as MainApp

        // Set up the RecyclerView with adapter and layout manager
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = GoKartAdapter(app.gokarts.findAll(), this)

        // Set the title of the top app bar
        binding.topAppBar.title = title
        setSupportActionBar(binding.topAppBar)
    }

    // Inflate the menu resource to display in the action bar
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // Handle menu item clicks
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> {
                // Launch GoKartActivity to add a new GoKart
                val launcherIntent = Intent(this, GoKartActivity::class.java)
                getResult.launch(launcherIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    // Activity result launcher to handle the result from GoKartActivity
    private val getResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        // Handle the result when the activity finishes
        if (result.resultCode == Activity.RESULT_OK) {
            // Notify the adapter that data has changed
            (binding.recyclerView.adapter)?.notifyItemRangeChanged(0, app.gokarts.findAll().size)
        }
        if (result.resultCode == Activity.RESULT_CANCELED) {
            // Show a snackbar message if adding a GoKart is cancelled
            Snackbar.make(binding.root, getString(R.string.gokart_add_cancelled), Snackbar.LENGTH_LONG).show()
        }
    }

    // Handle click events on GoKart items in the RecyclerView
    override fun onGoKartClick(gokart: GoKartModel) {
        // Launch GoKartActivity to edit the selected GoKart
        val launcherIntent = Intent(this, GoKartActivity::class.java)
        launcherIntent.putExtra("gokart_edit", gokart)
        getResult.launch(launcherIntent)
    }
}








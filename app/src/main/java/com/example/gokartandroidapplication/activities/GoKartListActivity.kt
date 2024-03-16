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

    lateinit var app: MainApp
    private lateinit var binding: ActivityGokartlistBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGokartlistBinding.inflate(layoutInflater)
        setContentView(binding.root)

        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = GoKartAdapter(app.gokarts.findAll(), this)
        binding.topAppBar.title = title  //Name of the Project
        setSupportActionBar(binding.topAppBar)
}

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> {
                val launcherIntent = Intent(this, GoKartActivity::class.java)
                getResult.launch(launcherIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private val getResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                (binding.recyclerView.adapter)?.
                notifyItemRangeChanged(0,app.gokarts.findAll().size)
            }
            if (it.resultCode == Activity.RESULT_CANCELED) {
                Snackbar.make(binding.root, "Driver Adding process Cancelled", Snackbar.LENGTH_LONG).show()
            }
        }

    override fun onGoKartClick(gokart: GoKartModel) {
        val launcherIntent = Intent(this, GoKartActivity::class.java)
        launcherIntent.putExtra("gokart_edit", gokart)
        getResult.launch(launcherIntent)
    }

}








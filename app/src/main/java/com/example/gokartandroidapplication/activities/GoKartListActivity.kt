package com.example.gokartandroidapplication.activities


import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
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

class GoKartListActivity : AppCompatActivity() {

    lateinit var app: MainApp
    private lateinit var binding: ActivityGokartlistBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGokartlistBinding.inflate(layoutInflater)
        setContentView(binding.root)

        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = GoKartAdapter(app.gokarts)
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
                notifyItemRangeChanged(0,app.gokarts.size)
            }
        }
}



class GoKartAdapter(private var gokarts: ArrayList<GoKartModel>) :
    RecyclerView.Adapter<GoKartAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardGokartBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val gokart = gokarts[holder.adapterPosition]
        holder.bind(gokart)
    }

    override fun getItemCount(): Int = gokarts.size

    class MainHolder(private val binding : CardGokartBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(gokart: GoKartModel) {
            binding.DriverName.text = gokart.name
            binding.CarModel.text = gokart.carModel
        }
    }
}






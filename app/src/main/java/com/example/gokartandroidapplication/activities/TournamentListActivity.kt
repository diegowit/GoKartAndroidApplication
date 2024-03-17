package com.example.gokartandroidapplication.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gokartandroidapplication.adapters.TournamentAdapter
import com.example.gokartandroidapplication.adapters.TournamentListener
import com.example.gokartandroidapplication.databinding.ActivityTournamentlistBinding
import com.example.gokartandroidapplication.main.MainApp
import com.example.gokartandroidapplication.models.TournamentModel
import com.google.android.material.snackbar.Snackbar
import com.example.gokartandroidapplication.R
class TournamentListActivity : AppCompatActivity(), TournamentListener {

    private lateinit var app: MainApp
    private lateinit var binding: ActivityTournamentlistBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTournamentlistBinding.inflate(layoutInflater)
        setContentView(binding.root)

        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = TournamentAdapter(app.tournaments.findAll(), this)
        binding.topAppBar.title = getString(R.string.tournament_title)
        setSupportActionBar(binding.topAppBar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add_tournament -> {
                val launcherIntent = Intent(this, TournamentActivity::class.java)
                getResult.launch(launcherIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private val getResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                (binding.recyclerView.adapter as TournamentAdapter).notifyDataSetChanged()
            }
            if (result.resultCode == Activity.RESULT_CANCELED) {
                Snackbar.make(binding.root, getString(R.string.tournament_add_cancelled), Snackbar.LENGTH_LONG).show()
            }
        }

    override fun onTournamentClick(tournament: TournamentModel) {
        val launcherIntent = Intent(this, TournamentActivity::class.java)
        launcherIntent.putExtra("tournament_edit", tournament)
        getResult.launch(launcherIntent)
    }
}
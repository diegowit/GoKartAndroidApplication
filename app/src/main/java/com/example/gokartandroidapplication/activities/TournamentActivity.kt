package com.example.gokartandroidapplication.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.gokartandroidapplication.R
import com.example.gokartandroidapplication.databinding.ActivityTournamentBinding
import com.example.gokartandroidapplication.helpers.showImagePicker
import com.example.gokartandroidapplication.main.MainApp
import com.example.gokartandroidapplication.models.TournamentModel
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import timber.log.Timber

class TournamentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTournamentBinding
    var tournament = TournamentModel()
    lateinit var app: MainApp


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTournamentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.topAppBar.title = title
        setSupportActionBar(binding.topAppBar)


        app = application as MainApp
        var edit = false

        Timber.i(getString(R.string.tournament_activity_started))


        if (intent.hasExtra("tournament_edit")) {
            edit = true
            tournament = intent.extras?.getParcelable("tournament_edit")!!
            binding.TournamentTitle.setText(tournament.title)
            binding.TournamentDate.setText(tournament.laps)
            binding.TournamentLocation.setText(tournament.laps)
            binding.TournamentLaps.setText(tournament.laps)
            binding.TournamentDescription.setText(tournament.description)
            binding.btnAddTournament.text = getString(R.string.success_tournament_created)

        }



        binding.btnAddTournament.setOnClickListener() {
            tournament.title = binding.TournamentTitle.text.toString()
            tournament.description = binding.TournamentDescription.text.toString()
            tournament.date = binding.TournamentDate.text.toString()
            tournament.laps = binding.TournamentLaps.text.toString()
            tournament.location = binding.TournamentLocation.text.toString()
            if (tournament.title.isNotEmpty()) {
                if (edit) {
                    app.tournaments.update(tournament.copy())
                }
                else{
                    app.tournaments.create(tournament.copy())
                }
                setResult(RESULT_OK)
                finish()
            }
            else {
                Snackbar.make(it, getString(R.string.success_tournament_created),
                    Snackbar.LENGTH_LONG).show()
            }
        }


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_tournament, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_cancel_tournament -> {
                setResult(RESULT_CANCELED)
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }


    }

package com.raman.tictactoy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun btnClick(view: View) {
        val btnSelected = view as Button
        var cellId = 0
        when (view.id) {
            R.id.b1 -> cellId = 1
            R.id.b2 -> cellId = 2
            R.id.b3 -> cellId = 3
            R.id.b4 -> cellId = 4
            R.id.b5 -> cellId = 5
            R.id.b6 -> cellId = 6
            R.id.b7 -> cellId = 7
            R.id.b8 -> cellId = 8
            R.id.b9 -> cellId = 9
        }
        playGame(cellId, btnSelected)
    }

    var activePlayer = 1
    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()

    fun playGame(cellId: Int, btnSelected: Button) {
        if (activePlayer == 1) {
            btnSelected.text = "X"
            btnSelected.setBackgroundColor(ContextCompat.getColor(this, R.color.blue))
            player1.add(cellId)
            activePlayer = 2
            btnSelected.isEnabled = false
            autoPlay()
        } else {
            btnSelected.text = "O"
            btnSelected.setBackgroundColor(ContextCompat.getColor(this, R.color.darkGreen))
            player2.add(cellId)
            activePlayer = 1
            btnSelected.isEnabled = false
        }
        checkWinner()
    }

    fun autoPlay() {
        var emptyCells = ArrayList<Int>()
        for (cellId in 1..9) {
            if (!(player1.contains(cellId) || player2.contains(cellId))) {
                emptyCells.add(cellId)
            }
        }
        val r = Random()
        if (emptyCells.size == 0) {
            restartGame()
            return
        }
        val randomIndex = r.nextInt(emptyCells.size)
        val cellId = emptyCells[randomIndex]
        var btnSelected: Button?
        btnSelected = when (cellId) {
            1 -> b1
            2 -> b2
            3 -> b3
            4 -> b4
            5 -> b5
            6 -> b6
            7 -> b7
            8 -> b8
            9 -> b9
            else -> b1
        }
        playGame(cellId, btnSelected)

    }

    private fun checkWinner() {
        var winner = -1
        if (player1.contains(1) && player1.contains(2) && player1.contains(3)) {
            winner = 1
        } else if (player2.contains(1) && player2.contains(2) && player2.contains(3)) {
            winner = 2
        }
        if (player1.contains(4) && player1.contains(5) && player1.contains(6)) {
            winner = 1
        } else if (player2.contains(4) && player2.contains(5) && player2.contains(6)) {
            winner = 2
        }
        if (player1.contains(7) && player1.contains(8) && player1.contains(9)) {
            winner = 1
        } else if (player2.contains(7) && player2.contains(8) && player2.contains(9)) {
            winner = 2
        }

        if (player1.contains(1) && player1.contains(4) && player1.contains(7)) {
            winner = 1
        } else if (player2.contains(1) && player2.contains(4) && player2.contains(7)) {
            winner = 2
        }
        if (player1.contains(2) && player1.contains(5) && player1.contains(8)) {
            winner = 1
        } else if (player2.contains(2) && player2.contains(5) && player2.contains(8)) {
            winner = 2
        }
        if (player1.contains(3) && player1.contains(6) && player1.contains(9)) {
            winner = 1
        } else if (player2.contains(3) && player2.contains(6) && player2.contains(9)) {
            winner = 2
        }

        if (winner == 1) {
            Log.e("winner"," $player2WinsCount")
            player1WinsCount += 1
            Log.e("winner"," $winner , player 1 : $player1WinsCount")
            Toast.makeText(this, "Player 1 wins the game", Toast.LENGTH_LONG).show()
            restartGame()
        } else if (winner == 2) {
            player2WinsCount += 1
            Log.e("winner"," $winner ")
            Toast.makeText(this, "Player 2 wins the game", Toast.LENGTH_LONG).show()
            restartGame()
        }
    }

    var player1WinsCount = 0
    var player2WinsCount = 0

    fun restartGame() {
        Handler().postDelayed( {
            for (index in 1..9) {
                var btnSelected: Button?
                Log.e("tag index", "$index")
                btnSelected = when (index) {
                    1 -> b1
                    2 -> b2
                    3 -> b3
                    4 -> b4
                    5 -> b5
                    6 -> b6
                    7 -> b7
                    8 -> b8
                    9 -> b9
                    else -> b1
                }
                btnSelected!!.text = ""
                btnSelected!!.setBackgroundResource(R.color.buttonBg)
                btnSelected!!.isEnabled = true
            }
            activePlayer = 1
            player1.clear()
            player2.clear()

            Toast.makeText(
                this,
                "Player1 : $player1WinsCount, Player2 : $player2WinsCount",
                Toast.LENGTH_SHORT
            ).show()
            player1Wins.text = player1WinsCount.toString()
            player2wins.text = player2WinsCount.toString()
        },1000)
    }

}

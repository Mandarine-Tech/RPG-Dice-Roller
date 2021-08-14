package com.example.rpgdiceroller

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

private val diceSize = 6

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.buttonRoll).setOnClickListener { rollDice() }
    }

    private fun rollDice() {
        val diceRoll = Dice(diceSize).roll()
        findViewById<TextView>(R.id.textView).text = diceRoll.toString()
        val drawableResource = when (diceRoll) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        val diceImage = findViewById<ImageView>(R.id.imageView)
        diceImage.setImageResource(drawableResource)
        diceImage.contentDescription = diceRoll.toString()
    }
}

class Dice(private val numSide: Int) {

    fun roll(): Int {
        return (1..numSide).random()
    }
}
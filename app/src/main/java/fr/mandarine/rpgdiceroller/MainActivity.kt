package fr.mandarine.rpgdiceroller

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.graphics.drawable.Drawable
import java.io.IOException
import java.io.InputStream

private var diceSize = 6

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.buttonRoll).setOnClickListener {
            val result: Int = rollDice()
            displayResult(result)
        }
        findViewById<Button>(R.id.buttonD4).setOnClickListener { diceSize = 4 }
        findViewById<Button>(R.id.buttonD6).setOnClickListener { diceSize = 6 }
        findViewById<Button>(R.id.buttonD8).setOnClickListener { diceSize = 8 }
        findViewById<Button>(R.id.buttonD10).setOnClickListener { diceSize = 10 }
        findViewById<Button>(R.id.buttonD12).setOnClickListener { diceSize = 12 }
        findViewById<Button>(R.id.buttonD20).setOnClickListener { diceSize = 20 }
        findViewById<Button>(R.id.buttonD100).setOnClickListener { diceSize = 100 }


        try {
            // get input stream
            val ims: InputStream = assets.open("dice/anis/d$diceSize.png")
            // load image as Drawable
            val d = Drawable.createFromStream(ims, null)
            // set image to ImageView
            findViewById<ImageView>(R.id.imageView).setImageDrawable(d)
            ims.close()
        } catch (ex: IOException) {
            return
        }
    }

    private fun rollDice(): Int {
        val diceRoll = Dice(diceSize).roll()
        findViewById<TextView>(R.id.textView).text = diceRoll.toString()
        return diceRoll
    }

    private fun displayResult(result: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(result.toString())

        //performing positive action
        builder.setPositiveButton("Close"){ _, _ -> }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(true)
        alertDialog.show()
    }
}

class Dice(private val numSide: Int) {

    fun roll(): Int {
        return (1..numSide).random()
    }
}
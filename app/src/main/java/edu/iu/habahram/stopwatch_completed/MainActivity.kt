package edu.iu.habahram.stopwatch_completed

import android.os.Bundle
import android.os.SystemClock
import android.widget.Button
import android.widget.Chronometer
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    lateinit var stopwatch: Chronometer  //The stopwatch
    var running = false  //Is the stopwatch running?
    var offset: Long = 0  //The base offset for the stopwatch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Get a reference to the stopwatch
        stopwatch = findViewById<Chronometer>(R.id.stopwatch)

        //The start button starts the stopwatch if it's not running
        val startButton = findViewById<Button>(R.id.start_button)
        startButton.setOnClickListener {
            if (!running) {
                setBaseTime()
                stopwatch.start()
                running = true
            }
        }

        //The pause button pauses the stopwatch if it’s running
        val pauseButton = findViewById<Button>(R.id.pause_button)
        pauseButton.setOnClickListener {
            if (running) {
                saveOffset()
                stopwatch.stop()
                running = false
            }
        }

        //The reset button sets the offset and stopwatch to 0
        val resetButton = findViewById<Button>(R.id.reset_button)
        resetButton.setOnClickListener {
            offset = 0
            setBaseTime()
        }
    }

    //Update the stopwatch base time, allowing for any offset
    fun setBaseTime() {
        stopwatch.base = SystemClock.elapsedRealtime() - offset
    }

    //Record the offset
    fun saveOffset() {
        offset = SystemClock.elapsedRealtime() - stopwatch.base
    }


}
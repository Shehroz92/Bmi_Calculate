package eu.tutorials.a7_minutesworkoutapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import eu.tutorials.a7_minutesworkoutapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding:ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.flStart?.setOnClickListener {
            val intent = Intent(this,ExerciseActivity::class.java)
            startActivity(intent)
        }

        binding?.BmiText?.setOnClickListener {
            val intent = Intent(this@MainActivity , CalculateBmi::class.java)
            startActivity(intent)
        }
        binding?.historyText?.setOnClickListener {
            val intent = Intent(this@MainActivity , HistoryActivity::class.java)
            startActivity(intent)
        }


    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}
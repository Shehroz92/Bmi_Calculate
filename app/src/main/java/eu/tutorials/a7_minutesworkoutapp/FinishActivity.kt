package eu.tutorials.a7_minutesworkoutapp

import android.icu.text.DateFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import java.text.SimpleDateFormat
import eu.tutorials.a7_minutesworkoutapp.databinding.ActivityExerciseBinding
import eu.tutorials.a7_minutesworkoutapp.databinding.ActivityFinishBinding
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Locale

class FinishActivity : AppCompatActivity() {
    //Todo 1: Create a binding variable
    private var binding: ActivityFinishBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//Todo 2: inflate the layout
        binding = ActivityFinishBinding.inflate(layoutInflater)
//Todo 3: bind the layout to this Activity
        setContentView(binding?.root)
//Todo 4: attach the layout to this activity
        setSupportActionBar(binding?.toolbarFinishActivity)
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding?.toolbarFinishActivity?.setNavigationOnClickListener {
            onBackPressed()
        }
        //END

        //TODO(Step 6 : Adding a click event to the Finish Button.)
        //START
        binding?.btnFinish?.setOnClickListener {
            finish()
        }
        val historyDao =(application as WorkOutApp).db.historyDao()
        addDateToDatabase(historyDao)


    }

    private fun addDateToDatabase(historyDao: HistoryDao){
        val c = Calendar.getInstance()
        val dateTime = c.time
        Log.e("Date: " , "" + dateTime )
        val sdf = SimpleDateFormat("dd MMM yyyy HH:mm:ss" , Locale.getDefault())
        val date = sdf.format(dateTime)
        Log.e("Formatted Date: " , "" + date )

        lifecycleScope.launch {
            historyDao.insert(HistoryEntity(date))
            Log.e(
                "Date: ",
                "Added..."
            )
        }


    }

}
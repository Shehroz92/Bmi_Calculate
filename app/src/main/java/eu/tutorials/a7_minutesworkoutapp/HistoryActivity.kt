package eu.tutorials.a7_minutesworkoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import eu.tutorials.a7_minutesworkoutapp.databinding.ActivityCalculateBmiBinding
import eu.tutorials.a7_minutesworkoutapp.databinding.ActivityHistoryBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HistoryActivity : AppCompatActivity() {

    private var binding : ActivityHistoryBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        if (supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title ="History"
        }
        binding?.toolbarHistoryActivity?.setNavigationOnClickListener { onBackPressed() }
        val historyDao =(application as WorkOutApp).db.historyDao()
        getAllCompletedDates(historyDao)

    }
    private fun getAllCompletedDates(historyDao: HistoryDao){
        lifecycleScope.launch{
            historyDao.fetchAllDate().collect { allCompletedDatesList ->
               if (allCompletedDatesList.isNotEmpty()){

                   binding?.rvHistory?.visibility = View.VISIBLE
                   binding?.exercisetitle?.visibility = View.VISIBLE
                   binding?.noexercisetitle?.visibility =View.INVISIBLE

                   binding?.rvHistory?.layoutManager=  LinearLayoutManager(this@HistoryActivity)
                   val dates = ArrayList<String>()
                   for ( date in allCompletedDatesList){
                       dates.add(date.date)
                   }
                   val historyAdapter = HistoryAdapter(dates)
                   binding?.rvHistory?.adapter = historyAdapter



               }else{
                   binding?.rvHistory?.visibility = View.GONE
                   binding?.exercisetitle?.visibility = View.GONE
                   binding?.noexercisetitle?.visibility =View.VISIBLE
               }

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }


}
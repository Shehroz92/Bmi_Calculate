package eu.tutorials.a7_minutesworkoutapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import eu.tutorials.a7_minutesworkoutapp.databinding.ItemHistoryBinding

class HistoryAdapter(private val items : ArrayList<String>):RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    class ViewHolder(binding : ItemHistoryBinding):RecyclerView.ViewHolder(binding.root){
        val llHistoryItemMain = binding.llHistoryItemMain
        val tvItem = binding.tvitem
        val tvPosition = binding.tvPosition

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return  ViewHolder(ItemHistoryBinding.inflate(
           LayoutInflater.from(parent.context),
           parent,
           false

       ))
    }

    override fun getItemCount(): Int {
       return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val date :String = items.get(position)
        holder.tvPosition.text = {position +1}.toString()
        holder.tvItem.text = date

        if (position % 2 == 0 ){
            holder.llHistoryItemMain.setBackgroundColor(
                ContextCompat.getColor( holder.itemView.context,
                R.color.teal_700
            ))

        }else{
            holder.llHistoryItemMain.setBackgroundColor(
                ContextCompat.getColor( holder.itemView.context,
                R.color.white
            ))
        }



    }


}
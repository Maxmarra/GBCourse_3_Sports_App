package com.example.android.sports

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.leanback.widget.DiffCallback
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.android.sports.databinding.SportsListItemBinding
import com.example.android.sports.model.Sport

class SportsAdapter(
    private val shortClickListener: ShortListener,
    private val longClickListener: LongListener,
) :
    ListAdapter<Sport, SportsAdapter.SportsViewHolder>(DiffCallback) {
    //private lateinit var context: Context
    class SportsViewHolder(private var binding: SportsListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            shortClickListener: ShortListener,
            longClickListener: LongListener,
            sport: Sport,
        ) {
            binding.sport = sport
            binding.longClickListner = longClickListener
            binding.shortClickListner = shortClickListener
            binding.executePendingBindings()
        }
//            binding.title.text = context.getString(sport.titleResourceId)
//            binding.subTitle.text = context.getString(sport.subTitleResourceId)
//            // Load the images into the ImageView using the Coil library.
//            binding.sportsImage.load(sport.imageResourceId)
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SportsViewHolder {
        //context = parent.context
        return SportsViewHolder(
            SportsListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: SportsViewHolder, position: Int) {
        val sport = getItem(position)

        holder.bind(shortClickListener, longClickListener, sport)
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Sport>() {
            override fun areItemsTheSame(oldItem: Sport, newItem: Sport): Boolean {
                return (oldItem.id == newItem.id ||
                        oldItem.titleResourceId == newItem.titleResourceId ||
                        oldItem.subTitleResourceId == newItem.subTitleResourceId
                        )
            }

            override fun areContentsTheSame(oldItem: Sport, newItem: Sport): Boolean {
                return oldItem == newItem
            }
        }
    }
}

class LongListener(val longClickListener: (sport: Sport) -> Boolean) {
    fun onLongClick(sport: Sport) = longClickListener(sport)
}

class ShortListener(val shortClickListener: (sport: Sport) -> Unit) {
    fun onShortClick(sport: Sport) = shortClickListener(sport)
}


























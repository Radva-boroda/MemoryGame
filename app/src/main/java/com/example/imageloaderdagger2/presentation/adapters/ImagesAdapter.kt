package dev.ronnie.imageloaderdagger2.presentation.adapters
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dev.ronnie.imageloaderdagger2.data.model.ImagesResponse
import dev.ronnie.imageloaderdagger2.databinding.ImageItemBinding

class ImagesAdapter: PagingDataAdapter<ImagesResponse, ImagesAdapter.ViewHolder>(DiffCallback()){

    var itemClickListener: ((ImagesResponse) -> (Unit))? = null

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {
            Log.i("ViewHolder", "Position")
            holder.bind(it, position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.i("CreateVH", "context")
        return ViewHolder(
            ImageItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), itemClickListener
        )
    }

    inner class ViewHolder(
        private val binding: ImageItemBinding,
        var itemClickListener: ((ImagesResponse) -> (Unit))? = null
    ) : RecyclerView.ViewHolder(binding.root) {
        private var imagesResponse: ImagesResponse? = null
        fun bind(imagesResponse: ImagesResponse, position: Int) {
            Log.i("images", "transfer")
            this.imagesResponse = imagesResponse
            binding.apply {
                itemView.setOnClickListener {
                    itemClickListener?.invoke(imagesResponse)
                }
                image = imagesResponse
                executePendingBindings()

            }
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<ImagesResponse>() {
        override fun areItemsTheSame(oldItem: ImagesResponse, newItem: ImagesResponse): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ImagesResponse, newItem: ImagesResponse): Boolean {
            return oldItem == newItem
        }
    }
}

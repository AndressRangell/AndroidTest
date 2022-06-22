package andres.rangel.androidtest.adapters

import andres.rangel.androidtest.R
import andres.rangel.androidtest.data.local.ShoppingItem
import andres.rangel.androidtest.databinding.ItemImageBinding
import andres.rangel.androidtest.databinding.ItemShoppingBinding
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import javax.inject.Inject

class ShoppingItemAdapter @Inject constructor(
    val glide: RequestManager
) : RecyclerView.Adapter<ShoppingItemAdapter.ShoppingItemViewHolder>() {

    class ShoppingItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private lateinit var binding: ItemShoppingBinding

    private val diffCallback = object : DiffUtil.ItemCallback<ShoppingItem>() {
        override fun areItemsTheSame(oldItem: ShoppingItem, newItem: ShoppingItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ShoppingItem, newItem: ShoppingItem): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var shoppingItems: List<ShoppingItem>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingItemViewHolder {
        return ShoppingItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_shopping, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ShoppingItemViewHolder, position: Int) {
        binding = ItemShoppingBinding.bind(holder.itemView)
        val shoppingItem = shoppingItems[position]
        holder.itemView.apply {
            glide.load(shoppingItem.imageUrl).into(binding.ivShoppingImage)

            binding.tvName.text = shoppingItem.name
            val amountText = "${shoppingItem.amount}x"
            binding.tvShoppingItemAmount.text = amountText
            val priceText = "${shoppingItem.price}€"
            binding.tvShoppingItemPrice.text = priceText
        }
    }

    override fun getItemCount() = shoppingItems.size

}
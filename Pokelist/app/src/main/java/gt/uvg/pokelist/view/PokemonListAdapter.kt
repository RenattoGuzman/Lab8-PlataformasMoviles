package gt.uvg.pokelist.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import gt.uvg.pokelist.R
import gt.uvg.pokelist.databinding.ItemPokemonViewBinding
import gt.uvg.pokelist.model.Pokemon

class PokemonListAdapter(private val pokemonList: List<Pokemon>) : RecyclerView.Adapter<PokemonListAdapter.PokemonListHolder>() {

    inner class PokemonListHolder(val binding: ItemPokemonViewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonListHolder {
        val binding = ItemPokemonViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonListHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonListHolder, position: Int) {
        val pokemon = pokemonList[position]
        pokemon.id = position + 1
        holder.binding.pokemonName.text = pokemon.name
        Picasso.get().load(pokemon.imageUrlFront).into(holder.binding.pokemonPhoto)
        holder.itemView.setOnClickListener {
            val bundle = bundleOf("pokemonId" to pokemon.id)
            holder.itemView.findNavController().navigate(R.id.action_mainFragment_to_detailFragment, bundle)
        }
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }
}
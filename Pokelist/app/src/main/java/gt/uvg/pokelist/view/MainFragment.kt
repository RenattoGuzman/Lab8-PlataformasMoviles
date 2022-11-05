package gt.uvg.pokelist.view


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import gt.uvg.pokelist.R
import gt.uvg.pokelist.model.PokemonResponse
import gt.uvg.pokelist.repository.API
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainFragment: Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_main, container,false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar2)

        this.progressBar=progressBar
        recyclerView.visibility=View.GONE
        recyclerView.layoutManager = LinearLayoutManager(activity)
        this.recyclerView = recyclerView
        PokeGet(requireContext())
        recyclerView.setHasFixedSize(true)


        return view
    }
    fun PokeGet(context: Context){
        API.retrofitService.getFirst100Pokemon().enqueue(object : Callback<PokemonResponse> {
            override fun onResponse(
                call: Call<PokemonResponse>,
                response: Response<PokemonResponse>
            ) {
                Toast.makeText(context,"FETCHED: " + response.body()!!.count, Toast.LENGTH_LONG).show()
                recyclerView.adapter=PokemonListAdapter(response.body()!!.results)
                progressBar.visibility=View.GONE
                recyclerView.visibility=View.VISIBLE
            }
            override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
                Toast.makeText(context, "ERROR", Toast.LENGTH_LONG).show()
            }
        })}

}
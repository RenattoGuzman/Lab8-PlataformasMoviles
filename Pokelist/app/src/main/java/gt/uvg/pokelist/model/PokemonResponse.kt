package gt.uvg.pokelist.model

class PokemonResponse (
    val count:Int,
    val next:String,
    val previous:String?,
    val results:List<Pokemon>
)
package com.example.menudishes

import com.example.menudishes.ui.theme.MenuDishesTheme
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import java.text.Normalizer

class MainActivity : androidx.activity.ComponentActivity() {
    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MenuDishesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DishListWithSearch()
                }
            }
        }
    }
}

@Composable
fun DishListWithSearch() {
    var searchQuery by remember { mutableStateOf("") }
    val filteredDishes = remember(searchQuery) {
        if (searchQuery.isEmpty()) {
            DishesData.dishes
        } else {
            val searchTerms = searchQuery.normalize().split(" ").filter { it.isNotEmpty() }
            DishesData.dishes.filter { dish ->
                searchTerms.all { term ->
                    dish.name.normalize().contains(term) ||
                            dish.description.normalize().contains(term) ||
                            dish.ingredients.any { it.normalize().contains(term) }
                }
            }
        }
    }

    Column {
        SearchBar(
            query = searchQuery,
            onQueryChange = { searchQuery = it }
        )
        DishList(dishes = filteredDishes)
    }
}

@Composable
fun SearchBar(query: String, onQueryChange: (String) -> Unit) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        placeholder = { Text("Pesquisar pratos...") },
        singleLine = true
    )
}

// Função auxiliar para normalizar strings
fun String.normalize(): String {
    return Normalizer.normalize(this.lowercase(), Normalizer.Form.NFD)
        .replace("\\p{InCombiningDiacriticalMarks}+".toRegex(), "")
}

@Composable
fun DishList(dishes: List<Dish>) {
    LazyColumn {
        items(dishes) { dish ->
            DishItem(dish)
        }
    }
}

@Composable
fun DishItem(dish: Dish) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Column {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = dish.name, style = MaterialTheme.typography.headlineLarge)
                Text(text = "\"" + dish.description + "\"", style = MaterialTheme.typography.headlineSmall)
            }
            LazyRow {
                items(dish.images) { image ->
                    Image(
                        painter = painterResource(id = imageResourceId(image)),
                        contentDescription = dish.name,
                        modifier = Modifier
                            .height(225.dp)
                            .width(375.dp),
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.Center
                    )
                }
            }
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = dish.ingredients.joinToString(", "), style = MaterialTheme.typography.headlineSmall)
            }
        }
    }
}

// Função auxiliar para obter o ID do recurso de imagem
@Composable
fun imageResourceId(imageName: String): Int {
    return when (imageName) {
        "drawable/veprowknedlowzelo" -> R.drawable.veprowknedlowzelo
        "drawable/svickovawnawsmetane" -> R.drawable.svickovawnawsmetane
        "drawable/goulashwtcheco" -> R.drawable.goulashwtcheco
        "drawable/insiderwpragawqueijowfritowfriedwcheese" -> R.drawable.insiderwpragawqueijowfritowfriedwcheese
        "drawable/gdaiwsuperapetitewbwbawajwcewjpg" -> R.drawable.gdaiwsuperapetitewbwbawajwcewjpg
        "drawable/boruvkovewknedliky" -> R.drawable.boruvkovewknedliky
        "drawable/rizekwchecowbdadigceb" -> R.drawable.rizekwchecowbdadigceb
        "drawable/ccaedaaaacdjbbifajeagigebdjjieee" -> R.drawable.ccaedaaaacdjbbifajeagigebdjjieee
        "drawable/koleno" -> R.drawable.koleno
        "drawable/svickovawomackawnawsmetanewcbdjbhwghhwfaiwnw" -> R.drawable.svickovawomackawnawsmetanewcbdjbhwghhwfaiwnw
        "drawable/ccfgegbcajagbfecbagdjbgdbgebhabewfacebook" -> R.drawable.ccfgegbcajagbfecbagdjbgdbgebhabewfacebook
        "drawable/faabbffdchfceebjibggifjbccjbieed" -> R.drawable.faabbffdchfceebjibggifjbccjbieed
        "drawable/dgawfwdbddbjejbwonymgfzskjymrbunqqbgzsinxibwaqjp" -> R.drawable.dgawfwdbddbjejbwonymgfzskjymrbunqqbgzsinxibwaqjp
        "drawable/kulajda" -> R.drawable.kulajda
        "drawable/ffebjfeecfiiibdcheefjhdiefdafjba" -> R.drawable.ffebjfeecfiiibdcheefjhdiefdafjba
        "drawable/hdijwfhet" -> R.drawable.hdijwfhet
        "drawable/tatarak" -> R.drawable.tatarak
        "drawable/ovocnewknedlikywdiddwreg" -> R.drawable.ovocnewknedlikywdiddwreg
        "drawable/cacaafcgwbhbgfcb" -> R.drawable.cacaafcgwbhbgfcb
        "drawable/hospodskywspanelskywptacekwpodle" -> R.drawable.hospodskywspanelskywptacekwpodle
        "drawable/jveigecffwdfdebwbacgjifjj" -> R.drawable.jveigecffwdfdebwbacgjifjj
        "drawable/jveigedebwcfaabwbabccjdee" -> R.drawable.jveigedebwcfaabwbabccjdee
        "drawable/svickovawnawsmetanec" -> R.drawable.svickovawnawsmetanec
        "drawable/bryndzovewhaluskywsowslaninou" -> R.drawable.bryndzovewhaluskywsowslaninou
        "drawable/hdijwfhetw" -> R.drawable.hdijwfhetw
        "drawable/bwhciwcdwbeautyshotwbbdcxgdhwburtywnawpivuwzwkotliku" -> R.drawable.bwhciwcdwbeautyshotwbbdcxgdhwburtywnawpivuwzwkotliku
        "drawable/topinkywwithwgarlicwhighwres" -> R.drawable.topinkywwithwgarlicwhighwres
        "drawable/imagewbwbacexfhg" -> R.drawable.imagewbwbacexfhg
        "drawable/dcdfadaifhfdcjdcfggaffdfeccbeddc" -> R.drawable.dcdfadaifhfdcjdcfggaffdfeccbeddc
        "drawable/efabbbcawbbjjwehfbwbaagwcbchfeccijab" -> R.drawable.efabbbcawbbjjwehfbwbaagwcbchfeccijab
        "drawable/veprovewvypeckywiiwcejjhgwghhwfaiwnw" -> R.drawable.veprovewvypeckywiiwcejjhgwghhwfaiwnw
        "drawable/maxresdefault" -> R.drawable.maxresdefault
        "drawable/gfebbicjafccaababafeiadceaddccbcwfacebook" -> R.drawable.gfebbicjafccaababafeiadceaddccbcwfacebook
        "drawable/escalopeswdewvitelawpanados" -> R.drawable.escalopeswdewvitelawpanados
        "drawable/pecccicenaccibwkachnawknedlickywandwredwcabbagewb" -> R.drawable.pecccicenaccibwkachnawknedlickywandwredwcabbagewb
        "drawable/czechwfoodwculturewcouve" -> R.drawable.czechwfoodwculturewcouve
        "drawable/marynowanywhermelin" -> R.drawable.marynowanywhermelin
        "drawable/sparewribswhebajbewbcia" -> R.drawable.sparewribswhebajbewbcia
        "drawable/marinovanawawpecenawveprovawzebirkawshutterstockwhdffbfhae" -> R.drawable.marinovanawawpecenawveprovawzebirkawshutterstockwhdffbfhae
        "drawable/difedjigwchfdwefbewiccfwajjefbfbehdc" -> R.drawable.difedjigwchfdwefbewiccfwajjefbfbehdc
        else -> R.drawable.placeholder
    }
}

// model/Dish.kt
data class Dish(
    val name: String,
    val description: String,
    val ingredients: List<String>, // Adiciona uma lista de ingredientes
    val images: List<String> // Lista de URLs ou paths de imagens para o prato
)

object DishesData {
    val dishes = listOf(
        Dish(
            name = "Vepřo knedlo zelo",
            description = "Prato nacional checo composto por carne de porco assada, knedlíky e couve fermentada.",
            ingredients = listOf("Carne de porco", "Knedlíky", "Couve fermentada", "Cebola", "Alho", "Cominho"),
            images = listOf("drawable/veprowknedlowzelo")
        ),
        Dish(
            name = "Svíčková na smetaně",
            description = "Lombo de vaca em molho de natas com cenoura, servido com knedlíky.",
            ingredients = listOf("Lombo de vaca", "Natas", "Cenoura", "Aipo", "Salsa", "Knedlíky"),
            images = listOf("drawable/svickovawnawsmetane")
        ),
        Dish(
            name = "Guláš",
            description = "Ensopado de carne picante, servido com knedlíky ou pão.",
            ingredients = listOf("Carne de vaca", "Cebola", "Pimentão", "Alho", "Páprica", "Cominho"),
            images = listOf("drawable/goulashwtcheco")
        ),
        Dish(
            name = "Smažený sýr",
            description = "Queijo frito, geralmente servido com batatas fritas e molho tártaro.",
            ingredients = listOf("Queijo Edam", "Ovo", "Farinha", "Pão ralado", "Óleo para fritar"),
            images = listOf("drawable/insiderwpragawqueijowfritowfriedwcheese")
        ),
        Dish(
            name = "Bramboráky",
            description = "Panquecas de batata fritas, temperadas com alho e manjerona.",
            ingredients = listOf("Batata ralada", "Farinha", "Ovo", "Alho", "Manjerona", "Sal"),
            images = listOf("drawable/gdaiwsuperapetitewbwbawajwcewjpg")
        ),
        Dish(
            name = "Knedlíky",
            description = "Bolinhos de pão cozidos a vapor, servidos como acompanhamento.",
            ingredients = listOf("Farinha", "Leite", "Ovo", "Fermento", "Sal"),
            images = listOf("drawable/boruvkovewknedliky")
        ),
        Dish(
            name = "Řízek",
            description = "Bife panado, geralmente de porco ou frango, similar ao schnitzel austríaco.",
            ingredients = listOf("Carne de porco ou frango", "Ovo", "Farinha", "Pão ralado", "Óleo para fritar"),
            images = listOf("drawable/rizekwchecowbdadigceb")
        ),
        Dish(
            name = "Smažený kapr",
            description = "Carpa frita, prato tradicional de Natal na República Checa.",
            ingredients = listOf("Carpa", "Farinha", "Ovo", "Pão ralado", "Limão", "Sal"),
            images = listOf("drawable/ccaedaaaacdjbbifajeagigebdjjieee")
        ),
        Dish(
            name = "Koleno",
            description = "Joelho de porco assado, servido com rábano picante e mostarda.",
            ingredients = listOf("Joelho de porco", "Alho", "Cominho", "Cerveja", "Rábano picante", "Mostarda"),
            images = listOf("drawable/koleno")
        ),
        Dish(
            name = "Svíčková omáčka",
            description = "Molho cremoso de legumes para acompanhar carne assada.",
            ingredients = listOf("Cenoura", "Aipo", "Salsa", "Natas", "Especiarias", "Sumo de limão"),
            images = listOf("drawable/svickovawomackawnawsmetanewcbdjbhwghhwfaiwnw")
        ),
        Dish(
            name = "Houskový knedlík",
            description = "Knedlíky feitos com pão, servidos como acompanhamento.",
            ingredients = listOf("Pão seco", "Leite", "Ovo", "Fermento", "Sal"),
            images = listOf("drawable/ccfgegbcajagbfecbagdjbgdbgebhabewfacebook")
        ),
        Dish(
            name = "Zelňačka",
            description = "Sopa de couve fermentada com batata e linguiça.",
            ingredients = listOf("Couve fermentada", "Batata", "Linguiça", "Cebola", "Natas", "Páprica"),
            images = listOf("drawable/faabbffdchfceebjibggifjbccjbieed")
        ),
        Dish(
            name = "Česnečka",
            description = "Sopa de alho cremosa, por vezes servida com croutons e queijo.",
            ingredients = listOf("Alho", "Batata", "Caldo de carne", "Ovo", "Croutons", "Queijo"),
            images = listOf("drawable/dgawfwdbddbjejbwonymgfzskjymrbunqqbgzsinxibwaqjp")
        ),
        Dish(
            name = "Kulajda",
            description = "Sopa cremosa com cogumelos, batata e ovo escalfado.",
            ingredients = listOf("Cogumelos", "Batata", "Natas", "Endro", "Ovo", "Vinagre"),
            images = listOf("drawable/kulajda")
        ),
        Dish(
            name = "Sekaná",
            description = "Rolo de carne picada assada, similar ao rolo de carne.",
            ingredients = listOf("Carne picada", "Cebola", "Alho", "Pão ralado", "Ovo", "Especiarias"),
            images = listOf("drawable/ffebjfeecfiiibdcheefjhdiefdafjba")
        ),
        Dish(
            name = "Utopenci",
            description = "Salsichas marinadas em vinagre com cebola e pimentão.",
            ingredients = listOf("Salsichas", "Cebola", "Pimentão", "Vinagre", "Pimenta", "Louro"),
            images = listOf("drawable/hdijwfhet")
        ),
        Dish(
            name = "Tatarák",
            description = "Carne de vaca crua picada, temperada e servida com torradas.",
            ingredients = listOf("Carne de vaca crua", "Cebola", "Ovo de codorniz", "Mostarda", "Molho inglês", "Páprica"),
            images = listOf("drawable/tatarak")
        ),
        Dish(
            name = "Ovocné knedlíky",
            description = "Bolinhos doces recheados com fruta, polvilhados com açúcar e manteiga.",
            ingredients = listOf("Massa de batata", "Fruta (ameixa ou morango)", "Açúcar em pó", "Manteiga derretida"),
            images = listOf("drawable/ovocnewknedlikywdiddwreg")
        ),
        Dish(
            name = "Koprová omáčka",
            description = "Molho cremoso de endro, servido com ovos cozidos e knedlíky.",
            ingredients = listOf("Endro", "Natas", "Farinha", "Caldo de legumes", "Ovos cozidos", "Knedlíky"),
            images = listOf("drawable/cacaafcgwbhbgfcb")
        ),
        Dish(
            name = "Španělský ptáček",
            description = "Rolo de carne de vaca recheado com ovo, salsicha e picles.",
            ingredients = listOf("Carne de vaca", "Ovo cozido", "Salsicha", "Picles", "Bacon", "Mostarda"),
            images = listOf("drawable/hospodskywspanelskywptacekwpodle")
        ),
        Dish(
            name = "Cmunda",
            description = "Panqueca de batata frita com carne de porco assada por cima.",
            ingredients = listOf("Batata ralada", "Carne de porco", "Cebola", "Alho", "Manjerona", "Banha"),
            images = listOf("drawable/jveigecffwdfdebwbacgjifjj")
        ),
        Dish(
            name = "Moravský vrabec",
            description = "Pedaços de carne de porco assados com alho e cominho.",
            ingredients = listOf("Carne de porco", "Alho", "Cominho", "Cebola", "Banha", "Sal"),
            images = listOf("drawable/jveigedebwcfaabwbabccjdee")
        ),
        Dish(
            name = "Svíčková na smetaně",
            description = "Lombo de vaca em molho cremoso de legumes, servido com knedlíky.",
            ingredients = listOf("Lombo de vaca", "Cenoura", "Aipo", "Salsa", "Natas", "Knedlíky"),
            images = listOf("drawable/svickovawnawsmetanec")
        ),
        Dish(
            name = "Halušky",
            description = "Pequenos nhoques de batata, muitas vezes servidos com queijo e bacon.",
            ingredients = listOf("Batata", "Farinha", "Queijo", "Bacon", "Cebola", "Natas"),
            images = listOf("drawable/bryndzovewhaluskywsowslaninou")
        ),
        Dish(
            name = "Pivní sýr",
            description = "Queijo marinado em cerveja e especiarias, servido como aperitivo.",
            ingredients = listOf("Queijo Olomoucké tvarůžky", "Cerveja", "Cebola", "Páprica", "Cominho", "Pimenta"),
            images = listOf("drawable/hdijwfhetw")
        ),
        Dish(
            name = "Buřty na pivu",
            description = "Salsichas cozidas em cerveja com cebola e pimentão.",
            ingredients = listOf("Salsichas", "Cerveja", "Cebola", "Pimentão", "Pimenta", "Mostarda"),
            images = listOf("drawable/bwhciwcdwbeautyshotwbbdcxgdhwburtywnawpivuwzwkotliku")
        ),
        Dish(
            name = "Topinky",
            description = "Fatias de pão fritas em gordura, esfregadas com alho.",
            ingredients = listOf("Pão de centeio", "Banha", "Alho", "Sal"),
            images = listOf("drawable/topinkywwithwgarlicwhighwres")
        ),
        Dish(
            name = "Uzené",
            description = "Carne de porco fumada, geralmente servida com knedlíky e espinafres.",
            ingredients = listOf("Carne de porco fumada", "Knedlíky", "Espinafres", "Alho"),
            images = listOf("drawable/imagewbwbacexfhg")
        ),
        Dish(
            name = "Bramborový salát",
            description = "Salada de batata, prato tradicional de Natal.",
            ingredients = listOf("Batata", "Cenoura", "Ervilhas", "Cebola", "Maionese", "Picles"),
            images = listOf("drawable/dcdfadaifhfdcjdcfggaffdfeccbeddc")
        ),
        Dish(
            name = "Zabijačkový guláš",
            description = "Gulache feito com miudezas de porco, servido durante a matança do porco.",
            ingredients = listOf("Miudezas de porco", "Cebola", "Alho", "Páprica", "Pimentão", "Cominho"),
            images = listOf("drawable/efabbbcawbbjjwehfbwbaagwcbchfeccijab")
        ),
        Dish(
            name = "Vepřové výpečky",
            description = "Pedaços de carne de porco assados até ficarem crocantes.",
            ingredients = listOf("Carne de porco", "Alho", "Cominho", "Cebola", "Cerveja", "Sal"),
            images = listOf("drawable/veprovewvypeckywiiwcejjhgwghhwfaiwnw")
        ),
        Dish(
            name = "Hovězí na česneku",
            description = "Carne de vaca cozida lentamente com muito alho.",
            ingredients = listOf("Carne de vaca", "Alho", "Cebola", "Pimentão", "Tomate", "Especiarias"),
            images = listOf("drawable/maxresdefault")
        ),
        Dish(
            name = "Drštková polévka",
            description = "Sopa de tripas picante, popular como cura para a ressaca.",
            ingredients = listOf("Tripas", "Batata", "Cebola", "Alho", "Páprica", "Manjerona"),
            images = listOf("drawable/gfebbicjafccaababafeiadceaddccbcwfacebook")
        ),
        Dish(
            name = "Telecí řízek",
            description = "Escalope de vitela panado, servido com salada de batata.",
            ingredients = listOf("Vitela", "Ovo", "Farinha", "Pão ralado", "Óleo", "Limão"),
            images = listOf("drawable/escalopeswdewvitelawpanados")
        ),
        Dish(
            name = "Pečená kachna",
            description = "Pato assado, geralmente servido com knedlíky e couve roxa.",
            ingredients = listOf("Pato", "Maçã", "Cebola", "Alho", "Manjerona", "Cominho"),
            images = listOf("drawable/pecccicenaccibwkachnawknedlickywandwredwcabbagewb")
        ),
        Dish(
            name = "Vepřová pečeně",
            description = "Carne de porco assada, geralmente servida com knedlíky e couve fermentada.",
            ingredients = listOf("Lombo de porco", "Alho", "Cominho", "Sal", "Pimenta", "Cebola"),
            images = listOf("drawable/czechwfoodwculturewcouve")
        ),
        Dish(
            name = "Nakládaný hermelín",
            description = "Queijo Camembert marinado em azeite com especiarias e pimentos, servido como aperitivo.",
            ingredients = listOf("Queijo Camembert", "Azeite", "Cebola", "Pimentão", "Alho", "Pimenta em grão"),
            images = listOf("drawable/marynowanywhermelin")
        ),
        Dish(
            name = "Marinovaná vepřová",
            description = "Carne de porco marinada em especiarias e ervas, depois grelhada ou assada.",
            ingredients = listOf("Carne de porco", "Alho", "Cebola", "Páprica", "Cominho", "Tomilho"),
            images = listOf("drawable/sparewribswhebajbewbcia")
        ),
        Dish(
            name = "Vepřová žebra",
            description = "Costeletas de porco assadas, muitas vezes marinadas em cerveja e especiarias.",
            ingredients = listOf("Costeletas de porco", "Cerveja", "Mel", "Alho", "Páprica", "Mostarda"),
            images = listOf("drawable/marinovanawawpecenawveprovawzebirkawshutterstockwhdffbfhae")
        ),
        Dish(
            name = "Domácí jablečný štrúdl",
            description = "Strudel de maçã caseiro, uma sobremesa popular feita com massa folhada e recheio de maçã.",
            ingredients = listOf("Massa folhada", "Maçãs", "Açúcar", "Canela", "Passas", "Nozes"),
            images = listOf("drawable/difedjigwchfdwefbewiccfwajjefbfbehdc")
        )
    ).sortedBy { it.name }
}
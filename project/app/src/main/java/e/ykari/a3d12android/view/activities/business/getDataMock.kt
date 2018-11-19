package e.ykari.a3d12android.view.activities.business

import android.app.Activity
import e.ykari.a3d12android.view.activities.data.manageData
import e.ykari.a3d12android.view.activities.data.model.Basket
import e.ykari.a3d12android.view.activities.data.model.Ingredient

class getDataMock(activity: Activity) : businessManagerInterface {

    override fun getRecipes(ingredients: ArrayList<Ingredient>): List<String> {

        val list = ArrayList<String>()

        //Omelette
        if( ingredients.contains(Ingredient("oeuf"))
        ){
            list.add("Omelette")
        }

        //Quiche
        if( ingredients.contains(Ingredient("oeuf")) &&
                ingredients.contains(Ingredient("jambon")) &&
                ingredients.contains(Ingredient("fromage")) &&
                ingredients.contains(Ingredient("creme fraiche")) &&
                ingredients.contains(Ingredient("pate"))
        ){

            list.add("Quiche Lorraine")

        }

        //Croque Mr
        if( ingredients.contains(Ingredient("pain")) &&
                ingredients.contains(Ingredient("jambon")) &&
                ingredients.contains(Ingredient("fromage"))
        ){

            list.add("Croque monsieur")

        }



        return list
    }

    val mActivity : Activity = activity

    override fun getBasket() : Basket {
        //get value from sharedPreference
        return manageData(mActivity).getBasket()
    }

    override fun addIngredient(ingredient: Ingredient) {

        val basket = manageData(mActivity).getBasket()
        basket.ingredients.add(ingredient)
        manageData(mActivity).saveBasket(basket)

    }

    override fun getBasketSize(): Int {
        return manageData(mActivity).getBasket().ingredients.size
    }

}
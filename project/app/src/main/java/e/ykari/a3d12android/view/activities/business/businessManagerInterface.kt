package e.ykari.a3d12android.view.activities.business

import e.ykari.a3d12android.view.activities.data.model.Basket
import e.ykari.a3d12android.view.activities.data.model.Ingredient


interface businessManagerInterface {

    fun  getBasket() : Basket
    fun addIngredient (ingredient: Ingredient)
    fun getBasketSize() : Int

    fun getRecipes(ingredients: ArrayList<Ingredient>): List<String>



}
package e.ykari.a3d12android.view.activities.data

import e.ykari.a3d12android.view.activities.data.model.Basket


interface getDataInterface {

    fun getBasket() : Basket

    fun saveBasket(basket : Basket)

}
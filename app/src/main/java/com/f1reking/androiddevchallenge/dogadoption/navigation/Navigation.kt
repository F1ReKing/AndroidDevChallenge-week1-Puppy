package com.f1reking.androiddevchallenge.dogadoption.navigation

object Navigation {

    const val NAV_LIST_SCREEN = "list_screen"
    const val NAV_ID = "nav_id"
    const val NAV_DETAILS_SCREEN = "details_screen/{$NAV_ID}"

    fun detailRoute(id: Int) = "details_screen/$id"


}
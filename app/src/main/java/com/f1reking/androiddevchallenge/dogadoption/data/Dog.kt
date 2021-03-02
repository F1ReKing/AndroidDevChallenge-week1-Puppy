package com.f1reking.androiddevchallenge.dogadoption.data

data class Dog(
    var id: Int,
    var name: String,
    var age: String,
    var gender: Int, //0:Male 1:Female
    var imgUrl: String,
    var description: String
)
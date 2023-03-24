package com.example.listingcar.util

import com.example.listingcar.dao.Car

class DataCars {

    private var cars = mutableListOf<Car>()

    init {
        cars = (0..5).map { Car(
            mark = mark[it],
            type_car = typeCar[it],
            horsePower = horsepower[it],
            color = color[it],
            image = Images[it]
        ) }.toMutableList()
    }

    fun getCars(): List<Car> {
        return cars
    }
    companion object {
        private val mark = mutableListOf(
            "Mitsubishi lancer evolution",
            "Audi A8 III",
            "Honda Civic IX",
            "Kia Cerato IV",
            "Volkswagen Tiguan I",
            "BMW M5 V"

        )
        private val Images = mutableListOf(
            "https://yastatic.net/naydex/autoru/1ukUi3778/4205ef1G/0N7taNo3l9visKjMWq2fvzfGYDQYyuJq7F5WKtPBwxQQCWeBjPXwqwzXEcnu3lGq3elnqvRN-wUFdz48iTkM1zDaDSnfAyP_J0X5nY6t66pisuHVfQ6jmn_84OjQs3EWE8_Ec53QVM79Y0W_mB6krAkswihRGFyA4SEICP-ZC2Ej_I-LqMfWX0FyCGhoLN1uN-UmJAsJJAvcX0HqsCITp-AfNgHOURT8-RM1_ltKxs8ZLfG7k-iNUsEDWistORuBQp6O0",
            "https://yastatic.net/naydex/autoru/1ukUi3778/4205ef1G/0N7taNo3l9visKjMWq2fvzfGYDQYyuJq7F5WKtPBwxQQCWeBjPXwm1wHQZku_lEKPXwSz8RIbmAQd17cjAl8x6XajVy6FoNqInC5zS5Ny6pisuHVfQ6jmn_84OjQs3EWE8_Ec53QVM79Y0W_mB6krAkswihRGFyA4SEICP-ZC2Ej_I-LqMfWX0FyCGhoLN1uN-UmJAsJJAvcX0HqsCITp-AfNgHOURT8-RM1_ltKxs8ZLfG7k-iNUsEDWistORuBQp6O0",
            "https://avatars.mds.yandex.net/get-autoru-vos/1990622/05bcbe37c918dd9e3bbc9f513850936d/1200x900n",
            "https://yastatic.net/naydex/autoru/1ukUi3778/4205ef1G/0N7taNo3l9visKjMWq2fvzfGYDQYyuJq7F5WKtPBwxQQCWeBjPXwyxwXIcke_lEqPUkHyrGdawBwV2sp7Fxs4gD6DTwKQyOfRzWc7S64m6pisuHVfQ6jmn_84OjQs3EWE8_Ec53QVM79Y0W_mB6krAkswihRGFyA4SEICP-ZC2Ej_I-LqMfWX0FyCGhoLN1uN-UmJAsJJAvcX0HqsCITp-AfNgHOURT8-RM1_ltKxs8ZLfG7k-iNUsEDWistORuBQp6O0",
            "https://avatars.mds.yandex.net/get-autoru-vos/2146283/103aa4d9c5dd5d0153858e4f5ecd820f/1200x900n",
            "https://avatars.mds.yandex.net/get-autoru-vos/2143601/7cf721f89b80c4fbd51ce4e17c8d683a/1200x900n"


        )
        private val typeCar = mutableListOf(
            "Седан",
            "Седан",
            "Хэтчбек 5 дв.",
            "Седан",
            "Внедорожник 5 дв.",
            "Седан"

        )
        private val horsepower = mutableListOf(
            280,
            290,
            142,
            150,
            170,
            560

        )
        private val color = mutableListOf(
            "белый",
            "белый",
            "белый",
            "синий",
            "синий",
            "синий"



        )
    }
}
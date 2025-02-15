package ru.andrewkir.saturday10.animals.presentation.contract

sealed class AnimalsUIEffect {

  class ShowNotification(val message: String): AnimalsUIEffect()
}
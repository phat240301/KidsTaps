package com.kidstap.app.domain.model

enum class AnimalType(val emoji: String, val nameEn: String, val nameVi: String) {
    CAT("🐱", "Cat", "Con Mèo"),
    DOG("🐶", "Dog", "Con Chó"),
    ELEPHANT("🐘", "Elephant", "Con Voi"),
    BIRD("🐦", "Bird", "Con Chim"),
    FISH("🐟", "Fish", "Con Cá"),
    RABBIT("🐰", "Rabbit", "Con Thỏ"),
    TIGER("🐯", "Tiger", "Con Hổ"),
    CHICK("🐥", "Chick", "Con Gà Con"),
    MONKEY("🐵", "Monkey", "Con Khỉ"),
    LION("🦁", "Lion", "Con Sư Tử");

    companion object {
        fun random() = entries.random()
        val commonAnimals = listOf(CAT, DOG, BIRD, FISH, RABBIT, ELEPHANT)
        fun randomCommon() = commonAnimals.random()
    }
}

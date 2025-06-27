package com.example.madarsoft.presentation.navigation

import kotlinx.serialization.Serializable

/**
 * Created by Aziza Helmy on 27/06/2025.
 */
sealed interface Destination {

    @Serializable
    data object Home : Destination

    @Serializable
    data object Details : Destination
}
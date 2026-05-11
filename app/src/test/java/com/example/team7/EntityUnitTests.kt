package com.example.team7

import com.example.team7.database.entities.Clothing
import com.example.team7.database.entities.Outfit
import com.example.team7.database.entities.User
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class EntityUnitTests {

   // Created by Tyler
    @Test
    fun userConstructor_setsUsername() {
        val user = User("alice", "pw123")
        assertEquals("alice", user.username)
    }
    // Created by Tyler
    @Test
    fun userConstructor_setsPassword() {
        val user = User("alice", "pw123")
        assertEquals("pw123", user.userPassword)
    }

    // Created by Will
    @Test
    fun user_isNotAdminByDefault() {
        val user = User("alice", "pw123")
        assertFalse(user.isAdmin)
    }
    // Created by Will
    @Test
    fun userSetAdmin_updatesAdminFlag() {
        val user = User("alice", "pw123")

        user.setAdmin(true)
        assertTrue(user.isAdmin)
    }
    // Created by Anthony
    @Test
    fun clothingConstructor_setsName() {
        val clothing = Clothing(7, "Beanie", "head", "uri://hat")
        assertEquals("Beanie", clothing.clothingName)
    }
    // Created by Anthony
    @Test
    fun clothingConstructor_setsType() {
        val clothing = Clothing(7, "Beanie", "head", "uri://hat")
        assertEquals("head", clothing.clothingType)
    }

    // Created by Vrunda
    @Test
    fun outfitConstructor_setsUserId() {
        val outfit = Outfit(12)

        assertEquals(12, outfit.userId)
    }
    // Created by Vrunda
    @Test
    fun outfitSetUris_returnsAllUris() {
        val outfit = Outfit(12)
        outfit.setUris("u1", "u2", "u3")
        assertArrayEquals(arrayOf("u1", "u2", "u3"), outfit.uris)
        assertNotNull(outfit.dateCreated)
    }
}

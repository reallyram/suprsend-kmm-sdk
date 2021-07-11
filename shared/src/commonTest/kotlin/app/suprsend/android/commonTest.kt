package app.suprsend.android

import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.double
import kotlinx.serialization.json.int
import kotlinx.serialization.json.intOrNull
import kotlinx.serialization.json.jsonPrimitive
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class CommonGreetingTest {

    @Test
    fun testExample() {
        assertTrue(Greeting().greeting().contains("Hello"), "Check 'Hello' is mentioned")
    }

    @Test
    fun jsonObjectTest() {
        var jsonObject = buildJsonObject {
            put("event_name", JsonPrimitive("Add to Cart"))
            put("price", JsonPrimitive(340))
        }

        assertEquals(340, (jsonObject.get("price") as JsonPrimitive).intOrNull)

        jsonObject = JsonObject(jsonObject + ("price" to JsonPrimitive(350)))

        assertEquals(350, (jsonObject.get("price") as JsonPrimitive).intOrNull)
    }
}
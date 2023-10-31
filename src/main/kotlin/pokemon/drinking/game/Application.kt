package pokemon.drinking.game

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.websocket.*
import io.ktor.server.netty.*
import pokemon.drinking.game.plugins.configureRouting

@JsonIgnoreProperties("tagLabel")
data class PlayerState(
    val name: String,
    val token: String,
    val space: Int,
    val lostTurns: Int,
    val tags: Set<String>,
    val drinks: Int,
    val finishes: Int,
    val birds: Int,
    val eliteFour: Boolean,
    val gyms: Map<Int, Boolean>
)

data class GameState(
    val players: List<PlayerState>,
    val currentPlayer: Int,
    val psyduck: Boolean,
    val slowpoke: Boolean,
    val manualMode: Boolean,
)

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    install(WebSockets)
    configureRouting()
}

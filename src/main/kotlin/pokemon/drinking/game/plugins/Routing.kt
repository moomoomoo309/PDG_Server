package pokemon.drinking.game.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import pokemon.drinking.game.GameMessage
import pokemon.drinking.game.GameState
import pokemon.drinking.game.PullMessage
import pokemon.drinking.game.PushMessage

val clients = mutableSetOf<WebSocketServerSession>()
lateinit var currentState: GameState

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        webSocket("/game") {
            clients += this
            val msg = receiveDeserialized<GameMessage>()
            when (msg) {
                is PushMessage -> {
                    currentState = msg.newState
                    for (client in clients)
                        if (client != this)
                            client.sendSerialized(msg)
                }
                is PullMessage -> {
                    this.sendSerialized(PullMessage(currentState = currentState))
                }
            }
        }
    }
}

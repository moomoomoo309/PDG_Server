package pokemon.drinking.game

enum class GameMessageType {
    Push,
    Pull
}

sealed class GameMessage(open val type: GameMessageType)

typealias StateChange = Pair<String, Any?>
data class PushMessage(override val type: GameMessageType = GameMessageType.Push, val newState: GameState, val stateChanges: List<StateChange>):
    GameMessage(type)

data class PullMessage(override val type: GameMessageType = GameMessageType.Pull, val currentState: GameState):
    GameMessage(type)



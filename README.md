# Pokemon Drinking Game Server
This is the server-side software that enables multiple people to join games of 
the pokemon drinking game over the internet.

## TODO:
- Add room code support
- Figure out client responsibilities vs. server responsibilities
  - Game state is synced, but current game is not event-based, so client handles all game logic on their turn
    - This is bad design, but the PDG was not designed with a server in mind.


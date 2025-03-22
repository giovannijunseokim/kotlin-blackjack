package blackjack.domain.participant

import blackjack.domain.Betting
import blackjack.domain.Deck

class Player(
    val name: String,
    val betting: Betting,
    deck: Deck,
) : Participant(deck) {
    init {
        ready()
    }

    private fun ready() {
        hit()
    }
}

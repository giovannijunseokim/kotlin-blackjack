package blackjack.domain.participant

import blackjack.domain.Deck
import blackjack.domain.GameResult
import blackjack.domain.Score
import blackjack.domain.state.Blackjack
import blackjack.domain.state.Busted
import blackjack.domain.state.Finished
import blackjack.domain.state.Stay

class Dealer(
    deck: Deck,
) : Participant(deck) {
    fun playerProfit(player: Player): Double = playerResult(player).calculateProfit(player.betting.amount)

    private fun playerResult(player: Player): GameResult {
        val dealerState = state
        val playerState = player.state
        check(dealerState is Finished) { "Player's turn is not finished" }
        check(playerState is Finished) { "Dealer's turn is not finished" }

        return when (playerState) {
            is Blackjack -> playerResultWhenBlackjack(dealerState)
            is Busted -> getResultWhenBusted()
            is Stay -> getResultWhenBusted(playerState.score, dealerState)
        }
    }

    private fun playerResultWhenBlackjack(dealerState: Finished): GameResult =
        when (dealerState) {
            is Blackjack -> GameResult.DRAW
            is Busted -> GameResult.BLACKJACK_WIN
            is Stay -> GameResult.BLACKJACK_WIN
        }

    private fun getResultWhenBusted(): GameResult = GameResult.LOSE

    private fun getResultWhenBusted(
        playerScore: Score,
        dealerState: Finished,
    ): GameResult =
        when (dealerState) {
            is Blackjack -> GameResult.LOSE
            is Busted -> GameResult.WIN
            is Stay -> getResultWhenBothStay(playerScore)
        }

    private fun getResultWhenBothStay(playerScore: Score): GameResult =
        when {
            playerScore > score -> GameResult.WIN
            playerScore < score -> GameResult.LOSE
            else -> GameResult.DRAW
        }
}

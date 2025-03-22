package blackjack.study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.time.measureTime

class ListMutableListStudy {
    @Test
    fun `List, MutableList의 변경 속도 비교`() {
        val mutableListAddTime =
            measureTime {
                val mutableList = mutableListOf(1, 2, 3, 4, 5, 6)
                mutableList.add(7)
            }

        val listAddTime =
            measureTime {
                var list = listOf(1, 2, 3, 4, 5, 6)
                list = list + 7
            }

        println("mutableListAddTime: $mutableListAddTime")
        println("listAddTime: $listAddTime")
        assertThat(mutableListAddTime > listAddTime).isTrue()
    }
}

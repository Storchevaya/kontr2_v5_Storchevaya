import kotlinx.coroutines.*

class MilkProduct(
    name: String,
    weight: Double,
    fat: Double,
    manufacturer: String,
    price: Double,
    private val shelfLife: Int
) : DairyProduct(name, weight, fat, manufacturer, price) {

    override fun calculatePricePerKg(): Double {
        return (price / weight) * 1000
    }

    // Функция 1: с задержкой
    suspend fun checkFresh() {
        println("Проверка свежести $name...")
        delay(3000)
        if (shelfLife > 7) {
            println("$name свежий! Срок хранения: $shelfLife дней")
        } else {
            println("$name скоро испортится! Осталось $shelfLife дней")
        }
    }

    // Функция 2: для сопрограммы
    suspend fun showSale(num: Int) {
        delay(1000)
        val sale = when {
            shelfLife <= 3 -> 50
            shelfLife <= 7 -> 30
            shelfLife <= 14 -> 15
            else -> 0
        }

        if (sale > 0) {
            val newPrice = price * (100 - sale) / 100
            println("[$num] Скидка $sale% на $name = ${"%.2f".format(newPrice)} руб.")
        } else {
            println("[$num] На $name скидки нет")
        }
    }
}

import kotlinx.coroutines.*

fun main() = runBlocking {
    val products = mutableListOf<MilkProduct>()
    println("     МОЛОЧНЫЕ ПРОДУКТЫ")
    for (i in 1..3) {
        println("\nПродукт $i")
        var name: String
        do {
            print("Название: ")
            name = readln()
            if (name.isBlank()) println("Ошибка! Введите название!")
        } while (name.isBlank())
        var weight: Double
        do {
            print("Вес (г): ")
            val input = readln()
            weight = input.toDoubleOrNull() ?: -1.0
            if (weight <= 0) println("Ошибка! Вес > 0!")
        } while (weight <= 0)
        var fat: Double
        do {
            print("Жирность (0-100): ")
            val input = readln()
            fat = input.toDoubleOrNull() ?: -1.0
            if (fat < 0 || fat > 100) println("Ошибка! Жирность 0-100!")
        } while (fat < 0 || fat > 100)
        var manufacturer: String
        do {
            print("Производитель: ")
            manufacturer = readln()
            if (manufacturer.isBlank()) println("Ошибка! Введите производителя!")
        } while (manufacturer.isBlank())
        var price: Double
        do {
            print("Цена (руб): ")
            val input = readln()
            price = input.toDoubleOrNull() ?: -1.0
            if (price < 0) println("Ошибка! Цена >= 0!")
        } while (price < 0)
        var days: Int
        do {
            print("Срок хранения (дни): ")
            val input = readln()
            days = input.toIntOrNull() ?: -1
            if (days <= 0) println("Ошибка! Срок > 0!")
        } while (days <= 0)
        val product = MilkProduct(name, weight, fat, manufacturer, price, days)
        products.add(product)
        println("Добавлено!")
    }
    println("\nВСЕ ПРОДУКТЫ")
    for (product in products) {
        println(product.displayInfo())
    }
    println("\nПРОВЕРКА СВЕЖЕСТИ")
    for (product in products) {
        product.checkFresh()
    }
    println("\nСКИДКИ")

    var n: Int
    do {
        print("Сколько раз проверить скидки? ")
        val input = readln()
        n = input.toIntOrNull() ?: -1
        if (n <= 0) println("Ошибка! Введите число > 0!")
    } while (n <= 0)
    val jobs = mutableListOf<Job>()
    for (num in 1..n) {
        for (product in products) {
            val job = launch {
                product.showSale(num)
            }
            jobs.add(job)
        }
    }
    jobs.joinAll()
}

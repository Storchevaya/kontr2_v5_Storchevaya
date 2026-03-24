abstract class DairyProduct(
    val name: String,
    val weight: Double,
    val fat: Double,
    val manufacturer: String,
    var price: Double
) : ProductInterface {
    override fun displayInfo(): String {
        return """
            Название: $name
            Вес: $weight г
            Жирность: $fat%
            Производитель: $manufacturer
            Цена: $price руб.
            Цена за кг: ${calculatePricePerKg()} руб.
        """.trimIndent()
    }
    override fun getPrice(): Double = price
    abstract fun calculatePricePerKg(): Double
}
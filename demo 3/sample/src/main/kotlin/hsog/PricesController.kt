package hsog

import kotlin.Int
import kotlin.String
import org.springframework.web.bind.`annotation`.DeleteMapping
import org.springframework.web.bind.`annotation`.GetMapping
import org.springframework.web.bind.`annotation`.PathVariable
import org.springframework.web.bind.`annotation`.PostMapping
import org.springframework.web.bind.`annotation`.RestController

@RestController
public class PricesController {
  @GetMapping("/prices")
  public fun getPrice(): String = "Hello from PricesController /GET"

  @PostMapping("/prices")
  public fun postPrice(): String = "Hello from PricesController /POST"

  @DeleteMapping("/prices/{id}")
  public fun deletePrice(@PathVariable id: Int): String = """
  |Hello from PricesController /DELETE
  |Deleted Price with ID $id
  """.trimMargin()
}

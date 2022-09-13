package hsog

import kotlin.Int
import kotlin.String
import org.springframework.web.bind.`annotation`.DeleteMapping
import org.springframework.web.bind.`annotation`.GetMapping
import org.springframework.web.bind.`annotation`.PathVariable
import org.springframework.web.bind.`annotation`.PostMapping
import org.springframework.web.bind.`annotation`.RestController

@RestController
public class ProductsController {
  @GetMapping("/products")
  public fun getProduct(): String = "Hello from ProductsController /GET"

  @PostMapping("/products")
  public fun postProduct(): String = "Hello from ProductsController /POST"

  @DeleteMapping("/products/{id}")
  public fun deleteProduct(@PathVariable id: Int): String = """
  |Hello from ProductsController /DELETE
  |Deleted Product with ID $id
  """.trimMargin()
}

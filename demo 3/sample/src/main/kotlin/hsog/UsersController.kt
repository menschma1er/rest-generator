package hsog

import kotlin.Int
import kotlin.String
import org.springframework.web.bind.`annotation`.DeleteMapping
import org.springframework.web.bind.`annotation`.GetMapping
import org.springframework.web.bind.`annotation`.PathVariable
import org.springframework.web.bind.`annotation`.PostMapping
import org.springframework.web.bind.`annotation`.RestController

@RestController
public class UsersController {
  @GetMapping("/users")
  public fun getUser(): String = "Hello from UsersController /GET"

  @PostMapping("/users")
  public fun postUser(): String = "Hello from UsersController /POST"

  @DeleteMapping("/users/{id}")
  public fun deleteUser(@PathVariable id: Int): String = """
  |Hello from UsersController /DELETE
  |Deleted User with ID $id
  """.trimMargin()
}

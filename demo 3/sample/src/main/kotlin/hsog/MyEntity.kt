package hsog

import hsog.Entity
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.Date

@RuntimeAnnotation
@BinaryAnnotation
@SourceAnnotation
@Entity("userEntity")
data class User(
    val name: String = "",
    val id: Int = 0,
) {}
@RuntimeAnnotation
@Entity("priceEntity")
data class Price(
    val name: Date = Date(),
    val price: Double = 0.0,
) {}
@SourceAnnotation
@Entity("productEntity")
data class Product(
    val name: String = "",
    val id: Int = 0,
) {}


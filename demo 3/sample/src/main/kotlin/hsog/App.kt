package hsog

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class APIGenerator

fun main(args: Array<String>) {
    runApplication<APIGenerator>(*args)
}

@RestController
class DefaultController {
    @GetMapping("/find")
    fun findRuntimeAnnotations() {

        println("---------- User ----------")
        val annotationsUser = User::class.java.annotations
        if (annotationsUser.size <= 1)
            print("No Annotations found on ${User::class.simpleName} ")
        for (annotation in annotationsUser) {
            when (annotation) {
                is RuntimeAnnotation -> println("RuntimeAnnotation found!")
                is SourceAnnotation -> print("SourceAnnotation found!")
                is Entity -> println("EntityAnnotation found!")
                is BinaryAnnotation -> println("BinaryAnnotation found!")
            }
        }
        println()
        println("---------- Product ----------")
        val annotationsProduct = Product::class.java.annotations
        if (annotationsProduct.size <= 1)
            print("No Annotations found on ${Product::class.simpleName} ")

        for (annotation in annotationsProduct) {
            when (annotation) {
                is RuntimeAnnotation -> println("RuntimeAnnotation found!")
                is SourceAnnotation -> print("SourceAnnotation found!")
                is Entity -> println("EntityAnnotation found!")
                is BinaryAnnotation -> println("BinaryAnnotation found!")
            }
        }
    }
}
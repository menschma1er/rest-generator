import com.google.auto.service.AutoService
import com.squareup.kotlinpoet.*
import hsog.Entity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.Processor
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement
import kotlin.io.path.Path
import kotlin.reflect.KClass

@AutoService(Processor::class)
class Generator : AbstractProcessor() {

    override fun getSupportedSourceVersion(): SourceVersion {
        return SourceVersion.latest()
    }

    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        return mutableSetOf(Entity::class.java.name)
    }

    override fun process(annotations: MutableSet<out TypeElement>?, roundEnv: RoundEnvironment): Boolean {
        val elementsWithConstant = roundEnv.getElementsAnnotatedWith(Entity::class.java)
        if (elementsWithConstant.isEmpty()) {
            return true
        }


        for (element in elementsWithConstant) {
            val fileName = "${element.simpleName}sController"
            val packageName = "hsog"
            val classBuilder = TypeSpec.classBuilder(fileName)

            val getEndpointBuilder = generateGetEndpoint(element.simpleName.toString())
            val postEndpointBuilder = generatePostEndpoint(element.simpleName.toString())
            val deleteEndpointBuilder = generateDeleteEndpoint(element.simpleName.toString())

            classBuilder
                .addFunction(getEndpointBuilder.build())
                .addFunction(postEndpointBuilder.build())
                .addFunction(deleteEndpointBuilder.build())

            val ann = generateClassAnnotation()
            classBuilder.addAnnotation(ann.build())

            val file = FileSpec.builder(packageName, fileName)
                .addType(classBuilder.build())
                .build()

            val generatedDirectory = "${System.getProperty("user.dir")}/sample/src/main/kotlin"
            file.writeTo(Path(generatedDirectory, ""))
        }
        return true


    }

    companion object {
        const val KAPT_KOTLIN_GENERATED_OPTION_NAME = "kapt.kotlin.generated"
        //const val KAPT_KOTLIN_GENERATED_OPTION_NAME = ""
    }

    private fun generateClassAnnotation(): AnnotationSpec.Builder {
        return AnnotationSpec.builder(
            ClassName(
                RestController::class.java.packageName,
                RestController::class.java.simpleName
            )
        )
    }

    private fun generateGetEndpoint(name: String): FunSpec.Builder {

        val annotation =
            AnnotationSpec.builder(ClassName(GetMapping::class.java.packageName, GetMapping::class.java.simpleName))
                .addMember("%S", "/" + name.lowercase() + "s")


        val funcBuilder = FunSpec.builder(name = "get$name").returns(String::class)
            .addStatement("return %S", "Hello from ${name + "sController"} /GET")

        funcBuilder.addAnnotation(annotation.build())

        return funcBuilder
    }

    private fun generatePostEndpoint(name: String): FunSpec.Builder {
        val annotation =
            AnnotationSpec.builder(ClassName(PostMapping::class.java.packageName, PostMapping::class.java.simpleName))
                .addMember("%S", "/" + name.lowercase() + "s")

        val funcBuilder = FunSpec.builder(name = "post$name").returns(String::class)
            .addStatement("return %S", "Hello from ${name + "sController"} /POST")

        funcBuilder.addAnnotation(annotation.build())

        return funcBuilder
    }

    private fun generateDeleteEndpoint(name: String): FunSpec.Builder {
        val annotation =
            AnnotationSpec.builder(
                ClassName(
                    DeleteMapping::class.java.packageName,
                    DeleteMapping::class.java.simpleName
                )
            )
                .addMember("%S", "/" + name.lowercase() + "s/{id}")
        val output = "Hello from ${name + "sController"} /DELETE\nDeleted $name with ID \$id".trimMargin()


        val funcBuilder = FunSpec.builder(name = "delete$name").returns(String::class)
            .addParameter(getAnnotatedParam(name, Int::class).build())
            .returns(String::class)
            .addStatement(
                //"return %P",
                //"Hello from ${name + "sController"} /DELETE",
                "return %P",
                output
            )

        funcBuilder.addAnnotation(annotation.build())

        return funcBuilder
    }

    private fun getAnnotatedParam(name: String, type: KClass<Int>): ParameterSpec.Builder {
        return ParameterSpec.builder("id", Int::class)
            .addAnnotation(PathVariable::class)
    }

}
//@DeleteMapping(value = "/posts/{id}")



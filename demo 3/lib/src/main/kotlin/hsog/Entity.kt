package hsog


@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.CLASS)
annotation class Entity(
	val propName: String,
)
package logging

import org.junit.jupiter.api.Test

internal class ErrorMessageTest {
    
    @Test
    fun generateDocumentation() {
        println("| Error code | Message | Resolution |")
        println("| -----------|---------|------------|")
        ErrorMessage.values().forEach {
            println("| ${it.code} | ${it.message} | TODO |")
        }
    }
}
// [Template no Kotlin Playground](https://pl.kotl.in/WcteahpyN)
import java.time.LocalTime
import java.time.Duration


enum class Nivel { 
    BASICO, INTERMEDIARIO, AVANCADO;
    
    override fun toString(): String {
        return when(this) {
         BASICO -> "básico" 
         INTERMEDIARIO -> "intermediário" 
         else -> "avançado"    
        }
    }
}

data class Usuario(val id: Int, val nome: String, val idade: Int, val pendencias: MutableList<String> = mutableListOf<String>())

data class ConteudoEducacional(val id: Int, var nome: String, val duracao: Long = 60)

data class Formacao(val id: Int, val nome: String, val nivel: Nivel, val conteudo: List<ConteudoEducacional>) {
    val inscritos = mutableListOf<Usuario>()
    val pendentes = mutableListOf<Usuario>()

    fun matricular(usuarios: List<Usuario>) {
        //TODO("Utilize o parâmetro $usuario para simular uma matrícula (usar a lista de $inscritos).")
        for(student in usuarios){
        	if(student.idade < 18){
                student.pendencias.add("menor de idade, solicitar permissão dos pais")
            	pendentes.add(student)    
            } else {
                inscritos.add(student)
            }
        }
    }
    
    fun exibeEstudantesInscritos(){
        println("Incritos na ${nome}:")
        println("=".repeat(80))
        for(student in inscritos){
            println(" nome: ${student.nome} - idade: ${student.idade} anos")
        }
        println("=".repeat(80))
    }

    
    fun exibeEstudantesComPendencia(){
        println("Incritos com pendências na ${nome}:")
        println("=".repeat(80))
        for(student in pendentes){
            println(" nome: ${student.nome} - idade: ${student.idade} anos - pendências: ${student.pendencias}")
        }
        println("=".repeat(80))
    }

    
    fun exibeGradeCurricular(){
        println("Grade curricular da ${nome} - Nivel ${nivel}:")
        println("=".repeat(80))
        for(content in conteudo){
            println(" curso: ${content.nome} - duração: ${converteMinutosToHorasEminutos(content.duracao)}")
        }
        println("=".repeat(80))
    }

    fun converteMinutosToHorasEminutos(minutos: Long): String {
        val convert = LocalTime.MIN.plus(Duration.ofMinutes(minutos)).toString().split(":");
        var result = "${convert[0]} hora e ${convert[1]} minutos";
        
        if(convert[0].toInt() < 1 && convert[1].toInt() > 0){
            result = "${convert[1]} minutos";
        } else if(convert[0].toInt() > 1 && convert[1].toInt() > 0){
        	result = "${convert[0]} horas e ${convert[1]} minutos";
        } else if(convert[0].toInt() > 1 && convert[1].toInt() < 1){
        	result = "${convert[0]} horas";
        } else if(convert[0].toInt() == 1 && convert[1].toInt() < 1){
        	result = "${convert[0]} hora";
        }

        
        return result
    }
}

fun main() {
    //TODO("Analise as classes modeladas para este domínio de aplicação e pense em formas de evoluí-las.")
    //TODO("Simule alguns cenários de teste. Para isso, crie alguns objetos usando as classes em questão.")
    val students = listOf(
        Usuario(id=1, nome="User 01", idade=30),
        Usuario(id=2, nome="User 02", idade=17),
        Usuario(id=3, nome="User 03", idade=16),
        Usuario(id=4, nome="User 04", idade=48),
        Usuario(id=5, nome="User 05", idade=25),
    )
    
    val content = listOf(
    	ConteudoEducacional(id=1,nome="Trabalhando com equipes ágeis",duracao=120),	
    	ConteudoEducacional(id=2,nome="Versionamento de código com Git e GitHub",duracao=75),	
    	ConteudoEducacional(id=3,nome="Contribuindo em um projeto opensource no GitHub",duracao=30),	
    	ConteudoEducacional(id=4,nome="Preparando seu ambiente de desenvolvimento Kotlin",duracao=30),	
    	ConteudoEducacional(id=5,nome="Primeiros passos na linguagem Kotlin",duracao=150),	
    	ConteudoEducacional(id=6,nome="Orientação a objetos em Kotlin",duracao=90),	
    	ConteudoEducacional(id=7,nome="Propriedades",duracao=15),	
    	ConteudoEducacional(id=8,nome="Métodos",duracao=30),	
    	ConteudoEducacional(id=9,nome="Eventos",duracao=30),	
    )
    
    val formation = Formacao(id=1,nome="Formação Orientação a objetos em Kotlin",nivel=Nivel.BASICO,conteudo=content)
	
    formation.matricular(students)
    formation.exibeGradeCurricular()
    formation.exibeEstudantesInscritos()
    formation.exibeEstudantesComPendencia()

}

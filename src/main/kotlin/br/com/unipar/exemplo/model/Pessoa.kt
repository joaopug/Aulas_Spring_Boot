package br.com.unipar.exemplo.model

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.GenerationType
import jakarta.persistence.GeneratedValue

@Entity//Essa anotação significa que essa classe será uma tabela no Banco de Dados

//Por padrão deixar o tipo data no class
data class Pessoa(
    @Id//Toda classe Entity precisa informar o id
    @GeneratedValue(strategy = GenerationType.AUTO) //Os ids
    val id: Long? = null,//Usamos o valor nulo para informar a JPA que este é um dado novo
    val nome: String,
    val idade: Int,
    val cpf: String,
)
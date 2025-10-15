package br.com.unipar.exemplo.controller

import br.com.unipar.exemplo.database.PessoasRepository
import br.com.unipar.exemplo.model.Pessoa
import org.apache.coyote.Response
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

//

//A anotação RestController não pode usar VIEW, basicamente é para contruir APIs
//A anotação Controller permite ter VIEW, para construir Web Applications

//Endpoint é uma rota onde você
@RestController
@RequestMapping("/pessoa")
class PessoaController(
    private val pessoaRepository: PessoasRepository
    // Uma injeção de dependência é necessária quando
    // usamos uma classe externa, essa injeção (variável)
    // deve ser privada, por uma questão de segurança.
) {
    //Quero fazer uma requisição para o BD fazer algo
    //Enviar para o banco
    @PostMapping

    //A internet se comunica entre si com JSON

    //ResquestBody -> Tenho um JSON e quero transformar numa classe
    fun cadastrarPessoa(@RequestBody pessoa: Pessoa)
            : ResponseEntity<Pessoa> {
        return ResponseEntity.ok(
            pessoaRepository.save<Pessoa>(pessoa)
        )
    }

    //Quero pegar algo do banco
    @GetMapping
    fun listarPessoas(): ResponseEntity<List<Pessoa>> {
        return ResponseEntity.ok(pessoaRepository.findAll())
    }

    @GetMapping("/{id}")
    fun listarId(@PathVariable id: Long): ResponseEntity<Pessoa> {
        val pessoa = pessoaRepository.findById(id).get()
        return if (pessoa != null) {
            ResponseEntity.ok(pessoa)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun excluirPessoa (@PathVariable id: Long): ResponseEntity<Void> {
        val pessoa = pessoaRepository.deleteById(id)
        return if (pessoa != null) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}

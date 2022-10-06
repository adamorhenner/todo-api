package com.todo.todoapi.controller;

import com.todo.todoapi.model.Tarefa;
import com.todo.todoapi.model.dto.TarefaDto;
import com.todo.todoapi.repository.TarefaRepository;
import com.todo.todoapi.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/todo")
@CrossOrigin(origins = "*", maxAge = 3600)
public class TarefaController {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<Tarefa> criar(@Validated(Tarefa.class) @RequestBody Tarefa tarefa) {
        Tarefa tarefaSalva = tarefaRepository.save(tarefa);
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefaSalva);
    }

    @GetMapping
    public ResponseEntity<List<TarefaDto>> findAll() {
        return ResponseEntity.ok(tarefaRepository.findAll().stream().map(dto-> TarefaDto.builder()
                .id(dto.getId())
                .descricao(dto.getDescricao())
                .concluido(dto.getConcluido())
                .build()).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> buscarPeloId(@PathVariable Long id) {
        Optional<Tarefa> tarefa = tarefaRepository.findById(id);
        return tarefa.isPresent()? ResponseEntity.ok(tarefa.get()) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover (@PathVariable Long id) {
        this.tarefaRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizar(@PathVariable Long id, @Validated(Tarefa.class) @RequestBody Tarefa tarefa) {
        Tarefa tarefaSalva = tarefaService.atualizar(id,tarefa);
        return ResponseEntity.ok(tarefaSalva);
    }

    @PutMapping("/{id}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarPropriedadeAtivo(@PathVariable Long id, @RequestBody Boolean concluido) {
        tarefaService.atualizarPropriedadeConcluido(id,concluido);
    }


}

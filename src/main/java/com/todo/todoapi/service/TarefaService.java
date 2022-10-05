package com.todo.todoapi.service;

import com.todo.todoapi.model.Tarefa;
import com.todo.todoapi.repository.TarefaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    public Tarefa atualizar(Long id, Tarefa tarefa){
        Tarefa tarefaSalva = findTarefaById(id);

        BeanUtils.copyProperties(tarefa,tarefaSalva,"id");
        return tarefaRepository.save(tarefaSalva);
    }

    private Tarefa findTarefaById(Long id) {
        return tarefaRepository.findById(id).orElseThrow(()-> new EmptyResultDataAccessException(1));
    }

    public void atualizarPropriedadeConcluido(Long id, Boolean concluido){
        Tarefa tarefaSalva = findTarefaById(id);
        tarefaSalva.setConcluido(concluido);
        tarefaRepository.save(tarefaSalva);
    }


}

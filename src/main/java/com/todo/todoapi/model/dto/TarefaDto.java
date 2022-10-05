package com.todo.todoapi.model.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TarefaDto {
    private String descricao;
    private Boolean concluido;
}

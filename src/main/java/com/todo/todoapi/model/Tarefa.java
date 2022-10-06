package com.todo.todoapi.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tarefa")
@SequenceGenerator(name = "SEQUENCE", sequenceName = "tarefa_id_seq", allocationSize = 1)
public class Tarefa extends BaseEntity {

    @NotNull
    @Column(name = "descricao")
    private String descricao;

    @NotNull
    @Column(name = "concluido")
    private Boolean concluido = false;
}

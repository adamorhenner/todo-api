CREATE TABLE tarefa
(
    id        BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    descricao VARCHAR(255),
    concluido BOOLEAN,
    CONSTRAINT pk_tarefa PRIMARY KEY (id)
);


INSERT INTO public.tarefa(
     concluido, descricao)
VALUES (false, 'fazer arroz'),
       (false, 'fazer feijao'),
       (false, 'lavar carro'),
       (false, 'estudar'),
       (false, 'ir ao compromisso');
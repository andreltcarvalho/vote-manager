package com.sistem.design.vote.manager.builder;

import com.sistem.design.vote.manager.app.dto.InsertAgendaDTO;
import com.sistem.design.vote.manager.app.dto.VoteDTO;
import com.sistem.design.vote.manager.app.exception.BusinessException;
import com.sistem.design.vote.manager.app.mapper.AgendaMapper;
import com.sistem.design.vote.manager.app.mapper.VoteMapper;
import com.sistem.design.vote.manager.app.model.Agenda;
import com.sistem.design.vote.manager.app.model.Vote;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * The type Test entity builder.
 */
public class VoteTestEntityBuilder {
    private VoteTestEntityBuilder() {

    }

    /**
     * Formatador para manter as datas no padrão esperado
     */
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    /**
     * Abstração para pegar o horário atual nos testes
     *
     * @return Hora atual
     */
    public static LocalDateTime getNow() {
        return LocalDateTime.now(ZoneId.systemDefault());
    }

    //Abaixo a mesma logica de builders, mas para os Votos.


    /**
     * Cria um voto baseado em uma Pauta
     *
     * @param agendaStartDate Horário de abertura da Pauta
     * @param agendaEndDate   Horário de fechamento da Pauta
     * @return Voto criado
     */
    public static Vote buildSimpleVote(LocalDateTime agendaStartDate, LocalDateTime agendaEndDate) {
        return buildVote("Sim", "12112112111", agendaStartDate, agendaEndDate);
    }

    /**
     * Cria um voto mais específico com parâmetros escolhidos
     *
     * @param voteResult      Resultado do Voto (Sim ou Não)
     * @param cpf             Cpf do Usuário
     * @param agendaStartDate Horário de abertura da Pauta
     * @param agendaEndDate   Horário de fechamento da Pauta
     * @return Entidade do voto.
     * @throws BusinessException Quando o voto for diferente de "Sim" e "Não" ou caso Pauta estiver fechada.
     */
    public static Vote buildVote(String voteResult, String cpf, LocalDateTime agendaStartDate, LocalDateTime agendaEndDate) {
        return VoteMapper.getVoteFromInsertDTO(new VoteDTO().setAgendaId(1L).setVoteResult(voteResult).setCpf(cpf)).setAgenda(AgendaTestEntityBuilder.buildTestAgendaWithDates(agendaStartDate, agendaEndDate));
    }
}

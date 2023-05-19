package com.sistem.design.vote.manager.app.utils;

import com.sistem.design.vote.manager.app.exception.BusinessException;
import com.sistem.design.vote.manager.app.model.Vote;

import java.util.List;

public class BusinessUtils {

    private BusinessUtils() {

    }

    public static void validateIfTheSessionIsOpen(Vote vote) {
        if (vote.getVoteTime().isBefore(vote.getAgenda().getStartDate())) {
            throw new BusinessException(String.format("This Agenda is not open yet. It will be possible to vote at %s", vote.getAgenda().getStartDate()));
        } else if (vote.getVoteTime().isAfter(vote.getAgenda().getEndDate())) {
            throw new BusinessException(String.format("This Agenda was closed at %s. Is not possible to vote anymore.", vote.getAgenda().getEndDate()));
        }
    }

    public static String validateAndReturnVoteResult(String voteResult) {
        List<String> possibleVotes = List.of("Sim", "Não");
        if (!possibleVotes.contains(voteResult)) {
            throw new BusinessException(String.format("The vote '%s' is invalid. The possible votes are: %s", voteResult, possibleVotes));
        }
        return voteResult;
    }
}

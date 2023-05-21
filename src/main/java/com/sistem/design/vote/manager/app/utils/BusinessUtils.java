package com.sistem.design.vote.manager.app.utils;

import com.sistem.design.vote.manager.app.exception.BusinessException;
import com.sistem.design.vote.manager.app.model.Agenda;
import com.sistem.design.vote.manager.app.model.Vote;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import static com.sistem.design.vote.manager.app.utils.Constants.*;

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

    public static boolean isSessionOpen(Agenda agenda) {
        LocalDateTime now = LocalDateTime.now(ZoneId.systemDefault());
        return now.isAfter(agenda.getStartDate()) && now.isBefore(agenda.getEndDate());

    }

    public static String validateAndReturnVoteResult(String voteResult) {
        List<String> possibleVotes = List.of(SIM, NAO);
        if (!possibleVotes.contains(voteResult)) {
            throw new BusinessException(String.format("The vote '%s' is invalid. The possible votes are: %s", voteResult, possibleVotes));
        }
        return voteResult;
    }
}

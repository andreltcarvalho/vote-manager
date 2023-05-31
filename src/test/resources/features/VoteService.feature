Feature: Vote Service

  Background:
    Given I create a new agenda with title "Pauta de Votação 1" and description "Pauta Aleatória"

  Scenario Outline: Vote End to End
    Then the agenda should be created successfully
    When I vote on the agenda "<agendaId>" with the result "<voteResult>" and userCpf "<cpf>"
    Then the vote is inserted with success for the agenda "<agendaId>" and userCpf "<cpf>"
    Examples:
      | cpf         | agendaId | voteResult |
      | 12112112111 | 1        | Sim        |
      | 12112112112 | 2        | Não        |


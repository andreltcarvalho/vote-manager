Feature: Agenda Service

  Scenario: Agenda End to End
    Given the system has no agendas
    When I create a new agenda with title "Pauta de Votação 1" and description "Esta pauta tem o Objetivo X"
    Then the agenda should be created successfully
    When I find the agenda by ID "1"
    Then the agenda should be found
    When I delete the agenda by ID "1"
    Then the agenda should be deleted


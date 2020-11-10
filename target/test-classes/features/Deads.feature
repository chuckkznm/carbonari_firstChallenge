Feature: Happy paths Deads

  Background: Deads
    Given me encuentro en el main de RESTool App
    When doy click en la pestaña Deads
    Then Compruebo que me encuentro en el modulo de Deads

  Scenario Outline: Alta de Deads
    When doy click en el botón Add Item en Deads
    And Ingreso "<nombre_dead>" en el campo name en Deads
    And Ingreso "<reason_dead>" en el campo reason en Deads
    And doy click en el botón submit en Deads
    Then compruebo la alerta con el texto Great Success! en Deads
    And Visualizo "<nombre_dead>" en la tabla de Deads
    And Cierro el navegador en Deads

    Examples:
    | nombre_dead |  reason_dead                               |
    | user1       |  Someone cut his head off                  |
   # | user2       |  Suicide                                   |
   # | user3       |  Got poisoned in the purpole Wedding       |

  Scenario Outline: Edit de Deads
    When doy click en el botón de Update Item en Deads
    And Ingreso "<nuevo_nombre_dead>" en el campo name en Deads
    And Ingreso "<reason_editable>" en el campo reason en Deads
    And doy click en el botón submit en Deads
    Then compruebo la alerta con el texto Great Success! en Deads
    And Visualizo "<nuevo_nombre_dead>" en la tabla de Deads
    And Cierro el navegador en Deads

    Examples:
      | nuevo_nombre_dead     | reason_editable         |
      | user_editable1        | Executive Producer      |
      #| user_editable2        | Co-Executive Producer   |
      #| user_editable3        | RESTool creator         |


  Scenario Outline: Delete de Deads
    When busco mi "<nuevo_nombre_dead>" y doy click en el botón de Delete Item en Deads
    And Doy click en el Alert Aceptar en Deads
    And Visualizo la inexistencia de "<nuevo_nombre_dead>" en la tabla de Deads
    And Cierro el navegador en Deads

    Examples:
      | nuevo_nombre_dead     |
      | user_editable1        |
      #| user_editable2       |
      #| user_editable3       |
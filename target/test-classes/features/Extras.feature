Feature: Happy paths Extras

  Background: Extras
    Given me encuentro en el Main de RESTool App
    When doy click en la pestaña Extras
    Then Compruebo que me encuentro en el modulo de Extras

  Scenario Outline: Alta de Extras
    When doy click en el botón Add Item en Extras
    And Ingreso "<nombre_extra>" en el campo name en Extras y doy Enter
    Then compruebo la alerta con el texto Great Success! en Extras
    And Visualizo "<nombre_extra>" en la tabla de Extras
    And Cierro el navegador en Extras

    Examples:
       | nombre_extra  |
       | user1         |
      #| user2         |
      #| user3         |


  Scenario Outline: Edit de Extras
    When busco mi "<nombre_extra>" y doy click en el botón de Update Item en Extras
    And Ingreso "<nombre_extra_editable>" en el campo name en Extras y doy Enter
    Then compruebo la alerta con el texto Great Success! en Extras
    And Visualizo "<nombre_extra_editable>" en la tabla de Extras
    And Cierro el navegador en Extras

    Examples:
       | nombre_extra          |  nombre_extra_editable      |
       | user1                 |    user1_editable           |
      #| user2                |                             |
      #| user3                |                             |


  Scenario Outline: Delete de Extras
    When busco mi "<nombre_extra_editable>" y doy click en el botón de Delete Item
    And doy click en el botón Aceptar
    And Compruebo la inexistencia de "<nombre_extra_editable>" en la tabla de Extras
    And Cierro el navegador en Extras

    Examples:
      | nombre_extra_editable |
      | user1_editable        |
      #| user2                 |
      #| user3                 |
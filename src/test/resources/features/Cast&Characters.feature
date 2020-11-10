Feature: Happy paths Cast&Characters

  Background: C&C
    Given me encuentro en la pantalla principal de RESTool App en C&C
    Then compruebo que me encuentro en C&C

  Scenario Outline: Alta de C&C
    When doy click en el botón ADD ITEM en C&C
    Then valido que abra el modal en C&C
    And Ingreso "<name>" en el campo Name en C&C
    And Ingreso "<real_name>" en el capo realName en C&C
    And Selecciono la opción "<location>" en el campo Location en C&C
    And Doy click en el botón Submit de C&C
    Then Compruebo la alerta con el texto Great Success! en C&C
    And Visualizo "<name>" en la tabla de C&C

    Examples:
      | name            | real_name             | location            |
      | user1           | probando1             | Beyond the Wall     |
     # | user2           | probando2             | Kings Landing       |
     # | user3           | probando3             | Winterfell          |

  Scenario Outline: Edit de C&C
    When busco mi nuevo item y doy click en el botón de Update Item en C&C
    And Ingreso "<number_test>" en el campo NumberTest en C&C
    And Selecciono la opción "<new_location>" en el campo Location en C&C
    And Doy click en el botón Submit de C&C
    Then Compruebo la alerta con el texto Great Success! en C&C
    And Visualizo "<name>" en la tabla de C&C

    Examples:
      | number_test        | new_location     | name  |
      | 2                  | Winterfell       | user1 |
      #| user2_edited      | Beyond the Wall  |       |
      #| user3_edited      | Kings Landing    |       |

  Scenario Outline: Delete de C&C
    When Busco el item "<item_a_eliminar>" y doy click en el botón de Delete Item en C&C
    And Doy click en el Alert Aceptar en C&C
    And Visualizo la inexistencia de "<item_a_eliminar>" en la tabla de C&C

    Examples:
    | item_a_eliminar |
    | user1           |
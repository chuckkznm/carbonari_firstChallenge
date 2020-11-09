Feature: Happy paths Employee

  Background: Employees
    Given me encuentro en la pantalla principal de RESTool App
    When doy click en la pestaña Employees
    Then compruebo que me redirija al modulo de Employees

  Scenario Outline: Alta de employee
    When Doy click en el botón Add Item
    And Ingreso "<nombre_employee>" en el campo name
    And Selecciono la opcion "<option>" en el campo job title
    And Doy click en el botón submit
    Then Compruebo la alerta con el texto Great Success!
    And Visualizo "<nombre_employee>" en la tabla de employees

    Examples:
      | nombre_employee | option                |
      | user1           | RESTool creator       |
     # | user2           | Executive Producer    |
     # | user3           | Co-Executive Producer |

    Scenario Outline: Edit de Employee
      When Busco mi "<nuevo_nombre_employee>" y doy click en el botón de Update Item
      And Ingreso "<nuevo_nombre_employee>" en el campo Name
      And Selecciono la opcion "<option_editable>" en el campo job title
      And Doy click en el botón submit
      Then Compruebo la alerta con el texto Great Success!
      And Visualizo "<nuevo_nombre_employee>" en la tabla de employees

      Examples:
        | nuevo_nombre_employee | option_editable         |
        | user1                 | Executive Producer      |
       # | user_editable2        | Co-Executive Producer   |
       # | user_editable3        | RESTool creator         |

  Scenario Outline: Delete de Employee
    When Busco mi "<nuevo_nombre_employee>" y doy click en el botón de Delete Item
    And Doy click en el Alert Aceptar
    And Visualizo la inexistencia de "<nuevo_nombre_employee>" en la tabla de employees

    Examples:

      | nuevo_nombre_employee |
      |    user1              |

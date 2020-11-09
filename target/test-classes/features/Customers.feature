Feature: Happy paths Employee

  Scenario Outline: Alta de employee
    Given me encuentro en la pantalla principal de RESTool App
    When Doy click en la pestaña Employees
    Then Compruebo que me redirija al modulo de Employees
    When Doy click en el botón Add Item
    And Ingreso "<nombre_employee>" en el campo name
    And Selecciono la opcion "<option>" en el campo job title
    And Doy click en el botón submit
    Then Compruebo la alerta con el texto Great Success!
    And Visualizo "<nombre_employee>" en la tabla de employees

    Examples:
      | nombre_employee | option            |
      | user1       | RESTool creator       |
      | user2       | Executive Producer    |
      | user3       | Co-Executive Producer |
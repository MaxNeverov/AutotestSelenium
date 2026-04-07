Feature: Корзина покупок

  Background:
    Given открыта страница логина
    When пользователь вводит логин "standard_user"
    And пользователь вводит пароль "secret_sauce"
    And нажимает кнопку Login

  Scenario: Успешное добавление товара в корзину
    When пользователь добавляет товар "Sauce Labs Backpack" в корзину
    Then товар "Sauce Labs Backpack" отображается в корзине
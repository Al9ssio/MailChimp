Feature: Registration
  As a user
  I want to register on the website
  So that I can use the website's services

  Scenario: Username is already taken
    Given I am on the registration page 1
    When I enter an email address 1
    And I enter a used username
    And I enter a password 1
    And I click the sign-up button 1
    Then I see an error message saying that the username is already taken

  Scenario: Username is too long
    Given I am on the registration page 2
    When I enter a long username
    And I enter an email address 2
    And I enter a password 2
    And I click the sign-up button 2
    Then I see an error message saying that the username is too long

  Scenario: Email is missing
    Given I am on the registration page 3
    When I do not enter an email address
    And I enter a username 3
    And I enter a password 3
    And I click the sign-up button 3
    Then I see an error message saying that the email address is missing

  Scenario: Registration is successful
    Given I am on the registration page 4
    When I enter an email address 4
    And I enter a username 4
    And I enter a password 4
    And I click the sign-up button 4
    Then I am taken to the success registration page

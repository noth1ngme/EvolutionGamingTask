Feature: Adding an ad to memo as a favorite
  This feature will go the website, Go to any category, search an ad and add to the favorites

  Scenario: Adding memo to the favorites
    Given I will go to the home page
    And I will choose a random category
    And I will choose a random sub-category (If needed)
    Then I will pick a random ad
    And I will add a memo to the favorites
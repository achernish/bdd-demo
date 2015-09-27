Scenario: Test case for transaction pre-authorization

Given a transaction com/inatec/api/test/stories/pre_authorize_transaction.table
When pre-authorize
Then transaction has been pre-authorized
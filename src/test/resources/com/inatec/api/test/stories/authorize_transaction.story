Scenario: Test case for transaction authorization

Given a transaction: com/inatec/api/test/stories/authorize_transaction.table
When authorize
Then transaction has been authorized
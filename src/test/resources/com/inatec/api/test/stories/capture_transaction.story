Scenario: Test case for transaction capturing

Given a transaction com/inatec/api/test/stories/capture_transaction.table
When pre-authorize
Then transaction has been pre-authorized
When capture
Then transaction has been captured
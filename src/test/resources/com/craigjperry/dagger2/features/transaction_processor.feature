Feature: Transaction Processor
  Back office system responsible for clearing incoming transactions.

  Background:
    Given bank accounts
      | AccountId | Balance |
      | 1         | 0       |
      | 2         | 10      |

  Scenario:
    Given a queue of inbound transactions
      | TransactionId | Account | Amount |
      | 1             | 1       | 10    |
      | 2             | 1       | -3     |
      | 3             | 2       | 10    |
      | 4             | 1       | -4    |
    When all transactions are processed
    Then bank account balances are
      | AccountId     | Balance |
      | 1             | 3       |
      | 2             | 20      |

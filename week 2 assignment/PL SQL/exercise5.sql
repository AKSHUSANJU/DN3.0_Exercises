REM   Script: Exercise 5
REM   exercise 5

CREATE OR REPLACE TRIGGER UpdateCustomerLastModified 
BEFORE UPDATE ON Customers 
FOR EACH ROW 
BEGIN 
    -- Update the LastModified column to the current date 
    :NEW.LastModified := SYSDATE; 
END UpdateCustomerLastModified; 
/

CREATE TABLE AuditLog ( 
    AuditID NUMBER PRIMARY KEY, 
    TransactionID NUMBER, 
    AccountID NUMBER, 
    TransactionDate DATE, 
    Amount NUMBER, 
    TransactionType VARCHAR2(10), 
    LogDate DATE 
);

CREATE OR REPLACE TRIGGER CheckTransactionRules 
BEFORE INSERT ON Transactions 
FOR EACH ROW 
DECLARE 
    v_balance NUMBER; 
BEGIN 
    -- Fetch the current balance of the account involved in the transaction 
    SELECT Balance INTO v_balance 
    FROM Accounts 
    WHERE AccountID = :NEW.AccountID 
    FOR UPDATE; 
 
    -- Ensure the withdrawal amount does not exceed the account balance 
    IF :NEW.TransactionType = 'Withdrawal' AND :NEW.Amount > v_balance THEN 
        RAISE_APPLICATION_ERROR(-20001, 'Error: Insufficient balance for withdrawal.'); 
    END IF; 
 
    -- Ensure the deposit amount is positive 
    IF :NEW.TransactionType = 'Deposit' AND :NEW.Amount <= 0 THEN 
        RAISE_APPLICATION_ERROR(-20002, 'Error: Deposit amount must be positive.'); 
    END IF; 
END CheckTransactionRules; 
/

CREATE OR REPLACE TRIGGER CheckTransactionRules 
BEFORE INSERT ON Transactions 
FOR EACH ROW 
DECLARE 
    v_balance NUMBER; 
BEGIN 
    -- Fetch the current balance of the account involved in the transaction 
    SELECT Balance INTO v_balance 
    FROM Accounts 
    WHERE AccountID = :NEW.AccountID 
    FOR UPDATE; 
 
    -- Ensure the withdrawal amount does not exceed the account balance 
    IF :NEW.TransactionType = 'Withdrawal' AND :NEW.Amount > v_balance THEN 
        RAISE_APPLICATION_ERROR(-20001, 'Error: Insufficient balance for withdrawal.'); 
    END IF; 
 
    -- Ensure the deposit amount is positive 
    IF :NEW.TransactionType = 'Deposit' AND :NEW.Amount <= 0 THEN 
        RAISE_APPLICATION_ERROR(-20002, 'Error: Deposit amount must be positive.'); 
    END IF; 
END CheckTransactionRules; 
/

CREATE OR REPLACE TRIGGER CheckTransactionRules 
BEFORE INSERT ON Transactions 
FOR EACH ROW 
DECLARE 
    v_balance NUMBER; 
BEGIN 
    -- Fetch the current balance of the account involved in the transaction 
    SELECT Balance INTO v_balance 
    FROM Accounts 
    WHERE AccountID = :NEW.AccountID 
    FOR UPDATE; 
 
    -- Ensure the withdrawal amount does not exceed the account balance 
    IF :NEW.TransactionType = 'Withdrawal' AND :NEW.Amount > v_balance THEN 
        RAISE_APPLICATION_ERROR(-20001, 'Error: Insufficient balance for withdrawal.'); 
    END IF; 
 
    -- Ensure the deposit amount is positive 
    IF :NEW.TransactionType = 'Deposit' AND :NEW.Amount <= 0 THEN 
        RAISE_APPLICATION_ERROR(-20002, 'Error: Deposit amount must be positive.'); 
    END IF; 
END CheckTransactionRules; 
/

CREATE SEQUENCE AuditLog_seq 
START WITH 1 
INCREMENT BY 1 
NOCACHE 
NOCYCLE;

CREATE OR REPLACE TRIGGER LogTransaction 
AFTER INSERT ON Transactions 
FOR EACH ROW 
BEGIN 
    -- Insert a record into the AuditLog table after a transaction is inserted 
    INSERT INTO AuditLog (AuditID, TransactionID, AccountID, TransactionDate, Amount, TransactionType, LogDate) 
    VALUES ( 
        AuditLog_seq.NEXTVAL,    -- Use the sequence to generate unique AuditID 
        :NEW.TransactionID, 
        :NEW.AccountID, 
        :NEW.TransactionDate, 
        :NEW.Amount, 
        :NEW.TransactionType, 
        SYSDATE 
    ); 
END LogTransaction; 
/


REM   Script: Exercise 6
REM   exercise 6

DECLARE 
    CURSOR cur_transactions IS 
        SELECT c.CustomerID, c.Name, t.TransactionDate, t.Amount, t.TransactionType 
        FROM Customers c 
        JOIN Accounts a ON c.CustomerID = a.CustomerID 
        JOIN Transactions t ON a.AccountID = t.AccountID 
        WHERE TRUNC(t.TransactionDate, 'MM') = TRUNC(SYSDATE, 'MM'); 
         
    v_customerID Customers.CustomerID%TYPE; 
    v_name Customers.Name%TYPE; 
    v_transactionDate Transactions.TransactionDate%TYPE; 
    v_amount Transactions.Amount%TYPE; 
    v_transactionType Transactions.TransactionType%TYPE; 
     
BEGIN 
    OPEN cur_transactions; 
     
    LOOP 
        FETCH cur_transactions INTO v_customerID, v_name, v_transactionDate, v_amount, v_transactionType; 
        EXIT WHEN cur_transactions%NOTFOUND; 
         
        DBMS_OUTPUT.PUT_LINE('Customer ID: ' || v_customerID); 
        DBMS_OUTPUT.PUT_LINE('Name: ' || v_name); 
        DBMS_OUTPUT.PUT_LINE('Transaction Date: ' || v_transactionDate); 
        DBMS_OUTPUT.PUT_LINE('Amount: ' || v_amount); 
        DBMS_OUTPUT.PUT_LINE('Transaction Type: ' || v_transactionType); 
        DBMS_OUTPUT.PUT_LINE('-------------------------'); 
    END LOOP; 
     
    CLOSE cur_transactions; 
END; 
/

DECLARE 
    CURSOR cur_accounts IS 
        SELECT AccountID, Balance 
        FROM Accounts; 
         
    v_accountID Accounts.AccountID%TYPE; 
    v_balance Accounts.Balance%TYPE; 
    v_annualFee NUMBER := 50;  -- Annual fee amount 
     
BEGIN 
    OPEN cur_accounts; 
     
    LOOP 
        FETCH cur_accounts INTO v_accountID, v_balance; 
        EXIT WHEN cur_accounts%NOTFOUND; 
         
        -- Deduct the annual fee from the account balance 
        UPDATE Accounts 
        SET Balance = Balance - v_annualFee, 
            LastModified = SYSDATE 
        WHERE AccountID = v_accountID; 
         
        DBMS_OUTPUT.PUT_LINE('Applied annual fee to Account ID: ' || v_accountID); 
    END LOOP; 
     
    CLOSE cur_accounts; 
     
    COMMIT; 
END; 
/

DECLARE 
    CURSOR cur_loans IS 
        SELECT LoanID, InterestRate 
        FROM Loans; 
         
    v_loanID Loans.LoanID%TYPE; 
    v_interestRate Loans.InterestRate%TYPE; 
    v_rateIncrease NUMBER := 0.5;  -- New policy interest rate increase 
     
BEGIN 
    OPEN cur_loans; 
     
    LOOP 
        FETCH cur_loans INTO v_loanID, v_interestRate; 
        EXIT WHEN cur_loans%NOTFOUND; 
         
        -- Update the interest rate based on the new policy 
        UPDATE Loans 
        SET InterestRate = v_interestRate + v_rateIncrease 
        WHERE LoanID = v_loanID; 
         
        DBMS_OUTPUT.PUT_LINE('Updated interest rate for Loan ID: ' || v_loanID); 
    END LOOP; 
     
    CLOSE cur_loans; 
     
    COMMIT; 
END; 
/


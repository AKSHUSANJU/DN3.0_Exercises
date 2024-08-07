REM   Script: Exercise 2
REM   Exercise 2

CREATE OR REPLACE PROCEDURE SafeTransferFunds( 
    p_fromAccountID IN NUMBER, 
    p_toAccountID IN NUMBER, 
    p_amount IN NUMBER 
) IS 
    insufficient_funds EXCEPTION; 
    v_balance_from NUMBER; 
BEGIN 
    -- Check the balance of the from account 
    SELECT Balance INTO v_balance_from 
    FROM Accounts 
    WHERE AccountID = p_fromAccountID 
    FOR UPDATE; 
 
    -- Raise exception if insufficient funds 
    IF v_balance_from < p_amount THEN 
        RAISE insufficient_funds; 
    END IF; 
 
    -- Deduct from the from account 
    UPDATE Accounts 
    SET Balance = Balance - p_amount, 
        LastModified = SYSDATE 
    WHERE AccountID = p_fromAccountID; 
 
    -- Add to the to account 
    UPDATE Accounts 
    SET Balance = Balance + p_amount, 
        LastModified = SYSDATE 
    WHERE AccountID = p_toAccountID; 
 
    -- Commit the transaction 
    COMMIT; 
 
EXCEPTION 
    WHEN insufficient_funds THEN 
        ROLLBACK; 
        INSERT INTO ErrorLog (ErrorDate, ErrorMessage) 
        VALUES (SYSDATE, 'Error: Insufficient funds in account ' || TO_CHAR(p_fromAccountID)); 
    WHEN OTHERS THEN 
        ROLLBACK; 
        INSERT INTO ErrorLog (ErrorDate, ErrorMessage) 
        VALUES (SYSDATE, 'Error: ' || SQLERRM); 
END SafeTransferFunds; 
 
 
 
CREATE OR REPLACE PROCEDURE UpdateSalary( 
    p_employeeID IN NUMBER, 
    p_percentage IN NUMBER 
) IS 
    employee_not_found EXCEPTION; 
    v_count NUMBER; 
BEGIN 
    -- Check if the employee exists 
    SELECT COUNT(*) INTO v_count 
    FROM Employees 
    WHERE EmployeeID = p_employeeID; 
 
    IF v_count = 0 THEN 
        RAISE employee_not_found; 
    END IF; 
 
    -- Update the salary 
    UPDATE Employees 
    SET Salary = Salary + (Salary * (p_percentage / 100)), 
        HireDate = SYSDATE 
    WHERE EmployeeID = p_employeeID; 
 
    -- Commit the transaction 
    COMMIT; 
 
EXCEPTION 
    WHEN employee_not_found THEN 
        INSERT INTO ErrorLog (ErrorDate, ErrorMessage) 
        VALUES (SYSDATE, 'Error: Employee ID ' || p_employeeID || ' not found.'); 
    WHEN OTHERS THEN 
        ROLLBACK; 
        INSERT INTO ErrorLog (ErrorDate, ErrorMessage) 
        VALUES (SYSDATE, 'Error: ' || SQLERRM); 
END UpdateSalary; 
 
 
 
 
CREATE OR REPLACE PROCEDURE AddNewCustomer( 
    p_customerID IN NUMBER, 
    p_name IN VARCHAR2, 
    p_dob IN DATE, 
    p_balance IN NUMBER 
) IS 
    customer_exists EXCEPTION; 
    v_count NUMBER; 
BEGIN 
    -- Check if the customer already exists 
    SELECT COUNT(*) INTO v_count 
    FROM Customers 
    WHERE CustomerID = p_customerID; 
 
    IF v_count > 0 THEN 
        RAISE customer_exists; 
    END IF; 
 
    -- Insert the new customer 
    INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified) 
    VALUES (p_customerID, p_name, p_dob, p_balance, SYSDATE); 
 
    -- Commit the transaction 
    COMMIT; 
 
EXCEPTION 
    WHEN customer_exists THEN 
        INSERT INTO ErrorLog (ErrorDate, ErrorMessage) 
        VALUES (SYSDATE, 'Error: Customer ID ' || p_customerID || ' already exists.'); 
    WHEN OTHERS THEN 
        ROLLBACK; 
        INSERT INTO ErrorLog (ErrorDate, ErrorMessage) 
        VALUES (SYSDATE, 'Error: ' || SQLERRM); 
END AddNewCustomer; 

/


REM   Script: exercise 1
REM   exercise 1

DESC Customers


ALTER TABLE Customers ADD IsVIP VARCHAR2(5);

BEGIN 
    FOR customer IN (SELECT CustomerID, DOB FROM Customers) LOOP 
        IF (MONTHS_BETWEEN(SYSDATE, customer.DOB) / 12) > 60 THEN 
            UPDATE Loans 
            SET InterestRate = InterestRate - (InterestRate * 0.01) 
            WHERE CustomerID = customer.CustomerID; 
        END IF; 
    END LOOP; 
    COMMIT; 
END; 
/

BEGIN 
    FOR customer IN (SELECT CustomerID, Balance FROM Customers) LOOP 
        IF customer.Balance > 10000 THEN 
            UPDATE Customers 
            SET IsVIP = 'TRUE' 
            WHERE CustomerID = customer.CustomerID; 
        END IF; 
    END LOOP; 
    COMMIT; 
END; 
/

BEGIN 
    FOR loan IN (SELECT LoanID, CustomerID, EndDate FROM Loans WHERE EndDate BETWEEN SYSDATE AND SYSDATE + 30) LOOP 
        DECLARE 
            customer_name VARCHAR2(100); 
        BEGIN 
            SELECT Name INTO customer_name FROM Customers WHERE CustomerID = loan.CustomerID; 
            DBMS_OUTPUT.PUT_LINE('Reminder: Dear ' || customer_name || ', your loan with Loan ID ' || loan.LoanID || ' is due on ' || TO_CHAR(loan.EndDate, 'DD-MON-YYYY') || '.'); 
        END; 
    END LOOP; 
END; 
/

BEGIN 
    FOR loan IN (SELECT LoanID, CustomerID, EndDate FROM Loans WHERE EndDate BETWEEN SYSDATE AND SYSDATE + 30) LOOP 
        DECLARE 
            customer_name VARCHAR2(100); 
        BEGIN 
            SELECT Name INTO customer_name FROM Customers WHERE CustomerID = loan.CustomerID; 
            DBMS_OUTPUT.PUT_LINE('Reminder: Dear ' || customer_name || ', your loan with Loan ID ' || loan.LoanID || ' is due on ' || TO_CHAR(loan.EndDate, 'DD-MON-YYYY') || '.'); 
        END; 
    END LOOP; 
END; 
/


REM   Script: Exercise 3
REM   exercise 3

CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus( 
    p_department IN VARCHAR2, 
    p_bonus_percentage IN NUMBER 
) IS 
BEGIN 
    -- Update the salary of employees in the specified department by adding the bonus percentage 
    UPDATE Employees 
    SET Salary = Salary + (Salary * (p_bonus_percentage / 100)), 
        HireDate = SYSDATE 
    WHERE Department = p_department; 
 
    -- Commit the transaction 
    COMMIT; 
END UpdateEmployeeBonus; 
/


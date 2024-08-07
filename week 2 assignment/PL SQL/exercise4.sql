REM   Script: Exercise 4
REM   exercise 4

CREATE OR REPLACE FUNCTION CalculateAge( 
    p_dob DATE 
) RETURN NUMBER IS 
    v_age NUMBER; 
BEGIN 
    -- Calculate age in years based on the date of birth 
    v_age := TRUNC(MONTHS_BETWEEN(SYSDATE, p_dob) / 12); 
    RETURN v_age; 
END CalculateAge; 
/

CREATE OR REPLACE FUNCTION CalculateMonthlyInstallment( 
    p_loanAmount NUMBER, 
    p_interestRate NUMBER, 
    p_loanDurationYears NUMBER 
) RETURN NUMBER IS 
    v_monthlyRate NUMBER; 
    v_totalMonths NUMBER; 
    v_monthlyInstallment NUMBER; 
BEGIN 
    -- Convert annual interest rate to a monthly interest rate 
    v_monthlyRate := p_interestRate / 12 / 100; 
    -- Calculate the total number of months for the loan 
    v_totalMonths := p_loanDurationYears * 12; 
 
    -- Calculate the monthly installment using the formula for an amortizing loan 
    v_monthlyInstallment := (p_loanAmount * v_monthlyRate) /  
                            (1 - POWER(1 + v_monthlyRate, -v_totalMonths)); 
 
    RETURN v_monthlyInstallment; 
END CalculateMonthlyInstallment; 
/

CREATE OR REPLACE FUNCTION HasSufficientBalance( 
    p_accountID NUMBER, 
    p_amount NUMBER 
) RETURN BOOLEAN IS 
    v_balance NUMBER; 
BEGIN 
    -- Get the current balance for the account 
    SELECT Balance INTO v_balance 
    FROM Accounts 
    WHERE AccountID = p_accountID; 
 
    -- Check if the balance is greater than or equal to the amount 
    IF v_balance >= p_amount THEN 
        RETURN TRUE; 
    ELSE 
        RETURN FALSE; 
    END IF; 
END HasSufficientBalance; 
/


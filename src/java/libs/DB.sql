CREATE TABLE account
(
    acount_num INT(4) PRIMARY KEY NOT NULL,
    balance INT(5) NOT NULL,
    created_date TIMESTAMP NOT NULL
);
CREATE TABLE deposit
(
    Deposit_id INT(4) PRIMARY KEY NOT NULL,
    acount_num INT(4),
    value INT(5) NOT NULL,
    date TIMESTAMP NOT NULL,
    CONSTRAINT deposit_account_acount_num_fk FOREIGN KEY (acount_num) REFERENCES account (acount_num)
);
CREATE INDEX deposit_account_acount_num_fk ON deposit (acount_num);
CREATE TABLE transfer
(
    transfer_id INT(4) PRIMARY KEY NOT NULL,
    sender INT(4),
    receiver INT(4),
    value INT(5) NOT NULL,
    date TIMESTAMP NOT NULL,
    CONSTRAINT transfer_account_acount_num_fk FOREIGN KEY (sender) REFERENCES account (acount_num),
    CONSTRAINT transfer_account_acount_num_fk2 FOREIGN KEY (receiver) REFERENCES account (acount_num)
);
CREATE INDEX transfer_account_acount_num_fk ON transfer (sender);
CREATE INDEX transfer_account_acount_num_fk2 ON transfer (receiver);
CREATE TABLE withdraw
(
    Withdraw_id INT(4) PRIMARY KEY NOT NULL,
    acount_num INT(4),
    value INT(5) NOT NULL,
    date TIMESTAMP NOT NULL,
    CONSTRAINT withdraw_account_acount_num_fk FOREIGN KEY (acount_num) REFERENCES account (acount_num)
);
CREATE INDEX withdraw_account_acount_num_fk ON withdraw (acount_num);
CREATE TABLE users
(
    user_id INT(4) PRIMARY KEY NOT NULL,
    frist_name VARCHAR(25) NOT NULL,
    last_name VARCHAR(25) NOT NULL,
    phone VARCHAR(11) NOT NULL,
    emil VARCHAR(30) NOT NULL,
    age INT(2) NOT NULL,
    password VARCHAR(30) NOT NULL,
    acount_num INT(4),
    photo VARCHAR(200),
    CONSTRAINT customer_account_acount_num_fk FOREIGN KEY (acount_num) REFERENCES account (acount_num)
);
CREATE INDEX customer_account_acount_num_fk ON users (acount_num);
CREATE UNIQUE INDEX customer_emil_uindex ON users (emil);
CREATE UNIQUE INDEX customer_phone_uindex ON users (phone);
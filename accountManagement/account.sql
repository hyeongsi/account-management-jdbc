DROP USER account CASCADE;

CREATE USER account
IDENTIFIED BY account;

GRANT connect, resource to account;

drop sequence account.account_id_seq;
CREATE SEQUENCE account.account_id_seq;

DROP TABLE account.tb_account;
CREATE TABLE account.tb_account (
  accountId number PRIMARY KEY,
  name varchar2(20) not null,
  balance number default 0
);

INSERT INTO account.tb_account(accountId, name, balance)
VALUES (account.account_id_seq.nextval, '��ƹ���', 500);
INSERT INTO account.tb_account(accountId, name, balance)
VALUES (account.account_id_seq.nextval, 'ȫ�浿', 27000);
INSERT INTO account.tb_account(accountId, name, balance)
VALUES (account.account_id_seq.nextval, '�Ӳ���', 100000);
INSERT INTO account.tb_account(accountId, name, balance)
VALUES (account.account_id_seq.nextval, '����', 300000);
INSERT INTO account.tb_account(accountId, name, balance)
VALUES (account.account_id_seq.nextval, '�̼���', 16000000);
commit;

select * from account.tb_account;

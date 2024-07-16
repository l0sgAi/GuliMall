CREATE TABLE tb_user (
  id bigint NOT NULL,
  username varchar(50) NOT NULL,
  mobile varchar(20) NOT NULL,
  password varchar(64),
  create_date datetime,
  PRIMARY KEY (id)
);
CREATE UNIQUE INDEX idx_user_username on tb_user(username);

CREATE TABLE tb_token (
  id bigint NOT NULL,
  user_id bigint NOT NULL,
  token varchar(100) NOT NULL,
  expire_date datetime,
  update_date datetime,
  PRIMARY KEY (id)
);

CREATE UNIQUE INDEX idx_token_user_id on tb_token(user_id);
CREATE UNIQUE INDEX idx_token on tb_token(token);


-- 账号：13612345678  密码：admin
INSERT INTO tb_user (id, username, mobile, password, create_date) VALUES (1067246875800000168, 'mark', '13612345678', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', now());

commit;

CREATE TABLE IF NOT EXISTS m_user(
  user_id varchar(6),
  password varchar(30) NOT NULL,
  user_name varchar(20) NOT NULL,
  create_date timestamp,
  create_at varchar(6),
  updated_date timestamp,
  updated_at varchar(6),
  version integer NOT NULL,
  PRIMARY KEY(user_id)
);

CREATE TABLE IF NOT EXISTS t_parent_todo(
  p_todo_id varchar(36),
  p_todo_name varchar(20) NOT NULL,
  is_finished boolean NOT NULL,
  limit_date timestamp,
  finish_date timestamp,
  create_date timestamp,
  create_at varchar(6),
  updated_date timestamp,
  updated_at varchar(6),
  version integer NOT NULL,
  PRIMARY KEY(p_todo_id)
);

CREATE TABLE IF NOT EXISTS t_child_todo(
  c_todo_id varchar(36),
  c_todo_name varchar(20) NOT NULL,
  is_finished boolean NOT NULL,
  p_todo_id varchar(36) NOT NULL,
  limit_date timestamp,
  finish_date timestamp,
  create_date timestamp,
  create_at varchar(6),
  updated_date timestamp,
  updated_at varchar(6),
  version integer NOT NULL,
  PRIMARY KEY(c_todo_id)
);
create table if not exists users (
    id bigserial not null,
    username varchar not null,
    fio varchar not null,
    primary key (id),
    UNIQUE (username)
);

create table if not exists logins (
    id bigserial not null,
    access_date timestamp,
    user_id bigserial not null,
    application varchar,
    primary key (id)
);

--IF NOT EXISTS ( SELECT  constraint_schema
--                ,       constraint_name
--                FROM    pg_constraint --information_schema.check_constraints
--                WHERE   constraint_schema = 'logins'
--                  AND   constraint_name = 'fk_logins_users_user_id'
--              )
--THEN
--    alter table logins ADD CONSTRAINT fk_logins_users_user_id
--          FOREIGN KEY (user_id) REFERENCES users(id);
--END IF;


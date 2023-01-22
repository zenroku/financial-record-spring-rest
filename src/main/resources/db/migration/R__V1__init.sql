drop table if exists public.transaction_history;
drop table if exists public.user_wallet;
drop table if exists public."user";
drop table if exists public.wallet;

DROP SEQUENCE if exists user_id_seq;
DROP SEQUENCE if exists wallet_id_seq;
DROP SEQUENCE if exists user_wallet_id_seq;
DROP SEQUENCE if exists transaction_history_id_seq;

CREATE SEQUENCE user_id_seq;

create table if not exists public."user" (
    id bigint primary key default nextval('user_id_seq'),
    first_name varchar(255) not null,
    last_name varchar(255) null,
    email varchar(50) not null,
    password text null,
    created_datetime timestamp null,
    updated_datetime timestamp null,
    CONSTRAINT user_unique UNIQUE (email)
);

CREATE SEQUENCE wallet_id_seq;

create table if not exists public.wallet (
    id bigint primary key default nextval('wallet_id_seq'),
    name varchar(255) not null,
    created_datetime timestamp default now(),
    updated_datetime timestamp default now(),
    CONSTRAINT wallet_unique UNIQUE (name)
);

insert into wallet (name) values ('GOPAY');
insert into wallet (name) values ('BCA');
insert into wallet (name) values ('BSI');
insert into wallet (name) values ('OVO');

CREATE SEQUENCE user_wallet_id_seq;

create table if not exists public.user_wallet (
    id bigint primary key default nextval('user_wallet_id_seq'),
    user_id bigint not null references public.user(id),
    wallet_id bigint not null references public.wallet(id),
    balance numeric(38,2) default 0,
    created_datetime timestamp default now(),
    updated_datetime timestamp default now()
);

CREATE SEQUENCE transaction_history_id_seq;

create table if not exists public.transaction_history (
    id bigint primary key default nextval('transaction_history_id_seq'),
    user_wallet_id bigint not null references public.user_wallet(id),
    action_type varchar(10) not null,
    description text not null,
    current_balance numeric(38,2) default 0,
    action_balance numeric(38,2) default 0,
    result_balance numeric(38,2) default 0,
    transaction_datetime timestamp default null,
    created_datetime timestamp default now(),
    updated_datetime timestamp default now()
);


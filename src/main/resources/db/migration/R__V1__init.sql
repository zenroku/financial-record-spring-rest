drop table if exists public.transaction_history;
drop table if exists public.user_wallet;
drop table if exists public."user";
drop table if exists public.wallet;

create table if not exists public."user" (
    id serial primary key,
    first_name varchar(255) not null,
    last_name varchar(255) null,
    email varchar(50) not null,
    password text null,
    created_datetime timestamp null,
    updated_datetime timestamp null,
    CONSTRAINT user_unique UNIQUE (email)
);

create table if not exists public.wallet (
    id serial primary key,
    name varchar(255) not null,
    created_datetime timestamp default now(),
    updated_datetime timestamp default now(),
    CONSTRAINT wallet_unique UNIQUE (name)
);

insert into wallet (name) values ('GOPAY');
insert into wallet (name) values ('BCA');
insert into wallet (name) values ('BSI');
insert into wallet (name) values ('OVO');

create table if not exists public.user_wallet (
    id serial primary key,
    user_id serial not null references public.user(id),
    wallet_id serial not null references public.wallet(id),
    balance bigint not null default 0,
    created_datetime timestamp default now(),
    updated_datetime timestamp default now()
);

create table if not exists public.transaction_history (
    id serial primary key,
    user_wallet_id serial not null references public.user_wallet(id),
    action_type varchar(10) not null,
    description text not null,
    balance bigint not null,
    created_datetime timestamp default now(),
    updated_datetime timestamp default now()
);
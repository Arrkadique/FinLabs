--liquibase formatted sql
--changeset User:1

create table users
(
    id bigserial primary key,
    username varchar not null,
    last_name varchar not null,
    password varchar not null,
    phone_number varchar not null,
    constraint role_id foreign key (id) references role (id),
    is_blocked boolean not null default false,
);

create table role
(
    id bigserial primary key,
    role varchar not null
);

create table logs
(
    id bigserial primary key,
    action_date date not null,
    action varchar not null
);

create table transactions
(
    id bigserial primary key,
    constraint user_id_from foreign key (id) references users(id),
    constraint user_id_to foreign key (id) references users(id),
    sum bigserial not null,
    transaction_date date not null,
    constraint card_id_from foreign key (id) references user_cards(id),
    constraint card_id_to foreign key (id) references user_cards(id),
    status varchar not null default approved,
);

create table user_cards
(
    id bigserial primary key,
    constraint user_id foreign key (id) references users(id),
    card_number int not null,
    card date not null,
    cvv int not null
);

create table user_history
(
    id bigserial primary key,
    constraint user_id foreign key (id) references users(id),
    username varchar not null,
    action_date date not null,
    action varchar not null
);

create table card_balance
(
    id bigserial primary key,
    constraint card_id foreign key (id) references user_cards(id),
    balance float not null
);

create table credit
(
    id bigserial primary key,
    credit_sum float not null,
    payment_date date not null,
    percent float not null,
    constraint user_id foreign key (id) references users(id),
    constraint user_card_id foreign key (id) references user_cards(id),
    is_approved boolean not null default false
);
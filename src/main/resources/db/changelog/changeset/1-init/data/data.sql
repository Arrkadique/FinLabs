--liquibase formatted sql
--changeset User:1

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

create table users
(
    id bigserial primary key,
    email varchar not null,
    username varchar not null,
    last_name varchar not null,
    password varchar not null,
    phone_number varchar not null,
    role_id int not null,
    foreign key (role_id) references role (id),
    code varchar not null,
    is_blocked boolean not null default false
);

create table user_cards
(
    id bigserial primary key,
    user_id bigserial not null,
    foreign key (user_id) references users(id),
    card_number varchar not null,
    card_date varchar not null,
    cvv varchar not null,
    balance float not null
);

create table card_balance
(
    id bigserial primary key,
    card_id bigserial not null,
    foreign key (card_id) references user_cards(id),
    balance float not null
);

create table transactions
(
    id bigserial primary key,
    user_id_from bigserial not null,
    foreign key (user_id_from) references users(id),
    user_id_to bigserial not null,
    foreign key (user_id_to) references users(id),
    sum bigserial not null,
    transaction_date date not null,
    card_id_from bigserial not null,
    foreign key (card_id_from) references user_cards(id),
    card_id_to bigserial not null,
    foreign key (card_id_to) references user_cards(id),
    status varchar not null default true
);

create table user_history
(
    id bigserial primary key,
    user_id bigserial not null,
    foreign key (user_id) references users(id),
    username varchar not null,
    action_date date not null,
    action varchar not null
);

create table credit
(
    id bigserial primary key,
    credit_sum float not null,
    payment_date int not null,
    percent float not null,
    user_id bigserial not null,
    foreign key (user_id) references users(id),
    card_id bigserial references user_cards(id) not null,
    is_approved boolean not null default false
);
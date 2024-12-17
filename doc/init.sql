-- we don't know how to generate root <with-no-name> (class Root) :(

grant select on performance_schema.* to 'mysql.session'@localhost;

grant trigger on sys.* to 'mysql.sys'@localhost;

grant alter, alter routine, application_password_admin, audit_abort_exempt, audit_admin, authentication_policy_admin, backup_admin, binlog_admin, binlog_encryption_admin, clone_admin, connection_admin, create, create role, create routine, create tablespace, create temporary tables, create user, create view, delete, drop, drop role, encryption_key_admin, event, execute, file, firewall_exempt, flush_optimizer_costs, flush_status, flush_tables, flush_user_resources, group_replication_admin, group_replication_stream, index, innodb_redo_log_archive, innodb_redo_log_enable, insert, lock tables, passwordless_user_admin, persist_ro_variables_admin, process, references, reload, replication client, replication slave, replication_applier, replication_slave_admin, resource_group_admin, resource_group_user, role_admin, select, sensitive_variables_observer, service_connection_admin, session_variables_admin, set_user_id, show databases, show view, show_routine, shutdown, super, system_user, system_variables_admin, table_encryption_admin, telemetry_log_admin, trigger, update, xa_recover_admin, grant option on *.* to admin;

grant alter, alter routine, application_password_admin, audit_admin, authentication_policy_admin, backup_admin, binlog_admin, binlog_encryption_admin, clone_admin, connection_admin, create, create role, create routine, create tablespace, create temporary tables, create user, create view, delete, drop, drop role, encryption_key_admin, event, execute, file, flush_optimizer_costs, flush_status, flush_tables, flush_user_resources, group_replication_admin, group_replication_stream, index, innodb_redo_log_archive, innodb_redo_log_enable, insert, lock tables, passwordless_user_admin, persist_ro_variables_admin, process, references, reload, replication client, replication slave, replication_applier, replication_slave_admin, resource_group_admin, resource_group_user, role_admin, select, sensitive_variables_observer, service_connection_admin, session_variables_admin, set_user_id, show databases, show view, show_routine, shutdown, super, system_user, system_variables_admin, table_encryption_admin, telemetry_log_admin, trigger, update, xa_recover_admin, grant option on *.* to 'debian-sys-maint'@localhost;

grant audit_abort_exempt, firewall_exempt, select, system_user on *.* to 'mysql.infoschema'@localhost;

grant audit_abort_exempt, authentication_policy_admin, backup_admin, clone_admin, connection_admin, firewall_exempt, persist_ro_variables_admin, session_variables_admin, shutdown, super, system_user, system_variables_admin on *.* to 'mysql.session'@localhost;

grant audit_abort_exempt, firewall_exempt, system_user on *.* to 'mysql.sys'@localhost;

grant alter, alter routine, application_password_admin, audit_abort_exempt, audit_admin, authentication_policy_admin, backup_admin, binlog_admin, binlog_encryption_admin, clone_admin, connection_admin, create, create role, create routine, create tablespace, create temporary tables, create user, create view, delete, drop, drop role, encryption_key_admin, event, execute, file, firewall_exempt, flush_optimizer_costs, flush_status, flush_tables, flush_user_resources, group_replication_admin, group_replication_stream, index, innodb_redo_log_archive, innodb_redo_log_enable, insert, lock tables, passwordless_user_admin, persist_ro_variables_admin, process, references, reload, replication client, replication slave, replication_applier, replication_slave_admin, resource_group_admin, resource_group_user, role_admin, select, sensitive_variables_observer, service_connection_admin, session_variables_admin, set_user_id, show databases, show view, show_routine, shutdown, super, system_user, system_variables_admin, table_encryption_admin, telemetry_log_admin, trigger, update, xa_recover_admin, grant option on *.* to root@localhost;

create table Book
(
    id          bigint auto_increment
        primary key,
    isbn        varchar(20)                                              not null,
    book_name   varchar(255)                                             not null,
    author      varchar(255)                                             null,
    press       varchar(255)                                             null,
    press_date  date                                                     null,
    press_place varchar(255)                                             null,
    price       decimal(10, 2)                                           null,
    pictures    text                                                     null,
    clc_code    varchar(20)                                              null,
    clc_name    varchar(255)                                             null,
    book_desc   text                                                     null,
    binding     enum ('paperback', 'hardcover')                          null,
    language    varchar(20)                                              null,
    format      varchar(50)                                              null,
    status      enum ('available', 'borrowed') default 'available'       null,
    created_at  timestamp                      default CURRENT_TIMESTAMP null,
    updated_at  timestamp                      default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    constraint isbn
        unique (isbn)
);

create table User
(
    id         bigint auto_increment
        primary key,
    openid     varchar(64)                                      not null,
    phone      varchar(20)                                      null,
    role       enum ('user', 'admin') default 'user'            null,
    created_at timestamp              default CURRENT_TIMESTAMP null,
    updated_at timestamp              default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    nickname   varchar(255)                                     null,
    avatar     text                                             null,
    constraint openid
        unique (openid),
    constraint phone
        unique (phone)
);

create table Donation
(
    id         bigint auto_increment
        primary key,
    admin_id   bigint                              not null,
    book_id    bigint                              not null,
    book_desc  text                                null,
    created_at timestamp default CURRENT_TIMESTAMP null,
    status     int                                 not null,
    isbn       varchar(20)                         not null,
    constraint Donation_ibfk_1
        foreign key (admin_id) references User (id),
    constraint Donation_ibfk_2
        foreign key (book_id) references Book (id)
);

create index admin_id
    on Donation (admin_id);

create index book_id
    on Donation (book_id);

create table Review
(
    id         bigint auto_increment
        primary key,
    user_id    bigint                              not null,
    book_id    bigint                              not null,
    content    text                                null,
    rating     int                                 null,
    created_at timestamp default CURRENT_TIMESTAMP null,
    constraint Review_ibfk_1
        foreign key (user_id) references User (id),
    constraint Review_ibfk_2
        foreign key (book_id) references Book (id),
    check (`rating` between 1 and 5)
);

create index book_id
    on Review (book_id);

create index user_id
    on Review (user_id);

create table Transaction
(
    id          bigint auto_increment
        primary key,
    user_id     bigint                                                   not null,
    book_id     bigint                                                   not null,
    borrow_date date                                                     not null,
    return_date date                                                     null,
    status      enum ('borrowing', 'returned') default 'borrowing'       null,
    created_at  timestamp                      default CURRENT_TIMESTAMP null,
    updated_at  timestamp                      default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    constraint Transaction_ibfk_1
        foreign key (user_id) references User (id),
    constraint Transaction_ibfk_2
        foreign key (book_id) references Book (id)
);

create index book_id
    on Transaction (book_id);

create index user_id
    on Transaction (user_id);


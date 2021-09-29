create table details (id bigint not null auto_increment, amount double precision, description varchar(255), line_id integer, ticket_id bigint, primary key (id)) engine=InnoDB;
create table tickets (id bigint not null auto_increment, created_date datetime not null, total_amount double precision, primary key (id)) engine=InnoDB;
alter table details add constraint FKgmx3tf85uavvfy920cfusk94q foreign key (ticket_id) references tickets (id);

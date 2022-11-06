create table deliver_event (
	id bigint not null auto_increment,
	deliver_id bigint not null,
	description text not null,
	event_date_time datetime not null,
	
	
	primary key (id)
);

alter table deliver_event add constraint fk_deliver_event_deliver
foreign key (deliver_id) references deliver (id);

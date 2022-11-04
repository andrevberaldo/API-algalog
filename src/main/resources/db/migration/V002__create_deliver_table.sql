create table deliver (
	id bigint not null auto_increment,
	customer_id bigint not null,
	fee decimal(10,2) not null,
	status varchar(20) not null,
	created_at datetime not null,
	concluded_at datetime,
	
	recipient_name varchar(60) not null,
	recipient_address varchar(150) not null,
	recipient_number varchar(20) not null,
	recipient_city_state_zipcode varchar(50) not null,
	
	primary key (id)
);

alter table deliver add constraint fk_deliver_customer
foreign key (customer_id) references customer (id);

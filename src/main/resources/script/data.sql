create table if not exists user_tb(
    id serial primary key ,
    email varchar(70) not null ,
    password varchar(70) not null ,
    role varchar(70) not null
);

create table if not exists category_tb(
    id serial primary key ,
    name varchar(50) not null ,
    date timestamp not null ,
    user_id INT not null  ,
    foreign key (user_id) references user_tb(id) on delete cascade on update cascade
);

create table if not exists task_tb(
  id serial primary key ,
  name varchar(70) not null ,
  description varchar(100) not null ,
  date timestamp not null ,
  status varchar(40) not null ,
  user_id INT not null ,
  category_id INT not null ,
  foreign key (user_id) references user_tb(id) on delete cascade on update cascade ,
  foreign key (category_id) references category_tb(id) on delete cascade on update cascade
);
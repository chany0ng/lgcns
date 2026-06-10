create table User (
                      createdAt datetime(6) default CURRENT_TIMESTAMP(6),
                      id int unsigned not null auto_increment,
                      updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP null,
                      telno varchar(12),
                      nickname varchar(31) not null,
                      email varchar(255) not null,
                      passwd varchar(255),
                      bloodType enum ('A','AB','B','O') default 'A',
                      primary key (id)
) engine=InnoDB
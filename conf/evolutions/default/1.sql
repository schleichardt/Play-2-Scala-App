# --- First database schema

# --- !Ups

set ignorecase true;

create table person (
  id                        bigint not null,
  firstname                 varchar(255) not null,
  lastname                  varchar(255) not null,
  constraint pk_person primary key (id))
;


# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists person;

SET REFERENTIAL_INTEGRITY TRUE;


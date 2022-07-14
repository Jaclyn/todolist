-- public.task definition

-- Drop table

-- DROP TABLE public.task;

CREATE TABLE public.task (
    id int4 NOT NULL,
    description varchar(255) NOT NULL,
    creation_date timestamp NOT NULL,
    last_updated_date timestamp NOT NULL,
    status varchar(1) NOT NULL,
    "owner" varchar(35) NULL,
    CONSTRAINT task_pkey PRIMARY KEY (id)
);

CREATE SEQUENCE task_id
INCREMENT 1
START 1;
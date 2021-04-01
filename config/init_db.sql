CREATE TABLE resume (
                        uuid      CHAR(36) PRIMARY KEY NOT NULL,
                        full_name TEXT                 NOT NULL
);

CREATE TABLE contact (
                         id          SERIAL,
                         resume_uuid CHAR(36) NOT NULL REFERENCES resume (uuid) ON DELETE CASCADE,
                         type        TEXT     NOT NULL,
                         value       TEXT     NOT NULL
);
create table sections
(
    id serial not null
        constraint sections_pk
            primary key,
    resume_uuid char(36)
        constraint sections_resume_uuid_fk
            references resume
            on update restrict on delete cascade,
    type text not null,
    content text not null
);

alter table sections owner to postgres;

CREATE UNIQUE INDEX contact_uuid_type_index
    ON contact (resume_uuid, type);
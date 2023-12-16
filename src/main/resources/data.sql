delete from item_permission_group WHERE exists (SELECT * FROM item_permission_group);
delete from permissions WHERE exists(SELECT * FROM permissions);
delete from permission_group WHERE exists(SELECT * FROM permission_group);

insert into permission_group
values (1, 'admin');



insert into permissions (permission_level, user_email, group_id)
values ('EDIT', 'ahmed@hotmail.com', 1);
insert into permissions (permission_level, user_email, group_id)
values ('VIEW', 'hassan@hotmail.com', 1);

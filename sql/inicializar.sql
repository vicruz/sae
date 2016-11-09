create table sae.usuario(
	id int not null auto_increment,
    usuario varchar(80),
    password varchar(80),
    email varchar(50),
    rol_id int default 2,
    fecha_alta timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    fecha_modificacion timestamp,
    primary key (id)
)charset=utf8;

create unique index IDX_USER on sae.usuario(usuario);

create table sae.rol(
	id int not null auto_increment,
    name varchar(80),
    primary key (id)
)charset=utf8;

create unique index IDX_ROL on sae.rol(name);

create table sae.alumno(
	id int not null auto_increment,
	ap_Paterno varchar(60),
    ap_Materno varchar(60),
    nombre varchar(60),
    fecha_Ingreso timestamp default current_timestamp,
    grado_id int,
    semaforo_id int,
    beca_id int default null,
    primary key(id)
)charset=utf8;

create table sae.grado(
	id int not null auto_increment,
    name varchar(30),
    primary key(id)
)charset=utf8;

create table sae.semaforo(
	id int not null auto_increment,
    name varchar(30),
    primary key(id)
)charset=utf8;


commit;


create table sae.cat_pagos(
	id int not null auto_increment,
    concepto varchar(50),
    monto decimal(7,2),
    fecha_alta timestamp default current_timestamp,
    primary key(id)
)charset=utf8;

create table sae.pago_grado(
	id int not null auto_increment,
	id_pago int not null,
    id_grado int not null,
    fecha_limite date,
    mes_corresponde int,
    anio_corresponde int,
    primary key(id)
)charset=utf8;

alter table sae.pago_grado add constraint foreign key (id_pago) references cat_pagos(id);

create unique index IDX_PAGO_ROL on sae.pago_grado(id_pago,id_grado);

create table sae.alumno_pago(
	id int not null auto_increment,
	id_alumno int not null,
    id_pago_grado int,
    monto decimal(7,2),
    pago decimal(7,2),
    fecha_pago timestamp not null default current_timestamp,
    id_semaforo int default 3,
    primary key(id)
)charset=utf8;

alter table sae.alumno_pago add constraint foreign key (id_pago_grado) references pago_grado(id);

/*create unique index IDX_PAGO_ROL on sae.pago_grado(id_alumno,id_grado);*/
    


/*Demo scripts*/


insert into sae.semaforo (name) values ('Pagado');
insert into sae.semaforo (name) values ('Parcial');
insert into sae.semaforo (name) values ('Adeudo');

insert into sae.grado (name) values ('Kinder 1');
insert into sae.grado (name) values ('Kinder 2');
insert into sae.grado (name) values ('Kinder 3');
insert into sae.grado (name) values ('Primaria 1');
insert into sae.grado (name) values ('Primaria 2');
insert into sae.grado (name) values ('Primaria 3');
insert into sae.grado (name) values ('Primaria 4');
insert into sae.grado (name) values ('Primaria 5');
insert into sae.grado (name) values ('Primaria 6');

insert into sae.rol (name) values ('admin');
insert into sae.rol (name) values ('user');

insert into sae.cat_pagos (concepto,monto) values ('Inscripción',1000);
insert into sae.cat_pagos (concepto,monto) values ('Inscripción',1250);
insert into sae.cat_pagos (concepto,monto) values ('Colegiatura',1000);
insert into sae.cat_pagos (concepto,monto) values ('Colegiatura',1250);
insert into sae.cat_pagos (concepto,monto) values ('Material',450);
insert into sae.cat_pagos (concepto,monto) values ('Material',250);
insert into sae.cat_pagos (concepto,monto) values ('Club Tareas',350);
insert into sae.cat_pagos (concepto,monto) values ('Inglés',700);

insert into sae.pago_grado (id_pago,id_grado,fecha_limite,mes_corresponde,anio_corresponde) values (1,1,'2016-11-05',11,2016);
insert into sae.pago_grado (id_pago,id_grado,fecha_limite,mes_corresponde,anio_corresponde) values (1,2,'2016-11-05',11,2016);
insert into sae.pago_grado (id_pago,id_grado,fecha_limite,mes_corresponde,anio_corresponde) values (1,3,'2016-11-05',11,2016);
insert into sae.pago_grado (id_pago,id_grado,fecha_limite,mes_corresponde,anio_corresponde) values (2,4,'2016-11-05',11,2016);
insert into sae.pago_grado (id_pago,id_grado,fecha_limite,mes_corresponde,anio_corresponde) values (2,5,'2016-11-05',11,2016);
insert into sae.pago_grado (id_pago,id_grado,fecha_limite,mes_corresponde,anio_corresponde) values (2,6,'2016-11-05',11,2016);
insert into sae.pago_grado (id_pago,id_grado,fecha_limite,mes_corresponde,anio_corresponde) values (2,7,'2016-11-05',11,2016);
insert into sae.pago_grado (id_pago,id_grado,fecha_limite,mes_corresponde,anio_corresponde) values (2,8,'2016-11-05',11,2016);
insert into sae.pago_grado (id_pago,id_grado,fecha_limite,mes_corresponde,anio_corresponde) values (2,9,'2016-11-05',11,2016);

insert into sae.pago_grado (id_pago,id_grado,fecha_limite,mes_corresponde,anio_corresponde) values (3,1,'2016-11-05',11,2016);
insert into sae.pago_grado (id_pago,id_grado,fecha_limite,mes_corresponde,anio_corresponde) values (3,2,'2016-11-05',11,2016);
insert into sae.pago_grado (id_pago,id_grado,fecha_limite,mes_corresponde,anio_corresponde) values (3,3,'2016-11-05',11,2016);
insert into sae.pago_grado (id_pago,id_grado,fecha_limite,mes_corresponde,anio_corresponde) values (4,4,'2016-11-05',11,2016);
insert into sae.pago_grado (id_pago,id_grado,fecha_limite,mes_corresponde,anio_corresponde) values (4,5,'2016-11-05',11,2016);
insert into sae.pago_grado (id_pago,id_grado,fecha_limite,mes_corresponde,anio_corresponde) values (4,6,'2016-11-05',11,2016);
insert into sae.pago_grado (id_pago,id_grado,fecha_limite,mes_corresponde,anio_corresponde) values (4,7,'2016-11-05',11,2016);
insert into sae.pago_grado (id_pago,id_grado,fecha_limite,mes_corresponde,anio_corresponde) values (4,8,'2016-11-05',11,2016);
insert into sae.pago_grado (id_pago,id_grado,fecha_limite,mes_corresponde,anio_corresponde) values (4,9,'2016-11-05',11,2016);
insert into sae.pago_grado (id_pago,id_grado,fecha_limite,mes_corresponde,anio_corresponde) values (5,1,'2016-11-05',11,2016);
insert into sae.pago_grado (id_pago,id_grado,fecha_limite,mes_corresponde,anio_corresponde) values (5,2,'2016-11-05',11,2016);
insert into sae.pago_grado (id_pago,id_grado,fecha_limite,mes_corresponde,anio_corresponde) values (5,3,'2016-11-05',11,2016);
insert into sae.pago_grado (id_pago,id_grado,fecha_limite,mes_corresponde,anio_corresponde) values (6,4,'2016-11-05',11,2016);
insert into sae.pago_grado (id_pago,id_grado,fecha_limite,mes_corresponde,anio_corresponde) values (6,5,'2016-11-05',11,2016);
insert into sae.pago_grado (id_pago,id_grado,fecha_limite,mes_corresponde,anio_corresponde) values (6,6,'2016-11-05',11,2016);
insert into sae.pago_grado (id_pago,id_grado,fecha_limite,mes_corresponde,anio_corresponde) values (6,7,'2016-11-05',11,2016);
insert into sae.pago_grado (id_pago,id_grado,fecha_limite,mes_corresponde,anio_corresponde) values (6,8,'2016-11-05',11,2016);
insert into sae.pago_grado (id_pago,id_grado,fecha_limite,mes_corresponde,anio_corresponde) values (6,9,'2016-11-05',11,2016);
insert into sae.pago_grado (id_pago,id_grado,fecha_limite,mes_corresponde,anio_corresponde) values (7,1,'2016-11-05',11,2016);
insert into sae.pago_grado (id_pago,id_grado,fecha_limite,mes_corresponde,anio_corresponde) values (7,2,'2016-11-05',11,2016);
insert into sae.pago_grado (id_pago,id_grado,fecha_limite,mes_corresponde,anio_corresponde) values (7,3,'2016-11-05',11,2016);
insert into sae.pago_grado (id_pago,id_grado,fecha_limite,mes_corresponde,anio_corresponde) values (7,4,'2016-11-05',11,2016);
insert into sae.pago_grado (id_pago,id_grado,fecha_limite,mes_corresponde,anio_corresponde) values (7,5,'2016-11-05',11,2016);
insert into sae.pago_grado (id_pago,id_grado,fecha_limite,mes_corresponde,anio_corresponde) values (7,6,'2016-11-05',11,2016);
insert into sae.pago_grado (id_pago,id_grado,fecha_limite,mes_corresponde,anio_corresponde) values (7,7,'2016-11-05',11,2016);
insert into sae.pago_grado (id_pago,id_grado,fecha_limite,mes_corresponde,anio_corresponde) values (7,8,'2016-11-05',11,2016);
insert into sae.pago_grado (id_pago,id_grado,fecha_limite,mes_corresponde,anio_corresponde) values (7,9,'2016-11-05',11,2016);
insert into sae.pago_grado (id_pago,id_grado,fecha_limite,mes_corresponde,anio_corresponde) values (8,1,'2016-11-05',11,2016);
insert into sae.pago_grado (id_pago,id_grado,fecha_limite,mes_corresponde,anio_corresponde) values (8,2,'2016-11-05',11,2016);
insert into sae.pago_grado (id_pago,id_grado,fecha_limite,mes_corresponde,anio_corresponde) values (8,3,'2016-11-05',11,2016);
insert into sae.pago_grado (id_pago,id_grado,fecha_limite,mes_corresponde,anio_corresponde) values (8,4,'2016-11-05',11,2016);
insert into sae.pago_grado (id_pago,id_grado,fecha_limite,mes_corresponde,anio_corresponde) values (8,5,'2016-11-05',11,2016);
insert into sae.pago_grado (id_pago,id_grado,fecha_limite,mes_corresponde,anio_corresponde) values (8,6,'2016-11-05',11,2016);
insert into sae.pago_grado (id_pago,id_grado,fecha_limite,mes_corresponde,anio_corresponde) values (8,7,'2016-11-05',11,2016);
insert into sae.pago_grado (id_pago,id_grado,fecha_limite,mes_corresponde,anio_corresponde) values (8,8,'2016-11-05',11,2016);
insert into sae.pago_grado (id_pago,id_grado,fecha_limite,mes_corresponde,anio_corresponde) values (8,9,'2016-11-05',11,2016);
commit;

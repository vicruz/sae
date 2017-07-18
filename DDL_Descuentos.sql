create table sae.alumno_descuento(
	id_descuento int not null auto_increment,
	id_alumno int not null,
    descuento decimal(5,2),
    fecha_inicio date,
    fecha_fin date,
    activo int(1) default true,
    primary key(id_descuento)
)charset=utf8;
alter table sae.alumno_descuento add constraint foreign key FK_ALUMNO_DESCUENTO (id_alumno) references sae.alumno(id);
commit;
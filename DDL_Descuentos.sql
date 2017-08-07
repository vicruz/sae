alter table sae.alumno add activo int(1) default 1;
alter table sae.cat_pagos add genera_adeudo int(1) default 0;
alter table sae.cat_pagos add pago_unico int(1) default 0;
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

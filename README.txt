# README.txt
#
# Copyright (C) 2012-2021 Rafael Corchuelo.
#
# In keeping with the traditional purpose of furthering education and research, it is
# the policy of the copyright owner to permit non-commercial use and redistribution of
# this software. It has been tested carefully, but it is not guaranteed for any particular
# purposes.  The copyright owner does not offer any warranties or representations, nor do
# they accept any liabilities with respect to them.

This is the Acme-Planner project of the june#16 development team.

Link GitHub repository: https://github.com/serrojjim/dp2-june-16
Link GitHub release:https://github.com/serrojjim/dp2-june-16/releases/tag/1.1.1


A continuación, detallaremos las distintas decisiones que hemos tomado sobre aspectos que no quedaban del todo claro en el documento de requisitos:

- Consideramos una entidad (shout, task o workplan) como spam si en algún campo de texto que posea se supera el umbral de spam. Otra foma de verlo seria contar la entidad con todos sus campos de texto y luego aplicarle el filtro de spam en general. Nos hemos decantado por la primera opción ya que vemos que es más lógico pensar que algo se considera spam si, por ejemplo, el título supera el umbral (por lo tanto es spam ese campo) pero la descripción contine palabras válidas que hacen que en conjunto se equilibre y no pase el umbral, nosotros consideramos que no se debería permitir su creación. Dicho de otro modo, si se quiere evitar el Spam, no se debe dejar ningún camino posible abierto. Si alguien quisiera meter Spam, bastaria con meter en el titulo, por ejemplo, "sex sex sex" y en el texto 100 caracteres, por ejemplo, "s s s s s s ....". Con esto, dicha persona habría conseguido meter Spam sin impedimento ninguno de la página.

- En cuanto a la fecha de creación de los workplan, nosotros hemos considerado oportuno que se puedan crear workplan en el pasado. Con esto, permitimos más flexibilidad a la hora de crear los workplan pudiendo añadir tareas anteriormente creadas a las que se harán en un futuro, las cuales pertenecen a un mismo plan de trabajo que se quiere crear. Vemos más conveniente que los wokplan se adapten a las tareas que contiene.

- Para la actualización de las tareas, hemos tomado la decisión de que mientras no se cambie la fecha de inicio en la tarea, permita actualizar todos sus demás campos sin problemas. En el momento que se cambie la fecha inicio de una tarea se activará la validación de esta. Con esto evitamos el problema que ocurría al editar una tarea en la cual la fecha de inicio estaba ya en el pasado nunca pasara la validación de ese formulario, impidiendo la actualización de los demás campos.

- Respecto al campo título en la entidad workplan, aunque no viene especificado en el docuemento de requisistos que lo tenga. Hemos visto conveniente la inclusión de este campo de texto ya que así permitimos que cualquier persona que use la aplicación pueda identificar a un workplan de manera sencilla.

-El criterio para que un workplan esté publicado es que no contenga tareas que sean consideradas spam, que su título tampoco sea considerado spam y que el propio workplan sea público. Se permite la adición de tareas un workplan una vez está publicado, ya que si se ha realizado una mala estimación de tareas a comienzos de sprint y una tarea resulta ser demasiado grande y se necesita dividir en dos, se deberían poder modificar las tareas del workplan.

-El criterio para que se publique una tarea es que en dicha tarea tanto el titulo como la descripcion no sean consideradas spam. Además se tiene
que cumplir que la carga de trabajo de dicha tarea no supere al total de horas compredidas entre la fecha inicial y la fecha final de la tarea. Por última instancia las fecha fin tiene que ser superior a la fecha de inicio cumpliendose que la de inicio no esté en el pasado.

-Los test relativos al listado de las entidades de un usuario anónimo solo han sido realizado su caso positivo, ya que consideramos que no es posible listar de forma no deseada estas funcionalidades.


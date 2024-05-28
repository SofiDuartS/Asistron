# Asistron

SOLID VISTAS

Los principios SOLID son aplicables al desarrollo de interfaces de usuario (vistas) en Android, ya que promueven la modularidad, la separación de responsabilidades y la facilidad de mantenimiento del código.

1. Principio de Responsabilidad Única (SRP): Cada componente de la vista debe tener una responsabilidad bien definida. Por ejemplo, la vista para registrar horario, es exclusivamente para ello.
2. Principio de Abierto/Cerrado (OCP): Las vistas deben estar abiertas para su extensión, pero cerradas para su modificación. Esto se puede lograr mediante el patron de arquitectura escogido, ya que al llenar el formulario, por medio del modelo se sube a la base de datos.
3. Principio de Sustitución de Liskov (LSP): Como las vistas no tienen subclases, este principio no aplica.
4. Principio de Segregación de Interfaces (ISP): Las interfaces de los componentes de vista deben ser específicas y no obligar a los usuarios a depender de métodos o propiedades que no necesitan. Por ejemplo, en lugar de tener una gran interfaz para el modulo en general, se divide en cada actividad como Modificar, Crear, Inhabilitar y Visualizar.
5. Principio de Inversión de Dependencias (DIP): Como solo es un módulo, este principio no aplica.

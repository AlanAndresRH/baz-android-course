# Reto de Criptomonedas

## Introducción
¡Gracias por participar en el programa de aprendizaje de Android!
Aquí encontraras las instrucciones del desafío.

## El Desafío
El propósito de este desafío es que puedas demostrar tus habilidades de desarrollo en Android. Esta es tu oportunidad para demostrar todo lo que has aprendido durante el curso.
En este desafío, construirás una aplicación de Android completa por tu propia cuenta. No queremos limitarte, por eso, mejor solicitamos que construyas una aplicación desde cero.
Esperamos que encuentres este ejercicio desafiante y llamativo.
El propósito es construir una aplicación que use la API pública de Bitso, la cuál debe incluir:

- Una pantalla que muestre todas las monedas disponibles
- Una pantalla de detalle para cada moneda
- Persistencia local

## Requerimientos

Estos son los requerimientos principales que evaluaremos:

- Hacer uso de todo lo que has aprendido durante el curso:
    - Mejores prácticas
    - Diseño de API
    - Diseño de UI
    - Patrones de diseño

## Enviando los entregables

Para publicar tu trabajo, deberás seguir estos pasos:

1. Crear un `pull request` con tu código, apuntando a la rama `master`
2. Llenar este [formato](https://forms.gle/Xhc38ydorhDZkGSy6)
3. Mantente al pendiente de la retroalimentación.
4. Genera los cambios conforme a los comentarios de tu mentor.

## Para empezar

Para empezar, sigue los siguientes pasos:

1. Realiza `Fork` a este proyecto
2. Convierte tu proyecto en privado
3. Concede accesos a este proyecto a tu mentor
4. Genera `commit` y sube tus cambios de manera periódica
5. Realiza los cambios según los comentarios de tu mentor
6. ¡Diviértete!

## Entregables

Proporcionamos las fechas de entrega para que pueda organizarse; por favor, tome este desafío con seriedad e intente progresar constantemente.
Vale la pena mencionar que solamente podrás obtener retroalimentación del equipo de revisión para tu primera entregable, de tal manera tendrás la oportunidad de corregir o mejorar tu código según nuestras sugerencias.
Para el último entregable, proporcionaremos cierta retroalimentación, pero ya no habrá una última revisión posterior a ello. Si estás teniendo conflictos con algo, contacta a tu menor o cualquier encargada para obtener ayuda a tiempo. Siéntete libre de usar el canal de Slack.

## Primer Entregable
Con base en el material de autoestudio y las mentorías hasta este entregable, sugerimos que desarrolles lo siguiente:

- Crear un cliente de red para poder consumir los siguientes servicios haciendo uso de `GsonAdapter` para obtener las clases de manera sencilla:
  - https://bitso.com/api_info#available-books
  - https://bitso.com/api_info#ticker
  - https://bitso.com/api_info#order-book 
- Busca imágenes que representen las monedas y agrega dichas imágenes a la lista de monedas.
- Trabaja con MVVM y LiveData para poder diseñar la aplicación:
    - Crear una pantalla que va a mostrar una lista de las monedas utilizando el servicio `available-books`
    - Crear una pantalla de detalle que mostrará el último precio, el más alto y el más bajo. Además, mostrar una lista de `bids` y `asks` haciendo uso del servicio `order-book`
- Hacer uso de buenas prácticas

### **Fecha de Entrega 3 de Febrero**

> Nota: la lista anterior de este entregable es sólo una guía para ayudarte a distribuir la carga de trabajo; puedes entregar más o menos elementos si es necesario. De igual manera, si entregas menos elementos en este punto, tendrás que cubrir los elementos restantes en el siguiente entregable.

## Segundo Entregable

Con base en el material de autoestudio y las mentorías hasta este entregable, sugerimos que desarrolles lo siguiente:

- Agregar persistencia utilizando `Room` para poder usar la aplicación cuando no se cuente con conexión a internet.
- Agregar la librería `OkHttp` para hacer uso del `HttpLoggingInterceptor` para todos los eventos de red, y además añadir el header `User-Agent`
- Implementar `function type`, `lambdas` y `extension function`
- Agregar algunas pruebas unitarias y de vista.
- Hacer _refactor_ de la vista, actualiza tus layouts haciendo uso de `ConstraintLayout`
- Hacer uso de buenas prácticas

### **Fecha de Entrega 17 de Febrero**

> Nota: la lista anterior de este entregable es sólo una guía para ayudarte a distribuir la carga de trabajo; puedes entregar más o menos elementos si es necesario. De igual manera, si entregas menos elementos en este punto, tendrás que cubrir los elementos restantes en el siguiente entregable.

## Tercer y último entregable
- Agrega un `linter` para poder hacer análisis estático de tu código.
- Has uso de algunas características avanzadas de Kotlin como: `inline functions`, `tail recursive`, `delegated properties` y `collection operations`
- Implementa `coroutines` y usa `suspend functions` con _Retrofit_.
- Implementa Hilt como _framework_ de inyección de dependencias.
- Implementa `navigation component` para navegar entre las distintas pantallas.
- Implementa RxJava2 o RxJava3, envuelve la respuesta de _Retrofit_ y crea un `observable` que vaya a ser observado o cambia la respuesta de _Retrofit_ a un `Observable`.
- Hacer uso de buenas prácticas

### **Fecha de Entrega Jueves 2 de Marzo**


> Importante: este es el último entregabble, por lo cual todos los requerimientos deben ser incluidos. Proveeremos retroalimentación de tu entregable y tendrás 3 días más para aplicar los cambios. En el tercer día, dejaremos de recibir cambios a las 11:00 a.m.



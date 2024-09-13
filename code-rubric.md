# Rubrica para la revisión de código
Este documento establece los criterios más comunes para revisar el código de un Wizeliner (en orden de relevancia)

| Criterio | Excelente | Bueno | Apto | En mejora |
|---|---|---|---|---|
| **Especificaciones del programa / Exactitud** | Sin errores, el programa siempre trabaja de forma correcta y cumple las especificaciones. | Pequeños detalles del programa no cumplen las especificaciones, el programa funciona de manera incorrecta con algunas interacciones. | Especificaciones importantes son incumplidas, el programa denota comportamiento incorrecto con frecuencia. | El programa funciona correctamente en situaciones muy específicas o no funciona. |
| **Legibilidad** | Sin error, el código es limpio, entendible y bien organizado. | Detalles menores con indentación consistente, uso de espacio en blanco, nombrado de variable, u organización general. | Al menos un detalle crítico con indentación, espacio en blanco, nombrado de variable u organización. | Problemas críticos con tres o cuatro categorías de legibilidad.
| **Desempeño del código** | Sin errores, se aborda de la mejor manera en cada caso. | N/A | Alguno de los enfoques no es escogido de la mejor manera en al menos una situación | Algunos detalles en el código pudieron haber sido completados de una forma más sencilla, rápida o de lo contrario con un mejor estilo. |
| **Documentación** | No hay errores, el código está comentado de forma correcta. | En al menos en una o dos situaciones el código podría haber mejorado haciendo uso de comentarios, o el código contiene comentarios de más. | No cuenta con encabezado el archivo, líneas de código complicadas o hay secciones sin comentarios o con falta de comentarios que generarían valor. | Sin encabezado en el archivo o sin comentarios. |
| **Especificaciones de la tarea** | Sin errores | N/A | Detalles menores den las especificaciones de la asignación son incumplidas, como nombrado de archivos incorrectos o instrucciones extra ejecutadas incorrectamente. | Detalles mayores de la especificación son incumplidas, así como instrucciones ignoradas o completamente incomprendidas. |

### Especificaciones del programa / Exactitud
Este es el criterio más importante. Un programa debe cumplir con sus especificaciones y correcto funcionamiento. Esto significa, que se comporta conforme al especificado, generando el resultado correcto para una variedad de casos.

### Legibilidad
El código necesita ser legible para ti y para terceros. Esto involucra:
* Usar la indentación de forma consistente (por ejemplo, en cada cuerpo de un método está indentado al mismo nivel)
* Agregar espacios en blanco (líneas en blanco, espaciado) dónde sea apropiado para facilitar la separación de distintas partes del código (por ejemplo, un espacio después de las comas en las listas, espacios en blanco entre los métodos o entre bloques de líneas relacionadas entre métodos, etc.)
* Nombrar variables con nombres significativos. Variables nombradas `A`, `B`, y `C` o `foo`, `bar`, y `baz` no aporta al entendimiento del propósito o información que almacena dicha variable. Nombres como `principal`, `maximo` y `contador` son más útiles. Las variables de ciclos son una excepción a la regla, las variables de los ciclos nombradas como `i`, `j`, etc. están bien.
* El código debe estar bien organizado. Los métodos deben estar bien definidos en una sección del programa, el código debería estar organizado en métodos de manera que los bloques de código que necesiten ser reutilizados estén contenidos dentro de métodos, y los métodos deben tener nombres significativos.

Por favor, usa de referencia la siguiente documentación:
- [Guía de estilo de Kotlin para Android](https://developer.android.com/kotlin/style-guide) 
Para tener una referencia de cómo se vería una legibilidad 'Excelente'.

### Documentación
Cada archivo que contiene código debería empezar con un comentario de encabezado. Al menos, este encabezado debe contener el nombre del archivo, una descripción de que es lo que hace el código y el nombre del autor (tú). Otros detalles que podrían incluirse son: la fecha en que fue escrito, una descripción más detallada del enfoque utilizado si el código fue complejo o pueda ser incomprensible, o referencias de los recursos que utilizaste para escribirlo.
Además, todo el código debe estar bien comentado. Esto requiere encontrar el balance entre agregar comentarios a todo, lo cual puede añadir más confusión y ruido innecesario en el código, y no agregar comentarios, lo cual deja al lector del código sin ningún apoyo para comprender la parte más compleja o secciones menos evidentes del código. En general, ten como objetivo agregar un comentario en cualquier línea de código que no puedas comprender tu mismo si vuelves a leer el mismo código en un mes sin haber pensado en el mismo en ese lapso.

## Desempeño del código
Regularmente, existen distintas maneras de escribir un programa que cumplan una especificación en particular, y regularmente, muchas de ellas son malas elecciones. Pueden ser malas elecciones porque pueden requerir más líneas de código (más tiempo y esfuerzo) del necesario, o pueden tomar mucho más tiempo de ejecución del necesario. Por ejemplo, una sección del código puede ser ejecutada 10 veces copiando y pegando el código 10 veces en fila o usando un simple ciclo `for`. La última es preferida, no solamente porque hace que sea más rápido escribir y leer el código después, sino porque es más fácil de modificar y mantener.

### Especificaciones de la tarea
Las tareas comúnmente contienen especificaciones y/o requerimientos fuera de los problemas de programación. Por ejemplo, la forma en la que nombras los archivos para publicarlos en la página del curso será especificada en la tarea. Otras instrucciones podrían ser incluidas, por lo cual te pedimos que leas las tareas con atención.

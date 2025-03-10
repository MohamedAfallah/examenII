[GITHUB](https://github.com/rifi45/examenII)

## Protocolo de desarrollo.
he usado el protocolo TCP puesto que es orientado a conexión y es mas seguro a la hora de intercambiar mensajes, en este caso se asegura de que los clientes reciban los datos de manera correcta y que los envien correctamente sin que se pierdan. La velocidad de transmisión de datos en este caso no tiene un gran papel por eso he optado por TCP 100%.

## Tratamiento de excepciones.
Para las excepciones tengo excepciones de entrada y salida tipo IOExcepcion y otra excepcion cuando el cliente envia un simbolo que no es el correcto(*, /, +, -). No se ha controlado la excepción del NumberFormat para comprobar los ints puesto que los clientes los creo yo con un new Cliente() y me da error directamente si no se introducen numeros.
## fichero log.
Para el fichero log, he creado una clase que me crea el fichero para si en caso de que quiera añadir un log para cada cliente o algo, se puede hacer de manera fácil.

Los levels que usado en el log del servidor son 2, INFO para información del inicio del servidor y WARNING para cuando ocurre un error en el servidor o el simbolo de operacion no es el correcto.
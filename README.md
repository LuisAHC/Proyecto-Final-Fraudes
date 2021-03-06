# Proyecto-Final-Fraudes
_Este proyecto se encuentra dockerizado._

_Para ver como ejecutar los contenedores docker refierase a la sección [Docker](#docker) de este README_
## Proyecto final para la academia de microservicios de IBM
Esta es una API que permite:
1. Dada una dirección IP, encontrar el país al que pertenece y mostrar:
   - El nombre y el código ISO del país
   - Moneda local y su cotización actual en dólares o euros
2. Marcar la IP en una lista negra no permitiéndole consultar la información del punto 1

Para cumplir con los requisitos anteriores se hizo uso de las siguientes APIs:
- [Fixer](https://fixer.io/) 
- [Currency Converter API](https://free.currencyconverterapi.com/) (En caso de que Fixer no se encuentre disponible)
- [IP2Country](https://ip2country.info/)
- [REST Countries](https://restcountries.com/)

## Implementación
A continuación, se enlistan las tecnologías y características con las que cuenta este proyecto.
1. Spring Boot Web
2. Eureka
3. Servidor de enrutamiento dinámico (Api Gateway Spring Cloud)
3. Resilience4J (Tolerancia a fallos, latencia y timeout)
4. Logs
5. Test Unitarios (JUnit, Mockito y Spring Boot Test)
6. Base de Datos en memoria (H2)
7. Consumo de Apis (Feign)
8. Métodos encapsulados (Response Entity)
9. Patrones de Diseño y/o arquitectónicos (DTO, Chain Of Responsability)
10. Para el micro servicio principal, escalamiento dinámico (puertos random)
11. Buenas practicas de programación
12. Programación Orientada a Objetos
13. Métodos documentados
15. Principios SOLID

## Funcionamiento
A continuación, se demuestra el funcionamiento del proyecto

### Buscar información del país al que pertenece una IP dada.

**Endpoint**: _http://localhost:8090/fraudes/api-fraudes/cliente/buscar_info/{ip}/_

La imagen 1 muestra una consulta exitosa.

![imagen 1](https://user-images.githubusercontent.com/25095612/156478285-3d3ac3f0-3c78-48c7-8170-4e867bab01cf.png)
<p align="center">
   Imagen 1
</p>

------------------------------------------------------------

La imagen 2 muestra una consulta a una IP que se encuentra en lista negra.


![imagen 2](https://user-images.githubusercontent.com/25095612/156472837-0098060a-1a6a-4109-9185-ca4bd49a7bd7.png)
<p align="center">
   Imagen 2
</p>

------------------------------------------------------------

La imagen 3 muestra la toleracia a fallos del endpoint.


![imagen 3](https://user-images.githubusercontent.com/25095612/156473261-c4cd2825-1c8a-41eb-95be-4c1732b998f2.png)
<p align="center">
   Imagen 3
</p>

------------------------------------------------------------

### Lista negra de IPs.
**Endpoint**: _http://localhost:8090/fraudes/api-fraudes/admin/banear/{ip}/_

La imagen 4 muestra una petición para añadir una IP a la lista negra exitosa.


![imagen 4](https://user-images.githubusercontent.com/25095612/156473579-68d0a418-f220-4117-96c0-30e26e6a4d71.png)
<p align="center">
   Imagen 4
</p>

------------------------------------------------------------

La imagen 5 muestra una petición para añadir una IP a la lista negra no exitosa.


![imagen 5](https://user-images.githubusercontent.com/25095612/156473673-11c6fc01-32f1-4be9-a1ad-a765e90cb4d4.png)
<p align="center">
   Imagen 5
</p>

## Docker
_Es necesario tener Docker instalado en la computadora en la que se quiera desplegar el proyecto_ [Obtener Docker](https://www.docker.com/get-started)

Para comenzar se debe crear una red a la que estarán asociados todos los contendores, para crearla utilice el siguiente comando en una terminal.
`docker network create springcloud`
Una vez creada la red podemos crear los contenedores.

### Construir y desplegar contenedores
Abrir una terminal dentro de la carpeta Eureka y ejecutar los siguientes comandos:
   ```
   docker build -t eureka:v1 .
   docker run -p 8761:8761 --name eureka --network springcloud eureka:v1
   ```
Abrir una terminal dentro de la carpeta Fraudes y ejecutar los siguientes comandos:
   ```
   docker build -t fraudes:v1 .
   docker run -P --name fraudes --network springcloud fraudes:v1
   ```
Abrir una terminal dentro de la carpeta Gateway y ejecutar los siguientes comandos:
   ```
   docker build -t gateway:v1 .
   docker run -p 8090:8090 --name gateway --network springcloud gateway:v1
   ```  
Después de ejecutar los comandos anteriores nuestra lista de contenedores se verá de la siguiente manera en Docket Desktop.

![Docker Desktop](https://user-images.githubusercontent.com/25095612/156476062-6c8d967f-178e-4a62-a2ac-504ebf5884f3.png)
<p align="center">
   Imagen 6
</p>

Y listo, ahora se puede acceder a cualquiera de los endpoints del proyecto.

# Proyecto-Final-Fraudes
## Proyecto final para la academia de microservicios de IBM
Esta es una API que permite:
1. Dada una dirección IP, encontrar el país al que pertenece y mostrar:
   - El nombre y el código ISO del país
   - Moneda local y su cotización actual en dólares o euros
2. Marcar la IP en una lista negra no permitiéndole consultar la información del punto 1

Para cumplir con los requisitos anteriores se hizo uso de las siguientes APIs:
- [Currency Converter API](https://free.currencyconverterapi.com/)
- [Fixer](https://fixer.io/) (En caso de que Currency Converter API no se encuentre disponible)
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


![imagen 1](https://user-images.githubusercontent.com/25095612/156471229-9f58ac54-d90a-4de2-909a-abe5ee6b186d.png)
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

------------------------------------------------------------

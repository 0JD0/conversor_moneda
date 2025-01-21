# Conversor de Monedas (API de ExchangeRate)

Este proyecto es una herramienta simple en Java que permite convertir una cantidad en **USD** (Dólar estadounidense) a otras monedas utilizando las tasas de cambio en tiempo real proporcionadas por la API **ExchangeRate API**.

## Descripción

El programa obtiene las tasas de conversión de una API pública y permite al usuario seleccionar la moneda a la que desea convertir una cantidad en USD. Las monedas disponibles son:

- ARS - Peso argentino
- BOB - Boliviano
- BRL - Real brasileño
- CLP - Peso chileno
- COP - Peso colombiano
- USD - Dólar estadounidense

El usuario puede seleccionar la moneda, ingresar la cantidad en USD y el programa mostrará el resultado de la conversión.

## Requisitos

- **Java 8 o superior** instalado en tu sistema.
- **Internet** para obtener las tasas de conversión desde la API.

## Dependencias

Este proyecto usa la librería **Gson** para manejar la conversión de JSON. Asegúrate de incluir la dependencia de **Gson** en tu proyecto.

Si estás utilizando Maven, agrega la siguiente dependencia a tu archivo `pom.xml`:

```xml
<dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
    <version>2.8.8</version>
</dependency>
```

Si usas Gradle, agrega la siguiente línea en tu archivo `build.gradle`:

```
implementation 'com.google.code.gson:gson:2.8.8'
```

Si no usas un gestor de dependencias, puedes descargar la librería directamente desde Maven Central e incluir el archivo JAR en tu proyecto.

## Cómo usar

1. **Clona o descarga el repositorio**:
    
    Si usas Git, puedes clonar el repositorio con el siguiente comando:
    
    ```bash
    git clone https://github.com/tu-usuario/conversor-moneda.git
    ```
    
2. **Compilar y ejecutar el código**:
    - Compila el código utilizando tu IDE preferido (como IntelliJ IDEA, Eclipse, etc.).
    - Ejecuta la clase `MainClass` para iniciar el programa.
3. **Interacción con el programa**:
    
    El programa mostrará un menú con las opciones de monedas disponibles. Puedes ingresar el número correspondiente a la moneda a la que deseas convertir, luego ingresar la cantidad en **USD** y el resultado de la conversión será mostrado.
    
    Ejemplo de ejecución:
    
    ```markdown
    Selecciona la moneda a la que deseas convertir (0 para salir):
    1. ARS - Peso argentino
    2. BOB - Boliviano
    3. BRL - Real brasileño
    4. CLP - Peso chileno
    5. COP - Peso colombiano
    6. USD - Dólar estadounidense
    0. Salir
    Opción: 1
    Ingresa la cantidad en USD a convertir: 100
    100.00 USD equivalen a 35072.00 ARS
    ```
    
    **Nota**: Si seleccionas `0`, el programa terminará.
    

## Funcionamiento Interno

1. **Obtención de las tasas de conversión**:
El programa hace una solicitud HTTP GET a la API pública de **ExchangeRate API** para obtener las tasas de conversión más recientes desde **USD** hacia otras monedas.
2. **Conversión de la cantidad**:
Después de obtener las tasas de conversión, el programa convierte la cantidad proporcionada en **USD** a la moneda seleccionada por el usuario utilizando la tasa de conversión correspondiente.
3. **Menú interactivo**:
El programa ejecuta un bucle donde el usuario puede seleccionar diferentes monedas a las que desea convertir y repetir el proceso tantas veces como desee.

## Manejo de Errores

- Si la API no devuelve la tasa de conversión o si hay un error al obtener las tasas, el programa imprimirá un mensaje de error.
- Si el usuario ingresa una opción no válida, el programa le pedirá que seleccione una opción válida.
- Si el usuario ingresa un valor no numérico para la cantidad a convertir, el programa notificará el error y continuará solicitando entradas correctas.
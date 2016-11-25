# json-fast-builder
Simple and fast json builder

<h2>Example of use</h2>
```java
JsonObjectBuilder jsonObjectBuilder = new JsonObjectBuilder();
jsonObjectBuilder
        .put("Nombre", "Hernán")
        .put("Edad", 31)
        .put("Soltero", true)
        .put("Direccion", new JsonObjectBuilder()
                .put("Calle", "Lavalle")
                .put("Numero", 311)
                .put("CodigoPostal", 5501)
        )
        .put("Companeros", new JsonArrayBuilder()
                .put("Nikolas")
                .put("Ivan")
                .put("Mariano")
                .put("Facundo"))
        .putNull("campoNull");
jsonObjectBuilder.toString();
```

<h2>Result</h2>
```json
{"Nombre":"Hernán","Edad":31,"Soltero":true,"Direccion":{"Calle":"Lavalle","Numero":311,"CodigoPostal":5501},"Companeros":["Nikolas","Ivan","Mariano","Facundo"],"campoNull":null}
```

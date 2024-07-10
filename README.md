# altiora-service
aplicacion cliente con spring y angular

1. Construir la imagen del proyecto backend

    docker build -t altiora-app .

2. Ejecutar la imagen del backend

    docker run -p 8080:8080 -p 9000:9000 altiora-app

3. Ir al directorio de altiora-ui y ejecutar para bajar las edependencias

    npm install

4. ejecutar el proyecto front

    ng serve
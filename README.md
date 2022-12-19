Java 17 Spring boot, gradle, liquibase, postgres
Для запуска через консоль проверить настройки подключения к базе данных в файле application.properties
Собрать jar файл и поместить его в корень приложения, запустить командой:

java -jar clevertec-0.0.1-SNAPSHOT.jar 1-1 2-2 card-124 card.txt product.txt

При таких параметрах читает данные продуктов и карт, и записывает чек в файл WriteCheck.txt,
 которые расположены в корне
Если не указывать card.txt или product.txt берет данные из кода :

java -jar clevertec-0.0.1-SNAPSHOT.jar 3-3 2-1 card-124

Если не передаем параметр карты скидка на чек нулевая:

java -jar clevertec-0.0.1-SNAPSHOT.jar 3-3 2-1

Доступно получение чека через контроллер, передать id, count и card в этом случае он
берет данные из базы:

http://localhost:9080/check?id=2&count=2&card=card-123

Для запуска через docker изменить datasource в файле application.properties на
spring.datasource.url=jdbc:postgresql://postgres:5432/clevertec
Пересобрать jar, в терминале в конcоле запустить команду:

docker-compose up -d

DirectorBuilder класс собирает чек,
CreateBuilderByInitialData класс возвращает реализацию CheckBuilder в зависимости отправляли
ли мы имя файлов из которых будут читаться данные

SuperMarketCheckBuilder, SuperMarketCheckBuilderGetDataFromFile, SuperMarketCheckBuilderGetDataFromDB
реализации CheckBuilder для сборки чека
WriteCheckInformation класс записывает созданные чек в файл WriteCheck.txt

# Webpage words counter
## Описание
Приложение создано для сбора статистики слов с веб-страницы по URL.
Функционал позволяет выбрать текст из отображаемой части **body** html-страницы, и 
сформировать словарь ключ-значение со словом в вверхнем регистре и частотой его употребления.

Пример:
```
{
    "ИННОВАЦИОННЫЕ": 1,
    "В": 38,
    "КОМАНДА": 5,
    "РЕАЛЬНО": 1,
    "ПОНРАВИЛОСЬ": 1
}
```

### Запуск приложения
1. Предварительно должен быть запущен Docker, и исполнить файл docker-compose.yaml
2. Создать базу данных с названием ``word_statistics``
3. Запустить Spring boot приложение

*Параметры подключения к базе данных и всего Spring приложения хранятся по пути ``*/src/main/resources/application.yaml``*
###Проверка работоспособности приложения
Для удобности тестирования приложения использовался Swagger. Для получения информации и тестирования контроллера, 
можно пройти по ссылке:

``http://localhost:8081/api/v1.0/swagger-ui/index.html``

Было создано 3 метода:

1. ``GET``-``*/api/v1.0/word_statistics/parse_webpage?URL=https://www.simbirsoft.com/`` - позволяет получить статистику слов в формате JSON
2. ``POST``-``*/api/v1.0/word_statistics?URL=https://www.simbirsoft.com/`` - позволяет сформировать статистику, сохранить в базу данных и получить список сохраненных 
сущностей в формате JSON
3. ``GET``- ``*/api/v1.0/word_statistics?URL=https://www.simbirsoft.com/&page=1&size=12`` - позволяет получить страницу в формате JSON сущностей из базы данных 
по URL. Здесь ``page`` - номер страницы, а ``size``- ее размер. 

###Примечания
1. Логи работы Spring boot-приложения сохраняются в файл logfile.log в корень репозитория.
2. Протестирован парсинг метод сервиса ``HtmlWordsParserService``.
3. Комментарии и документация в приложеннии выполнены на английском языке.

### Используемые инструменты
- **IIntelliJ IDEA**
- **Spring boot** 
- **Docker** - для создания контейнера с базой данных
- **Postgresql** - как база данных
- **OpenAPI (Swagger)** - для документации и тестирования API
- **Jsoup** - для парсинга html-странцы
- **Junit**, **AspectJ** и **Mockito** - для тестирования
- **Liquibase** - для инициализации структуры базы данных
- **Project Lombok** - для упрощения разработки
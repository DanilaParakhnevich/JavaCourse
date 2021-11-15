<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%request.getSession().setAttribute("command", "result_page");%>
<html>
    <head>
        <title>Site</title>
    </head>
    <body>
        <small>Ниже вы можете получить результаты разбора xml-файла 3 парсерами со сведениями о банках</small>
        <form action="/xmlparsing/reader" method="post" enctype="multipart/form-data"/>
            <input type="text" name="xml"/>
            <input type="file" name="file"/>
            <br>
            <input type="submit"/>
            <select name="ParserType">
                <option value="1">DOM Parser</option>
                <option value="2">Sax Parser</option>
                <option value="3">StAX Parser</option>
            </select>

        </form>
        <style>
            body {background: #c7b39b url(https://img1.akspic.ru/originals/0/7/1/1/21170-luna-utro-cifrovoe_iskusstvo-minimalizm-gorizont-1920x1080.jpg)}
        </style>
    </body>
</html>
